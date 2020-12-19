package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_Advertising;
import ir.tdaapp.paymanyar.Model.Services.S_AdvertisingFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Charge;

public class P_AdvertisingFragment {
    Context context;
    S_AdvertisingFragment s_advertisingFragment;
    Api_Advertising api_advertising;
    Disposable dispose_getAds,dispose_setAds;

    public P_AdvertisingFragment(Context context, S_AdvertisingFragment s_advertisingFragment) {
        this.context = context;
        this.s_advertisingFragment = s_advertisingFragment;
        api_advertising = new Api_Advertising();
    }

    public void start() {
        s_advertisingFragment.onHideAll();
        s_advertisingFragment.onLoading(true);
        s_advertisingFragment.OnStart();
        getAds();
    }

    void getAds() {
        s_advertisingFragment.onLoading(true);
        Single<List<VM_Charge>> data = api_advertising.getAds();
        dispose_getAds = data.subscribeWith(new DisposableSingleObserver<List<VM_Charge>>() {
            @Override
            public void onSuccess(List<VM_Charge> vm_charges) {
                s_advertisingFragment.onHideAll();
                s_advertisingFragment.onShowRecycler(true);
                setAds(vm_charges);
            }

            @Override
            public void onError(Throwable e) {
                s_advertisingFragment.onHideAll();
                s_advertisingFragment.onError(Error.GetErrorVolley(e.toString()));
            }
        });
    }

    void setAds(List<VM_Charge> ads) {
        Observable<VM_Charge> data = Observable.fromIterable(ads);
        dispose_setAds=data.subscribe(ad -> {
            s_advertisingFragment.onItem(ad);
        }, throwable -> {

        }, () -> {
            s_advertisingFragment.onFinish();
        });
    }

    public void cancel(Context context, String TAG) {
        api_advertising.Cancel(context, TAG);
        if (dispose_getAds != null) {
            dispose_getAds.dispose();
        }

        if (dispose_setAds != null) {
            dispose_setAds.dispose();
        }
    }
}
