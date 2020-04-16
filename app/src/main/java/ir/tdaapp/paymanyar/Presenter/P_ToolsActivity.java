package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import ir.tdaapp.paymanyar.Model.Services.S_ToolsActivity;
import ir.tdaapp.paymanyar.View.Fragments.ToolsFragment;

public class P_ToolsActivity {

    Context context;
    S_ToolsActivity s_toolsActivity;

    public P_ToolsActivity(Context context, S_ToolsActivity s_toolsActivity) {
        this.context = context;
        this.s_toolsActivity = s_toolsActivity;
    }

    //زمانی که این متد فراخوانی شود تمام عملیات اکتیویتی انجام می شود
    public void start(){
        s_toolsActivity.OnStart();
        s_toolsActivity.onAddFragment(new ToolsFragment(),0,0,false, ToolsFragment.TAG);
    }
}
