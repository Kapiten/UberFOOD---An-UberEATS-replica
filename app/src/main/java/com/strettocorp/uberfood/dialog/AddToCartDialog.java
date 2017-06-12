package com.strettocorp.uberfood.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.strettocorp.uberfood.R;
import com.strettocorp.uberfood.essentials.Self;
import com.strettocorp.uberfood.restaurant_menu.RestaurantMenuFragment;

/**
 * Created by StrettO on 2017/06/12.
 */

public class AddToCartDialog extends DialogFragment {

    private static final String ARG_NAME = "arg_name";

    private static final String SAVED_CURRENT_VAL = "saved_currectn_val";

    private TextView mTitleTV;
    private ImageButton mCloseBTN;
    private EditText mQuantityET;
    private Spinner mIntentionSPN;
    private Button mMinusBTN, mPlusBTN, mAddToCartBTN;

    private int mCurrentVal = 1;

    public static AddToCartDialog newInstance(String name) {
        Bundle arguments = new Bundle();
        arguments.putString(ARG_NAME, name);

        AddToCartDialog dialog = new AddToCartDialog();
        dialog.setArguments(arguments);

        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_add_to_cart, null);

        mTitleTV = (TextView) view.findViewById(R.id.dlg_title_TV);
        mCloseBTN = (ImageButton) view.findViewById(R.id.dlg_close_BTN);
        mQuantityET = (EditText) view.findViewById(R.id.dlg_cart_quantity_ET);
        mMinusBTN = (Button) view.findViewById(R.id.dlg_cart_minus_BTN);
        mPlusBTN = (Button) view.findViewById(R.id.dlg_cart_plus_BTN);
        mIntentionSPN = (Spinner) view.findViewById(R.id.dlg_intention_SPN);
        mAddToCartBTN = (Button) view.findViewById(R.id.dlg_add_to_cart_BTN);

        mTitleTV.setTypeface(Self.mUberFont);

        String title = getArguments().getString(ARG_NAME, "No Title");
        if(title != null) {
            mTitleTV.setText(title);
        }

        mMinusBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(getCurrentVal() > 1) {
                        setCurrentVal(getCurrentVal() - 1);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        mPlusBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getCurrentVal() < 99) {
                    setCurrentVal(getCurrentVal() + 1);
                }
            }
        });

        mAddToCartBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResult();
                dismiss();
            }
        });

        mCloseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(SAVED_CURRENT_VAL, getCurrentVal());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if(savedInstanceState != null) {
            setCurrentVal(savedInstanceState.getInt(SAVED_CURRENT_VAL));
        }
    }

    public int getCurrentVal() {
        return mCurrentVal;
    }

    public void setCurrentVal(int currentVal) {
        mCurrentVal = currentVal;
        mQuantityET.setText(String.valueOf(mCurrentVal));
        mAddToCartBTN.setText(getString(R.string.add_to_cart_qty, getCurrentVal()));
    }

    private void sendResult() {
        if (getTargetFragment() == null) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(RestaurantMenuFragment.EXTRA_ATC_QTY, getCurrentVal());

        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
    }
}
