package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;
import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.DBExcute;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.FieldItem;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.RecordHolder;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.database;
import ir.tdaapp.paymanyar.Model.Services.S_GPSFragment;
import ir.tdaapp.paymanyar.Model.Services.S_MagnifierFragment;

public class P_MagnifierFragment implements SurfaceHolder.Callback {

    Context context;
    S_MagnifierFragment s_magnifierFragment;

    Camera camera;
    SurfaceHolder surfaceHolder;
    android.hardware.Camera.PictureCallback rawCallback;
    android.hardware.Camera.ShutterCallback shutterCallback;
    android.hardware.Camera.PictureCallback jpegCallback;
    int zoom=1;
    int S_width=10;int s_height=10;

    public P_MagnifierFragment(Context context, S_MagnifierFragment s_magnifierFragment) {
        this.context = context;
        this.s_magnifierFragment = s_magnifierFragment;

    }


    public void Initial(SurfaceView surfaceView){
        surfaceHolder = surfaceView.getHolder();
        surfaceView.setFocusable(true);
        surfaceView.setFocusableInTouchMode(true);
        s_height=surfaceView.getHeight();
        S_width=surfaceView.getWidth();

        Handler myHandler = new Handler();
        myHandler.postDelayed(runnable,1000);
    }

    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            surfaceHolder.addCallback(P_MagnifierFragment.this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
            rawCallback = new android.hardware.Camera.PictureCallback() {
                public void onPictureTaken(byte[] data, Camera camera) {
                    Log.d("Log", "onPictureTaken - raw");
                }
            };

            /** Handles data for jpeg picture */
            shutterCallback = new android.hardware.Camera.ShutterCallback() {
                public void onShutter() {
                    Log.i("Log", "onShutter'd");
                }
            };
            jpegCallback = new android.hardware.Camera.PictureCallback() {
                public void onPictureTaken(byte[] data, Camera camera) {
                    FileOutputStream outStream = null;
                    try {
                        outStream = new FileOutputStream(String.format(
                                "/sdcard/DCIM/Camera/%d.jpg", System.currentTimeMillis()));
                        outStream.write(data);
                        outStream.close();
                        Log.d("Log", "onPictureTaken - wrote bytes: " + data.length);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                    }
                    Log.d("Log", "onPictureTaken - jpeg");
                    camera.startPreview();
                }
            };
        }
    };

    public void Requestermission(){

    }

    public void captureImage() {
        // TODO Auto-generated method stub
        camera.takePicture(shutterCallback, rawCallback, jpegCallback);
    }

    public void start_camera()
    {
        Handler myHandler = new Handler();
        myHandler.postDelayed(camRun,1000);
    }

    Runnable camRun=new Runnable() {
        @Override
        public void run() {
            try{
                camera = Camera.open();
            }catch(RuntimeException e){
                Log.e("tag", "init_camera: " + e);
                return;
            }
            Camera.Parameters param;
            param = camera.getParameters();
            //modify parameter
            param.setPreviewFrameRate(20);
            List<Camera.Size> sizes = param.getSupportedPreviewSizes();
            Camera.Size optimalSize = getOptimalPreviewSize(sizes,S_width,s_height);
            param.setPreviewSize(optimalSize.width,optimalSize.height);

            param.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
            ChangeZoom();
            camera.setParameters(param);
            try {
                camera.setDisplayOrientation(90);
                camera.setPreviewDisplay(surfaceHolder);
                camera.startPreview();

                //camera.takePicture(shutter, raw, jpeg)
            } catch (Exception e) {
                Log.e("tag", "init_camera: " + e);
                return;
            }
        }
    };

    public void stop_camera()
    {
        camera.stopPreview();
        camera.release();
    }

    private Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {

        final double ASPECT_TOLERANCE = 0.1;
        double targetRatio=(double)h / w;

        if (sizes == null) return null;

        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        int targetHeight = h;

        for (Camera.Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE) continue;
            if (Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }

        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Camera.Size size : sizes) {
                if (Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }
        }
        return optimalSize;
    }
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try{
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        }catch (Exception e){}
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int w, int h) {
        //before changing the application orientation, you need to stop the preview, rotate and then start it again

        if(surfaceHolder.getSurface() == null)//check if the surface is ready to receive camera data
            return;

        try{
            camera.stopPreview();
        } catch (Exception e){
            //this will happen when you are trying the camera if it's not running
        }

        //now, recreate the camera preview
        try{
            //set the focusable true
            //this.setFocusable(true);
            //set the touch able true
            //this.setFocusableInTouchMode(true);
            //set the camera display orientation lock
            camera.setDisplayOrientation(90);

            Camera.Parameters params = camera.getParameters();
            List<Camera.Size> sizes = params.getSupportedPreviewSizes();
            Camera.Size optimalSize = getOptimalPreviewSize(sizes,w,h);
            params.setPreviewSize(optimalSize.width,optimalSize.height);
            camera.setParameters(params);

            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch(Exception exp){
            Log.i("Camera","FROM surfaceChanged: "+exp.toString());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    private void ChangeZoom(){
        try{
            Camera.Parameters parameters = camera.getParameters();
            int maxZoom = parameters.getMaxZoom();
            if (parameters.isZoomSupported()) {
                if (zoom>0 && zoom < maxZoom) {
                    parameters.setZoom(zoom);
                    camera.setParameters(parameters);
                } else {
                    // zoom parameter is incorrect
                }
            }
        }catch (Exception e){}
    }

    public void ZoomIn(){
        zoom++;
        ChangeZoom();
    }

    public void ZoomOut(){
        zoom--;
        ChangeZoom();
    }


}
