package ir.tdaapp.paymanyar.Model.ViewModels;

//ویو مدل مربوط به شارژ
public class VM_Charge {
    private int Id;
    private String Title;
    private String SubTitle;

    public VM_Charge() {
    }

    public VM_Charge(int id, String title, String subTitle) {
        Id = id;
        Title = title;
        SubTitle = subTitle;
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
}
