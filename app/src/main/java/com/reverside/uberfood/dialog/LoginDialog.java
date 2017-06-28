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

import com.reverside.uberfood.R;
import com.reverside.uberfood.account.AccountActivity;
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

        mLoginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mUsernameET.getText().toString();
                String password = mPasswordET.getText().toString();

                mProgressDialog.show();
                new Self.ServComBuilder(getActivity())
                        .loginUser(email, password)
                        .setServResponse(new Self.ServComBuilder.ServResponse() {
                            @Override
                            public void onResponse(int responseCode, String response) {
                                if(responseCode == RestResponseCodes.SUCCESS) {
                                    mProgressDialog.hide();
                                    startActivity(new Intent(getActivity(), RestaurantActivity.class));
                                } else {
                                    mProgressDialog.hide();
                                    Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onResponse(Object object) {

                            }
                        });
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
                startActivity(new Intent(getActivity(), AccountActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
    }
}
