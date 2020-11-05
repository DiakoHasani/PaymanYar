package ir.tdaapp.paymanyar.Model.Services;


import android.widget.ArrayAdapter;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FilterPowerSupplyNetwork;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Job;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PowerSupplyNetwork;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_ProvincesAndCities;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_WorkExperience;

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
    void onFinish();
    void getProvinces(ArrayAdapter<VM_ProvincesAndCities> provinces);
    void getCities(ArrayAdapter<VM_ProvincesAndCities> cities);
    void getJobs(ArrayAdapter<VM_Job> jobs);
    void getWorkExperiences(ArrayAdapter<VM_WorkExperience> workExperiences);
    void onItem(VM_PowerSupplyNetwork powerSupplyNetwork);
    VM_FilterPowerSupplyNetwork getFilter();
    void onLoadingPaging(boolean load);
    void onErrorPaging(ResaultCode result);
    boolean isWorkedInternet();
    int getPage();
    boolean isLoadingProgress();
    boolean checkedJobSpinner();
}