package com.strettocorp.uberfood.cart;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.strettocorp.uberfood.R;
import com.strettocorp.uberfood.custom.SwipeButton;
import com.strettocorp.uberfood.custom.SwipeButtonCustomItems;

/**
 * A placeholder fragment containing a simple view.
 */
public class CartFragment extends Fragment {

    public static CartFragment newInstance() {
        return new CartFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_cart, container, false);

        SwipeButton swipeButton = (SwipeButton) view.findViewById(R.id.cart_place_order_BTN);

        SwipeButtonCustomItems swipeBtnSettings = new SwipeButtonCustomItems() {
            @Override
            public void onSwipeConfirm() {
                Snackbar.make(getView(), "Placing Order", Snackbar.LENGTH_SHORT).show();
            }
        };

        swipeBtnSettings
                .setButtonPressText(">> NEW TEXT! >>")
                .setGradientColor1(0xFF888888)
                .setGradientColor2(0xFF666666)
                .setGradientColor2Width(60)
                .setGradientColor3(0xFF333333)
                .setPostConfirmationColor(0xFF888888)
                .setActionConfirmDistanceFraction(0.7)
                .setActionConfirmText("Action Confirmed");

        if (swipeButton != null) {
            swipeButton.setSwipeButtonCustomItems(swipeBtnSettings);
        }

        return view;
    }
}
