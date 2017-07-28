package com.reverside.uberfood.change_password;

import android.support.v4.app.Fragment;

import com.reverside.uberfood.essentials.SingleFragmentActivity;

public class ChangePasswordActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return ChangePasswordFragment.newInatance();
    }
}
