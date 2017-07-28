package com.reverside.uberfood.custom;

import android.content.Context;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.reverside.uberfood.R;

/**
 * Created by StrettO on 2017/06/11.
 */

public class CustomRadioButton extends AppCompatRadioButton {

    public CustomRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public View getRootView() {
        LayoutInflater inflater =
                LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.list_item_prep_radio, null);
        return view;
    }
}
