package ir.tdaapp.paymanyar.Model.Services;

import com.google.android.gms.maps.OnMapReadyCallback;

import ir.tdaapp.paymanyar.Model.Adapters.FilterAdapter;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_EshtalItem;

public interface S_GPSFragment {
    public void OnMapReady(OnMapReadyCallback mapReadyCallback);
    public void OnLocationChange(String lat,String lon);
    public void ShowLocations();
}
