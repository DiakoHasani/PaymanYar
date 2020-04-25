package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_Tender;
import ir.tdaapp.paymanyar.Model.Services.S_DetailsTenderFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailsTender;

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

    public void start(int Id) {
        s_detailsTenderDialog.OnStart();
        s_detailsTenderDialog.onHideAll();
        s_detailsTenderDialog.onLoading(true);

        getDetails(Id);
    }

    //در اینجا جزئیات مناقصه از سرور گرفته می شود
    void getDetails(int Id) {

        Single<VM_DetailsTender> data = api_tender.getDetailsTender(Id);

        dispose_getDetails = data.subscribeWith(new DisposableSingleObserver<VM_DetailsTender>() {
            @Override
            public void onSuccess(VM_DetailsTender detailsTender) {
                s_detailsTenderDialog.onHideAll();
                s_detailsTenderDialog.onGetDetail(detailsTender);
                s_detailsTenderDialog.onFinish();
            }

            @Override
            public void onError(Throwable e) {
                s_detailsTenderDialog.onHideAll();
                s_detailsTenderDialog.onError(Error.GetErrorVolley(e.toString()));
            }
        });
    }

    public void Cancel(String Tag) {
        if (dispose_getDetails != null) {
            dispose_getDetails.dispose();
        }
    }
}
