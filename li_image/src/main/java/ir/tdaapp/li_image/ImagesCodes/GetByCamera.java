package ir.tdaapp.li_image.ImagesCodes;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import ir.tdaapp.li_image.Services.IGetBitmapImage;

import static androidx.core.content.FileProvider.getUriForFile;

public class GetByCamera extends Application{

    Activity getActivity;
    int RequestCode=0;
    IGetBitmapImage iGetBitmapImage;

    public GetByCamera(Activity getActivity, int requestCode, IGetBitmapImage iGetBitmapImage) {
        this.getActivity = getActivity;
        RequestCode = requestCode;
        this.iGetBitmapImage = iGetBitmapImage;
        Get();
    }

    void Get(){
        Dexter.withActivity(getActivity).withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA).withListener(
                new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        String fileName = "temp.jpg";
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, getCacheImagePath(getActivity,fileName));

                        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP ) {
                            takePictureIntent.setClipData(ClipData.newRawUri("",getCacheImagePath(getActivity,fileName)));
                            takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION|Intent.FLAG_GRANT_READ_URI_PERMISSION );
                        }
                        if (takePictureIntent.resolveActivity(getActivity.getPackageManager()) != null) {
                            getActivity.startActivityForResult(takePictureIntent, RequestCode);
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }
                }
        ).check();
    }

    private Uri getCacheImagePath(Activity getActivity, String fileName) {
        File path = new File(getActivity.getExternalCacheDir(), "camera");
        if (!path.exists()) path.mkdirs();
        File image = new File(path, fileName);
        return getUriForFile(getActivity, getActivity.getPackageName() + ".provider", image);
    }

    public void Continue(){
        try {
            Uri selectedImage = getCacheImagePath(getActivity,"temp.jpg");
            getActivity.getContentResolver().notifyChange(selectedImage, null);
            ContentResolver cr = getActivity.getContentResolver();
            Bitmap bitmap = android.provider.MediaStore.Images.Media.getBitmap(cr, selectedImage);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            Bitmap decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));

            iGetBitmapImage.GetImage(decoded);

        } catch (Exception e) {
            iGetBitmapImage.GetImage(null);
        }
    }

}
