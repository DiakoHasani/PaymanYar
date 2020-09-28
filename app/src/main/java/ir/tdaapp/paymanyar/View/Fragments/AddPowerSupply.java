package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Enums.AdType;
import ir.tdaapp.paymanyar.Model.Services.S_AddPowerSupply;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_AdType;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Job;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_ProvincesAndCities;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_WorkExperience;
import ir.tdaapp.paymanyar.Presenter.P_AddPowerSupply;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;

/**
 * مربوط به افزودن نیروکار
 **/
public class AddPowerSupply extends BaseFragment implements S_AddPowerSupply {

    public static final String TAG = "AddPowerSupply";

    P_AddPowerSupply p_addPowerSupply;

    Toolbar toolbar;
    Spinner cmb_AdType, cmb_WorkExperiences, cmb_Provinces, cmb_City, cmb_Job;
    TextInputEditText txt_Name, txt_CellPhone, txt_Description;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_power_supply, container, false);

        findItem(view);
        implement();

        setToolbar();

        p_addPowerSupply.getProvinces();
        p_addPowerSupply.getAdTypes();
        p_addPowerSupply.getJobs();
        p_addPowerSupply.getWorkExperiences();

        return view;
    }

    void findItem(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        cmb_AdType = view.findViewById(R.id.cmb_AdType);
        cmb_WorkExperiences = view.findViewById(R.id.cmb_WorkExperiences);
        cmb_Provinces = view.findViewById(R.id.cmb_Provinces);
        cmb_City = view.findViewById(R.id.cmb_City);
        cmb_Job = view.findViewById(R.id.cmb_Job);
        txt_Name = view.findViewById(R.id.txt_Name);
        txt_CellPhone = view.findViewById(R.id.txt_CellPhone);
        txt_Description = view.findViewById(R.id.txt_Description);
    }

    void implement() {
        p_addPowerSupply=new P_AddPowerSupply(getContext(),this);

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
    public void onLoading(boolean load) {

    }

    @Override
    public void onError(ResaultCode result) {

    }

    @Override
    public void onSuccess() {

    }

    /**
     * در اینجا اسپینر استان ست می شود
     * **/
    @Override
    public void getProvinces(ArrayAdapter<VM_ProvincesAndCities> provinces) {
        cmb_Provinces.setAdapter(provinces);
    }

    /**
     * در اینجا اسپینر شهرها ست می شود
     * **/
    @Override
    public void getCities(ArrayAdapter<VM_ProvincesAndCities> cities) {
        cmb_City.setAdapter(cities);
    }

    /**
     * در اینجا اسپینر شغل ست می شود
     * **/
    @Override
    public void getJobs(ArrayAdapter<VM_Job> jobs) {
        cmb_Job.setAdapter(jobs);
    }

    /**
     * در اینجا اسپینر سابقه کار ست می شود
     * **/
    @Override
    public void getWorkExperiences(ArrayAdapter<VM_WorkExperience> workExperiences) {
        cmb_WorkExperiences.setAdapter(workExperiences);
    }

    /**
     * در اینجا اسپینر نوع آگهی ست می شود
     * **/
    @Override
    public void getAdTypes(ArrayAdapter<VM_AdType> adapter) {
        cmb_AdType.setAdapter(adapter);
    }
}
