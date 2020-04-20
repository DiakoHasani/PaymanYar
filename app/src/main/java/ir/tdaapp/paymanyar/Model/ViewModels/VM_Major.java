package ir.tdaapp.paymanyar.Model.ViewModels;

import androidx.annotation.NonNull;

//ویو مدل مربوط به رشته تحصیلی
public class VM_Major {
    int Id;
    String Title;

    public VM_Major() {
    }

    public VM_Major(int id, String title) {
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
