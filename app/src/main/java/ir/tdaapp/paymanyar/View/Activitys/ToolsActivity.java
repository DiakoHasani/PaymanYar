package ir.tdaapp.paymanyar.View.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.DBExcute;
import ir.tdaapp.paymanyar.Model.Services.S_ToolsActivity;
import ir.tdaapp.paymanyar.Presenter.P_ToolsActivity;
import ir.tdaapp.paymanyar.R;

import android.os.Bundle;
import android.util.DisplayMetrics;

public class ToolsActivity extends AppCompatActivity implements S_ToolsActivity {

    public final static String TAG="ToolsActivity";
    P_ToolsActivity p_toolsActivity;
    private DBExcute dbExcute;
    DisplayMetrics displayMetrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);

        implement();
        p_toolsActivity.start();
    }

    void implement(){
        p_toolsActivity=new P_ToolsActivity(getApplicationContext(),this);
        displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    }

    //زمانی که اکتیویتی عملیات اولیه خود را انجام دهد متد زیر فراخوانی می شود
    @Override
    public void OnStart() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        dbExcute=DBExcute.getInstance(this);
        dbExcute.Open();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbExcute.Close();
    }

    //در این متد یک فرگمنت می گیرد و آن را نمایش می دهد
    //animEnter زمانی که فرگمنت بالا می آید با این انیمیشن نمایش داده می شود
    //animExit فرگمنت با این انیمیشن بسته می شود
    //اگر مقادیر ورودی های انیمیشن صفر باشد به منظور لازم نبودن نمایش انیمیشن می باشد
    //backStack در اینجا از ما می خواهد که فرگمنت را به پشته اضافه کند یا خیر
    //fragmentTAG زمانی که یک فرگمنت به پشته اضافه شد بااستفاده از این ورودی می توانیم به آن دسترسی داشته باشیم
    @Override
    public void onAddFragment(Fragment fragment, int animEnter, int animExit, boolean backStack, String fragmentTAG) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        //اگر ورودی های انیمیشن غیر صفر باشند انیمیشن ها را اضافه می کند
        if (animEnter != 0 && animExit != 0) {
            transaction.setCustomAnimations(animEnter, animExit, animEnter, animExit);
        }

        //در اینجا فرگمنت اضافه می شود
        transaction.add(R.id.FrameTools, fragment, fragmentTAG);

        //در اینجا فرگمنت را به پشته اضافه می کند
        if (backStack){
            transaction.addToBackStack(null);
        }

        //در اینجا فرگمنت نمایش داده می شود
        transaction.commit();
    }

    //در اینجا اندازه عرض صفحه نمایش برگشت داده می شود
    public int getWidthDisplay(){
        return displayMetrics.heightPixels;
    }

    //در اینجا اندازه طول صفحه نمایش برگشت داده می شود
    public int getHeightDisplay(){
        return displayMetrics.widthPixels;
    }
}
