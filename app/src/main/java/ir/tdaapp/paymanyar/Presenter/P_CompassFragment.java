package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.DBExcute;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.FieldItem;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.RecordHolder;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.database;
import ir.tdaapp.paymanyar.Model.Services.S_CompassFragment;
import ir.tdaapp.paymanyar.Model.Services.S_GPSFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Compass;
import ir.tdaapp.paymanyar.Model.Utilitys.CompassFormatter;

public class P_CompassFragment {

    private Compass compass;
    Context context;
    private float currentAzimuth;
    private CompassFormatter sotwFormatter;
    private S_CompassFragment s_compassFragment;

    public P_CompassFragment(Context context, S_CompassFragment s_compassFragment) {
        this.context = context;
        sotwFormatter = new CompassFormatter(this.context);
        this.s_compassFragment=s_compassFragment;

        setupCompass();
    }

    private void setupCompass() {
        compass = new Compass(this.context);
        Compass.CompassListener cl = getCompassListener();
        compass.setListener(cl);
    }

    private void adjustArrow(float azimuth) {
        Animation an = new RotateAnimation(-currentAzimuth, -azimuth,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        currentAzimuth = azimuth;

        an.setDuration(500);
        an.setRepeatCount(0);
        an.setFillAfter(true);

        s_compassFragment.HandRotation(an);
    }

    private void adjustSotwLabel(float azimuth) {
        s_compassFragment.SetDegreeString(sotwFormatter.format(azimuth));
    }

    private Compass.CompassListener getCompassListener() {
        return new Compass.CompassListener() {
            @Override
            public void onNewAzimuth(final float azimuth) {
                // UI updates only in UI thread
                // https://stackoverflow.com/q/11140285/444966
                        adjustArrow(azimuth);
                        adjustSotwLabel(azimuth);
            }
        };
    }

    public void StartCompass(){
        compass.start();
    }

    public void StopCompass(){
        compass.stop();
    }

}
