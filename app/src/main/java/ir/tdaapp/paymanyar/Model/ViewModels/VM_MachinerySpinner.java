package ir.tdaapp.paymanyar.Model.ViewModels;

import androidx.annotation.NonNull;

/**
 * برای اسپینر ماشین آلات
 * **/
public class VM_MachinerySpinner {
    int id;
    String title;

    public VM_MachinerySpinner() {
        id = 0;
        title = "";
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
