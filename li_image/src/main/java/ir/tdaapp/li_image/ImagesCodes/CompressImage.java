package ir.tdaapp.li_image.ImagesCodes;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

import id.zelory.compressor.Compressor;

/**
 * Created by Diako on 6/16/2019.
 */


//Add This Code To Gradle: implementation 'id.zelory:compressor:2.1.0'

public class CompressImage {

    int Width, Height, Quality;
    Context context;

    public CompressImage(int Width, int Height, int Quality, Context context) {
        this.Width = Width;
        this.Height = Height;
        this.Quality = Quality;
        this.context = context;
    }

    public Bitmap Compress(String Path) {
        File file = new File(Path);
        Bitmap img = null;

        try {
            Bitmap compressedImage = new Compressor(context)
                    .setMaxWidth(Width)
                    .setMaxHeight(Height)
                    .setQuality(Quality)
                    .setCompressFormat(Bitmap.CompressFormat.WEBP)
                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES).getAbsolutePath())
                    .compressToBitmap(file);
            img = compressedImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public Bitmap Compress(Bitmap bmp) {

        String Name = "temp.jpg";

        File file = new File(SaveImageToMob.SaveImageCamera(Name, bmp));
        Bitmap img = null;
        try {
            Bitmap compressedImage = new Compressor(context)
                    .setMaxWidth(Width)
                    .setMaxHeight(Height)
                    .setQuality(Quality)
                    .setCompressFormat(Bitmap.CompressFormat.WEBP)
                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES).getAbsolutePath())
                    .compressToBitmap(file);
            img = compressedImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
