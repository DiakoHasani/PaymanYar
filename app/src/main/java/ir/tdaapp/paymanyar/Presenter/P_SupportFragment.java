package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;
import android.widget.EditText;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.li_utility.Codes.Validation;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_SMS;
import ir.tdaapp.paymanyar.Model.Services.S_SupportFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;

public class P_SupportFragment {

    Context context;
    S_SupportFragment s_supportFragment;
    Disposable dispose_sendMessage;
    Api_SMS api_sms;

    public P_SupportFragment(Context context, S_SupportFragment s_supportFragment) {
        this.context = context;
        this.s_supportFragment = s_supportFragment;

        api_sms = new Api_SMS();
    }

    public void sendMessage(EditText txt) {

        if (Validation.Required(txt, context.getResources().getString(R.string.ThisValueMust_be_Filled))) {

            s_supportFragment.onLoading(true);

            int userId=((MainActivity)context).getTbl_user().getUserId(context);

            Single<VM_Message> data = api_sms.postSMS(txt.getText().toString(), userId);

            dispose_sendMessage = data.subscribeWith(new DisposableSingleObserver<VM_Message>() {
                @Override
                public void onSuccess(VM_Message message) {
                    s_supportFragment.onLoading(false);
                    s_supportFragment.onFinish(message);
                }

                @Override
                public void onError(Throwable e) {
                    s_supportFragment.onLoading(false);
                    s_supportFragment.onError(Error.GetErrorVolley(e.toString()));
                }
            });
        } else {
            s_supportFragment.onNotValid();
        }

    }

    public void Cancel(String tag) {

        api_sms.Cancel(tag, context);
        if (dispose_sendMessage != null) {
            dispose_sendMessage.dispose();
        }

    }
}
