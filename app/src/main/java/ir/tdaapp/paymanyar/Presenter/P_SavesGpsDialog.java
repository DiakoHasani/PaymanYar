package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.DBCursor;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.DBExcute;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.FieldItem;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.RecordHolder;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.database;
import ir.tdaapp.paymanyar.Model.Services.S_SavesGpsDialog;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_SavesGps;

public class P_SavesGpsDialog {

    private Context context;
    private S_SavesGpsDialog s_savesGpsDialog;
    Disposable dispose_getSavesGps, dispose_setGPS;
    private DBExcute dbExcute;

    public P_SavesGpsDialog(Context context, S_SavesGpsDialog s_savesGpsDialog) {
        this.context = context;
        this.s_savesGpsDialog = s_savesGpsDialog;
        dbExcute=DBExcute.getInstance(this.context);
    }

    public void start() {
        s_savesGpsDialog.OnStart();

        s_savesGpsDialog.onHideAll();
        ShowLocations();
    }

    //در اینجا نقشه ها گرفته می شوند
    void getSavesGps(ArrayList<VM_SavesGps> arrayList) {

        Single<List<VM_SavesGps>> vals = Single.create(emitter -> {

            new Thread(() -> {
                try {

                    emitter.onSuccess(arrayList);

                } catch (Exception ex) {
                    emitter.onError(ex);
                }
            }).run();

        });


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

    public void ShowLocations(){
        dbExcute.Open();

        ArrayList<VM_SavesGps> arrayList=new ArrayList<>();

        dbExcute.Read(database.QRY_GPS_GET_All,null).RecordFound(new DBCursor.ListCounter() {
            @Override
            public void onEachrecord(ArrayList<FieldItem> record) {
                VM_SavesGps item=new VM_SavesGps();
                item.setId(record.get(0).value);
                item.setLength(record.get(1).value);
                item.setWide(record.get(2).value);
                arrayList.add(item);
            }
        }, null, new DBCursor.FetchFinishListener() {
            @Override
            public void onFinish() {
                getSavesGps(arrayList);
            }
        },3);

    }


    public void RemoveITem(String id){
        dbExcute.Open();
        dbExcute.Execute(database.QRY_GPS_REMOVE_ITEM,new RecordHolder(new FieldItem("#1#",id)));
    }

    public void ShareItem(VM_SavesGps item){

    }


}
