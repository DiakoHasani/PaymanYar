package ir.tdaapp.paymanyar.View.Fragments;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.util.List;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import ir.tdaapp.li_image.ImagesCodes.CompressImage;
import ir.tdaapp.li_image.ImagesCodes.GetByCamera;
import ir.tdaapp.li_image.ImagesCodes.GetByGalery;
import ir.tdaapp.li_image.ImagesCodes.SaveImageToMob;
import ir.tdaapp.li_utility.Codes.Validation;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Adapters.FileUploadItemAdapter;
import ir.tdaapp.paymanyar.Model.Enums.AdType;
import ir.tdaapp.paymanyar.Model.Enums.FileUploadAnalizeTenderType;
import ir.tdaapp.paymanyar.Model.Enums.StepsAddPower;
import ir.tdaapp.paymanyar.Model.Services.S_AddPowerSupply;
import ir.tdaapp.paymanyar.Model.Services.onClickFileTypeItemDialog;
import ir.tdaapp.paymanyar.Model.Services.onClickFileUpload_AnalizeTender;
import ir.tdaapp.paymanyar.Model.Services.onClickGoToLoginDialog;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.ProjectDirectory;
import ir.tdaapp.paymanyar.Model.Utilitys.openUrl;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_AdType;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_City;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FileUploadAnalizeTender;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Job;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PostPowerSupply;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_ProvincesAndCities;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_WorkExperience;
import ir.tdaapp.paymanyar.Presenter.P_AddPowerSupply;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Dialogs.AdUpgradeDialog;
import ir.tdaapp.paymanyar.View.Dialogs.ErrorAplicationDialog;
import ir.tdaapp.paymanyar.View.Dialogs.FileTypeItemDialog;
import ir.tdaapp.paymanyar.View.Dialogs.GoToLoginDialog;

import static android.app.Activity.RESULT_OK;

/**
 * مربوط به افزودن نیروکار
 **/
public class AddPowerSupply extends BaseFragment implements S_AddPowerSupply, View.OnClickListener {

    public static final String TAG = "AddPowerSupply";

    P_AddPowerSupply p_addPowerSupply;
    FileUploadItemAdapter fileUploadItemAdapter;

