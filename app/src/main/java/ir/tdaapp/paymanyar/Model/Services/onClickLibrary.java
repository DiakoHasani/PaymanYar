package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.paymanyar.Model.ViewModels.VM_Library;

public interface onClickLibrary {
    void clickDownload(VM_Library library);
    void clickItem(int id);
    void clickShare(String url,String title);
}
