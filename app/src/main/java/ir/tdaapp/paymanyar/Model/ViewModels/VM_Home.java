package ir.tdaapp.paymanyar.Model.ViewModels;

import java.util.List;

//مربوط به تمامی اطلاعات صفحه اصلی می باشد مثل اسلایدرها و ورژن دیتابیس و برنامه و غیره
public class VM_Home {
    //در اینجا لیست اسلایدرها ست می شوند
    List<VM_HomeSlider> sliders;

    //در اینجا لیست آپدیت های برنامه ست می شوند
    List<VM_UpdateApp> updates;

    public VM_Home(List<VM_HomeSlider> sliders, List<VM_UpdateApp> updates) {
        this.sliders = sliders;
        this.updates = updates;
    }

    public List<VM_HomeSlider> getSliders() {
        return sliders;
    }

    public void setSliders(List<VM_HomeSlider> sliders) {
        this.sliders = sliders;
    }

    public List<VM_UpdateApp> getUpdates() {
        return updates;
    }

    public void setUpdates(List<VM_UpdateApp> updates) {
        this.updates = updates;
    }
}
