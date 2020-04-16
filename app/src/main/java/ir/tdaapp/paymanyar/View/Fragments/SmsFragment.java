package ir.tdaapp.paymanyar.View.Fragments;

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
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Services.S_SmsFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Presenter.P_SmsFragment;
import ir.tdaapp.paymanyar.R;

public class SmsFragment extends BaseFragment implements S_SmsFragment {

    Toolbar toolBar;
    RecyclerView recycler;
    P_SmsFragment p_smsFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sms_fragment, container, false);

        findItem(view);
        implement();

        return view;
    }

    void findItem(View view) {
        toolBar = view.findViewById(R.id.toolBar);
        recycler = view.findViewById(R.id.recycler);
    }

    void implement(){
        p_smsFragment=new P_SmsFragment(getContext(),this);
    }

    //زمانی که عملیات گرفتن داده ها از سرور شروع شود متد زیر فراخوانی می شود
    @Override
    public void OnStart() {

    }

    //اگر خای رخ دهد متد زیر فراخوانی می شود
    @Override
    public void onError(ResaultCode resault) {

    }

    //اگر عملیات ما به په پایان رسید متد زیر فراخوانی می شود
    @Override
    public void onFinish() {

    }
}
