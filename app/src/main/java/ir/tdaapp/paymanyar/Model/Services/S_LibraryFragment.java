package ir.tdaapp.paymanyar.Model.Services;

import java.util.List;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_HomeSlider;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Library;

public interface S_LibraryFragment {
    void OnStart();
    void onError(ResaultCode resalt);
    void onHideAll();
    void onFinish();
    void onLoading(boolean load);
    void onSuccess();
    void onLibraryItem(VM_Library library);
    void onLoadingPaging(boolean load);
    void onEmptyItem();
    void onShowPDF(String pdfName);
    void onLoadingDownloadPDF(boolean load);
    void onErrorDownloadPDF();
    void onItemSlider(VM_HomeSlider slider);
    int onGetCurrentSlider();
    void onSetCurrentSlider(int i, boolean b);
    int onGetItem(int i);
    int onGetCountSlider();
    void noItemSlider(boolean show);
    void onLoadingSlider(boolean show);
    void onShowSlider(boolean show);
    void onErrorSlider(ResaultCode result);
    void onShowReloadSlider(boolean show);
    void onFinishSlider();
    void onStartSlider();
}
