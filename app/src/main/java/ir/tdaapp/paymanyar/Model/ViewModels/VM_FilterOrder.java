package ir.tdaapp.paymanyar.Model.ViewModels;

public class VM_FilterOrder {

    //آنالیز مناقصه
    boolean tenderAnalise;

    //زمان بندی
    boolean scheduling;

    //حسابرسی
    boolean audit;

    //مابه التفاوت
    boolean difference;

    //متره و برآورد
    boolean costEstimation;

    public VM_FilterOrder() {
        tenderAnalise = false;
        scheduling = false;
        audit = false;
        difference = false;
        costEstimation = false;
    }

    public boolean isTenderAnalise() {
        return tenderAnalise;
    }

    public void setTenderAnalise(boolean tenderAnalise) {
        this.tenderAnalise = tenderAnalise;
    }

    public boolean isScheduling() {
        return scheduling;
    }

    public void setScheduling(boolean scheduling) {
        this.scheduling = scheduling;
    }

    public boolean isAudit() {
        return audit;
    }

    public void setAudit(boolean audit) {
        this.audit = audit;
    }

    public boolean isDifference() {
        return difference;
    }

    public void setDifference(boolean difference) {
        this.difference = difference;
    }

    public boolean isCostEstimation() {
        return costEstimation;
    }

    public void setCostEstimation(boolean costEstimation) {
        this.costEstimation = costEstimation;
    }
}
