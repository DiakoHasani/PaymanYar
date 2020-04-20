package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_Tender;
import ir.tdaapp.paymanyar.Model.Services.S_TenderNotificationFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotification;

public class P_TenderNotificationFragment {

    private Context context;
    private S_TenderNotificationFragment s_tenderNotificationFragment;
    Api_Tender api_tender;
    Disposable dispose_getTenderNotification, dispose_setTenders;

    public P_TenderNotificationFragment(Context context, S_TenderNotificationFragment s_tenderNotificationFragment) {
        this.context = context;
        this.s_tenderNotificationFragment = s_tenderNotificationFragment;
        api_tender = new Api_Tender();
    }

    public void start(int page) {

        //اگر مقدار پیج ما صفر باشد یعنی صفحه ما تازه باز شده است یا ریست شده است و عملیات اولیه آن نیز انجام می شود
        //اگر غیر از صفر باشد یعنی کاربر به آخر رسایکلر رسیده و داده های بعدی را از سرور می گیریم
        if (page == 0) {
            s_tenderNotificationFragment.OnStart();
            s_tenderNotificationFragment.onHideAll();
            s_tenderNotificationFragment.onLoading(true);
        } else {
            s_tenderNotificationFragment.onLoadingPaging(true);
        }

        getTenderNotification(page);
    }

    //در اینجا مناقصه ها برگشت داده میشوند
    void getTenderNotification(int page) {

        Single<List<VM_TenderNotification>> data = api_tender.getTenderNotification(page);
        dispose_getTenderNotification = data.subscribeWith(new DisposableSingleObserver<List<VM_TenderNotification>>() {
            @Override
            public void onSuccess(List<VM_TenderNotification> vm_tenderNotifications) {

                s_tenderNotificationFragment.onSuccess();

                if (page == 0) {
                    s_tenderNotificationFragment.onHideAll();
                    s_tenderNotificationFragment.onShowRecycler();
                } else {
                    s_tenderNotificationFragment.onLoadingPaging(false);
                    s_tenderNotificationFragment.onShowRecycler();
                }

                setTenders(vm_tenderNotifications);
            }

            @Override
            public void onError(Throwable e) {
                if (page == 0) {
                    s_tenderNotificationFragment.onHideAll();
                    s_tenderNotificationFragment.onError(Error.GetErrorVolley(e.toString()));
                } else {
                    s_tenderNotificationFragment.onLoadingPaging(false);
                    s_tenderNotificationFragment.onError(Error.GetErrorVolley(e.toString()));
                }
            }
        });
    }

    //در اینجا مناقصات به رسایکلر اضافه می شوند
    void setTenders(List<VM_TenderNotification> tenders) {

        Observable<VM_TenderNotification> data = Observable.fromIterable(tenders);

        dispose_setTenders = data.subscribe(vm_tenderNotification -> {
            s_tenderNotificationFragment.onItemTender(vm_tenderNotification);
        }, throwable -> {

        }, () -> {
            s_tenderNotificationFragment.onFinish();
        });
    }

    public void Cancel(String Tag) {
        if (dispose_getTenderNotification != null) {
            dispose_getTenderNotification.dispose();
        }

        if (dispose_setTenders != null) {
            dispose_setTenders.dispose();
        }
    }
}
