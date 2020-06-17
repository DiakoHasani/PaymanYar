package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;
import android.widget.EditText;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.li_utility.Codes.Validation;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_SMS;
import ir.tdaapp.paymanyar.Model.Services.S_SupportFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Support;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;

public class P_SupportFragment {

    Context context;
    S_SupportFragment s_supportFragment;
    Disposable dispose_sendMessage, dispose_getVals, dispose_setUsers;
    Api_SMS api_sms;

    public P_SupportFragment(Context context, S_SupportFragment s_supportFragment) {
        this.context = context;
        this.s_supportFragment = s_supportFragment;

        api_sms = new Api_SMS();
    }

    public void start() {
        s_supportFragment.OnStart();
        s_supportFragment.onHideAll();
        s_supportFragment.onLoading(true);
        getVals();
    }

    void getVals() {

        Single<List<VM_Support>> getSupports = api_sms.getUsersSupport();
        dispose_getVals = getSupports.subscribeWith(new DisposableSingleObserver<List<VM_Support>>() {
            @Override
            public void onSuccess(List<VM_Support> vm_supports) {
                s_supportFragment.onHideAll();
                s_supportFragment.onSuccess();
                setUsers(vm_supports);
            }

            @Override
            public void onError(Throwable e) {
                s_supportFragment.onHideAll();
                s_supportFragment.onError(Error.GetErrorVolley(e.toString()));
            }
        });

    }

    void setUsers(List<VM_Support> users) {
        Observable<VM_Support> list = Observable.fromIterable(users);

        dispose_setUsers = list.subscribe(support -> {
            s_supportFragment.onUser(support);
        }, throwable -> {

        }, () -> {
            s_supportFragment.onFinish();
        });

    }

    public void sendMessage(EditText txt) {

        if (Validation.Required(txt, context.getResources().getString(R.string.ThisValueMust_be_Filled))) {


            int userId = ((MainActivity) context).getTbl_user().getUserId(context);

            //اگر کاربر حساب کاربری داشته باشد اجازه ارسال را به او می دهد
            if (userId != 0) {
                s_supportFragment.onLoadingSend(true);

                Single<VM_Message> data = api_sms.postSMS(txt.getText().toString(), userId);

                dispose_sendMessage = data.subscribeWith(new DisposableSingleObserver<VM_Message>() {
                    @Override
                    public void onSuccess(VM_Message message) {
                        s_supportFragment.onLoadingSend(false);
                        s_supportFragment.onFinishSend(message);
                    }

                    @Override
                    public void onError(Throwable e) {
                        s_supportFragment.onLoadingSend(false);
                        s_supportFragment.onErrorSend(Error.GetErrorVolley(e.toString()));
                    }
                });
            } else {
                s_supportFragment.onCreateAccount();
            }
        } else {
            s_supportFragment.onNotValid();
        }

    }

    public void Cancel(String tag) {

        api_sms.Cancel(tag, context);
        if (dispose_sendMessage != null) {
            dispose_sendMessage.dispose();
        }

        if (dispose_getVals != null) {
            dispose_getVals.dispose();
        }

        if (dispose_setUsers != null) {
            dispose_setUsers.dispose();
        }

    }
}
