package com.reverside.uberfood.restaurant_menu_item;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.reverside.uberfood.essentials.SingleFragmentActivity;

public class RestaurantMenuItemActivity extends SingleFragmentActivity {

    private static final String EXTRA_CUISINE_ID = "com.strettocorp.uberfood.extras.extra_cuisine_id";

    @Override
    protected Fragment createFragment() {
        return RestaurantMenuItemFragment.newInstance(getIntent().getIntExtra(EXTRA_CUISINE_ID, 0));
    }

    public static Intent newIntent(Context context, int cuisineId) {
        Intent intent = new Intent(context, RestaurantMenuItemActivity.class);
        intent.putExtra(EXTRA_CUISINE_ID, cuisineId);

        return intent;
    }
}
