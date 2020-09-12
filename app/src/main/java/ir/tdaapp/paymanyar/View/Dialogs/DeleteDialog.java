package ir.tdaapp.paymanyar.View.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ir.tdaapp.paymanyar.Model.Services.clickDeleteDialog;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseDialogFragment;
import ir.tdaapp.paymanyar.R;

/**
 * مربوط به دیالوگ حذف
 * **/
public class DeleteDialog extends BaseDialogFragment implements View.OnClickListener {

    public final static String TAG = "DeleteDialog";

    Button btn_No, btn_Yes;
    clickDeleteDialog deleteDialog;

    public DeleteDialog(clickDeleteDialog deleteDialog) {
        this.deleteDialog = deleteDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.delete_dialog, container, false);

        findItem(view);
        implement();

        return view;
    }

    void findItem(View view) {
        btn_No = view.findViewById(R.id.btn_No);
        btn_Yes = view.findViewById(R.id.btn_Yes);
    }

    void implement() {
        btn_No.setOnClickListener(this);
        btn_Yes.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_No:
                deleteDialog.clickedNo();
                dismiss();
                break;
            case R.id.btn_Yes:
                deleteDialog.clickedYes();
                dismiss();
                break;
        }
    }
}
