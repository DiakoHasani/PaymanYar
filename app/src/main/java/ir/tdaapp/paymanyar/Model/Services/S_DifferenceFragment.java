package ir.tdaapp.paymanyar.Model.Services;

import java.io.File;
import java.util.List;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Enums.StepsAnalizeTender;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_AnaliseInfo;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FileUploadAnalizeTender;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_InputAnalizeTender;

//مربوط به صفحه مابه التفاوت
public interface S_DifferenceFragment {
    void OnStart();
    void onValuesFiles(List<VM_FileUploadAnalizeTender> vals);
    void onSelectedFile(VM_FileUploadAnalizeTender val, File file);
    void onValidFile(VM_FileUploadAnalizeTender val,File file);
    void onNotValidFile(String errorText);
    List<String> onGetUrlPaths();
    void onLoading(boolean isLoad);
    void onNoAccount();
    VM_InputAnalizeTender onGetInputAnalize(List<String> fileUrls);
    boolean isValidInputs();
    void onNotValid();
    void onError(ResaultCode resaultCode);
    void onSuccess();
    void onSetDetailsData();
    int onItemId();
    void onLoadingGetItem(boolean load);
    void onErrorGetItem(ResaultCode result);
    void onShowReloadDialog(boolean show);
    void onSetAnaliseInfo(VM_AnaliseInfo analiseInfo);
    void startTimer(String time);
    void onAnimation_Step_pay(StepsAnalizeTender step, boolean enablesAnim);
    String onGetFileName();
    void onLoadingDownloadFile(boolean load);
    void onErrorDownloadFile(Throwable e);
    void onShowFile(String fileName);
}
