package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Material;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Notification;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_States;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_PowerSupply;
import ir.tdaapp.paymanyar.Model.Services.RemoveSupplyNetwork;
import ir.tdaapp.paymanyar.Model.Services.S_MyMaterialFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Material;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;

/**
 * مربوط به صفحه مصالح من
 **/
public class P_MyMaterialFragment {
    Context context;
    S_MyMaterialFragment s_materialFragment;
    Api_PowerSupply api_powerSupply;
    Tbl_States tbl_states;
    Tbl_Material tbl_material;
    Disposable dispose_getVals, dispose_setVals,dispose_deleteMaterial;
    Tbl_Notification tbl_notification;

    public P_MyMaterialFragment(Context context, S_MyMaterialFragment s_materialFragment) {
        this.context = context;
        this.s_materialFragment = s_materialFragment;
        api_powerSupply = new Api_PowerSupply();
        tbl_states = new Tbl_States(context);
        tbl_material = new Tbl_Material(context);
        tbl_notification=new Tbl_Notification();
    }

    public void start() {
        if (((MainActivity) context).getTbl_user().hasAccount(context)) {
            s_materialFragment.OnStart();
            s_materialFragment.onHideAll();
            s_materialFragment.onLoading(true);
            getVals();
        } else {
            s_materialFragment.noAccount();
        }
    }

    void getVals() {
        int userId = ((MainActivity) context).getTbl_user().getUserId(context);
        Single<List<VM_Material>> data = api_powerSupply.getMyMaterials(userId, tbl_states.getProvincesOrCities(0),context);
        dispose_getVals = data.subscribeWith(new DisposableSingleObserver<List<VM_Material>>() {
            @Override
            public void onSuccess(List<VM_Material> vm_materials) {
                s_materialFragment.onHideAll();
                s_materialFragment.onLoading(false);
                s_materialFragment.onSuccess();

                if (vm_materials.size() > 0) {
                    setVals(vm_materials);
                } else {
                    s_materialFragment.onEmpty();
                    s_materialFragment.onFinish();
                }
            }

            @Override
            public void onError(Throwable e) {
                s_materialFragment.onHideAll();
                s_materialFragment.onLoading(false);
                s_materialFragment.onError(Error.GetErrorVolley(e.toString()));
            }
        });
    }

    void setVals(List<VM_Material> vals){
        Observable<VM_Material> data = Observable.fromIterable(vals);

        dispose_setVals = data.subscribe(material -> {
            s_materialFragment.onItem(material);
        }, throwable -> {

        }, () -> {
            s_materialFragment.onFinish();
        });
    }

    /**
     * در اینجا یک مصالح حذف می شود
     * **/
    public void deleteMaterial(int id, RemoveSupplyNetwork removeSupplyNetwork){
        String apiKey = tbl_notification.getToken(context);
        Single<VM_Message> data=api_powerSupply.deleteMaterial(id,apiKey);
        s_materialFragment.onLoadingDelete(true);
        dispose_deleteMaterial = data.subscribeWith(new DisposableSingleObserver<VM_Message>() {
            @Override
            public void onSuccess(VM_Message message) {
                s_materialFragment.onLoadingDelete(false);
                removeSupplyNetwork.onSuccess(message);
            }

            @Override
            public void onError(Throwable e) {
                s_materialFragment.onLoadingDelete(false);
                removeSupplyNetwork.onError(Error.GetErrorVolley(e.toString()));
            }
        });
    }

    public void cancel(String tag) {
        api_powerSupply.cancel(tag, context);

        if (dispose_getVals != null) {
            dispose_getVals.dispose();
        }

        if (dispose_setVals != null) {
            dispose_setVals.dispose();
        }

        if (dispose_deleteMaterial != null) {
            dispose_deleteMaterial.dispose();
        }
    }
}
