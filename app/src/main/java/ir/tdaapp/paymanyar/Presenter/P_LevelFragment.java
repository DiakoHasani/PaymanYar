package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.Image;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.DBExcute;
import ir.tdaapp.paymanyar.Model.Services.S_IPE_SemiWideFragment;
import ir.tdaapp.paymanyar.Model.Services.S_LevelFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BubbleLevel;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Activitys.ToolsActivity;

public class P_LevelFragment {

    private Context context;
    private S_LevelFragment s_levelFragment;
    private DBExcute dbExcute;

    BubbleLevel bubbleLevel;
    SensorManager sensorManager;
    Sensor sensor;

    public P_LevelFragment(Context context, S_LevelFragment s_levelFragment1, int width, int height, ImageView img) {
        this.context = context;
        this.s_levelFragment = s_levelFragment1;
        this.dbExcute = DBExcute.getInstance(this.context);

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        bubbleLevel = new BubbleLevel(sensorManager, sensor, this.context, this.s_levelFragment,width,height, img);

        sensorManager.registerListener(bubbleLevel, sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }


    public void start() {
        s_levelFragment.OnStart();
    }
}
