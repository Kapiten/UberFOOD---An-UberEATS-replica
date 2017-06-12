package com.strettocorp.uberfood.restaurant;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.strettocorp.uberfood.R;
import com.strettocorp.uberfood.essentials.SingleFragmentActivity;

public class RestaurantActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return RestaurantFragment.newInstance();
    }
}
