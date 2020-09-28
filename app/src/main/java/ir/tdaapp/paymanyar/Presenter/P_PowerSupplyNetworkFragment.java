package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Jobs;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_States;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_WorkExperiences;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_PowerSupply;
import ir.tdaapp.paymanyar.Model.Services.S_PowerSupplyNetworkFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Job;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PowerSupplyNetwork;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_ProvincesAndCities;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_WorkExperience;
import ir.tdaapp.paymanyar.R;

/**
 * مربوط به صفحه شبکه تامین نیروکار
 **/
public class P_PowerSupplyNetworkFragment {

    Context context;
    S_PowerSupplyNetworkFragment s_powerSupplyNetworkFragment;
    Tbl_States tbl_states;
    Tbl_WorkExperiences tbl_workExperiences;
    Tbl_Jobs tbl_jobs;
    Api_PowerSupply api_powerSupply;
    Disposable dispose_getVals, dispose_setVals;

    public P_PowerSupplyNetworkFragment(Context context, S_PowerSupplyNetworkFragment s_powerSupplyNetworkFragment) {
        this.context = context;
        this.s_powerSupplyNetworkFragment = s_powerSupplyNetworkFragment;
        tbl_states = new Tbl_States(context);
        tbl_jobs = new Tbl_Jobs(context);
        tbl_workExperiences = new Tbl_WorkExperiences(context);
        api_powerSupply = new Api_PowerSupply();
    }

    public void start() {

        s_powerSupplyNetworkFragment.OnStart();

        if (s_powerSupplyNetworkFragment.getPage() == 0) {
            s_powerSupplyNetworkFragment.onHideAll();
            s_powerSupplyNetworkFragment.onLoading(true);
        } else {
            s_powerSupplyNetworkFragment.onLoadingPaging(true);
        }
        getVals();
    }

    /**
     * در اینجا مقادیر از سرور گرفته می شود
     **/
    void getVals() {
        Single<List<VM_PowerSupplyNetwork>> data = api_powerSupply.getPowerSupply(s_powerSupplyNetworkFragment.getFilter()
                , tbl_jobs.getJobs(), tbl_states.getProvincesOrCities(0), tbl_workExperiences.getWorkExperiences());

        dispose_getVals = data.subscribeWith(new DisposableSingleObserver<List<VM_PowerSupplyNetwork>>() {
            @Override
            public void onSuccess(List<VM_PowerSupplyNetwork> vm_powerSupplyNetworks) {
                if (vm_powerSupplyNetworks.size() != 0) {

                    if (s_powerSupplyNetworkFragment.getPage() == 0) {
                        s_powerSupplyNetworkFragment.onLoading(false);
                        s_powerSupplyNetworkFragment.onHideAll();
                        s_powerSupplyNetworkFragment.onSuccess();
                    } else {
                        s_powerSupplyNetworkFragment.onLoadingPaging(false);
                    }

                    setVals(vm_powerSupplyNetworks);
                } else {
                    if (s_powerSupplyNetworkFragment.getPage() == 0) {
                        s_powerSupplyNetworkFragment.onHideAll();
                        s_powerSupplyNetworkFragment.onEmpty();
                    } else {
                        s_powerSupplyNetworkFragment.onLoadingPaging(false);
                    }

                    s_powerSupplyNetworkFragment.onFinish();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (s_powerSupplyNetworkFragment.getPage() == 0) {
                    s_powerSupplyNetworkFragment.onLoading(false);
                    s_powerSupplyNetworkFragment.onHideAll();
                    s_powerSupplyNetworkFragment.onError(Error.GetErrorVolley(e.toString()));
                } else {
                    s_powerSupplyNetworkFragment.onLoadingPaging(false);
                    s_powerSupplyNetworkFragment.onErrorPaging(Error.GetErrorVolley(e.toString()));
                }
            }
        });
    }

    /**
     * در اینجا آیتم ها در رسایکلر ست می شوند
     **/
    void setVals(List<VM_PowerSupplyNetwork> powerSupplyNetworks) {
        Observable<VM_PowerSupplyNetwork> data = Observable.fromIterable(powerSupplyNetworks);

        dispose_setVals = data.subscribe(powerSupplyNetwork -> {
            s_powerSupplyNetworkFragment.onItem(powerSupplyNetwork);
        }, throwable -> {

        }, () -> {
            s_powerSupplyNetworkFragment.onFinish();
        });
    }

    /**
     * در اینجا لیست استانها گرفته می شود
     **/
    public void getProvinces() {
        ArrayAdapter<VM_ProvincesAndCities> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, tbl_states.getProvincesOrCities(0));
        s_powerSupplyNetworkFragment.getProvinces(adapter);
    }

    /**
     * در اینجا لیست شهرها گرفته می شود
     **/
    public void getCities(int parentId) {
        if (parentId != 0) {
            ArrayAdapter<VM_ProvincesAndCities> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, tbl_states.getProvincesOrCities(parentId));
            s_powerSupplyNetworkFragment.getCities(adapter);
        } else {
            List<VM_ProvincesAndCities> cities = new ArrayList<>();
            cities.add(new VM_ProvincesAndCities(0, context.getString(R.string.City), 0));
            ArrayAdapter<VM_ProvincesAndCities> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, cities);
            s_powerSupplyNetworkFragment.getCities(adapter);
        }
    }

    public void getJobs() {
        ArrayAdapter<VM_Job> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, tbl_jobs.getJobs());
        s_powerSupplyNetworkFragment.getJobs(adapter);
    }

    /**
     * در اینجا لیست سابقه کارها گرفته می شود
     **/
    public void getWorkExperiences() {
        ArrayAdapter<VM_WorkExperience> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, tbl_workExperiences.getWorkExperiences());
        s_powerSupplyNetworkFragment.getWorkExperiences(adapter);
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
