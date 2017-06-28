package com.reverside.uberfood.cart;

import android.support.v4.app.Fragment;

import com.reverside.uberfood.essentials.SingleFragmentActivity;

public class CartActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return CartFragment.newInstance();
    }
}
