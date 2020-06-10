package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_SavesGps;
import ir.tdaapp.paymanyar.Model.Services.S_GPSFragment;

public class P_GPSFragment {

    Context context;
    S_GPSFragment s_gpsFragment;
    private GoogleMap googleMap;
    Tbl_SavesGps tbl_savesGps;

    public P_GPSFragment(Context context, S_GPSFragment s_gpsFragment) {
        this.context = context;
        this.s_gpsFragment = s_gpsFragment;

        tbl_savesGps=new Tbl_SavesGps(context);
    }


    public void ShowMap(){
        try {
            MapsInitializer.initialize(this.context.getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        OnMapReadyCallback readyCallback=new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                LatLng sydney = new LatLng(-34, 151);

                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        googleMap.addMarker(new MarkerOptions().position(latLng).title("Your Mark").snippet(""));
                        if(s_gpsFragment!=null)s_gpsFragment.OnLocationChange(String.valueOf(latLng.latitude),String.valueOf(latLng.longitude));
                    }
                });

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        };

        if(this.s_gpsFragment!=null)this.s_gpsFragment.OnMapReady(readyCallback);

    }

    public void ChangeMapType(int type){
        int typeMap=0;

        switch (type){
            case 0:
                typeMap=GoogleMap.MAP_TYPE_NORMAL;
                break;
            case 1:
                typeMap=GoogleMap.MAP_TYPE_SATELLITE;
                break;
            case 2:
                typeMap=GoogleMap.MAP_TYPE_HYBRID;
                break;
        }

        if(googleMap!=null)googleMap.setMapType(typeMap);
    }

    public void SaveLocation(String lat,String lon){
        tbl_savesGps.saveLocation(lat,lon);
    }
}
