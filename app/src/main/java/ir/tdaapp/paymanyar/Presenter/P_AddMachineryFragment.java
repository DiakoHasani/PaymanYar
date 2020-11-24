package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Enums.AdTypeCondition;
import ir.tdaapp.paymanyar.Model.Enums.FileUploadAnalizeTenderType;
import ir.tdaapp.paymanyar.Model.Enums.StepsAddPower;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_AdTypeMachinery;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Machineries;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_States;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_PowerSupply;
import ir.tdaapp.paymanyar.Model.Services.S_AddMachineryFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_AdTypeMachinery;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FileUploadAnalizeTender;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_MachinerySpinner;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PostMachinery;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PostPowerSupply;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_ProvincesAndCities;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;

/**
 * مربوط به افزودن ماشین آلات
 **/
public class P_AddMachineryFragment {

    Context context;
    S_AddMachineryFragment s_addMachineryFragment;
    Tbl_AdTypeMachinery tbl_adTypeMachinery;
    Tbl_States tbl_states;
    Tbl_Machineries tbl_machineries;
    Api_PowerSupply api_powerSupply;
    Disposable dispose_addItem, dispose_getDetailItem, dispose_getMachineries;
    List<VM_ProvincesAndCities> states;

    public P_AddMachineryFragment(Context context, S_AddMachineryFragment s_addMachineryFragment) {
        this.context = context;
        this.s_addMachineryFragment = s_addMachineryFragment;

        tbl_machineries = new Tbl_Machineries(context);
        tbl_adTypeMachinery = new Tbl_AdTypeMachinery(context);
        tbl_states = new Tbl_States(context);
        api_powerSupply = new Api_PowerSupply();
        states = tbl_states.getProvincesOrCities(0);
    }

    public void start() {
        getProvinces();
        getAdTypeMachineries();
        getMachineries();
        getDefaultFileUploadValues();

        //در اینجا دکمه ارتقا آگهی فعال یا غیر فعال می شود
        s_addMachineryFragment.enableUpgradeOrder(s_addMachineryFragment.getIdItem() != 0);

        //در اینجا دکمه افزودن فعال یا غیر فعال می شود
        s_addMachineryFragment.enableShowSteps(s_addMachineryFragment.getIdItem() == 0);

        if (s_addMachineryFragment.getIdItem() != 0) {
            getDetailItem();
        }
    }

