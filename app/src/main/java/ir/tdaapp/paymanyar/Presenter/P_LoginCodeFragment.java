package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;
import android.os.CountDownTimer;
import android.provider.Settings;

import com.google.android.material.textfield.TextInputEditText;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.li_utility.Codes.Replace;
import ir.tdaapp.li_utility.Codes.Validation;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_Login;
import ir.tdaapp.paymanyar.Model.Services.S_LoginCodeFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;

public class P_LoginCodeFragment {

    Context context;
    S_LoginCodeFragment s_loginCodeFragment;
    Api_Login api_login;
    CountDownTimer timer;
    Disposable dispose_sendCode,dispose_resendSMS;

    public P_LoginCodeFragment(Context context, S_LoginCodeFragment s_loginCodeFragment) {
        this.context = context;
        this.s_loginCodeFragment = s_loginCodeFragment;
        api_login = new Api_Login();
    }

    public void sendCode(TextInputEditText txt,String phoneNumber) {

        if (Validation.Required(txt, context.getString(R.string.ThisValueMust_be_Filled))) {

            s_loginCodeFragment.onLoading(true);

            try {

                String api_key=((MainActivity)context).getTbl_notification().getToken(context);
                String android_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                Single<VM_Message> data = api_login.checkCode(phoneNumber, android_id, Integer.valueOf(Replace.Number_fn_To_en(txt.getText().toString())),api_key);

                dispose_sendCode = data.subscribeWith(new DisposableSingleObserver<VM_Message>() {
                    @Override
                    public void onSuccess(VM_Message message) {
                        s_loginCodeFragment.onLoading(false);
                        s_loginCodeFragment.onFinish(message);
                    }

                    @Override
                    public void onError(Throwable e) {
                        s_loginCodeFragment.onLoading(false);
                        s_loginCodeFragment.onError(Error.GetErrorVolley(e.toString()));
                    }
                });

            } catch (Exception e) {
                s_loginCodeFragment.onError(ResaultCode.Error);
            }

        } else {
            s_loginCodeFragment.onNotValid();
        }

    }

    public void resendSMS(String phoneNumber){

        Single<VM_Message> data=api_login.resendMessage(phoneNumber);
        s_loginCodeFragment.onLoadingResend(true);

        dispose_resendSMS=data.subscribeWith(new DisposableSingleObserver<VM_Message>() {
            @Override
            public void onSuccess(VM_Message message) {
                s_loginCodeFragment.onLoadingResend(false);
                s_loginCodeFragment.onFinishResend(message);
            }

            @Override
            public void onError(Throwable e) {
                s_loginCodeFragment.onLoadingResend(false);
                s_loginCodeFragment.onError(Error.GetErrorVolley(e.toString()));
            }
        });

    }

    //مربوط به تایمر ارسال مجدد پیامک می باشد
    public void startTimer() {

        s_loginCodeFragment.onStartTimer();

        timer = new CountDownTimer(150000, 1000) {
            @Override
            public void onTick(long l) {
                s_loginCodeFragment.onSecondTimer(String.valueOf(l / 1000));
            }

            @Override
            public void onFinish() {
                s_loginCodeFragment.onFinishTimer();
            }
        }.start();

    }

    public void Cancel(String tag) {
        api_login.cancel(tag, context);

        if (timer != null) {
            timer.cancel();
        }

        if (dispose_sendCode != null) {
            dispose_sendCode.dispose();
        }

        if (dispose_resendSMS != null) {
            dispose_resendSMS.dispose();
        }
    }
}
