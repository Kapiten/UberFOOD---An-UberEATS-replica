package com.reverside.uberfood.order;

import android.support.v4.app.Fragment;

import com.reverside.uberfood.essentials.SingleFragmentActivity;

public class OrderActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return OrderFragment.newInstance();
    }
}
