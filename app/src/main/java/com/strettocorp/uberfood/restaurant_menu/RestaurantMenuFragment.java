package com.strettocorp.uberfood.restaurant_menu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.strettocorp.uberfood.R;
import com.strettocorp.uberfood.dialog.AddToCartDialog;
import com.strettocorp.uberfood.entity.Cuisine;
import com.strettocorp.uberfood.entity.Restaurant;
import com.strettocorp.uberfood.essentials.DataLoader;
import com.strettocorp.uberfood.essentials.Self;
import com.strettocorp.uberfood.restaurant_menu_item.RestaurantMenuItemActivity;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class RestaurantMenuFragment extends Fragment {

    private static final String TAG = "RestaurantMenuFragment";

    public static final String EXTRA_ATC_QTY = "com.strettocorp.uberfood.extras";

    private static final String ARG_RESTAURANT = "arg_restaurant";
    private static final int REQUEST_ATC_QTY = 1;

    private TextView mTitleTV;
    private RecyclerView mMenuRV;
    private MenuItemAdapter mAdapter;
    private Restaurant mRestaurant;

    private Self mSelf;

    private DataLoader<MenuItemHolder> mDataLoader;

    public static RestaurantMenuFragment newInstance() {
        return new RestaurantMenuFragment();
    }

    public static RestaurantMenuFragment newInstance(String restaurant) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_RESTAURANT, restaurant);

        RestaurantMenuFragment fragment = new RestaurantMenuFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDataLoader = new DataLoader(getActivity(), new Handler());
        mDataLoader.setDataLoaderListener(new DataLoader.DataLoaderListener<MenuItemHolder>() {
            private MenuItemHolder mHolder;

            @Override
            public void onDataLoaded(MenuItemHolder holder) {
                mHolder = holder;
            }

            @Override
            public void onImageLoaded(Bitmap bitmap) {
                mHolder.bindPhoto(bitmap);
            }
        });
        mDataLoader.start();
        mDataLoader.getLooper();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_restaurant_menu, container, false);

        String restaurant = getArguments().getString(ARG_RESTAURANT);
        if(restaurant != null) {
            mRestaurant = Restaurant.fromString(restaurant);
        }

        mSelf = Self.get(getActivity());

        mTitleTV = (TextView) view.findViewById(R.id.rest_m_title_TV);
        mTitleTV.setTypeface(Self.mUberFont);
        mTitleTV.setText(mRestaurant.getName());

        TextView mOperHrs = (TextView) view.findViewById(R.id.rest_m_oper_hrs_TV);
        mOperHrs.setTypeface(Self.mUberFont);
        mOperHrs.setText(mSelf.getOperatingHrsString(mRestaurant.getOperatingHours()));

        mMenuRV = (RecyclerView) view.findViewById(R.id.rest_m_RV);

        mMenuRV.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadMenu();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mDataLoader.quit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mDataLoader.clearQueue();
    }

    private void loadMenu() {
        if(mAdapter == null) {
            mAdapter = new MenuItemAdapter(Self.getCuisines(mRestaurant.getRestaurantId()));
            mSelf.setServResponse(new Self.ServResponse() {
                @Override
                public void onResponse(int responseCode) {
                    mMenuRV.setAdapter(mAdapter);
                }
            });

        } else {
            mAdapter.setCuisines(Self.getCuisines(mRestaurant.getRestaurantId()));
            mSelf.setServResponse(new Self.ServResponse() {
                @Override
                public void onResponse(int responseCode) {
                    mAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    private class MenuItemHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{

        private RelativeLayout mItemDisplayRL, mItemNoteRL;
        private ImageView mItemIV;
        private TextView mItemTitleTV, mItemDescriptionTV, mItemPriceTV;
        private Cuisine mCuisine;

        public MenuItemHolder(View itemView) {
            super(itemView);

            mItemDisplayRL = (RelativeLayout) itemView.findViewById(R.id.rest_mitem_display_RL);
            mItemNoteRL = (RelativeLayout) itemView.findViewById(R.id.rest_mitem_note_RL);
            mItemIV = (ImageView) itemView.findViewById(R.id.rest_mitem_IV);
            mItemTitleTV = (TextView) itemView.findViewById(R.id.rest_mitem_title_TV);
            mItemDescriptionTV = (TextView) itemView.findViewById(R.id.rest_mitem_description_TV);
            mItemPriceTV = (TextView) itemView.findViewById(R.id.rest_mitem_price_TV);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            AddToCartDialog dialog = AddToCartDialog.newInstance(mCuisine.getName());
            dialog.setTargetFragment(RestaurantMenuFragment.this, REQUEST_ATC_QTY);
            dialog.show(getFragmentManager(), "AddToCartDlg");
        }

        private void bindMenuItem(Cuisine cuisine) {
            mCuisine = cuisine;
            mItemTitleTV.setText(cuisine.getName());
            mItemDescriptionTV.setText(cuisine.getDescription());
            mItemPriceTV.setText(cuisine.getPrice());
            if(cuisine.getProfilePic() != null && !cuisine.getProfilePic().isEmpty())
                mItemDisplayRL.setVisibility(View.VISIBLE);
            else
                mItemDisplayRL.setVisibility(View.GONE);
        }


        public void bindPhoto(Bitmap bitmap) {
            mItemIV.setImageBitmap(bitmap);
        }
    }

    private class MenuItemAdapter extends RecyclerView.Adapter<MenuItemHolder> {
        List<Cuisine> mCuisines;

        public MenuItemAdapter(List<Cuisine> cuisines) {
            mCuisines = cuisines;
        }

        @Override
        public MenuItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MenuItemHolder(LayoutInflater.from(getActivity())
                    .inflate(R.layout.list_item_food_item, parent, false));
        }

        @Override
        public void onBindViewHolder(MenuItemHolder holder, int position) {
            Cuisine cuisine = mCuisines.get(position);
            holder.bindMenuItem(cuisine);

            if(cuisine.getProfilePic() != null && !cuisine.getProfilePic().isEmpty())
                mDataLoader.queueImage(holder, cuisine.getProfilePic());
        }

        @Override
        public int getItemCount() {
            return mCuisines.size();
        }

        public void setCuisines(List<Cuisine> cuisines) {
            mCuisines = cuisines;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != Activity.RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case REQUEST_ATC_QTY:
                int result = data.getIntExtra(EXTRA_ATC_QTY, 1);
                //Toast.makeText(getActivity(), "Add " + result + " items in the cart", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
