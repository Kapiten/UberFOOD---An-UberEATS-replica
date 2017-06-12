package com.strettocorp.uberfood.restaurant_menu_item;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.strettocorp.uberfood.R;
import com.strettocorp.uberfood.custom.CustomPreparation;

/**
 * A placeholder fragment containing a simple view.
 */
public class RestaurantMenuItemFragment extends Fragment {

    private ImageView mDisplayIV;
    private TextView mTitleTV, mDescriptionTV;
    private LinearLayout mPrepLL;
    private Button mAddToCartBTN;
    private ImageButton mOnCartBTN;

    public static RestaurantMenuItemFragment newInstance() {
        return new RestaurantMenuItemFragment();
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

        /*final CustomPreparation preparation = new CustomPreparation(getActivity(), null);
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
        });*/

        return view;
    }
}
