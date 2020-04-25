package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.DBExcute;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.FieldItem;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.RecordHolder;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.database;
import ir.tdaapp.paymanyar.Model.Services.S_GPSFragment;
import ir.tdaapp.paymanyar.Model.Services.S_MagnifierFragment;

public class P_MagnifierFragment {

    Context context;
    S_MagnifierFragment s_magnifierFragment;

    public P_MagnifierFragment(Context context, S_MagnifierFragment s_magnifierFragment) {
        this.context = context;
        this.s_magnifierFragment = s_magnifierFragment;
    }



    public void Requestermission(){

    }


}
