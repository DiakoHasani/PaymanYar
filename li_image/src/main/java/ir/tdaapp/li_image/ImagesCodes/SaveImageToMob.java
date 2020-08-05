package ir.tdaapp.li_image.ImagesCodes;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Diako on 7/9/2019.
 */

public class SaveImageToMob {


    public static String SaveImageCamera(String Name, Bitmap bmp) {
        File sdCardDirectory = Environment.getExternalStorageDirectory();

        String Path = sdCardDirectory.getPath();

        File image = new File(Path + "/" + Name);


        FileOutputStream outStream;

        try {
            outStream = new FileOutputStream(image);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            /* 100 to keep full quality of the image */

            outStream.flush();
            outStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image.getPath();
    }
}
