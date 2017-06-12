package com.strettocorp.uberfood.restaurant_menu_item;

import android.support.v4.app.Fragment;

import com.strettocorp.uberfood.essentials.SingleFragmentActivity;

public class RestaurantMenuItemActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return RestaurantMenuItemFragment.newInstance();
    }
}
