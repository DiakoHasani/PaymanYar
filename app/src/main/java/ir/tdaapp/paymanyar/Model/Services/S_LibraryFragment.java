package ir.tdaapp.paymanyar.Model.Services;

import java.util.List;

import ir.tdaapp.li_volley.Enum.ResaultCode;
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
}
