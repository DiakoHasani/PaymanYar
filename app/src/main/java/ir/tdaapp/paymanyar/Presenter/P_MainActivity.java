package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import ir.tdaapp.paymanyar.Model.Services.S_MainActivity;
import ir.tdaapp.paymanyar.View.Fragments.ChargeFragment;
import ir.tdaapp.paymanyar.View.Fragments.HomeFragment;
import ir.tdaapp.paymanyar.View.Fragments.NewsPaperFragment;
import ir.tdaapp.paymanyar.View.Fragments.TenderNotificationFragment;

public class P_MainActivity {

    Context context;
    S_MainActivity s_mainActivity;

    public P_MainActivity(Context context, S_MainActivity s_mainActivity) {
        this.context = context;
        this.s_mainActivity = s_mainActivity;
    }

    //زمانی که این متد فراخوانی شود تمام عملیات اکتیویتی انجام می شود
    public void start() {
        s_mainActivity.OnStart();
        s_mainActivity.onAddFragment(new HomeFragment(), 0, 0, false, HomeFragment.TAG);
    }
}
