package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailSMS;

public interface S_Detail_SMS_Dialog {
    void onError(ResaultCode result);
    void OnStart();
    void onHideAll();
    void onFinish(VM_DetailSMS sms);
    void onLoading(boolean load);
}
