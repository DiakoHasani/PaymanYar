package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_SavesGps;
import ir.tdaapp.paymanyar.Model.Services.S_SavesGpsDialog;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_SavesGps;

public class P_SavesGpsDialog {

    private Context context;
    private S_SavesGpsDialog s_savesGpsDialog;
    Disposable dispose_getSavesGps, dispose_setGPS;
    Tbl_SavesGps tbl_savesGps;

    public P_SavesGpsDialog(Context context, S_SavesGpsDialog s_savesGpsDialog) {
        this.context = context;
        this.s_savesGpsDialog = s_savesGpsDialog;
        tbl_savesGps = new Tbl_SavesGps(context);
    }

    public void start() {
        s_savesGpsDialog.OnStart();

        s_savesGpsDialog.onHideAll();
        setGPS();
    }

    //در اینجا نقشه ها به رسایکلر اضافه می شوند
    void setGPS() {
        s_savesGpsDialog.onShowRecycler();
        Observable<VM_SavesGps> list = Observable.fromIterable(tbl_savesGps.getAll());
        dispose_setGPS = list.subscribe(gps -> {
                    s_savesGpsDialog.onAddGpsItem(gps);
                }, throwable -> {
                },
                () -> {
                    s_savesGpsDialog.onFinish();
                });
    }


    public void RemoveITem(String id) {
        tbl_savesGps.removeItem(id);
    }

    public void ShareItem(VM_SavesGps item) {
        try {
            String uri = "google.streetview:cbll=" + item.getLength() + "," + item.getWide();

            Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            mapIntent.setPackage("com.google.android.apps.maps");
            this.context.startActivity(mapIntent);
        } catch (Exception e) {
        }
    }


}
