package ir.tdaapp.paymanyar.View.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
import ir.tdaapp.paymanyar.Model.Services.onClickSliderItem;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.openUrl;
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
    ErrorAplicationDialog errorAplicationDialog;
    RelativeLayout point1, point2, point3, point4, point5, point6, point7, point8;
    ImageView btn_reload;
    CardView btn_TenderAnalise, btn_Scheduling, btn_Audit, btn_Difference, btn_CostEstimation;
    CardView btn_WorkForce, btn_Machinery, btn_Materials,btn_orders;
    ErrorAplicationDialog soonAdded;

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
        point1 = view.findViewById(R.id.point1);
        point2 = view.findViewById(R.id.point2);
        point3 = view.findViewById(R.id.point3);
        point4 = view.findViewById(R.id.point4);
        point5 = view.findViewById(R.id.point5);
        point6 = view.findViewById(R.id.point6);
        point7 = view.findViewById(R.id.point7);
        point8 = view.findViewById(R.id.point8);
        btn_reload = view.findViewById(R.id.btn_reload);
        btn_TenderAnalise = view.findViewById(R.id.btn_TenderAnalise);
        btn_Scheduling = view.findViewById(R.id.btn_Scheduling);
        btn_Audit = view.findViewById(R.id.btn_Audit);
        btn_Difference = view.findViewById(R.id.btn_Difference);
        btn_CostEstimation = view.findViewById(R.id.btn_CostEstimation);
        btn_WorkForce = view.findViewById(R.id.btn_WorkForce);
        btn_Machinery = view.findViewById(R.id.btn_Machinery);
        btn_Materials = view.findViewById(R.id.btn_Materials);
        btn_orders = view.findViewById(R.id.btn_orders);
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
        btn_reload.setOnClickListener(this);
        btn_TenderAnalise.setOnClickListener(this);
        btn_Scheduling.setOnClickListener(this);
        btn_Audit.setOnClickListener(this);
        btn_Difference.setOnClickListener(this);
        btn_CostEstimation.setOnClickListener(this);
        btn_WorkForce.setOnClickListener(this);
        btn_Machinery.setOnClickListener(this);
        btn_Materials.setOnClickListener(this);
        btn_orders.setOnClickListener(this);

        //در اینجا زمانی که یکی از صفحه های اسلایدر تغییر پیجینگ مربوط به آن را تغییر می دهد
        Slider.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                try {

                    if (null != getContext()) {
                        point1.setBackground(getResources().getDrawable(R.drawable.page_slider));
                        point2.setBackground(getResources().getDrawable(R.drawable.page_slider));
                        point3.setBackground(getResources().getDrawable(R.drawable.page_slider));
                        point4.setBackground(getResources().getDrawable(R.drawable.page_slider));
                        point5.setBackground(getResources().getDrawable(R.drawable.page_slider));
                        point6.setBackground(getResources().getDrawable(R.drawable.page_slider));
                        point7.setBackground(getResources().getDrawable(R.drawable.page_slider));
                        point8.setBackground(getResources().getDrawable(R.drawable.page_slider));

                        //در اینجا پیجینگ مربوط به آن عکس اسلایدر اکتیو می شود
                        switch (onGetCurrentSlider() + 1) {
                            case 1:
                                point1.setBackground(getResources().getDrawable(R.drawable.page_slider_active));
                                break;
                            case 2:
                                point2.setBackground(getResources().getDrawable(R.drawable.page_slider_active));
                                break;
                            case 3:
                                point3.setBackground(getResources().getDrawable(R.drawable.page_slider_active));
                                break;
                            case 4:
                                point4.setBackground(getResources().getDrawable(R.drawable.page_slider_active));
                                break;
                            case 5:
                                point5.setBackground(getResources().getDrawable(R.drawable.page_slider_active));
                                break;
                            case 6:
                                point6.setBackground(getResources().getDrawable(R.drawable.page_slider_active));
                                break;
                            case 7:
                                point7.setBackground(getResources().getDrawable(R.drawable.page_slider_active));
                                break;
                            case 8:
                                point8.setBackground(getResources().getDrawable(R.drawable.page_slider_active));
                                break;
                        }
                    }
                } catch (Exception e) {
                }

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

        sliderHomeAdapter.setClickSliderItem(new onClickSliderItem() {
            @Override
            public void onClick(VM_HomeSlider model) {
                p_homeFragment.resetSlider();
                openUrl.getLink(model.getUrlKind(), model.getUrl(), getContext());
            }

            @Override
            public void onToach(int id) {
                p_homeFragment.resetSlider();
            }
        });

        Loading.startShimmerAnimation();
    }

    //زمانی که در گرفتن اطلاعات از سرور خطای رخ دهد متد زیر فراخوانی می شود
    @Override
    public void onError(ResaultCode resault) {

        btn_reload.setVisibility(View.VISIBLE);
        String text = "";

        switch (resault) {
            case NetworkError:
                text = getString(R.string.please_Checked_Your_Internet_Connection);
                break;
            case TimeoutError:
                text = getString(R.string.YourInternetIsVrySlow);
                break;
            case ServerError:
                text = getString(R.string.There_Was_an_Error_In_The_Server);
                break;
            case ParseError:
            case Error:
                text = getString(R.string.There_Was_an_Error_In_The_Application);
                break;
        }

        errorAplicationDialog = new ErrorAplicationDialog(getString(R.string.Error), text, getString(R.string.Again), R.drawable.ic_error, R.color.colorError, () -> {
            p_homeFragment.start();
            errorAplicationDialog.dismiss();
        });
        errorAplicationDialog.show(getActivity().getSupportFragmentManager(), ErrorAplicationDialog.TAG);

    }

    //در اینجا تمام آیتم ها مخفی می شوند
    @Override
    public void onHideAll() {
        btn_reload.setVisibility(View.GONE);
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
        p_homeFragment.startSlider();

        //در اینجا پیجینگ اسلایدر ایجاد می شود
        for (int i = 1; i <= onGetCountSlider(); i++) {

            if (i == 1) {
                point1.setVisibility(View.VISIBLE);
            }
            if (i == 2) {
                point2.setVisibility(View.VISIBLE);
                point2.setBackground(getResources().getDrawable(R.drawable.page_slider));
            }
            if (i == 3) {
                point3.setVisibility(View.VISIBLE);
                point3.setBackground(getResources().getDrawable(R.drawable.page_slider));
            }
            if (i == 4) {
                point4.setVisibility(View.VISIBLE);
                point4.setBackground(getResources().getDrawable(R.drawable.page_slider));
            }
            if (i == 5) {
                point5.setVisibility(View.VISIBLE);
                point5.setBackground(getResources().getDrawable(R.drawable.page_slider));
            }
            if (i == 6) {
                point6.setVisibility(View.VISIBLE);
                point6.setBackground(getResources().getDrawable(R.drawable.page_slider));
            }
            if (i == 7) {
                point7.setVisibility(View.VISIBLE);
                point7.setBackground(getResources().getDrawable(R.drawable.page_slider));
            }
            if (i == 8) {
                point8.setVisibility(View.VISIBLE);
                point8.setBackground(getResources().getDrawable(R.drawable.page_slider));
            }

        }
        point1.setBackground(getResources().getDrawable(R.drawable.page_slider_active));

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

    //در اینجا تعداد اسلایدرها برگشت داده می شود
    @Override
    public int onGetCountSlider() {
        return Slider.getAdapter().getItemCount();
    }

    //برای به دست آوردن صفحه بعد اسلایدر می باشد
    @Override
    public int onGetItem(int i) {
        return Slider.getCurrentItem() + i;
    }

    //در اینجا صفحه جاری اسلایدر گرفته می شود
    @Override
    public int onGetCurrentSlider() {
        return Slider.getCurrentItem();
    }

    @Override
    public void onSetCurrentSlider(int i, boolean b) {
        Slider.setCurrentItem(i, b);
    }

    //در اینجا دکمه لاگین در منو بغل نمایش داده می شود
    @Override
    public void onShowMenuLoginNavigation() {
        Menu nav_Menu = nav_View.getMenu();
        nav_Menu.findItem(R.id.menu_Login).setVisible(true);
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
            case R.id.btn_reload:
                p_homeFragment.start();
                break;
            case R.id.btn_TenderAnalise:
                ((MainActivity) getActivity()).onAddFragment(new AnalizeTendersFragment(), R.anim.fadein, R.anim.fadeout, true, AnalizeTendersFragment.TAG);
                break;
            case R.id.btn_orders:
                ((MainActivity) getActivity()).onAddFragment(new OrdersFragment(), R.anim.fadein, R.anim.fadeout, true, OrdersFragment.TAG);
                break;
            case R.id.btn_Scheduling:
                ((MainActivity) getActivity()).onAddFragment(new SchedulingFragment(), R.anim.fadein, R.anim.fadeout, true, SchedulingFragment.TAG);
                break;
            case R.id.btn_Audit:
                ((MainActivity) getActivity()).onAddFragment(new AuditFragment(), R.anim.fadein, R.anim.fadeout, true, AuditFragment.TAG);
                break;
            case R.id.btn_CostEstimation:
                ((MainActivity) getActivity()).onAddFragment(new CostEstimationFragment(), R.anim.fadein, R.anim.fadeout, true, CostEstimationFragment.TAG);
                break;
            case R.id.btn_Difference:
                ((MainActivity) getActivity()).onAddFragment(new DifferenceFragment(), R.anim.fadein, R.anim.fadeout, true, DifferenceFragment.TAG);
                break;
            case R.id.btn_WorkForce:
                ((MainActivity) getActivity()).onAddFragment(new PowerSupplyNetworkFragment(), R.anim.fadein, R.anim.fadeout, true, PowerSupplyNetworkFragment.TAG);
                break;
            case R.id.btn_Machinery:
            case R.id.btn_Materials:

                soonAdded = new ErrorAplicationDialog(getString(R.string.Soon), getString(R.string.It_will_be_presented_in_the_next_version), getString(R.string.ok), R.drawable.ic_error, R.color.colorError, () -> {
                    soonAdded.dismiss();
                });
                soonAdded.show(getActivity().getSupportFragmentManager(), ErrorAplicationDialog.TAG);

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
            case R.id.menu_SendApplication:
                p_homeFragment.ShareApplication();
                break;
            case R.id.menu_smsBox:
                ((MainActivity) getActivity()).onAddFragment(new SmsFragment(), R.anim.fadein, R.anim.fadeout, true, SmsFragment.TAG);
                break;
            case R.id.menu_contactUS:
                ((MainActivity) getActivity()).onAddFragment(new SupportFragment(), R.anim.fadein, R.anim.fadeout, true, SupportFragment.TAG);
                break;
            case R.id.menu_telegramChannel:
                openUrl.getTelegram("http://telegram.me/paimanyar", getContext());
                break;
        }

        drawer.closeDrawers();

        return true;
    }

    public DrawerLayout getDrawer() {
        return drawer;
    }

}
