package ir.tdaapp.paymanyar.View.Fragments;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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
import android.webkit.MimeTypeMap;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


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
import androidx.core.content.FileProvider;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import es.dmoral.toasty.Toasty;
import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;
import ir.tdaapp.li_image.ImagesCodes.CompressImage;
import ir.tdaapp.li_image.ImagesCodes.GetByCamera;
import ir.tdaapp.li_image.ImagesCodes.GetByGalery;
import ir.tdaapp.li_image.ImagesCodes.SaveImageToMob;
import ir.tdaapp.li_utility.Codes.Replace;
import ir.tdaapp.li_utility.Codes.ShowPrice;
import ir.tdaapp.li_utility.Codes.Validation;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Adapters.FileUpload_AnalizeTenderAdapter;
import ir.tdaapp.paymanyar.Model.Enums.FileUploadAnalizeTenderType;
import ir.tdaapp.paymanyar.Model.Enums.StepsAnalizeTender;
import ir.tdaapp.paymanyar.Model.Services.S_SchedulingFragment;
import ir.tdaapp.paymanyar.Model.Services.onClickFileTypeOrderDialog;
import ir.tdaapp.paymanyar.Model.Services.onClickFileUpload_AnalizeTender;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.ProjectDirectory;
import ir.tdaapp.paymanyar.Model.Utilitys.openUrl;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_AnaliseInfo;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FileUploadAnalizeTender;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_InputAnalizeTender;
import ir.tdaapp.paymanyar.Presenter.P_SchedulingFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Dialogs.ErrorAplicationDialog;
import ir.tdaapp.paymanyar.View.Dialogs.FileTypeOrderDialog;
import pl.droidsonroids.gif.GifImageView;

import static android.app.Activity.RESULT_OK;
import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

//صفحه زمانبندی مناقصات
public class SchedulingFragment extends BaseFragment implements S_SchedulingFragment, View.OnClickListener {

    public static final String TAG = "SchedulingFragment";

    Toolbar toolbar;
    P_SchedulingFragment p_schedulingFragment;
    FileUpload_AnalizeTenderAdapter fileUploadAdapter;
    RecyclerView recycler_File;
    CircularProgressButton loadingButton, btn_ShowSteps;
    EditText txt_NationalEstimate;
    RelativeLayout steps;
    Animation aniSlide_up, aniFadeOut, aniFadeIn;
    EditText txt_TenderName, txt_ContractorName, txt_CellPhone, txt_Description;
    int tenderId = 0;
    ErrorAplicationDialog errorAplicationDialog;
    LinearLayout btn_Support, btn_Home;
    CardView btn_Previous_Orders, btn_NewOrder;
    NestedScrollView scroll;
    Handler handler_clear;
    RelativeLayout step_sendOrder, step_orderCheck, step_duration, step_orderCost, step_pay, step_doing;
    GifImageView progress_loading;
    ImageView btn_reload;
    RelativeLayout loading;
    TextView lbl_Time_Pay, lbl_price_order_cost, lbl_Time_duration;
    Chronometer timer;
    String doingTime = "";
    RelativeLayout step_pay_Background, step_orderCheck_Background, step_doing_Background;
    String[] Permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    RadioButton rdo_Detailed_Scheduling, rdo_Initial_Scheduling;
    EditText txt_Delivery_time, txt_Project_duration;
    RadioGroup radioButtonsGroup;

    //آیدی سفارش
    int id = 0;

    //نام فایل نهایی سفارش که کاربر آن را دانلود می کند
    String fileName = "";

    //اگر مقدار زیر ترو باشد یعنی کاربر به صفحه پرداخت رفته است
    boolean isPayment = false;

