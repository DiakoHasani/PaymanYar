package ir.tdaapp.paymanyar.View.Dialogs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseDialogFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.openUrl;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Charge;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Fragments.HomeFragment;
import ir.tdaapp.paymanyar.View.Fragments.LoginFragment;

//دیالوگ مربوط به خرید شارژ
public class BuyChargeDialog extends BaseDialogFragment implements View.OnClickListener {

    public final static String TAG = "ByChargeDialog";

    private VM_Charge charge;
    TextView lbl_Title, lbl_Description, btn_Cancel, btn_Buy;
    ImageView img;

    public BuyChargeDialog(VM_Charge charge) {
        this.charge = charge;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.by_charge_dialog, container, false);

        findItem(view);
        implement();

        setDetails();

        return view;
    }

    void findItem(View view) {
        lbl_Title = view.findViewById(R.id.lbl_Title);
        lbl_Description = view.findViewById(R.id.lbl_Description);
        img = view.findViewById(R.id.img);
        btn_Cancel = view.findViewById(R.id.btn_Cancel);
        btn_Buy = view.findViewById(R.id.btn_Buy);
    }

    void implement() {
        btn_Buy.setOnClickListener(this);
        btn_Cancel.setOnClickListener(this);
    }

    void setDetails() {
        lbl_Title.setText(charge.getTitle());
        lbl_Description.setText(charge.getSubTitle());

        if (charge.isSpecial()) {
            img.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_special_charge_store));
        } else {
            img.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_charge_store));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Cancel:
                getActivity().onBackPressed();
                break;
            case R.id.btn_Buy:

                if (((MainActivity) getActivity()).getTbl_user().hasAccount(getContext())) {
                    getDialog().dismiss();
                    gotoBuy();
                } else {
                    Toast.makeText(getContext(), getResources().getString(R.string.Create_an_account_first), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    void gotoBuy() {

        String api_key = ((MainActivity) getActivity()).getTbl_notification().getToken(getContext());
        String url = "http://tiptop.tdaapp.ir/Payment/Index?ChargeId=" + charge.getId() + "&ApiKey=" + api_key;
        openUrl.getWeb(url,getContext());
    }
}
