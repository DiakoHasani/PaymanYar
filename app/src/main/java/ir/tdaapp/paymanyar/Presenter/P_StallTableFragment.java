package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import ir.tdaapp.paymanyar.Model.Services.S_StallTableFragment;

public class P_StallTableFragment {

    private Context context;
    private S_StallTableFragment s_stallTableFragment;

    public P_StallTableFragment(Context context, S_StallTableFragment s_stallTableFragment) {
        this.context = context;
        this.s_stallTableFragment = s_stallTableFragment;
    }

    public void start(){
        s_stallTableFragment.OnStart();
    }
}
