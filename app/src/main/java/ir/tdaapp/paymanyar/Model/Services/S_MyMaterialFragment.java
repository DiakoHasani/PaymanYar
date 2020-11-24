package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Material;

/**
 * مربوط به صفحه مصالح من
 **/
public interface S_MyMaterialFragment {
    void onLoading(boolean load);
    void OnStart();
    void onSuccess();
    void onError(ResaultCode result);
    void onHideAll();
    void onItem(VM_Material machinery);
    void onFinish();
    void noAccount();
    void onEmpty();
    void onLoadingDelete(boolean load);
}
