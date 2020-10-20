package ir.tdaapp.paymanyar.Model.ViewModels;

import java.util.ArrayList;
import java.util.List;

import ir.tdaapp.paymanyar.Model.Enums.StepsAddPower;

/**
 * مربوط به افزودن ماشین آلات
 **/
public class VM_PostMachinery {

    //مربوط به آیدی یوزر
    int userId;

    //قیمت
    String price;

    //نوع آگهی
    VM_AdTypeMachinery adType;

    //آیدی ماشین آلات
    int machineryId;

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

    public VM_PostMachinery() {
        userId = machineryId = state = city = 0;
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

    public VM_AdTypeMachinery getAdType() {
        return adType;
    }

    public void setAdType(VM_AdTypeMachinery adType) {
        this.adType = adType;
    }

    public int getMachineryId() {
        return machineryId;
    }

    public void setMachineryId(int machineryId) {
        this.machineryId = machineryId;
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
