package com.strettocorp.uberfood.restaurant;

import android.graphics.Bitmap;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.strettocorp.uberfood.R;
import com.strettocorp.uberfood.entity.Restaurant;
import com.strettocorp.uberfood.essentials.DataLoader;
import com.strettocorp.uberfood.essentials.Self;
import com.strettocorp.uberfood.restaurant_menu.RestaurantMenuActivity;

import org.w3c.dom.Text;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class RestaurantFragment extends Fragment {

    private TextView mLocationTV, mSearchTV;
    private EditText mSearchET;
    private ViewFlipper mRestaurantTitleVF;
    private RecyclerView mRestaurantRV;

    private RestAdapter mAdapter;

    private Self mSelf;

    private DataLoader<RestHolder> mDataLoader;

    public static RestaurantFragment newInstance() {
        return new RestaurantFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);

        mDataLoader = new DataLoader(getActivity(), new Handler());
        mDataLoader.setDataLoaderListener(new DataLoader.DataLoaderListener<RestHolder>() {
            private RestHolder mHolder;
            @Override
            public void onDataLoaded(RestHolder holder) {
                mHolder = holder;
            }

            @Override
            public void onImageLoaded(Bitmap bitmap) {
                mHolder.bindRestPhoto(bitmap);
            }
        });
        mDataLoader.start();
        mDataLoader.getLooper();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_restaurant, container, false);
        mLocationTV = (TextView) view.findViewById(R.id.restaurant_location_TV);
        mSearchTV = (TextView) view.findViewById(R.id.restaurant_search_TV);
        mSearchET = (EditText) view.findViewById(R.id.restaurant_search_ET);
        mRestaurantTitleVF = (ViewFlipper) view.findViewById(R.id.restaurant_title_VF);
        mRestaurantRV = (RecyclerView) view.findViewById(R.id.restaurant_RV);

        mRestaurantRV.setLayoutManager(new LinearLayoutManager(getActivity()));

        mSelf = Self.get(getActivity());

        loadRestuarants();

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

    private void loadRestuarants() {
        if(mAdapter == null) {
            mAdapter = new RestAdapter(Self.getRestaurants());

            mSelf.setServResponse(new Self.ServResponse() {
                @Override
                public void onResponse(int responseCode) {
                    //Toast.makeText(getActivity(), restaurant.getDescription(), Toast.LENGTH_SHORT).show();
                    mRestaurantRV.setAdapter(mAdapter);
                }
            });
        } else {
            mAdapter.setRestaurants(Self.getRestaurants());

            mSelf.setServResponse(new Self.ServResponse() {
                @Override
                public void onResponse(int responseCode) {
                    //Toast.makeText(getActivity(), restaurant.getDescription(), Toast.LENGTH_SHORT).show();
                    mAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    private class RestHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{
        private Restaurant mRestaurant;
        private int mPos;
        private ImageView mOnDisplayIV;
        private TextView mRestTitleTV, mRestDeliTimeTV, mRestMenuItem1TV, mRestMenuItem2TV;
        private ViewFlipper mRestMenuItemVF;

        public RestHolder(View itemView) {
            super(itemView);

            mOnDisplayIV = (ImageView) itemView.findViewById(R.id.restaurant_ondisplay_IV);
            mRestTitleTV = (TextView) itemView.findViewById(R.id.restaurant_title_TV);
            mRestDeliTimeTV = (TextView) itemView.findViewById(R.id.restaurant_deli_time_TV);
            mRestMenuItem1TV = (TextView) itemView.findViewById(R.id.restaurant_menu_item1_TV);
            mRestMenuItem2TV = (TextView) itemView.findViewById(R.id.restaurant_menu_item2_TV);
            mRestMenuItemVF = (ViewFlipper) itemView.findViewById(R.id.restaurant_menu_item_VF);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            startActivity(RestaurantMenuActivity.newIntent(getActivity(), mRestaurant));
        }

        public void bindRestaurant(Restaurant restaurant, int pos) {
            mRestaurant = restaurant;
            mPos = pos;
            mRestTitleTV.setText(restaurant.getName());
            mRestMenuItem1TV.setText(restaurant.getDescription());
            /*String name = restaurant.getProfilePic()
                    .toLowerCase()
                    .substring(restaurant.getProfilePic().lastIndexOf("/") + 1,
                            restaurant.getProfilePic().lastIndexOf("."))
                    .replace(" ", "_");*/

            //Log.i("Self", name);
            //Toast.makeText(getActivity(), restaurant.getName(), Toast.LENGTH_SHORT).show();
        }

        public void bindRestPhoto(Bitmap bitmap) {
            if(mAdapter.mRestaurants.get(mPos).getName().equals(mRestaurant.getName())) {
                mOnDisplayIV.setImageBitmap(bitmap);
            }
        }
    }

    private class RestAdapter extends RecyclerView.Adapter<RestHolder> {
        private List<Restaurant> mRestaurants;

        public RestAdapter(List<Restaurant> restaurants) {
            mRestaurants = restaurants;
        }

        @Override
        public RestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RestHolder(LayoutInflater.from(getActivity())
                    .inflate(R.layout.list_item_restaurant, parent, false));
        }

        @Override
        public void onBindViewHolder(final RestHolder holder, int position) {
            final Restaurant restaurant = mRestaurants.get(position);

            holder.bindRestaurant(restaurant, position);
            mDataLoader.queueImage(holder, restaurant.getProfilePic());
        }

        @Override
        public int getItemCount() {
            return mRestaurants.size();
        }

        public void setRestaurants(List<Restaurant> restaurants) {
            mRestaurants = restaurants;
        }
    }
}
