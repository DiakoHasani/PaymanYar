package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.shimmer.ShimmerFrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Adapters.ChargeAdapter;
import ir.tdaapp.paymanyar.Model.Services.S_ChargeFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Charge;
import ir.tdaapp.paymanyar.Presenter.P_ChargeFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Activitys.ToolsActivity;
import ir.tdaapp.paymanyar.View.Dialogs.ErrorAplicationDialog;

//مربوط به فرگمنت شارژ
public class ChargeFragment extends BaseFragment implements S_ChargeFragment {

    public final static String TAG = "ChargeFragment";

    Toolbar toolBar;
    RecyclerView recycler;
    ChargeAdapter chargeAdapter;
    LinearLayoutManager layoutManager;
    ShimmerFrameLayout Loading;
    P_ChargeFragment p_chargeFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.charge_fragment, container, false);

        findItem(view);
        implement();
        setToolbar();

        p_chargeFragment.start();

        return view;
    }

    void findItem(View view) {
        toolBar = view.findViewById(R.id.toolBar);
        recycler = view.findViewById(R.id.recycler);
        Loading = view.findViewById(R.id.Loading);
    }

    void implement() {
        p_chargeFragment = new P_ChargeFragment(getContext(), this);
    }

    //در اینجا تنظیمات تولبار ست می شود
    void setToolbar() {

        toolBar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolBar.setTitle(getContext().getResources().getString(R.string.Charge));
        ((MainActivity) getActivity()).setSupportActionBar(toolBar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        toolBar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });
        setHasOptionsMenu(true);
    }

    @Override
    public void OnStart() {

        Loading.startShimmerAnimation();

        chargeAdapter = new ChargeAdapter(getContext());
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        recycler.setAdapter(chargeAdapter);
        recycler.setLayoutManager(layoutManager);
    }

    @Override
    public void onLoading(boolean load) {
        if (load) {
            Loading.setVisibility(View.VISIBLE);
        } else {
            Loading.setVisibility(View.GONE);
        }
    }

    @Override
    public void onHideAll() {
        recycler.setVisibility(View.GONE);
        Loading.setVisibility(View.GONE);
    }

    @Override
    public void onFinish() {
        Loading.stopShimmerAnimation();
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

        new ErrorAplicationDialog(getString(R.string.Error), text, getString(R.string.Again), R.drawable.ic_error, R.color.colorError, () -> {
            p_chargeFragment.start();
        }).show(getActivity().getSupportFragmentManager(), ErrorAplicationDialog.TAG);

    }

    @Override
    public void onItemCharge(VM_Charge charge) {
        chargeAdapter.add(charge);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        p_chargeFragment.Cancel(TAG);
    }
}
