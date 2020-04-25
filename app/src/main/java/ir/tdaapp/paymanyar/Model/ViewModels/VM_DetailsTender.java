package ir.tdaapp.paymanyar.Model.ViewModels;

//مربوط به جزئیات مناقصات
public class VM_DetailsTender {

    //کدپیمانیار
    private int Id;

    //برآورد ملی
    private String NationalEstimate;

    //تاریخ بازگشایی
    private String ReopeningDate;

    //ارسال پیشنهاد تا
    private String SendSuggestionsUp;

    //دریافت اسناد تا
    private String GetDocumentsUp;

    //محل دریافت اسناد
    private String Place_of_Receipt_of_Documents;

    //دستگاه مناقصه گذار
    private String TenderDevice;

    //وبسایت
    private String website;

    //توضیحات
    private String Description;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNationalEstimate() {
        return NationalEstimate;
    }

    public void setNationalEstimate(String nationalEstimate) {
        NationalEstimate = nationalEstimate;
    }

    public String getReopeningDate() {
        return ReopeningDate;
    }

    public void setReopeningDate(String reopeningDate) {
        ReopeningDate = reopeningDate;
    }

    public String getSendSuggestionsUp() {
        return SendSuggestionsUp;
    }

    public void setSendSuggestionsUp(String sendSuggestionsUp) {
        SendSuggestionsUp = sendSuggestionsUp;
    }

    public String getGetDocumentsUp() {
        return GetDocumentsUp;
    }

    public void setGetDocumentsUp(String getDocumentsUp) {
        GetDocumentsUp = getDocumentsUp;
    }

    public String getPlace_of_Receipt_of_Documents() {
        return Place_of_Receipt_of_Documents;
    }

    public void setPlace_of_Receipt_of_Documents(String place_of_Receipt_of_Documents) {
        Place_of_Receipt_of_Documents = place_of_Receipt_of_Documents;
    }

    public String getTenderDevice() {
        return TenderDevice;
    }

    public void setTenderDevice(String tenderDevice) {
        TenderDevice = tenderDevice;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
