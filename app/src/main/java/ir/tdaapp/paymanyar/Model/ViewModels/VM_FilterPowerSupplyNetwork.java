package ir.tdaapp.paymanyar.Model.ViewModels;

/**
 * مربوط به فیلتر صفحه شبکه تامین نیروکار
 **/
public class VM_FilterPowerSupplyNetwork {

    //استان
    int stateId;

    //شهر
    int cityId;

    //شغل
    int jobId;

    //سابقه کار
    int workExperienceId;

    int paging;

    public VM_FilterPowerSupplyNetwork() {
        stateId = cityId = jobId = workExperienceId = paging = 0;
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

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getWorkExperienceId() {
        return workExperienceId;
    }

    public void setWorkExperienceId(int workExperienceId) {
        this.workExperienceId = workExperienceId;
    }

    public int getPaging() {
        return paging;
    }

    public void setPaging(int paging) {
        this.paging = paging;
    }
}
