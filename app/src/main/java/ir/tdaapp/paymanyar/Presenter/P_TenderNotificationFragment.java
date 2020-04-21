package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;
import android.graphics.Color;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_City;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Estimate;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_IncludesTheWord;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Major;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_Tender;
import ir.tdaapp.paymanyar.Model.Services.S_TenderNotificationFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_City;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Estimate;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_IncludesTheWord;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Major;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotification;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotifications;
import ir.tdaapp.paymanyar.R;

public class P_TenderNotificationFragment {

    private Context context;
    private S_TenderNotificationFragment s_tenderNotificationFragment;
    Api_Tender api_tender;
    Disposable dispose_getTenderNotification, dispose_setTenders, dispose_getSpinnerDatas, dispose_getMajors;
    Disposable dispose_getIncludesTheWord, dispose_getFromEstimate, dispose_getUntilEstimate;
    Tbl_City tbl_city;
    Tbl_Major tbl_major;
    Tbl_IncludesTheWord tbl_includesTheWord;
    Tbl_Estimate tbl_estimate;
    PersianCalendar initDate;

    public P_TenderNotificationFragment(Context context, S_TenderNotificationFragment s_tenderNotificationFragment) {
        this.context = context;
        this.s_tenderNotificationFragment = s_tenderNotificationFragment;
        api_tender = new Api_Tender();
        tbl_city = new Tbl_City();
        tbl_major = new Tbl_Major();
        tbl_includesTheWord = new Tbl_IncludesTheWord();
        tbl_estimate = new Tbl_Estimate();
        initDate = new PersianCalendar();
        initDate.setPersianDate(1398, 3, 10);
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

        Single<VM_TenderNotification> data = api_tender.getTenderNotification(page);
        dispose_getTenderNotification = data.subscribeWith(new DisposableSingleObserver<VM_TenderNotification>() {

            @Override
            public void onSuccess(VM_TenderNotification notification) {

                s_tenderNotificationFragment.onSuccess();
                s_tenderNotificationFragment.onCountTenders(notification.getCountTenders());

                if (page == 0) {
                    s_tenderNotificationFragment.onHideAll();
                    s_tenderNotificationFragment.onShowRecycler();
                } else {
                    s_tenderNotificationFragment.onLoadingPaging(false);
                    s_tenderNotificationFragment.onShowRecycler();
                }

                setTenders(notification.getTenderNotifications());

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
    void setTenders(List<VM_TenderNotifications> tenders) {

        Observable<VM_TenderNotifications> data = Observable.fromIterable(tenders);

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
        getIncludesTheWord();
        getFromEstimate();
        getUntilEstimate();
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

    //در اینجا داده های اسپینر شامل کلمه ست می شوند
    void getIncludesTheWord() {

        Single<List<VM_IncludesTheWord>> includesTheWords = tbl_includesTheWord.getIncludesTheWords(context);

        dispose_getIncludesTheWord = includesTheWords.subscribeWith(new DisposableSingleObserver<List<VM_IncludesTheWord>>() {
            @Override
            public void onSuccess(List<VM_IncludesTheWord> vm_includesTheWords) {
                ArrayAdapter<VM_IncludesTheWord> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, vm_includesTheWords);
                s_tenderNotificationFragment.onItemIncludesTheWordSpinner(adapter);
            }

            @Override
            public void onError(Throwable e) {
                List<VM_IncludesTheWord> c = new ArrayList<>();
                c.add(new VM_IncludesTheWord(0, context.getResources().getString(R.string.Error)));
                ArrayAdapter<VM_IncludesTheWord> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, c);
                s_tenderNotificationFragment.onItemIncludesTheWordSpinner(adapter);
            }
        });
    }

    //در اینجا داده های اسپینر برآورد از گرفته می شود
    void getFromEstimate() {
        Single<List<VM_Estimate>> estimates = tbl_estimate.getFromEstimates(context);

        dispose_getFromEstimate = estimates.subscribeWith(new DisposableSingleObserver<List<VM_Estimate>>() {
            @Override
            public void onSuccess(List<VM_Estimate> vm_estimates) {
                ArrayAdapter<VM_Estimate> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, vm_estimates);
                s_tenderNotificationFragment.onItemFromEstimateSpinner(adapter);
            }

            @Override
            public void onError(Throwable e) {
                List<VM_Estimate> v = new ArrayList<>();
                v.add(new VM_Estimate(0, context.getString(R.string.Error)));
                ArrayAdapter<VM_Estimate> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, v);
                s_tenderNotificationFragment.onItemFromEstimateSpinner(adapter);
            }
        });
    }

    //در اینجا داده های اسپینر برآورد تا گرفته می شود
    void getUntilEstimate() {

        Single<List<VM_Estimate>> estimates = tbl_estimate.getUntilEstimates(context);
        dispose_getUntilEstimate = estimates.subscribeWith(new DisposableSingleObserver<List<VM_Estimate>>() {
            @Override
            public void onSuccess(List<VM_Estimate> vm_estimates) {
                ArrayAdapter<VM_Estimate> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, vm_estimates);
                s_tenderNotificationFragment.onItemUntilEstimateSpinner(adapter);
            }

            @Override
            public void onError(Throwable e) {
                List<VM_Estimate> v = new ArrayList<>();
                v.add(new VM_Estimate(0, context.getString(R.string.Error)));
                ArrayAdapter<VM_Estimate> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, v);
                s_tenderNotificationFragment.onItemUntilEstimateSpinner(adapter);
            }
        });
    }

    //در اینجا تنظیمات اولیه دیالوگ تاریخ فارسی انجام می شود و سپس به ویو ارسال می شود
    public PersianDatePickerDialog getDatePicker() {

        PersianDatePickerDialog picker = new PersianDatePickerDialog(context)
                .setPositiveButtonString(context.getString(R.string.ok))
                .setNegativeButton(context.getString(R.string.NeverMind))
                .setTodayButton(context.getString(R.string.Today))
                .setTodayButtonVisible(true)
                .setInitDate(initDate)
                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                .setMinYear(1300)
                .setActionTextColor(Color.GRAY);

        return picker;
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

        if (dispose_getFromEstimate != null) {
            dispose_getFromEstimate.dispose();
        }

        if (dispose_getUntilEstimate != null) {
            dispose_getUntilEstimate.dispose();
        }
    }
}
