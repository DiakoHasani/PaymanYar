package ir.tdaapp.paymanyar.Model.Services;

import android.widget.ArrayAdapter;

import java.io.File;
import java.util.List;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Enums.StepsAddPower;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_AdTypeMaterial;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FileUploadAnalizeTender;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Material;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_MaterialSpinner;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PostMaterial;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_ProvincesAndCities;

public interface S_AddMaterialFragment {
    void onLoading(boolean load);

    void onError(ResaultCode result);

    void onSuccess();

    void getProvinces(ArrayAdapter<VM_ProvincesAndCities> provinces);

    void getCities(ArrayAdapter<VM_ProvincesAndCities> cities);

    void getMaterials(ArrayAdapter<String> materials);

    void getAdTypes(ArrayAdapter<VM_AdTypeMaterial> adTypes);

    void defaultValueFileUpload(List<VM_FileUploadAnalizeTender> values);

    void onSelectedFile(VM_FileUploadAnalizeTender val, File file);

    void onValidFile(VM_FileUploadAnalizeTender val, File file);

    void onNotValidFile(String errorText);

    boolean checkValidation();

    void isValid();

    void notValid();

    VM_PostMaterial getInputUser();

    List<String> getUrlFiles();

    void noAccount();

    void onAnimationStep(StepsAddPower stepsAddPower, boolean isAnimation);

    void disableAnimationAllSteps();

    int getIdItem();

    void enableUpgradeOrder(boolean enable);

    void enableShowSteps(boolean enable);

    void onLoadingGetDetail(boolean load);

    void onErrorGetDetail(ResaultCode result);

    void onDetailData(VM_PostMaterial model);
}
