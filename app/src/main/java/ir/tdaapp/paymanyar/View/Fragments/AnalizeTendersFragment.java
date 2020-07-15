package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import ir.tdaapp.li_utility.Codes.ShowPrice;
import ir.tdaapp.li_utility.Codes.Validation;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Adapters.FileUpload_AnalizeTenderAdapter;
import ir.tdaapp.paymanyar.Model.Enums.FileUploadAnalizeTenderType;
import ir.tdaapp.paymanyar.Model.Services.S_AnalizeTenders;
import ir.tdaapp.paymanyar.Model.Services.onClickFileUpload_AnalizeTender;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FileUploadAnalizeTender;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_InputAnalizeTender;
import ir.tdaapp.paymanyar.Presenter.P_AnalizeTenders;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Dialogs.ErrorAplicationDialog;

//مربوط به صفحه آنالیز مناقصات
public class AnalizeTendersFragment extends BaseFragment implements S_AnalizeTenders, View.OnClickListener {

    public static final String TAG = "AnalizeTendersFragment";

    Toolbar toolbar;
    P_AnalizeTenders p_analizeTenders;
    FileUpload_AnalizeTenderAdapter fileUploadAdapter;
    RecyclerView recycler_File;
    CircularProgressButton loadingButton, btn_ShowSteps;
    EditText txt_percent1, txt_percent2, txt_percent3, txt_percent4, txt_percent5;
    EditText txt_percent6, txt_percent7, txt_percent8, txt_percent9, txt_percent10;
    EditText txt_NationalEstimate;
    RelativeLayout steps;
    Animation aniFadeIn, aniFadeOut;
    EditText txt_TenderName, txt_ContractorName, txt_CellPhone, txt_Description;
    int tenderId = 0;
    ErrorAplicationDialog errorAplicationDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.analize_tenders, container, false);

        findItem(view);
        implement();
        setToolbar();

        p_analizeTenders.start();

        return view;
    }

    void implement() {
        p_analizeTenders = new P_AnalizeTenders(getContext(), this);

        txt_NationalEstimate.addTextChangedListener(new ShowPrice(txt_NationalEstimate));
        btn_ShowSteps.setOnClickListener(this);

        aniFadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.slide_up);
        aniFadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.long_fadeout);
    }

    void findItem(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        recycler_File = view.findViewById(R.id.recycler_File);
        loadingButton = view.findViewById(R.id.loadingButton);
        txt_percent1 = view.findViewById(R.id.txt_percent1);
        txt_percent2 = view.findViewById(R.id.txt_percent2);
        txt_percent3 = view.findViewById(R.id.txt_percent3);
        txt_percent4 = view.findViewById(R.id.txt_percent4);
        txt_percent5 = view.findViewById(R.id.txt_percent5);
        txt_percent6 = view.findViewById(R.id.txt_percent6);
        txt_percent7 = view.findViewById(R.id.txt_percent7);
        txt_percent8 = view.findViewById(R.id.txt_percent8);
        txt_percent9 = view.findViewById(R.id.txt_percent9);
        txt_percent10 = view.findViewById(R.id.txt_percent10);
        txt_NationalEstimate = view.findViewById(R.id.txt_NationalEstimate);
        btn_ShowSteps = view.findViewById(R.id.btn_ShowSteps);
        steps = view.findViewById(R.id.steps);
        txt_TenderName = view.findViewById(R.id.txt_TenderName);
        txt_ContractorName = view.findViewById(R.id.txt_ContractorName);
        txt_CellPhone = view.findViewById(R.id.txt_CellPhone);
        txt_Description = view.findViewById(R.id.txt_Description);
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
                p_analizeTenders.openFile(getActivity(), file);
            }

            @Override
            public void onClickClose(VM_FileUploadAnalizeTender file) {

            }
        });
    }

    //زمانی که کاربر یک فایل انتخاب کند متد زیر فراخوانی می شود
    @Override
    public void onSelectedFile(VM_FileUploadAnalizeTender val, File file) {
        p_analizeTenders.checkValidationFile(file, val);
    }

    //اگر فایل انتخاب شده ولید باشد متد زیر فراخوانی می شود
    @Override
    public void onValidFile(VM_FileUploadAnalizeTender val, File file) {

        val.setType(p_analizeTenders.getTypeFile(file));

        if (val.getType() != FileUploadAnalizeTenderType.empty) {
            val.setPath(file.getPath());
            fileUploadAdapter.addFile(val);
        } else {
            Toast.makeText(getContext(), getString(R.string.error_In_Your_File), Toast.LENGTH_SHORT).show();
        }
    }

    //اگر فایل انتخاب شده ولید نباشد متد زیر فراخوانی می شود
    @Override
    public void onNotValidFile(String errorText) {
        Toast.makeText(getContext(), errorText, Toast.LENGTH_SHORT).show();
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
        Toast.makeText(getContext(), getString(R.string.Create_an_account_first), Toast.LENGTH_SHORT).show();
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
            input.setFee(txt_NationalEstimate.getText().toString());

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

            //در اینجا درصدها ست می شود
            if (!txt_percent1.getText().toString().equalsIgnoreCase("")) {
                input.setPrice1(Float.valueOf(txt_percent1.getText().toString()));
            }
            if (!txt_percent2.getText().toString().equalsIgnoreCase("")) {
                input.setPrice2(Float.valueOf(txt_percent2.getText().toString()));
            }
            if (!txt_percent3.getText().toString().equalsIgnoreCase("")) {
                input.setPrice3(Float.valueOf(txt_percent3.getText().toString()));
            }
            if (!txt_percent4.getText().toString().equalsIgnoreCase("")) {
                input.setPrice4(Float.valueOf(txt_percent4.getText().toString()));
            }
            if (!txt_percent5.getText().toString().equalsIgnoreCase("")) {
                input.setPrice5(Float.valueOf(txt_percent5.getText().toString()));
            }
            if (!txt_percent6.getText().toString().equalsIgnoreCase("")) {
                input.setPrice6(Float.valueOf(txt_percent6.getText().toString()));
            }
            if (!txt_percent7.getText().toString().equalsIgnoreCase("")) {
                input.setPrice7(Float.valueOf(txt_percent7.getText().toString()));
            }
            if (!txt_percent8.getText().toString().equalsIgnoreCase("")) {
                input.setPrice8(Float.valueOf(txt_percent8.getText().toString()));
            }
            if (!txt_percent9.getText().toString().equalsIgnoreCase("")) {
                input.setPrice9(Float.valueOf(txt_percent9.getText().toString()));
            }
            if (!txt_percent10.getText().toString().equalsIgnoreCase("")) {
                input.setPrice10(Float.valueOf(txt_percent10.getText().toString()));
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

        if (!Validation.Required(txt_ContractorName, getString(R.string.ThisValueMust_be_Filled))) {
            valid = false;
        }

        if (!Validation.Required(txt_CellPhone, getString(R.string.ThisValueMust_be_Filled))) {
            valid = false;
        }

        if (!Validation.Required(txt_Description, getString(R.string.ThisValueMust_be_Filled))) {
            valid = false;
        }

        return valid;
    }

    //اگر مقادیر ولید نباشند متد زیر فراخوانی می شوند
    @Override
    public void onNotValid() {
        Toast.makeText(getContext(), getString(R.string.Please_enter_full_values), Toast.LENGTH_SHORT).show();
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

            steps.setAnimation(aniFadeIn);
            steps.setVisibility(View.VISIBLE);
        });
    }

    //در اینجا تنظیمات تولبار ست می شود
    void setToolbar() {
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setTitle(getContext().getResources().getString(R.string.TenderAnalise));
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });
        setHasOptionsMenu(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ShowSteps:
                p_analizeTenders.addOrder();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        p_analizeTenders.cancel(TAG);
    }
}
