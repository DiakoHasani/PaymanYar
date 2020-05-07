package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Charge;

public interface S_ChargeFragment {
    void OnStart();
    void onLoading(boolean load);
    void onHideAll();
    void onFinish();
    void onShowRecycler(boolean show);
    void onError(ResaultCode result);
    void onItemCharge(VM_Charge charge);
    void onSuccessInventory(int inventory);
    void onErrorInventory();
    void onLoadingInventory(boolean load);
}
