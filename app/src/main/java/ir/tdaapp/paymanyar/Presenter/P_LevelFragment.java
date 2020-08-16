package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.widget.ImageView;

import ir.tdaapp.paymanyar.Model.Services.S_LevelFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BubbleLevel;

public class P_LevelFragment {

    private Context context;
    private S_LevelFragment s_levelFragment;

    BubbleLevel bubbleLevel;
    SensorManager sensorManager;
    Sensor sensor;
    int marginCircular;

    public P_LevelFragment(Context context, S_LevelFragment s_levelFragment1, int marginCircular, int width, int height) {
        this.context = context;
        this.marginCircular = marginCircular;
        this.s_levelFragment = s_levelFragment1;

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        bubbleLevel = new BubbleLevel(context, width, height,marginCircular, this.s_levelFragment);

        sensorManager.registerListener(bubbleLevel, sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }


    public void start() {
        s_levelFragment.OnStart();
    }
}
