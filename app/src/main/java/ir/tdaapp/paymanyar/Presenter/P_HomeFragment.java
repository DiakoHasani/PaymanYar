package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import androidx.core.content.FileProvider;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.BuildConfig;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Setting;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_Home;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_User;
import ir.tdaapp.paymanyar.Model.Services.S_HomeFragment;
import ir.tdaapp.paymanyar.Model.Services.resultVersionApp_Sql;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Home;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_HomeSlider;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_UpdateApp;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;

public class P_HomeFragment {

    S_HomeFragment s_homeFragment;
    Context context;
    Disposable dispose_getVals, dispose_setSliderItem, dispose_validationUser;
    Api_Home api_home;
    Tbl_Setting tbl_setting;
    boolean SliderNext;
    boolean started;
    Handler handler;
    Api_User api_user;

    public P_HomeFragment(S_HomeFragment s_homeFragment, Context context) {
        this.s_homeFragment = s_homeFragment;
        this.context = context;
        api_home = new Api_Home();
        tbl_setting = new Tbl_Setting(context);
        api_user = new Api_User();
    }

    //با فراخوانی این متد عملیات گرفتن داده ها شروع می شود
    public void start() {

        validationUser();

        s_homeFragment.OnStart();
        s_homeFragment.onHideAll();
        getVals();

        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        started = false;
        handler = new Handler();
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
                    s_homeFragment.onShowSlider(true);
                    s_homeFragment.onFinish();
                    setUpdates(vm_home.getUpdates());
                });
    }

    //در اینجا آپدیت ها یکی یکی گرفته می شوند و عملیات خود را انجام می دهیم
    void setUpdates(List<VM_UpdateApp> updates) {

        tbl_setting.setUpdates(updates, new resultVersionApp_Sql() {
            @Override
            public void updateApp(boolean update, boolean hadUpdate) {
                //اگر برنامه نیاز به آپدیت داشته باشد شرط زیر اجرا می شود
                if (update) {
                    s_homeFragment.onUpdateApp(hadUpdate);
                }
            }

            @Override
            public void clearData(boolean clear) {
                //اگر شرط زیر درست باشد اپلیکیشن باید کلیر دیتا شود
                if (clear) {
                    clearApplicationData();
                }
            }
        });
    }

    //با فراخوانی این متد تمامی عملیات این پرزنتر لغو می شود این متد زمانی که فرگمنت ما بسته شود فراخوانی می شود
    public void Cancel(String TAG) {
        //با بسته شدن صفحه تمامی عملیات وولی لغو می شوند
        api_home.Cancel(TAG, context);

        api_user.cancel(TAG, context);

        //در اینجا آرایکس جاوا مربوط به گرفتن اطلاعات از سرور لغو می شود
        if (dispose_getVals != null) {
            dispose_getVals.dispose();
        }

        if (dispose_setSliderItem != null) {
            dispose_setSliderItem.dispose();
        }

        if (dispose_validationUser != null) {
            dispose_validationUser.dispose();
        }
    }

    //برای پاک کردن حافظه دیتابیس
    public void clearApplicationData() {
        File cacheDirectory = context.getCacheDir();
        File applicationDirectory = new File(cacheDirectory.getParent());
        if (applicationDirectory.exists()) {
            String[] fileNames = applicationDirectory.list();
            for (String fileName : fileNames) {
                if (!fileName.equals("lib")) {
                    deleteFile(new File(applicationDirectory, fileName));
                }
            }
        }
    }

    public static boolean deleteFile(File file) {
        boolean deletedAll = true;
        if (file != null) {
            if (file.isDirectory()) {
                String[] children = file.list();
                for (int i = 0; i < children.length; i++) {
                    deletedAll = deleteFile(new File(file, children[i])) && deletedAll;
                }
            } else {
                deletedAll = file.delete();
            }
        }

        return deletedAll;
    }

    public void startSlider() {
        started = true;
        handler.postDelayed(runnable, 4000);
    }

    public void resetSlider() {
        handler.removeCallbacks(runnable);
        startSlider();
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {

                //در اینجا معلوم می شود که اسلایدر در کدام پیج است
                int PagingSliderPosition = s_homeFragment.onGetCurrentSlider();

                //در اینجا چک می شود که اسلایدر در صفحه اول است اگر شرط درست باشد به صفحه بعد می رود
                if (PagingSliderPosition == 0) {
                    s_homeFragment.onSetCurrentSlider(s_homeFragment.onGetItem(+1), true);
                    SliderNext = true;
                }
                //در اینجا چک می شود که اگر اسلایدر در صفحه آخر است به صفحه قبل بر می گردد
                else if (PagingSliderPosition == s_homeFragment.onGetCountSlider() - 1) {
                    s_homeFragment.onSetCurrentSlider(s_homeFragment.onGetItem(-1), true);
                    SliderNext = false;
                } else {
                    //در اینجا اسلایدر به صفحه بعد می رود
                    if (SliderNext == true) {
                        s_homeFragment.onSetCurrentSlider(s_homeFragment.onGetItem(+1), true);
                    }
                    //در اینجا اسلایدر به صفحه قبل می رود
                    else {
                        s_homeFragment.onSetCurrentSlider(s_homeFragment.onGetItem(-1), true);
                    }
                }
                if (started) {
                    startSlider();
                }

            }catch (Exception e){}
        }
    };

    public void ShareApplication() {
        ApplicationInfo app = context.getApplicationInfo();
        String filePath = app.sourceDir;

        Intent intent = new Intent(Intent.ACTION_SEND);

        // MIME of .apk is "application/vnd.android.package-archive".
        // but Bluetooth does not accept this. Let's use "*/*" instead.
        intent.setType("*/*");

        // Append file and send Intent
        File originalApk = new File(filePath);

        try {
            //Make new directory in new location=
            File tempFile = new File(context.getExternalCacheDir() + "/ExtractedApk");
            //If directory doesn't exists create new
            if (!tempFile.isDirectory())
                if (!tempFile.mkdirs())
                    return;
            //Get application's name and convert to lowercase
            tempFile = new File(tempFile.getPath() + "/" + context.getString(app.labelRes).replace(" ", "").toLowerCase() + ".apk");
            //If file doesn't exists create new
            if (!tempFile.exists()) {
                if (!tempFile.createNewFile()) {
                    return;
                }
            }
            //Copy file to new location
            InputStream in = new FileInputStream(originalApk);
            OutputStream out = new FileOutputStream(tempFile);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            System.out.println("File copied.");
            //Open share dialog
//          intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(tempFile));
            Uri photoURI = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", tempFile);
//          intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(tempFile));
            intent.putExtra(Intent.EXTRA_STREAM, photoURI);
            context.startActivity(Intent.createChooser(intent, "Share app via"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //در اینجا چک می شود کاربر حق استفاده از این اکانت را دارد یا خیر
    void validationUser() {

        int userId = ((MainActivity) context).getTbl_user().getUserId(context);
        String api_Key = ((MainActivity) context).getTbl_notification().getToken(context);

        if (userId != 0) {
            Single<Boolean> val = api_user.validationUser(userId, api_Key);
            dispose_validationUser = val.subscribeWith(new DisposableSingleObserver<Boolean>() {
                @Override
                public void onSuccess(Boolean res) {

                    //در اینجا حساب کاربر از گوشی پاک می شود
                    if (!res) {
                        ((MainActivity) context).getTbl_user().add(context, 0);
                        s_homeFragment.onShowMenuLoginNavigation();
                    }
                }

                @Override
                public void onError(Throwable e) {

                }
            });
        }

    }
}
