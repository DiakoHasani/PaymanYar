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
import ir.tdaapp.li_utility.Codes.Replace;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Adapters.SliderDetailPowerSupplyAdapter;
import ir.tdaapp.paymanyar.Model.Enums.AdType;
import ir.tdaapp.paymanyar.Model.Services.S_DetailPowerSupplyFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.ZoomOutPageTransformer;
import ir.tdaapp.paymanyar.Model.Utilitys.openUrl;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailPowerSupply;
import ir.tdaapp.paymanyar.Presenter.P_DetailPowerSupplyFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Dialogs.ErrorAplicationDialog;

/**
 * در اینجا جزئیات نیروکار نمایش داده می شود
 **/
public class DetailPowerSupplyFragment extends BaseFragment implements S_DetailPowerSupplyFragment, View.OnClickListener {

    public final static String TAG = "DetailPowerSupplyFragment";

    P_DetailPowerSupplyFragment p_detailPowerSupplyFragment;
    SliderDetailPowerSupplyAdapter sliderDetailPowerSupplyAdapter;
    Toolbar toolbar;
    ViewPager2 slider;
    ShimmerFrameLayout loading;
    ImageView btn_reload;
    TextView lbl_AdType, lbl_Name, lbl_Call, lbl_WorkExperience, lbl_Job, lbl_Province, lbl_Description;
    ErrorAplicationDialog errorAplicationDialog;
    int id;
    RelativeLayout rightSlider, leftSlider;
    LinearLayout btn_Support, btn_Home;
    RelativeLayout cellPhoneItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_power_supply, container, false);

        findItem(view);
        implement();
        setToolbar();

        new Handler().postDelayed(() -> {
            p_detailPowerSupplyFragment.start();
        }, 300);

        return view;
    }

    void findItem(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        slider = view.findViewById(R.id.slider);
        loading = view.findViewById(R.id.loading);
        btn_reload = view.findViewById(R.id.btn_reload);
        lbl_AdType = view.findViewById(R.id.lbl_AdType);
        lbl_Name = view.findViewById(R.id.lbl_Name);
        lbl_Call = view.findViewById(R.id.lbl_Call);
        lbl_WorkExperience = view.findViewById(R.id.lbl_WorkExperience);
        lbl_Job = view.findViewById(R.id.lbl_Job);
        lbl_Province = view.findViewById(R.id.lbl_Province);
        lbl_Description = view.findViewById(R.id.lbl_Description);
        leftSlider = view.findViewById(R.id.leftSlider);
        rightSlider = view.findViewById(R.id.rightSlider);
        btn_Support = view.findViewById(R.id.btn_Support);
        btn_Home = view.findViewById(R.id.btn_Home);
        cellPhoneItem = view.findViewById(R.id.cellPhoneItem);
    }

    void implement() {
        p_detailPowerSupplyFragment = new P_DetailPowerSupplyFragment(getContext(), this);
        leftSlider.setOnClickListener(this);
        rightSlider.setOnClickListener(this);
        btn_reload.setOnClickListener(this);
        btn_Home.setOnClickListener(this);
        btn_Support.setOnClickListener(this);
        cellPhoneItem.setOnClickListener(this);

        try {
            id = getArguments().getInt("id");
        } catch (Exception e) {
            id = 0;
        }
    }

    //در اینجا تنظیمات تولبار ست می شود
    void setToolbar() {
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setTitle(getContext().getResources().getString(R.string.DetailPowerSupply));
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
            p_detailPowerSupplyFragment.start();
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
    public void onItem(VM_DetailPowerSupply detailPowerSupply) {
        try {

            //در اینجا نوع آگهی ست می شود
            if (detailPowerSupply.getAdType() == AdType.presentation) {
                lbl_AdType.setText(getString(R.string.Presentation));
            } else {
                lbl_AdType.setText(getString(R.string.Request));
            }

            //در اینجا نام ست می شود
            if (!detailPowerSupply.getName().equalsIgnoreCase("")) {
                lbl_Name.setText(detailPowerSupply.getName());
            } else {
                lbl_Name.setText("-");
            }

            //در اینجا شماره تماس ست می شود
            if (!detailPowerSupply.getPhone().equalsIgnoreCase("")) {
                lbl_Call.setText(detailPowerSupply.getPhone());
            } else {
                lbl_Call.setText("-");
            }

            //در اینجا سابقه کار ست می شود
            if (detailPowerSupply.getWorkExperienceId() != 0) {
                lbl_WorkExperience.setText(p_detailPowerSupplyFragment.getTitleWorkExperiencesById(detailPowerSupply.getWorkExperienceId()));
            } else {
                lbl_WorkExperience.setText("-");
            }

            //در اینجا شغل ست می شود
            if (!detailPowerSupply.getJobTitle().equalsIgnoreCase("")) {
                lbl_Job.setText(detailPowerSupply.getJobTitle());
            } else {
                lbl_Job.setText("-");
            }

            //در اینجا استان ست می شود
            if (detailPowerSupply.getStateId() != 0) {
                lbl_Province.setText(p_detailPowerSupplyFragment.getTitleStateById(detailPowerSupply.getStateId()));
            }

            //در اینجا شهر ست می شود
            if (detailPowerSupply.getCityId() != 0) {
                String text = "";

                if (lbl_Province.getText().length() > 0) {
                    text = lbl_Province.getText().toString() + " - ";
                }

                text += p_detailPowerSupplyFragment.getTitleStateById(detailPowerSupply.getCityId());
                lbl_Province.setText(text);
            }

            //در اینجا توضیحات ست می شود
            if (!detailPowerSupply.getDescription().equalsIgnoreCase("")) {
                lbl_Description.setText(detailPowerSupply.getDescription());
            } else {
                lbl_Description.setText("-");
            }

            if (detailPowerSupply.getImages().size() > 0) {
                sliderDetailPowerSupplyAdapter = new SliderDetailPowerSupplyAdapter(getContext(), detailPowerSupply.getImages());
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
        p_detailPowerSupplyFragment.cancel(TAG);
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
                p_detailPowerSupplyFragment.start();
                break;
            case R.id.btn_Support:
                ((MainActivity) getActivity()).onAddFragment(new SupportFragment(), R.anim.fadein, R.anim.short_fadeout, true, SupportFragment.TAG);
                break;
            case R.id.btn_Home:
                ((MainActivity) getActivity()).backToHome();
                break;
            case R.id.cellPhoneItem:
                String phoneNumber = lbl_Call.getText().toString();
                if (!phoneNumber.equalsIgnoreCase("") && !phoneNumber.equalsIgnoreCase("-")) {
                    openUrl.getLink(6, Replace.Number_fn_To_en(phoneNumber), getContext());
                }
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }
}
