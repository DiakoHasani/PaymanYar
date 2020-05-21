package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Services.S_SupportFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.Presenter.P_SupportFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;

public class SupportFragment extends BaseFragment implements S_SupportFragment, View.OnClickListener {

    public static final String TAG = "SupportFragment";

    CardView btn_Send;
    EditText txt_message;
    RelativeLayout loading;
    P_SupportFragment p_supportFragment;
    Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.support_fragment, container, false);

        findItem(view);
        implement();
        setToolbar();

        return view;
    }

    void findItem(View view) {
        btn_Send = view.findViewById(R.id.btn_Send);
        txt_message = view.findViewById(R.id.txt_message);
        loading = view.findViewById(R.id.loading);
        toolbar = view.findViewById(R.id.toolbar);
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
    public void onFinish(VM_Message message) {
        Toast.makeText(getContext(), message.getMessage(), Toast.LENGTH_SHORT).show();
        btn_Send.setEnabled(true);
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

        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
        btn_Send.setEnabled(true);
    }

    @Override
    public void onNotValid() {
        btn_Send.setEnabled(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Send:

                if (((MainActivity) getActivity()).getTbl_user().hasAccount(getContext())) {
                    btn_Send.setEnabled(false);
                    p_supportFragment.sendMessage(txt_message);
                } else {
                    Toast.makeText(getContext(), getContext().getString(R.string.Create_an_account_first), Toast.LENGTH_SHORT).show();
                    ((MainActivity) getActivity()).onAddFragment(new LoginFragment(), R.anim.fadein, R.anim.short_fadeout, true, LoginFragment.TAG);
                }
                break;
        }
    }
}
