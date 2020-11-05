package ir.tdaapp.paymanyar.Model.Services;

import android.widget.ArrayAdapter;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FilterMaterial;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Material;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_MaterialSpinner;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_ProvincesAndCities;

/**
 * مربوط به صفحه مصالح
 * **/
public interface S_MaterialFragment {
    void OnStart();
    void onLoading(boolean load);
    void onHideAll();
    void onEmpty();
    void onError(ResaultCode result);
    void onSuccess();
    void onFinish();
    void getProvinces(ArrayAdapter<VM_ProvincesAndCities> provinces);
    void getCities(ArrayAdapter<VM_ProvincesAndCities> cities);
    void getMaterials(ArrayAdapter<VM_MaterialSpinner> adapter);
    void onItem(VM_Material material);
    VM_FilterMaterial getFilter();
    void onLoadingPaging(boolean load);
    void onErrorPaging(ResaultCode result);
    boolean isWorkedInternet();
    int getPage();
    boolean isLoadingProgress();
    boolean checkedMaterialSpinner();
}
