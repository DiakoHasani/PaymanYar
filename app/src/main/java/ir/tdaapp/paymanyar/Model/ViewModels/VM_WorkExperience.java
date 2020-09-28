package ir.tdaapp.paymanyar.Model.ViewModels;

import androidx.annotation.NonNull;

/**
 * مربوط به سابقه کار
 * **/
public class VM_WorkExperience {
    int id;
    String title;

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
