package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.paymanyar.Model.ViewModels.VM_FileUploadAnalizeTender;

//مربوط کلیک فایل صفحه آنالیز مناقصات
public interface onClickFileUpload_AnalizeTender {
    void onClickFile(VM_FileUploadAnalizeTender file);
    void onClickClose(VM_FileUploadAnalizeTender file);
}
