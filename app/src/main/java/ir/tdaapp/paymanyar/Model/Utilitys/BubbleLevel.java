package ir.tdaapp.paymanyar.Model.Utilitys;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.ToneGenerator;

import ir.tdaapp.paymanyar.Model.Services.S_LevelFragment;
import ir.tdaapp.paymanyar.R;


/**
 * Created by Victor on 13/03/2019.
 *
 * این کلاس برای تراز است.
 *
 * This Class Used for Calculating the Level. By Sensors
 *
 */

public class BubbleLevel implements SensorEventListener{

    static private final String TAG = "BubbleLevel";
    static private final double GRAVITY = 9.81d;
    static private final double MIN_DEGREE = -80d;
    static private final double MAX_DEGREE = 80d;

    private Sensor                  sensor;
    private SensorManager           sensorManager;
    private ToneGenerator           toneGenerator;
    private Canvas                  canvasY;
    private Canvas                  canvasX;
    private Canvas                  canvasZ;//New
    private Rect                    rectangleY;
    private Rect                    rectangleX;
    private Rect                    rectangleZ;//New
    private Paint                   paintRectangle;
    private Paint                   paintCrcle,paintEmptyCircle;
    private Bitmap                  bitmapY;
    private Bitmap                  bitmapX;
    private Bitmap                  bitmapZ;//New
    private Paint                   paintLine,paintLineGray;


    private Boolean enablePhoto;
    private Boolean tonePlayed;
    private double thetaX;
    private double thetaY;
    private Context ctx;
    private S_LevelFragment s_levelFragment;

    public BubbleLevel(SensorManager sensorManager, Sensor sensor, Context ctx, S_LevelFragment s_levelFragment) {

        this.s_levelFragment=s_levelFragment;
        this.ctx=ctx;
        this.sensorManager = sensorManager;
        this.sensor = sensor;


        toneGenerator = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100);

        bitmapY = Bitmap.createBitmap(
                150, // Width
                400, // Height
                Bitmap.Config.ARGB_8888 // Config
        );

        bitmapX = Bitmap.createBitmap(
                400, // Width
                150, // Height
                Bitmap.Config.ARGB_8888 // Config
        );

        bitmapZ = Bitmap.createBitmap(
                600, // Width
                600, // Height
                Bitmap.Config.ARGB_8888 // Config
        );

        canvasY = new Canvas(bitmapY);
        canvasY.drawColor(Color.TRANSPARENT);

        canvasX = new Canvas(bitmapX);
        canvasY.drawColor(Color.LTGRAY);

        canvasZ = new Canvas(bitmapZ);
        canvasZ.drawColor(Color.TRANSPARENT);

        rectangleY = new Rect(0,0, canvasY.getWidth(), canvasY.getHeight());
        rectangleX = new Rect(0,0, canvasX.getWidth(), canvasX.getHeight());
        rectangleZ = new Rect(0,0, canvasZ.getWidth(), canvasZ.getHeight());

        paintRectangle = new Paint();
        paintRectangle.setStyle(Paint.Style.FILL);
        paintRectangle.setColor(ctx.getResources().getColor(R.color.colorPrimary));
        paintRectangle.setAntiAlias(true);

//        paintCrcle = new Paint();
//        paintCrcle.setStyle(Paint.Style.FILL);
//        paintCrcle.setColor(ctx.getResources().getColor(R.color.colorPrimary));
//        paintCrcle.setAntiAlias(true);
//        paintCrcle.setAlpha(50);
//
//        paintEmptyCircle = new Paint();
//        paintEmptyCircle.setStyle(Paint.Style.STROKE);
//        paintEmptyCircle.setStrokeWidth(2f);
//        paintEmptyCircle.setColor(ctx.getResources().getColor(R.color.colorPrimary));
//        paintEmptyCircle.setAntiAlias(true);
//        paintEmptyCircle.setAlpha(50);
//
//        paintLine = new Paint();
//        paintLine.setStyle(Paint.Style.FILL);
//        paintLine.setColor(ctx.getResources().getColor(R.color.colorPrimary));
//        paintLine.setAntiAlias(true);
//        paintLine.setStrokeWidth(8f);
//
//        paintLineGray = new Paint();
//        paintLineGray.setStyle(Paint.Style.FILL);
//        paintLineGray.setColor(ctx.getResources().getColor(R.color.colorPrimary));
//        paintLineGray.setAntiAlias(true);
//        paintLineGray.setStrokeWidth(2f);

        enablePhoto = false;
        tonePlayed = false;
        thetaX = 0d;
        thetaY = 0d;

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        //This Actions are Physics Formulas
        double gx = sensorEvent.values[0] > GRAVITY ? GRAVITY : sensorEvent.values[0];
        double gy = sensorEvent.values[1] > GRAVITY ? GRAVITY : sensorEvent.values[1];

        gx = gx < -GRAVITY ? -GRAVITY : gx;
        gy = gy < -GRAVITY ? -GRAVITY : gy;

        thetaX = Math.toDegrees(Math.asin(gy/GRAVITY));
        thetaY = Math.toDegrees(Math.asin(gx/GRAVITY));

        //Create Backgrounds
        canvasY.drawRect(rectangleY, paintRectangle);
        canvasX.drawRect(rectangleX, paintRectangle);
        canvasZ.drawCircle(300,300,300,paintRectangle);//We Draw Circle for the Circular Shape in Center of Screen.


        //Draw Bubbles in X and Y Axises
        Bitmap bmp = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.ic_bubble);
        int x=getLineLocation(thetaX)*2-25;
        int y=getLineLocation(thetaY)*2-25;
        canvasY.drawBitmap(bmp,55, x, paintRectangle);
        canvasX.drawBitmap(bmp,y, 55, paintRectangle);

        //Draw Bubble in Circular Shape is Different with others.
        x=getLineLocationC(thetaX);
        y=getLineLocationC(thetaY);
        canvasZ.drawBitmap(bmp,y, x, paintRectangle);

        if(this.s_levelFragment!=null){s_levelFragment.OnImageFinished(bitmapY,1);}
        if(this.s_levelFragment!=null){s_levelFragment.OnImageFinished(bitmapX,0);}
        if(this.s_levelFragment!=null){s_levelFragment.OnImageFinished(bitmapZ,2);}

    }

    private int getLineLocation(double angle){
        Double value =  ( - angle + 90d) * 1.111d;
        return value.intValue();
    }

    private int getLineLocationC(double angle){
        Double value =  ( - angle + 90d) * 3.112d;
        return value.intValue();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


//
//    public double[] getPhoneAngles() {
//        double [] angles = {thetaX, thetaY};
//
//        return angles;
//    }

}
