package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Adapters.SmsAdapter;
import ir.tdaapp.paymanyar.Model.Services.S_SmsFragment;
import ir.tdaapp.paymanyar.Model.Services.addSMS;
import ir.tdaapp.paymanyar.Model.Services.onClickSMS;
import ir.tdaapp.paymanyar.Model.Services.removeSMS;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_SMS;
import ir.tdaapp.paymanyar.Presenter.P_SmsFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Dialogs.ErrorAplicationDialog;

public class SmsFragment extends BaseFragment implements S_SmsFragment, View.OnClickListener {

    public static final String TAG = "SmsFragment";

    Toolbar toolBar;
    RecyclerView recycler;
    P_SmsFragment p_smsFragment;
    ShimmerFrameLayout loading;
    LinearLayout empty;
    SmsAdapter smsAdapter;
    LinearLayoutManager layoutManager;
    ErrorAplicationDialog errorAplicationDialog;
    RelativeLayout loading_Archive, btn_Fevorites;
    boolean showAllSMS = true;

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
        loading_Archive = view.findViewById(R.id.loading_Archive);
        btn_Fevorites = view.findViewById(R.id.btn_Fevorites);
    }

    void implement() {
        p_smsFragment = new P_SmsFragment(getContext(), this);
        btn_Fevorites.setOnClickListener(this);
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

        smsAdapter.setClickSMS(new onClickSMS() {
            @Override
            public void onClickLayout(String id) {

            }

            @Override
            public void onClickAddFevorit(String id, ImageView star) {
                p_smsFragment.addFevorit(id, new addSMS() {
                    @Override
                    public void onSuccess() {
                        star.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_star_yellow));
                    }

                    @Override
                    public void onError(String message) {
                        star.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_star_border_yellow));
                    }
                });
            }

            @Override
            public void onClickRemoveFevorit(String id, ImageView star) {
                p_smsFragment.removeFevorit(id, new removeSMS() {
                    @Override
                    public void onSuccess() {
                        star.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_star_border_yellow));
                    }

                    @Override
                    public void onError(String message) {
                        star.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_star_yellow));
                    }
                });
            }

            @Override
            public void onClickArchive(String msgId) {
                p_smsFragment.ArchiveMessage(msgId);
            }
        });
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
        if (load) {
            loading.setVisibility(View.VISIBLE);
        } else {
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

    @Override
    public void onArchiveMessage(VM_Message message, String messageId) {
        if (message.isResult()) {
            smsAdapter.archiveSMS(messageId);
        } else {
            Toast.makeText(getContext(), message.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onErrorArchiveMessage(ResaultCode result) {
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

        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onLoadingArchive(boolean archive) {
        if (archive) {
            loading_Archive.setVisibility(View.VISIBLE);
        } else {
            loading_Archive.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onShowAllSMS() {
        return showAllSMS;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Fevorites:
                showAllSMS = !showAllSMS;
                p_smsFragment.start();
                break;
        }
    }
}
