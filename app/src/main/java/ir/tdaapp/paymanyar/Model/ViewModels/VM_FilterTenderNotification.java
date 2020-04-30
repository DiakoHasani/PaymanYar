package ir.tdaapp.paymanyar.Model.ViewModels;

//مربوط به فیلتر مناقصات
public class VM_FilterTenderNotification {

    //آی دی استان
    private int cityId;

    //آی دی رشته تحصیلی
    private int majorId;

    //شامل کلمه
    private String includesTheWord;

    //از تاریخ
    private String date;

    //آی دی برآورد از
    private int fromEstimateId;

    //آی دی تا برآورد
    private int untilEstimateId;

    private int page;

    //آی دی گاربر
    private int userId;

    //آی دی مناقصه
    private String tenderId;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public String getIncludesTheWord() {
        return includesTheWord;
    }

    public void setIncludesTheWord(String includesTheWord) {
        this.includesTheWord = includesTheWord;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getFromEstimateId() {
        return fromEstimateId;
    }

    public void setFromEstimateId(int fromEstimateId) {
        this.fromEstimateId = fromEstimateId;
    }

    public int getUntilEstimateId() {
        return untilEstimateId;
    }

    public void setUntilEstimateId(int untilEstimateId) {
        this.untilEstimateId = untilEstimateId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTenderId() {
        return tenderId;
    }

    public void setTenderId(String tenderId) {
        this.tenderId = tenderId;
    }
}
