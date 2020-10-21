package ir.tdaapp.paymanyar.Model.ViewModels;

/**
 * مربوط به فیلتر مصالح
 * **/
public class VM_FilterMaterial {
    //استان
    int stateId;

    //شهر
    int cityId;

    //آیدی مصالح
    int materialId;

    int paging;

    public VM_FilterMaterial() {
        stateId = cityId = materialId = paging = 0;
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

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public int getPaging() {
        return paging;
    }

    public void setPaging(int paging) {
        this.paging = paging;
    }
}
