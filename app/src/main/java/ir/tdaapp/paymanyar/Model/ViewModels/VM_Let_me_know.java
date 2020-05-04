package ir.tdaapp.paymanyar.Model.ViewModels;

//مربوط به فیلترهای با خبرم کن در صفحه مناقصات
public class VM_Let_me_know {

    //آیدی شهر
    private int cityId;

    //آیدی رشته تحصیلی
    private int majorId;

    //آی دی برآورد از
    private int fromEstimateId;

    //آی دی تا برآورد
    private int untilEstimateId;

    private String token;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
