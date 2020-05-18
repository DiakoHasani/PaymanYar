package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_SMS;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_SMS;
import ir.tdaapp.paymanyar.Model.Services.S_SmsFragment;
import ir.tdaapp.paymanyar.Model.Services.addSMS;
import ir.tdaapp.paymanyar.Model.Services.removeSMS;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_SMS;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;

public class P_SmsFragment {

    Context context;
    S_SmsFragment s_smsFragment;
    Api_SMS api_sms;
    Tbl_SMS tbl_sms;
    Disposable dispose_getSMS, dispose_setSMS, dispose_ArchiveMessage;

    public P_SmsFragment(Context context, S_SmsFragment s_smsFragment) {
        this.context = context;
        this.s_smsFragment = s_smsFragment;
        api_sms = new Api_SMS();
        tbl_sms = new Tbl_SMS(context);
    }

    public void start() {
        s_smsFragment.OnStart();
        getSMS();
    }

    //در اینجا لیست پیامک ها گرفته می شود
    void getSMS() {

        s_smsFragment.onHideAll();
        s_smsFragment.onLoading(true);

        int userId = ((MainActivity) context).getTbl_user().getUserId(context);

        Single<List<VM_SMS>> data = api_sms.getSMS(1, tbl_sms, s_smsFragment.onShowAllSMS());

        dispose_getSMS = data.subscribeWith(new DisposableSingleObserver<List<VM_SMS>>() {
            @Override
            public void onSuccess(List<VM_SMS> vm_sms) {
                s_smsFragment.onHideAll();

                if (vm_sms.size() == 0) {
                    s_smsFragment.onEmpty();
                } else {
                    s_smsFragment.onSuccess();
                    setSMS(vm_sms);
                }
            }

            @Override
            public void onError(Throwable e) {
                s_smsFragment.onHideAll();
                s_smsFragment.onError(Error.GetErrorVolley(e.toString()));
            }
        });
    }

    //در اینجا پیام ها یکی یکی برای اضافه شدن به رسایکلر به صفحه ارسال می شوند
    void setSMS(List<VM_SMS> smsList) {

        Observable<VM_SMS> data = Observable.fromIterable(smsList);

        dispose_setSMS = data.subscribe(sms -> {
            s_smsFragment.onItemSMS(sms);
        }, throwable -> {

        }, () -> {
            s_smsFragment.onFinish();
        });
    }

    //در اینجا یک پیام حذف می شود
    public void ArchiveMessage(String smsId) {

        s_smsFragment.onLoadingArchive(true);

        int userId = ((MainActivity) context).getTbl_user().getUserId(context);
        Single<VM_Message> data = api_sms.setArchiveSMS(smsId, 1);
        dispose_ArchiveMessage = data.subscribeWith(new DisposableSingleObserver<VM_Message>() {
            @Override
            public void onSuccess(VM_Message message) {
                s_smsFragment.onLoadingArchive(false);
                s_smsFragment.onArchiveMessage(message, smsId);
            }

            @Override
            public void onError(Throwable e) {
                s_smsFragment.onLoadingArchive(false);
                s_smsFragment.onErrorArchiveMessage(Error.GetErrorVolley(e.toString()));
            }
        });
    }

    public void addFevorit(String id, addSMS a) {
        tbl_sms.add(id, a);
    }

    public void removeFevorit(String id, removeSMS r) {
        tbl_sms.remove(id, r);
    }

    public void Cancel(String tag) {
        api_sms.Cancel(tag, context);

        if (dispose_getSMS != null) {
            dispose_getSMS.dispose();
        }

        if (dispose_setSMS != null) {
            dispose_setSMS.dispose();
        }

        if (dispose_ArchiveMessage != null) {
            dispose_ArchiveMessage.dispose();
        }
    }
}
