package com.reverside.uberfood.maps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.reverside.uberfood.R;

public class MapsActivity extends FragmentActivity
        implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private LocationManager mLocManager;

    /*
        Reverside GeoLocation:
        Latitude	-26.08923
        Longitude	28.02319

        Latitude: -26.088955921681062
  Longitude: 28.02312461915153
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Location loca = new Location(LocationManager.GPS_PROVIDER);
                loca.setLatitude(latLng.latitude);
                loca.setLongitude(latLng.longitude);

                Location location = new Location(LocationManager.GPS_PROVIDER);
                location.setLatitude(-26.088955921681062);
                location.setLongitude(28.02312461915153);

                Toast.makeText(MapsActivity.this, "Distance: " + location.distanceTo(loca), Toast.LENGTH_LONG).show();
            }
        });

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(41.889882, 12.479267);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mLocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this, "Cannot get permissions!",
                    Toast.LENGTH_LONG).show();
            return;
        }

        Location location = mLocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null) {
            mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0l,
                    0f, this);
            Toast.makeText(
                    this,
                    "Current location:\nLatitude: " + location.getLatitude()
                            + "\n" + "Longitude: " + location.getLongitude(),
                    Toast.LENGTH_LONG).show();
        } else {

            Toast.makeText(this, "Cannot fetch current location!",
                    Toast.LENGTH_LONG).show();
        }

        mLocManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
                0, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocManager.removeUpdates(this);
    }
}
