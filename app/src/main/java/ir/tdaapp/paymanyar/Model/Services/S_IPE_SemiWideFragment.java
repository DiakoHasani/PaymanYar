package ir.tdaapp.paymanyar.Model.Services;

import java.util.ArrayList;

import ir.tdaapp.paymanyar.Model.ViewModels.VM_EshtalItem;

public interface S_IPE_SemiWideFragment {
    void OnStart();
    void SetItems(ArrayList<VM_EshtalItem> list);
}
