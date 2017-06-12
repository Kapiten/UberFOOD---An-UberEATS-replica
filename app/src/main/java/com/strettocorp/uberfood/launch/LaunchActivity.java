package com.strettocorp.uberfood.launch;

import android.support.design.internal.ScrimInsetsFrameLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.strettocorp.uberfood.R;
import com.strettocorp.uberfood.essentials.SingleFragmentActivity;

public class LaunchActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return LaunchFragment.newInstance();
    }
}
