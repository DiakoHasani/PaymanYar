package ir.tdaapp.paymanyar.View.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ir.tdaapp.paymanyar.Model.Services.onClickFilterOrdersDialog;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseDialogFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FilterOrder;
import ir.tdaapp.paymanyar.R;

public class FilterOrdersDialog extends BaseDialogFragment implements View.OnClickListener {

    public final static String TAG = "FilterOrdersDialog";

    VM_FilterOrder filterOrder;
    CheckBox chk_tenderAnalise, chk_scheduling, chk_audit, chk_difference, chk_costEstimation;
    TextView btn_Search, btn_Cancel;
    onClickFilterOrdersDialog clickFilterOrdersDialog;

    public FilterOrdersDialog(VM_FilterOrder filterOrder, onClickFilterOrdersDialog clickFilterOrdersDialog) {
        this.filterOrder = filterOrder;
        this.clickFilterOrdersDialog = clickFilterOrdersDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.filter_orders_dialog, container, false);

        findItem(view);
        implement();
        getFilter();

        return view;
    }

    void findItem(View view) {
        chk_tenderAnalise = view.findViewById(R.id.chk_tenderAnalise);
        chk_scheduling = view.findViewById(R.id.chk_scheduling);
        chk_audit = view.findViewById(R.id.chk_audit);
        chk_difference = view.findViewById(R.id.chk_difference);
        chk_costEstimation = view.findViewById(R.id.chk_costEstimation);
        btn_Search = view.findViewById(R.id.btn_Search);
        btn_Cancel = view.findViewById(R.id.btn_Cancel);
    }

    void implement() {
        btn_Search.setOnClickListener(this);
        btn_Cancel.setOnClickListener(this);
    }

    //در اینجا اگر کاربر قبلا فیلتر کرده باشد اونارو نمایش میده
    void getFilter() {
        chk_tenderAnalise.setChecked(filterOrder.isTenderAnalise());
        chk_scheduling.setChecked(filterOrder.isScheduling());
        chk_audit.setChecked(filterOrder.isAudit());
        chk_difference.setChecked(filterOrder.isDifference());
        chk_costEstimation.setChecked(filterOrder.isCostEstimation());
    }

    //در اینجا فیلتر های جدید در ویومدل ست می شود
    void setFilter() {
        filterOrder.setTenderAnalise(chk_tenderAnalise.isChecked());
        filterOrder.setScheduling(chk_scheduling.isChecked());
        filterOrder.setAudit(chk_audit.isChecked());
        filterOrder.setDifference(chk_difference.isChecked());
        filterOrder.setCostEstimation(chk_costEstimation.isChecked());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Search:
                setFilter();

                if (clickFilterOrdersDialog != null) {
                    clickFilterOrdersDialog.clickSearch();
                }

                dismiss();
                break;
            case R.id.btn_Cancel:

                if (clickFilterOrdersDialog != null) {
                    clickFilterOrdersDialog.clickCancel();
                }

                dismiss();
                break;
        }
    }
}
