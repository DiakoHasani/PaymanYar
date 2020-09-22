package ir.tdaapp.paymanyar.Model.ViewModels;

/**
 * مربوط به آیتم های رسایکلر صفحه شبکه تامین مالی
 * **/
public class VM_PowerSupplyNetwork {
    int id;

    //شغل
    String jobTitle;

    //نام کاربر
    String name;

    //سابقه کار
    String workExperience;

    //شماره موبایل
    String cellPhone;

    //نام استان و شهر
    String ProvinceAndCity;

    //تاریخ
    String date;

    //عکس
    String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getProvinceAndCity() {
        return ProvinceAndCity;
    }

    public void setProvinceAndCity(String provinceAndCity) {
        ProvinceAndCity = provinceAndCity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
