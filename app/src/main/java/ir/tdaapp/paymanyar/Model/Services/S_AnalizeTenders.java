package ir.tdaapp.paymanyar.Model.Services;

import java.io.File;
import java.util.List;

import ir.tdaapp.paymanyar.Model.ViewModels.VM_FileUploadAnalizeTender;

public interface S_AnalizeTenders {
    void OnStart();
    void onValuesFiles(List<VM_FileUploadAnalizeTender> vals);
    void onSelectedFile(VM_FileUploadAnalizeTender val,File file);
    void onValidFile(VM_FileUploadAnalizeTender val,File file);
    void onNotValidFile(String errorText);
}
