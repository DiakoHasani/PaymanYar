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
}
