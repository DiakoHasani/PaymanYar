package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotifications;

public interface S_FavoriteTenderNotificationsFragment {
    void OnStart();
    void onSuccess();
    void onHideAll();
    void onFinish();
    void onLoading(boolean load);
    void onError(ResaultCode result);
    void onItem(VM_TenderNotifications item);
    void onEmpty();
}
