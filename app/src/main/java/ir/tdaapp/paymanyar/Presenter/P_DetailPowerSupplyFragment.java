package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Jobs;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_States;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_WorkExperiences;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_PowerSupply;
import ir.tdaapp.paymanyar.Model.Services.S_DetailPowerSupplyFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailPowerSupply;


/**
 * مربوط به جزئیات نیروکار
 **/
public class P_DetailPowerSupplyFragment {

    Context context;
    S_DetailPowerSupplyFragment s_detailPowerSupply;
    Tbl_WorkExperiences tbl_workExperiences;
    Tbl_Jobs tbl_jobs;
    Tbl_States tbl_states;
    Api_PowerSupply api_powerSupply;
    Disposable dispose_getVal;

    public P_DetailPowerSupplyFragment(Context context, S_DetailPowerSupplyFragment s_detailPowerSupply) {
        this.context = context;
        this.s_detailPowerSupply = s_detailPowerSupply;
        tbl_workExperiences = new Tbl_WorkExperiences(context);
        tbl_jobs = new Tbl_Jobs(context);
        tbl_states = new Tbl_States(context);
        api_powerSupply = new Api_PowerSupply();
    }

    public void start() {
        s_detailPowerSupply.OnStart();
        s_detailPowerSupply.onLoading(true);
        getVal();
    }

    void getVal() {
        Single<VM_DetailPowerSupply> data = api_powerSupply.getDetailPowerSupply(s_detailPowerSupply.onItemId());

        dispose_getVal=data.subscribeWith(new DisposableSingleObserver<VM_DetailPowerSupply>() {
            @Override
            public void onSuccess(VM_DetailPowerSupply detailPowerSupply) {
                s_detailPowerSupply.onLoading(false);
                s_detailPowerSupply.onSuccess();
                s_detailPowerSupply.onItem(detailPowerSupply);
                s_detailPowerSupply.onFinish();
            }

            @Override
            public void onError(Throwable e) {
                s_detailPowerSupply.onLoading(false);
                s_detailPowerSupply.onError(Error.GetErrorVolley(e.toString()));
                s_detailPowerSupply.onFinish();
            }
        });
    }

    /**
     * در اینجا تایتل سابقه کار براساس آیدی برگشت داده می شود
     **/
    public String getTitleWorkExperiencesById(int id) {
        return tbl_workExperiences.getTitleById(id);
    }

    /**
     * در اینجا تایتل شغل براساس آیدی آن برگشت می دهد
     **/
    public String getTitleJobById(int id) {
        return tbl_jobs.getTitleById(id);
    }

    /**
     * در اینجا تایتل شهر براساس آیدی آن برگشت می دهد
     **/
    public String getTitleStateById(int id) {
        return tbl_states.getTitleById(id);
    }

    public void cancel(String tag) {
        api_powerSupply.cancel(tag, context);

        if (dispose_getVal != null) {
            dispose_getVal.dispose();
        }
    }
}
