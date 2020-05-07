package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_Charge;
import ir.tdaapp.paymanyar.Model.Services.S_ChargeFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Charge;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;

public class P_ChargeFragment {

    Context context;
    S_ChargeFragment s_chargeFragment;
    Api_Charge api_charge;
    Disposable dispose_getCharges, dispose_setCharges,dispose_getInventoryUser;

    public P_ChargeFragment(Context context, S_ChargeFragment s_chargeFragment) {
        this.context = context;
        this.s_chargeFragment = s_chargeFragment;
        api_charge = new Api_Charge();
    }

    public void start() {
        s_chargeFragment.onHideAll();
        s_chargeFragment.onLoading(true);
        s_chargeFragment.OnStart();
        getCharges();
        getInventoryUser();
    }

    void getCharges() {

        Single<List<VM_Charge>> data = api_charge.getCharges();

        dispose_getCharges = data.subscribeWith(new DisposableSingleObserver<List<VM_Charge>>() {
            @Override
            public void onSuccess(List<VM_Charge> vm_charges) {
                s_chargeFragment.onHideAll();
                s_chargeFragment.onShowRecycler(true);
                setCharges(vm_charges);
            }

            @Override
            public void onError(Throwable e) {
                s_chargeFragment.onHideAll();
                s_chargeFragment.onError(Error.GetErrorVolley(e.toString()));
            }
        });

    }

    //در اینجا موجودی کاربر گرفته می شود
    void getInventoryUser(){

        s_chargeFragment.onLoadingInventory(true);
        Single<Integer> data=api_charge.getInventory(((MainActivity)context).getTbl_user().getUserId(context));

        dispose_getInventoryUser=data.subscribeWith(new DisposableSingleObserver<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                s_chargeFragment.onLoadingInventory(false);
                s_chargeFragment.onSuccessInventory(integer);
            }

            @Override
            public void onError(Throwable e) {
                s_chargeFragment.onLoadingInventory(false);
                s_chargeFragment.onErrorInventory();
            }
        });
    }

    //در اینجا شارژها یکی یکی به رسایکلر اضافه می شوند
    void setCharges(List<VM_Charge> charges) {
        Observable<VM_Charge> data = Observable.fromIterable(charges);
        dispose_setCharges = data.subscribe(charge -> {
            s_chargeFragment.onItemCharge(charge);
        }, throwable -> {

        }, () -> {
            s_chargeFragment.onFinish();
        });
    }

    public void Cancel(String Tag) {

        api_charge.Cancel(context, Tag);

        if (dispose_getCharges != null) {
            dispose_getCharges.dispose();
        }

        if (dispose_setCharges != null) {
            dispose_setCharges.dispose();
        }

        if (dispose_getInventoryUser != null) {
            dispose_getInventoryUser.dispose();
        }

    }

}
