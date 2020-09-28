package ir.tdaapp.paymanyar.Model.ViewModels;

import androidx.annotation.NonNull;
import ir.tdaapp.paymanyar.Model.Enums.AdType;

public class VM_AdType {
    AdType adType;
    String title;

    public VM_AdType() {
    }

    public VM_AdType(AdType adType, String title) {
        this.adType = adType;
        this.title = title;
    }

    public AdType getAdType() {
        return adType;
    }

    public void setAdType(AdType adType) {
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
