package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import ir.tdaapp.paymanyar.Model.Services.S_ChargeFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Charge;
import ir.tdaapp.paymanyar.Presenter.P_ChargeFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Dialogs.BuyChargeDialog;
import ir.tdaapp.paymanyar.View.Dialogs.ErrorAplicationDialog;
import ir.tdaapp.paymanyar.View.Dialogs.FilterWideDialog;

//مربوط به فرگمنت شارژ
public class ChargeFragment extends BaseFragment implements S_ChargeFragment,View.OnClickListener {

    public final static String TAG = "ChargeFragment";

    Toolbar toolBar;
    RecyclerView recycler;
    ChargeAdapter chargeAdapter;
    LinearLayoutManager layoutManager;
    ShimmerFrameLayout Loading;
    P_ChargeFragment p_chargeFragment;
    ProgressBar progressDay, progressHour;
    TextView lbl_Hour, lbl_Day;
    ErrorAplicationDialog errorAplicationDialog;
    CardView btn_Home;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.charge_fragment, container, false);

        findItem(view);
        implement();
        setToolbar();

        return view;
    }

    void findItem(View view) {
        toolBar = view.findViewById(R.id.toolBar);
        recycler = view.findViewById(R.id.recycler);
        Loading = view.findViewById(R.id.Loading);
        progressHour = view.findViewById(R.id.progressHour);
        progressDay = view.findViewById(R.id.progressDay);
        lbl_Hour = view.findViewById(R.id.lbl_Hour);
        lbl_Day = view.findViewById(R.id.lbl_Day);
        btn_Home = view.findViewById(R.id.btn_Home);
    }

    void implement() {
        p_chargeFragment = new P_ChargeFragment(getContext(), this);
        btn_Home.setOnClickListener(this);
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
    public void onResume() {
        super.onResume();

        new Handler().postDelayed(() -> {
            p_chargeFragment.start();
        },300);
    }

    @Override
    public void OnStart() {

        Loading.startShimmerAnimation();

        chargeAdapter = new ChargeAdapter(getContext());
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        recycler.setAdapter(chargeAdapter);
        recycler.setLayoutManager(layoutManager);

        chargeAdapter.setOnClickChargeItem(charge -> {

            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag(BuyChargeDialog.TAG);

            if (prev == null) {
                ft.addToBackStack(null);

                BuyChargeDialog byChargeDialog = new BuyChargeDialog(charge);

                byChargeDialog.show(ft, BuyChargeDialog.TAG);
            }

        });

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

        errorAplicationDialog =new ErrorAplicationDialog(getString(R.string.Error), text, getString(R.string.Again), R.drawable.ic_error, R.color.colorError, () -> {
            p_chargeFragment.start();
            errorAplicationDialog.dismiss();
        });

        errorAplicationDialog.show(getActivity().getSupportFragmentManager(), ErrorAplicationDialog.TAG);

    }

    @Override
    public void onItemCharge(VM_Charge charge) {
        chargeAdapter.add(charge);
    }

    //در اینجا موجودی گرفته می شود
    @Override
    public void onSuccessInventory(int inventory) {

        int day = inventory / 24;
        int hour = inventory % 24;

        lbl_Hour.setText(String.valueOf(hour));
        lbl_Day.setText(String.valueOf(day));
    }

    //در اینجا اگر در گرفتن موجودی خطای رخ دهد متد زیر رخ می دهد
    @Override
    public void onErrorInventory() {
        lbl_Day.setVisibility(View.INVISIBLE);
        lbl_Hour.setVisibility(View.INVISIBLE);

        progressDay.setVisibility(View.INVISIBLE);
        progressHour.setVisibility(View.INVISIBLE);
    }

    //مربوط به لودینگ موجودی
    @Override
    public void onLoadingInventory(boolean load) {
        if (load) {
            lbl_Day.setVisibility(View.INVISIBLE);
            lbl_Hour.setVisibility(View.INVISIBLE);

            progressDay.setVisibility(View.VISIBLE);
            progressHour.setVisibility(View.VISIBLE);
        } else {
            lbl_Day.setVisibility(View.VISIBLE);
            lbl_Hour.setVisibility(View.VISIBLE);

            progressDay.setVisibility(View.INVISIBLE);
            progressHour.setVisibility(View.INVISIBLE);
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
        p_chargeFragment.Cancel(TAG);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_Home:
                ((MainActivity)getActivity()).backToHome();
                break;
        }
    }
}
