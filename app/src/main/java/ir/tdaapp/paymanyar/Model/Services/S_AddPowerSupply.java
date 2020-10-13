package ir.tdaapp.paymanyar.Model.Services;

import android.widget.ArrayAdapter;

import java.io.File;
import java.util.List;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Enums.StepsAddPower;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_AdType;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FileUploadAnalizeTender;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Job;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PostPowerSupply;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_ProvincesAndCities;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_WorkExperience;

/**
 * مربوط به افزودن نیروکار
 **/
public interface S_AddPowerSupply {
    void onLoading(boolean load);

    void onError(ResaultCode result);

    void onSuccess();

    void getProvinces(ArrayAdapter<VM_ProvincesAndCities> provinces);

    void getCities(ArrayAdapter<VM_ProvincesAndCities> cities);

    void getJobs(ArrayAdapter<VM_Job> jobs);

    void getWorkExperiences(ArrayAdapter<VM_WorkExperience> workExperiences);

    void getAdTypes(ArrayAdapter<VM_AdType> adapter);

    void defaultValueFileUpload(List<VM_FileUploadAnalizeTender> values);

    void onSelectedFile(VM_FileUploadAnalizeTender val, File file);

    void onValidFile(VM_FileUploadAnalizeTender val, File file);

    void onNotValidFile(String errorText);

    boolean checkValidation();

    void isValid();

    void notValid();

    VM_PostPowerSupply getInputUser();

    List<String> getUrlFiles();

    void noAccount();

    void onAnimationStep(StepsAddPower stepsAddPower, boolean isAnimation);

    void disableAnimationAllSteps();

    int getIdItem();

    void enableUpgradeOrder(boolean enable);

    void enableShowSteps(boolean enable);

    void onLoadingGetDetail(boolean load);

    void onErrorGetDetail(ResaultCode result);

    void onDetailData(VM_PostPowerSupply model);
}
