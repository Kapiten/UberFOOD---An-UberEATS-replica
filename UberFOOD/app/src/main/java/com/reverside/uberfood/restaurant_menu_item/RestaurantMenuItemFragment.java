package com.reverside.uberfood.restaurant_menu_item;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.reverside.uberfood.custom.CustomPreparation;
import com.reverside.uberfood.R;
import com.reverside.uberfood.entity.Cuisine;
import com.reverside.uberfood.essentials.Self;

/**
 * A placeholder fragment containing a simple view.
 */
public class RestaurantMenuItemFragment extends Fragment {

    private static final String ARG_CUISINE_ID = "arg_cuisine_id";

    private ImageView mDisplayIV;
    private TextView mTitleTV, mDescriptionTV;
    private LinearLayout mPrepLL;
    private Button mAddToCartBTN;
    private ImageButton mOnCartBTN;
    private Self mSelf;

    public static RestaurantMenuItemFragment newInstance() {
        return new RestaurantMenuItemFragment();
    }

    public static RestaurantMenuItemFragment newInstance(int cuisineId) {
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_CUISINE_ID, cuisineId);

        RestaurantMenuItemFragment fragment = newInstance();
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_rest_menu_item, container, false);
        mDisplayIV = (ImageView) view.findViewById(R.id.rest_menu_item_display_IV);
        mTitleTV = (TextView) view.findViewById(R.id.rest_menu_item_title_TV);
        mDescriptionTV = (TextView) view.findViewById(R.id.rest_menu_item_description_TV);
        mPrepLL = (LinearLayout) view.findViewById(R.id.rest_menu_item_prep_LL);
        mAddToCartBTN = (Button) view.findViewById(R.id.rest_menu_item_add_to_cart_BTN);
        mOnCartBTN = (ImageButton) view.findViewById(R.id.rest_menu_item_on_cart_BTN);

        mSelf = Self.get(getActivity());

        final int cuisineId = getArguments().getInt(ARG_CUISINE_ID);

        if(cuisineId > 0) {
            new Self.ServComBuilder(getActivity())
                    .getCuisine(cuisineId)
                    .setServResponse(new Self.ServComBuilder.ServResponse<Cuisine>() {
                        @Override
                        public void onResponse(int responseCode, String response) {

                        }

                        @Override
                        public void onResponse(Cuisine cuisine) {
                            mTitleTV.setText(cuisine.getName());
                        }
                    });
        }

        final CustomPreparation preparation = new CustomPreparation(getActivity(), null);
        preparation.setTitle("Sauces");
        preparation.setRequired(true);

        mPrepLL.addView(preparation, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        preparation.addItem(CustomPreparation.ItemType.Check, "Mustard", "R2.50");
        preparation.addItem(CustomPreparation.ItemType.Check, "Tomato Sauce", "R2.50");
        preparation.addItem(CustomPreparation.ItemType.Check, "Mayo", "R2.50");

        final CustomPreparation preparation2 = new CustomPreparation(getActivity(), null);
        preparation2.setTitle("Spices");

        mPrepLL.addView(preparation2, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        preparation2.addItem(CustomPreparation.ItemType.Radio, "Low", "");
        preparation2.addItem(CustomPreparation.ItemType.Radio, "Mild", "");
        preparation2.addItem(CustomPreparation.ItemType.Radio, "Hot", "");

        mAddToCartBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prep1Str = "";
                for(CustomPreparation.PrepItem prepItem : preparation.getPrepItems()) {
                    if(prepItem.isChecked()) {
                        prep1Str += prepItem.getName() + " | ";
                    }
                }

                String prep2Str = "";
                for(CustomPreparation.PrepItem prepItem : preparation2.getPrepItems()) {
                    if(prepItem.isChecked()) {
                        prep2Str += prepItem.getName() + " | ";
                    }
                }

                Toast.makeText(getActivity(), prep1Str, Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), prep2Str, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
