package ir.tdaapp.paymanyar.View.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import ir.tdaapp.paymanyar.Model.Services.S_MagnifierFragment;
import ir.tdaapp.paymanyar.Presenter.P_MagnifierFragment;
import ir.tdaapp.paymanyar.R;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

//مربوط به ذره بین
public class MagnifierActivity extends AppCompatActivity implements View.OnClickListener, S_MagnifierFragment {

    public final static String TAG = "MagnifierFragment";
    SurfaceView surfaceView;
    SeekBar zoomBar;
    int preZoom = 0;
    private P_MagnifierFragment p_magnifierFragment;
    private String[] Permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    ImageView save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnifier);

        findItem();
        implement();
    }

    void findItem() {
        save = findViewById(R.id.btn_save);
        zoomBar = findViewById(R.id.seekBar_zoom);

        surfaceView = findViewById(R.id.magnifier_surface);
    }

    void implement() {
        p_magnifierFragment = new P_MagnifierFragment(this, this);
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
        Dexter.withActivity(this).withPermissions(Permissions).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                if (report.areAllPermissionsGranted()) {
                    p_magnifierFragment.start_camera();
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
