package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Material;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_States;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_PowerSupply;
import ir.tdaapp.paymanyar.Model.Services.S_DetailMaterialFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailMaterial;

public class P_DetailMaterialFragment {

    Context context;
    S_DetailMaterialFragment s_detailMaterialFragment;
    Tbl_States tbl_states;
    Tbl_Material tbl_material;
    Api_PowerSupply api_powerSupply;
    Disposable dispose_getVal;

    public P_DetailMaterialFragment(Context context, S_DetailMaterialFragment s_detailMaterialFragment) {
        this.context = context;
        this.s_detailMaterialFragment = s_detailMaterialFragment;
        tbl_states = new Tbl_States(context);
        tbl_material = new Tbl_Material(context);
        api_powerSupply = new Api_PowerSupply();
    }

    public void start() {
        s_detailMaterialFragment.OnStart();
        s_detailMaterialFragment.onLoading(true);
        getVal();
    }

    void getVal(){
        Single<VM_DetailMaterial> data=api_powerSupply.getDetailMaterial(s_detailMaterialFragment.onItemId());
        dispose_getVal=data.subscribeWith(new DisposableSingleObserver<VM_DetailMaterial>() {
            @Override
            public void onSuccess(VM_DetailMaterial detailMaterial) {
                s_detailMaterialFragment.onLoading(false);
                s_detailMaterialFragment.onSuccess();
                s_detailMaterialFragment.onItem(detailMaterial);
                s_detailMaterialFragment.onFinish();
            }

            @Override
            public void onError(Throwable e) {
                s_detailMaterialFragment.onLoading(false);
                s_detailMaterialFragment.onError(Error.GetErrorVolley(e.toString()));
                s_detailMaterialFragment.onFinish();
            }
        });
    }

    /**
     * در اینجا تایتل شهر براساس آیدی آن برگشت می دهد
     **/
    public String getTitleStateById(int id) {
        return tbl_states.getTitleById(id);
    }

    /**
     * در اینجا تایتل ماشین آلات براساس آیدی آن برگشت داده می شود
     **/
    public String getTitleMaterialById(int id) {
        return tbl_material.getTitleById(id);
    }

    public void cancel(String tag) {
        api_powerSupply.cancel(tag, context);

        if (dispose_getVal != null) {
            dispose_getVal.dispose();
        }
    }
}
