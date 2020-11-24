package ir.tdaapp.paymanyar.Model.Services;

public interface S_UpdateAppDialog {
    String getApplicationUrl();
    void onError();
    void onSuccess(String fileName);
    void onLoading(boolean load);
}
