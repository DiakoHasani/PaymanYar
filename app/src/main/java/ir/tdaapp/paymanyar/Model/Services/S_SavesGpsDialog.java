package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.paymanyar.Model.ViewModels.VM_SavesGps;

public interface S_SavesGpsDialog {
    void OnStart();
    void onError();
    void onHideAll();
    void onFinish();
    void onAddGpsItem(VM_SavesGps gps);
    void onShowRecycler();
    void onShowDontHaveItem();
}
