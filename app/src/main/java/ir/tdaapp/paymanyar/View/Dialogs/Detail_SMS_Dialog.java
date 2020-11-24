package ir.tdaapp.paymanyar.View.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import es.dmoral.toasty.Toasty;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Services.S_Detail_SMS_Dialog;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseDialogFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailSMS;
import ir.tdaapp.paymanyar.Presenter.P_Detail_SMS_Dialog;
import ir.tdaapp.paymanyar.R;
import pl.droidsonroids.gif.GifImageView;

public class Detail_SMS_Dialog extends BaseDialogFragment implements S_Detail_SMS_Dialog,View.OnClickListener {

    public static final String TAG="Detail_SMS_Dialog";

    private String smsId;

    public Detail_SMS_Dialog(String smsId) {
        this.smsId = smsId;
    }

    ImageView close;
    TextView lbl_Title,lbl_Description;
    P_Detail_SMS_Dialog p_detail_sms_dialog;
    GifImageView loading;
    ImageView btn_reload;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view=inflater.inflate(R.layout.detail_sms_dialog,container,false);

        findItem(view);
        implement();

        p_detail_sms_dialog.start(smsId);

        return view;
    }

    void findItem(View view){
        close=view.findViewById(R.id.close);
        lbl_Title=view.findViewById(R.id.lbl_Title);
        lbl_Description=view.findViewById(R.id.lbl_Description);
        loading=view.findViewById(R.id.loading);
        btn_reload=view.findViewById(R.id.btn_reload);
    }

    void implement(){
        p_detail_sms_dialog=new P_Detail_SMS_Dialog(getContext(),this);

        btn_reload.setOnClickListener(this);
        close.setOnClickListener(this);
    }

    @Override
    public void onError(ResaultCode result) {
        String message = "";

        switch (result) {
            case NetworkError:
                message = getString(R.string.please_Checked_Your_Internet_Connection);
                break;
            case TimeoutError:
                message = getString(R.string.YourInternetIsVrySlow);
                break;
            case ServerError:
                message = getString(R.string.There_Was_an_Error_In_The_Server);
                break;
            case ParseError:
            case Error:
                message = getString(R.string.There_Was_an_Error_In_The_Application);
                break;
        }

        Toasty.error(getContext(), message, Toast.LENGTH_SHORT, true).show();
        btn_reload.setVisibility(View.VISIBLE);
    }

    @Override
    public void OnStart() {
        lbl_Title.setText("");
        lbl_Description.setText("");
    }

    @Override
    public void onHideAll() {
        lbl_Description.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
        btn_reload.setVisibility(View.GONE);
    }

    @Override
    public void onFinish(VM_DetailSMS sms) {
        lbl_Description.setVisibility(View.VISIBLE);

        lbl_Description.setText(sms.getDescription());
        lbl_Title.setText(sms.getDate());
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
    public void onDestroy() {
        super.onDestroy();
        p_detail_sms_dialog.Cancel(TAG);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_reload:
                p_detail_sms_dialog.start(smsId);
                break;
            case R.id.close:
                getActivity().onBackPressed();
                break;
        }
    }
}
