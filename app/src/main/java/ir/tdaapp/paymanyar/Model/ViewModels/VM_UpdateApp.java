package ir.tdaapp.paymanyar.Model.ViewModels;

//مربوط به ورژن دیتابیس و ورژن اپلیکیشن و کویری های دیتابیس و غیره می باشد
public class VM_UpdateApp {

    String Query;
    Double Vesrsion, AppVesrsion;
    boolean Cleardate;

    public String getQuery() {
        return Query;
    }

    public void setQuery(String query) {
        Query = query;
    }

    public boolean isCleardate() {
        return Cleardate;
    }

    public void setCleardate(boolean cleardate) {
        Cleardate = cleardate;
    }

    public Double getVesrsion() {
        return Vesrsion;
    }

    public void setVesrsion(Double vesrsion) {
        Vesrsion = vesrsion;
    }

    public Double getAppVesrsion() {
        return AppVesrsion;
    }

    public void setAppVesrsion(Double appVesrsion) {
        AppVesrsion = appVesrsion;
    }
}
