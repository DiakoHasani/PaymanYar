package ir.tdaapp.paymanyar.Model.ViewModels;

//مربوط به مشخصات اسلایدر صفحه اصلی
public class VM_HomeSlider {
    private int id;
    private String title,url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
