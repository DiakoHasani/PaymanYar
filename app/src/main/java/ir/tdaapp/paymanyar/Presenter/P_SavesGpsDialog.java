package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_SavesGps;
import ir.tdaapp.paymanyar.Model.Services.S_SavesGpsDialog;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_SavesGps;

public class P_SavesGpsDialog {

    private Context context;
    private S_SavesGpsDialog s_savesGpsDialog;
    Tbl_SavesGps tbl_savesGps;
    Disposable dispose_getSavesGps, dispose_setGPS;

    public P_SavesGpsDialog(Context context, S_SavesGpsDialog s_savesGpsDialog) {
        this.context = context;
        this.s_savesGpsDialog = s_savesGpsDialog;
        tbl_savesGps = new Tbl_SavesGps();
    }

    public void start() {
        s_savesGpsDialog.OnStart();

        s_savesGpsDialog.onHideAll();
        getSavesGps();
    }

    //در اینجا نقشه ها گرفته می شوند
    void getSavesGps() {

        Single<List<VM_SavesGps>> vals = tbl_savesGps.getSavesGps();

        dispose_getSavesGps = vals.subscribeWith(new DisposableSingleObserver<List<VM_SavesGps>>() {
            @Override
            public void onSuccess(List<VM_SavesGps> vm_savesGps) {

                //اگر کاربر نقشه ی ذخیره شده ای داشته باشد شرط زیر اجرا می شود در غیر این صورت آیکون نبود آیتم نمایش داده می شود
                if (vm_savesGps.size() > 0) {
                    s_savesGpsDialog.onShowRecycler();
                    setGPS(vm_savesGps);
                } else {
                    s_savesGpsDialog.onShowDontHaveItem();
                }

            }

            @Override
            public void onError(Throwable e) {
                s_savesGpsDialog.onError();
            }
        });
    }

    //در اینجا نقشه ها به رسایکلر اضافه می شوند
    void setGPS(List<VM_SavesGps> savesGps) {
        Observable<VM_SavesGps> list = Observable.fromIterable(savesGps);
        dispose_setGPS = list.subscribe(gps -> {
                    s_savesGpsDialog.onAddGpsItem(gps);
                }, throwable -> {
                },
                () -> {
                    s_savesGpsDialog.onFinish();
                });
    }

    //در اینجا اگر کاربر صفحه را ببندد عملیات گرفتن داده ها لغو می شود
    public void Cancel() {
        if (dispose_getSavesGps != null) {
            dispose_getSavesGps.dispose();
        }
        if (dispose_setGPS != null) {
            dispose_setGPS.dispose();
        }
    }
}
