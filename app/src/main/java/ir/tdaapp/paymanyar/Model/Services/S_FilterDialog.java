package ir.tdaapp.paymanyar.Model.Services;

import android.graphics.Bitmap;

import ir.tdaapp.paymanyar.Model.Adapters.FilterAdapter;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_EshtalItem;

public interface S_FilterDialog {
    void OnRowsFind(FilterAdapter adapter);
    void OnItemChoosed(VM_EshtalItem choosed);
}
