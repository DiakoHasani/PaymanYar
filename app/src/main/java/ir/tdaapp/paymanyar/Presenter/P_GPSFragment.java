package ir.tdaapp.paymanyar.Presenter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;

import androidx.core.content.ContextCompat;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_SavesGps;
import ir.tdaapp.paymanyar.Model.Services.S_GPSFragment;

public class P_GPSFragment {

    Context context;
    S_GPSFragment s_gpsFragment;
    private GoogleMap googleMap;
    Tbl_SavesGps tbl_savesGps;
    LatLng myPosition;

    public P_GPSFragment(Context context, S_GPSFragment s_gpsFragment) {
        this.context = context;
        this.s_gpsFragment = s_gpsFragment;

        tbl_savesGps = new Tbl_SavesGps(context);
    }

    //در اینجا لوکیشن روشن می شود
    public void setLocation() {
        String provider = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if (!provider.contains("gps")) {
            context.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }

    }

    public void ShowMap() {
        try {
            MapsInitializer.initialize(this.context.getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        OnMapReadyCallback readyCallback = new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                googleMap.setMyLocationEnabled(true);

                // Getting LocationManager object from System Service LOCATION_SERVICE
                LocationManager locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);

                // Creating a criteria object to retrieve provider
                Criteria criteria = new Criteria();

                // Getting the name of the best provider
                String provider = locationManager.getBestProvider(criteria, true);

                double longitude=35;
                double latitude=45;
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {

                    // Getting Current Location
//                    Location location = locationManager.getLastKnownLocation(provider);

//                    if (location != null) {
//                        // Getting latitude of the current location
//                        latitude = location.getLatitude();
//
//                        // Getting longitude of the current location
//                        longitude = location.getLongitude();
//
//                        // Creating a LatLng object for the current location
//                        LatLng latLng = new LatLng(latitude, longitude);
//
//                        myPosition = new LatLng(latitude, longitude);
//
//                        googleMap.addMarker(new MarkerOptions().position(myPosition).title("Start"));
//                    } else {
//                        longitude = 40;
//                        latitude = 30;
//                    }

                } else {
                }


                // For dropping a marker at a point on the Map
                LatLng sydney = new LatLng(longitude, latitude);

                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        googleMap.addMarker(new MarkerOptions().position(latLng).title("Your Mark").snippet(""));
                        if (s_gpsFragment != null)
                            s_gpsFragment.OnLocationChange(String.valueOf(latLng.latitude), String.valueOf(latLng.longitude));
                    }
                });

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        };

        if (this.s_gpsFragment != null) this.s_gpsFragment.OnMapReady(readyCallback);

    }

    public void ChangeMapType(int type) {
        int typeMap = 0;

        switch (type) {
            case 0:
                typeMap = GoogleMap.MAP_TYPE_NORMAL;
                break;
            case 1:
                typeMap = GoogleMap.MAP_TYPE_SATELLITE;
                break;
            case 2:
                typeMap = GoogleMap.MAP_TYPE_HYBRID;
                break;
        }

        if (googleMap != null) googleMap.setMapType(typeMap);
    }

    public void SaveLocation(String lat, String lon) {
        tbl_savesGps.saveLocation(lat, lon);
    }
}
