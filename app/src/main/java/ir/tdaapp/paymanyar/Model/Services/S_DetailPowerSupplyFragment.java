package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailPowerSupply;

public interface S_DetailPowerSupplyFragment {
    void OnStart();
    void onLoading(boolean load);
    void onError(ResaultCode result);
    void onSuccess();
    void onFinish();
    void onItem(VM_DetailPowerSupply detailPowerSupply);
    int onItemId();
    int getCurrentSlider();
}
