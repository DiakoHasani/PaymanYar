package ir.tdaapp.paymanyar.Model.ViewModels;

public class VM_SavesGps {

    //آی دی نقشه
    private int Id;

    //عرض نقشه
    private int Wide;

    //طول نقشه
    private int Length;

    public VM_SavesGps() {
    }

    public VM_SavesGps(int id, int wide, int length) {
        Id = id;
        Wide = wide;
        Length = length;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getLength() {
        return Length;
    }

    public void setLength(int length) {
        Length = length;
    }

    public int getWide() {
        return Wide;
    }

    public void setWide(int wide) {
        Wide = wide;
    }
}
