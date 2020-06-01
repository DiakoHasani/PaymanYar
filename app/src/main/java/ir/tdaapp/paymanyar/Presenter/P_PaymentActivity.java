package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_User;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_Charge;
import ir.tdaapp.paymanyar.Model.Services.S_PaymentActivity;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.View.Activitys.PaymentActivity;

public class P_PaymentActivity {
    Context context;
    S_PaymentActivity s_paymentActivity;
    Api_Charge api_charge;
    Disposable dispose_start;
    Tbl_User tbl_user;

    public P_PaymentActivity(Context context, S_PaymentActivity s_paymentActivity) {
        this.context = context;
        this.s_paymentActivity = s_paymentActivity;

        api_charge = new Api_Charge();
        tbl_user=new Tbl_User();
    }

    public void start() {
        s_paymentActivity.OnStart();
        s_paymentActivity.onLoading(true);

        int userId =tbl_user.getUserId(context);
        Single<Integer> val = api_charge.getInventory(userId);

        dispose_start = val.subscribeWith(new DisposableSingleObserver<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                s_paymentActivity.onLoading(false);

                int day = integer / 24;
                int hour = integer % 24;

                s_paymentActivity.onFinish(day, hour);
            }

            @Override
            public void onError(Throwable e) {
                s_paymentActivity.onLoading(false);
                s_paymentActivity.onError(Error.GetErrorVolley(e.toString()));
            }
        });
    }

    public void Cancel(String tag) {
        api_charge.Cancel(context, tag);

        if (dispose_start != null) {
            dispose_start.dispose();
        }
    }
}
