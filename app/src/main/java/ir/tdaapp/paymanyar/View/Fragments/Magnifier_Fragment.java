package ir.tdaapp.paymanyar.View.Fragments;

import android.content.Context;
import android.graphics.PorterDuff;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import ir.tdaapp.paymanyar.Model.Services.S_MagnifierFragment;
import ir.tdaapp.paymanyar.Presenter.P_GPSFragment;
import ir.tdaapp.paymanyar.Presenter.P_MagnifierFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.ToolsActivity;
import ir.tdaapp.paymanyar.View.Dialogs.SavesGpsDialog;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.digidemic.unitof.P;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class Magnifier_Fragment extends Fragment implements View.OnClickListener, S_MagnifierFragment  {

    public final static String TAG = "MagnifierFragment";
    Toolbar toolbar;
    CardView save,ZoomIn,ZoomOut;
    SurfaceView surfaceView;
    private P_MagnifierFragment p_magnifierFragment;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.magnifier_fragment,container,false);

        findItem(view);
        implement();
        setToolbar();

        return view;
    }

    void findItem(View view){
        toolbar=view.findViewById(R.id.toolbar);
        save=view.findViewById(R.id.magnifier_btnSave);

        ZoomIn=view.findViewById(R.id.magnifier_zoombtn);
        ZoomOut=view.findViewById(R.id.magnifier_zoomoutbtn);

        surfaceView=view.findViewById(R.id.magnifier_surface);
    }

    void implement(){
        p_magnifierFragment=new P_MagnifierFragment(this.getContext(),this);

        save.setOnClickListener(this);
        ZoomIn.setOnClickListener(this);
        ZoomOut.setOnClickListener(this);

        p_magnifierFragment.Initial(surfaceView);
        p_magnifierFragment.start_camera();
    }



    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        p_magnifierFragment.stop_camera();
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
            case R.id.magnifier_btnSave:
                p_magnifierFragment.captureImage();
                break;
            case R.id.magnifier_zoombtn:
                p_magnifierFragment.ZoomIn();
                break;
            case R.id.magnifier_zoomoutbtn:
                p_magnifierFragment.ZoomOut();
                break;

        }
    }





}
