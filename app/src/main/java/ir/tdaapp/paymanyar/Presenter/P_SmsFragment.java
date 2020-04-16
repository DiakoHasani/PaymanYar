package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import ir.tdaapp.paymanyar.Model.Services.S_SmsFragment;

public class P_SmsFragment {
    Context context;
    S_SmsFragment s_smsFragment;

    public P_SmsFragment(Context context, S_SmsFragment s_smsFragment) {
        this.context = context;
        this.s_smsFragment = s_smsFragment;
    }
}
