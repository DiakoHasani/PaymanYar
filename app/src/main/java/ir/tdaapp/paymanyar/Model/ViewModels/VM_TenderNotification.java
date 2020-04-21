package ir.tdaapp.paymanyar.Model.ViewModels;

import java.util.List;

//مربوط به اطلاع رسانی مناقصات
public class VM_TenderNotification {

    private List<VM_TenderNotifications> TenderNotifications;

    private int CountTenders;

    public VM_TenderNotification() {
    }

    public VM_TenderNotification(List<VM_TenderNotifications> tenderNotifications, int countTenders) {
        TenderNotifications = tenderNotifications;
        CountTenders = countTenders;
    }

    public List<VM_TenderNotifications> getTenderNotifications() {
        return TenderNotifications;
    }

    public void setTenderNotifications(List<VM_TenderNotifications> tenderNotifications) {
        TenderNotifications = tenderNotifications;
    }

    public int getCountTenders() {
        return CountTenders;
    }

    public void setCountTenders(int countTenders) {
        CountTenders = countTenders;
    }
}
