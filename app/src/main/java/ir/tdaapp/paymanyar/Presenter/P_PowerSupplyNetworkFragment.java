package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_ProvincesAndCities;
import ir.tdaapp.paymanyar.Model.Services.S_PowerSupplyNetworkFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_ProvincesAndCities;
import ir.tdaapp.paymanyar.R;

/**
 * مربوط به صفحه شبکه تامین نیروکار
 **/
public class P_PowerSupplyNetworkFragment {

    Context context;
    S_PowerSupplyNetworkFragment s_powerSupplyNetworkFragment;
    Tbl_ProvincesAndCities tbl_provincesAndCities;

    public P_PowerSupplyNetworkFragment(Context context, S_PowerSupplyNetworkFragment s_powerSupplyNetworkFragment) {
        this.context = context;
        this.s_powerSupplyNetworkFragment = s_powerSupplyNetworkFragment;
        tbl_provincesAndCities = new Tbl_ProvincesAndCities(context);
    }

    public void start() {
        s_powerSupplyNetworkFragment.OnStart();
        s_powerSupplyNetworkFragment.onHideAll();
        getVals();
    }

    /**
     * در اینجا مقادیر از سرور گرفته می شود
     **/
    void getVals() {
        s_powerSupplyNetworkFragment.onLoading(true);
    }

    /**
     * در اینجا لیست استانها گرفته می شود
     **/
    public void getProvinces() {
        ArrayAdapter<VM_ProvincesAndCities> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, tbl_provincesAndCities.getProvincesOrCities(0));
        s_powerSupplyNetworkFragment.getProvinces(adapter);
    }

    /**
     * در اینجا لیست شهرها گرفته می شود
     **/
    public void getCities(int parentId) {
        if (parentId != 0) {
            ArrayAdapter<VM_ProvincesAndCities> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, tbl_provincesAndCities.getProvincesOrCities(parentId));
            s_powerSupplyNetworkFragment.getCities(adapter);
        } else {
            List<VM_ProvincesAndCities> cities = new ArrayList<>();
            cities.add(new VM_ProvincesAndCities(0, context.getString(R.string.City), 0));
            ArrayAdapter<VM_ProvincesAndCities> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, cities);
            s_powerSupplyNetworkFragment.getCities(adapter);
        }
    }
}
