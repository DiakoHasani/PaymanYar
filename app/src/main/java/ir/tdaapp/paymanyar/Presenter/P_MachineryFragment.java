package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;
import android.os.Handler;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Machineries;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_States;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_PowerSupply;
import ir.tdaapp.paymanyar.Model.Services.S_MachineryFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Machinery;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_MachinerySpinner;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_ProvincesAndCities;
import ir.tdaapp.paymanyar.R;

/**
 * مربوط به صفحه ماشین آلات
 **/
public class P_MachineryFragment {
    Context context;
    S_MachineryFragment s_machineryFragment;
    Tbl_States tbl_states;
    Tbl_Machineries tbl_machineries;
    Api_PowerSupply api_powerSupply;
    Disposable dispose_getVals, dispose_setVals, dispose_getMachineries;

    public P_MachineryFragment(Context context, S_MachineryFragment s_machineryFragment) {
        this.context = context;
        this.s_machineryFragment = s_machineryFragment;
        tbl_states = new Tbl_States(context);
        api_powerSupply = new Api_PowerSupply();
        tbl_machineries = new Tbl_Machineries(context);
    }

    public void start() {
        s_machineryFragment.OnStart();

        if (s_machineryFragment.getPage() == 0) {
            s_machineryFragment.onHideAll();
            s_machineryFragment.onLoading(true);
        } else {
            s_machineryFragment.onLoadingPaging(true);
        }

        //در اینجا دسته ماشین آلات از سرور گرفته می شود و در اسپینر ست می شود
        if (!s_machineryFragment.checkedMachinerySpinner()) {
            getMachineries();
        }
        getVals();
    }

    /**
     * در اینجا لیست استانها گرفته می شود
     **/
    public void getProvinces() {
        ArrayAdapter<VM_ProvincesAndCities> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, tbl_states.getProvincesOrCities(0));
        s_machineryFragment.getProvinces(adapter);
    }

    /**
     * در اینجا لیست شهرها گرفته می شود
     **/
    public void getCities(int parentId) {
        if (parentId != 0) {
            ArrayAdapter<VM_ProvincesAndCities> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, tbl_states.getProvincesOrCities(parentId));
            s_machineryFragment.getCities(adapter);
        } else {
            List<VM_ProvincesAndCities> cities = new ArrayList<>();
            cities.add(new VM_ProvincesAndCities(0, context.getString(R.string.City), 0));
            ArrayAdapter<VM_ProvincesAndCities> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, cities);
            s_machineryFragment.getCities(adapter);
        }
    }

    /**
     * در اینجا لیست ماشین آلات گرفته می شود
     **/
    public void getMachineries() {

        Single<List<VM_MachinerySpinner>> data = api_powerSupply.getMachinerySpinner(context);
        dispose_getMachineries = data.subscribeWith(new DisposableSingleObserver<List<VM_MachinerySpinner>>() {
            @Override
            public void onSuccess(List<VM_MachinerySpinner> machinerySpinners) {
                ArrayAdapter<VM_MachinerySpinner> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, machinerySpinners);
                s_machineryFragment.getMachineries(adapter);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    void getVals() {
        Single<List<VM_Machinery>> data = api_powerSupply.getMachineries(s_machineryFragment.getFilter(),
                tbl_states.getProvincesOrCities(0));

        dispose_getVals = data.subscribeWith(new DisposableSingleObserver<List<VM_Machinery>>() {
            @Override
            public void onSuccess(List<VM_Machinery> vm_machineries) {
                if (vm_machineries.size() != 0) {

                    if (s_machineryFragment.getPage() == 0) {
                        s_machineryFragment.onLoading(false);
                        s_machineryFragment.onHideAll();
                        s_machineryFragment.onSuccess();
                    } else {
                        s_machineryFragment.onLoadingPaging(false);
                    }

                    setVals(vm_machineries);
                } else {
                    if (s_machineryFragment.getPage() == 0) {
                        s_machineryFragment.onHideAll();
                        s_machineryFragment.onEmpty();
                        s_machineryFragment.onFinish();
                    } else {
                        new Handler().postDelayed(() -> {
                            s_machineryFragment.onLoadingPaging(false);
                            s_machineryFragment.onFinish();
                        }, 1500);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                if (s_machineryFragment.getPage() == 0) {
                    s_machineryFragment.onLoading(false);
                    s_machineryFragment.onHideAll();
                    s_machineryFragment.onError(Error.GetErrorVolley(e.toString()));
                } else {
                    s_machineryFragment.onLoadingPaging(false);
                    s_machineryFragment.onErrorPaging(Error.GetErrorVolley(e.toString()));
                }
            }
        });
    }

    void setVals(List<VM_Machinery> vals) {
        Observable<VM_Machinery> data = Observable.fromIterable(vals);
        dispose_setVals = data.subscribe(powerSupplyNetwork -> {
            s_machineryFragment.onItem(powerSupplyNetwork);
        }, throwable -> {

        }, () -> {
            s_machineryFragment.onFinish();
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

        if (dispose_getMachineries != null) {
            dispose_getMachineries.dispose();
        }
    }
}
