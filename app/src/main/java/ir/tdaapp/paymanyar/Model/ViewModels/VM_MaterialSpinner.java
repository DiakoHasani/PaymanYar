package ir.tdaapp.paymanyar.Model.ViewModels;

import androidx.annotation.NonNull;

/**
 * مربوط به اسپینر مصالح
 **/
public class VM_MaterialSpinner {
    int id;
    String title;

    public VM_MaterialSpinner() {
    }

    public VM_MaterialSpinner(int id, String title) {
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
        return title;
    }
}
