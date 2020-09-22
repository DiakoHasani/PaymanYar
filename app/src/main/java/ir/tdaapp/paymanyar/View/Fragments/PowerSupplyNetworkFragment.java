package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Adapters.PowerSupplyNetworkAdapter;
import ir.tdaapp.paymanyar.Model.Services.S_PowerSupplyNetworkFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PowerSupplyNetwork;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_ProvincesAndCities;
import ir.tdaapp.paymanyar.Presenter.P_PowerSupplyNetworkFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Dialogs.ErrorAplicationDialog;

/**
 * صفحه شبکه تامین نیروکار
 **/
public class PowerSupplyNetworkFragment extends BaseFragment implements S_PowerSupplyNetworkFragment {

    public final static String TAG = "PowerSupplyNetworkFragment";

    Spinner cmb_Province, cmb_City;
    P_PowerSupplyNetworkFragment p_powerSupplyNetworkFragment;
    Toolbar toolbar;
    RecyclerView recycler;
    ShimmerFrameLayout loading;
    ImageView reload;
    LinearLayout empty;
    PowerSupplyNetworkAdapter powerSupplyNetworkAdapter;
    LinearLayoutManager layoutManager;
    ErrorAplicationDialog errorAplicationDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.power_supply_network_fragment, container, false);

        findItem(view);
        implement();
        setToolbar();

        p_powerSupplyNetworkFragment.getProvinces();
        p_powerSupplyNetworkFragment.start();

        return view;
    }

    void findItem(View view) {
        cmb_Province = view.findViewById(R.id.cmb_Province);
        cmb_City = view.findViewById(R.id.cmb_City);
        toolbar = view.findViewById(R.id.toolbar);
        recycler = view.findViewById(R.id.recycler);
        loading = view.findViewById(R.id.loading);
        reload = view.findViewById(R.id.reload);
        empty = view.findViewById(R.id.empty);
    }

    void implement() {
        p_powerSupplyNetworkFragment = new P_PowerSupplyNetworkFragment(getContext(), this);

        cmb_Province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                try {
                    VM_ProvincesAndCities item = ((VM_ProvincesAndCities) adapterView.getSelectedItem());

                    if (item != null) {
                        p_powerSupplyNetworkFragment.getCities(item.getId());
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
        toolbar.setTitle(getContext().getResources().getString(R.string.PowerSupplyNetwork));
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });
        setHasOptionsMenu(true);
    }

    @Override
    public void OnStart() {
        powerSupplyNetworkAdapter = new PowerSupplyNetworkAdapter(getContext());
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        recycler.setAdapter(powerSupplyNetworkAdapter);
        recycler.setLayoutManager(layoutManager);
    }

    @Override
    public void onLoading(boolean load) {
        if (load) {
            loading.setVisibility(View.VISIBLE);
        } else {
            loading.setVisibility(View.GONE);
        }
    }

    @Override
    public void onHideAll() {
        recycler.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
        reload.setVisibility(View.GONE);
        empty.setVisibility(View.GONE);
    }

    /**
     * اگر آیتمی برای نمایش در رسایکلر نباشد متد زیر فراخوانی می شود
     **/
    @Override
    public void onEmpty() {
        empty.setVisibility(View.VISIBLE);
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

        reload.setVisibility(View.VISIBLE);

        errorAplicationDialog = new ErrorAplicationDialog(getString(R.string.Error), text, getString(R.string.Again), R.drawable.ic_error, R.color.colorError, () -> {
            p_powerSupplyNetworkFragment.start();
            errorAplicationDialog.dismiss();
        });
        errorAplicationDialog.show(getActivity().getSupportFragmentManager(), ErrorAplicationDialog.TAG);
    }

    /**
     * اگر داده ها با موفقیت از سرور گرفته شود متد زیر فراخوانی می شود
     **/
    @Override
    public void onSuccess() {
        recycler.setVisibility(View.VISIBLE);
    }

    /**
     * در اینجا لیست استان ها برای اسپینر گرفته می شود
     **/
    @Override
    public void getProvinces(ArrayAdapter<VM_ProvincesAndCities> provinces) {
        cmb_Province.setAdapter(provinces);
    }

    /**
     * در اینجا لیست شهرها برای اسپینر گرفته می شود
     **/
    @Override
    public void getCities(ArrayAdapter<VM_ProvincesAndCities> cities) {
        cmb_City.setAdapter(cities);
    }

    /**
     * در اینجا یک آیتم به رسایکلر اضافه می شود
     **/
    @Override
    public void onItem(VM_PowerSupplyNetwork powerSupplyNetwork) {
        powerSupplyNetworkAdapter.add(powerSupplyNetwork);
    }
}
