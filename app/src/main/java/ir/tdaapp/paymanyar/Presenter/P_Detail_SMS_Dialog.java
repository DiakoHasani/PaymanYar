package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_SMS;
import ir.tdaapp.paymanyar.Model.Services.S_Detail_SMS_Dialog;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailSMS;

public class P_Detail_SMS_Dialog {

    private Context context;
    private S_Detail_SMS_Dialog s_detail_sms_dialog;
    Api_SMS api_sms;
    Disposable dispose_getDetail;

    public P_Detail_SMS_Dialog(Context context, S_Detail_SMS_Dialog s_detail_sms_dialog) {
        this.context = context;
        this.s_detail_sms_dialog = s_detail_sms_dialog;
        api_sms = new Api_SMS();
    }

    public void start(String msgId) {
        s_detail_sms_dialog.onHideAll();
        s_detail_sms_dialog.OnStart();

        getDetail(msgId);
    }

    void getDetail(String msgId) {

        s_detail_sms_dialog.onLoading(true);
        Single<VM_DetailSMS> data = api_sms.getDetailSMS(msgId);

        dispose_getDetail = data.subscribeWith(new DisposableSingleObserver<VM_DetailSMS>() {
            @Override
            public void onSuccess(VM_DetailSMS sms) {
                s_detail_sms_dialog.onHideAll();
                s_detail_sms_dialog.onFinish(sms);
            }

            @Override
            public void onError(Throwable e) {
                s_detail_sms_dialog.onHideAll();
                s_detail_sms_dialog.onError(Error.GetErrorVolley(e.toString()));
            }
        });
    }

    public void Cancel(String tag) {
        api_sms.Cancel(tag, context);

        if (dispose_getDetail != null) {
            dispose_getDetail.dispose();
        }
    }
}
