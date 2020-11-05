package ir.tdaapp.paymanyar.View.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.bumptech.glide.manager.SupportRequestManagerFragment;
import com.flurry.android.FlurryAgent;

import java.util.List;

import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import io.reactivex.plugins.RxJavaPlugins;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Notification;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_User;
import ir.tdaapp.paymanyar.Model.Services.S_MainActivity;
import ir.tdaapp.paymanyar.Presenter.P_MainActivity;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Fragments.FilterFramesFragment;
import ir.tdaapp.paymanyar.View.Fragments.HomeFragment;
import ir.tdaapp.paymanyar.View.Fragments.LoginCodeFragment;
import ir.tdaapp.paymanyar.View.Fragments.LoginFragment;

public class MainActivity extends AppCompatActivity implements S_MainActivity {

    public final static String TAG = "MainActivity";
    public static boolean isActive = false;

    P_MainActivity p_mainActivity;
    private Tbl_Notification tbl_notification;
    private Tbl_User tbl_user;
    DisplayMetrics displayMetrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //اگر در آرایکس جاوا خطای رخ دهد کد زیر فراخوانی می شود
        RxJavaPlugins.setErrorHandler(throwable -> {
            sendErrorToServer("خطای آرایکس جاوا", throwable.toString());
        });

        findItem();
        implement();

        p_mainActivity.start();

        //زمانی که برنامه بسته باشد و کاربر روی نوتیفیکشن کلیک کند کد زیر را فراخوانی می کند
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if (extras != null) {
            if (extras.getString("key1") != null) {
                for (String key : extras.keySet()) {
                    if (key.equalsIgnoreCase("key1")) {
                        onAddFragment(new HomeFragment(), 0, 0, false, HomeFragment.TAG);
                        onAddFragment(new FilterFramesFragment(), R.anim.short_fadein, R.anim.short_fadeout, true, FilterFramesFragment.TAG);
                    }
                }
            } else {
                onAddFragment(new HomeFragment(), 0, 0, false, HomeFragment.TAG);
            }
        } else {
            onAddFragment(new HomeFragment(), 0, 0, false, HomeFragment.TAG);
        }

        //اینجا تنظیمات فلورای انجام می شود
        settingFlurry();
    }

    void findItem() {
    }

    void implement() {
        p_mainActivity = new P_MainActivity(getApplicationContext(), this);
        tbl_notification = new Tbl_Notification();
        tbl_user = new Tbl_User();

        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    }

    //در اینجا برنامه به صفحه اول بر می گردد
    public void backToHome() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();

        for (Fragment i : fragments) {
            if (!(i instanceof HomeFragment || i instanceof SupportRequestManagerFragment)) {
                getSupportFragmentManager().beginTransaction().remove(i).commit();
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        //زمانی که کاربر روی نوتیفیکیشن کلیک کند کد زیر فراخوانی می شود
        Bundle extras = intent.getExtras();
        if (extras != null) {
            for (String key : extras.keySet()) {
                if (key.equalsIgnoreCase("key1")) {
                    //در اینجا چک می کند که صفحه مناقصات کاربر باز است اگر باز باشد آن را ریست می کند
                    Fragment hasFragmentFilterFrames = getSupportFragmentManager().findFragmentByTag(FilterFramesFragment.TAG);
                    if (hasFragmentFilterFrames != null) {
                        ((FilterFramesFragment) hasFragmentFilterFrames).reset();
                    } else {
                        onAddFragment(new FilterFramesFragment(), R.anim.short_fadein, R.anim.short_fadeout, true, FilterFramesFragment.TAG);
                    }

                }
            }

        }
    }

    //در اینجا خطاهای برنامه به سمت سرور ارسال می شوند
    public void sendErrorToServer(String name, String text) {
        p_mainActivity.sendError(name, text);
    }

    //زمانی که اکتیویتی عملیات اولیه خود را انجام دهد متد زیر فراخوانی می شود
    @Override
    public void OnStart() {

    }

    //در این متد یک فرگمنت می گیرد و آن را نمایش می دهد
    //animEnter زمانی که فرگمنت بالا می آید با این انیمیشن نمایش داده می شود
    //animExit فرگمنت با این انیمیشن بسته می شود
    //اگر مقادیر ورودی های انیمیشن صفر باشد به منظور لازم نبودن نمایش انیمیشن می باشد
    //backStack در اینجا از ما می خواهد که فرگمنت را به پشته اضافه کند یا خیر
    //fragmentTAG زمانی که یک فرگمنت به پشته اضافه شد بااستفاده از این ورودی می توانیم به آن دسترسی داشته باشیم
    @Override
    public void onAddFragment(Fragment fragment,
                              @AnimatorRes @AnimRes int animEnter,
                              @AnimatorRes @AnimRes int animExit,
                              boolean backStack, String fragmentTAG) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        //اگر ورودی های انیمیشن غیر صفر باشند انیمیشن ها را اضافه می کند
        if (animEnter != 0 && animExit != 0) {
            transaction.setCustomAnimations(animEnter, animExit, animEnter, animExit);
        }

        //در اینجا فرگمنت اضافه می شود
        transaction.add(R.id.FrameMain, fragment, fragmentTAG);

        //در اینجا فرگمنت را به پشته اضافه می کند
        if (backStack) {
            transaction.addToBackStack(null);
        }

        //در اینجا فرگمنت نمایش داده می شود
        transaction.commit();
    }

    public Tbl_Notification getTbl_notification() {
        return tbl_notification;
    }

    public Tbl_User getTbl_user() {
        return tbl_user;
    }

    //ر اینجا تنظیمات فلورای انجام می شود
    void settingFlurry() {
        new FlurryAgent.Builder()
                .withLogEnabled(true)
                .build(this, "G3CSR3SH55SXQ36NXBR3");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isActive = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isActive = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        isActive = true;
    }

    //زمانی که لاگین کاربر به اتمام میرسد در اینجا عملیت بستن صفحات لاگین انجام می شود
    public void onBackPressedLoginPage() {

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.FrameMain);
        if (fragment instanceof LoginCodeFragment) {
            onBackPressed();
        }

        fragment = getSupportFragmentManager().findFragmentById(R.id.FrameMain);
        if (fragment instanceof LoginFragment) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {

        //چک می کند که منو بغل صفحه اصلی باز است اگر باز باشد آن را می بندد در غیر این صورت عملیات بستن صفحه جاری را انجام می دهد
        if (!closeDrawer()) {
            super.onBackPressed();
        }
    }

    //در اینج اگر منوی بل باز باشد آن را می بندد
    boolean closeDrawer() {

        HomeFragment fragment = ((HomeFragment) getSupportFragmentManager().findFragmentByTag(HomeFragment.TAG));
        if (fragment != null) {
            if (fragment.getDrawer().isDrawerOpen(GravityCompat.START)) {
                fragment.getDrawer().closeDrawers();
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        List<Fragment> fragments = getSupportFragmentManager().getFragments();

        if (fragments.size() > 0) {

            Fragment fragment = fragments.get(fragments.size() - 1);

            if (fragment != null) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    //در اینجا اندازه عرض صفحه نمایش برگشت داده می شود
    public int getWidthDisplay() {
        return displayMetrics.widthPixels;
    }

    //در اینجا اندازه طول صفحه نمایش برگشت داده می شود
    public int getHeightDisplay() {
        return displayMetrics.heightPixels;
    }
}
