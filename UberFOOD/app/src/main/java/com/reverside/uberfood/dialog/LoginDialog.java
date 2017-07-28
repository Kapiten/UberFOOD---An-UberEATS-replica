package com.reverside.uberfood.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.reverside.uberfood.R;
import com.reverside.uberfood.account.AccountActivity;
import com.reverside.uberfood.account.FirebaseLoginActivity;
import com.reverside.uberfood.change_password.ChangePasswordActivity;
import com.reverside.uberfood.essentials.Constants;
import com.reverside.uberfood.essentials.Constants.RestResponseCodes;
import com.reverside.uberfood.essentials.Self;
import com.reverside.uberfood.restaurant.RestaurantActivity;

/**
 * Created by StrettO on 2017/06/23.
 */

public class LoginDialog extends DialogFragment {

    private Button mLoginBTN, mRegisterBTN, mForgotPasswBTN;
    private EditText mUsernameET, mPasswordET;
    private ScrollView mLaunchSV;
    private LinearLayout mLaunchLL;
    private ProgressDialog mProgressDialog;

    private FirebaseAuth mAuth;

    public static LoginDialog newInstance() {
        return new LoginDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_login, null);
        mLoginBTN = (Button) view.findViewById(R.id.launch_lgn_login_BTN);
        mRegisterBTN = (Button) view.findViewById(R.id.launch_lgn_register_BTN);
        mForgotPasswBTN = (Button) view.findViewById(R.id.launch_lgn_fgt_pword_BTN);
        mUsernameET = (EditText) view.findViewById(R.id.launch_lgn_usrname_ET);
        mPasswordET = (EditText) view.findViewById(R.id.launch_lgn_pword_ET);
        mLaunchSV = (ScrollView) view.findViewById(R.id.launch_SV);
        mLaunchLL = (LinearLayout) view.findViewById(R.id.launch_lgn_LL);

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("Logging in...");
        mProgressDialog.setCancelable(false);

        mAuth = FirebaseAuth.getInstance();

        mLoginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mUsernameET.getText().toString();
                final String password = mPasswordET.getText().toString();

                mProgressDialog.show();
                if(!email.isEmpty() && !password.isEmpty()) {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        FirebaseUser currentUser = mAuth.getCurrentUser();
                                        updateUI(currentUser);
                                    } else {
                                        new Self.ServComBuilder(getActivity())
                                                .loginUser(email, password)
                                                .setServResponse(new Self.ServComBuilder.ServResponse() {
                                                    @Override
                                                    public void onResponse(int responseCode, String response) {
                                                        if(responseCode == RestResponseCodes.SUCCESS) {
                                                            mProgressDialog.hide();
                                                            startActivity(new Intent(getActivity(), RestaurantActivity.class));
                                                            dismiss();
                                                        } else {
                                                            mProgressDialog.hide();
                                                            updateUI(null);
                                                            Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                    @Override
                                                    public void onResponse(Object object) {

                                                    }
                                                });

                                    }
                                    mProgressDialog.hide();
                                }
                            });
                }
            }
        });

        mForgotPasswBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ChangePasswordActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
            }
        });

        mRegisterBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getActivity(), AccountActivity.class)
                 //       .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
                new Self.ServComBuilder(getActivity())
                        .testOrder()
                        .setServResponse(new Self.ServComBuilder.ServResponse() {
                            @Override
                            public void onResponse(int responseCode, String response) {
                                if(responseCode == RestResponseCodes.SUCCESS) {
                                    mProgressDialog.hide();
                                    startActivity(new Intent(getActivity(), RestaurantActivity.class));
                                    dismiss();
                                } else {
                                    mProgressDialog.hide();
                                    updateUI(null);
                                    Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onResponse(Object object) {

                            }
                        });
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
    }

    private void updateUI(FirebaseUser currentUser) {
        if(currentUser != null) {
            startActivity(new Intent(getActivity(), RestaurantActivity.class));
            dismiss();
        } else {
            Toast.makeText(getActivity(), "Failed to login", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
}
