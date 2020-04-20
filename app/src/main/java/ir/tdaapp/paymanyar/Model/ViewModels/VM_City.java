package ir.tdaapp.paymanyar.Model.ViewModels;

import androidx.annotation.NonNull;

// ویو مدل مربوط به استان ها
public class VM_City {
    private int Id;
    private String Title;

    public VM_City() {
    }

    public VM_City(int id, String title) {
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
