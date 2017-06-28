package com.reverside.uberfood.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.reverside.uberfood.R;
import com.reverside.uberfood.essentials.Self;
import com.reverside.uberfood.restaurant_menu.RestaurantMenuFragment;

/**
 * Created by StrettO on 2017/06/12.
 */

public class AddToCartDialog extends DialogFragment {

    private static final String ARG_NAME = "arg_name";
    private static final String ARG_CUISINE_ID = "arg_cuisine_id";

    private static final String SAVED_CURRENT_VAL = "saved_current_val";
    private static final String SAVED_INTENTION_STR = "saved_intention_str";

    private TextView mTitleTV;
    private ImageButton mCloseBTN;
    private EditText mQuantityET;
    private Spinner mIntentionSPN;
    private Button mMinusBTN, mPlusBTN, mAddToCartBTN;
    private LinearLayout mIntentionLL;

    private int mCurrentVal = 1;
    private String mIntentionStr = "add";
    private Self mSelf;

    public static AddToCartDialog newInstance(String name) {
        Bundle arguments = new Bundle();
        arguments.putString(ARG_NAME, name);

        AddToCartDialog dialog = new AddToCartDialog();
        dialog.setArguments(arguments);

        return dialog;
    }

    public static AddToCartDialog newInstance(int cuisineId, String name) {
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_CUISINE_ID, cuisineId);
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

        mSelf = Self.get(getActivity());

        mTitleTV = (TextView) view.findViewById(R.id.dlg_title_TV);
        mCloseBTN = (ImageButton) view.findViewById(R.id.dlg_close_BTN);
        mQuantityET = (EditText) view.findViewById(R.id.dlg_cart_quantity_ET);
        mMinusBTN = (Button) view.findViewById(R.id.dlg_cart_minus_BTN);
        mPlusBTN = (Button) view.findViewById(R.id.dlg_cart_plus_BTN);
        mIntentionSPN = (Spinner) view.findViewById(R.id.dlg_intention_SPN);
        mAddToCartBTN = (Button) view.findViewById(R.id.dlg_add_to_cart_BTN);
        mIntentionLL = (LinearLayout) view.findViewById(R.id.dlg_intention_LL);

        mTitleTV.setTypeface(Self.mUberFont);

        String title = getArguments().getString(ARG_NAME, "No Title");
        if(title != null) {
            mTitleTV.setText(title);
        }

        int cuisineId = getArguments().getInt(ARG_CUISINE_ID, 0);
        if(cuisineId > 0) {
            checkItems(cuisineId).execute();
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

        mIntentionSPN.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setIntentionStr(getResources().getStringArray(R.array.dlg_atc_intention)[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
        outState.putString(SAVED_INTENTION_STR, getIntentionStr());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if(savedInstanceState != null) {
            setCurrentVal(savedInstanceState.getInt(SAVED_CURRENT_VAL));
            setIntentionStr(savedInstanceState.getString(SAVED_INTENTION_STR));
        }
    }

    private AsyncTask<Integer, Void, Boolean> checkItems(final int id) {
        final Handler handler = new Handler();

        return new AsyncTask<Integer, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Integer... params) {
                if(Self.get(getActivity())
                        .getOrderListItem(
                        Self.get(getActivity())
                                .getSelectedRestaurant(), id).getCuisineId() != null) {
                    return true;
                }
                /*for(OrderList orderList : Self.get(getActivity()).getOrderList(Self.get(getActivity()).getSelectedRestaurant())) {
                    if(orderList.getCuisineId().equals(id + "")) {
                        return true;
                    }
                }*/
                return false;
            }

            @Override
            protected void onPostExecute(final Boolean aVoid) {
                super.onPostExecute(aVoid);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(aVoid) {
                            int qty = Self.get(getActivity()).getOrderListItem(
                                    mSelf.getSelectedRestaurant(), id)
                                    .getQuantity();

                            setCurrentVal(qty);
                            mIntentionLL.setVisibility(View.VISIBLE);
                        } else {
                            mIntentionLL.setVisibility(View.GONE);
                        }
                    }
                });
            }
        };
    }

    public int getCurrentVal() {
        return mCurrentVal;
    }

    public void setCurrentVal(int currentVal) {
        mCurrentVal = currentVal;
        mQuantityET.setText(String.valueOf(mCurrentVal));

        if(!getResources().getStringArray(R.array.dlg_atc_intention)[1].equals(mIntentionStr)) {
            mAddToCartBTN.setText(getString(R.string.add_to_cart_qty, getCurrentVal()));
        }
    }

    public String getIntentionStr() {
        return mIntentionStr;
    }

    public void setIntentionStr(String intentionStr) {
        mIntentionStr = intentionStr;

        if (getResources().getStringArray(R.array.dlg_atc_intention)[1].equals(intentionStr)) {
            mAddToCartBTN.setBackground(getResources().getDrawable(R.drawable.btn_red));
            mMinusBTN.setEnabled(false);
            mPlusBTN.setEnabled(false);
            mQuantityET.setEnabled(false);
            mAddToCartBTN.setText("Remove Item");
        } else {
            mAddToCartBTN.setBackground(getResources().getDrawable(R.drawable.btn_green));
            mMinusBTN.setEnabled(true);
            mPlusBTN.setEnabled(true);
            mQuantityET.setEnabled(true);
            mAddToCartBTN.setText(getString(R.string.add_to_cart_qty, getCurrentVal()));
        }
    }

    private void sendResult() {
        if (getTargetFragment() == null) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(RestaurantMenuFragment.EXTRA_ATC_QTY, getCurrentVal());
        intent.putExtra(RestaurantMenuFragment.EXTRA_ATC_INTENTION, getIntentionStr());

        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
    }
}
