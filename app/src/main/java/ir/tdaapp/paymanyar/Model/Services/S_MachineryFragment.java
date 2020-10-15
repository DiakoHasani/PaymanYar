package ir.tdaapp.paymanyar.Model.Services;

import android.widget.ArrayAdapter;

import java.util.List;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FilterMachinery;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Machinery;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_MachinerySpinner;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_ProvincesAndCities;

public interface S_MachineryFragment {
    void OnStart();
    void onLoading(boolean load);
    void onHideAll();
    void onEmpty();
    void onError(ResaultCode result);
    void onSuccess();
    void onFinish();
    void getProvinces(ArrayAdapter<VM_ProvincesAndCities> provinces);
    void getCities(ArrayAdapter<VM_ProvincesAndCities> cities);
    void getMachineries(ArrayAdapter<VM_MachinerySpinner> machinerySpinners);
    void onItem(VM_Machinery machinery);
    VM_FilterMachinery getFilter();
    void onLoadingPaging(boolean load);
    void onErrorPaging(ResaultCode result);
    boolean isWorkedInternet();
    int getPage();
    boolean isLoadingProgress();
}
