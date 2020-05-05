package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_Tender;
import ir.tdaapp.paymanyar.Model.Services.S_DetailsTenderFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailsTender;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FilterTenderNotification;

public class P_DetailsTenderFragment {

    Context context;
    S_DetailsTenderFragment s_detailsTenderDialog;
    Api_Tender api_tender;
    Disposable dispose_getDetails;

    public P_DetailsTenderFragment(Context context, S_DetailsTenderFragment s_detailsTenderDialog) {
        this.context = context;
        this.s_detailsTenderDialog = s_detailsTenderDialog;
        api_tender = new Api_Tender();
    }

    public void start(VM_FilterTenderNotification filter) {
        s_detailsTenderDialog.OnStart();
        s_detailsTenderDialog.onHideAll();
        s_detailsTenderDialog.onLoading(true);

        getDetails(filter);
    }

    //در اینجا جزئیات مناقصه از سرور گرفته می شود
    void getDetails(VM_FilterTenderNotification filter) {

        Single<VM_DetailsTender> data = api_tender.getDetailsTender(filter);

        dispose_getDetails = data.subscribeWith(new DisposableSingleObserver<VM_DetailsTender>() {
            @Override
            public void onSuccess(VM_DetailsTender detailsTender) {

                //اگر مناقصه پولی باشد و کاربر موجودی داشته باشد می تواند آن را نگاه کند در غیر این صورت آیتم ویژه مشترکان نمایش داده می شود
                if (detailsTender.isStatus()) {

                    //در اینجا تمامی آیتم ها مخفی می شوند
                    s_detailsTenderDialog.onHideAll();

                    //در اینجا جزئیات مناقصه ها در المنت ها ست می شود
                    s_detailsTenderDialog.onGetDetail(detailsTender);

                    //برای زمانی که عملیات ما به پایان رسیده باشد
                    s_detailsTenderDialog.onFinish();
                } else {
                    s_detailsTenderDialog.onHideAll();
                    s_detailsTenderDialog.onShowSubscribers();
                }

                //در اینجا دکمه آیتم بعدی ست می شود
                s_detailsTenderDialog.onGetNextTender(detailsTender.getNextTenderId());

                //در اینجا دکمه آیتم قبلی ست می شود
                s_detailsTenderDialog.onGetPrevTender(detailsTender.getBeforeTenderId());

            }

            @Override
            public void onError(Throwable e) {
                s_detailsTenderDialog.onHideAll();
                s_detailsTenderDialog.onError(Error.GetErrorVolley(e.toString()));
            }
        });
    }

    public void Cancel(String Tag) {

        api_tender.Cancel(Tag, context);

        if (dispose_getDetails != null) {
            dispose_getDetails.dispose();
        }
    }
}
