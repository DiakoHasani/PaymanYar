package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import com.google.android.material.textfield.TextInputEditText;

import android.provider.Settings.Secure;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.li_utility.Codes.Validation;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_Login;
import ir.tdaapp.paymanyar.Model.Services.S_LoginFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;

public class P_LoginFragment {

    private Context context;
    private S_LoginFragment s_loginFragment;
    Api_Login api_login;
    Disposable dispose_startLogin;

    public P_LoginFragment(Context context, S_LoginFragment s_loginFragment) {
        this.context = context;
        this.s_loginFragment = s_loginFragment;
        api_login = new Api_Login();
    }

    public void startLogin(TextInputEditText txt_CellPhone) {

        if (Validation.Required(txt_CellPhone, context.getString(R.string.ThisValueMust_be_Filled))) {

            try {

                String android_id = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
                String api_key = ((MainActivity) context).getTbl_notification().getToken(context);

                s_loginFragment.onLoading(true);
                Single<VM_Message> data = api_login.login(txt_CellPhone.getText().toString(), android_id, api_key);

                dispose_startLogin = data.subscribeWith(new DisposableSingleObserver<VM_Message>() {
                    @Override
                    public void onSuccess(VM_Message message) {
                        s_loginFragment.onLoading(false);
                        s_loginFragment.onFinish(message);
                    }

                    @Override
                    public void onError(Throwable e) {
                        s_loginFragment.onLoading(false);
                        s_loginFragment.onError(Error.GetErrorVolley(e.toString()));
                    }
                });

            } catch (Exception e) {
                s_loginFragment.onError(ResaultCode.Error);
            }

        } else {
            s_loginFragment.onNotValid();
        }

    }

    public void Cancel(String tag) {
        api_login.cancel(tag, context);

        if (dispose_startLogin != null) {
            dispose_startLogin.dispose();
        }
    }
}
