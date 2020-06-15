package ir.tdaapp.paymanyar.Model.ViewModels;

//مربوط به مشخصات اسلایدر صفحه اصلی
public class VM_HomeSlider {
    private int id;
    private String title,image,url;
    private int urlKind;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getUrlKind() {
        return urlKind;
    }

    public void setUrlKind(int urlKind) {
        this.urlKind = urlKind;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
