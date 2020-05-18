package ir.tdaapp.paymanyar.Model.ViewModels;

//مربوط به ویو مدل کتابخانه
public class VM_Library {

    private int Id;
    private String Title;
    private String Url;
    private boolean IsDownloaded;

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

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public boolean isDownloaded() {
        return IsDownloaded;
    }

    public void setDownloaded(boolean downloaded) {
        IsDownloaded = downloaded;
    }
}
