package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Adapters.SupportAdapter;
import ir.tdaapp.paymanyar.Model.Services.S_SupportFragment;
import ir.tdaapp.paymanyar.Model.Services.onClickSupport;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.openUrl;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Support;
import ir.tdaapp.paymanyar.Presenter.P_SupportFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Dialogs.ErrorAplicationDialog;

public class SupportFragment extends BaseFragment implements S_SupportFragment, View.OnClickListener {

    public static final String TAG = "SupportFragment";

    P_SupportFragment p_supportFragment;
    Toolbar toolbar;
    RecyclerView recycler;
    SupportAdapter supportAdapter;
    ShimmerFrameLayout loading;
    ErrorAplicationDialog errorAplicationDialog;
    EditText txt_Message;
    CardView btn_Send;
    ProgressBar progress_btn_send;
    TextView lbl_btn_send;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.support_fragment, container, false);

        findItem(view);
        implement();
        setToolbar();

        new Handler().postDelayed(() -> {
            p_supportFragment.start();
        }, 300);

        return view;
    }

    void findItem(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        recycler = view.findViewById(R.id.recycler);
        loading = view.findViewById(R.id.loading);
        txt_Message = view.findViewById(R.id.txt_Message);
        btn_Send = view.findViewById(R.id.btn_Send);
        progress_btn_send = view.findViewById(R.id.progress_btn_send);
        lbl_btn_send = view.findViewById(R.id.lbl_btn_send);
    }

    void implement() {
        p_supportFragment = new P_SupportFragment(getContext(), this);
        btn_Send.setOnClickListener(this);
    }

    //در اینجا تنظیمات تولبار ست می شود
    void setToolbar() {

        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setTitle(getContext().getResources().getString(R.string.Support));
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void OnStart() {
        loading.startShimmerAnimation();
        supportAdapter = new SupportAdapter(getContext());
        recycler.setAdapter(supportAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        supportAdapter.setClickSupport(new onClickSupport() {
            @Override
            public void onClickSMS(String phoneNumber) {

                if (phoneNumber != null) {

                    if (phoneNumber.length() != 0) {

                        if (phoneNumber.charAt(0) == '0') {
                            String a = "+98";
                            int b = 0;
                            for (char i : phoneNumber.toCharArray()) {
                                if (b != 0) {
                                    a += i;
                                }
                                b++;
                            }
                            openUrl.getWhatsApp(a, getContext());
                        }else{
                            openUrl.getWhatsApp(phoneNumber, getContext());
                        }

                    }

                }
            }

            @Override
            public void onClickTelegram(String url) {
                openUrl.getTelegram(url, getContext());
            }

            @Override
            public void onClickCall(String phoneNumber) {
                openUrl.call(phoneNumber, getContext());
            }

            @Override
            public void onClickEmail(String url) {
                openUrl.getEmail(url, getContext());
            }
        });
    }

    @Override
    public void onHideAll() {
        recycler.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess() {
        recycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFinish() {
        loading.stopShimmerAnimation();
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

        errorAplicationDialog = new ErrorAplicationDialog(getString(R.string.Error), text, getString(R.string.Again), R.drawable.ic_error, R.color.colorError, () -> {
            p_supportFragment.start();
            errorAplicationDialog.dismiss();
        });
        errorAplicationDialog.show(getActivity().getSupportFragmentManager(), ErrorAplicationDialog.TAG);
    }

    @Override
    public void onNotValid() {
        btn_Send.setEnabled(true);
    }

    @Override
    public void onUser(VM_Support support) {
        supportAdapter.add(support);
    }

    @Override
    public void onCreateAccount() {
        Toast.makeText(getContext(), getString(R.string.Create_an_account_first), Toast.LENGTH_SHORT).show();
        ((MainActivity) getActivity()).onAddFragment(new LoginFragment(), 0, 0, true, LoginFragment.TAG);
        btn_Send.setEnabled(true);
    }

    @Override
    public void onErrorSend(ResaultCode result) {
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
        btn_Send.setEnabled(false);
    }

    @Override
    public void onLoadingSend(boolean load) {
        if (load) {
            lbl_btn_send.setVisibility(View.INVISIBLE);
            progress_btn_send.setVisibility(View.VISIBLE);
        } else {
            lbl_btn_send.setVisibility(View.VISIBLE);
            progress_btn_send.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFinishSend(VM_Message message) {
        Toast.makeText(getContext(), message.getMessage(), Toast.LENGTH_SHORT).show();
        btn_Send.setEnabled(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Send:
                btn_Send.setEnabled(false);
                p_supportFragment.sendMessage(txt_Message);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        p_supportFragment.Cancel(TAG);
    }
}
