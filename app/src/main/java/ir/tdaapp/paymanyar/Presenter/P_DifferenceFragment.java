package ir.tdaapp.paymanyar.Presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.obsez.android.lib.filechooser.ChooserDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Enums.FileUploadAnalizeTenderType;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_Tender;
import ir.tdaapp.paymanyar.Model.Services.S_DifferenceFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_AnaliseInfo;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FileUploadAnalizeTender;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_InputAnalizeTender;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;

//مربوط به صفحه مابه التفاوت
public class P_DifferenceFragment {
    Context context;
    S_DifferenceFragment s_differenceFragment;
    Api_Tender api_tender;
    Disposable dispose_addOrder, dispose_getDetailItem;
    Disposable dispose_downloadFile;

    public P_DifferenceFragment(Context context, S_DifferenceFragment s_differenceFragment) {
        this.context = context;
        this.s_differenceFragment = s_differenceFragment;
        api_tender = new Api_Tender();
    }

    public void start() {
        s_differenceFragment.OnStart();
        setValueFiles();
        s_differenceFragment.onSetDetailsData();
    }

    void setValueFiles() {
        List<VM_FileUploadAnalizeTender> vals = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            VM_FileUploadAnalizeTender v = new VM_FileUploadAnalizeTender();
            v.setId(i);
            v.setPath("");
            v.setType(FileUploadAnalizeTenderType.empty);
            vals.add(v);
        }

