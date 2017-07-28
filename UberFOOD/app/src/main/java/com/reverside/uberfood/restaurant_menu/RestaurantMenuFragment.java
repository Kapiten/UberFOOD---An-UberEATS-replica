package com.reverside.uberfood.restaurant_menu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.reverside.uberfood.cart.CartActivity;
import com.reverside.uberfood.dialog.AddToCartDialog;
import com.reverside.uberfood.entity.CuisineList;
import com.reverside.uberfood.entity.Restaurant;
import com.reverside.uberfood.R;
import com.reverside.uberfood.entity.Cuisine;
import com.reverside.uberfood.essentials.Constants;
import com.reverside.uberfood.essentials.DataLoader;
import com.reverside.uberfood.essentials.Self;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class RestaurantMenuFragment extends Fragment {

    private static final String TAG = "RestaurantMenuFragment";

    public static final String EXTRA_ATC_QTY = "com.strettocorp.uberfood.extras.atc_qty";
    public static final String EXTRA_ATC_INTENTION = "com.strettocorp.uberfood.extras.atc_intention";

    private static final String ARG_RESTAURANT = "arg_restaurant";

    private static final int REQUEST_ATC_QTY = 1;

    private static final String SAVED_CUISINE = "saved_cuisine";

    private TextView mTitleTV;
    private RecyclerView mMenuRV;
    private MenuItemAdapter mAdapter;
    private Restaurant mRestaurant;
    private RelativeLayout mCheckOutRBTN;
    private TextView mCheckOutQtyTV;

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

        mDataLoader = new DataLoader<>(getActivity(), new Handler());
        mDataLoader.setDataLoaderListener(new DataLoader.DataLoaderListener<MenuItemHolder>() {
            private MenuItemHolder mHolder;

            @Override
            public void onDataLoaded(MenuItemHolder holder) {
                mHolder = holder;
            }

            @Override
            public void onImageLoaded(Bitmap bitmap) {
                //mHolder.bindPhoto(bitmap);
            }
        });
        mDataLoader.start();
        mDataLoader.getLooper();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_restaurant_menu, container, false);

        mSelf = Self.get(getActivity());
        String restaurant = getArguments().getString(ARG_RESTAURANT);
        if(restaurant != null) {
            mRestaurant = Restaurant.fromString(restaurant);
            mSelf.setSelectedRestaurant(mRestaurant);
        }

        mTitleTV = (TextView) view.findViewById(R.id.rest_m_title_TV);
        mTitleTV.setTypeface(Self.mUberFont);
        mTitleTV.setText(mRestaurant.getName());

        //TextView mOperHrs = (TextView) view.findViewById(R.id.rest_m_oper_hrs_TV);
        //mOperHrs.setTypeface(Self.mUberFont);
        //mOperHrs.setText(mSelf.getOperatingHrsString(mRestaurant.getOperatingHours()));

        mMenuRV = (RecyclerView) view.findViewById(R.id.rest_m_RV);

        mMenuRV.setLayoutManager(new LinearLayoutManager(getActivity()));

        mCheckOutRBTN = (RelativeLayout) view.findViewById(R.id.rest_m_check_out_RBTN);
        mCheckOutQtyTV = (TextView) view.findViewById(R.id.rest_m_check_out_qty_TV);

        mCheckOutRBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CartActivity.class));
            }
        });

        loadMenu();

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if(mSelf.getSelectedCuisine() != null) {
            outState.putString(SAVED_CUISINE, mSelf.getSelectedCuisine().toString());
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if(savedInstanceState != null) {
            String jsonStr = savedInstanceState.getString(SAVED_CUISINE);
            if(jsonStr != null && !jsonStr.isEmpty()) {
                mSelf.getSelectedCuisine().fromString(jsonStr);
            }
        }
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

    private void checkItems() {
        if(mSelf.getOrderList(mSelf.getSelectedRestaurant()).size() > 0) {
            if(mCheckOutRBTN.getVisibility() != View.VISIBLE)
                mCheckOutRBTN.setVisibility(View.VISIBLE);

            mCheckOutQtyTV.setText(mSelf.getOrderList(mSelf.getSelectedRestaurant()).size() + "");
        } else {
            mCheckOutRBTN.setVisibility(View.GONE);
        }
    }

    private void loadMenu() {
        if(mAdapter == null) {
            new Self.ServComBuilder(getActivity())
                    .getCuisinesByRestaurantId(mRestaurant.getRestaurantId())
                    .setServResponse(new Self.ServComBuilder.ServResponse<List<Cuisine>>() {
                        @Override
                        public void onResponse(int responseCode, String response) {

                        }

                        @Override
                        public void onResponse(List<Cuisine> cuisines) {
                            mAdapter = new MenuItemAdapter(cuisines);
                            mMenuRV.setAdapter(mAdapter);
                        }
                    });
        } else {
            new Self.ServComBuilder(getActivity())
                    .getCuisinesByRestaurantId(mRestaurant.getRestaurantId())
                    .setServResponse(new Self.ServComBuilder.ServResponse<List<Cuisine>>() {
                        @Override
                        public void onResponse(int responseCode, String response) {

                        }

                        @Override
                        public void onResponse(List<Cuisine> cuisines) {
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
            mSelf.setSelectedCuisine(mCuisine);
                
            AddToCartDialog dialog = AddToCartDialog.newInstance(mCuisine.getCuisineId(), mCuisine.getName());
            dialog.setTargetFragment(RestaurantMenuFragment.this, REQUEST_ATC_QTY);
            dialog.show(getFragmentManager(), "AddToCartDlg");
            //startActivity(RestaurantMenuItemActivity.newIntent(getActivity(), mSelf.getSelectedCuisine().getCuisineId()));
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

            Glide.with(getActivity())
                    .load("http://" +
                            Constants.GlassfishConnProps.URL + ":" +
                            Constants.GlassfishConnProps.PORT + "/" +
                            Constants.GlassfishConnProps.WEB_APP + "/" +
                            Constants.GlassfishConnProps.REST_HOME + "/" +
                            "fileservice/download/image?path=" +
                            cuisine.getProfilePic())
                    .into(mItemIV);

            /*if(mCuisine.getType().equals("2")) {
                mItemNoteRL.setVisibility(View.VISIBLE);
            } else {
                mItemNoteRL.setVisibility(View.GONE);
            }*/
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

            //if(cuisine.getProfilePic() != null && !cuisine.getProfilePic().isEmpty())
            //    mDataLoader.queueImage(holder, cuisine.getProfilePic());
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
                int qty = data.getIntExtra(EXTRA_ATC_QTY, 1);
                String intention = data.getStringExtra(EXTRA_ATC_INTENTION);
                if(intention.equals(getResources().getStringArray(R.array.dlg_atc_intention)[0])) {//change
                    mSelf.getOrderListItem(
                            mSelf.getSelectedRestaurant(),
                            mSelf.getSelectedCuisine().getCuisineId())
                    .setQuantity(qty);
                } else if(intention.equals(getResources().getStringArray(R.array.dlg_atc_intention)[1])) {//cancel
                    if(mSelf.getOrderList(mSelf.getSelectedRestaurant()) != null) {
                        mSelf.getOrderList(mSelf.getSelectedRestaurant())
                                .remove(mSelf.getOrderListItem(
                                        mSelf.getSelectedRestaurant(),
                                        mSelf.getSelectedCuisine().getCuisineId()));

                        Snackbar.make(getView(), mSelf.getSelectedCuisine().getName() + " removed from cart", Snackbar.LENGTH_SHORT).show();
                    }
                } else {
                    CuisineList cuisineList = new CuisineList();
                    cuisineList.setCuisineId(mSelf.getSelectedCuisine().getCuisineId() + "");
                    cuisineList.setQuantity(qty);

                    if(mSelf.getOrderList(mSelf.getSelectedRestaurant()) == null) {
                        mSelf.setOrderList(mSelf.getSelectedRestaurant(), new ArrayList<CuisineList>());
                    }
                    mSelf.getOrderList(mSelf.getSelectedRestaurant()).add(cuisineList);
                }

                checkItems();
                break;
        }
    }
}
