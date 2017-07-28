package com.reverside.uberfood.restaurant;

import android.support.v4.app.Fragment;

import com.reverside.uberfood.essentials.SingleFragmentActivity;

public class RestaurantActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return RestaurantFragment.newInstance();
    }
}
