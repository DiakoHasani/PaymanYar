package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_City;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Major;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_Tender;
import ir.tdaapp.paymanyar.Model.Services.S_TenderNotificationFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_City;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Major;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotification;
import ir.tdaapp.paymanyar.R;

public class P_TenderNotificationFragment {

    private Context context;
    private S_TenderNotificationFragment s_tenderNotificationFragment;
    Api_Tender api_tender;
    Disposable dispose_getTenderNotification, dispose_setTenders, dispose_getSpinnerDatas, dispose_getMajors;
    Tbl_City tbl_city;
    Tbl_Major tbl_major;

    public P_TenderNotificationFragment(Context context, S_TenderNotificationFragment s_tenderNotificationFragment) {
        this.context = context;
        this.s_tenderNotificationFragment = s_tenderNotificationFragment;
        api_tender = new Api_Tender();
        tbl_city = new Tbl_City();
        tbl_major=new Tbl_Major();
    }

    public void start(int page) {

        //اگر مقدار پیج ما صفر باشد یعنی صفحه ما تازه باز شده است یا ریست شده است و عملیات اولیه آن نیز انجام می شود
        //اگر غیر از صفر باشد یعنی کاربر به آخر رسایکلر رسیده و داده های بعدی را از سرور می گیریم
        if (page == 0) {
            s_tenderNotificationFragment.OnStart();
            s_tenderNotificationFragment.onHideAll();
            s_tenderNotificationFragment.onLoading(true);
        } else {
            s_tenderNotificationFragment.onLoadingPaging(true);
        }

        getTenderNotification(page);
    }

    //در اینجا مناقصه ها برگشت داده میشوند
    void getTenderNotification(int page) {

        Single<List<VM_TenderNotification>> data = api_tender.getTenderNotification(page);
        dispose_getTenderNotification = data.subscribeWith(new DisposableSingleObserver<List<VM_TenderNotification>>() {
            @Override
            public void onSuccess(List<VM_TenderNotification> vm_tenderNotifications) {

                s_tenderNotificationFragment.onSuccess();

                if (page == 0) {
                    s_tenderNotificationFragment.onHideAll();
                    s_tenderNotificationFragment.onShowRecycler();
                } else {
                    s_tenderNotificationFragment.onLoadingPaging(false);
                    s_tenderNotificationFragment.onShowRecycler();
                }

                setTenders(vm_tenderNotifications);
            }

            @Override
            public void onError(Throwable e) {
                if (page == 0) {
                    s_tenderNotificationFragment.onHideAll();
                    s_tenderNotificationFragment.onError(Error.GetErrorVolley(e.toString()));
                } else {
                    s_tenderNotificationFragment.onLoadingPaging(false);
                    s_tenderNotificationFragment.onError(Error.GetErrorVolley(e.toString()));
                }
            }
        });
    }

    //در اینجا مناقصات به رسایکلر اضافه می شوند
    void setTenders(List<VM_TenderNotification> tenders) {

        Observable<VM_TenderNotification> data = Observable.fromIterable(tenders);

        dispose_setTenders = data.subscribe(vm_tenderNotification -> {
            s_tenderNotificationFragment.onItemTender(vm_tenderNotification);
        }, throwable -> {

        }, () -> {
            s_tenderNotificationFragment.onFinish();
        });
    }

    //در اینجا عملیات گرفتن داده های اسپینر شروع می شود
    public void getSpinnerDatas() {
        getCitiesSpinner();
        getMajorsSpinner();
    }

    //در اینجا داده اسپینر استان ست می شود
    void getCitiesSpinner() {

        Single<List<VM_City>> cities = tbl_city.getCitys(context);

        dispose_getSpinnerDatas = cities.subscribeWith(new DisposableSingleObserver<List<VM_City>>() {
            @Override
            public void onSuccess(List<VM_City> vm_cities) {
                ArrayAdapter<VM_City> adapter_City = new ArrayAdapter<>(context, R.layout.spinner_item2, vm_cities);
                s_tenderNotificationFragment.onItemCitySpinner(adapter_City);
            }

            @Override
            public void onError(Throwable e) {
                //اگر خطای در فراخوانی استانها رخ دهد کد زیر فراخوانی می شود و یک سطر بامتن خطا و آی دی صفر به اسپینر اضافه می شود
                List<VM_City> c = new ArrayList<>();
                c.add(new VM_City(0, context.getResources().getString(R.string.Error)));
                ArrayAdapter<VM_City> adapter_City = new ArrayAdapter<>(context, R.layout.spinner_item2, c);
                s_tenderNotificationFragment.onItemCitySpinner(adapter_City);
            }
        });
    }

    //در اینجا داده های اسپینر رشته تحصیلی گرفته می شوند
    void getMajorsSpinner() {

        Single<List<VM_Major>> majors = tbl_major.getMojors(context);

        dispose_getMajors = majors.subscribeWith(new DisposableSingleObserver<List<VM_Major>>() {
            @Override
            public void onSuccess(List<VM_Major> vm_majors) {
                ArrayAdapter<VM_Major> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, vm_majors);
                s_tenderNotificationFragment.onItemMajorSpinner(adapter);
            }

            @Override
            public void onError(Throwable e) {

                ///اگر خطای در فراخوانی رشته های تحصیلی خطای رخ دهد کد زیر فراخوانی می شود و یک سطر بامتن خطا و آی دی صفر به اسپینر اضافه می شود
                List<VM_Major> m = new ArrayList<>();
                m.add(new VM_Major(0, context.getResources().getString(R.string.Error)));
                ArrayAdapter<VM_Major> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, m);
                s_tenderNotificationFragment.onItemMajorSpinner(adapter);
            }
        });

    }

    public void Cancel(String Tag) {
        if (dispose_getTenderNotification != null) {
            dispose_getTenderNotification.dispose();
        }

        if (dispose_setTenders != null) {
            dispose_setTenders.dispose();
        }

        if (dispose_getSpinnerDatas != null) {
            dispose_getSpinnerDatas.dispose();
        }

        if (dispose_getMajors != null) {
            dispose_getMajors.dispose();
        }
    }
}
