package ir.tdaapp.paymanyar.View.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ir.tdaapp.paymanyar.Model.Services.onResumeChargeDialog;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseDialogFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Charge;
import ir.tdaapp.paymanyar.R;

//دیالوگ مربوط به خرید شارژ
public class ByChargeDialog extends BaseDialogFragment implements View.OnClickListener {

    public final static String TAG = "ByChargeDialog";

    private onResumeChargeDialog resumeChargeDialog;
    private VM_Charge charge;
    TextView lbl_Title, lbl_Description, btn_Cancel, btn_Buy;
    ImageView img;
    boolean clickedButtonBuy = false;

    public ByChargeDialog(VM_Charge charge, onResumeChargeDialog resumeChargeDialog) {
        this.resumeChargeDialog = resumeChargeDialog;
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

    void implement(){
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
    public void onResume() {
        super.onResume();

        if (resumeChargeDialog != null) {
            if (clickedButtonBuy) {
                resumeChargeDialog.onResume();
            }
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Cancel:
                getActivity().onBackPressed();
                break;
            case R.id.btn_Buy:
                clickedButtonBuy = true;
                break;
        }
    }
}
