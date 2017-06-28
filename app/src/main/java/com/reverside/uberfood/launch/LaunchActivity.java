package com.reverside.uberfood.launch;

import android.support.v4.app.Fragment;

import com.reverside.uberfood.essentials.SingleFragmentActivity;

public class LaunchActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return LaunchFragment.newInstance();
    }
}
