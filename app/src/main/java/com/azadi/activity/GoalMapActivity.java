package com.azadi.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.azadi.R;
import com.azadi.databinding.ActivityGoalMapBinding;
import com.azadi.databinding.ActivityGoalTimerBinding;
import com.azadi.utils.GPSTracker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoalMapActivity extends AppCompatActivity implements OnMapReadyCallback {
  ActivityGoalMapBinding binding;
    double lat, lng;
    LatLng latLng;
    GoogleMap mMap;
    GPSTracker gpsTracker;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityGoalMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.flat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GoalMapActivity.this,GoalPhotoActivity.class));
            }
        });




      SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById( R.id.map );
        mapFragment.getMapAsync( GoalMapActivity.this );

        gpsTracker = new GPSTracker(GoalMapActivity.this);
        lat = gpsTracker.getLatitude();
        lng = gpsTracker.getLongitude();
        Log.e("sdfdsv", lat + "");
        Log.e("sdfdsv", lng + "");


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(GoalMapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(GoalMapActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setTrafficEnabled(true);
        ////set direction circle marker bootom right on map
     /*  mMap.setPadding(10, 1200, 10, 10);*/
        enableMyLocationIfPermitted();

       /* mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMinZoomPreference(11);*/


        //Place current location marker
        latLng = new LatLng(lat, lng);

       /* MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(
                GoalMapActivity.this, R.raw.maps_style);
        mMap.setMapStyle(style);*/
        ////SHOW LOCATION OF MARKER AND COLOUR

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
    }


    private void enableMyLocationIfPermitted() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else if (mMap != null) {
            mMap.setMyLocationEnabled(true);
        }
    }
}