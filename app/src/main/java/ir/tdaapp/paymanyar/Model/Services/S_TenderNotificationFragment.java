package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotification;

public interface S_TenderNotificationFragment {

    void OnStart();
    void onSuccess();
    void onHideAll();
    void onFinish();
    void onLoading(boolean load);
    void onLoadingPaging(boolean load);
    void onError(ResaultCode result);
    void onShowRecycler();
    void onItemTender(VM_TenderNotification notification);
}
