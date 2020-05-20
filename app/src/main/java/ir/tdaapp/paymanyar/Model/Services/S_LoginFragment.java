package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;

public interface S_LoginFragment {

    void onError(ResaultCode result);
    void onLoading(boolean load);
    void onFinish(VM_Message message);
    void onNotValid();

}