        s_differenceFragment.onValuesFiles(vals);
    }

    //در اینجا کاربر یک فایل انتخاب می کند
    public void openFile(Activity activity, VM_FileUploadAnalizeTender val) {
        Dexter.withActivity(activity).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(
                new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        new ChooserDialog(activity)
                                .withFilter(false, false, "rar", "zip", "pdf", "xlsx", "docx", "pptx", "jpg", "png", "jpeg")
                                .withResources(R.string.choseOneFile, R.string.select, R.string.cancel)
                                .withChosenListener((s, file) -> {
                                    s_differenceFragment.onSelectedFile(val, file);
                                })
                                .build().show();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }
        ).check();
    }

    //در اینجا چک می کند که فایل انتخاب شده ولید است یا خیر
    public void checkValidationFile(File file, VM_FileUploadAnalizeTender val) {
        Uri f = Uri.fromFile(file);
        String format = MimeTypeMap.getFileExtensionFromUrl(f.toString());

        //در اینجا فرمت را بررسی می کند
        boolean isValidFormat;
        switch (format) {
            case "rar":
            case "zip":
            case "pdf":
            case "xlsx":
            case "docx":
            case "pptx":
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
            s_differenceFragment.onNotValidFile(context.getString(R.string.Format_Your_File_Not_Valid));
        } else {
            /////////در اینجا حجم فایل بررسی می شود
            // در اینجا مقدار بایت گرفته می شود
            long fileSizeInBytes = file.length();
            // در اینجا مقدار کیلوبایت گرفته می شود
            long fileSizeInKB = fileSizeInBytes / 1024;
            //  در اینجا مقدار مگابایت گرفته می شود
            long fileSizeInMB = fileSizeInKB / 1024;
            if (fileSizeInMB > 10) {
                s_differenceFragment.onNotValidFile(context.getString(R.string.Maximum_Length_File_10MB));
            } else {
                s_differenceFragment.onValidFile(val, file);
            }
        }
    }

    //در اینجا نوع فایل برگشت داده می شود
    public FileUploadAnalizeTenderType getTypeFile(File file) {

        Uri f = Uri.fromFile(file);
        String format = MimeTypeMap.getFileExtensionFromUrl(f.toString());

        FileUploadAnalizeTenderType type;
        switch (format) {
            case "rar":
                type = FileUploadAnalizeTenderType.rar;
                break;
            case "zip":
                type = FileUploadAnalizeTenderType.zip;
                break;
            case "pdf":
                type = FileUploadAnalizeTenderType.pdf;
                break;
            case "xlsx":
                type = FileUploadAnalizeTenderType.excel;
                break;
            case "docx":
                type = FileUploadAnalizeTenderType.word;
                break;
            case "pptx":
                type = FileUploadAnalizeTenderType.powerPoint;
                break;
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

    public FileUploadAnalizeTenderType getTypeFile(String format) {
        FileUploadAnalizeTenderType type;
        switch (format) {
            case "rar":
                type = FileUploadAnalizeTenderType.rar;
                break;
            case "zip":
                type = FileUploadAnalizeTenderType.zip;
                break;
            case "pdf":
                type = FileUploadAnalizeTenderType.pdf;
                break;
            case "xlsx":
                type = FileUploadAnalizeTenderType.excel;
                break;
            case "docx":
                type = FileUploadAnalizeTenderType.word;
                break;
            case "pptx":
                type = FileUploadAnalizeTenderType.powerPoint;
                break;
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

    //در اینجا سفارش جدید به سمت سرور ارسال می شود
    public void addOrder() {

        if (s_differenceFragment.isValidInputs()) {
            //اگر کاربر حساب کاربری داشته باشد اجازه افزودن سفارش را می دهد
            if (((MainActivity) context).getTbl_user().hasAccount(context)) {

                s_differenceFragment.onLoading(true);
                //در اینجا فایل ها آپلود می شوند و آدرس آن ها برگشت داده می شوند
                api_tender.uploadFiles(s_differenceFragment.onGetUrlPaths(), urls -> {
                    //در اینجا مقادیر ورودی گرفته می شوند
                    VM_InputAnalizeTender input = s_differenceFragment.onGetInputAnalize(urls);

                    Single<VM_Message> data = api_tender.postOrderDifference(input);
                    dispose_addOrder = data.subscribeWith(new DisposableSingleObserver<VM_Message>() {
                        @Override
                        public void onSuccess(VM_Message message) {
                            if (message.isResult()) {
                                s_differenceFragment.onSuccess();
                            } else {
                                s_differenceFragment.onLoading(false);
                                Toast.makeText(context, message.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            s_differenceFragment.onLoading(false);
                            s_differenceFragment.onError(Error.GetErrorVolley(e.toString()));
                        }
                    });
                });

            } else {
                s_differenceFragment.onNoAccount();
            }
        } else {
            s_differenceFragment.onNotValid();
        }
    }

    //در اینجا جزئیات سفارش گرفته می شود
    public void getDetailItem() {

        s_differenceFragment.onShowReloadDialog(true);
        s_differenceFragment.onLoadingGetItem(true);

        Single<VM_AnaliseInfo> val = api_tender.getOrderAnaliseInfo(s_differenceFragment.onItemId());
        dispose_getDetailItem = val.subscribeWith(new DisposableSingleObserver<VM_AnaliseInfo>() {
            @Override
            public void onSuccess(VM_AnaliseInfo vm_analiseInfo) {
                s_differenceFragment.onShowReloadDialog(false);
                s_differenceFragment.onSetAnaliseInfo(vm_analiseInfo);
            }

            @Override
            public void onError(Throwable e) {
                s_differenceFragment.onShowReloadDialog(false);
                s_differenceFragment.onErrorGetItem(Error.GetErrorVolley(e.toString()));
            }
        });
    }

    public void downloadFile(String title) {

        s_differenceFragment.onLoadingDownloadFile(true);

        Single<Boolean> val = api_tender.downloadOrder(s_differenceFragment.onGetFileName(), title);

        dispose_downloadFile = val.subscribeWith(new DisposableSingleObserver<Boolean>() {
            @Override
            public void onSuccess(Boolean a) {
                new Handler(Looper.getMainLooper()).post(() -> {
                    s_differenceFragment.onLoadingDownloadFile(false);
                    s_differenceFragment.onShowFile(title);
                });
            }

            @Override
            public void onError(Throwable e) {
                new Handler(Looper.getMainLooper()).post(() -> {
                    s_differenceFragment.onLoadingDownloadFile(false);
                    s_differenceFragment.onErrorDownloadFile(e);
                });
            }
        });

    }

    public void cancel(String tag) {

        api_tender.Cancel(tag, context);

        if (dispose_addOrder != null) {
            dispose_addOrder.dispose();
        }

        if (dispose_getDetailItem != null) {
            dispose_getDetailItem.dispose();
        }

        if (dispose_downloadFile != null) {
            dispose_downloadFile.dispose();
        }
    }
}
