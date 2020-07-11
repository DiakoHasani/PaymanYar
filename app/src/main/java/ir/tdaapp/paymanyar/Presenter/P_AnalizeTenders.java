package ir.tdaapp.paymanyar.Presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
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

import ir.tdaapp.paymanyar.Model.Enums.FileUploadAnalizeTenderType;
import ir.tdaapp.paymanyar.Model.Services.S_AnalizeTenders;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FileUploadAnalizeTender;
import ir.tdaapp.paymanyar.R;

public class P_AnalizeTenders {

    Context context;
    S_AnalizeTenders s_analizeTenders;

    public P_AnalizeTenders(Context context, S_AnalizeTenders s_analizeTenders) {
        this.context = context;
        this.s_analizeTenders = s_analizeTenders;
    }

    public void start() {
        s_analizeTenders.OnStart();
        setValueFiles();
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

        s_analizeTenders.onValuesFiles(vals);
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
                                    s_analizeTenders.onSelectedFile(val, file);
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
            s_analizeTenders.onNotValidFile(context.getString(R.string.Format_Your_File_Not_Valid));
        }else{
            /////////در اینجا حجم فایل بررسی می شود
            // در اینجا مقدار بایت گرفته می شود
            long fileSizeInBytes = file.length();
            // در اینجا مقدار کیلوبایت گرفته می شود
            long fileSizeInKB = fileSizeInBytes / 1024;
            //  در اینجا مقدار مگابایت گرفته می شود
            long fileSizeInMB = fileSizeInKB / 1024;
            if (fileSizeInMB > 10) {
                s_analizeTenders.onNotValidFile(context.getString(R.string.Maximum_Length_File_10MB));
            }else{
                s_analizeTenders.onValidFile(val, file);
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
}
