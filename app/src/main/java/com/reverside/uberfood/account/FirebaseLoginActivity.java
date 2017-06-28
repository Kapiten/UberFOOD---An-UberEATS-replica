package com.reverside.uberfood.account;

import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.reverside.uberfood.R;
import com.reverside.uberfood.restaurant.RestaurantActivity;

/**
 * A login screen that offers login via email/password.
 */
public class FirebaseLoginActivity extends Activity {

    private static final int REQUEST_SIGNUP = 1;
    private static final String PARAM_PASSW = "com.reverside.uberfood.extras.para._passw";
    private static final String KEY_ERROR_MESSAGE = "ERR_MSG";
    //strettocorp
    private EditText mUsernameET, mPasswordET;
    private Button mSignInBTN, mForgotPasswordBTN, mSignUpBTN;

    private String mAuthTokenType;

    private FirebaseAuth mAuth;

    private ProgressDialog mProgress;

    public static Intent newAccountIntent(Context context,
                                          String accountType,
                                          String authType,
                                          Boolean isAddingNewAccount,
                                          AccountAuthenticatorResponse response) {
        Intent intent = new Intent(context, AccountActivity.class);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);

        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsernameET = (EditText) findViewById(R.id.launch_lgn_email);
        mPasswordET = (EditText) findViewById(R.id.launch_lgn_password);
        mSignInBTN = (Button) findViewById(R.id.launch_lgn_login_BTN);
        mForgotPasswordBTN = (Button) findViewById(R.id.launch_lgn_fgt_pword_BTN);
        mSignUpBTN = (Button) findViewById(R.id.launch_lgn_register_BTN);

        mProgress = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();

        mSignInBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //submit();
                mProgress.show();
                String email = mUsernameET.getText().toString();
                String password = mPasswordET.getText().toString();
                if(!email.isEmpty() && !password.isEmpty()) {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        //Toast.makeText(FirebaseLoginActivity.this, "Successful login.", Toast.LENGTH_SHORT).show();
                                        FirebaseUser currentUser = mAuth.getCurrentUser();
                                        updateUI(currentUser);
                                    } else {
                                        Toast.makeText(FirebaseLoginActivity.this, "Failed to login \n" +
                                                task.getException().getMessage() + "", Toast.LENGTH_SHORT).show();
                                        try {
                                            task.getException().printStackTrace();
                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                        }
                                        updateUI(null);
                                    }
                                    mProgress.hide();
                                }
                            });
                }
            }
        });

        mForgotPasswordBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(), OrderActivity.class));
            }
        });

        mSignUpBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
                //intent.putExtras(getIntent().getExtras());
                //startActivityForResult(intent, REQUEST_SIGNUP);
                mProgress.show();
                String email = mUsernameET.getText().toString();
                String password = mPasswordET.getText().toString();
                if(!email.isEmpty() && !password.isEmpty()) {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(FirebaseLoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        Toast.makeText(FirebaseLoginActivity.this, "Successful login.", Toast.LENGTH_SHORT).show();
                                        FirebaseUser currentUser = mAuth.getCurrentUser();
                                        updateUI(currentUser);
                                    } else {
                                        Toast.makeText(FirebaseLoginActivity.this, "Failed to authenticate user \n" +
                                                task.getException().getMessage() + "", Toast.LENGTH_SHORT).show();
                                        try {
                                            task.getException().printStackTrace();
                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                        }
                                        updateUI(null);
                                    }
                                    mProgress.hide();
                                }
                            });
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        if(user != null) {
            startActivity(new Intent(this, RestaurantActivity.class));
        }
    }

    String authToken = null;
    /*public void submit() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                final String username = mUsernameET.getText().toString();
                final String password = mPasswordET.getText().toString();

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
                }.execute();
            }
        });

    }*/

    /*private void finishLogin(Intent intent) {
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

        //setAccountAuthenticatorResult(intent.getExtras());
        setResult(RESULT_OK, intent);
        finish();
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP && resultCode == RESULT_OK) {
            //finishLogin(data);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}

