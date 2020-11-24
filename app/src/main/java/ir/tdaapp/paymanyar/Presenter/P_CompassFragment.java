package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

import ir.tdaapp.paymanyar.Model.Services.S_CompassFragment;

public class P_CompassFragment {

    Context context;
    private float currentAzimuth;
    private S_CompassFragment s_compassFragment;

    public P_CompassFragment(Context context, S_CompassFragment s_compassFragment) {
        this.context = context;
        this.s_compassFragment=s_compassFragment;

    }

}
