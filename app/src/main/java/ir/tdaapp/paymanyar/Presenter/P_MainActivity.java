package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;


import androidx.fragment.app.Fragment;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_Central;
import ir.tdaapp.paymanyar.Model.Services.S_MainActivity;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Fragments.ChargeFragment;
import ir.tdaapp.paymanyar.View.Fragments.HomeFragment;
import ir.tdaapp.paymanyar.View.Fragments.NewsPaperFragment;
import ir.tdaapp.paymanyar.View.Fragments.TenderNotificationFragment;

public class P_MainActivity {

    Context context;
    S_MainActivity s_mainActivity;
    Api_Central api_central;

    public P_MainActivity(Context context, S_MainActivity s_mainActivity) {
        this.context = context;
        this.s_mainActivity = s_mainActivity;
    }

    //زمانی که این متد فراخوانی شود تمام عملیات اکتیویتی انجام می شود
    public void start() {
        s_mainActivity.OnStart();
    }

    //اگر در برنامه خطای رخ دهد در اینجا آن خطا را به سمت سرور ارسال می کنیم
    public void sendError(String name, String text) {
        api_central.postError(name, text);
    }

    public void cancel(String tag) {
        api_central.cancel(tag, context);
    }
}
