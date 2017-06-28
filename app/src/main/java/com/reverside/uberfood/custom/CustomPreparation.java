package com.reverside.uberfood.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.reverside.uberfood.R;
import com.reverside.uberfood.essentials.Constants.JSON_Tags;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by StrettO on 2017/05/28.
 */

public class CustomPreparation extends LinearLayout {

    private static final String TAG = "CustomPreparation";
    public static final int SELECT_ALL = 0;
    public static final int SELECT_ONE = 1;

    public static final class ItemType {
        public static final int Check = 0;
        public static final int Radio = 1;
    }

    private TextView mTitleTV, mRequiredTV, mSpecialIntrsTV, mPrepItemsPriceTV;
    private ViewFlipper mPrepItemsVF;
    private RadioGroup mPrepItemsRG;
    private RadioButton mPrepItemsRB;
    private CheckBox mPrepItemsCB;
    private LinearLayout mPrepNoteLL;
    private EditText mPrepNoteET;
    private LinearLayout mPrepItemsLL;
    private String mTitle, mItemName, mItemPrice;
    private boolean mSelected, mRequired;
    private List<PrepItem> mPrepItems;

    public CustomPreparation(Context context) {
        super(context);
        init(context, null);
    }

    public CustomPreparation(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_preparation, this);
        mTitleTV = (TextView) view.findViewById(R.id.prep_title_TV);
        mRequiredTV = (TextView) view.findViewById(R.id.prep_required_TV);
        mPrepNoteET = (EditText) view.findViewById(R.id.prep_note_ET);
        mPrepItemsLL = (LinearLayout) view.findViewById(R.id.prep_items_LL);
        mPrepItems = new ArrayList<>();

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.CustomPreparationAttrs, 0, 0);

        try {
            setTitle(a.getString(R.styleable.CustomPreparationAttrs_prep_title));
            setRequired(a.getBoolean(R.styleable.CustomPreparationAttrs_prep_required, false));
            setSelected(a.getBoolean(R.styleable.CustomPreparationAttrs_prep_item_selected, false));
            setItemName(a.getString(R.styleable.CustomPreparationAttrs_prep_item_name));
            setItemPrice(a.getString(R.styleable.CustomPreparationAttrs_prep_item_price));
        } finally {
            a.recycle();
        }
    }

    public void addItem(int itemType, String name, String price) {
        LayoutInflater inflater =
                (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(itemType == ItemType.Check) {
            View view = inflater.inflate(R.layout.list_item_prep_check, null);
            CheckBox mItem = (CheckBox) view.findViewById(R.id.prep_items_CB);
            TextView mPrice = (TextView) view.findViewById(R.id.prep_items_price_TV);

            mItem.setText(name);
            mPrice.setText(price);

            mPrepItemsLL.addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            final PrepItem prepItem =new PrepItem(mPrepItems.size(), name, price);
            mPrepItems.add(prepItem);

            mItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mPrepItems.get(prepItem.getIndex()).setChecked(isChecked);
                    for(PrepItem prepItem1 : mPrepItems) {
                        Log.v(TAG, prepItem1.toString());
                    }
                }
            });
        } else if(itemType == ItemType.Radio) {
            View view = inflater.inflate(R.layout.list_item_prep_radio, null);
            RadioButton mItem =(RadioButton) view.findViewById(R.id.prep_items_RB);
            TextView mPrice = (TextView) view.findViewById(R.id.prep_items_price_TV);

            mItem.setText(name);
            mPrice.setText(price);

            mPrepItemsLL.addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            final PrepItem prepItem =new PrepItem(mPrepItems.size(), name, price);
            mPrepItems.add(prepItem);

            mItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    for(View view : mPrepItemsLL.getTouchables()) {
                        if(view instanceof RadioButton) {
                            ((RadioButton) view).setChecked(false);
                        }
                    }

                    buttonView.setChecked(isChecked);
                    mPrepItems.get(prepItem.getIndex()).setChecked(isChecked);
                    for(PrepItem prepItem1 : mPrepItems) {
                        Log.v(TAG, prepItem1.toString());
                    }
                }
            });
        }
    }

    public static class PrepItem {
        private int mIndex;
        private String mName;
        private String mPrice;
        private boolean mChecked = false;

        public PrepItem(int index, String name, String price) {
            mIndex = index;
            mName = name;
            mPrice = price;
        }

        public int getIndex() {
            return mIndex;
        }

        public void setIndex(int index) {
            mIndex = index;
        }

        public String getName() {
            return mName;
        }

        public void setName(String name) {
            mName = name;
        }

        public String getPrice() {
            return mPrice;
        }

        public void setPrice(String price) {
            mPrice = price;
        }

        public boolean isChecked() {
            return mChecked;
        }

        public void setChecked(boolean checked) {
            mChecked = checked;
        }

        @Override
        public String toString() {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("index", mIndex);
                jsonObject.put("name", mName);
                jsonObject.put("price", mPrice);
                jsonObject.put("checked", mChecked);
            } catch (JSONException ex) {
                ex.printStackTrace();
            }

            return jsonObject.toString();
        }
    }

    public List<PrepItem> getPrepItems() {
        return mPrepItems;
    }

    public void setPrepItems(List<PrepItem> prepItems) {
        mPrepItems = prepItems;
    }

    public List<PrepItem> getSelectedItems() {
        List<PrepItem> items = new ArrayList<>();
        for (PrepItem prepItem : mPrepItems) {
            if(prepItem.isChecked()) {
                items.add(prepItem);
            }
        }

        return items;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;

        if(mTitle != null && mTitleTV != null) {
            mTitleTV.setText(mTitle);
        }
    }

    public String getItemName() {
        return mItemName;
    }

    public void setItemName(String itemName) {
        mItemName = itemName;
    }

    public String getItemPrice() {
        return mItemPrice;
    }

    public void setItemPrice(String itemPrice) {
        mItemPrice = itemPrice;
    }

    @Override
    public boolean isSelected() {
        return mSelected;
    }

    @Override
    public void setSelected(boolean selected) {
        mSelected = selected;
    }

    public boolean isRequired() {
        return mRequired;
    }

    public void setRequired(boolean required) {
        mRequired = required;

        if(mRequired) {
            mRequiredTV.setVisibility(VISIBLE);
        } else {
            mRequiredTV.setVisibility(GONE);
        }
    }

    public String toJSONString(){
        JSONObject obj = new JSONObject();

        try {
            JSONArray jsonArray = new JSONArray();
            for (PrepItem prepItem : mPrepItems) {
                if(prepItem.isChecked()) {
                    jsonArray.put(prepItem.toString());
                }
            }

            obj.put(JSON_Tags.EXTRAS, jsonArray.toString());
            obj.put(JSON_Tags.SPEC_INSTRU, mPrepNoteET.getText().toString());
        } catch (JSONException ex) {
            ex.printStackTrace();
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return obj.toString();
    }
}
