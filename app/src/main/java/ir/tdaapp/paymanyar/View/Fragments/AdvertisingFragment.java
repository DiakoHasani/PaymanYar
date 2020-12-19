package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.shimmer.ShimmerFrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Adapters.ChargeAdapter;
import ir.tdaapp.paymanyar.Model.Services.S_AdvertisingFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Charge;
import ir.tdaapp.paymanyar.Presenter.P_AdvertisingFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Dialogs.BuyAdsDialog;
import ir.tdaapp.paymanyar.View.Dialogs.ErrorAplicationDialog;

/**
 * مربوط به صفحه تبلیغات
 **/
public class AdvertisingFragment extends BaseFragment implements S_AdvertisingFragment, View.OnClickListener {

    public final static String TAG = "AdvertisingFragment";

    Toolbar toolBar;
    RecyclerView recycler;
    ShimmerFrameLayout loading;
    CardView btn_Home;
    ErrorAplicationDialog errorAplicationDialog;
    P_AdvertisingFragment p_advertisingFragment;
    ChargeAdapter chargeAdapter;
    LinearLayoutManager layoutManager;
    ImageView reloadImageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.advertising_fragment, container, false);

        findItem(view);
        implement();
        setToolbar();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        new Handler().postDelayed(() -> {
            p_advertisingFragment.start();
        }, 300);
    }

    void findItem(View view) {
        toolBar = view.findViewById(R.id.toolBar);
        recycler = view.findViewById(R.id.recycler);
        loading = view.findViewById(R.id.loading);
        btn_Home = view.findViewById(R.id.btn_Home);
        reloadImageView = view.findViewById(R.id.reloadImageView);
    }

    void implement() {
        p_advertisingFragment = new P_AdvertisingFragment(getContext(), this);
        btn_Home.setOnClickListener(this);
        reloadImageView.setOnClickListener(this);
    }

    //در اینجا تنظیمات تولبار ست می شود
    void setToolbar() {
        toolBar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolBar.setTitle(getContext().getResources().getString(R.string.Advertising));
        ((MainActivity) getActivity()).setSupportActionBar(toolBar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        toolBar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });
        setHasOptionsMenu(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Home:
                ((MainActivity) getActivity()).backToHome();
                break;
            case R.id.reloadImageView:
                p_advertisingFragment.start();
                break;
        }
    }

    @Override
    public void OnStart() {
        loading.startShimmerAnimation();
        chargeAdapter = new ChargeAdapter(getContext());

        chargeAdapter.setOnClickChargeItem(charge -> {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag(BuyAdsDialog.TAG);

            if (prev == null) {
                ft.addToBackStack(null);
                BuyAdsDialog buyAdsDialog = new BuyAdsDialog(charge);
                buyAdsDialog.show(ft, BuyAdsDialog.TAG);
            }
        });

        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(chargeAdapter);
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
        reloadImageView.setVisibility(View.GONE);
    }

    @Override
    public void onFinish() {
        loading.stopShimmerAnimation();
    }

    @Override
    public void onShowRecycler(boolean show) {
        if (show) {
            recycler.setVisibility(View.VISIBLE);
        } else {
            recycler.setVisibility(View.GONE);
        }
    }

    @Override
    public void onError(ResaultCode result) {

        reloadImageView.setVisibility(View.VISIBLE);

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

        errorAplicationDialog = new ErrorAplicationDialog(getString(R.string.Error), text, getString(R.string.Again), R.drawable.ic_error, R.color.colorError, () -> {
            p_advertisingFragment.start();
            errorAplicationDialog.dismiss();
        });

        errorAplicationDialog.show(getActivity().getSupportFragmentManager(), ErrorAplicationDialog.TAG);
    }

    @Override
    public void onItem(VM_Charge item) {
        chargeAdapter.add(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        p_advertisingFragment.cancel(getContext(), TAG);
    }
}
