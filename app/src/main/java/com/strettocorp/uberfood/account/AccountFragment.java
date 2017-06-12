package com.strettocorp.uberfood.account;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.strettocorp.uberfood.R;
import com.strettocorp.uberfood.cart.CartActivity;
import com.strettocorp.uberfood.essentials.Self;

/**
 * A placeholder fragment containing a simple view.
 */
public class AccountFragment extends Fragment {

    private ImageView mProPicIV;
    private EditText mFirstNameET, mLastNameET, mEmailET, mMobileET, mPasswordET, mPasswordVerifyET;
    private ProgressDialog mProgressDialog;


    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_account, container, false);

        ((AppCompatActivity)getActivity()).setSupportActionBar((Toolbar) view.findViewById(R.id.toolbar));

        mFirstNameET = (EditText) view.findViewById(R.id.account_firstname_ET);
        mLastNameET = (EditText) view.findViewById(R.id.account_lastname_ET);
        mEmailET = (EditText) view.findViewById(R.id.account_email_ET);
        mMobileET = (EditText) view.findViewById(R.id.account_mobile_ET);
        mPasswordET = (EditText) view.findViewById(R.id.account_new_password_ET);
        mPasswordVerifyET = (EditText) view.findViewById(R.id.account_verify_password_ET);

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("Registering...");
        mProgressDialog.setCancelable(false);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_account, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_done:
                register();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void register() {
        String firstname = mFirstNameET.getText().toString();
        String lastname = mLastNameET.getText().toString();
        String email = mEmailET.getText().toString();
        String mobile = mMobileET.getText().toString();
        String password = mPasswordVerifyET.getText().toString();
        Self self = Self.get(getActivity());
        mProgressDialog.show();
        self.registerUser(email, password, firstname, lastname, mobile);
        self.setServResponse(new Self.ServResponse() {
            @Override
            public void onResponse(int responseCode) {
                if(responseCode == 1) {
                    mProgressDialog.hide();
                    Toast.makeText(getActivity(), "Welcome to UberFood", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getActivity(), CartActivity.class));
                } else {
                    mProgressDialog.hide();
                    Toast.makeText(getActivity(), "Something went wrong: " + responseCode, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
