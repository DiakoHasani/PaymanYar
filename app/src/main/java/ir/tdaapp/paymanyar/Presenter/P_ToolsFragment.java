package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import ir.tdaapp.paymanyar.Model.Services.S_ToolsFragment;

public class P_ToolsFragment {

    private Context context;
    S_ToolsFragment s_toolsFragment;

    public P_ToolsFragment(Context context, S_ToolsFragment s_toolsFragment) {
        this.context = context;
        this.s_toolsFragment = s_toolsFragment;
    }

    public void start(){
        s_toolsFragment.OnStart();
    }
}
