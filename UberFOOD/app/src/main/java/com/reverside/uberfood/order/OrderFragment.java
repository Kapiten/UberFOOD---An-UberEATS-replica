package com.reverside.uberfood.order;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.iid.FirebaseInstanceId;
import com.reverside.uberfood.R;
import com.reverside.uberfood.essentials.Constants;
import com.reverside.uberfood.essentials.FirebaseMsgService;
import com.reverside.uberfood.essentials.Self;

/**
 * A placeholder fragment containing a simple view.
 */
public class OrderFragment extends Fragment
        implements OnMapReadyCallback {

    public static final String TAG = "OrderFragment";

    private ImageView mDisplayIV, mOReqPrgIV, mOPrepPrgIV, mODeliPrgIV, mStatRequestIV, mStatFoodIV, mStatCourierIV;
    private TextView mTitleTV, mOReqTV, mOPrepTV, mODeliLocaTV, mODeliTV;
    private LinearLayout mOReqLL, mOPrepLL, mODeliLL;
    private Button mOReqCancelBTN;
    private MapView mMapView;
    private GoogleMap mMap;
    private BroadcastReceiver mReceiver;
    private LocationManager lm;

    private Self mSelf;

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_order, container, false);

        mSelf = Self.get(getActivity());
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.order_delivery_MV);
        mapFragment.getMapAsync(this);

        lm = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);

        mDisplayIV = (ImageView) view.findViewById(R.id.order_resta_display_IV);
        mOReqPrgIV = (ImageView) view.findViewById(R.id.order_prg_confirm_IV);
        mOPrepPrgIV = (ImageView) view.findViewById(R.id.order_prg_food_IV);
        mODeliPrgIV = (ImageView) view.findViewById(R.id.order_prg_courier_IV);
        mTitleTV = (TextView) view.findViewById(R.id.order_resta_name_TV);
        mOReqTV = (TextView) view.findViewById(R.id.order_request_TV);
        mOPrepTV = (TextView) view.findViewById(R.id.order_food_prep_TV);
        mODeliLocaTV = (TextView) view.findViewById(R.id.order_deli_location_TV);
        mODeliTV = (TextView) view.findViewById(R.id.order_delivery_TV);
        mOReqCancelBTN = (Button) view.findViewById(R.id.order_cancel_BTN);
        mOReqLL = (LinearLayout) view.findViewById(R.id.order_request_LL);
        mOPrepLL = (LinearLayout) view.findViewById(R.id.order_food_prep_LL);
        mODeliLL = (LinearLayout) view.findViewById(R.id.order_delivery_LL);
        mStatRequestIV = (ImageView) view.findViewById(R.id.order_request_status_IV);
        mStatFoodIV = (ImageView) view.findViewById(R.id.order_food_status_IV);
        mStatCourierIV = (ImageView) view.findViewById(R.id.order_courier_status_IV);

        String token = FirebaseInstanceId.getInstance().getToken();
        //Toast.makeText(getActivity(), "Token: " + token, Toast.LENGTH_SHORT).show();
        Log.i(TAG, "Token: " + token);

        mTitleTV.setText(mSelf.getSelectedRestaurant().getName());
        mTitleTV.setTypeface(Self.mUberFont);

        Glide.with(getActivity())
                .load("http://" +
                        Constants.GlassfishConnProps.URL + ":" +
                        Constants.GlassfishConnProps.PORT + "/" +
                        Constants.GlassfishConnProps.WEB_APP + "/" +
                        Constants.GlassfishConnProps.REST_HOME + "/" +
                        "fileservice/download/image?path=" +
                        mSelf.getSelectedRestaurant().getProfilePic())
                .into(mDisplayIV);

        activateBReceiver();
        placeOrder();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(getActivity())
                .registerReceiver(mReceiver,
                        new IntentFilter(FirebaseMsgService.REQUEST_ORDER));
    }

    @Override
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getActivity())
                .unregisterReceiver(mReceiver);
    }

    private void placeOrder() {
        new Self.ServComBuilder(getActivity())
                .addOrder(mSelf.getOrders().get(mSelf.getSelectedRestaurant()))
                .setServResponse(new Self.ServComBuilder.ServResponse() {
                    @Override
                    public void onResponse(int responseCode, String response) {
                        if(responseCode == Constants.RestResponseCodes.SUCCESS) {
                            Toast.makeText(getActivity(), "Orders was sent", Toast.LENGTH_SHORT).show();
                            Log.i(Constants.UNI_TAG, "placeOrder = SUCCESS " + mSelf.getOrders().get(mSelf.getSelectedRestaurant()));
                        } else {
                            Log.i(Constants.UNI_TAG, "placeOrder = FAIL " + response);
                        }
                    }

                    @Override
                    public void onResponse(Object object) {

                    }
                });
    }

    private void activateBReceiver() {
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String value = intent.getStringExtra("Key");
                String msg = intent.getStringExtra("MSG");

                switch (value) {
                    case "Orders":
                        mOReqLL.setVisibility(View.GONE);
                        //mOReqTV.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, , null);
                        mOReqTV.setText("Orders accepted!");
                        mOPrepTV.setText("Food is being prepared");
                        mOPrepTV.setCompoundDrawablePadding(-20);
                        //mOPrepTV.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, , null);
                        mOPrepLL.setVisibility(View.VISIBLE);
                        mOPrepPrgIV.setImageDrawable(getResources().getDrawable(R.drawable.ic_adown_green));

                        mStatRequestIV.setImageDrawable(getResources().getDrawable(R.drawable.ic_complete));
                        mStatFoodIV.setImageDrawable(getResources().getDrawable(R.drawable.ic_busy));
                        break;
                    case "Food":
                        mOPrepLL.setVisibility(View.GONE);
                        mOPrepTV.setCompoundDrawablePadding(-20);
                        mOPrepTV.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_complete), null);
                        mOPrepTV.setText("Food is prepared!");
                        mODeliTV.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_busy), null);
                        mODeliLL.setVisibility(View.VISIBLE);
                        mODeliPrgIV.setImageDrawable(getResources().getDrawable(R.drawable.ic_adown_green));

                        mStatFoodIV.setImageDrawable(getResources().getDrawable(R.drawable.ic_complete));
                        mStatCourierIV.setImageDrawable(getResources().getDrawable(R.drawable.ic_busy));
                        break;
                    case "Courier":
                        mStatCourierIV.setImageDrawable(getResources().getDrawable(R.drawable.ic_complete));
                        mODeliTV.setText("Courier has arrived.");
                        break;
                }

                Toast.makeText(context, "MSG: " + msg, Toast.LENGTH_SHORT).show();
            }
        };
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

        mockLocation(-26.108776, 28.02319, 10);
    }

    private void setMock(double lat, double lng, float accuracy) {

    }

    public void mockLocation(double latitude, double longitude, float accuracy){
        lm.addTestProvider (LocationManager.GPS_PROVIDER,
                "requiresNetwork" == "",
                "requiresSatellite" == "",
                "requiresCell" == "",
                "hasMonetaryCost" == "",
                "supportsAltitude" == "",
                "supportsSpeed" == "",
                "supportsBearing" == "",
                android.location.Criteria.POWER_LOW,
                android.location.Criteria.ACCURACY_FINE);

        Location newLocation = new Location(LocationManager.GPS_PROVIDER);

        newLocation.setLatitude(latitude);
        newLocation.setLongitude(longitude);
        newLocation.setAccuracy(accuracy);
        newLocation.setAltitude(0);
        newLocation.setAccuracy(500);
        newLocation.setTime(System.currentTimeMillis());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            newLocation.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        }
        lm.setTestProviderEnabled(LocationManager.GPS_PROVIDER, true);

        lm.setTestProviderStatus(LocationManager.GPS_PROVIDER,
                LocationProvider.AVAILABLE,
                null,System.currentTimeMillis());

        lm.setTestProviderLocation(LocationManager.GPS_PROVIDER, newLocation);
    }
}
