package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotifications;

public interface S_FilterFramesFragment {
    void OnStart();
    void onError(ResaultCode resault);
    void onFinish();
    void onHideAll();
    void onSuccess();
    void onLoading(boolean load);
    void onEmpty();
    void onCountTenders(int count);
    void onItemTenders(VM_TenderNotifications item);
}
