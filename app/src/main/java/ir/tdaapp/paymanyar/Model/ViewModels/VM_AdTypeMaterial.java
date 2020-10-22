package ir.tdaapp.paymanyar.Model.ViewModels;

import androidx.annotation.NonNull;
import ir.tdaapp.paymanyar.Model.Enums.AdTypeMaterial;

/**
 * مربوط به نوع آگهی مصالح
 * **/
public class VM_AdTypeMaterial {
    AdTypeMaterial adType;
    String title;

    public VM_AdTypeMaterial() {
    }

    public VM_AdTypeMaterial(AdTypeMaterial adType, String title) {
        this.adType = adType;
        this.title = title;
    }

    public AdTypeMaterial getAdType() {
        return adType;
    }

    public void setAdType(AdTypeMaterial adType) {
        this.adType = adType;
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
