package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_AdUpgrade;

public interface S_AdUpgradeDialog {
    void OnStart();
    void onLoading(boolean load);
    void onSuccess();
    void onHideAll();
    void onError(ResaultCode resaultCode);
    void onFinish();
    void onItem(VM_AdUpgrade upgrade);
}
