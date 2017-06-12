package com.strettocorp.uberfood.restaurant_menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.strettocorp.uberfood.R;
import com.strettocorp.uberfood.entity.Restaurant;
import com.strettocorp.uberfood.essentials.SingleFragmentActivity;

public class RestaurantMenuActivity extends SingleFragmentActivity {

    private static String mRestaurant = null;
    @Override
    protected Fragment createFragment() {
        if(mRestaurant == null)
            return RestaurantMenuFragment.newInstance();
        else
            return RestaurantMenuFragment.newInstance(mRestaurant);
    }

    public static Intent newIntent(Context context, Restaurant restaurant) {
        mRestaurant = restaurant.toString();
        return new Intent(context, RestaurantMenuActivity.class);
    }
}
