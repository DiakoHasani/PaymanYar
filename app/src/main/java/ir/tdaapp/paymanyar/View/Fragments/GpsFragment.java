package ir.tdaapp.paymanyar.View.Fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import java.util.List;

import ir.tdaapp.paymanyar.Model.Services.S_GPSFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Presenter.P_GPSFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.ToolsActivity;
import ir.tdaapp.paymanyar.View.Dialogs.SavesGpsDialog;

public class GpsFragment extends BaseFragment implements S_GPSFragment, View.OnClickListener{

    public final static String TAG = "GpsFragment";
    Toolbar toolbar;
    LinearLayout saves;
    MapView mMapView;
    P_GPSFragment p_gpsFragment;
    CardView btnSave,btnDefault,btnSatelite,btnGround;
    TextView lat,lon;
    private String[] Permisions={Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.gps_fragment,container,false);

        findItem(view);
        mMapView.onCreate(savedInstanceState);
        implement();
        setToolbar();

        return view;
    }

    void findItem(View view){
        toolbar=view.findViewById(R.id.toolbar);
        saves=view.findViewById(R.id.saves);

        btnDefault=view.findViewById(R.id.gps_defaultbtn);
        btnSatelite=view.findViewById(R.id.gps_satelitebtn);
        btnGround=view.findViewById(R.id.gps_groundbtn);
        btnSave=view.findViewById(R.id.gps_savebtn);

        lat=view.findViewById(R.id.gps_lat);
        lon=view.findViewById(R.id.gps_lon);

        mMapView = (MapView) view.findViewById(R.id.gps_mapview);
    }

    void implement(){
        p_gpsFragment=new P_GPSFragment(this.getContext(),this);

        saves.setOnClickListener(this);
        mMapView.onResume(); // needed to get the map to display immediately

        //درخواست دسترسی مجوز برای نقشه در حین اجرا
        Dexter.withActivity(getActivity()).withPermissions(Permisions).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                if(report.areAllPermissionsGranted()){
                    p_gpsFragment.ShowMap();
                }else{
                    ActivityCompat.requestPermissions(getActivity(),Permisions,22);
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

            }
        }).check();

        btnDefault.setOnClickListener(this);
        btnGround.setOnClickListener(this);
        btnSatelite.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==22){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED  && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                p_gpsFragment.ShowMap();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    //در اینجا تنظیمات تولبار ست می شود
    void setToolbar() {

        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setTitle(getContext().getResources().getString(R.string.GPS));
        ((ToolsActivity) getActivity()).setSupportActionBar(toolbar);
        ((ToolsActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });
        setHasOptionsMenu(true);
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()){
            case R.id.saves:
                SavesGpsDialog savesGpsDialog=new SavesGpsDialog();
                savesGpsDialog.show(((ToolsActivity)getActivity()).getSupportFragmentManager(),SavesGpsDialog.TAG);
                break;
            case R.id.gps_defaultbtn:
                ResetMapTypeButtons();
                btnDefault.setBackgroundResource(R.color.colorSelected);
                p_gpsFragment.ChangeMapType(0);
                break;
            case R.id.gps_satelitebtn:
                ResetMapTypeButtons();
                btnSatelite.setBackgroundResource(R.color.colorSelected);
                p_gpsFragment.ChangeMapType(1);
                break;
            case R.id.gps_groundbtn:
                ResetMapTypeButtons();
                btnGround.setBackgroundResource(R.color.colorSelected);
                p_gpsFragment.ChangeMapType(2);
                break;
            case R.id.gps_savebtn:
                if(lat.getText().toString().length()>0){
                    p_gpsFragment.SaveLocation(lat.getText().toString(),lon.getText().toString());
                }
                break;

        }
    }

    private void ResetMapTypeButtons(){
        btnSatelite.setBackgroundResource(R.color.colorWhite);
        btnGround.setBackgroundResource(R.color.colorWhite);
        btnDefault.setBackgroundResource(R.color.colorWhite);
    }

    @Override
    public void OnMapReady(OnMapReadyCallback mapReadyCallback) {
        mMapView.getMapAsync(mapReadyCallback);
    }

    @Override
    public void OnLocationChange(String lat, String lon) {
        this.lat.setText(lat);
        this.lon.setText(lon);
    }

    @Override
    public void ShowLocations() {

    }
}
