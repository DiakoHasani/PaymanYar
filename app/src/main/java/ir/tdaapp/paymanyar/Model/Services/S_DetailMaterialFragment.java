package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailMaterial;

/**
 * مربوط به جزئیات مصالح
 * **/
public interface S_DetailMaterialFragment {
    void OnStart();
    void onLoading(boolean load);
    void onError(ResaultCode result);
    void onSuccess();
    void onFinish();
    void onItem(VM_DetailMaterial detailMaterial);
    int onItemId();
    int getCurrentSlider();
}
