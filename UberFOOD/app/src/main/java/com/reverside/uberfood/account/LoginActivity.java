package com.reverside.uberfood.account;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.reverside.uberfood.R;
import com.reverside.uberfood.entity.User;
import com.reverside.uberfood.essentials.Constants.*;
import com.reverside.uberfood.order.OrderActivity;

import static com.reverside.uberfood.essentials.Constants.AccountDetails.sServerAuthenticate;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AccountAuthenticatorActivity {

    private static final String EXTRA_ACCOUNT_NAME = "com.reverside.uberfood.extras.account_name";
    private static final String EXTRA_ACCOUNT_TYPE = "com.reverside.uberfood.extras.account_type";
    private static final String EXTRA_AUTH_TYPE = "com.reverside.uberfood.extras.auth_type";
    private static final String EXTRA_IS_ADDING_ACCOUNT = "com.reverside.uberfood.extras.is_adding_account";
    private static final int REQUEST_SIGNUP = 1;
    private static final String PARAM_PASSW = "com.reverside.uberfood.extras.para,_passw";
    private static final String KEY_ERROR_MESSAGE = "ERR_MSG";

    private EditText mUsernameET, mPasswordET;
    private Button mSignInBTN, mForgotPasswordBTN, mSignUpBTN;

    private AccountManager mAccountManager;
    private String mAuthTokenType;

    public static Intent newAccountIntent(Context context,
                                          String accountType,
                                          String authType,
                                          Boolean isAddingNewAccount,
                                          AccountAuthenticatorResponse response) {
        Intent intent = new Intent(context, AccountActivity.class);
        intent.putExtra(EXTRA_ACCOUNT_TYPE, accountType);
        intent.putExtra(EXTRA_AUTH_TYPE, authType);
        intent.putExtra(EXTRA_IS_ADDING_ACCOUNT, isAddingNewAccount);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);

        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAccountManager = AccountManager.get(getApplicationContext());

        mUsernameET = (EditText) findViewById(R.id.launch_lgn_email);
        mPasswordET = (EditText) findViewById(R.id.launch_lgn_password);
        mSignInBTN = (Button) findViewById(R.id.launch_lgn_login_BTN);
        mForgotPasswordBTN = (Button) findViewById(R.id.launch_lgn_fgt_pword_BTN);
        mSignUpBTN = (Button) findViewById(R.id.launch_lgn_register_BTN);

        String accountName = getIntent().getStringExtra(EXTRA_ACCOUNT_NAME);
        mAuthTokenType = getIntent().getStringExtra(EXTRA_AUTH_TYPE);
        if(mAuthTokenType == null) {
            mAuthTokenType = AccountDetails.AUTHTOKEN_TYPE_FULL_ACCESS;
        }

        if(accountName != null) {
            Log.i(getClass().getSimpleName(), "AccountName: " + accountName);
        }

        mSignInBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        mForgotPasswordBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), OrderActivity.class));
            }
        });

        mSignUpBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
                //intent.putExtras(getIntent().getExtras());
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP && resultCode == RESULT_OK) {
            finishLogin(data);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    String authToken = null;
    public void submit() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                final String username = mUsernameET.getText().toString();
                final String password = mPasswordET.getText().toString();
                final String accountType = getIntent().getStringExtra(EXTRA_ACCOUNT_TYPE);

                final Bundle data = new Bundle();

                try {
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    authToken = sServerAuthenticate.userSignIn(user, accountType);

                    sServerAuthenticate.setLoginResponse(new ServerComAuthenticate.LoginResponse() {
                        @Override
                        public void onResponse() {
                            data.putString(AccountManager.KEY_ACCOUNT_NAME, username);
                            data.putString(AccountManager.KEY_ACCOUNT_TYPE, accountType);
                            data.putString(AccountManager.KEY_AUTHTOKEN, authToken);
                            data.putString(PARAM_PASSW, password);

                            finishLogin(new Intent().putExtras(data));
                        }
                    });
                } catch (Exception ex){
                    data.putString(KEY_ERROR_MESSAGE, ex.getMessage());
                    Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                    ex.printStackTrace();
                }

                /*if (intent.hasExtra(KEY_ERROR_MESSAGE)) {
                } else {
                }
                new AsyncTask<Void, Void, Intent>() {
                    @Override
                    protected Intent doInBackground(Void... params) {

                        final Intent res = new Intent();
                        res.putExtras(data);
                        return res;
                    }

                    @Override
                    protected void onPostExecute(Intent intent) {

                    }
                }.execute();*/
            }
        });

    }

    private void finishLogin(Intent intent) {
        String accName = intent.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
        String accpassword = intent.getStringExtra(PARAM_PASSW);
        final Account account = new Account(accName, "com.reverside.uberfood.auth_type");

        if(getIntent().getBooleanExtra(EXTRA_IS_ADDING_ACCOUNT, false)) {
            String authToken = intent.getStringExtra(AccountManager.KEY_AUTHTOKEN);
            String authTokenType = mAuthTokenType;

            mAccountManager.addAccountExplicitly(account, accpassword, null);
            mAccountManager.setAuthToken(account, authTokenType, authToken);
        } else {
            mAccountManager.setPassword(account, accpassword);
        }

        setAccountAuthenticatorResult(intent.getExtras());
        setResult(RESULT_OK, intent);
        finish();
    }
}

