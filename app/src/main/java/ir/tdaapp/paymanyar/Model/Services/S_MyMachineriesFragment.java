package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Machinery;

public interface S_MyMachineriesFragment {
    void onLoading(boolean load);
    void OnStart();
    void onSuccess();
    void onError(ResaultCode result);
    void onHideAll();
    void onItem(VM_Machinery machinery);
    void onFinish();
    void noAccount();
    void onEmpty();
    void onLoadingDelete(boolean load);
}
