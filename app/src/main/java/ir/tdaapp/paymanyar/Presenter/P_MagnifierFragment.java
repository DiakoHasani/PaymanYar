package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import ir.tdaapp.paymanyar.Model.Services.S_MagnifierFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.ProjectDirectory;

public class P_MagnifierFragment implements SurfaceHolder.Callback {

    Context context;
    S_MagnifierFragment s_magnifierFragment;

    Camera camera;
    SurfaceHolder surfaceHolder;
    android.hardware.Camera.PictureCallback rawCallback;
    android.hardware.Camera.ShutterCallback shutterCallback;
    android.hardware.Camera.PictureCallback jpegCallback;
    int zoom = 1;
    int S_width = 10;
    int s_height = 10;
    Camera.Parameters parameters;
    int maxZoom;

    public P_MagnifierFragment(Context context, S_MagnifierFragment s_magnifierFragment) {
        this.context = context;
        this.s_magnifierFragment = s_magnifierFragment;
    }


    public void Initial(SurfaceView surfaceView) {
        surfaceHolder = surfaceView.getHolder();
        surfaceView.setFocusable(true);
        surfaceView.setFocusableInTouchMode(true);
        s_height = surfaceView.getRootView().getWidth() / 2;
        S_width = surfaceView.getRootView().getWidth() / 2;

        Handler myHandler = new Handler();
        myHandler.postDelayed(runnable, 1000);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            surfaceHolder.addCallback(P_MagnifierFragment.this);
            surfaceHolder.setFixedSize(S_width, s_height);
            rawCallback = (data, camera) -> Log.d("Log", "onPictureTaken - raw");

            /** Handles data for jpeg picture */
            shutterCallback = () -> Log.i("Log", "onShutter'd");
            jpegCallback = (data, camera) -> {
                FileOutputStream outStream;
                try {

                    ProjectDirectory.createDirectory("paymanyar");

                    String name = UUID.randomUUID().toString() + ".jpg";
                    name = "paymanyar/" + name;

                    File sdCardDirectory = Environment.getExternalStorageDirectory();

                    String path = sdCardDirectory.getPath() + "/" + name;

                    outStream = new FileOutputStream(path);
                    outStream.write(data);
                    outStream.close();

                    MediaScannerConnection.scanFile(context, new String[]{path}, new String[]{"image/jpeg"}, null);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                }
                refreshCamera();
            };
        }
    };

    public void captureImage() {
        try {
            Camera.Parameters params = camera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
            params.setPictureFormat(ImageFormat.JPEG);

//            List<Camera.Size> sizes = params.getSupportedPreviewSizes();
//            Camera.Size selected = sizes.get(0);
//            params.setPreviewSize(selected.width,selected.height);

            camera.setParameters(params);
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
            camera.takePicture(null, null, null, jpegCallback);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start_camera() {
        Handler myHandler = new Handler();
        myHandler.postDelayed(camRun, 1000);
    }

    Runnable camRun = new Runnable() {
        @Override
        public void run() {
            try {
                camera = Camera.open();
            } catch (RuntimeException e) {
                Log.e("tag", "init_camera: " + e);
                return;
            }
            SetCameraParameters(S_width, s_height);

            ChangeZoom(Math.round(maxZoom / 2));
            try {
                camera.setDisplayOrientation(90);
                camera.setPreviewDisplay(surfaceHolder);
                camera.startPreview();
                camera.setDisplayOrientation(1);
            } catch (Exception e) {
                Log.e("tag", "init_camera: " + e);
                return;
            }
        }
    };

    public void stop_camera() {
        camera.stopPreview();
        camera.release();
    }

    private Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {

        final double ASPECT_TOLERANCE = 0.5;
//        double targetRatio=(double)s_height / S_width;
        double targetRatio = 1.3f;

        if (sizes == null) return null;

        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        int targetHeight = 800;

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
        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (Exception e) {
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int w, int h) {
        //before changing the application orientation, you need to stop the preview, rotate and then start it again

        if (surfaceHolder.getSurface() == null)//check if the surface is ready to receive camera data
            return;

        try {
            camera.stopPreview();
        } catch (Exception e) {
            //this will happen when you are trying the camera if it's not running
        }

        //now, recreate the camera preview
        try {
            SetCameraParameters(w, h);

            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
            camera.setDisplayOrientation(1);
        } catch (Exception exp) {
            Log.i("Camera", "FROM surfaceChanged: " + exp.toString());
        }
    }

    private void SetCameraParameters(int w, int h) {
        try {
            camera.setDisplayOrientation(90);
//            surfaceHolder.setFixedSize(S_width,s_height);
            Camera.Parameters params = camera.getParameters();

            List<Camera.Size> sizes = params.getSupportedPreviewSizes();
            Camera.Size optimalSize = getOptimalPreviewSize(sizes, w, h);
            params.setPreviewSize(optimalSize.width, optimalSize.height);
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
            camera.setParameters(params);

            parameters = camera.getParameters();
            maxZoom = parameters.getMaxZoom();
            s_magnifierFragment.onMaxZoomCamera(maxZoom);
        } catch (Exception e) {
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    private void ChangeZoom(int val) {
        try {
            if (parameters.isZoomSupported()) {
                if (val >= 0 && val < maxZoom) {
                    parameters.setZoom(val);
                    camera.setParameters(parameters);
                } else {
                    // zoom parameter is incorrect
                }
            }
        } catch (Exception e) {
        }
    }

    public void ZoomIn(int val) {
        zoom++;
        ChangeZoom(val);
    }

    public void ZoomOut(int val) {
        zoom--;
        ChangeZoom(val);
    }

    public void refreshCamera() {
        if (surfaceHolder.getSurface() == null) {
            // preview surface does not exist
            return;
        }
        // stop preview before making changes
        try {
            camera.stopPreview();
        } catch (Exception e) {
            // ignore: tried to stop a non-existent preview
        }

        // set preview size and make any resize, rotate or
        // reformatting changes here
        // start preview with new settings
        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (Exception e) {

        }
    }
}
