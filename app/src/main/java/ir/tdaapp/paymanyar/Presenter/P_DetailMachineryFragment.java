package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Machineries;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_States;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_PowerSupply;
import ir.tdaapp.paymanyar.Model.Services.S_DetailMachineryFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailMachinery;

public class P_DetailMachineryFragment {
    Context context;
    S_DetailMachineryFragment s_detailMachineryFragment;
    Tbl_States tbl_states;
    Tbl_Machineries tbl_machineries;
    Api_PowerSupply api_powerSupply;
    Disposable dispose_getVal;

    public P_DetailMachineryFragment(Context context, S_DetailMachineryFragment s_detailMachineryFragment) {
        this.context = context;
        this.s_detailMachineryFragment = s_detailMachineryFragment;
        tbl_states = new Tbl_States(context);
        tbl_machineries = new Tbl_Machineries(context);
        api_powerSupply = new Api_PowerSupply();
    }

    public void start() {
        s_detailMachineryFragment.OnStart();
        s_detailMachineryFragment.onLoading(true);
        getVal();
    }

    /**
     * در اینجا تایتل شهر براساس آیدی آن برگشت می دهد
     **/
    public String getTitleStateById(int id) {
        return tbl_states.getTitleById(id);
    }

    /**
     * در اینجا تایتل ماشین آلات براساس آیدی آن برگشت داده می شود
     **/
    public String getTitleMachineryById(int id) {
        return tbl_machineries.getTitleById(id);
    }

    void getVal() {
        Single<VM_DetailMachinery> data=api_powerSupply.getDetailMachinery(s_detailMachineryFragment.onItemId());
        dispose_getVal=data.subscribeWith(new DisposableSingleObserver<VM_DetailMachinery>() {
            @Override
            public void onSuccess(VM_DetailMachinery detailMachinery) {
                s_detailMachineryFragment.onLoading(false);
                s_detailMachineryFragment.onSuccess();
                s_detailMachineryFragment.onItem(detailMachinery);
                s_detailMachineryFragment.onFinish();
            }

            @Override
            public void onError(Throwable e) {
                s_detailMachineryFragment.onLoading(false);
                s_detailMachineryFragment.onError(Error.GetErrorVolley(e.toString()));
                s_detailMachineryFragment.onFinish();
            }
        });
    }

    public void cancel(String tag) {
        api_powerSupply.cancel(tag, context);

        if (dispose_getVal != null) {
            dispose_getVal.dispose();
        }
    }
}
