package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Tender;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_Tender;
import ir.tdaapp.paymanyar.Model.Services.S_FavoriteTenderNotificationsFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotifications;

public class P_FavoriteTenderNotificationsFragment {

    Context context;
    S_FavoriteTenderNotificationsFragment s_favoriteTenderNotificationsFragment;
    Tbl_Tender tbl_tender;
    Api_Tender api_tender;
    Disposable dispose_getTenders,dispose_setTenders;

    public P_FavoriteTenderNotificationsFragment(Context context, S_FavoriteTenderNotificationsFragment s_favoriteTenderNotificationsFragment) {
        this.context = context;
        this.s_favoriteTenderNotificationsFragment = s_favoriteTenderNotificationsFragment;
        tbl_tender=new Tbl_Tender(context);
        api_tender=new Api_Tender();
    }

    public void start() {
        s_favoriteTenderNotificationsFragment.OnStart();
        s_favoriteTenderNotificationsFragment.onHideAll();

        getTenders();
    }

    void getTenders(){
        s_favoriteTenderNotificationsFragment.onLoading(true);

        Single<List<VM_TenderNotifications>> data=api_tender.getFavorites(tbl_tender.getFavorites(),tbl_tender);
        dispose_getTenders=data.subscribeWith(new DisposableSingleObserver<List<VM_TenderNotifications>>() {
            @Override
            public void onSuccess(List<VM_TenderNotifications> notifications) {
                s_favoriteTenderNotificationsFragment.onHideAll();
                s_favoriteTenderNotificationsFragment.onSuccess();
                setTenders(notifications);
            }

            @Override
            public void onError(Throwable e) {
                s_favoriteTenderNotificationsFragment.onHideAll();
                s_favoriteTenderNotificationsFragment.onError(Error.GetErrorVolley(e.toString()));
            }
        });
    }

    void setTenders(List<VM_TenderNotifications> tenders){

        Observable<VM_TenderNotifications> vals=Observable.fromIterable(tenders);
        dispose_setTenders=vals.subscribe(vm_tenderNotifications -> {
            s_favoriteTenderNotificationsFragment.onItem(vm_tenderNotifications);
        },throwable -> {

        },() -> {
            s_favoriteTenderNotificationsFragment.onFinish();
        });

    }

    public void cancel(String tag){
        api_tender.Cancel(tag,context);

        if (dispose_getTenders!=null){
            dispose_getTenders.dispose();
        }

        if (dispose_setTenders!=null){
            dispose_setTenders.dispose();
        }
    }

}
