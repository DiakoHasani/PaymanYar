package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_SMS;

public interface S_SmsFragment {
    void OnStart();
    void onError(ResaultCode resault);
    void onFinish();
    void onHideAll();
    void onSuccess();
    void onLoading(boolean load);
    void onItemSMS(VM_SMS sms);
    void onEmpty();
    void onArchiveMessage(VM_Message message,String messageId);
    void onErrorArchiveMessage(ResaultCode result);
    void onLoadingArchive(boolean archive);
    boolean onShowAllSMS();
}
