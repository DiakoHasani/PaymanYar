package ir.tdaapp.paymanyar.Model.ViewModels;

//مربوط به ورودی ارسال سفارش در صفحه آنالیز مناقصه
public class VM_InputAnalizeTender {

    public VM_InputAnalizeTender() {
        userId = 0;
        tenderId = 0;
        fee = "0";
        contractorName = "";
        cellPhone = "";
        tenderName = "";
        description = "";
        fileUrl1 = fileUrl2 = fileUrl3 = fileUrl4 = fileUrl5 = fileUrl6 = fileUrl7 = fileUrl8 = fileUrl9 = fileUrl10 = "";
        price1 = price2 = price3 = price4 = price5 = price6 = price7 = price8 = price9 = price10 = 0;
        detailedSchedule = initialSchedule = false;
        landDeliveryDate = "";
        projectDuration = 0;
        jumSystem = false;
        pardisSystem = false;
        saharSystem = false;
        noSystem = false;
        audit_of_the_year = "";
    }

    //آیدی کاربر
    int userId;

    //آیدی مناقصه
    int tenderId;

    //برآورد مالی
    String fee;

    //نام پیمانکار
    String contractorName;

    //شماره تماس
    String cellPhone;

    //نام مناقصه
    String tenderName;

    //توضیحات
    String description;

    //مربوط به آدرس فایل ها
    String fileUrl1, fileUrl2, fileUrl3, fileUrl4, fileUrl5, fileUrl6, fileUrl7, fileUrl8, fileUrl9, fileUrl10;

    //مربوط به درصدهای پیشنهادی
    float price1, price2, price3, price4, price5, price6, price7, price8, price9, price10;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTenderId() {
        return tenderId;
    }

    public void setTenderId(int tenderId) {
        this.tenderId = tenderId;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getContractorName() {
        return contractorName;
    }

    public void setContractorName(String contractorName) {
        this.contractorName = contractorName;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getTenderName() {
        return tenderName;
    }

    public void setTenderName(String tenderName) {
        this.tenderName = tenderName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileUrl1() {
        return fileUrl1;
    }

    public void setFileUrl1(String fileUrl1) {
        this.fileUrl1 = fileUrl1;
    }

    public String getFileUrl2() {
        return fileUrl2;
    }

    public void setFileUrl2(String fileUrl2) {
        this.fileUrl2 = fileUrl2;
    }

    public String getFileUrl3() {
        return fileUrl3;
    }

    public void setFileUrl3(String fileUrl3) {
        this.fileUrl3 = fileUrl3;
    }

    public String getFileUrl4() {
        return fileUrl4;
    }

    public void setFileUrl4(String fileUrl4) {
        this.fileUrl4 = fileUrl4;
    }

    public String getFileUrl5() {
        return fileUrl5;
    }

    public void setFileUrl5(String fileUrl5) {
        this.fileUrl5 = fileUrl5;
    }

    public String getFileUrl6() {
        return fileUrl6;
    }

    public void setFileUrl6(String fileUrl6) {
        this.fileUrl6 = fileUrl6;
    }

    public String getFileUrl7() {
        return fileUrl7;
    }

    public void setFileUrl7(String fileUrl7) {
        this.fileUrl7 = fileUrl7;
    }

    public String getFileUrl8() {
        return fileUrl8;
    }

    public void setFileUrl8(String fileUrl8) {
        this.fileUrl8 = fileUrl8;
    }

    public String getFileUrl9() {
        return fileUrl9;
    }

    public void setFileUrl9(String fileUrl9) {
        this.fileUrl9 = fileUrl9;
    }

    public String getFileUrl10() {
        return fileUrl10;
    }

    public void setFileUrl10(String fileUrl10) {
        this.fileUrl10 = fileUrl10;
    }

    public float getPrice1() {
        return price1;
    }

    public void setPrice1(float price1) {
        this.price1 = price1;
    }

    public float getPrice2() {
        return price2;
    }

    public void setPrice2(float price2) {
        this.price2 = price2;
    }

    public float getPrice3() {
        return price3;
    }

    public void setPrice3(float price3) {
        this.price3 = price3;
    }

    public float getPrice4() {
        return price4;
    }

    public void setPrice4(float price4) {
        this.price4 = price4;
    }

    public float getPrice5() {
        return price5;
    }

    public void setPrice5(float price5) {
        this.price5 = price5;
    }

    public float getPrice6() {
        return price6;
    }

    public void setPrice6(float price6) {
        this.price6 = price6;
    }

    public float getPrice7() {
        return price7;
    }

    public void setPrice7(float price7) {
        this.price7 = price7;
    }

    public float getPrice8() {
        return price8;
    }

    public void setPrice8(float price8) {
        this.price8 = price8;
    }

    public float getPrice9() {
        return price9;
    }

    public void setPrice9(float price9) {
        this.price9 = price9;
    }

    public float getPrice10() {
        return price10;
    }

    public void setPrice10(float price10) {
        this.price10 = price10;
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
