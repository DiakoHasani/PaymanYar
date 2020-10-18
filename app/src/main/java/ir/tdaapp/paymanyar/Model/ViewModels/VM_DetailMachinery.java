package ir.tdaapp.paymanyar.Model.ViewModels;

import java.util.ArrayList;
import java.util.List;

import ir.tdaapp.paymanyar.Model.Enums.AdTypeCondition;

/**
 * مربوط به جزئیات ماشین آلات
 **/
public class VM_DetailMachinery {
    int id;

    //نوع آگهی
    AdTypeCondition adTypeCondition;

    //قیمت
    String price;

    //آیدی ماشین آلات
    int MachineryId;

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

    public VM_DetailMachinery() {
        id = MachineryId = stateId = cityId = 0;
        price = title = cellPhone = description = "-";
        images=new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AdTypeCondition getAdTypeCondition() {
        return adTypeCondition;
    }

    public void setAdTypeCondition(AdTypeCondition adTypeCondition) {
        this.adTypeCondition = adTypeCondition;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getMachineryId() {
        return MachineryId;
    }

    public void setMachineryId(int machineryId) {
        MachineryId = machineryId;
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
