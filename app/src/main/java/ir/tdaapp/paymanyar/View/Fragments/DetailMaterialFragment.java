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
import ir.tdaapp.paymanyar.Model.Enums.AdTypeMaterial;
import ir.tdaapp.paymanyar.Model.Services.S_DetailMaterialFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.ZoomOutPageTransformer;
import ir.tdaapp.paymanyar.Model.Utilitys.openUrl;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailMaterial;
import ir.tdaapp.paymanyar.Presenter.P_DetailMaterialFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Dialogs.ErrorAplicationDialog;

/**
 * مربوط به جزئیات مصالح
 **/
public class DetailMaterialFragment extends BaseFragment implements S_DetailMaterialFragment, View.OnClickListener {

    public final static String TAG = "DetailMaterialFragment";
    P_DetailMaterialFragment p_detailMaterialFragment;
    SliderDetailPowerSupplyAdapter sliderDetailPowerSupplyAdapter;
    Toolbar toolbar;
    ViewPager2 slider;
    ShimmerFrameLayout loading;
    ImageView btn_reload;
    TextView lbl_AdType, lbl_Price, lbl_MaterialCategory, lbl_Title, lbl_Call, lbl_Province, lbl_Description;
    ErrorAplicationDialog errorAplicationDialog;
    int id;
    RelativeLayout rightSlider, leftSlider;
    LinearLayout btn_Support, btn_Home;
    RelativeLayout cellPhoneItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_material_fragment, container, false);

        findItem(view);
        implement();
        setToolbar();

        new Handler().postDelayed(() -> {
            p_detailMaterialFragment.start();
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
        lbl_MaterialCategory = view.findViewById(R.id.lbl_MaterialCategory);
        lbl_Title = view.findViewById(R.id.lbl_Title);
        lbl_Call = view.findViewById(R.id.lbl_Call);
        lbl_Province = view.findViewById(R.id.lbl_Province);
        lbl_Description = view.findViewById(R.id.lbl_Description);
        rightSlider = view.findViewById(R.id.rightSlider);
        leftSlider = view.findViewById(R.id.leftSlider);
        btn_Support = view.findViewById(R.id.btn_Support);
        btn_Home = view.findViewById(R.id.btn_Home);
        cellPhoneItem = view.findViewById(R.id.cellPhoneItem);
    }

    void implement() {
        p_detailMaterialFragment=new P_DetailMaterialFragment(getContext(),this);
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
        toolbar.setTitle(getContext().getResources().getString(R.string.DetailMaterial));
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
            p_detailMaterialFragment.start();
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
    public void onItem(VM_DetailMaterial detailMaterial) {
        try {

            //نوع آگهی
            if (detailMaterial.getAdTypeMaterial()== AdTypeMaterial.Sales){
                lbl_AdType.setText(getString(R.string.Sales));
            }else{
                lbl_AdType.setText(getString(R.string.Buy));
            }

            //قیمت
            if (!detailMaterial.getPrice().equalsIgnoreCase("-")) {
                lbl_Price.setText(detailMaterial.getPrice());
            } else {
                lbl_Price.setText(getString(R.string.Agreement));
            }

            //دسته مصالح
            if (!detailMaterial.getMaterialsTitle().equalsIgnoreCase("")) {
                lbl_MaterialCategory.setText(detailMaterial.getMaterialsTitle());
            } else {
                lbl_MaterialCategory.setText("-");
            }

            //در اینجا عنوان ست می شود
            if (!detailMaterial.getTitle().equalsIgnoreCase("")) {
                lbl_Title.setText(detailMaterial.getTitle());
            } else {
                lbl_Title.setText("-");
            }

            //شماره تماس
            if (!detailMaterial.getCellPhone().equalsIgnoreCase("")) {
                lbl_Call.setText(detailMaterial.getCellPhone());
            } else {
                lbl_Call.setText("-");
            }

            //استان و شهر
            if (detailMaterial.getStateId() != 0 || detailMaterial.getCityId() != 0) {

                String cityName = p_detailMaterialFragment.getTitleStateById(detailMaterial.getCityId());
                String stateName = p_detailMaterialFragment.getTitleStateById(detailMaterial.getStateId());

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
            if (!detailMaterial.getDescription().equalsIgnoreCase("")) {
                lbl_Description.setText(detailMaterial.getDescription());
            } else {
                lbl_Description.setText("-");
            }

            if (detailMaterial.getImages().size() > 0) {
                sliderDetailPowerSupplyAdapter = new SliderDetailPowerSupplyAdapter(getContext(), detailMaterial.getImages());
            } else {
                List<String> urls = new ArrayList<>();
                urls.add("noImage");
                sliderDetailPowerSupplyAdapter = new SliderDetailPowerSupplyAdapter(getContext(), urls);
            }

            slider.setAdapter(sliderDetailPowerSupplyAdapter);
            slider.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
            slider.setPageTransformer(new ZoomOutPageTransformer());

        }catch (Exception e){}
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
        p_detailMaterialFragment.cancel(TAG);
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
                p_detailMaterialFragment.start();
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
