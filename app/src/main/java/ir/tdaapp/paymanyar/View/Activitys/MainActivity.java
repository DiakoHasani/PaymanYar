package ir.tdaapp.paymanyar.View.Activitys;

import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import io.reactivex.plugins.RxJavaPlugins;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.DBExcute;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Notification;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_User;
import ir.tdaapp.paymanyar.Model.Services.S_MainActivity;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.Presenter.P_MainActivity;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Dialogs.UpdateAppDialog;
import ir.tdaapp.paymanyar.View.Fragments.HomeFragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements S_MainActivity {

    public final static String TAG = "MainActivity";
    public static boolean isActive=false;

    P_MainActivity p_mainActivity;
    private Tbl_Notification tbl_notification;
    private Tbl_User tbl_user;
    private DBExcute dbExcute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //اگر در آرایکس جاوا خطای رخ دهد کد زیر فراخوانی می شود
        RxJavaPlugins.setErrorHandler(throwable -> {
            sendErrorToServer("خطای آرایکس جاوا",throwable.toString());
        });

        getNotification();
        findItem();
        implement();
        p_mainActivity.start();
    }

    void findItem() {
    }

    void implement() {
        p_mainActivity = new P_MainActivity(getApplicationContext(), this);
        tbl_notification=new Tbl_Notification();
        tbl_user=new Tbl_User();

    }

    //زمانی که کاربر روی نوتیفیکشن کلیک کند متد زیر فراخوانی می شود
    void getNotification() {

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if (extras != null) {
            for (String key : extras.keySet()) {
                Object value = extras.get(key);
                if (key.equalsIgnoreCase("key1"))
                    Toast.makeText(MainActivity.this, value.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }

    //در اینجا خطاهای برنامه به سمت سرور ارسال می شوند
    public void sendErrorToServer(String name,String text){
        p_mainActivity.sendError(name,text);
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

    //اگر ارسال خطا به سمت سرور با موفقیت انجام شود متد زیر فراخوانی می شود
    @Override
    public void onSuccessSendError(VM_Message message) {

    }

    //اگر خطای در ارسال خطاهای پیش آمده به سمت سرور رخ دهد متد زیر فراخوانی می شود
    @Override
    public void onErrorSendError(ResaultCode result) {

    }

    public Tbl_Notification getTbl_notification() {
        return tbl_notification;
    }

    public Tbl_User getTbl_user() {
        return tbl_user;
    }

    @Override
    protected void onResume() {
        super.onResume();
        dbExcute= DBExcute.getInstance(this);
        dbExcute.Open();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbExcute.Close();
        isActive=false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isActive=false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
        isActive=true;
    }
}
