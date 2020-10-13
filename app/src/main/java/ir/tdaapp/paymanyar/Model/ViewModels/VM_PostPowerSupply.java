package ir.tdaapp.paymanyar.Model.ViewModels;

import java.util.ArrayList;
import java.util.List;

import ir.tdaapp.paymanyar.Model.Enums.AdType;
import ir.tdaapp.paymanyar.Model.Enums.StepsAddPower;

/**
 * مربوط به مقادیر افزودن نیروکار
 **/
public class VM_PostPowerSupply {

    public VM_PostPowerSupply() {
        userId = 0;
        workExperiences = 0;
        state = 0;
        city = 0;
        job = 0;
        name = "";
        cellPhone = "";
        description = "";
        images = new ArrayList<>();
        adType = AdType.title;
        special = false;
        stepPower = StepsAddPower.Check_The_Ad;
    }

    //مربوط به آیدی یوزر
    int userId;

    //نام
    String name;

    //نوع آگهی
    AdType adType;

    //سابقه کار
    int workExperiences;

    //استان
    int state;

    //شهر
    int city;

    //شغل
    int job;

    //شماره تماس
    String cellPhone;

    //توضیحات
    String description;

    //لیست عکس ها
    List<String> images;

    //ویژه بودن یا غیرویژه بودن آگهی
    boolean special;

    StepsAddPower stepPower;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AdType getAdType() {
        return adType;
    }

    public void setAdType(AdType adType) {
        this.adType = adType;
    }

    public int getWorkExperiences() {
        return workExperiences;
    }

    public void setWorkExperiences(int workExperiences) {
        this.workExperiences = workExperiences;
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

    public int getJob() {
        return job;
    }

    public void setJob(int job) {
        this.job = job;
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
