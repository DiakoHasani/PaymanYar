package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_PowerSupply;
import ir.tdaapp.paymanyar.Model.Services.S_AdUpgradeDialog;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_AdUpgrade;

/**
 * مربوط به دیالوگ ارتقا آگهی نیروکار یا مصالح یا ماشین آلات
 **/
public class P_AdUpgradeDialog {

    Context context;
    S_AdUpgradeDialog s_adUpgradeDialog;
    Api_PowerSupply api_powerSupply;
    Disposable dispose_getVals, dispose_setVals;

    public P_AdUpgradeDialog(Context context, S_AdUpgradeDialog s_adUpgradeDialog) {
        this.context = context;
        this.s_adUpgradeDialog = s_adUpgradeDialog;
        api_powerSupply = new Api_PowerSupply();
    }

    public void start() {
        s_adUpgradeDialog.OnStart();
        s_adUpgradeDialog.onHideAll();
        s_adUpgradeDialog.onLoading(true);
        getVals();
    }

    void getVals() {

        Single<List<VM_AdUpgrade>> data = api_powerSupply.getUpgrades();
        dispose_getVals = data.subscribeWith(new DisposableSingleObserver<List<VM_AdUpgrade>>() {
            @Override
            public void onSuccess(List<VM_AdUpgrade> vm_adUpgrades) {
                s_adUpgradeDialog.onHideAll();
                s_adUpgradeDialog.onSuccess();
                setVals(vm_adUpgrades);
            }

            @Override
            public void onError(Throwable e) {
                s_adUpgradeDialog.onHideAll();
                s_adUpgradeDialog.onError(Error.GetErrorVolley(e.toString()));
            }
        });
    }

    void setVals(List<VM_AdUpgrade> adUpgrades) {
        Observable<VM_AdUpgrade> data = Observable.fromIterable(adUpgrades);
        dispose_setVals = data.subscribe(adUpgrade -> {
            s_adUpgradeDialog.onItem(adUpgrade);
        }, throwable -> {

        }, () -> {
            s_adUpgradeDialog.onFinish();
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
    }
}
