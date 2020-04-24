package ir.tdaapp.paymanyar.Model.ViewModels;

import androidx.annotation.NonNull;

// ویو مدل مربوط به استان ها
public class VM_EshtalItem {
    private String Id;
    private String Title;
    private String Value;
    private String Parent;
    private String Unit;
    private String Column_id;
    public VM_EshtalItem() {
    }

    public VM_EshtalItem(String id, String title,String value,String parent,String unit,String ColumnID) {
        Id = id;
        Title = title;
        Value=value;
        Parent=parent;
        this.Unit=unit;
        this.Column_id=ColumnID;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getParent() {
        return Parent;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public void setParent(String parent) {
        Parent = parent;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getColumn_id() {
        return Column_id;
    }

    public void setColumn_id(String column_id) {
        Column_id = column_id;
    }

    @NonNull
    @Override
    public String toString() {
        return getTitle();
    }
}
