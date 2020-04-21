package ir.tdaapp.paymanyar.Model.ViewModels;

import androidx.annotation.NonNull;

//مربوط به ویومدل برآورد
public class VM_Estimate {

    private int Id;
    private String Title;

    public VM_Estimate() {
    }

    public VM_Estimate(int id, String title) {
        Id = id;
        Title = title;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    @NonNull
    @Override
    public String toString() {
        return getTitle();
    }
}
