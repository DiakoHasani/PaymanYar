package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.PorterDuff;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import edu.arbelkilani.compass.Compass;
import edu.arbelkilani.compass.CompassListener;
import ir.tdaapp.paymanyar.Model.Services.S_CompassFragment;
import ir.tdaapp.paymanyar.Model.Services.S_MagnifierFragment;
import ir.tdaapp.paymanyar.Presenter.P_CompassFragment;
import ir.tdaapp.paymanyar.Presenter.P_MagnifierFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.ToolsActivity;


public class Compass_Fragment extends Fragment implements S_CompassFragment {

    public final static String TAG = "CompassFragment";
    Toolbar toolbar;
    ImageView hands;
    private P_CompassFragment p_compassFragment;
    Compass compass;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.compass_fragment,container,false);

        findItem(view);
        implement();
        setToolbar();

        return view;
    }

    void findItem(View view){
        toolbar=view.findViewById(R.id.toolbar);

        compass = view.findViewById(R.id.compass_1);
    }

    void implement(){
        p_compassFragment=new P_CompassFragment(this.getContext(),this);

        compass.setListener(new CompassListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                Log.d(TAG, "onSensorChanged : " + event);
                if(event.sensor==null){
                    Toast.makeText(getContext(),"سنسور قطب نما بر روی گوشی شما فعال نیست",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                if(sensor==null){
                    Toast.makeText(getContext(),"سنسور قطب نما بر روی گوشی شما فعال نیست",Toast.LENGTH_LONG).show();
                }
                Log.d(TAG, "onAccuracyChanged : sensor : " + sensor);
                Log.d(TAG, "onAccuracyChanged : accuracy : " + accuracy);
            }
        });




    }



    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    //در اینجا تنظیمات تولبار ست می شود
    void setToolbar() {

        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setTitle(getContext().getResources().getString(R.string.compass));
        ((ToolsActivity) getActivity()).setSupportActionBar(toolbar);
        ((ToolsActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });
        setHasOptionsMenu(true);
    }


    @Override
    public void HandRotation(Animation an) {}

    @Override
    public void SetDegreeString(String txt) {}

    @Override
    public void onDestroy() {
        super.onDestroy();
        compass.setListener(null);
    }
}
