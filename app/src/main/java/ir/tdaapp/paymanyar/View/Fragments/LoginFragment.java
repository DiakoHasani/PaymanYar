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

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Services.S_LoginFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.Presenter.P_LoginFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;

public class LoginFragment extends BaseFragment implements S_LoginFragment,View.OnClickListener {

    public final static String TAG = "LoginFragment";
    P_LoginFragment p_loginFragment;

    TextInputEditText txt_CellPhone;
    CardView btn_Login;
    TextView lbl_btn_Login;
    ProgressBar progress_btn_Login;
    RelativeLayout bottom;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        findItem(view);
        implement();

        return view;
    }

    void findItem(View view) {
        txt_CellPhone = view.findViewById(R.id.txt_CellPhone);
        btn_Login = view.findViewById(R.id.btn_Login);
        lbl_btn_Login = view.findViewById(R.id.lbl_btn_Login);
        progress_btn_Login = view.findViewById(R.id.progress_btn_Login);
        bottom = view.findViewById(R.id.bottom);
    }

    void implement() {
        p_loginFragment = new P_LoginFragment(getContext(), this);
        btn_Login.setOnClickListener(this);

        KeyboardVisibilityEvent.setEventListener(getActivity(), isOpen -> {
            if (isOpen) {
                bottom.setVisibility(View.GONE);
            } else {
                bottom.setVisibility(View.VISIBLE);
            }
        });

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
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onLoading(boolean load) {

        if (load) {
            btn_Login.setEnabled(false);
            lbl_btn_Login.setVisibility(View.GONE);
            progress_btn_Login.setVisibility(View.VISIBLE);
        } else {
            btn_Login.setEnabled(true);
            lbl_btn_Login.setVisibility(View.VISIBLE);
            progress_btn_Login.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFinish(VM_Message message) {

        if (message.isResult()) {

            ((MainActivity)getActivity()).onAddFragment(new LoginCodeFragment(txt_CellPhone.getText().toString()),
                    R.anim.fadein,R.anim.short_fadeout,true,LoginCodeFragment.TAG);

        } else {
            Toast.makeText(getContext(), message.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onNotValid() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_Login:
                p_loginFragment.startLogin(txt_CellPhone);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        p_loginFragment.Cancel(TAG);
    }
}
