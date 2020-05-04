package ir.tdaapp.paymanyar.Model.Services;

import android.widget.ArrayAdapter;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_City;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Estimate;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_IncludesTheWord;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Major;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotifications;

public interface S_TenderNotificationFragment {

    void OnStart();
    void onSuccess();
    void onHideAll();
    void onFinish();
    void onLoading(boolean load);
    void onLoadingPaging(boolean load);
    void onError(ResaultCode result);
    void onShowRecycler();
    void onItemTender(VM_TenderNotifications notification);
    void onItemCitySpinner(ArrayAdapter<VM_City> adapter);
    void onItemMajorSpinner(ArrayAdapter<VM_Major> adapter);
    void onItemFromEstimateSpinner(ArrayAdapter<VM_Estimate> adapter);
    void onItemUntilEstimateSpinner(ArrayAdapter<VM_Estimate> adapter);
    void onCountTenders(int count);
    void onErrorLetMeKnow(ResaultCode result);
    void onSuccessLetMeKnow(VM_Message message);
}
