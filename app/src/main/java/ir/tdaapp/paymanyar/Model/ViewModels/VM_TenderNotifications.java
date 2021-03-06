package ir.tdaapp.paymanyar.Model.ViewModels;

//ویو مدل لیست مناقصات
public class VM_TenderNotifications {

    private String Id;
    private String Title;

    //در اینجا اگر مقدار ترو باشد یعنی مناقصخ در لیست علاقه مندی ها ذخیره شده است
    private boolean IsStar;

    //اگر مقدار فالس باشد یعنی پولی است اگر ترو باشد یعنی رایگان است
    private boolean Free;

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

    public boolean isFree() {
        return Free;
    }

    public void setFree(boolean free) {
        Free = free;
    }

    public boolean isStar() {
        return IsStar;
    }

    public void setStar(boolean star) {
        IsStar = star;
    }
}
