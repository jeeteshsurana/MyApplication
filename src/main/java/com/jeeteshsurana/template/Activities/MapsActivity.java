package com.jeeteshsurana.template.Activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jeeteshsurana.template.R;
import com.jeeteshsurana.template.Utils.PermissionClass;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {
    private GoogleMap mMap;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    String mprovider;
    private String TAG = "MapsActivity";
    SupportMapFragment mapFragment;
    LatLng sydney;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //Getting location
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        mprovider = locationManager.getBestProvider(criteria, true);

        if (mprovider != null && !mprovider.equals("")) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                PermissionClass.checkAndRequestPermissions(this);
            } else {
                Location location = locationManager.getLastKnownLocation(mprovider);
                locationManager.requestLocationUpdates(mprovider, 15000, 1, this);
                if (location == null) {

                } else {
                    onLocationChanged(location);
                }
            }
        }

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            PermissionClass.checkAndRequestPermissions(this);
        } else {
            Location location = locationManager.getLastKnownLocation(mprovider);
            locationManager.requestLocationUpdates(mprovider, 15000, 1, this);
            if (location == null) {

            } else {
                onLocationChanged(location);
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        //we are getting Location and add marker in map
        sydney = new LatLng(location.getLatitude(), location.getLongitude());
        Toast.makeText(this, "latLang" + location.getLatitude() + location.getLongitude(), Toast.LENGTH_SHORT).show();
        //if mMap are not null then it add marker otherwise we are reassign the map fragment
        if (mMap != null) {
            Log.d(TAG, "onLocationChanged: not got null");
            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in my location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        } else {
            Log.d(TAG, "onLocationChanged: getting null");
            mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }

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
}
