package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.facebook.shimmer.ShimmerFrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Adapters.MachineryAdapter;
import ir.tdaapp.paymanyar.Model.Services.S_MyMachineriesFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Machinery;
import ir.tdaapp.paymanyar.Presenter.P_MyMachineriesFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Dialogs.ErrorAplicationDialog;

/**
 * مربوط به ماشین آلات من
 **/
public class MyMachineriesFragment extends BaseFragment implements S_MyMachineriesFragment, View.OnClickListener {

    public static final String TAG = "MyMachineriesFragment";

    P_MyMachineriesFragment p_myMachineriesFragment;
    Toolbar toolbar;
    RecyclerView recycler;
    ShimmerFrameLayout loading;
    SwipeRefreshLayout reload;
    ErrorAplicationDialog errorAplicationDialog;
    LinearLayoutManager layoutManager;
    ImageView btn_refresh;
    LinearLayout empty;
    MachineryAdapter machineryAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_machineries_fragment, container, false);

        findItem(view);
        implement();
        setToolbar();

        p_myMachineriesFragment.start();

        return view;
    }

    void findItem(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        recycler = view.findViewById(R.id.recycler);
        loading = view.findViewById(R.id.loading);
        reload = view.findViewById(R.id.reload);
        btn_refresh = view.findViewById(R.id.btn_refresh);
        empty = view.findViewById(R.id.empty);
    }

    void implement() {
        p_myMachineriesFragment = new P_MyMachineriesFragment(getContext(), this);
        btn_refresh.setOnClickListener(this);

        reload.setOnRefreshListener(() -> {
            p_myMachineriesFragment.start();
        });
    }

    void setToolbar() {
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setTitle(getContext().getResources().getString(R.string.MyMachinery));
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
        if (load) {
            loading.startShimmerAnimation();
            loading.setVisibility(View.VISIBLE);
        } else {
            loading.stopShimmerAnimation();
            loading.setVisibility(View.GONE);
        }
    }

    @Override
    public void OnStart() {
        machineryAdapter=new MachineryAdapter(getContext());
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        recycler.setAdapter(machineryAdapter);
        recycler.setLayoutManager(layoutManager);

        machineryAdapter.setClickPowerSupplyNetwork(id -> {
        });
    }

    @Override
    public void onSuccess() {
        recycler.setVisibility(View.VISIBLE);
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

        btn_refresh.setVisibility(View.VISIBLE);
        errorAplicationDialog = new ErrorAplicationDialog(getString(R.string.Error), text, getString(R.string.Again), R.drawable.ic_error, R.color.colorError, () -> {
            p_myMachineriesFragment.start();
            errorAplicationDialog.dismiss();
        });
        errorAplicationDialog.show(getActivity().getSupportFragmentManager(), ErrorAplicationDialog.TAG);
    }

    @Override
    public void onHideAll() {
        loading.setVisibility(View.GONE);
        recycler.setVisibility(View.GONE);
        btn_refresh.setVisibility(View.GONE);
        empty.setVisibility(View.GONE);
        reload.setRefreshing(false);
    }

    @Override
    public void onItem(VM_Machinery machinery) {
        machineryAdapter.add(machinery);
    }

    @Override
    public void onFinish() {

    }

    @Override
    public void noAccount() {
        btn_refresh.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).onAddFragment(new LoginFragment(), R.anim.fadein, R.anim.short_fadeout, true, LoginFragment.TAG);
    }

    @Override
    public void onEmpty() {
        empty.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_refresh:
                p_myMachineriesFragment.start();
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        p_myMachineriesFragment.cancel(TAG);
    }
}
