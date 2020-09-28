package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_AdType;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Jobs;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_States;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_WorkExperiences;
import ir.tdaapp.paymanyar.Model.Services.S_AddPowerSupply;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_AdType;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Job;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_ProvincesAndCities;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_WorkExperience;
import ir.tdaapp.paymanyar.R;

/**
 * مربوط به افزودن نیروکار
 **/
public class P_AddPowerSupply {

    Context context;
    S_AddPowerSupply s_addPowerSupply;
    Tbl_States tbl_states;
    Tbl_WorkExperiences tbl_workExperiences;
    Tbl_Jobs tbl_jobs;
    Tbl_AdType tbl_adType;

    public P_AddPowerSupply(Context context, S_AddPowerSupply s_addPowerSupply) {
        this.context = context;
        this.s_addPowerSupply = s_addPowerSupply;

        tbl_states = new Tbl_States(context);
        tbl_workExperiences = new Tbl_WorkExperiences(context);
        tbl_jobs = new Tbl_Jobs(context);
        tbl_adType = new Tbl_AdType(context);
    }

    public void start() {

    }

    /**
     * در اینجا لیست استان ها گرفته می شود
     **/
    public void getProvinces() {
        ArrayAdapter<VM_ProvincesAndCities> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, tbl_states.getProvincesOrCities(0));
        s_addPowerSupply.getProvinces(adapter);
    }

    /**
     * در اینجا لیست شهرها گرفته می شود
     **/
    public void getCities(int parentId) {
        if (parentId != 0) {
            ArrayAdapter<VM_ProvincesAndCities> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, tbl_states.getProvincesOrCities(parentId));
            s_addPowerSupply.getCities(adapter);
        } else {
            List<VM_ProvincesAndCities> cities = new ArrayList<>();
            cities.add(new VM_ProvincesAndCities(0, context.getString(R.string.City), 0));
            ArrayAdapter<VM_ProvincesAndCities> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, cities);
            s_addPowerSupply.getCities(adapter);
        }
    }

    /**
     * در اینجا لیست سابقه کار گرفته می شود
     * **/
    public void getWorkExperiences() {
        ArrayAdapter<VM_WorkExperience> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, tbl_workExperiences.getWorkExperiences());
        s_addPowerSupply.getWorkExperiences(adapter);
    }

    /**
     * در اینجا لیست شغل ها گرفته می شود
     * **/
    public void getJobs() {
        ArrayAdapter<VM_Job> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, tbl_jobs.getJobs());
        s_addPowerSupply.getJobs(adapter);
    }

    /**
     * در اینجا لیست نوع آگهی گرفته می شود
     * **/
    public void getAdTypes() {
        ArrayAdapter<VM_AdType> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, tbl_adType.getAdTypes());
        s_addPowerSupply.getAdTypes(adapter);
    }
}