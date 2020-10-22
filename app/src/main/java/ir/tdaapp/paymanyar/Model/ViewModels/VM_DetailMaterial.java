package ir.tdaapp.paymanyar.Model.ViewModels;

import java.util.ArrayList;
import java.util.List;

import ir.tdaapp.paymanyar.Model.Enums.AdTypeMaterial;

/**
 * مربوط به جزئیات مصالح
 * **/
public class VM_DetailMaterial {
    int id;

    //نوع آگهی
    AdTypeMaterial adTypeMaterial;

    //قیمت
    String price;

    //دسته مصالح
    int materialId;

    //عنوان
    String title;

    //شماره تماس
    String cellPhone;

    //آیدی استان
    int stateId;

    //آیدی شهر
    int cityId;

    //توضیحات
    String description;

    List<String> images;

    public VM_DetailMaterial() {
        id = materialId = stateId = cityId = 0;
        price = title = cellPhone = description = "-";
        images=new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AdTypeMaterial getAdTypeMaterial() {
        return adTypeMaterial;
    }

    public void setAdTypeMaterial(AdTypeMaterial adTypeMaterial) {
        this.adTypeMaterial = adTypeMaterial;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
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

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
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
}
