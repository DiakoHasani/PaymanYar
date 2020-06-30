package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import ir.tdaapp.paymanyar.Model.Services.S_UnitConversionFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Presenter.P_UnitConversionFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.ToolsActivity;

public class UnitConversionFragment extends BaseFragment implements View.OnClickListener, S_UnitConversionFragment {

    public static final String TAG = "UnitConversionFragment";

    private P_UnitConversionFragment p_unitConversionFragment;
    Toolbar toolBar;
    EditText value, answer;
    Spinner sp_value, sp_answer;
    CardView btnConvert, btnDistance, btnArea, btnVolume, btnPressure, btnMass, btnDenisty, btnPower, btnEnergy, btnTime, btnAcceleration, btnSpeed, btnFerequence, btnAngel, btnForce, btnTorque, btnLight;
    int mode = 5;
    CardView btn_Home;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.unit_conversion_fragment, container, false);

        findItem(view);
        implement();
        setToolbar();

        return view;
    }

    void findItem(View view) {
        toolBar = view.findViewById(R.id.toolBar);
        btnAcceleration = view.findViewById(R.id.unit_btnAcceleration);
        btnAngel = view.findViewById(R.id.unit_btnAngel);
        btnArea = view.findViewById(R.id.unit_btnArea);
        btnConvert = view.findViewById(R.id.unit_btnChange);
        btnDenisty = view.findViewById(R.id.unit_btnDensity);
        btnDistance = view.findViewById(R.id.unit_btnDistance);
        btnEnergy = view.findViewById(R.id.unit_btnEnergy);
        btnFerequence = view.findViewById(R.id.unit_btnFerequence);
        btnForce = view.findViewById(R.id.unit_btnForce);
        btnLight = view.findViewById(R.id.unit_btnLight);
        btnMass = view.findViewById(R.id.unit_btnMass);
        btnPower = view.findViewById(R.id.unit_btnPower);
        btnPressure = view.findViewById(R.id.unit_btnPressure);
        btnSpeed = view.findViewById(R.id.unit_btnSpeed);
        btnTime = view.findViewById(R.id.unit_btnTime);
        btnTorque = view.findViewById(R.id.unit_btnTorque);
        btnVolume = view.findViewById(R.id.unit_btnVolume);

        sp_answer = view.findViewById(R.id.unit_spinnerAnswer);
        sp_value = view.findViewById(R.id.unit_spinnerValue);

        value = view.findViewById(R.id.unit_value);
        answer = view.findViewById(R.id.unit_answer);
        btn_Home = view.findViewById(R.id.btn_Home);

    }

    void implement() {

        p_unitConversionFragment = new P_UnitConversionFragment(this.getContext(), this);

        //نمایش اولیه مقادیر گزینه فاصله
        p_unitConversionFragment.ShowItemsFor(5);
        mode = 1;

        btnVolume.setOnClickListener(this);
        btnTorque.setOnClickListener(this);
        btnTime.setOnClickListener(this);
        btnSpeed.setOnClickListener(this);
        btnPressure.setOnClickListener(this);
        btnPower.setOnClickListener(this);
        btnMass.setOnClickListener(this);
        btnLight.setOnClickListener(this);
        btnForce.setOnClickListener(this);
        btnFerequence.setOnClickListener(this);
        btnEnergy.setOnClickListener(this);
        btnDistance.setOnClickListener(this);
        btnDenisty.setOnClickListener(this);
        btnConvert.setOnClickListener(this);
        btnArea.setOnClickListener(this);
        btnAngel.setOnClickListener(this);
        btnAcceleration.setOnClickListener(this);
        btn_Home.setOnClickListener(this);
    }

    //در اینجا تنظیمات تولبار ست می شود
    void setToolbar() {
        toolBar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolBar.setTitle(getContext().getResources().getString(R.string.UnitConversion));
        ((ToolsActivity) getActivity()).setSupportActionBar(toolBar);
        ((ToolsActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        toolBar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.unit_btnAcceleration:
                ResetButtons();
                btnAcceleration.setBackgroundResource(R.color.colorSelected);
                p_unitConversionFragment.ShowItemsFor(1);
                mode = 1;
                break;
            case R.id.unit_btnAngel:
                ResetButtons();
                btnAngel.setBackgroundResource(R.color.colorSelected);
                p_unitConversionFragment.ShowItemsFor(2);
                mode = 2;
                break;
            case R.id.unit_btnArea:
                ResetButtons();
                btnArea.setBackgroundResource(R.color.colorSelected);
                p_unitConversionFragment.ShowItemsFor(3);
                mode = 3;
                break;
            case R.id.unit_btnChange:
                answer.setText("");
                value.setError(null);
                if (value.getText().toString().length() > 0) {
                    p_unitConversionFragment.ConvertValue(value.getText().toString(), sp_value.getSelectedItem().toString(), sp_answer.getSelectedItem().toString(), mode);
                } else {
                    value.setError("لطفا مقدار را وارد کنید");
                }
                break;
            case R.id.unit_btnDensity:
                ResetButtons();
                btnDenisty.setBackgroundResource(R.color.colorSelected);
                p_unitConversionFragment.ShowItemsFor(17);
                mode = 17;
                break;
            case R.id.unit_btnDistance:
                ResetButtons();
                btnDistance.setBackgroundResource(R.color.colorSelected);
                p_unitConversionFragment.ShowItemsFor(5);
                mode = 5;
                break;
            case R.id.unit_btnEnergy:
                ResetButtons();
                btnEnergy.setBackgroundResource(R.color.colorSelected);
                p_unitConversionFragment.ShowItemsFor(6);
                mode = 6;
                break;
            case R.id.unit_btnFerequence:
                ResetButtons();
                btnFerequence.setBackgroundResource(R.color.colorSelected);
                p_unitConversionFragment.ShowItemsFor(7);
                mode = 7;
                break;
            case R.id.unit_btnForce:
                ResetButtons();
                btnForce.setBackgroundResource(R.color.colorSelected);
                p_unitConversionFragment.ShowItemsFor(8);
                mode = 8;
                break;
            case R.id.unit_btnLight:
                ResetButtons();
                btnLight.setBackgroundResource(R.color.colorSelected);
                p_unitConversionFragment.ShowItemsFor(9);
                mode = 9;
                break;
            case R.id.unit_btnMass:
                ResetButtons();
                btnMass.setBackgroundResource(R.color.colorSelected);
                p_unitConversionFragment.ShowItemsFor(10);
                mode = 10;
                break;
            case R.id.unit_btnPower:
                ResetButtons();
                btnPower.setBackgroundResource(R.color.colorSelected);
                p_unitConversionFragment.ShowItemsFor(11);
                mode = 11;
                break;
            case R.id.unit_btnPressure:
                ResetButtons();
                btnPressure.setBackgroundResource(R.color.colorSelected);
                p_unitConversionFragment.ShowItemsFor(12);
                mode = 12;
                break;
            case R.id.unit_btnSpeed:
                ResetButtons();
                btnSpeed.setBackgroundResource(R.color.colorSelected);
                p_unitConversionFragment.ShowItemsFor(13);
                mode = 13;
                break;
            case R.id.unit_btnTime:
                ResetButtons();
                btnTime.setBackgroundResource(R.color.colorSelected);
                p_unitConversionFragment.ShowItemsFor(14);
                mode = 14;
                break;
            case R.id.unit_btnTorque:
                ResetButtons();
                btnTorque.setBackgroundResource(R.color.colorSelected);
                p_unitConversionFragment.ShowItemsFor(15);
                mode = 15;
                break;
            case R.id.unit_btnVolume:
                ResetButtons();
                btnVolume.setBackgroundResource(R.color.colorSelected);
                p_unitConversionFragment.ShowItemsFor(16);
                mode = 16;
                break;
            case R.id.btn_Home:
                getActivity().finish();
                break;

        }
    }

    private void ResetButtons() {
        btnAcceleration.setBackgroundResource(R.color.colorWhite);
        btnVolume.setBackgroundResource(R.color.colorWhite);
        btnTorque.setBackgroundResource(R.color.colorWhite);
        btnTime.setBackgroundResource(R.color.colorWhite);
        btnSpeed.setBackgroundResource(R.color.colorWhite);
        btnPressure.setBackgroundResource(R.color.colorWhite);
        btnPower.setBackgroundResource(R.color.colorWhite);
        btnMass.setBackgroundResource(R.color.colorWhite);
        btnLight.setBackgroundResource(R.color.colorWhite);
        btnForce.setBackgroundResource(R.color.colorWhite);
        btnFerequence.setBackgroundResource(R.color.colorWhite);
        btnEnergy.setBackgroundResource(R.color.colorWhite);
        btnDistance.setBackgroundResource(R.color.colorWhite);
        btnDenisty.setBackgroundResource(R.color.colorWhite);
        btnArea.setBackgroundResource(R.color.colorWhite);
        btnAngel.setBackgroundResource(R.color.colorWhite);

        value.setText("");
        answer.setText("");

    }

    @Override
    public void ShowUnits(ArrayList<String> arr) {
        //نمایش گزینه ها در اسپینر برای انتخاب
        ArrayAdapter aa = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_item, arr);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_value.setAdapter(aa);
        sp_answer.setAdapter(aa);
    }

    @Override
    public void ConvertedValue(String answer) {
        this.answer.setText(answer);
    }
}
