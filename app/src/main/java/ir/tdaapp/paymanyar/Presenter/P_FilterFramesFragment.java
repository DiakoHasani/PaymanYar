package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Tender;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_Tender;
import ir.tdaapp.paymanyar.Model.Services.S_FilterFramesFragment;
import ir.tdaapp.paymanyar.Model.Services.addTender;
import ir.tdaapp.paymanyar.Model.Services.removeTender;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotification;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotifications;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;

public class P_FilterFramesFragment {

    Context context;
    S_FilterFramesFragment s_filterFramesFragment;
    Api_Tender api_tender;
    Tbl_Tender tbl_tender;
    Disposable dispose_getTenders, dispose_setTenders;

    public P_FilterFramesFragment(Context context, S_FilterFramesFragment s_filterFramesFragment) {
        this.context = context;
        this.s_filterFramesFragment = s_filterFramesFragment;
        api_tender = new Api_Tender();
        tbl_tender = new Tbl_Tender(context);
    }

    public void start(int page) {
        s_filterFramesFragment.OnStart();
        s_filterFramesFragment.onHideAll();
        s_filterFramesFragment.onLoading(true);
        getTenders(page);
    }

    void getTenders(int page) {
        String api_Key = ((MainActivity) context).getTbl_notification().getToken(context);

        Single<VM_TenderNotification> data = api_tender.getTendersFilter(page, api_Key, tbl_tender);

        dispose_getTenders = data.subscribeWith(new DisposableSingleObserver<VM_TenderNotification>() {
            @Override
            public void onSuccess(VM_TenderNotification notification) {
                s_filterFramesFragment.onHideAll();
                s_filterFramesFragment.onCountTenders(notification.getCountTenders());

                if (notification.getTenderNotifications().size() > 0) {
                    s_filterFramesFragment.onSuccess();
                    setTenders(notification.getTenderNotifications());
                } else {
                    s_filterFramesFragment.onEmpty();
                    s_filterFramesFragment.onFinish();
                }
            }

            @Override
            public void onError(Throwable e) {
                s_filterFramesFragment.onHideAll();
                s_filterFramesFragment.onError(Error.GetErrorVolley(e.toString()));
            }
        });
    }

    void setTenders(List<VM_TenderNotifications> tenders) {

        Observable<VM_TenderNotifications> data = Observable.fromIterable(tenders);
        dispose_setTenders = data.subscribe(vm_tenderNotifications -> {
            s_filterFramesFragment.onItemTenders(vm_tenderNotifications);
        }, throwable -> {

        }, () -> {
            s_filterFramesFragment.onFinish();
        });
    }

    //در اینجا یک مناقصه به لیست علاقه مندی ها اضافه می شود
    public void AddFavorit(String id, addTender tender) {
        tbl_tender.Add(id, tender);
    }

    public void RemoveFevorit(String id, removeTender t) {
        tbl_tender.remove(id, t);
    }

    public void cancel(String tag) {
        api_tender.Cancel(tag, context);

        if (dispose_getTenders != null) {
            dispose_getTenders.dispose();
        }

        if (dispose_setTenders != null) {
            dispose_setTenders.dispose();
        }
    }

}
