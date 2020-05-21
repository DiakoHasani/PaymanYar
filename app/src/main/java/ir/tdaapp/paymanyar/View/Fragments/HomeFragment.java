package ir.tdaapp.paymanyar.View.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Adapters.SliderHomeAdapter;
import ir.tdaapp.paymanyar.Model.Services.S_HomeFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_HomeSlider;
import ir.tdaapp.paymanyar.Presenter.P_HomeFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Activitys.ToolsActivity;
import ir.tdaapp.paymanyar.View.Dialogs.ErrorAplicationDialog;
import ir.tdaapp.paymanyar.View.Dialogs.UpdateAppDialog;

public class HomeFragment extends BaseFragment implements S_HomeFragment, View.OnClickListener,
        NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "HomeFragment";

    ViewPager2 Slider;
    P_HomeFragment p_homeFragment;
    SliderHomeAdapter sliderHomeAdapter;
    ShimmerFrameLayout Loading;
    CardView btn_Tools, btn_NewsPaper, btn_TenderNotification, btn_PriceRange, btn_Libraries;
    Toolbar toolbar;
    NavigationView nav_View;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    LinearLayout btn_SMS, btn_Support;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        findItem(view);
        implement();
        setToolBar();

        p_homeFragment.start();

        return view;
    }

    void findItem(View view) {
        Slider = view.findViewById(R.id.Slider);
        Loading = view.findViewById(R.id.Loading);
        btn_Tools = view.findViewById(R.id.btn_Tools);
        btn_NewsPaper = view.findViewById(R.id.btn_NewsPaper);
        btn_TenderNotification = view.findViewById(R.id.btn_TenderNotification);
        toolbar = view.findViewById(R.id.toolbar);
        nav_View = view.findViewById(R.id.nav_View);
        drawer = view.findViewById(R.id.drawer);
        btn_PriceRange = view.findViewById(R.id.btn_PriceRange);
        btn_Libraries = view.findViewById(R.id.btn_Libraries);
        btn_SMS = view.findViewById(R.id.btn_SMS);
        btn_Support = view.findViewById(R.id.btn_Support);
    }

    void implement() {
        p_homeFragment = new P_HomeFragment(this, getContext());
        toggle = new ActionBarDrawerToggle(getActivity(), drawer, R.string.Open, R.string.Close);
        btn_Tools.setOnClickListener(this);
        btn_NewsPaper.setOnClickListener(this);
        btn_TenderNotification.setOnClickListener(this);
        btn_PriceRange.setOnClickListener(this);
        btn_Libraries.setOnClickListener(this);
        btn_SMS.setOnClickListener(this);
        btn_Support.setOnClickListener(this);

        Slider.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });

        nav_View.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        if (((MainActivity) getActivity()).getTbl_user().hasAccount(getContext())) {
            hideMenuLoginNavigation();
        }
    }

    void setToolBar() {

        toolbar.setTitle("");
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setHasOptionsMenu(true);

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorWhite));
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    public void hideMenuLoginNavigation() {
        Menu nav_Menu = nav_View.getMenu();
        nav_Menu.findItem(R.id.menu_Login).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (toggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    //زمانی که عملیات گرفتن اطلاعات شروع شود اولین بار متد زیر فراخوانی می شود تا عملیات اولیه انجام شود
    @Override
    public void OnStart() {
        sliderHomeAdapter = new SliderHomeAdapter(getContext());
        Slider.setAdapter(sliderHomeAdapter);
        Slider.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        Loading.startShimmerAnimation();
    }

    //زمانی که در گرفتن اطلاعات از سرور خطای رخ دهد متد زیر فراخوانی می شود
    @Override
    public void onError(ResaultCode resault) {

    }

    //در اینجا تمام آیتم ها مخفی می شوند
    @Override
    public void onHideAll() {

    }

    //در اینجا حالت لودینگ برنامه ست می شود
    @Override
    public void onLoading(boolean isLoad) {
        if (isLoad) {
            Loading.setVisibility(View.VISIBLE);
        } else {
            Loading.setVisibility(View.GONE);
        }
    }

    //در اینجا یکی یکی آیتم های اسلایدر اضافه می شوند
    @Override
    public void onItemSlider(VM_HomeSlider slider) {
        sliderHomeAdapter.add(slider);
    }

    //زمانی عملیات گرفتن داده ها از اینترنت و خواندن داده ها و تمامی عملیات به پیان برسد متد زیر فراخوانی می شود
    @Override
    public void onFinish() {
        Loading.stopShimmerAnimation();
    }

    @Override
    public void onShowSlider(boolean show) {
        if (show) {
            Slider.setVisibility(View.VISIBLE);
        } else {
            Slider.setVisibility(View.GONE);
        }
    }

    //اگر برنامه نیاز به آپدیت داشته باشد متد زیر فراخوانی می شود
    @Override
    public void onUpdateApp(boolean hadUpdate) {

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag(UpdateAppDialog.TAG);

        if (prev == null) {
            ft.addToBackStack(null);

            UpdateAppDialog updateAppDialog = new UpdateAppDialog(hadUpdate);

            //اگر آپدیت اجباری باشد در اینجا می گوییم که دیالوگ بسته نشود تا کاربر برنامه را آپدیت نکند اپلیکیشن قابل استفاده نیست
            updateAppDialog.setCancelable(!hadUpdate);
            updateAppDialog.show(ft, UpdateAppDialog.TAG);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //ر اینجا تمامی عملیات ما لغو می شوند
        p_homeFragment.Cancel(TAG);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Tools:
                startActivity(new Intent(getContext(), ToolsActivity.class));
                break;
            case R.id.btn_NewsPaper:
                ((MainActivity) getActivity()).onAddFragment(new NewsPaperFragment(), R.anim.fadein, R.anim.fadeout, true, NewsPaperFragment.TAG);
                break;
            case R.id.btn_TenderNotification:
                ((MainActivity) getActivity()).onAddFragment(new TenderNotificationFragment(), R.anim.fadein, R.anim.fadeout, true, TenderNotificationFragment.TAG);
                break;
            case R.id.btn_PriceRange:
                ((MainActivity) getActivity()).onAddFragment(new PriceRangeFragment(), R.anim.fadein, R.anim.fadeout, true, PriceRangeFragment.TAG);
                break;
            case R.id.btn_Libraries:
                ((MainActivity) getActivity()).onAddFragment(new LibraryFragment(), R.anim.fadein, R.anim.fadeout, true, LibraryFragment.TAG);
                break;
            case R.id.btn_SMS:
                ((MainActivity) getActivity()).onAddFragment(new SmsFragment(), R.anim.fadein, R.anim.fadeout, true, SmsFragment.TAG);
                break;
            case R.id.btn_Support:
                ((MainActivity) getActivity()).onAddFragment(new SupportFragment(), R.anim.fadein, R.anim.fadeout, true, SupportFragment.TAG);
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.menu_Tender:
                ((MainActivity) getActivity()).onAddFragment(new ChargeFragment(), R.anim.fadein, R.anim.fadeout, true, ChargeFragment.TAG);
                break;
            case R.id.menu_Login:
                ((MainActivity) getActivity()).onAddFragment(new LoginFragment(), R.anim.fadein, R.anim.fadeout, true, LoginFragment.TAG);
                break;
        }

        drawer.closeDrawers();

        return true;
    }

}
