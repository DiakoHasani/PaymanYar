package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Machineries;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_States;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_PowerSupply;
import ir.tdaapp.paymanyar.Model.Services.S_MyMachineriesFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Machinery;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PowerSupplyNetwork;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;

public class P_MyMachineriesFragment {
    Context context;
    S_MyMachineriesFragment s_myMachineriesFragment;
    Api_PowerSupply api_powerSupply;
    Tbl_States tbl_states;
    Tbl_Machineries tbl_machineries;
    Disposable dispose_getVals, dispose_setVals;

    public P_MyMachineriesFragment(Context context, S_MyMachineriesFragment s_myMachineriesFragment) {
        this.context = context;
        this.s_myMachineriesFragment = s_myMachineriesFragment;
        api_powerSupply = new Api_PowerSupply();
        tbl_states = new Tbl_States(context);
        tbl_machineries = new Tbl_Machineries(context);
    }

    public void start() {
        if (((MainActivity) context).getTbl_user().hasAccount(context)) {
            s_myMachineriesFragment.OnStart();
            s_myMachineriesFragment.onHideAll();
            s_myMachineriesFragment.onLoading(true);
            getVals();
        } else {
            s_myMachineriesFragment.noAccount();
        }
    }

    void getVals() {
        int userId = ((MainActivity) context).getTbl_user().getUserId(context);
        Single<List<VM_Machinery>> data = api_powerSupply.getMyMachineries(userId, tbl_states.getProvincesOrCities(0), tbl_machineries.getMachineries());
        dispose_getVals = data.subscribeWith(new DisposableSingleObserver<List<VM_Machinery>>() {
            @Override
            public void onSuccess(List<VM_Machinery> vm_machineries) {
                s_myMachineriesFragment.onHideAll();
                s_myMachineriesFragment.onLoading(false);
                s_myMachineriesFragment.onSuccess();

                if (vm_machineries.size() > 0) {
                    setVals(vm_machineries);
                } else {
                    s_myMachineriesFragment.onEmpty();
                    s_myMachineriesFragment.onFinish();
                }
            }

            @Override
            public void onError(Throwable e) {
                s_myMachineriesFragment.onHideAll();
                s_myMachineriesFragment.onLoading(false);
                s_myMachineriesFragment.onError(Error.GetErrorVolley(e.toString()));
            }
        });
    }

    void setVals(List<VM_Machinery> vals) {
        Observable<VM_Machinery> data = Observable.fromIterable(vals);

        dispose_setVals = data.subscribe(machinery -> {
            s_myMachineriesFragment.onItem(machinery);
        }, throwable -> {

        }, () -> {
            s_myMachineriesFragment.onFinish();
        });
    }

    public void cancel(String tag) {
        api_powerSupply.cancel(tag, context);

        if (dispose_getVals != null) {
            dispose_getVals.dispose();
        }

        if (dispose_setVals != null) {
            dispose_setVals.dispose();
        }
    }
}
