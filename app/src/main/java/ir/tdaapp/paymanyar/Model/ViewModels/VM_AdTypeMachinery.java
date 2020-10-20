package ir.tdaapp.paymanyar.Model.ViewModels;

import androidx.annotation.NonNull;
import ir.tdaapp.paymanyar.Model.Enums.AdTypeCondition;

/**
 * مربوط به نوع آگهی ماشین آلات
 * **/
public class VM_AdTypeMachinery {
    AdTypeCondition adTypeCondition;
    String title;

    public VM_AdTypeMachinery() {
    }

    public VM_AdTypeMachinery(AdTypeCondition adTypeCondition, String title) {
        this.adTypeCondition = adTypeCondition;
        this.title = title;
    }

    public AdTypeCondition getAdTypeCondition() {
        return adTypeCondition;
    }

    public void setAdTypeCondition(AdTypeCondition adTypeCondition) {
        this.adTypeCondition = adTypeCondition;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NonNull
    @Override
    public String toString() {
        return title;
    }
}
