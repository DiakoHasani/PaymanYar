package ir.tdaapp.paymanyar.Model.ViewModels;

//ویو مدل مربوط به شارژ
public class VM_Charge {
    private int Id;
    private String Title;
    private String SubTitle;
    private boolean IsSpecial;
    private int TotalHour;

    public VM_Charge() {
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

    public String getSubTitle() {
        return SubTitle;
    }

    public void setSubTitle(String subTitle) {
        SubTitle = subTitle;
    }

    public boolean isSpecial() {
        return IsSpecial;
    }

    public void setSpecial(boolean special) {
        IsSpecial = special;
    }

    public int getTotalHour() {
        return TotalHour;
    }

    public void setTotalHour(int totalHour) {
        TotalHour = totalHour;
    }
}
