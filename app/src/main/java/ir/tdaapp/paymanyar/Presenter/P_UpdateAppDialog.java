package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_Application;
import ir.tdaapp.paymanyar.Model.Services.OnProgressDownloadFile;
import ir.tdaapp.paymanyar.Model.Services.S_UpdateAppDialog;

public class P_UpdateAppDialog {
    Context context;
    S_UpdateAppDialog s_updateAppDialog;
    Api_Application api_application;
    Disposable dispose_updateApplication;

    public P_UpdateAppDialog(Context context, S_UpdateAppDialog s_updateAppDialog) {
        this.context = context;
        this.s_updateAppDialog = s_updateAppDialog;
        api_application = new Api_Application();
    }

    public void updateApplication(OnProgressDownloadFile onProgressDownloadFile) {
        s_updateAppDialog.onLoading(true);
        String applicationName = "paymanyar.apk";
        Single<Boolean> data = api_application.downloadAPK(s_updateAppDialog.getApplicationUrl(), applicationName, onProgressDownloadFile);
        dispose_updateApplication = data.subscribeWith(new DisposableSingleObserver<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                new Handler(Looper.getMainLooper()).post(() -> {
                    s_updateAppDialog.onLoading(false);
                    s_updateAppDialog.onSuccess(applicationName);
                });
            }

            @Override
            public void onError(Throwable e) {
                new Handler(Looper.getMainLooper()).post(() -> {
                    s_updateAppDialog.onLoading(false);
                    s_updateAppDialog.onError();
                });
            }
        });
    }

    public void cancel() {
        api_application.cancel();

        if (dispose_updateApplication != null) {
            dispose_updateApplication.dispose();
        }
    }
}
