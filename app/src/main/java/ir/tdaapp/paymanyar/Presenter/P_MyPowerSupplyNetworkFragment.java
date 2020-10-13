package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Jobs;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_States;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_WorkExperiences;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_PowerSupply;
import ir.tdaapp.paymanyar.Model.Services.S_MyPowerSupplyNetworkFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PowerSupplyNetwork;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;

public class P_MyPowerSupplyNetworkFragment {
    Context context;
    S_MyPowerSupplyNetworkFragment s_myPowerSupplyNetworkFragment;
    Api_PowerSupply api_powerSupply;
    Tbl_States tbl_states;
    Tbl_WorkExperiences tbl_workExperiences;
    Tbl_Jobs tbl_jobs;
    Disposable dispose_getVals, dispose_setVals;

    public P_MyPowerSupplyNetworkFragment(Context context, S_MyPowerSupplyNetworkFragment s_myPowerSupplyNetworkFragment) {
        this.context = context;
        this.s_myPowerSupplyNetworkFragment = s_myPowerSupplyNetworkFragment;
        api_powerSupply = new Api_PowerSupply();
        tbl_states = new Tbl_States(context);
        tbl_workExperiences = new Tbl_WorkExperiences(context);
        tbl_jobs = new Tbl_Jobs(context);
    }

    public void start() {
        if (((MainActivity) context).getTbl_user().hasAccount(context)) {
            s_myPowerSupplyNetworkFragment.OnStart();
            s_myPowerSupplyNetworkFragment.onHideAll();
            s_myPowerSupplyNetworkFragment.onLoading(true);
            getVals();
        } else {
            s_myPowerSupplyNetworkFragment.noAccount();
        }
    }

    void getVals() {
        int userId = ((MainActivity) context).getTbl_user().getUserId(context);
        Single<List<VM_PowerSupplyNetwork>> data = api_powerSupply.getMyPowerSupplyNetwork(userId
                , tbl_jobs.getJobs(), tbl_states.getProvincesOrCities(0), tbl_workExperiences.getWorkExperiences());

        dispose_getVals = data.subscribeWith(new DisposableSingleObserver<List<VM_PowerSupplyNetwork>>() {
            @Override
            public void onSuccess(List<VM_PowerSupplyNetwork> vm_powerSupplyNetworks) {
                s_myPowerSupplyNetworkFragment.onHideAll();
                s_myPowerSupplyNetworkFragment.onLoading(false);
                s_myPowerSupplyNetworkFragment.onSuccess();

                if (vm_powerSupplyNetworks.size() > 0) {
                    setVals(vm_powerSupplyNetworks);
                } else {
                    s_myPowerSupplyNetworkFragment.onEmpty();
                    s_myPowerSupplyNetworkFragment.onFinish();
                }
            }

            @Override
            public void onError(Throwable e) {
                s_myPowerSupplyNetworkFragment.onHideAll();
                s_myPowerSupplyNetworkFragment.onLoading(false);
                s_myPowerSupplyNetworkFragment.onError(Error.GetErrorVolley(e.toString()));
            }
        });
    }

    void setVals(List<VM_PowerSupplyNetwork> powerSupplyNetworks) {
        Observable<VM_PowerSupplyNetwork> data = Observable.fromIterable(powerSupplyNetworks);

        dispose_setVals = data.subscribe(powerSupplyNetwork -> {
            s_myPowerSupplyNetworkFragment.onItem(powerSupplyNetwork);
        }, throwable -> {

        }, () -> {
            s_myPowerSupplyNetworkFragment.onFinish();
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
