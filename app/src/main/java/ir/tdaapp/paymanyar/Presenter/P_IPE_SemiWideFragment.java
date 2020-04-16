package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import ir.tdaapp.paymanyar.Model.Services.S_IPE_SemiWideFragment;

public class P_IPE_SemiWideFragment {

    private Context context;
    private S_IPE_SemiWideFragment s_ipe_semiWideFragment;

    public P_IPE_SemiWideFragment(Context context, S_IPE_SemiWideFragment s_ipe_semiWideFragment) {
        this.context = context;
        this.s_ipe_semiWideFragment = s_ipe_semiWideFragment;
    }

    public void start(){
        s_ipe_semiWideFragment.OnStart();
    }
}
