package ir.tdaapp.paymanyar.Model.Services;

/**
 * در اینجا وضعیت دانلود فایل گرفته می شود
 * **/
public interface OnProgressDownloadFile {
    void onStart();
    void onProgress(int percent);
    void onFinish();
}
