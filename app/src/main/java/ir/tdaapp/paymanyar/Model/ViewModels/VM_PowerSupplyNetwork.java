package ir.tdaapp.paymanyar.Model.ViewModels;

import ir.tdaapp.paymanyar.Model.Enums.NetworkItemType;

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

    //برای ویژه بودن
    boolean special;

    //برای وضعیت تایید آگهی
    boolean isActive;

    //مربوط به عادی یا برنزی یا نقره ای بودن آگهی می باشد
    NetworkItemType networkItemType;

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

    public boolean isSpecial() {
        return special;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }

    public NetworkItemType getNetworkItemType() {
        return networkItemType;
    }

    public void setNetworkItemType(NetworkItemType networkItemType) {
        this.networkItemType = networkItemType;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
