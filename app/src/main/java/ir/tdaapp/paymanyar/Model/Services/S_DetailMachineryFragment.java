package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailMachinery;

/**
 * مربوط به جزئیات ماشین آلات
 * **/
public interface S_DetailMachineryFragment {
    void OnStart();
    void onLoading(boolean load);
    void onError(ResaultCode result);
    void onSuccess();
    void onFinish();
    void onItem(VM_DetailMachinery detailMachinery);
    int onItemId();
    int getCurrentSlider();
}
