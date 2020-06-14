package ir.tdaapp.paymanyar.Presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.util.List;

import androidx.core.content.FileProvider;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.li_image.ImagesCodes.SaveImageToMob;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_City;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Major;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Tender;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_Tender;
import ir.tdaapp.paymanyar.Model.Services.S_DetailsTenderFragment;
import ir.tdaapp.paymanyar.Model.Services.addTender;
import ir.tdaapp.paymanyar.Model.Services.removeTender;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailsTender;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FilterTenderNotification;
import ir.tdaapp.paymanyar.R;

public class P_DetailsTenderFragment {

    Context context;
    S_DetailsTenderFragment s_detailsTenderDialog;
    Api_Tender api_tender;
    Disposable dispose_getDetails;
    Tbl_Tender tbl_tender;
    Tbl_City tbl_city;
    Tbl_Major tbl_major;

    public P_DetailsTenderFragment(Context context, S_DetailsTenderFragment s_detailsTenderDialog) {
        this.context = context;
        this.s_detailsTenderDialog = s_detailsTenderDialog;
        api_tender = new Api_Tender();
        tbl_tender = new Tbl_Tender(context);
        tbl_city = new Tbl_City(context);
        tbl_major = new Tbl_Major(context);
    }

    public void start(VM_FilterTenderNotification filter) {
        s_detailsTenderDialog.OnStart();
        s_detailsTenderDialog.onHideAll();
        s_detailsTenderDialog.onLoading(true);

        getDetails(filter);
    }

    //در اینجا جزئیات مناقصه از سرور گرفته می شود
    void getDetails(VM_FilterTenderNotification filter) {

        Single<VM_DetailsTender> data = api_tender.getDetailsTender(filter, tbl_tender);

        dispose_getDetails = data.subscribeWith(new DisposableSingleObserver<VM_DetailsTender>() {
            @Override
            public void onSuccess(VM_DetailsTender detailsTender) {

                //اگر مناقصه پولی باشد و کاربر موجودی داشته باشد می تواند آن را نگاه کند در غیر این صورت آیتم ویژه مشترکان نمایش داده می شود
                if (detailsTender.isStatus()) {

                    //در اینجا تمامی آیتم ها مخفی می شوند
                    s_detailsTenderDialog.onHideAll();

                    //در اینجا جزئیات مناقصه ها در المنت ها ست می شود
                    s_detailsTenderDialog.onGetDetail(detailsTender);

                    //برای زمانی که عملیات ما به پایان رسیده باشد
                    s_detailsTenderDialog.onFinish();
                } else {
                    s_detailsTenderDialog.onHideAll();
                    s_detailsTenderDialog.onShowSubscribers();
                }

                //در اینجا دکمه آیتم بعدی ست می شود
                if (!s_detailsTenderDialog.isFavoritePage()) {
                    s_detailsTenderDialog.onGetNextTender(detailsTender.getNextTenderId());
                } else {
                    s_detailsTenderDialog.onGetNextTender(tbl_tender.getNextTenderIdFavorite(filter.getTenderId()));
                }

                //در اینجا دکمه آیتم قبلی ست می شود
                if (!s_detailsTenderDialog.isFavoritePage()){
                    s_detailsTenderDialog.onGetPrevTender(detailsTender.getBeforeTenderId());
                }else{
                    s_detailsTenderDialog.onGetPrevTender(tbl_tender.getPrevTenderIdFavorite(filter.getTenderId()));
                }

                s_detailsTenderDialog.isFevorit(detailsTender.isFevorit());

            }

            @Override
            public void onError(Throwable e) {
                s_detailsTenderDialog.onHideAll();
                s_detailsTenderDialog.onError(Error.GetErrorVolley(e.toString()));
            }
        });
    }

    //در اینجا یک مناقصه به لیست علاقه مندی ها اضافه می شود
    public void AddFavorit(String id, addTender tender) {
        tbl_tender.Add(id, tender);
    }

    public void RemoveFevorit(String id, removeTender t) {
        tbl_tender.remove(id, t);
    }

    //ر اینجا نام شهر براساس آیدی گرفته می شود
    public String getCityTitle(int id) {
        return tbl_city.getTitleById(id);
    }

    //ر اینجا نام رشته تحصیلی براساس آیدی گرفته می شود
    public String getMajorTitle(int id) {
        return tbl_major.getTitleById(id);
    }

    //در اینجا ازصفحه اسکرین شات می گیرد
    public Bitmap takeScreenshot(Activity activity) {
        View rootView = activity.findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    //در اینجا اسکرین شات صفحه را به اشتراک می زارد
    public void share(Activity activity, String url) {

        Dexter.withActivity(activity).withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE).withListener(
                new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {

                        File imagePath = new File(SaveImageToMob.SaveImageCamera("screenShot.png", takeScreenshot(activity)));

//                        Uri uri = Uri.fromFile(imagePath);
                        Uri uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", imagePath);
                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("image/*");
                        String shareBody = url;
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, context.getString(R.string.See_the_Following_Tender_in_the_Contractor));
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);

                        activity.startActivity(Intent.createChooser(sharingIntent, context.getString(R.string.share)));

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }
                }
        ).check();
    }

    public void Cancel(String Tag) {

        api_tender.Cancel(Tag, context);

        if (dispose_getDetails != null) {
            dispose_getDetails.dispose();
        }
    }
}
