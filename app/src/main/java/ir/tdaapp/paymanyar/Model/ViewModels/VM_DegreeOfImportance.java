package ir.tdaapp.paymanyar.Model.ViewModels;

import androidx.annotation.NonNull;

//برای اسپینر میزان اهمیت در صفحه دامنه قیمت
public class VM_DegreeOfImportance {
    private int id;
    private String title;

    public VM_DegreeOfImportance() {
    }

    public VM_DegreeOfImportance(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return getTitle();
    }
}