    /**
     * در اینجا لیست استان ها گرفته می شود
     **/
    public void getProvinces() {
        ArrayAdapter<VM_ProvincesAndCities> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, states);
        s_addMachineryFragment.getProvinces(adapter);
    }

    /**
     * در اینجا لیست شهرها گرفته می شود
     **/
    public void getCities(int parentId) {
        if (parentId != 0) {
            ArrayAdapter<VM_ProvincesAndCities> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, tbl_states.getProvincesOrCities(parentId));
            s_addMachineryFragment.getCities(adapter);
        } else {
            List<VM_ProvincesAndCities> cities = new ArrayList<>();
            cities.add(new VM_ProvincesAndCities(0, context.getString(R.string.City), 0));
            ArrayAdapter<VM_ProvincesAndCities> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, cities);
            s_addMachineryFragment.getCities(adapter);
        }
    }

    /**
     * در اینجا دسته ماشین آلات گرفته می شود
     **/
    public void getMachineries() {

        Single<String[]> data = api_powerSupply.getMachinerysTitle();
        dispose_getMachineries = data.subscribeWith(new DisposableSingleObserver<String[]>() {
            @Override
            public void onSuccess(String[] vals) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.select_dialog_item, vals);
                s_addMachineryFragment.getMachineries(adapter);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * در اینجا لیست نوع آگهی گرفته می شود
     **/
    public void getAdTypeMachineries() {
        ArrayAdapter<VM_AdTypeMachinery> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, tbl_adTypeMachinery.getAdTypes());
        s_addMachineryFragment.getAdTypes(adapter);
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
        s_addMachineryFragment.defaultValueFileUpload(vals);
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
            s_addMachineryFragment.onNotValidFile(context.getString(R.string.Format_Your_File_Not_Valid));
        } else {
            /////////در اینجا حجم فایل بررسی می شود
            // در اینجا مقدار بایت گرفته می شود
            long fileSizeInBytes = file.length();
            // در اینجا مقدار کیلوبایت گرفته می شود
            long fileSizeInKB = fileSizeInBytes / 1024;
            //  در اینجا مقدار مگابایت گرفته می شود
            long fileSizeInMB = fileSizeInKB / 1024;
            if (fileSizeInMB > 10) {
                s_addMachineryFragment.onNotValidFile(context.getString(R.string.Maximum_Length_File_10MB));
            } else {
                s_addMachineryFragment.onValidFile(val, file);
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
     * در اینجا جزئیات آگهی گرفته می شود
     **/
    public void getDetailItem() {
        s_addMachineryFragment.onLoadingGetDetail(true);

        Single<VM_PostMachinery> data = api_powerSupply.getDetailMyMachinery(s_addMachineryFragment.getIdItem(), context);
        dispose_getDetailItem = data.subscribeWith(new DisposableSingleObserver<VM_PostMachinery>() {
            @Override
            public void onSuccess(VM_PostMachinery powerSupply) {
                s_addMachineryFragment.onDetailData(powerSupply);
                s_addMachineryFragment.onLoadingGetDetail(false);
            }

            @Override
            public void onError(Throwable e) {
                s_addMachineryFragment.onErrorGetDetail(Error.GetErrorVolley(e.toString()));
            }
        });
    }

    /**
     * در اینجا آیتم اضافه می شود
     **/
    public void addItem() {

        if (s_addMachineryFragment.checkValidation()) {
            s_addMachineryFragment.isValid();

            if (((MainActivity) context).getTbl_user().hasAccount(context)) {

                s_addMachineryFragment.onLoading(true);
                s_addMachineryFragment.disableAnimationAllSteps();

                //در اینجا عکس ها آپلود می شوند بعد آدرس های آن را گرفته و برای ارسال به سرور در ویومدل ست می شوند
                api_powerSupply.uploadFile(s_addMachineryFragment.getUrlFiles(), urls -> {
                    VM_PostMachinery input = s_addMachineryFragment.getInputUser();
                    input.setImages(urls);

                    Single<VM_Message> data = api_powerSupply.addMachinery(input);

                    dispose_addItem = data.subscribeWith(new DisposableSingleObserver<VM_Message>() {
                        @Override
                        public void onSuccess(VM_Message message) {
                            if (message.isResult()) {
                                s_addMachineryFragment.onAnimationStep(StepsAddPower.Check_The_Ad, true);
                                s_addMachineryFragment.onSuccess();
                            } else {
                                Toasty.error(context, message.getMessage(), Toast.LENGTH_SHORT,true).show();
                                s_addMachineryFragment.onLoading(false);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            s_addMachineryFragment.onLoading(false);
                            s_addMachineryFragment.onError(Error.GetErrorVolley(e.toString()));
                        }
                    });
                });

            } else {
                s_addMachineryFragment.noAccount();
            }

        } else {
            s_addMachineryFragment.notValid();
        }

    }

    /**
     * در اینجا براساس نوع آگهی پوزیشن آن برگشت داده می شود
     **/
    public int getPositionAdType(AdTypeCondition adTypeMachinery) {
        return tbl_adTypeMachinery.getPositionByName(adTypeMachinery);
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

        api_powerSupply.cancel(tag, context);

        if (dispose_addItem != null) {
            dispose_addItem.dispose();
        }

        if (dispose_getDetailItem != null) {
            dispose_getDetailItem.dispose();
        }
    }
}
