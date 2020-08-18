package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import java.util.ArrayList;

import ir.tdaapp.paymanyar.Model.Services.S_UnitConversionFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.UnitConversion;

public class P_UnitConversionFragment {

    Context context;
    S_UnitConversionFragment s_unitConversionFragment;
    UnitConversion unitConversion;

    public P_UnitConversionFragment(Context context, S_UnitConversionFragment s_unitConversionFragment) {
        this.context = context;
        this.s_unitConversionFragment = s_unitConversionFragment;
        unitConversion = new UnitConversion();
    }


    public void ShowItemsFor(int mode) {
        ArrayList<String> arr = unitConversion.ShowSpinnerValues(mode);
        s_unitConversionFragment.ShowUnits(arr);
    }

    public void ConvertValue(String value, String value_unit, String answer_unit, int mode) {
        s_unitConversionFragment.ConvertedValue(unitConversion.ConvertValues(value, mode, value_unit, answer_unit));
    }


}
