package com.reverside.uberfood.account;

import android.support.v4.app.Fragment;

import com.reverside.uberfood.essentials.SingleFragmentActivity;

public class AccountActivity extends SingleFragmentActivity {

    public AccountFragment mFragment;

    @Override
    protected Fragment createFragment() {
        return (mFragment = AccountFragment.newInstance());
    }

    @Override
    public void onBackPressed() {
        if(mFragment.onBack())
            super.onBackPressed();
    }
}
