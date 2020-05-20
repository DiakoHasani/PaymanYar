package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;

public interface S_LoginCodeFragment {
    void onError(ResaultCode result);
    void onLoading(boolean load);
    void onFinish(VM_Message message);
    void onNotValid();
    void onStartTimer();
    void onSecondTimer(String second);
    void onFinishTimer();
    void onLoadingResend(boolean load);
    void onFinishResend(VM_Message message);
}
