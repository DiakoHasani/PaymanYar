package ir.tdaapp.paymanyar.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputEditText;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import es.dmoral.toasty.Toasty;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Services.S_LoginCodeFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.Presenter.P_LoginCodeFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;

//این صفحه مربوط به زمانی ست که که کاربر در صفحه لاگین شماره خود را وارد می کند و در این صفحه کدی که برای او پیامک شده است وارد می کند تا عملیات لاگین به اتمام برسد
public class LoginCodeFragment extends BaseFragment implements S_LoginCodeFragment, View.OnClickListener {

    public static final String TAG = "LoginCodeFragment";
    P_LoginCodeFragment p_loginCodeFragment;

    CardView btn_Resend, btn_Login;
    TextView lbl_resend, lbl_btn_Login;
    TextInputEditText txt_Code;
    ProgressBar progress_btn_Login, progress_btn_Resend;
    RelativeLayout bottom;

    String PhoneNumber;

    public LoginCodeFragment(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_code_fragment, container, false);

        findItem(view);
        implement();

        return view;
    }

    void findItem(View view) {
        btn_Resend = view.findViewById(R.id.btn_Resend);
        btn_Login = view.findViewById(R.id.btn_Login);
        lbl_resend = view.findViewById(R.id.lbl_resend);
        txt_Code = view.findViewById(R.id.txt_Code);
        progress_btn_Login = view.findViewById(R.id.progress_btn_Login);
        progress_btn_Resend = view.findViewById(R.id.progress_btn_Resend);
        lbl_btn_Login = view.findViewById(R.id.lbl_btn_Login);
        bottom = view.findViewById(R.id.bottom);
    }

    void implement() {
        p_loginCodeFragment = new P_LoginCodeFragment(getContext(), this);
        btn_Login.setOnClickListener(this);
        btn_Resend.setOnClickListener(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        p_loginCodeFragment.Cancel(TAG);
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
        Toasty.error(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoading(boolean load) {
        if (load) {
            btn_Login.setEnabled(false);
            progress_btn_Login.setVisibility(View.VISIBLE);
            lbl_btn_Login.setVisibility(View.GONE);
        } else {
            btn_Login.setEnabled(true);
            progress_btn_Login.setVisibility(View.GONE);
            lbl_btn_Login.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onFinish(VM_Message message) {
        if (message.isResult()) {

            try {

                ((MainActivity)getActivity()).getTbl_user().add(getContext(),message.getCode());

                HomeFragment homeFragment=((HomeFragment)getActivity().getSupportFragmentManager().findFragmentByTag(HomeFragment.TAG));

                if (homeFragment!=null){
                    homeFragment.hideMenuLoginNavigation();
                }

                //در اینجا صفحات مربوط به لاگین بسته می شود
                ((MainActivity)getActivity()).onBackPressedLoginPage();


            }catch (Exception e){ }

        } else {
            Toasty.error(getContext(), message.getMessage(), Toast.LENGTH_SHORT,true).show();
        }
    }

    @Override
    public void onNotValid() {

    }

    @Override
    public void onStartTimer() {
        btn_Resend.setEnabled(false);
        lbl_resend.setText("0");
    }

    @Override
    public void onSecondTimer(String second) {
        lbl_resend.setText(second);
    }

    @Override
    public void onFinishTimer() {
        btn_Resend.setEnabled(true);
        lbl_resend.setText(getContext().getString(R.string.Resend));
    }

    @Override
    public void onLoadingResend(boolean load) {
        if (load) {
            progress_btn_Resend.setVisibility(View.VISIBLE);
            lbl_resend.setVisibility(View.GONE);
        } else {
            progress_btn_Resend.setVisibility(View.GONE);
            lbl_resend.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onFinishResend(VM_Message message) {
        if (message.isResult()) {
            p_loginCodeFragment.startTimer();
        } else {
            Toasty.error(getContext(), message.getMessage(), Toast.LENGTH_SHORT,true).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Login:
                p_loginCodeFragment.sendCode(txt_Code, PhoneNumber);
                break;
            case R.id.btn_Resend:
                p_loginCodeFragment.resendSMS(PhoneNumber);
                break;
        }
    }
}
