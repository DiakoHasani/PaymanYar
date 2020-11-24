package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_HomeSlider;

public interface S_HomeFragment {

    void OnStart();

    void onError(ResaultCode resault);

    void onHideAll();

    void onLoading(boolean isLoad);

    void onItemSlider(VM_HomeSlider slider);

    void onFinish();

    void onShowSlider(boolean show);

    void onUpdateApp(boolean hadUpdate);

    int onGetCountSlider();

    int onGetItem(int i);

    int onGetCurrentSlider();

    void onSetCurrentSlider(int i, boolean b);

    void onShowMenuLoginNavigation();

    float getApplicationVersion();
}
