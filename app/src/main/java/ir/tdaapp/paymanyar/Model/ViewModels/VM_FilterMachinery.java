package ir.tdaapp.paymanyar.Model.ViewModels;

/**
 * فیلتر صفحه ماشین آلات
 * **/
public class VM_FilterMachinery {
    //استان
    int stateId;

    //شهر
    int cityId;

    //آیدی ماشین آلات
    int machineryId;

    int paging;

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

    public int getMachineryId() {
        return machineryId;
    }

    public void setMachineryId(int machineryId) {
        this.machineryId = machineryId;
    }

    public int getPaging() {
        return paging;
    }

    public void setPaging(int paging) {
        this.paging = paging;
    }
}
