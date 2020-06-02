package ir.tdaapp.paymanyar.View.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import ir.tdaapp.paymanyar.Model.Services.S_MagnifierFragment;
import ir.tdaapp.paymanyar.Presenter.P_GPSFragment;
import ir.tdaapp.paymanyar.Presenter.P_MagnifierFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.ToolsActivity;
import ir.tdaapp.paymanyar.View.Dialogs.SavesGpsDialog;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.digidemic.unitof.P;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class Magnifier_Fragment extends Fragment implements View.OnClickListener, S_MagnifierFragment {

    public final static String TAG = "MagnifierFragment";
    Toolbar toolbar;
    SurfaceView surfaceView;
    SeekBar zoomBar;
    int preZoom = 0;
    private P_MagnifierFragment p_magnifierFragment;
    private String[] Permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    ImageView save;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.magnifier_fragment, container, false);

        findItem(view);
        implement();
        setToolbar();

        return view;
    }

    void findItem(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        save = view.findViewById(R.id.btn_save);
        zoomBar = view.findViewById(R.id.seekBar_zoom);

        surfaceView = view.findViewById(R.id.magnifier_surface);
    }

    void implement() {
        p_magnifierFragment = new P_MagnifierFragment(this.getContext(), this);
        save.setOnClickListener(this);
        zoomBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i > preZoom) {
                    p_magnifierFragment.ZoomIn(i);
                } else {
                    p_magnifierFragment.ZoomOut(i);
                }
                preZoom = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        p_magnifierFragment.Initial(surfaceView);

        //بررسی دسترسی و سپس نمایش دوربین
        Dexter.withActivity(getActivity()).withPermissions(Permissions).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                if (report.areAllPermissionsGranted()) {
                    p_magnifierFragment.start_camera();
                } else {
                    ActivityCompat.requestPermissions(getActivity(), Permissions, 23);
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

            }
        }).check();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 23) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                p_magnifierFragment.start_camera();
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }

    //در اینجا تنظیمات تولبار ست می شود
    void setToolbar() {

        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setTitle(getContext().getResources().getString(R.string.magnifier));
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

        switch (view.getId()) {
            case R.id.btn_save:
                p_magnifierFragment.captureImage();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        p_magnifierFragment.stop_camera();
    }
}
