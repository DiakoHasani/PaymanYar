package ir.tdaapp.paymanyar.Model.Services;


import android.widget.ArrayAdapter;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PowerSupplyNetwork;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_ProvincesAndCities;

/**
 * مربوط به صفحه شبکه تامین نیروکار
 * **/
public interface S_PowerSupplyNetworkFragment {
    void OnStart();
    void onLoading(boolean load);
    void onHideAll();
    void onEmpty();
    void onError(ResaultCode result);
    void onSuccess();
    void getProvinces(ArrayAdapter<VM_ProvincesAndCities> provinces);
    void getCities(ArrayAdapter<VM_ProvincesAndCities> cities);
    void onItem(VM_PowerSupplyNetwork powerSupplyNetwork);
}