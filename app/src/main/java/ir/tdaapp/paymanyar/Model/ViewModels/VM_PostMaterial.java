package ir.tdaapp.paymanyar.Model.ViewModels;

import java.util.ArrayList;
import java.util.List;

import ir.tdaapp.paymanyar.Model.Enums.StepsAddPower;

/**
 * ویومدل مربوط به افزودن مصالح
 * **/
public class VM_PostMaterial {
    //مربوط به آیدی یوزر
    int userId;

    //قیمت
    String price;

    //نوع آگهی
    VM_AdTypeMaterial adType;

    //آیدی مصالح
    int materialId;

    //استان
    int state;

    //شهر
    int city;

    //عنوان
    String title;

    //شماره تماس
    String cellPhone;

    //توضیحات
    String description;

    //لیست عکس ها
    List<String> images;

    //ویژه بودن یا غیرویژه بودن آگهی
    boolean special;

    StepsAddPower stepPower;

    public VM_PostMaterial() {
        userId = materialId = state = city = 0;
        price = title = cellPhone = description = "";
        images = new ArrayList<>();
        special = false;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public VM_AdTypeMaterial getAdType() {
        return adType;
    }

    public void setAdType(VM_AdTypeMaterial adType) {
        this.adType = adType;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public boolean isSpecial() {
        return special;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }

    public StepsAddPower getStepPower() {
        return stepPower;
    }

    public void setStepPower(StepsAddPower stepPower) {
        this.stepPower = stepPower;
    }
}