    String[] imagePermissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    GetByCamera getByCamera;
    GetByGalery getByGalery;
    CompressImage compressImage;
    TextView lbl_doing;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scheduling_fragment, container, false);

        findItem(view);
        implement();
        setToolbar();

        p_schedulingFragment.start();

        return view;
    }

    void implement() {
        p_schedulingFragment = new P_SchedulingFragment(getContext(), this);

        txt_NationalEstimate.addTextChangedListener(new ShowPrice(txt_NationalEstimate));

        btn_ShowSteps.setOnClickListener(this);
        btn_Support.setOnClickListener(this);
        btn_Home.setOnClickListener(this);
        btn_Previous_Orders.setOnClickListener(this);
        btn_NewOrder.setOnClickListener(this);
        btn_reload.setOnClickListener(this);
        step_pay.setOnClickListener(this);
        loadingButton.setOnClickListener(this);
        rdo_Initial_Scheduling.setOnClickListener(this);
        rdo_Detailed_Scheduling.setOnClickListener(this);

        txt_Delivery_time.setOnFocusChangeListener((view, b) -> {
            if (b) {
                showDatePersian();
            }
        });

        aniSlide_up = AnimationUtils.loadAnimation(getContext(), R.anim.slide_up);
        aniFadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.long_fadeout);
        aniFadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.fadein);

        handler_clear = new Handler(Looper.getMainLooper());
        timer.setText("");

        compressImage = new CompressImage(480, 780, 100, getContext());

    }

    void findItem(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        recycler_File = view.findViewById(R.id.recycler_File);
        loadingButton = view.findViewById(R.id.loadingButton);
        txt_NationalEstimate = view.findViewById(R.id.txt_NationalEstimate);
        btn_ShowSteps = view.findViewById(R.id.btn_ShowSteps);
        steps = view.findViewById(R.id.steps);
        txt_TenderName = view.findViewById(R.id.txt_TenderName);
        txt_ContractorName = view.findViewById(R.id.txt_ContractorName);
        txt_CellPhone = view.findViewById(R.id.txt_CellPhone);
        txt_Description = view.findViewById(R.id.txt_Description);
        btn_Support = view.findViewById(R.id.btn_Support);
        btn_Home = view.findViewById(R.id.btn_Home);
        btn_Previous_Orders = view.findViewById(R.id.btn_Previous_Orders);
        btn_NewOrder = view.findViewById(R.id.btn_NewOrder);
        scroll = view.findViewById(R.id.scroll);
        step_sendOrder = view.findViewById(R.id.step_sendOrder);
        step_orderCheck = view.findViewById(R.id.step_orderCheck);
        step_duration = view.findViewById(R.id.step_duration);
        step_orderCost = view.findViewById(R.id.step_orderCost);
        step_pay = view.findViewById(R.id.step_pay);
        step_doing = view.findViewById(R.id.step_doing);
        progress_loading = view.findViewById(R.id.progress_loading);
        btn_reload = view.findViewById(R.id.btn_reload);
        loading = view.findViewById(R.id.loading);
        lbl_Time_Pay = view.findViewById(R.id.lbl_Time_Pay);
        lbl_price_order_cost = view.findViewById(R.id.lbl_price_order_cost);
        lbl_Time_duration = view.findViewById(R.id.lbl_Time_duration);
        timer = view.findViewById(R.id.timer);
        step_pay_Background = view.findViewById(R.id.step_pay_Background);
        step_orderCheck_Background = view.findViewById(R.id.step_orderCheck_Background);
        step_doing_Background = view.findViewById(R.id.step_doing_Background);
        rdo_Detailed_Scheduling = view.findViewById(R.id.rdo_Detailed_Scheduling);
        rdo_Initial_Scheduling = view.findViewById(R.id.rdo_Initial_Scheduling);
        txt_Delivery_time = view.findViewById(R.id.txt_Delivery_time);
        txt_Project_duration = view.findViewById(R.id.txt_Project_duration);
        radioButtonsGroup = view.findViewById(R.id.radioButtonsGroup);
        lbl_doing = view.findViewById(R.id.lbl_doing);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ShowSteps:
                p_schedulingFragment.addOrder();
                break;
            case R.id.btn_Home:
                ((MainActivity) getActivity()).backToHome();
                break;
            case R.id.btn_Support:
                ((MainActivity) getActivity()).onAddFragment(new SupportFragment(), R.anim.fadein, R.anim.short_fadeout, true, SupportFragment.TAG);
                break;
            case R.id.btn_Previous_Orders:
                ((MainActivity) getActivity()).onAddFragment(new OrdersFragment(), R.anim.fadein, R.anim.short_fadeout, true, OrdersFragment.TAG);
                break;
            case R.id.rdo_Initial_Scheduling:
            case R.id.rdo_Detailed_Scheduling:
                rdo_Initial_Scheduling.setError(null);
                rdo_Detailed_Scheduling.setError(null);
                break;
            case R.id.btn_NewOrder:
                doingTime = "00:00:00";
                timer.stop();
                fileUploadAdapter.setEnabled(true);
                step_pay.setEnabled(false);
                loadingButton.setEnabled(false);
                tenderId = 0;
                id = 0;
                scrollToTop();
                clear();
                break;
            case R.id.btn_reload:
                p_schedulingFragment.getDetailItem();
                break;
            case R.id.step_pay:
                step_pay.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.click_item));
                isPayment = true;
                if (id != 0) {
                    String url = paymentUrl+"PaymentOrder/Index?OrderId=" + id;
                    openUrl.getWeb(url, getContext());
                }
                break;
            case R.id.loadingButton:

                Dexter.withActivity(getActivity()).withPermissions(Permissions).withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (!fileName.equalsIgnoreCase("")) {

                            String t = "";
                            try {
                                String[] a = fileName.split("/");
                                t = a[1];
                            } catch (Exception e) {
                            }
                            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), t);

                            //در اینجا چک می شود اگر قبلا این فایل دانلود شده است آن را نمایش می دهد در غیر این صورت آن را دانلود می کند
                            if (file.exists()) {
                                onShowFile(t);
                            } else {
                                p_schedulingFragment.downloadFile(t);
                            }
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }
                }).check();
                break;
        }
    }

    @Override
    public void OnStart() {

    }

    //در اینجا مقادیر فایل ها ست می شود
    @Override
    public void onValuesFiles(List<VM_FileUploadAnalizeTender> vals) {
        fileUploadAdapter = new FileUpload_AnalizeTenderAdapter(getContext(), vals);
        recycler_File.setAdapter(fileUploadAdapter);
        recycler_File.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        fileUploadAdapter.setClickFileUpload_analizeTender(new onClickFileUpload_AnalizeTender() {
            @Override
            public void onClickFile(VM_FileUploadAnalizeTender file) {
                FileTypeOrderDialog fileTypeOrderDialog = new FileTypeOrderDialog(new onClickFileTypeOrderDialog() {
                    @Override
                    public void clickedCamera() {
                        getByCamera = new GetByCamera(getActivity(), 1, image -> {
                            saveImage(file, image);
                        });
                    }

                    @Override
                    public void clickedGallery() {
                        getByGalery = new GetByGalery(getActivity(), 2, image -> {
                            saveImage(file, image);
                        });
                    }

                    @Override
                    public void clickedFile() {
                        p_schedulingFragment.openFile(getActivity(), file);
                    }
                });

                fileTypeOrderDialog.show(getActivity().getSupportFragmentManager(), FileTypeOrderDialog.TAG);
            }

            @Override
            public void onClickClose(VM_FileUploadAnalizeTender file) {

            }
        });
    }

    //زمانی که کاربر یک فایل انتخاب کند متد زیر فراخوانی می شود
    @Override
    public void onSelectedFile(VM_FileUploadAnalizeTender val, File file) {
        p_schedulingFragment.checkValidationFile(file, val);
    }

    //اگر فایل انتخاب شده ولید باشد متد زیر فراخوانی می شود
    @Override
    public void onValidFile(VM_FileUploadAnalizeTender val, File file) {

        val.setType(p_schedulingFragment.getTypeFile(file));

        if (val.getType() != FileUploadAnalizeTenderType.empty) {
            val.setPath(file.getPath());
            fileUploadAdapter.addFile(val);
        } else {
            Toasty.error(getContext(), getString(R.string.error_In_Your_File), Toast.LENGTH_SHORT,true).show();
        }
    }

    //اگر فایل انتخاب شده ولید نباشد متد زیر فراخوانی می شود
    @Override
    public void onNotValidFile(String errorText) {
        Toasty.error(getContext(), errorText, Toast.LENGTH_SHORT,true).show();
    }

    //در اینجا آدرس فایل ها برگشت داده می شود
    @Override
    public List<String> onGetUrlPaths() {
        return fileUploadAdapter.getPathFiles();
    }

    @Override
    public void onLoading(boolean isLoad) {
        if (isLoad) {
            btn_ShowSteps.startAnimation();
        } else {
            btn_ShowSteps.revertAnimation();
        }
    }

    //اگر کاربر حساب کاربری نداشته باشد متد زیر فراخوانی می شود
    @Override
    public void onNoAccount() {
        Toasty.error(getContext(), getString(R.string.Create_an_account_first), Toast.LENGTH_SHORT,true).show();
        ((MainActivity) getActivity()).onAddFragment(new LoginFragment(), R.anim.fadein, R.anim.short_fadeout, true, LoginFragment.TAG);
    }

    //در اینجا مقادیر سفارش برای ارسال به سرور گرفته می شوند
    @Override
    public VM_InputAnalizeTender onGetInputAnalize(List<String> fileUrls) {

        VM_InputAnalizeTender input = new VM_InputAnalizeTender();

        try {

            //در اینجا نام مناقصه ست می شود
            input.setTenderName(txt_TenderName.getText().toString());

            //در اینجا برآورد مالی ست می شود
            input.setFee(Replace.Number_fn_To_en(txt_NationalEstimate.getText().toString().replace(",", "").replace("٬", "")));

            //در اینجا نام پیمانکار ست می شود
            input.setContractorName(txt_ContractorName.getText().toString());

            //در اینجا شماره تماس ست می شود
            input.setCellPhone(txt_CellPhone.getText().toString());

            //در اینجا توضیحات ست می شود
            input.setDescription(txt_Description.getText().toString());

            //در اینجا آیدی مناقصه ست می شود
            input.setTenderId(tenderId);

            //در اینجا آیدی کاربر ست می شود
            input.setUserId(((MainActivity) getActivity()).getTbl_user().getUserId(getContext()));

            input.setDetailedSchedule(rdo_Detailed_Scheduling.isChecked());
            input.setInitialSchedule(rdo_Initial_Scheduling.isChecked());
            input.setLandDeliveryDate(txt_Delivery_time.getText().toString());
            try {
                input.setProjectDuration(Integer.valueOf(txt_Project_duration.getText().toString()));
            } catch (Exception e) {
            }

            //در اینجا آدرس فایل ها ست می شود
            for (int i = 0; i < fileUrls.size(); i++) {
                if (i == 0) {
                    input.setFileUrl1(fileUrls.get(i));
                }
                if (i == 1) {
                    input.setFileUrl2(fileUrls.get(i));
                }
                if (i == 2) {
                    input.setFileUrl3(fileUrls.get(i));
                }
                if (i == 3) {
                    input.setFileUrl4(fileUrls.get(i));
                }
                if (i == 4) {
                    input.setFileUrl5(fileUrls.get(i));
                }
                if (i == 5) {
                    input.setFileUrl6(fileUrls.get(i));
                }
                if (i == 6) {
                    input.setFileUrl7(fileUrls.get(i));
                }
                if (i == 7) {
                    input.setFileUrl8(fileUrls.get(i));
                }
                if (i == 8) {
                    input.setFileUrl9(fileUrls.get(i));
                }
                if (i == 9) {
                    input.setFileUrl10(fileUrls.get(i));
                }
            }

        } catch (Exception e) {
        }

        return input;
    }

    //در اینجا ولید بودن ورودی ها برای ارسال سفارش چک می شود
    @Override
    public boolean isValidInputs() {

        boolean valid = true;

        if (!Validation.Required(txt_TenderName, getString(R.string.ThisValueMust_be_Filled))) {
            valid = false;
        }

        if (!Validation.Required(txt_NationalEstimate, getString(R.string.ThisValueMust_be_Filled))) {
            valid = false;
        }

        if (!Validation.Required(txt_CellPhone, getString(R.string.ThisValueMust_be_Filled))) {
            valid = false;
        } else {
            if (!Validation.ValidPhoneNumber(txt_CellPhone, getString(R.string.phoneNumberIsNotValid))) {
                valid = false;
            }
        }

        if (!Validation.Required(txt_Delivery_time, getString(R.string.ThisValueMust_be_Filled))) {
            valid = false;
        }

        if (!Validation.Required(txt_Project_duration, getString(R.string.ThisValueMust_be_Filled))) {
            valid = false;
        }

        if (!rdo_Initial_Scheduling.isChecked() && !rdo_Detailed_Scheduling.isChecked()) {
            rdo_Initial_Scheduling.setError("");
            rdo_Detailed_Scheduling.setError("");
            valid = false;
        }

        return valid;
    }

    //اگر مقادیر ولید نباشند متد زیر فراخوانی می شوند
    @Override
    public void onNotValid() {
        Toasty.error(getContext(), getString(R.string.Please_enter_full_values), Toast.LENGTH_SHORT,true).show();
    }

    @Override
    public void onError(ResaultCode resaultCode) {

        String text = "";

        switch (resaultCode) {
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

    //زمانی که عملیات افزودن سفارش با موفقیت انجام شود متد زیر فراخوانی می شود
    @Override
    public void onSuccess() {
        btn_ShowSteps.revertAnimation(() -> {
            btn_ShowSteps.setAnimation(aniFadeOut);
            btn_ShowSteps.setVisibility(View.GONE);

            //ابتدا رنگ تمام استپ ها به رنگ غیر فعال تغییر می کند
            setDisableColorSteps();

            //در اینجا انیمیشن استپ ها غیر فعال می شوند
            disableStepAnimation();

            step_sendOrder.setBackground(getActivity().getResources().getDrawable(R.drawable.background_enable_step));
            step_orderCheck_Background.setBackground(getActivity().getResources().getDrawable(R.drawable.background_enable_step));
            onAnimation_Step_pay(StepsAnalizeTender.orderCheck, true);

            step_pay.setEnabled(false);
            fileUploadAdapter.setEnabled(false);
            loadingButton.setEnabled(false);

            steps.setAnimation(aniSlide_up);
            steps.setVisibility(View.VISIBLE);
        });
    }

    //اگر کاربر در صفحه مناقصات با زدن دکمه آنالیز وارد شود در اینجا آن مقادیر در ادیت تکست ها ست می شوند
    @Override
    public void onSetDetailsData() {
        try {

            Bundle bundle = getArguments();

            //در اینجا آیدی مناقصه گرفته می شود
            if (bundle != null) {

                //اگر شرط زیر درست باشد یعنی کاربر از صفحه سفارشات وارد شده است
                //در غیر این صورت کاربر از طریق دیگری وارد شده است
                if (bundle.get("Id") != null) {

                    id = bundle.getInt("Id");

                    //در اینجا فایل آپلود غیر فعال می شود
                    if (fileUploadAdapter != null) {
                        fileUploadAdapter.setEnabled(false);
                    }

                    p_schedulingFragment.getDetailItem();

                } else {
                    tenderId = bundle.getInt("tenderId");

                    //در اینجا نام مناقصه گرفته می شود
                    if (bundle.getString("tenderName") != null) {
                        txt_TenderName.setText(bundle.getString("tenderName"));
                    }

                    //در اینجا برآورد مالی ست می شود
                    if (bundle.getString("fee") != null) {
                        new ShowPrice(txt_NationalEstimate);
                        txt_NationalEstimate.setText(bundle.getString("fee"));
                    }

                    //در اینجا توضیحات ست می شود
                    if (bundle.getString("description") != null) {
                        txt_Description.setText(bundle.getString("description"));
                    }
                }
            }

        } catch (Exception e) {
        }
    }

    //در اینجا آیدی آیتم پاس برگشت داده می شود
    @Override
    public int onItemId() {
        return id;
    }

    //مربوط به لودینگ گرفتن جزئیات سفارش
    @Override
    public void onLoadingGetItem(boolean load) {
        if (load) {
            btn_reload.setVisibility(View.GONE);
            progress_loading.setVisibility(View.VISIBLE);
        } else {
            progress_loading.setVisibility(View.GONE);
        }
    }

    //مربوط به ارور، گرفتن جزئیات سفارش
    @Override
    public void onErrorGetItem(ResaultCode result) {

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

        Toasty.error(getContext(), text, Toast.LENGTH_SHORT,true).show();

        btn_reload.setVisibility(View.VISIBLE);
        progress_loading.setVisibility(View.GONE);
    }

    //در اینجا دیالوگ رلود نمایش داده می شود
    @Override
    public void onShowReloadDialog(boolean show) {
        if (show) {
            loading.setVisibility(View.VISIBLE);
        } else {
            loading.setVisibility(View.GONE);
        }
    }

    //در اینجا مشخصات سفارش در المنت ها ست می شود
    @Override
    public void onSetAnaliseInfo(VM_AnaliseInfo analiseInfo) {

        try {

            if (analiseInfo.getTenderName() != null) {
                if (!analiseInfo.getTenderName().equalsIgnoreCase("null"))
                    txt_TenderName.setText(analiseInfo.getTenderName());
            }

            if (analiseInfo.getNationalEstimate() != null) {
                if (!analiseInfo.getNationalEstimate().equalsIgnoreCase("null"))
                    txt_NationalEstimate.setText(analiseInfo.getNationalEstimate());
            }

            if (analiseInfo.getProjectName() != null) {
                if (!analiseInfo.getProjectName().equalsIgnoreCase("null"))
                    txt_TenderName.setText(analiseInfo.getProjectName());
            }

            if (analiseInfo.getPhoneNumber() != null) {
                if (!analiseInfo.getPhoneNumber().equalsIgnoreCase("null"))
                    txt_CellPhone.setText(analiseInfo.getPhoneNumber());
            }

            if (analiseInfo.getDescription() != null) {
                if (!analiseInfo.getDescription().equalsIgnoreCase("null"))
                    txt_Description.setText(analiseInfo.getDescription());
            }

            if (analiseInfo.getContractorName() != null) {
                if (!analiseInfo.getContractorName().equalsIgnoreCase("null"))
                    txt_ContractorName.setText(analiseInfo.getContractorName());
            }

            if (analiseInfo.getTimePay() != null) {
                if (!analiseInfo.getTimePay().equalsIgnoreCase("null"))
                    lbl_Time_Pay.setText(analiseInfo.getTimePay());
            }

            if (analiseInfo.getAmountPayable() != null) {
                if (!analiseInfo.getAmountPayable().equalsIgnoreCase("null") && !analiseInfo.getAmountPayable().equalsIgnoreCase(""))
                    lbl_price_order_cost.setText(analiseInfo.getAmountPayable() + " " + getString(R.string.Toman));
            }

            if (analiseInfo.getDoingTime() != null) {
                if (!analiseInfo.getDoingTime().equalsIgnoreCase("null")) {
                    lbl_Time_duration.setText(analiseInfo.getDoingTime() + " " + getString(R.string.Hour));
                }
            }

            //در اینجا نام فایل برای دانلود گرفته می شود
            if (analiseInfo.getFileName() != null) {
                if (!analiseInfo.getFileName().equalsIgnoreCase("null")) {
                    try {
                        fileName = analiseInfo.getFileName();
                    } catch (Exception e) {
                    }
                }
            }

            //در اینجا رادیوباتن های تفصیلی و اولیه ست می شوند
            radioButtonsGroup.clearCheck();
            rdo_Detailed_Scheduling.setChecked(analiseInfo.isDetailedSchedule());
            rdo_Initial_Scheduling.setChecked(analiseInfo.isInitialSchedule());
            ///////////////////

            //در اینجا ادیت تکست تاریخ تحویل زمین ست می شود
            if (analiseInfo.getLandDeliveryDate() != null) {
                if (!analiseInfo.getLandDeliveryDate().equalsIgnoreCase("null")) {
                    txt_Delivery_time.setText(analiseInfo.getLandDeliveryDate());
                }
            }

            //در اینجا مدت زمان پروژه ست می شود
            txt_Project_duration.setText(analiseInfo.getProjectDuration() + "");

            //در اینجا فایل ها ست می شوند
            if (analiseInfo.getFileUrls() != null) {

                List<String> files = analiseInfo.getFileUrls();

                for (int i = 0; i < files.size(); i++) {
                    try {
                        VM_FileUploadAnalizeTender file = fileUploadAdapter.getFileByPosition(i);
                        String name = files.get(i);

                        String[] u = name.split("\\.");
                        if (u.length > 0) {
                            String format = u[u.length - 1];
                            FileUploadAnalizeTenderType type = p_schedulingFragment.getTypeFile(format);
                            file.setPath(name);
                            file.setType(type);
                            fileUploadAdapter.addFile(file);
                        }
                    } catch (Exception e) {
                    }
                }
            }

            btn_ShowSteps.setVisibility(View.GONE);
            if (analiseInfo.getStep() != null) {

                //ابتدا رنگ تمام استپ ها به رنگ غیر فعال تغییر می کند
                setDisableColorSteps();

                loadingButton.setEnabled(false);

                //در اینجا انیمیشن استپ ها غیر فعال می شوند
                disableStepAnimation();

                step_pay.setEnabled(false);

                steps.setVisibility(View.VISIBLE);

                lbl_doing.setTextColor(getResources().getColor(R.color.colorTitle));
                lbl_doing.setText(getString(R.string.doing));

                //در اینجا تایمر ست می شود
                if (analiseInfo.getTimer() != null) {
                    if (!analiseInfo.getTimer().equalsIgnoreCase("") && !analiseInfo.getTimer().equalsIgnoreCase("null")) {
                        startTimer(analiseInfo.getTimer().replace(",", ":"));
                    }else if (analiseInfo.getTimer().equalsIgnoreCase("")){
                        lbl_doing.setText(getString(R.string.as_soon_as_possible));
                        lbl_doing.setTextColor(getResources().getColor(R.color.colorError));
                    }else if (analiseInfo.getTimer().equalsIgnoreCase("null")){
                        lbl_doing.setText(getString(R.string.Done));
                        lbl_doing.setTextColor(getResources().getColor(R.color.colorError));
                    }
                }

                switch (analiseInfo.getStep()) {
                    case sendOrder:
                    case orderCheck:
                        step_sendOrder.setBackground(getActivity().getResources().getDrawable(R.drawable.background_enable_step));
                        step_orderCheck_Background.setBackground(getActivity().getResources().getDrawable(R.drawable.background_enable_step));
                        onAnimation_Step_pay(analiseInfo.getStep(), true);
                        lbl_doing.setText("");
                        break;
                    case duration:
                    case orderCost:
                    case pay:
                        step_sendOrder.setBackground(getActivity().getResources().getDrawable(R.drawable.background_enable_step));
                        step_orderCheck_Background.setBackground(getActivity().getResources().getDrawable(R.drawable.background_enable_step));
                        step_duration.setBackground(getActivity().getResources().getDrawable(R.drawable.background_enable_step));
                        step_orderCost.setBackground(getActivity().getResources().getDrawable(R.drawable.background_enable_step));
                        step_pay_Background.setBackground(getActivity().getResources().getDrawable(R.drawable.background_enable_step));

                        step_pay.setEnabled(true);
                        onAnimation_Step_pay(analiseInfo.getStep(), true);
                        lbl_doing.setText("");
                        break;
                    case doing:
                        step_sendOrder.setBackground(getActivity().getResources().getDrawable(R.drawable.background_enable_step));
                        step_orderCheck_Background.setBackground(getActivity().getResources().getDrawable(R.drawable.background_enable_step));
                        step_duration.setBackground(getActivity().getResources().getDrawable(R.drawable.background_enable_step));
                        step_orderCost.setBackground(getActivity().getResources().getDrawable(R.drawable.background_enable_step));
                        step_pay_Background.setBackground(getActivity().getResources().getDrawable(R.drawable.background_enable_step));
                        step_doing_Background.setBackground(getActivity().getResources().getDrawable(R.drawable.background_enable_step));

                        onAnimation_Step_pay(analiseInfo.getStep(), true);
                        break;
                    case takingOrders:
                        step_sendOrder.setBackground(getActivity().getResources().getDrawable(R.drawable.background_enable_step));
                        step_orderCheck_Background.setBackground(getActivity().getResources().getDrawable(R.drawable.background_enable_step));
                        step_duration.setBackground(getActivity().getResources().getDrawable(R.drawable.background_enable_step));
                        step_orderCost.setBackground(getActivity().getResources().getDrawable(R.drawable.background_enable_step));
                        step_pay_Background.setBackground(getActivity().getResources().getDrawable(R.drawable.background_enable_step));
                        step_doing_Background.setBackground(getActivity().getResources().getDrawable(R.drawable.background_enable_step));
                        lbl_doing.setTextColor(getResources().getColor(R.color.colorError));
                        lbl_doing.setText(getString(R.string.Done));

                        loadingButton.setBackground(getActivity().getResources().getDrawable(R.drawable.circular_border_shape_enable));
                        loadingButton.setEnabled(true);
                        break;
                }

            }

        } catch (Exception e) {
            int a = 1;
            a++;
        }

    }

    //در اینجا انیمیشن تمام استپ ها غیر فعال می شوند
    void disableStepAnimation() {
        onAnimation_Step_pay(StepsAnalizeTender.sendOrder, false);
        onAnimation_Step_pay(StepsAnalizeTender.orderCheck, false);
        onAnimation_Step_pay(StepsAnalizeTender.duration, false);
        onAnimation_Step_pay(StepsAnalizeTender.doing, false);
        onAnimation_Step_pay(StepsAnalizeTender.pay, false);
        onAnimation_Step_pay(StepsAnalizeTender.orderCost, false);
        onAnimation_Step_pay(StepsAnalizeTender.takingOrders, false);
    }

    //در اینجا رنگ تمام استپ ها به رنگ غیرفعال تغییر می کند
    void setDisableColorSteps() {
        step_sendOrder.setBackground(getActivity().getResources().getDrawable(R.drawable.background_disable_step));
        step_orderCheck_Background.setBackground(getActivity().getResources().getDrawable(R.drawable.background_disable_step));
        step_duration.setBackground(getActivity().getResources().getDrawable(R.drawable.background_disable_step));
        step_orderCost.setBackground(getActivity().getResources().getDrawable(R.drawable.background_disable_step));
        step_pay_Background.setBackground(getActivity().getResources().getDrawable(R.drawable.background_disable_step));
        step_doing_Background.setBackground(getActivity().getResources().getDrawable(R.drawable.background_disable_step));
        loadingButton.setBackground(getActivity().getResources().getDrawable(R.drawable.circular_border_shape_disable));
    }

    //در اینجا تنظیمات تولبار ست می شود
    void setToolbar() {
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setTitle(getContext().getResources().getString(R.string.Schedule));
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });
        setHasOptionsMenu(true);
    }

    //در اینجا اسکرول به صورت انیمیشن بالا می رود
    void scrollToTop() {
        int x = 0;
        int y = 0;

        ObjectAnimator xTranslate = ObjectAnimator.ofInt(scroll, "scrollX", x);
        ObjectAnimator yTranslate = ObjectAnimator.ofInt(scroll, "scrollY", y);

        AnimatorSet animators = new AnimatorSet();
        animators.setDuration(1200L);
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
    public void onDestroy() {
        super.onDestroy();
        p_schedulingFragment.cancel(TAG);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }

    //در دو متد زیر عملیات پاکسازی تمامی داده های جاری در المنت ها پاک می شود
    void clear() {
        new Thread(() -> {
            try {

                clear2("stepAnimation");

                Thread.sleep(100);
                clear2("stepItems");

                Thread.sleep(100);
                clear2("txt_Project_duration");

                Thread.sleep(100);
                clear2("txt_Delivery_time");

                Thread.sleep(100);
                clear2("radioButtonsGroup");

                Thread.sleep(100);
                clear2("fileUploadAdapter");

                Thread.sleep(100);
                clear2("txt_Description");

                Thread.sleep(100);
                clear2("txt_CellPhone");

                Thread.sleep(100);
                clear2("txt_ContractorName");

                Thread.sleep(100);
                clear2("txt_NationalEstimate");

                Thread.sleep(100);
                clear2("txt_TenderName");

                Thread.sleep(500);
                clear2("steps");

            } catch (Exception e) {
            }
        }).start();
    }

    void clear2(String val) {
        handler_clear.post(() -> {
            switch (val) {
                case "fileUploadAdapter":
                    fileUploadAdapter.clearAll();
                    break;
                case "txt_Description":
                    txt_Description.setError(null);
                    txt_Description.setText("");
                    break;
                case "txt_CellPhone":
                    txt_CellPhone.setError(null);
                    txt_CellPhone.setText("");
                    break;
                case "txt_ContractorName":
                    txt_ContractorName.setError(null);
                    txt_ContractorName.setText("");
                    break;
                case "txt_NationalEstimate":
                    txt_NationalEstimate.setError(null);
                    txt_NationalEstimate.setText("");
                    break;
                case "txt_TenderName":
                    txt_TenderName.setError(null);
                    txt_TenderName.setText("");
                    break;
                case "steps":
                    if (steps.getVisibility() == View.VISIBLE) {
                        steps.setAnimation(aniFadeOut);
                        steps.setVisibility(View.GONE);

                        btn_ShowSteps.setAnimation(aniFadeIn);
                        btn_ShowSteps.setVisibility(View.VISIBLE);
                    }
                    break;
                case "stepItems":
                    timer.setText("");
                    lbl_Time_duration.setText("");
                    lbl_Time_Pay.setText("");
                    lbl_price_order_cost.setText("");
                    break;
                case "stepAnimation":
                    disableStepAnimation();
                    break;
                case "radioButtonsGroup":
                    rdo_Initial_Scheduling.setError(null);
                    rdo_Detailed_Scheduling.setError(null);
                    radioButtonsGroup.clearCheck();
                    break;
                case "txt_Delivery_time":
                    txt_Delivery_time.setError(null);
                    txt_Delivery_time.setText("");
                    break;
                case "txt_Project_duration":
                    txt_Project_duration.setError(null);
                    txt_Project_duration.setText("");
                    break;
            }
        });
    }

    //مربوط به تایمر استپ در حال انجام
    @Override
    public void startTimer(String time) {
        doingTime = time;
        timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                try {

                    String[] t = doingTime.split(":");

                    int h, m, s;

                    //در اینجا ساعت گرفته می شود
                    h = Integer.valueOf(t[0].trim());

                    //در اینجا دقیقه گرفته می شود
                    m = Integer.valueOf(t[1].trim());

                    //در اینجا ثانیه گرفته می شود
                    s = Integer.valueOf(t[2].trim());

                    h = h < 0 ? 0 : h;
                    m = m < 0 ? 0 : m;
                    s = s < 0 ? 0 : s;

                    //در اینجا ساعت و دقیقه و ثانیه تنظیم می شود
                    if (h > 0 || m > 0 || s > 0) {
                        if (s > 0) {
                            s--;
                        } else {
                            if (m > 0) {
                                m--;
                            } else {
                                if (h > 0) {
                                    h--;
                                } else {
                                    h = 00;
                                }
                                m = 59;
                            }
                            s = 59;
                        }
                        ////////////////////////////////////////////

                        //در اینجا اگر زمان مثل ساعت یا دقیقه یا ثانیه تک رقمی باشد یک صفر به پشت آن اضافه می شود
                        String hh, mm, ss;

                        if (h >= 0 && h < 10) {
                            hh = "0" + h;
                        } else {
                            hh = h + "";
                        }

                        if (m >= 0 && m < 10) {
                            mm = "0" + m;
                        } else {
                            mm = m + "";
                        }

                        if (s >= 0 && s < 10) {
                            ss = "0" + s;
                        } else {
                            ss = s + "";
                        }
                        ///////////////////////////////////////////////////////////////

                        //در اینجا زمان ها نمایش داده می شوند
                        doingTime = hh + " : " + mm + " : " + ss;
                        chronometer.setText(Replace.Number_en_To_fa(doingTime));
                    } else {
                        doingTime = "00 : 00 : 00";
                        chronometer.setText(getString(R.string.as_soon_as_possible));
                        timer.stop();
                    }

                } catch (Exception e) {
                    chronometer.setText(Replace.Number_en_To_fa(doingTime));
                }
            }
        });
        timer.start();
    }

    //برای نمایش انیمیشن پرداخت هزینه
    @Override
    public void onAnimation_Step_pay(StepsAnalizeTender step, boolean enablesAnim) {

        if (enablesAnim) {
            Animation anim = new AlphaAnimation(0.0f, 1.0f);
            anim.setDuration(500);
            anim.setRepeatMode(Animation.REVERSE);
            anim.setRepeatCount(Animation.INFINITE);

            if (step == StepsAnalizeTender.sendOrder || step == StepsAnalizeTender.orderCheck) {
                step_orderCheck_Background.startAnimation(anim);
            }

            if (step == StepsAnalizeTender.duration || step == StepsAnalizeTender.orderCost || step == StepsAnalizeTender.pay) {
                step_pay_Background.startAnimation(anim);
            }

            if (step == StepsAnalizeTender.doing) {
                step_doing_Background.startAnimation(anim);
            }

        } else {

            if (step == StepsAnalizeTender.sendOrder || step == StepsAnalizeTender.orderCheck) {
                step_orderCheck_Background.setAnimation(null);
            }

            if (step == StepsAnalizeTender.duration || step == StepsAnalizeTender.orderCost || step == StepsAnalizeTender.pay) {
                step_pay_Background.setAnimation(null);
            }

            if (step == StepsAnalizeTender.doing) {
                step_doing_Background.setAnimation(null);
            }

        }

    }

    @Override
    public String onGetFileName() {
        return fileName;
    }

    //مربوط به لودینگ دکمه دانلود فایل
    @Override
    public void onLoadingDownloadFile(boolean load) {
        if (load) {
            loadingButton.startAnimation();
        } else {
            loadingButton.revertAnimation();
        }
    }

    //اگر در دانلود فایل خطای رخ دهد متد زیر فراخوانی می شود
    @Override
    public void onErrorDownloadFile(Throwable e) {
        Toasty.error(getContext(), getString(R.string.There_Was_an_Error_In_The_Application), Toast.LENGTH_SHORT,true).show();
    }

    //در اینجا فایل نمایش داده می شود
    @Override
    public void onShowFile(String fileName) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);

        if (file.exists()) {

            try {
                String extension = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(file).toString());
                String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_NEW_TASK);
                Uri uri = FileProvider.getUriForFile(getContext(), getActivity().getApplicationContext().getPackageName() + ".provider", file);

                intent.setDataAndType(uri, mimeType);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                startActivity(Intent.createChooser(intent, getString(R.string.ChoseApp)));
            } catch (Exception e) {
                Toasty.error(getContext(), getString(R.string.There_Was_an_Error_In_The_Application), Toast.LENGTH_SHORT).show();
            }

        } else {
            Toasty.error(getContext(), getString(R.string.notFoundThisPDF), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (id != 0 && isPayment) {
            isPayment = false;
            p_schedulingFragment.getDetailItem();
        }
    }

    //در اینجا دیالوگ تاریخ فارسی نمایش داده می شود
    void showDatePersian() {
        PersianDatePickerDialog picker = p_schedulingFragment.getDatePicker();
        picker.setListener(new Listener() {
            @Override
            public void onDateSelected(PersianCalendar persianCalendar) {
                txt_Delivery_time.setError(null);
                txt_Delivery_time.setText(persianCalendar.getPersianYear() + "/" + persianCalendar.getPersianMonth() + "/" + persianCalendar.getPersianDay());
            }

            @Override
            public void onDismissed() {

            }
        });
        picker.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            //اگر کاربر از طریق دوربین یک عکس برای آپلود فایل گرفته باشد شرط زیر فراخوانی می شود
            if (requestCode == 1) {
                getByCamera.Continue();
            }

            //اگر کاربر از طریق گالری عکس انتخاب کزده باشد شرط زیر اجرا می شود
            if (requestCode == 2) {
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

}
