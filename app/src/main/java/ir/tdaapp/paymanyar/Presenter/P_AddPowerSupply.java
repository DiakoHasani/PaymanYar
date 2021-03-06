package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Enums.AdType;
import ir.tdaapp.paymanyar.Model.Enums.FileUploadAnalizeTenderType;
import ir.tdaapp.paymanyar.Model.Enums.StepsAddPower;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_AdType;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Jobs;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_States;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_WorkExperiences;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_PowerSupply;
import ir.tdaapp.paymanyar.Model.Services.S_AddPowerSupply;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_AdType;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FileUploadAnalizeTender;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Job;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PostPowerSupply;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_ProvincesAndCities;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_WorkExperience;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;

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
    Api_PowerSupply api_powerSupply;
    Disposable dispose_addItem, dispose_getDetailItem, dispose_getJobs;
    List<VM_ProvincesAndCities> states;
    List<VM_WorkExperience> workExperiences;

    public P_AddPowerSupply(Context context, S_AddPowerSupply s_addPowerSupply) {
        this.context = context;
        this.s_addPowerSupply = s_addPowerSupply;

        tbl_states = new Tbl_States(context);
        tbl_workExperiences = new Tbl_WorkExperiences(context);
        tbl_jobs = new Tbl_Jobs(context);
        tbl_adType = new Tbl_AdType(context);

        api_powerSupply = new Api_PowerSupply();
        states = tbl_states.getProvincesOrCities(0);
        workExperiences = tbl_workExperiences.getWorkExperiences();
    }

    public void start() {
        getProvinces();
        getAdTypes();
        getJobs();
        getWorkExperiences();
        getDefaultFileUploadValues();

        //در اینجا دکمه ارتقا آگهی فعال یا غیر فعال می شود
        s_addPowerSupply.enableUpgradeOrder(s_addPowerSupply.getIdItem() != 0);

        //در اینجا دکمه افزودن فعال یا غیر فعال می شود
        s_addPowerSupply.enableShowSteps(s_addPowerSupply.getIdItem() == 0);

        if (s_addPowerSupply.getIdItem() != 0) {
            getDetailItem();
        }
    }

    /**
     * در اینجا لیست استان ها گرفته می شود
     **/
    public void getProvinces() {
        ArrayAdapter<VM_ProvincesAndCities> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, states);
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
     **/
    public void getWorkExperiences() {
        ArrayAdapter<VM_WorkExperience> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, workExperiences);
        s_addPowerSupply.getWorkExperiences(adapter);
    }

    /**
     * در اینجا لیست شغل ها گرفته می شود
     **/
    public void getJobs() {
        Single<String[]> data = api_powerSupply.getJobsTitle();
        dispose_getJobs = data.subscribeWith(new DisposableSingleObserver<String[]>() {
            @Override
            public void onSuccess(String[] jobs) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.select_dialog_item, jobs);
                s_addPowerSupply.getJobs(adapter);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * در اینجا لیست نوع آگهی گرفته می شود
     **/
    public void getAdTypes() {
        ArrayAdapter<VM_AdType> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, tbl_adType.getAdTypes());
        s_addPowerSupply.getAdTypes(adapter);
    }

    /**
     * در اینجا داده های پیش فرض فایل آپلود ست می شود
     **/
    public void getDefaultFileUploadValues() {
        List<VM_FileUploadAnalizeTender> vals = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            VM_FileUploadAnalizeTender v = new VM_FileUploadAnalizeTender();
            v.setId(i);
            v.setPath("");
            v.setType(FileUploadAnalizeTenderType.empty);
            vals.add(v);
        }
        s_addPowerSupply.defaultValueFileUpload(vals);
    }

    //در اینجا چک می کند که فایل انتخاب شده ولید است یا خیر
    public void checkValidationFile(File file, VM_FileUploadAnalizeTender val) {
        Uri f = Uri.fromFile(file);
        String format = MimeTypeMap.getFileExtensionFromUrl(f.toString());

        //در اینجا فرمت را بررسی می کند
        boolean isValidFormat;
        switch (format) {
            case "jpg":
            case "png":
            case "jpeg":
                isValidFormat = true;
                break;
            default:
                isValidFormat = false;
                break;
        }
        if (!isValidFormat) {
            s_addPowerSupply.onNotValidFile(context.getString(R.string.Format_Your_File_Not_Valid));
        } else {
            /////////در اینجا حجم فایل بررسی می شود
            // در اینجا مقدار بایت گرفته می شود
            long fileSizeInBytes = file.length();
            // در اینجا مقدار کیلوبایت گرفته می شود
            long fileSizeInKB = fileSizeInBytes / 1024;
            //  در اینجا مقدار مگابایت گرفته می شود
            long fileSizeInMB = fileSizeInKB / 1024;
            if (fileSizeInMB > 10) {
                s_addPowerSupply.onNotValidFile(context.getString(R.string.Maximum_Length_File_10MB));
            } else {
                s_addPowerSupply.onValidFile(val, file);
            }
        }
    }

    //در اینجا نوع فایل برگشت داده می شود
    public FileUploadAnalizeTenderType getTypeFile(File file) {

        Uri f = Uri.fromFile(file);
        String format = MimeTypeMap.getFileExtensionFromUrl(f.toString());

        FileUploadAnalizeTenderType type;
        switch (format) {
            case "jpeg":
            case "jpg":
                type = FileUploadAnalizeTenderType.jpg;
                break;
            case "png":
                type = FileUploadAnalizeTenderType.png;
                break;
            default:
                type = FileUploadAnalizeTenderType.empty;
                break;
        }

        return type;
    }

    /**
     * در اینجا آیتم اضافه می شود
     **/
    public void addItem() {

        if (s_addPowerSupply.checkValidation()) {
            s_addPowerSupply.isValid();

            if (((MainActivity) context).getTbl_user().hasAccount(context)) {

                s_addPowerSupply.onLoading(true);
                s_addPowerSupply.disableAnimationAllSteps();

                //در اینجا عکس ها آپلود می شوند بعد آدرس های آن را گرفته و برای ارسال به سرور در ویومدل ست می شوند
                api_powerSupply.uploadFile(s_addPowerSupply.getUrlFiles(), urls -> {
                    VM_PostPowerSupply input = s_addPowerSupply.getInputUser();
                    input.setImages(urls);

                    Single<VM_Message> data = api_powerSupply.addPowerSupply(input);

                    dispose_addItem = data.subscribeWith(new DisposableSingleObserver<VM_Message>() {
                        @Override
                        public void onSuccess(VM_Message message) {
                            if (message.isResult()) {
                                s_addPowerSupply.onAnimationStep(StepsAddPower.Check_The_Ad, true);
                                s_addPowerSupply.onSuccess();
                            } else {
                                Toasty.error(context, message.getMessage(), Toast.LENGTH_SHORT,true).show();
                                s_addPowerSupply.onLoading(false);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            s_addPowerSupply.onLoading(false);
                            s_addPowerSupply.onError(Error.GetErrorVolley(e.toString()));
                        }
                    });
                });

            } else {
                s_addPowerSupply.noAccount();
            }

        } else {
            s_addPowerSupply.notValid();
        }

    }

    /**
     * در اینجا جزئیات آگهی گرفته می شود
     **/
    public void getDetailItem() {
        s_addPowerSupply.onLoadingGetDetail(true);

        Single<VM_PostPowerSupply> data = api_powerSupply.getDetailMyPowerSupply(s_addPowerSupply.getIdItem());
        dispose_getDetailItem = data.subscribeWith(new DisposableSingleObserver<VM_PostPowerSupply>() {
            @Override
            public void onSuccess(VM_PostPowerSupply powerSupply) {
                s_addPowerSupply.onDetailData(powerSupply);
                s_addPowerSupply.onLoadingGetDetail(false);
            }

            @Override
            public void onError(Throwable e) {
                s_addPowerSupply.onErrorGetDetail(Error.GetErrorVolley(e.toString()));
            }
        });
    }

    /**
     * در اینجا پوزیشن نوع آگهی برگشت داده می شود
     **/
    public int getPositionAdType(AdType adType) {
        return tbl_adType.getPositionByName(adType);
    }

    /**
     * در اینجا پوزیشن سابقه کار برگشت داده می شود
     **/
    public int getPositionWorkExperience(int id) {

        int position = 0;
        for (int i = 0; i < workExperiences.size(); i++) {
            if (workExperiences.get(i).getId() == id) {
                position = i;
                break;
            }
        }
        return position;
    }

    /**
     * در اینجا پوزیشن استان برگشت داده می شود
     **/
    public int getPositionState(int id) {

        int position = 0;

        for (int i = 0; i < states.size(); i++) {
            if (states.get(i).getId() == id) {
                position = i;
                break;
            }
        }

        return position;
    }

    /**
     * در اینجا پوزیشن شهر برگشت داده می شود
     **/
    public int getPositionCity(int id, int parent) {
        return tbl_states.getPositionByIdCity(id, parent);
    }

    public void cancel(String tag) {
        states = null;
        workExperiences = null;

        api_powerSupply.cancel(tag, context);

        if (dispose_addItem != null) {
            dispose_addItem.dispose();
        }

        if (dispose_getDetailItem != null) {
            dispose_getDetailItem.dispose();
        }

        if (dispose_getJobs != null) {
            dispose_getJobs.dispose();
        }
    }
}