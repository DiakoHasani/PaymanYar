package ir.tdaapp.paymanyar.Model.Services;

import android.widget.ArrayAdapter;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_AdType;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Job;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_ProvincesAndCities;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_WorkExperience;

/**
 * مربوط به افزودن نیروکار
 * **/
public interface S_AddPowerSupply {
    void onLoading(boolean load);
    void onError(ResaultCode result);
    void onSuccess();
    void getProvinces(ArrayAdapter<VM_ProvincesAndCities> provinces);
    void getCities(ArrayAdapter<VM_ProvincesAndCities> cities);
    void getJobs(ArrayAdapter<VM_Job> jobs);
    void getWorkExperiences(ArrayAdapter<VM_WorkExperience> workExperiences);
    void getAdTypes(ArrayAdapter<VM_AdType> adapter);
}
