package com.reverside.uberfood.restaurant_menu;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.reverside.uberfood.entity.Restaurant;
import com.reverside.uberfood.essentials.SingleFragmentActivity;

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
