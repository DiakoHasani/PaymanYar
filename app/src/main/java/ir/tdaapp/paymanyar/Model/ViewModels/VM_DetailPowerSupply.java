package ir.tdaapp.paymanyar.Model.ViewModels;

import java.util.ArrayList;
import java.util.List;

import ir.tdaapp.paymanyar.Model.Enums.AdType;

/**
 * مربوط به جزئیات نیروکار
 * **/
public class VM_DetailPowerSupply {
    int id;
    String dateInsert;
    String name;
    AdType adType;
    int jobId;
    int workExperienceId;
    int stateId;
    int cityId;
    String phone;
    String description;
    boolean special;
    List<String> images;

    public VM_DetailPowerSupply() {
        id = 0;
        dateInsert = "";
        name = "";
        phone = "";
        description = "";
        jobId = 0;
        workExperienceId = 0;
        stateId = 0;
        cityId = 0;
        special = false;
        images=new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateInsert() {
        return dateInsert;
    }

    public void setDateInsert(String dateInsert) {
        this.dateInsert = dateInsert;
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

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getWorkExperienceId() {
        return workExperienceId;
    }

    public void setWorkExperienceId(int workExperienceId) {
        this.workExperienceId = workExperienceId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSpecial() {
        return special;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
