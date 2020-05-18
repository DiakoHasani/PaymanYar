package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Adapters.SmsAdapter;
import ir.tdaapp.paymanyar.Model.Services.S_SmsFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_SMS;
import ir.tdaapp.paymanyar.Presenter.P_SmsFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Dialogs.ErrorAplicationDialog;

public class SmsFragment extends BaseFragment implements S_SmsFragment {

    public static final String TAG="SmsFragment";

    Toolbar toolBar;
    RecyclerView recycler;
    P_SmsFragment p_smsFragment;
    ShimmerFrameLayout loading;
    LinearLayout empty;
    SmsAdapter smsAdapter;
    LinearLayoutManager layoutManager;
    ErrorAplicationDialog errorAplicationDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sms_fragment, container, false);

        findItem(view);
        implement();
        setToolbar();
        p_smsFragment.start();

        return view;
    }

    void findItem(View view) {
        toolBar = view.findViewById(R.id.toolBar);
        recycler = view.findViewById(R.id.recycler);
        loading = view.findViewById(R.id.loading);
        empty = view.findViewById(R.id.empty);
    }

    void implement() {
        p_smsFragment = new P_SmsFragment(getContext(), this);
    }

    //در اینجا تنظیمات تولبار ست می شود
    void setToolbar() {

        toolBar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolBar.setTitle(getContext().getResources().getString(R.string.SMS));
        ((MainActivity) getActivity()).setSupportActionBar(toolBar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        toolBar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });
        setHasOptionsMenu(true);
    }

    //زمانی که عملیات گرفتن داده ها از سرور شروع شود متد زیر فراخوانی می شود
    @Override
    public void OnStart() {

        loading.startShimmerAnimation();
        smsAdapter = new SmsAdapter(getContext());
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        recycler.setAdapter(smsAdapter);
        recycler.setLayoutManager(layoutManager);
    }

    //اگر خطای رخ دهد متد زیر فراخوانی می شود
    @Override
    public void onError(ResaultCode resault) {
        String text = "";

        switch (resault) {
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
            p_smsFragment.start();
            errorAplicationDialog.dismiss();
        });
        errorAplicationDialog.show(getActivity().getSupportFragmentManager(), ErrorAplicationDialog.TAG);
    }

    //اگر عملیات ما به په پایان رسید متد زیر فراخوانی می شود
    @Override
    public void onFinish() {
        loading.stopShimmerAnimation();
    }

    @Override
    public void onHideAll() {
        recycler.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
        empty.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess() {
        recycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoading(boolean load) {
        if (load){
            loading.setVisibility(View.VISIBLE);
        }else{
            loading.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemSMS(VM_SMS sms) {
        smsAdapter.add(sms);
    }

    @Override
    public void onEmpty() {
        empty.setVisibility(View.VISIBLE);
    }
}
