package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Adapters.SliderDetailPowerSupplyAdapter;
import ir.tdaapp.paymanyar.Model.Services.S_DetailMachineryFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.ZoomOutPageTransformer;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailMachinery;
import ir.tdaapp.paymanyar.Presenter.P_DetailMachineryFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Dialogs.ErrorAplicationDialog;

/**
 * جزئیات ماشین آلات
 **/
public class DetailMachineryFragment extends BaseFragment implements S_DetailMachineryFragment, View.OnClickListener {

    public final static String TAG = "DetailMachineryFragment";

    P_DetailMachineryFragment p_detailMachineryFragment;
    SliderDetailPowerSupplyAdapter sliderDetailPowerSupplyAdapter;
    Toolbar toolbar;
    ViewPager2 slider;
    ShimmerFrameLayout loading;
    ImageView btn_reload;
    TextView lbl_AdType, lbl_Price, lbl_MachineryCategory, lbl_Title, lbl_Call, lbl_Province, lbl_Description;
    ErrorAplicationDialog errorAplicationDialog;
    int id;
    RelativeLayout rightSlider, leftSlider;
    LinearLayout btn_Support, btn_Home;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_machinery_fragment, container, false);

        findItem(view);
        implement();
        setToolbar();

        new Handler().postDelayed(() -> {
            p_detailMachineryFragment.start();
        }, 300);

        return view;
    }

    void findItem(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        slider = view.findViewById(R.id.slider);
        loading = view.findViewById(R.id.loading);
        btn_reload = view.findViewById(R.id.btn_reload);
        lbl_AdType = view.findViewById(R.id.lbl_AdType);
        lbl_Price = view.findViewById(R.id.lbl_Price);
        lbl_MachineryCategory = view.findViewById(R.id.lbl_MachineryCategory);
        lbl_Title = view.findViewById(R.id.lbl_Title);
        lbl_Call = view.findViewById(R.id.lbl_Call);
        lbl_Province = view.findViewById(R.id.lbl_Province);
        lbl_Description = view.findViewById(R.id.lbl_Description);
        rightSlider = view.findViewById(R.id.rightSlider);
        leftSlider = view.findViewById(R.id.leftSlider);
        btn_Support = view.findViewById(R.id.btn_Support);
        btn_Home = view.findViewById(R.id.btn_Home);
    }

    void implement() {
        p_detailMachineryFragment = new P_DetailMachineryFragment(getContext(), this);
        leftSlider.setOnClickListener(this);
        rightSlider.setOnClickListener(this);
        btn_reload.setOnClickListener(this);
        btn_Home.setOnClickListener(this);
        btn_Support.setOnClickListener(this);

        try {
            id = getArguments().getInt("id");
        } catch (Exception e) {
            id = 0;
        }
    }

    //در اینجا تنظیمات تولبار ست می شود
    void setToolbar() {
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setTitle(getContext().getResources().getString(R.string.DetailMachinery));
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });
        setHasOptionsMenu(true);
    }

    @Override
    public void OnStart() {
        slider.setVisibility(View.GONE);
        btn_reload.setVisibility(View.GONE);
    }

    @Override
    public void onLoading(boolean load) {
        if (load) {
            loading.setVisibility(View.VISIBLE);
            loading.startShimmerAnimation();
        } else {
            loading.setVisibility(View.GONE);
            loading.stopShimmerAnimation();
        }
    }

    @Override
    public void onError(ResaultCode result) {
        String text = "";

        switch (result) {
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

        btn_reload.setVisibility(View.VISIBLE);

        errorAplicationDialog = new ErrorAplicationDialog(getString(R.string.Error), text, getString(R.string.Again), R.drawable.ic_error, R.color.colorError, () -> {
            p_detailMachineryFragment.start();
            errorAplicationDialog.dismiss();
        });
        errorAplicationDialog.show(getActivity().getSupportFragmentManager(), ErrorAplicationDialog.TAG);
    }

    @Override
    public void onSuccess() {
        slider.setVisibility(View.VISIBLE);
        btn_reload.setVisibility(View.GONE);
    }

    @Override
    public void onFinish() {

    }

    /**
     * در اینجا جزئیات آگهی ست می شوند
     **/
    @Override
    public void onItem(VM_DetailMachinery detailMachinery) {
        try {

            //نوع آگهی
            if (detailMachinery.getAdTypeCondition() != null) {
                switch (detailMachinery.getAdTypeCondition()) {
                    case RentTake:
                        lbl_AdType.setText(getString(R.string.RentTake));
                        break;
                    case RentGive:
                        lbl_AdType.setText(getString(R.string.RentGive));
                        break;
                    case Sales:
                        lbl_AdType.setText(getString(R.string.Sales));
                        break;
                    case Buy:
                        lbl_AdType.setText(getString(R.string.Buy));
                        break;
                    default:
                        lbl_AdType.setText("-");
                        break;
                }
            }

            //قیمت
            if (!detailMachinery.getPrice().equalsIgnoreCase("")) {
                lbl_Price.setText(detailMachinery.getPrice());
            } else {
                lbl_Price.setText("-");
            }

            //دسته ماشین آلات
            if (detailMachinery.getMachineryId() != 0) {
                lbl_MachineryCategory.setText(p_detailMachineryFragment.getTitleMachineryById(detailMachinery.getMachineryId()));
            } else {
                lbl_MachineryCategory.setText("-");
            }

            //در اینجا عنوان ست می شود
            if (!detailMachinery.getTitle().equalsIgnoreCase("")) {
                lbl_Title.setText(detailMachinery.getTitle());
            } else {
                lbl_Title.setText("-");
            }

            //شماره تماس
            if (!detailMachinery.getCellPhone().equalsIgnoreCase("")) {
                lbl_Call.setText(detailMachinery.getCellPhone());
            } else {
                lbl_Call.setText("-");
            }

            //استان و شهر
            if (detailMachinery.getStateId() != 0 || detailMachinery.getCityId() != 0) {

                String cityName = p_detailMachineryFragment.getTitleStateById(detailMachinery.getCityId());
                String stateName = p_detailMachineryFragment.getTitleStateById(detailMachinery.getStateId());

                if (!stateName.equalsIgnoreCase("")) {
                    lbl_Province.setText(stateName);
                }

                if (!cityName.equalsIgnoreCase("")) {
                    if (!stateName.equalsIgnoreCase("")) {
                        String val = stateName + " - " + cityName;
                        lbl_Province.setText(val);
                    } else {
                        lbl_Province.setText(cityName);
                    }
                }

            } else {
                lbl_Province.setText("-");
            }

            //توضیحات
            if (!detailMachinery.getDescription().equalsIgnoreCase("")) {
                lbl_Description.setText(detailMachinery.getDescription());
            } else {
                lbl_Description.setText("-");
            }

            if (detailMachinery.getImages().size() > 0) {
                sliderDetailPowerSupplyAdapter = new SliderDetailPowerSupplyAdapter(getContext(), detailMachinery.getImages());
            } else {
                List<String> urls = new ArrayList<>();
                urls.add("noImage");
                sliderDetailPowerSupplyAdapter = new SliderDetailPowerSupplyAdapter(getContext(), urls);
            }

            slider.setAdapter(sliderDetailPowerSupplyAdapter);
            slider.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
            slider.setPageTransformer(new ZoomOutPageTransformer());

        } catch (Exception e) {
        }
    }

    /**
     * در اینجا آیدی آگهی برگشت داده می شود
     **/
    @Override
    public int onItemId() {
        return id;
    }

    /**
     * در اینجا پوزیشن عکس جاری رو از اسلایدر گرفته و آن را برگشت می دهد
     **/
    @Override
    public int getCurrentSlider() {
        if (slider.getAdapter() != null)
            return slider.getCurrentItem();
        return -1;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        p_detailMachineryFragment.cancel(TAG);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.leftSlider:
                if (slider.getAdapter() != null) {
                    if ((slider.getAdapter().getItemCount() - 1) > getCurrentSlider()) {
                        if (getCurrentSlider() != -1) {
                            slider.setCurrentItem(getCurrentSlider() + 1);
                        }
                    }
                }
                break;
            case R.id.rightSlider:

                if (getCurrentSlider() != 0 && getCurrentSlider() != -1) {
                    slider.setCurrentItem(getCurrentSlider() - 1);
                }
                break;
            case R.id.btn_reload:
                p_detailMachineryFragment.start();
                break;
            case R.id.btn_Support:
                ((MainActivity) getActivity()).onAddFragment(new SupportFragment(), R.anim.fadein, R.anim.short_fadeout, true, SupportFragment.TAG);
                break;
            case R.id.btn_Home:
                ((MainActivity) getActivity()).backToHome();
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }
}
