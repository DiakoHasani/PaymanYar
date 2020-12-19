package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Charge;

/**
 * مربوط به صفحه تبلیغات
 **/
public interface S_AdvertisingFragment {
    void OnStart();
    void onLoading(boolean load);
    void onHideAll();
    void onFinish();
    void onShowRecycler(boolean show);
    void onError(ResaultCode result);
    void onItem(VM_Charge item);
}
