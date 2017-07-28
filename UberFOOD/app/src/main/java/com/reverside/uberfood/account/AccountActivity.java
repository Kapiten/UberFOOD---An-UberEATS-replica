package com.reverside.uberfood.account;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.reverside.uberfood.essentials.Constants;
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
