package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Enums.AdTypeMaterial;
import ir.tdaapp.paymanyar.Model.Enums.FileUploadAnalizeTenderType;
import ir.tdaapp.paymanyar.Model.Enums.StepsAddPower;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_AdTypeMaterial;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Material;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_States;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_PowerSupply;
import ir.tdaapp.paymanyar.Model.Services.S_AddMaterialFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_AdTypeMaterial;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FileUploadAnalizeTender;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_MaterialSpinner;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PostMaterial;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_ProvincesAndCities;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;

public class P_AddMaterialFragment {
    Context context;
    S_AddMaterialFragment s_addMaterialFragment;
    Tbl_States tbl_states;
    Tbl_Material tbl_material;
    Api_PowerSupply api_powerSupply;
    Disposable dispose_addItem, dispose_getDetailItem, dispose_getMaterials;
    List<VM_ProvincesAndCities> states;
    Tbl_AdTypeMaterial tbl_adTypeMaterial;

    public P_AddMaterialFragment(Context context, S_AddMaterialFragment s_addMaterialFragment) {
        this.context = context;
        this.s_addMaterialFragment = s_addMaterialFragment;

        tbl_states = new Tbl_States(context);
        tbl_material = new Tbl_Material(context);
        api_powerSupply = new Api_PowerSupply();
        tbl_adTypeMaterial = new Tbl_AdTypeMaterial(context);
        states = tbl_states.getProvincesOrCities(0);
    }

    public void start() {
        getProvinces();
        geAdTypes();
        getMaterials();
        getDefaultFileUploadValues();

        //در اینجا دکمه ارتقا آگهی فعال یا غیر فعال می شود
        s_addMaterialFragment.enableUpgradeOrder(s_addMaterialFragment.getIdItem() != 0);

        //در اینجا دکمه افزودن فعال یا غیر فعال می شود
        s_addMaterialFragment.enableShowSteps(s_addMaterialFragment.getIdItem() == 0);

        if (s_addMaterialFragment.getIdItem() != 0) {
            getDetailItem();
        }
    }

    /**
     * در اینجا لیست استان ها گرفته می شود
     **/
    public void getProvinces() {
        ArrayAdapter<VM_ProvincesAndCities> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, states);
        s_addMaterialFragment.getProvinces(adapter);
    }

    /**
     * در اینجا لیست شهرها گرفته می شود
     **/
    public void getCities(int parentId) {
        if (parentId != 0) {
            ArrayAdapter<VM_ProvincesAndCities> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, tbl_states.getProvincesOrCities(parentId));
            s_addMaterialFragment.getCities(adapter);
        } else {
            List<VM_ProvincesAndCities> cities = new ArrayList<>();
            cities.add(new VM_ProvincesAndCities(0, context.getString(R.string.City), 0));
            ArrayAdapter<VM_ProvincesAndCities> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, cities);
            s_addMaterialFragment.getCities(adapter);
        }
    }

    /**
     * در اینجا دسته مصالح گرفته می شود
     **/
    public void getMaterials() {

        Single<String[]> data = api_powerSupply.getMaterialsTitle();
        dispose_getMaterials = data.subscribeWith(new DisposableSingleObserver<String[]>() {
            @Override
            public void onSuccess(String[] vals) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.select_dialog_item, vals);
                s_addMaterialFragment.getMaterials(adapter);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * در اینجا نوع آگهی گرفته می شود
     **/
    public void geAdTypes() {
        ArrayAdapter<VM_AdTypeMaterial> adapter = new ArrayAdapter<>(context, R.layout.spinner_item2, tbl_adTypeMaterial.getVals());
        s_addMaterialFragment.getAdTypes(adapter);
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
        s_addMaterialFragment.defaultValueFileUpload(vals);
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
            s_addMaterialFragment.onNotValidFile(context.getString(R.string.Format_Your_File_Not_Valid));
        } else {
            /////////در اینجا حجم فایل بررسی می شود
            // در اینجا مقدار بایت گرفته می شود
            long fileSizeInBytes = file.length();
            // در اینجا مقدار کیلوبایت گرفته می شود
            long fileSizeInKB = fileSizeInBytes / 1024;
            //  در اینجا مقدار مگابایت گرفته می شود
            long fileSizeInMB = fileSizeInKB / 1024;
            if (fileSizeInMB > 10) {
                s_addMaterialFragment.onNotValidFile(context.getString(R.string.Maximum_Length_File_10MB));
            } else {
                s_addMaterialFragment.onValidFile(val, file);
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
     * در اینجا پوزیشن نوع آگهی بر اساس نام آن برگشت داده می شود
     **/
    public int getPositionAdType(AdTypeMaterial adType) {
        return tbl_adTypeMaterial.getPositionByName(adType);
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

    /**
     * در اینجا آیتم اضافه می شود
     **/
    public void addItem() {

        if (s_addMaterialFragment.checkValidation()) {
            s_addMaterialFragment.isValid();

            if (((MainActivity) context).getTbl_user().hasAccount(context)) {

                s_addMaterialFragment.onLoading(true);
                s_addMaterialFragment.disableAnimationAllSteps();

                //در اینجا عکس ها آپلود می شوند بعد آدرس های آن را گرفته و برای ارسال به سرور در ویومدل ست می شوند
                api_powerSupply.uploadFile(s_addMaterialFragment.getUrlFiles(), urls -> {
                    VM_PostMaterial input = s_addMaterialFragment.getInputUser();
                    input.setImages(urls);

                    Single<VM_Message> data = api_powerSupply.addMaterial(input);

                    dispose_addItem = data.subscribeWith(new DisposableSingleObserver<VM_Message>() {
                        @Override
                        public void onSuccess(VM_Message message) {
                            if (message.isResult()) {
                                s_addMaterialFragment.onAnimationStep(StepsAddPower.Check_The_Ad, true);
                                s_addMaterialFragment.onSuccess();
                            } else {
                                Toast.makeText(context, message.getMessage(), Toast.LENGTH_SHORT).show();
                                s_addMaterialFragment.onLoading(false);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            s_addMaterialFragment.onLoading(false);
                            s_addMaterialFragment.onError(Error.GetErrorVolley(e.toString()));
                        }
                    });
                });

            } else {
                s_addMaterialFragment.noAccount();
            }

        } else {
            s_addMaterialFragment.notValid();
        }

    }

    /**
     * در اینجا جزئیات آگهی گرفته می شود
     **/
    public void getDetailItem() {
        s_addMaterialFragment.onLoadingGetDetail(true);

        Single<VM_PostMaterial> data = api_powerSupply.getDetailMyMaterial(s_addMaterialFragment.getIdItem(), context);
        dispose_getDetailItem = data.subscribeWith(new DisposableSingleObserver<VM_PostMaterial>() {
            @Override
            public void onSuccess(VM_PostMaterial material) {
                s_addMaterialFragment.onDetailData(material);
                s_addMaterialFragment.onLoadingGetDetail(false);
            }

            @Override
            public void onError(Throwable e) {
                s_addMaterialFragment.onErrorGetDetail(Error.GetErrorVolley(e.toString()));
            }
        });
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

        if (dispose_getMaterials != null) {
            dispose_getMaterials.dispose();
        }
    }

}
