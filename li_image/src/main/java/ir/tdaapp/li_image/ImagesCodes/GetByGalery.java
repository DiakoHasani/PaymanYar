package ir.tdaapp.li_image.ImagesCodes;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;

import ir.tdaapp.li_image.Services.IGetBitmapImage;

public class GetByGalery {

    Activity getActivity;
    int RequestCode = 0;
    IGetBitmapImage iGetBitmapImage;

    public GetByGalery(Activity getActivity, int requestCode, IGetBitmapImage iGetBitmapImage) {
        this.getActivity = getActivity;
        RequestCode = requestCode;
        this.iGetBitmapImage = iGetBitmapImage;
        Get();
    }

    void Get() {
        Dexter.withActivity(getActivity).withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE).withListener(
                new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI).setType("image/jpg");
                        getActivity.startActivityForResult(intent, RequestCode);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }
        ).check();
    }

    public void Continue(Intent data) {

        try {

            Uri selectedImage = data.getData();
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor c = getActivity.getContentResolver().query(selectedImage, filePath, null, null, null);
            c.moveToFirst();
            final int columnIndex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString(columnIndex);
            c.close();

            File file = new File(picturePath);
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            iGetBitmapImage.GetImage(bitmap);

        } catch (Exception e) {
            iGetBitmapImage.GetImage(null);
        }

    }
}
