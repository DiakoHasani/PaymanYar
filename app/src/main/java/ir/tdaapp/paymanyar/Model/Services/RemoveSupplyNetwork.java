package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;

/**
 * مربوط به حذف آیتم در بخش شبکه تامین
 * **/
public interface RemoveSupplyNetwork {
    void onSuccess(VM_Message message);
    void onError(ResaultCode result);
}
