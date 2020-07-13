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
import ir.tdaapp.paymanyar.Model.Adapters.FileUpload_AnalizeTenderAdapter;
import ir.tdaapp.paymanyar.Model.Enums.FileUploadAnalizeTenderType;
import ir.tdaapp.paymanyar.Model.Services.S_AnalizeTenders;
import ir.tdaapp.paymanyar.Model.Services.onClickFileUpload_AnalizeTender;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FileUploadAnalizeTender;
import ir.tdaapp.paymanyar.Presenter.P_AnalizeTenders;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;

//مربوط به صفحه آنالیز مناقصات
public class AnalizeTenders extends BaseFragment implements S_AnalizeTenders, View.OnClickListener {

    public static final String TAG = "AnalizeTenders";

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
                clickedShowSteps();
                break;
        }
    }

    //زمانی که کاربر دکمه نمایش مراحل سفارش کلیک کند متد زیر فراخوانی می شود
    void clickedShowSteps() {
        btn_ShowSteps.startAnimation();
        new Handler().postDelayed(() -> {
            btn_ShowSteps.revertAnimation(() -> {
                btn_ShowSteps.setAnimation(aniFadeOut);
                btn_ShowSteps.setVisibility(View.GONE);

                steps.setAnimation(aniFadeIn);
                steps.setVisibility(View.VISIBLE);
            });
        }, 2000);
    }
}
