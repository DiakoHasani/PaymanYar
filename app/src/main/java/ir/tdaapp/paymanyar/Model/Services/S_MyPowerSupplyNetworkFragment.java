package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PowerSupplyNetwork;

public interface S_MyPowerSupplyNetworkFragment {
    void onLoading(boolean load);
    void OnStart();
    void onSuccess();
    void onError(ResaultCode result);
    void onHideAll();
    void onItem(VM_PowerSupplyNetwork powerSupplyNetwork);
    void onFinish();
    void noAccount();
    void onEmpty();
}
