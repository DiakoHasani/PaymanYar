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
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Material;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_States;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_PowerSupply;
import ir.tdaapp.paymanyar.Model.Services.S_MaterialFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Material;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_MaterialSpinner;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_ProvincesAndCities;
import ir.tdaapp.paymanyar.R;

/**
 * مربوط به صفحه مصالح
 **/
public class P_MaterialFragment {
    Context context;
    S_MaterialFragment s_materialFragment;
    Tbl_Material tbl_material;
    Tbl_States tbl_states;
    Api_PowerSupply api_powerSupply;
    Disposable dispose_getVals, dispose_setVals, dispose_getMaterials;

    public P_MaterialFragment(Context context, S_MaterialFragment s_materialFragment) {
        this.context = context;
        this.s_materialFragment = s_materialFragment;
        tbl_material = new Tbl_Material(context);
        api_powerSupply = new Api_PowerSupply();
        tbl_states = new Tbl_States(context);
    }

    public void start() {
        s_materialFragment.OnStart();

        if (s_materialFragment.getPage() == 0) {
            s_materialFragment.onHideAll();
            s_materialFragment.onLoading(true);
        } else {
            s_materialFragment.onLoadingPaging(true);
        }

        //در اینجا لیست مصالح اسپینر از سرور گرفته می شود
        if (!s_materialFragment.checkedMaterialSpinner()) {
            getMaterials();
        }

        getVals();
    }

    void getVals() {
        Single<List<VM_Material>> data = api_powerSupply.getMaterials(s_materialFragment.getFilter(), tbl_states.getProvincesOrCities(0),context);
        dispose_getVals = data.subscribeWith(new DisposableSingleObserver<List<VM_Material>>() {
            @Override
            public void onSuccess(List<VM_Material> vm_materials) {
                if (vm_materials.size() != 0) {

                    if (s_materialFragment.getPage() == 0) {
                        s_materialFragment.onLoading(false);
                        s_materialFragment.onHideAll();
                        s_materialFragment.onSuccess();
                    } else {
                        s_materialFragment.onLoadingPaging(false);
                    }

                    setVals(vm_materials);
                } else {
                    if (s_materialFragment.getPage() == 0) {
                        s_materialFragment.onHideAll();
                        s_materialFragment.onEmpty();
                        s_materialFragment.onFinish();
                    } else {
                        new Handler().postDelayed(() -> {
                            s_materialFragment.onLoadingPaging(false);
                            s_materialFragment.onFinish();
                        }, 1500);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                if (s_materialFragment.getPage() == 0) {
                    s_materialFragment.onLoading(false);
                    s_materialFragment.onHideAll();
                    s_materialFragment.onError(Error.GetErrorVolley(e.toString()));
                } else {
                    s_materialFragment.onLoadingPaging(false);
                    s_materialFragment.onErrorPaging(Error.GetErrorVolley(e.toString()));
                }
            }
        });
    }

    void setVals(List<VM_Material> materials) {
        Observable<VM_Material> data = Observable.fromIterable(materials);
        dispose_setVals = data.subscribe(material -> {
            s_materialFragment.onItem(material);
        }, throwable -> {

        }, () -> {
            s_materialFragment.onFinish();
        });
    }

    /**
     * در اینجا لیست استانها گرفته می شود
     **/
    public void getProvinces() {
        ArrayAdapter<VM_ProvincesAndCities> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, tbl_states.getProvincesOrCities(0));
        s_materialFragment.getProvinces(adapter);
    }

    /**
     * در اینجا لیست شهرها گرفته می شود
     **/
    public void getCities(int parentId) {
        if (parentId != 0) {
            ArrayAdapter<VM_ProvincesAndCities> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, tbl_states.getProvincesOrCities(parentId));
            s_materialFragment.getCities(adapter);
        } else {
            List<VM_ProvincesAndCities> cities = new ArrayList<>();
            cities.add(new VM_ProvincesAndCities(0, context.getString(R.string.City), 0));
            ArrayAdapter<VM_ProvincesAndCities> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, cities);
            s_materialFragment.getCities(adapter);
        }
    }

    /**
     * در اینجا لیست مصالح گرفته می شود
     **/
    public void getMaterials() {

        Single<List<VM_MaterialSpinner>> data = api_powerSupply.getMaterialSpinner(context);

        dispose_getMaterials=data.subscribeWith(new DisposableSingleObserver<List<VM_MaterialSpinner>>() {
            @Override
            public void onSuccess(List<VM_MaterialSpinner> vm_materialSpinners) {
                ArrayAdapter<VM_MaterialSpinner> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, vm_materialSpinners);
                s_materialFragment.getMaterials(adapter);
            }

            @Override
            public void onError(Throwable e) {

            }
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

        if (dispose_getMaterials != null) {
            dispose_getMaterials.dispose();
        }
    }

}
