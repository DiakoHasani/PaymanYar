package ir.tdaapp.paymanyar.Model.ViewModels;

import ir.tdaapp.paymanyar.Model.Enums.AdTypeMaterial;
import ir.tdaapp.paymanyar.Model.Enums.NetworkItemType;

/**
 * مربوط به آیتم های رسایکلر صفحه مصالح
 * **/
public class VM_Material {

    int id;

    //دسته مصالح
    String Material;

    //نوع آگهی
    AdTypeMaterial adType;

    //قیمت
    String price;

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

    //مربوط به عادی یا برنزی یا نقره ای بودن آگهی می باشد
    NetworkItemType networkItemType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }

    public AdTypeMaterial getAdType() {
        return adType;
    }

    public void setAdType(AdTypeMaterial adType) {
        this.adType = adType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
}
