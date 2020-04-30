package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailsTender;

public interface S_DetailsTenderFragment {
    void OnStart();
    void onLoading(boolean load);
    void onFinish();
    void onError(ResaultCode result);
    void onHideAll();
    void onGetDetail(VM_DetailsTender detailsTender);
    void onShowSubscribers();
    void onGetNextTender(String tenderId);
    void onGetPrevTender(String tenderId);
}
