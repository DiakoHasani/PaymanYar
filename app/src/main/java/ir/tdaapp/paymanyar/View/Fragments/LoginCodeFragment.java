package ir.tdaapp.paymanyar.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.R;

//این صفحه مربوط به زمانی ست که که کاربر در صفحه لاگین شماره خود را وارد می کند و در این صفحه کدی که برای او پیامک شده است وارد می کند تا عملیات لاگین به اتمام برسد
public class LoginCodeFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.login_code_fragment,container,false);
        return view;
    }
}
