package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_Home;
import ir.tdaapp.paymanyar.Model.Services.S_HomeFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Home;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_HomeSlider;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_UpdateApp;

public class P_HomeFragment {

    S_HomeFragment s_homeFragment;
    Context context;
    Disposable dispose_getVals, dispose_setSliderItem, dispose_setUpdates;
    Api_Home api_home;

    public P_HomeFragment(S_HomeFragment s_homeFragment, Context context) {
        this.s_homeFragment = s_homeFragment;
        this.context = context;
        api_home = new Api_Home();
    }

    //با فراخوانی این متد عملیات گرفتن داده ها شروع می شود
    public void start() {
        s_homeFragment.OnStart();
        getVals();
    }

    //در اینجا داده ها را از سرور می خواند
    void getVals() {

        s_homeFragment.onLoading(true);
        s_homeFragment.onShowSlider(false);
        Single<VM_Home> data = api_home.HomeData();

        dispose_getVals = data.subscribeWith(new DisposableSingleObserver<VM_Home>() {
            @Override
            public void onSuccess(VM_Home vm_home) {
                s_homeFragment.onLoading(false);
                setSliderItem(vm_home);
            }

            @Override
            public void onError(Throwable e) {
                s_homeFragment.onShowSlider(false);
                s_homeFragment.onLoading(false);
                s_homeFragment.onError(Error.GetErrorVolley(e.toString()));
            }
        });
    }

    //در اینجا آیتم های اسلایدر یکی یکی به ویو پیجر اضافه می شوند
    //بعد از اینکه کار اسلایدر به پایان رسید متد مربوط به عملیات آپدیت فراخوانی می شود
    void setSliderItem(VM_Home vm_home) {
        Observable<VM_HomeSlider> data = Observable.fromIterable(vm_home.getSliders());
        dispose_setSliderItem = data.subscribe(slider -> {
                    s_homeFragment.onItemSlider(slider);
                },
                throwable -> {
                }, () -> {
                    s_homeFragment.onLoading(false);
                    s_homeFragment.onShowSlider(true);
                    setUpdates(vm_home.getUpdates());
                });
    }

    //در اینجا آپدیت ها یکی یکی گرفته می شوند و عملیات خود را انجام می دهیم
    void setUpdates(List<VM_UpdateApp> updates) {
        Observable<VM_UpdateApp> list = Observable.fromIterable(updates);
        dispose_setUpdates = list.subscribe(vm_updateApp -> {

                }
                , throwable -> {
                }
                , () -> {
                    s_homeFragment.onFinish();
                });
    }

    //با فراخوانی این متد تمامی عملیات این پرزنتر لغو می شود این متد زمانی که فرگمنت ما بسته شود فراخوانی می شود
    public void Cancel(String TAG) {
        //با بسته شدن صفحه تمامی عملیات وولی لغو می شوند
        api_home.Cancel(TAG, context);

        //در اینجا آرایکس جاوا مربوط به گرفتن اطلاعات از سرور لغو می شود
        if (dispose_getVals != null) {
            dispose_getVals.dispose();
        }

        if (dispose_setSliderItem != null) {
            dispose_setSliderItem.dispose();
        }
        if (dispose_setUpdates != null) {
            dispose_setUpdates.dispose();
        }

    }
}
