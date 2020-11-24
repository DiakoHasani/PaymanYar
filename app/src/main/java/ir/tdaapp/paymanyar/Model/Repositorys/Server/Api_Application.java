package ir.tdaapp.paymanyar.Model.Repositorys.Server;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.Single;
import ir.tdaapp.paymanyar.Model.Services.OnProgressDownloadFile;

/**
 * این کلاس برای کارهای جانبی برنامه می باشد مثل دانلود آخرین ورژن اپلیکیشن برای بروزرسانی برنامه
 **/
public class Api_Application {

    //زمانی که این متغیر فالس باشد یعنی کاربر بی خیال شده و لازم نیست که به دانلود فایل ادامه دهد
    boolean isActiveToDownload = true;

    /**
     * در اینجا فایل ای پی کی برنامه برای آپدیت از سرور گرفته می شود
     **/
    public Single<Boolean> downloadAPK(String fileUrl, String title, OnProgressDownloadFile onProgressDownloadFile) {
        return Single.create(emitter -> {
            new Thread(() -> {
                try {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(() -> {
                        onProgressDownloadFile.onStart();
                    });

                    URL url = new URL(fileUrl);
                    HttpURLConnection c = (HttpURLConnection) url.openConnection();
//                    c.setRequestMethod("GET");
//                    c.setDoOutput(true);
                    c.connect();
                    int fileLength = c.getContentLength();

                    String PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
                    File file = new File(PATH);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    File outputFile = new File(file, title);
                    FileOutputStream fos = new FileOutputStream(outputFile);
                    InputStream is = c.getInputStream();
                    byte[] buffer = new byte[1024];
                    int len1;
                    long total = 0;
                    while ((len1 = is.read(buffer)) != -1) {
                        total += len1;
                        fos.write(buffer, 0, len1);
                        long finalTotal = total;
                        handler.post(() -> {
                            onProgressDownloadFile.onProgress((int) (finalTotal * 100 / fileLength));
                        });
                    }
                    handler.post(() -> {
                        onProgressDownloadFile.onFinish();
                    });
                    fos.flush();
                    fos.close();
                    is.close();
                    if (isActiveToDownload)
                        emitter.onSuccess(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    if (isActiveToDownload)
                        emitter.onSuccess(false);
                }
            }).start();
        });
    }

    public void cancel() {
        isActiveToDownload = false;
    }
}
