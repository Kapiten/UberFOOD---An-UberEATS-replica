package com.reverside.uberfood.cart;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.reverside.uberfood.custom.SwipeButtonCustomItems;
import com.reverside.uberfood.entity.Cuisine;
import com.reverside.uberfood.entity.Orders;
import com.reverside.uberfood.essentials.Constants;
import com.reverside.uberfood.essentials.Self;
import com.reverside.uberfood.R;
import com.reverside.uberfood.custom.SwipeButton;
import com.reverside.uberfood.order.OrderActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A placeholder fragment containing a simple view.
 */
public class CartFragment extends Fragment
    implements OnMapReadyCallback {

    private static final String TAG = "CartActivity";

    private TextView mRestaTitleTV, mRestaDeliTimeTV, mDeliLocatNameTV, mDeliLocatAddressTV,
                    mSubtotalTV, mTaxTV, mDeliFeeTV, mTotalTV, mContactNumTV;
    private EditText mDeliNoteET, mExtrasNoteET;
    private RecyclerView mCartItemsRV;
    private Button mChangeContactNumBTN;
    private GoogleMap mMap;
    private Self mSelf;
    private CartItemAdapter mAdapter;
    private List<Cuisine> mCuisines;

    private double mTotal = 0, mSubtotal = 0;

    public static CartFragment newInstance() {
        return new CartFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_cart, container, false);

        mSelf = Self.get(getActivity());
        mCuisines = new ArrayList<>();

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.order_delivery_MV);
        mapFragment.getMapAsync(this);

        mRestaTitleTV = (TextView) view.findViewById(R.id.cart_restaurant_name_TV);
        mRestaDeliTimeTV = (TextView) view.findViewById(R.id.cart_restaurant_deli_time_TV);
        mDeliLocatNameTV = (TextView) view.findViewById(R.id.cart_loca_placename_TV);
        mDeliLocatAddressTV = (TextView) view.findViewById(R.id.cart_loca_address_TV);
        mDeliNoteET = (EditText) view.findViewById(R.id.cart_loca_deli_note_ET);
        mExtrasNoteET = (EditText) view.findViewById(R.id.cart_extras_note_TV);
        mSubtotalTV = (TextView) view.findViewById(R.id.cart_total_subtotal_price_TV);
        mTaxTV = (TextView) view.findViewById(R.id.cart_total_tax_price_TV);
        mDeliFeeTV = (TextView) view.findViewById(R.id.cart_total_deli_fee_price_TV);
        mTotalTV = (TextView) view.findViewById(R.id.cart_total_price_TV);
        mContactNumTV = (TextView) view.findViewById(R.id.cart_contact_num_TV);
        mChangeContactNumBTN = (Button) view.findViewById(R.id.cart_contact_option_BTN);
        mCartItemsRV = (RecyclerView) view.findViewById(R.id.cart_items_RV);

        mCartItemsRV.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRestaTitleTV.setText(mSelf.getSelectedRestaurant().getName());

        mDeliFeeTV.setText(getString(R.string.currency_txt,
                "R",
                getPriceFormat(Constants.AdditionalCosts.DELIVERY_FEE) + ""));

        SwipeButton swipeButton = (SwipeButton) view.findViewById(R.id.cart_place_order_BTN);

        SwipeButtonCustomItems swipeBtnSettings = new SwipeButtonCustomItems() {
            @Override
            public void onSwipeConfirm() {
                Orders orders = new Orders();
                orders.setOrderNo("TS99-12345");
                orders.setRestaurantId(mSelf.getSelectedRestaurant().getRestaurantId() + "");
                orders.setDate(new SimpleDateFormat(Constants.DATE_FORMAT).format(new Date()));
                orders.setDeliveryNote(mDeliNoteET.getText().toString());
                orders.setExtrasNote(mExtrasNoteET.getText().toString());
                orders.setSubtotal(mSubtotalTV.getText().toString());
                orders.setTax(mTaxTV.getText().toString());
                orders.setDeliveryFee(mDeliFeeTV.getText().toString());
                orders.setStatus(1);

                if (mSelf.getOrder(mSelf.getSelectedRestaurant().getRestaurantId()) == null) {
                    try {
                        mSelf.getOrders().put(mSelf.getSelectedRestaurant(), orders);
                        Log.i(Constants.UNI_TAG, "swipeBnt = NoResta");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        Log.i(Constants.UNI_TAG, "swipeBnt = RestaID=" + mSelf.getSelectedRestaurant().getRestaurantId());
                        mSelf.getOrders().replace(mSelf.getSelectedRestaurant(), orders);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                startActivity(new Intent(getActivity(), OrderActivity.class));
            }
        };

        swipeBtnSettings
                .setButtonPressText(">> Placing Orders >>")
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

        loadItems();

        return view;
    }

    public double getTotal() {
        return mTotal;
    }

    public void setTotal(double total) {
        mTotal = total;
    }

    private double getPrice(String price) {
        price = price.replace("R", "");
        double dprice = 0.00;

        try {
            dprice = Double.parseDouble(price);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return dprice;
    }

    private String getPriceFormat(double price) {
        /*DecimalFormat dformat = new DecimalFormat("#.##");
        dformat.setRoundingMode(RoundingMode.CEILING);
        return dformat.format(price);*/

        return String.format(java.util.Locale.US,"%.2f", price);
    }

    private void addToTotal(double price) {
        double total = getTotal() + price;
        setTotal(total);

        mSubtotalTV.setText(getString(R.string.currency_txt, "R", getPriceFormat(total) + ""));

        double vat = Constants.AdditionalCosts.VAT + 1;
        vat = (vat * total) - total;

        mTaxTV.setText(getString(R.string.currency_txt,
                "R",
                getPriceFormat(vat) + ""));

        total = total + vat + Constants.AdditionalCosts.DELIVERY_FEE;
        mTotalTV.setText(getString(R.string.currency_txt,
                "R",
                getPriceFormat(total) + ""));
    }

    private String generateOrderNo(int restaurantId, String name) {
        Pattern pat = Pattern.compile("[A-Z][^A-Z]*$");
        Matcher match = pat.matcher(name);
        String letterId = "";


        if(match.find())
        {
            String allCaps = match.group();
            if(allCaps.length() >= 2) {
                letterId = allCaps.substring(0, 2);
            }
        }

        if(letterId.isEmpty()) {
            if(name.length() >= 2) {
                letterId = name.substring(0, 2).toUpperCase();
            } else {
                letterId = "X" + name.toUpperCase();
            }
        }

        return letterId;
    }

    private void loadItems() {
        if(mAdapter == null) {
            mAdapter = new CartItemAdapter();
            mCartItemsRV.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class CartItemHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{

        private TextView mTitleTV, mPrepTV, mQtyTV, mPriceTV;
        private Cuisine mCuisine;

        public CartItemHolder(View itemView) {
            super(itemView);

            mTitleTV = (TextView) itemView.findViewById(R.id.cart_item_title_TV);
            mPrepTV = (TextView) itemView.findViewById(R.id.cart_item_prep_TV);
            mQtyTV = (TextView) itemView.findViewById(R.id.cart_item_qty_TV);
            mPriceTV = (TextView) itemView.findViewById(R.id.cart_item_price_TV);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

        public void bindItem(Cuisine cuisine) {
            mCuisine = cuisine;

            mTitleTV.setText(cuisine.getName());
            //mPrepTV.setText(cuisine);

            try {
                int qty = mSelf.getOrderListItem(mSelf.getSelectedRestaurant(), cuisine.getCuisineId())
                        .getQuantity();
                double price = getPrice(cuisine.getPrice()) * qty;

                mQtyTV.setText(getString(R.string.quantity_cnt,
                        qty));
                mPriceTV.setText(getString(R.string.currency_txt, "R", getPriceFormat(price) + ""));

                addToTotal(price);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private class CartItemAdapter extends RecyclerView.Adapter<CartItemHolder> {
        public CartItemAdapter() {
            setTotal(0);
        }

        @Override
        public CartItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new CartItemHolder(LayoutInflater.from(getActivity())
                    .inflate(R.layout.list_item_cart_item, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(final CartItemHolder cartItemHolder, int i) {
            String cuisineId = mSelf.getOrderList(mSelf.getSelectedRestaurant())
                    .get(i).getCuisineId();

            if(cuisineId != null) {
                new Self.ServComBuilder(getActivity())
                        .getCuisine(Integer.parseInt(cuisineId))
                        .setServResponse(new Self.ServComBuilder.ServResponse<Cuisine>() {
                            @Override
                            public void onResponse(int responseCode, String response) {
                                if(responseCode != 1) {
                                    //Log.i(TAG, response);
                                }
                            }

                            @Override
                            public void onResponse(Cuisine cuisine) {
                                cartItemHolder.bindItem(cuisine);
                                mCuisines.add(cuisine);
                            }
                        });
            }
        }

        @Override
        public int getItemCount() {
            return mSelf.getOrderList(mSelf.getSelectedRestaurant()).size();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        //mMap.setMyLocationEnabled(true);

        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        try {
            MapsInitializer.initialize(this.getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Updates the location and zoom of the MapView
        //-26.108776, 28.053038
        CameraUpdate cameraUpdate =
                CameraUpdateFactory.newLatLngZoom(
                        new LatLng(-26.08923, 28.02319), 10);
        mMap.animateCamera(cameraUpdate);
    }
}
