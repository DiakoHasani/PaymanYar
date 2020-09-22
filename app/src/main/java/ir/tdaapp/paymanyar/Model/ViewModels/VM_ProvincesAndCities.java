package ir.tdaapp.paymanyar.Model.ViewModels;

import androidx.annotation.NonNull;

/**
 * مربوط به استان و شهر
 **/
public class VM_ProvincesAndCities {
    int id;
    String title;
    int parentId;

    public VM_ProvincesAndCities() {
    }

    public VM_ProvincesAndCities(int id, String title, int parentId) {
        this.id = id;
        this.title = title;
        this.parentId = parentId;
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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @NonNull
    @Override
    public String toString() {
        if (title != null)
            return title;
        return "";
    }
}
