package ir.tdaapp.paymanyar.Model.Utilitys;

import android.os.Environment;

import java.io.File;

//در این کلاس می توانیم یک پوشه در حافظه ایجاد کرده و داده های مربوط به پروژه را در آن نگهداری کنیم
public class ProjectDirectory {

    //در اینجا یک پوشه ایجاد می کند
    public static String createDirectory(String directoryName) {

        if (!existsDirectory(directoryName)) {
            File directory= new File(Environment.getExternalStorageDirectory(),directoryName);
            directory.mkdir();
        }

        return directoryName;
    }

    //در اینجا چک می کند که چنین پوشه ای قبلا ایجاد شده است یا نه
    public static boolean existsDirectory(String directoryName) {

        File sdCardDirectory = Environment.getExternalStorageDirectory();
        String Path = sdCardDirectory.getPath() + "/" + directoryName;
        File directory = new File(Path);

        return directory.mkdir();
    }
}
