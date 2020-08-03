package ir.tdaapp.paymanyar.Model.ViewModels;

import java.util.List;

import ir.tdaapp.paymanyar.Model.Enums.StepsAnalizeTender;

//مربوط به جزئیات آنالیز مناقصه
public class VM_AnaliseInfo {

    int id;

    //نام مناقصه
    String tenderName;

    //برآورد مالی
    String nationalEstimate;

    //نام پیمانکار
    String contractorName;

    //شماره موبایل
    String phoneNumber;

    //توضیحات
    String description;

    //آدرس فایل ها
    List<String> fileUrls;

    //درصد های پیشنهادی
    List<Float> percents;

    //وضعیت سفارش
    StepsAnalizeTender step;

    //زمان پرداخت هزینه
    String timePay;

    //مدت زمان انجام
    String doingTime;

    //هزینه سفارش
    String AmountPayable;

    //مربوط به زمان تایمر
    String timer;

    //مربوط به نام فایل
    String fileName;

    //زمانبندی تفصیلی در بخش زمانبندی
    boolean detailedSchedule;

    //زمانبندی اولیه در بخش زمانبندی
    boolean initialSchedule;

    //تاریخ تحویل زمین در بخش زمانبندی
    String landDeliveryDate;

    //مدت زمان پروژه در بخش زمانبندی
    int projectDuration;

    //سامانه جام در بخش حسابرسی
    boolean jumSystem;

    //سامانه پردیس در بخش حسابرسی
    boolean pardisSystem;

    //سامانه سحر در بخش حسابرسی
    boolean saharSystem;

    //نیاز به ثبت سامانه نیست در بخش حسابرسی
    boolean noSystem;

    //حسابرسی سال در بخش حسابرسی
    String audit_of_the_year;

    //فهرست بها در متره و برآورد
    String priceList;

    //پیشنهادی در متره و برآورد
    float suggested;

    //بالاسری در متره و برآورد
    float above;

    //منطقه در متره و برآورد
    float zone;

    //تجهیز کارگاه در متره و برآورد
    float equippingTheIngotWorkshop;

    //بهنگام در متره و برآورد
    float timely;

    //نام پروژه
    String projectName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenderName() {
        return tenderName;
    }

    public void setTenderName(String tenderName) {
        this.tenderName = tenderName;
    }

    public String getNationalEstimate() {
        return nationalEstimate;
    }

    public void setNationalEstimate(String nationalEstimate) {
        this.nationalEstimate = nationalEstimate;
    }

    public String getContractorName() {
        return contractorName;
    }

    public void setContractorName(String contractorName) {
        this.contractorName = contractorName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getFileUrls() {
        return fileUrls;
    }

    public void setFileUrls(List<String> fileUrls) {
        this.fileUrls = fileUrls;
    }

    public List<Float> getPercents() {
        return percents;
    }

    public void setPercents(List<Float> percents) {
        this.percents = percents;
    }

    public StepsAnalizeTender getStep() {
        return step;
    }

    public void setStep(StepsAnalizeTender step) {
        this.step = step;
    }

    public String getTimePay() {
        return timePay;
    }

    public void setTimePay(String timePay) {
        this.timePay = timePay;
    }

    public String getDoingTime() {
        return doingTime;
    }

    public void setDoingTime(String doingTime) {
        this.doingTime = doingTime;
    }

    public String getAmountPayable() {
        return AmountPayable;
    }

    public void setAmountPayable(String amountPayable) {
        AmountPayable = amountPayable;
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isDetailedSchedule() {
        return detailedSchedule;
    }

    public void setDetailedSchedule(boolean detailedSchedule) {
        this.detailedSchedule = detailedSchedule;
    }

    public boolean isInitialSchedule() {
        return initialSchedule;
    }

    public void setInitialSchedule(boolean initialSchedule) {
        this.initialSchedule = initialSchedule;
    }

    public String getLandDeliveryDate() {
        return landDeliveryDate;
    }

    public void setLandDeliveryDate(String landDeliveryDate) {
        this.landDeliveryDate = landDeliveryDate;
    }

    public int getProjectDuration() {
        return projectDuration;
    }

    public void setProjectDuration(int projectDuration) {
        this.projectDuration = projectDuration;
    }

    public boolean isJumSystem() {
        return jumSystem;
    }

    public void setJumSystem(boolean jumSystem) {
        this.jumSystem = jumSystem;
    }

    public boolean isPardisSystem() {
        return pardisSystem;
    }

    public void setPardisSystem(boolean pardisSystem) {
        this.pardisSystem = pardisSystem;
    }

    public boolean isSaharSystem() {
        return saharSystem;
    }

    public void setSaharSystem(boolean saharSystem) {
        this.saharSystem = saharSystem;
    }

    public boolean isNoSystem() {
        return noSystem;
    }

    public void setNoSystem(boolean noSystem) {
        this.noSystem = noSystem;
    }

    public String getAudit_of_the_year() {
        return audit_of_the_year;
    }

    public void setAudit_of_the_year(String audit_of_the_year) {
        this.audit_of_the_year = audit_of_the_year;
    }

    public String getPriceList() {
        return priceList;
    }

    public void setPriceList(String priceList) {
        this.priceList = priceList;
    }

    public float getSuggested() {
        return suggested;
    }

    public void setSuggested(float suggested) {
        this.suggested = suggested;
    }

    public float getAbove() {
        return above;
    }

    public void setAbove(float above) {
        this.above = above;
    }

    public float getZone() {
        return zone;
    }

    public void setZone(float zone) {
        this.zone = zone;
    }

    public float getEquippingTheIngotWorkshop() {
        return equippingTheIngotWorkshop;
    }

    public void setEquippingTheIngotWorkshop(float equippingTheIngotWorkshop) {
        this.equippingTheIngotWorkshop = equippingTheIngotWorkshop;
    }

    public float getTimely() {
        return timely;
    }

    public void setTimely(float timely) {
        this.timely = timely;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
