package ir.tdaapp.paymanyar.Model.Utilitys;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import ir.tdaapp.paymanyar.Model.Enums.DisplaySize;
import ir.tdaapp.paymanyar.Model.Services.S_LevelFragment;
import ir.tdaapp.paymanyar.View.Activitys.LevelActivity;


/**
 * Created by Victor on 13/03/2019.
 * <p>
 * این کلاس برای تراز است.
 * <p>
 * This Class Used for Calculating the Level. By Sensors
 */
public class BubbleLevel implements SensorEventListener {

    private S_LevelFragment s_levelFragment;
    int screenWidth, screenHeight;
    Context context;
    DisplaySize displaySize;
    int sensorNumber = 0;
    float x, y;
    int centralWidth, centralHeight;

    //در اینجا نقطه مرکزی دایره نگهداری می شوند
    float xa, ya;

    double powX, powY;

    double c, radius;

    int marginCircular;

    boolean isSensorEnabled = true;

    DecimalFormat df;

    public BubbleLevel(Context context, int width, int height, int marginCircular, S_LevelFragment s_levelFragment) {
        this.s_levelFragment = s_levelFragment;
        this.screenWidth = width;
        this.screenHeight = height;
        this.marginCircular = marginCircular;
        this.context = context;

        centralWidth = ((LevelActivity) context).getWidthDisplay();
        centralHeight = ((LevelActivity) context).getHeightDisplay();

        displaySize = DisplayPhone.getDisplaySize(context);

        if (displaySize == DisplaySize.xSmall) {
            sensorNumber = 18;
        } else if (displaySize == DisplaySize.small) {
            sensorNumber = 24;
        } else if (displaySize == DisplaySize.medium) {
            sensorNumber = 32;
        } else if (displaySize == DisplaySize.large) {
            sensorNumber = 48;
        } else if (displaySize == DisplaySize.xLarge) {
            sensorNumber = 72;
        }

        xa = centralWidth / 2;
        ya = centralHeight / 2;

        radius = screenWidth / 2 - marginCircular / 2;

        df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (isSensorEnabled) {
            x = ((this.screenWidth * 50 / 100) - (sensorEvent.values[0] * sensorNumber));
            y = ((this.screenHeight * 50 / 100) - (sensorEvent.values[1] * sensorNumber));

            powX = Math.pow(xa - x, 2);
            powY = Math.pow(ya - y, 2);

            c = Math.sqrt(powX + powY);
            c = ((int) c) + 1;

//            Log.e("ffgfgf", "x: " + x + " y: " + y + " c: " + c + " radius:" + radius + " xa:" + xa + " ya:" + ya + " marginCircular:" + marginCircular);
//
//            Log.e("tyrtuyu", "powX:" + powX + " powY:" + powY);


            if (c <= radius) {
                s_levelFragment.onPositionBubble(x, y);
            }

            //در اینجا چک می کند که حباب به وسط صفحه رسیده است یا خیر
            if (((int) x) == ((int) xa) && ((int) y) == ((int) ya)) {
                s_levelFragment.onCenterBubble(true);
            }
//            //در اینجا چک می کند که حباب به بالا یا پایین صفحه رسیده است یا خیر
//            else if (df.format(x).equalsIgnoreCase(df.format(xa)) && c >= radius) {
//                s_levelFragment.on_Y_Bubble(true);
//            }
//            //در اینجا چک می کند که حباب به راست یا چپ صفحه رسیده است یا خیر
//            else if (df.format(y).equalsIgnoreCase(df.format(ya)) && c >= radius) {
//                s_levelFragment.on_X_Bubble(true);
//            }
            //اگر هیچکدام از شرط ها درست نباشد رنگ تراز به حالت عادی بر می گردد
            else {
                s_levelFragment.onCenterBubble(false);
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void setSensorEnabled(boolean sensorEnabled) {
        isSensorEnabled = sensorEnabled;
    }
}
