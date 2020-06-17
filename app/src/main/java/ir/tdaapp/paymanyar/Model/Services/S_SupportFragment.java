package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Support;

public interface S_SupportFragment {
    void OnStart();
    void onHideAll();
    void onSuccess();
    void onFinish();
    void onLoading(boolean load);
    void onError(ResaultCode result);
    void onNotValid();
    void onUser(VM_Support support);
    void onCreateAccount();
    void onErrorSend(ResaultCode result);
    void onLoadingSend(boolean load);
    void onFinishSend(VM_Message message);
}
