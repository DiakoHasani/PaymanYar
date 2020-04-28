package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.DBExcute;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.FieldItem;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.RecordHolder;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.database;
import ir.tdaapp.paymanyar.Model.Services.S_GPSFragment;
import ir.tdaapp.paymanyar.Model.Services.S_UnitConversionFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.UnitConversion;

public class P_UnitConversionFragment {

    Context context;
    S_UnitConversionFragment s_unitConversionFragment;

    public P_UnitConversionFragment(Context context, S_UnitConversionFragment s_unitConversionFragment) {
        this.context = context;
        this.s_unitConversionFragment = s_unitConversionFragment;
    }


    public void ShowItemsFor(int mode){
        UnitConversion unitConversion=new UnitConversion();
        ArrayList<String> arr=unitConversion.ShowSpinnerValues(mode);
        s_unitConversionFragment.ShowUnits(arr);
    }

    public void ConvertValue(String value,String value_unit,String answer_unit,int mode){
        UnitConversion unitConversion=new UnitConversion();
        s_unitConversionFragment.ConvertedValue(unitConversion.ConvertValues(value,mode,value_unit,answer_unit));
    }


}