    Toolbar toolbar;
    Spinner cmb_AdType, cmb_WorkExperiences, cmb_Provinces, cmb_City;
    TextInputEditText txt_Name, txt_CellPhone;
    EditText txt_Description;
    LinearLayoutManager layoutManager;
    RecyclerView recycler;
    GetByCamera getByCamera;
    GetByGalery getByGalery;
    CompressImage compressImage;
    String[] imagePermissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    Handler handler_clear;
    CircularProgressButton btn_ShowSteps;
    RelativeLayout step;
    NestedScrollView nestedScroll;
    CardView btn_Previous_Orders, btn_UpgradeOrder;
    ErrorAplicationDialog errorAplicationDialog;
    Animation aniFadeOut, aniSlide_up;
    RelativeLayout step_RegistrationRequest, step_Check_the_ad, step_Post_an_ad;
    int id = 0;
    RelativeLayout loading_GetDetail;
    ProgressBar progress_Loading_GetDetail;
    ImageView btn_Refresh_Loading_GetDetail;
    int positionCitySpinner = 0;
    int countSetAdapterCitySpinner = 0;
    boolean enableBankingPortal = false;
    LinearLayout btn_Support, btn_Home;
    AutoCompleteTextView txt_Job;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_power_supply, container, false);

        findItem(view);
        implement();

        setToolbar();

        positionCitySpinner = countSetAdapterCitySpinner = 0;
        p_addPowerSupply.start();

        return view;
    }

    void findItem(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        cmb_AdType = view.findViewById(R.id.cmb_AdType);
        cmb_WorkExperiences = view.findViewById(R.id.cmb_WorkExperiences);
        cmb_Provinces = view.findViewById(R.id.cmb_Provinces);
        cmb_City = view.findViewById(R.id.cmb_City);
        txt_Job = view.findViewById(R.id.txt_Job);
        txt_Name = view.findViewById(R.id.txt_Name);
        txt_CellPhone = view.findViewById(R.id.txt_CellPhone);
        txt_Description = view.findViewById(R.id.txt_Description);
        recycler = view.findViewById(R.id.recycler);
        btn_ShowSteps = view.findViewById(R.id.btn_ShowSteps);
        step = view.findViewById(R.id.step);
        nestedScroll = view.findViewById(R.id.nestedScroll);
        btn_Previous_Orders = view.findViewById(R.id.btn_Previous_Orders);
        step_RegistrationRequest = view.findViewById(R.id.step_RegistrationRequest);
        step_Check_the_ad = view.findViewById(R.id.step_Check_the_ad);
        step_Post_an_ad = view.findViewById(R.id.step_Post_an_ad);
        btn_UpgradeOrder = view.findViewById(R.id.btn_UpgradeOrder);
        loading_GetDetail = view.findViewById(R.id.loading_GetDetail);
        btn_Refresh_Loading_GetDetail = view.findViewById(R.id.btn_Refresh_Loading_GetDetail);
        progress_Loading_GetDetail = view.findViewById(R.id.progress_Loading_GetDetail);
        btn_Support = view.findViewById(R.id.btn_Support);
        btn_Home = view.findViewById(R.id.btn_Home);
    }

    void implement() {
        p_addPowerSupply = new P_AddPowerSupply(getContext(), this);
        compressImage = new CompressImage(480, 780, 100, getContext());
        handler_clear = new Handler(Looper.getMainLooper());
        btn_Previous_Orders.setOnClickListener(this);
        btn_ShowSteps.setOnClickListener(this);
        btn_UpgradeOrder.setOnClickListener(this);
        btn_Refresh_Loading_GetDetail.setOnClickListener(this);
        btn_Support.setOnClickListener(this);
        btn_Home.setOnClickListener(this);
        aniFadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.long_fadeout);
        aniSlide_up = AnimationUtils.loadAnimation(getContext(), R.anim.slide_up);

        cmb_Provinces.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                try {
                    VM_ProvincesAndCities item = ((VM_ProvincesAndCities) adapterView.getSelectedItem());

                    if (item != null) {
                        p_addPowerSupply.getCities(item.getId());
                    }

                } catch (Exception e) {
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        try {
            if (getArguments() != null) {
                id = getArguments().getInt("id");
            }
        } catch (Exception e) {
        }
    }

    void setToolbar() {

        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setTitle(getContext().getResources().getString(R.string.AddPowerSupply));
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (enableBankingPortal) {
            enableBankingPortal = false;
            p_addPowerSupply.getDetailItem();
        }
    }

    @Override
    public void onLoading(boolean load) {
        if (load) {
            btn_ShowSteps.setEnabled(false);
            btn_ShowSteps.startAnimation();
        } else {
            btn_ShowSteps.setEnabled(true);
            btn_ShowSteps.revertAnimation();
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

        errorAplicationDialog = new ErrorAplicationDialog(getString(R.string.Error), text, getString(R.string.ok), R.drawable.ic_error, R.color.colorError, () -> {
            errorAplicationDialog.dismiss();
        });
        errorAplicationDialog.show(getActivity().getSupportFragmentManager(), ErrorAplicationDialog.TAG);
    }

    @Override
    public void onSuccess() {
        btn_ShowSteps.revertAnimation(() -> {
            enableUpgradeOrder(false);
            btn_ShowSteps.setAnimation(aniFadeOut);
            btn_ShowSteps.setVisibility(View.GONE);

            step.setAnimation(aniSlide_up);
            step.setVisibility(View.VISIBLE);
        });
    }

    /**
     * در اینجا اسپینر استان ست می شود
     **/
    @Override
    public void getProvinces(ArrayAdapter<VM_ProvincesAndCities> provinces) {
        cmb_Provinces.setAdapter(provinces);
    }

    /**
     * در اینجا اسپینر شهرها ست می شود
     **/
    @Override
    public void getCities(ArrayAdapter<VM_ProvincesAndCities> cities) {
        cmb_City.setAdapter(cities);
        if (positionCitySpinner != 0) {
            if (countSetAdapterCitySpinner == 1) {
                cmb_City.setSelection(positionCitySpinner);
                positionCitySpinner = 0;
            }
        }
        countSetAdapterCitySpinner++;
    }

    /**
     * در اینجا اسپینر شغل ست می شود
     **/
    @Override
    public void getJobs(ArrayAdapter<String> jobs) {
        txt_Job.setAdapter(jobs);
    }

    /**
     * در اینجا اسپینر سابقه کار ست می شود
     **/
    @Override
    public void getWorkExperiences(ArrayAdapter<VM_WorkExperience> workExperiences) {
        cmb_WorkExperiences.setAdapter(workExperiences);
    }

    /**
     * در اینجا اسپینر نوع آگهی ست می شود
     **/
    @Override
    public void getAdTypes(ArrayAdapter<VM_AdType> adapter) {
        cmb_AdType.setAdapter(adapter);
    }

    /**
     * در اینجا داده های پیش فرض فایل آپلود ست می شود سپس آداپتر مربوط به آن ست می شود
     **/
    @Override
    public void defaultValueFileUpload(List<VM_FileUploadAnalizeTender> values) {
        fileUploadItemAdapter = new FileUploadItemAdapter(getContext(), values);
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);

        fileUploadItemAdapter.setClickFileUpload_analizeTender(new onClickFileUpload_AnalizeTender() {
            @Override
            public void onClickFile(VM_FileUploadAnalizeTender file) {

                FileTypeItemDialog fileTypeItemDialog = new FileTypeItemDialog(new onClickFileTypeItemDialog() {
                    @Override
                    public void clickedCamera() {
                        getByCamera = new GetByCamera(getActivity(), 3, image -> {
                            saveImage(file, image);
                        });
                    }

                    @Override
                    public void clickedGallery() {
                        getByGalery = new GetByGalery(getActivity(), 4, image -> {
                            saveImage(file, image);
                        });
                    }
                });
                fileTypeItemDialog.show(getActivity().getSupportFragmentManager(), FileTypeItemDialog.TAG);
            }

            @Override
            public void onClickClose(VM_FileUploadAnalizeTender file) {

            }
        });

        recycler.setAdapter(fileUploadItemAdapter);
        recycler.setLayoutManager(layoutManager);

        //در اینجا کلیک آیتم های آداپتر فعال یا غیرفعال می شوند
        fileUploadItemAdapter.setEnableUpload(getIdItem() == 0);
    }

    /**
     * زمانی که کاربر یک فایل انتخاب کند متد زیر فراخوانی می شود
     **/
    @Override
    public void onSelectedFile(VM_FileUploadAnalizeTender val, File file) {
        p_addPowerSupply.checkValidationFile(file, val);
    }

    /**
     * اگر فایل انتخاب شده ولید باشد متد زیر فراخوانی می شود
     **/
    @Override
    public void onValidFile(VM_FileUploadAnalizeTender val, File file) {
        val.setType(p_addPowerSupply.getTypeFile(file));

        if (val.getType() != FileUploadAnalizeTenderType.empty) {
            val.setPath(file.getPath());
            fileUploadItemAdapter.addFile(val);
        } else {
            Toast.makeText(getContext(), getString(R.string.error_In_Your_File), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * اگر فایل انتخاب شده ولید نباشد متد زیر فراخوانی می شود
     **/
    @Override
    public void onNotValidFile(String errorText) {
        Toast.makeText(getContext(), errorText, Toast.LENGTH_SHORT).show();
    }

    /**
     * در اینجا ولیدیشن المنت ها را چک می کند
     **/
    @Override
    public boolean checkValidation() {
        boolean isValid = true;

        //در اینجا ولیدیشن شغل چک می شود
        if (!Validation.Required(txt_Job, getString(R.string.ThisValueMust_be_Filled))) {
            isValid = false;
        }

        //در اینجا ولیدیشن نوع آگهی چک می شود
        if (cmb_AdType.getAdapter() != null) {
            if (((VM_AdType) cmb_AdType.getSelectedItem()).getAdType() == AdType.title) {
                isValid = false;
                ((TextView) cmb_AdType.getSelectedView()).setError(getString(R.string.PleaseSelectedOneJobType));
            }
        }

        //در اینجا ولیدیشن سابقه کار چک می شود
        if (cmb_WorkExperiences.getAdapter() != null) {
            if (((VM_WorkExperience) cmb_WorkExperiences.getSelectedItem()).getId() == 0) {
                isValid = false;
                ((TextView) cmb_WorkExperiences.getSelectedView()).setError(getString(R.string.PleaseSelectedOneWorkExperiences));
            }
        }

        //در اینجا ولیدیشن نام چک می شود
        if (!Validation.Required(txt_Name, getString(R.string.ThisValueMust_be_Filled))) {
            isValid = false;
        }

        //در اینجا ولیدیشن شماره موبایل چک می شود
        if (!Validation.Required(txt_CellPhone, getString(R.string.ThisValueMust_be_Filled))) {
            isValid = false;
        } else if (!Validation.ValidPhoneNumber(txt_CellPhone, getString(R.string.phoneNumberIsNotValid))) {
            isValid = false;
        }

        //در اینجا ولیدیشن توضیحات چک می شود
        if (!Validation.Required(txt_Description, getString(R.string.ThisValueMust_be_Filled))) {
            isValid = false;
        }

        return isValid;
    }

    /**
     * اگر مقادیر کاربر ولید باشد متد زیر فراخوانی می شود
     **/
    @Override
    public void isValid() {

    }

    /**
     * اگر مقادیر ولید نباشن متد زیر فراخوانی می شود
     **/
    @Override
    public void notValid() {
        Toast.makeText(getContext(), getString(R.string.Please_enter_full_values), Toast.LENGTH_SHORT).show();
    }

    /**
     * در اینجا مقادیر کاربر برای افزودن در المنت ها گرفته می شود
     **/
    @Override
    public VM_PostPowerSupply getInputUser() {

        VM_PostPowerSupply input = new VM_PostPowerSupply();

        try {

            input.setAdType(((VM_AdType) cmb_AdType.getSelectedItem()).getAdType());
            input.setWorkExperiences(((VM_WorkExperience) cmb_WorkExperiences.getSelectedItem()).getId());
            input.setState(((VM_ProvincesAndCities) cmb_Provinces.getSelectedItem()).getId());
            input.setCity(((VM_ProvincesAndCities) cmb_City.getSelectedItem()).getId());
            input.setJobTitle(txt_Job.getText().toString());
            input.setName(txt_Name.getText().toString());
            input.setCellPhone(txt_CellPhone.getText().toString());
            input.setDescription(txt_Description.getText().toString());
            input.setUserId(((MainActivity) getActivity()).getTbl_user().getUserId(getContext()));

        } catch (Exception e) {
        }

        return input;
    }

    /**
     * در اینجا لیست آدرس های عکس های انتخاب شده توسط کاربر برگشت داده می شوند
     **/
    @Override
    public List<String> getUrlFiles() {
        return fileUploadItemAdapter.getPathFiles();
    }

    /**
     * اگر کاربر حساب کاربری نداشته باشد متد زیر فراخوانی می شود
     **/
    @Override
    public void noAccount() {
        GoToLoginDialog goToLoginDialog = new GoToLoginDialog(new onClickGoToLoginDialog() {
            @Override
            public void ok() {
                ((MainActivity) getActivity()).onAddFragment(new LoginFragment(), R.anim.fadein, R.anim.short_fadeout, true, LoginFragment.TAG);
            }

            @Override
            public void neverMind() {

            }
        });

        goToLoginDialog.show(getActivity().getSupportFragmentManager(), GoToLoginDialog.TAG);
    }

    /**
     * در اینجا انیمیشن استپ ها ست می شود
     **/
    @Override
    public void onAnimationStep(StepsAddPower stepsAddPower, boolean isAnimation) {
        if (isAnimation) {

            Animation anim = new AlphaAnimation(0.0f, 1.0f);
            anim.setDuration(500);
            anim.setRepeatMode(Animation.REVERSE);
            anim.setRepeatCount(Animation.INFINITE);

            step_RegistrationRequest.setBackground(getContext().getResources().getDrawable(R.drawable.background_disable_step));
            step_Check_the_ad.setBackground(getContext().getResources().getDrawable(R.drawable.background_disable_step));
            step_Post_an_ad.setBackground(getContext().getResources().getDrawable(R.drawable.background_disable_step));

            switch (stepsAddPower) {
                case RegistrationRequest:
                    step_RegistrationRequest.setBackground(getContext().getResources().getDrawable(R.drawable.background_enable_step));
                    step_RegistrationRequest.startAnimation(anim);
                    break;
                case Check_The_Ad:
                    step_RegistrationRequest.setBackground(getContext().getResources().getDrawable(R.drawable.background_enable_step));
                    step_Check_the_ad.setBackground(getContext().getResources().getDrawable(R.drawable.background_enable_step));
                    step_Check_the_ad.startAnimation(anim);
                    break;
                case Post_an_Ad:
                    step_RegistrationRequest.setBackground(getContext().getResources().getDrawable(R.drawable.background_enable_step));
                    step_Check_the_ad.setBackground(getContext().getResources().getDrawable(R.drawable.background_enable_step));
                    step_Post_an_ad.setBackground(getContext().getResources().getDrawable(R.drawable.background_enable_step));
                    step_Post_an_ad.startAnimation(anim);
                    break;
            }
        } else {
            switch (stepsAddPower) {
                case RegistrationRequest:
                    step_RegistrationRequest.setAnimation(null);
                    break;
                case Check_The_Ad:
                    step_Check_the_ad.setAnimation(null);
                    break;
                case Post_an_Ad:
                    step_Post_an_ad.setAnimation(null);
                    break;
            }
        }
    }

    /**
     * در اینجا انیمیشن استپ ها غیر فعال می شوند
     **/
    @Override
    public void disableAnimationAllSteps() {
        step_RegistrationRequest.setAnimation(null);
        step_Check_the_ad.setAnimation(null);
        step_Post_an_ad.setAnimation(null);
    }

    /**
     * در اینجا آیدی سفارش برگشت داده می شود
     **/
    @Override
    public int getIdItem() {
        return id;
    }

    /**
     * در اینجا دکمه ارتقا آگهی فعال یا غیر فعال می شود
     **/
    @Override
    public void enableUpgradeOrder(boolean enable) {
        if (enable) {
            btn_UpgradeOrder.setEnabled(true);
            btn_UpgradeOrder.setCardBackgroundColor(getResources().getColor(R.color.colorPDF));
        } else {
            btn_UpgradeOrder.setEnabled(false);
            btn_UpgradeOrder.setCardBackgroundColor(getResources().getColor(R.color.colorDisable));
        }
    }

    /**
     * در اینجا دکمه افزودن آگهی فعال یا غیر فعال می شود
     **/
    @Override
    public void enableShowSteps(boolean enable) {
        if (enable) {
            btn_ShowSteps.setEnabled(true);
            btn_ShowSteps.setBackground(getResources().getDrawable(R.drawable.border_button_show_steps));
        } else {
            btn_ShowSteps.setEnabled(false);
            btn_ShowSteps.setBackground(getResources().getDrawable(R.drawable.border_button_show_steps_disable));
        }
    }

    /**
     * مربوط به لودینگ گرفتن جزئیات آگهی
     **/
    @Override
    public void onLoadingGetDetail(boolean load) {
        if (load) {
            loading_GetDetail.setVisibility(View.VISIBLE);
            progress_Loading_GetDetail.setVisibility(View.VISIBLE);
            btn_Refresh_Loading_GetDetail.setVisibility(View.GONE);
        } else {
            loading_GetDetail.setVisibility(View.GONE);
        }
    }

    /**
     * مربوط به ارور گرفتن جزئیات آگهی
     **/
    @Override
    public void onErrorGetDetail(ResaultCode result) {
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
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();

        progress_Loading_GetDetail.setVisibility(View.GONE);
        btn_Refresh_Loading_GetDetail.setVisibility(View.VISIBLE);
    }

    /**
     * در اینجا جزئیات آگهی ست می شود
     **/
    @Override
    public void onDetailData(VM_PostPowerSupply model) {
        try {

            //نوع آگهی
            if (model.getAdType() != null) {
                cmb_AdType.setSelection(p_addPowerSupply.getPositionAdType(model.getAdType()));
            }

            //سابقه کار
            cmb_WorkExperiences.setSelection(p_addPowerSupply.getPositionWorkExperience(model.getWorkExperiences()));

            //استان
            cmb_Provinces.setSelection(p_addPowerSupply.getPositionState(model.getState()));

            //شهر
//            cmb_City.setSelection(p_addPowerSupply.getPositionCity(model.getCity(), model.getState()));
            positionCitySpinner = p_addPowerSupply.getPositionCity(model.getCity(), model.getState());

            //شغل
            txt_Job.setText(model.getJobTitle());

            //نام
            txt_Name.setText(model.getName());

            //شماره تماس
            txt_CellPhone.setText(model.getCellPhone());

            //توضیحات
            txt_Description.setText(model.getDescription());

            //اگر آگهی در وضعیت انتشار باشد کاربر می تواند آن را ارتقا دهد
            if (model.getStepPower() == StepsAddPower.Post_an_Ad) {
                enableUpgradeOrder(true);
            } else {
                enableUpgradeOrder(false);
            }

            //اگر آگهی ویژه باشد یعنی قبلا ارتقا داده شده و دکمه ارتقا غیرفعال می شود
            if (model.isSpecial()) {
                enableUpgradeOrder(false);
            }

            List<String> paths = model.getImages();
            for (int i = 0; i < paths.size(); i++) {
                try {
                    VM_FileUploadAnalizeTender tender = fileUploadItemAdapter.getItemByPosition(i);
                    tender.setPath(paths.get(i));
                    tender.setType(FileUploadAnalizeTenderType.jpg);
                    fileUploadItemAdapter.addFile(tender);
                } catch (Exception e) {
                }
            }

            btn_ShowSteps.setVisibility(View.GONE);
            step.setVisibility(View.VISIBLE);
            disableAnimationAllSteps();
            onAnimationStep(model.getStepPower(), true);

        } catch (Exception e) {
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            //اگر کاربر از طریق دوربین یک عکس برای آپلود فایل گرفته باشد شرط زیر فراخوانی می شود
            if (requestCode == 3) {
                getByCamera.Continue();
            }

            //اگر کاربر از طریق گالری عکس انتخاب کزده باشد شرط زیر اجرا می شود
            if (requestCode == 4) {
                getByGalery.Continue(data);
            }
        }
    }

    public void saveImage(VM_FileUploadAnalizeTender file, Bitmap bitmap) {

        Dexter.withActivity(getActivity()).withPermissions(imagePermissions).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                ProjectDirectory.createDirectory("paymanyar");

                String name = UUID.randomUUID().toString() + ".jpg";
                name = "paymanyar/" + name;

                String path = SaveImageToMob.SaveImageCamera(name, compressImage.Compress(bitmap));

                File img = new File(path);

                onSelectedFile(file, img);
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

            }
        }).check();
    }

    /**
     * در اینجا المنت ها به صورت انیمیشن پاک می شوند
     **/
    void clear() {
        new Thread(() -> {
            try {

                id = 0;

                Thread.sleep(100);
                clear2("recycler");

                Thread.sleep(100);
                clear2("txt_Description");

                Thread.sleep(100);
                clear2("txt_Job");

                Thread.sleep(100);
                clear2("txt_CellPhone");

                Thread.sleep(100);
                clear2("txt_Name");

                Thread.sleep(100);
                clear2("cmb_City");

                Thread.sleep(100);
                clear2("cmb_Provinces");

                Thread.sleep(100);
                clear2("cmb_WorkExperiences");

                Thread.sleep(100);
                clear2("cmb_AdType");

                Thread.sleep(100);
                clear2("step");

            } catch (Exception e) {
            }
        }).start();
    }

    void clear2(String val) {
        handler_clear.post(() -> {
            switch (val) {
                case "recycler":
                    p_addPowerSupply.getDefaultFileUploadValues();
                    break;
                case "txt_Description":
                    txt_Description.setError(null);
                    txt_Description.setText("");
                    break;
                case "txt_CellPhone":
                    txt_CellPhone.setError(null);
                    txt_CellPhone.setText("");
                    break;
                case "txt_Name":
                    txt_Name.setError(null);
                    txt_Name.setText("");
                    break;
                case "txt_Job":
                    txt_Job.setText("");
                    break;
                case "cmb_City":
                    if (cmb_City.getAdapter() != null) {
                        if (cmb_City.getAdapter().getCount() > 0) {
                            cmb_City.setSelection(0);
                        }
                    }
                    break;
                case "cmb_Provinces":
                    p_addPowerSupply.getProvinces();
                    break;
                case "cmb_WorkExperiences":
                    p_addPowerSupply.getWorkExperiences();
                    break;
                case "cmb_AdType":
                    p_addPowerSupply.getAdTypes();
                    break;
                case "step":
                    disableAnimationAllSteps();
                    step.setVisibility(View.GONE);
                    btn_ShowSteps.setVisibility(View.VISIBLE);
                    enableShowSteps(true);
                    enableUpgradeOrder(false);
                    break;
            }
        });
    }

    /**
     * در اینجا اسکرول صفحه به صورت انیمیشن بالا می رود
     **/
    void scrollToTop() {
        int x = 0;
        int y = 0;

        ObjectAnimator xTranslate = ObjectAnimator.ofInt(nestedScroll, "scrollX", x);
        ObjectAnimator yTranslate = ObjectAnimator.ofInt(nestedScroll, "scrollY", y);

        AnimatorSet animators = new AnimatorSet();
        animators.setDuration(1100L);
        animators.playTogether(xTranslate, yTranslate);

        animators.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator arg0) {
            }

            @Override
            public void onAnimationRepeat(Animator arg0) {
            }

            @Override
            public void onAnimationEnd(Animator arg0) {
            }

            @Override
            public void onAnimationCancel(Animator arg0) {
            }
        });
        animators.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Previous_Orders:
                scrollToTop();
                clear();
                break;
            case R.id.btn_ShowSteps:
                if (getIdItem() == 0) {
                    p_addPowerSupply.addItem();
                }
                break;
            case R.id.btn_UpgradeOrder:
                if (getIdItem() != 0) {
                    AdUpgradeDialog adUpgradeDialog = new AdUpgradeDialog(id -> {
                        enableBankingPortal = true;
                        String url = paymentUrl + "PaymentAd/Index?Parmetr=" + getIdItem() + ":" + id + ":1";
                        openUrl.getWeb(url, getContext());
                    });
                    adUpgradeDialog.show(getActivity().getSupportFragmentManager(), AdUpgradeDialog.TAG);
                }
                break;
            case R.id.btn_Refresh_Loading_GetDetail:
                p_addPowerSupply.start();
                break;
            case R.id.btn_Home:
                ((MainActivity) getActivity()).backToHome();
                break;
            case R.id.btn_Support:
                ((MainActivity) getActivity()).onAddFragment(new SupportFragment(), R.anim.fadein, R.anim.short_fadeout, true, SupportFragment.TAG);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        p_addPowerSupply.cancel(TAG);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }
}
