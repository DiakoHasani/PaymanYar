package ir.tdaapp.paymanyar.Model.ViewModels;

public class VM_SavesGps {

    //آی دی نقشه
    private String Id;

    //عرض نقشه
    private String Wide;

    //طول نقشه
    private String Length;

    public VM_SavesGps() {
    }

    public VM_SavesGps(String id, String wide, String length) {
        Id = id;
        Wide = wide;
        Length = length;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getLength() {
        return Length;
    }

    public void setLength(String length) {
        Length = length;
    }

    public String getWide() {
        return Wide;
    }

    public void setWide(String wide) {
        Wide = wide;
    }
}
