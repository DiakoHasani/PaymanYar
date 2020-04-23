package ir.tdaapp.paymanyar.View.Dialogs;

import android.app.Dialog;
import android.view.View;

import androidx.annotation.NonNull;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseDialogFragment;
import ir.tdaapp.paymanyar.R;

//دیالوگ مربوط به جزئیات مناقصات
public class DetailsTenderDialog extends BaseDialogFragment {
    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View view=View.inflate(getContext(), R.layout.details_tender_dialog,null);
        dialog.setContentView(view);
    }
}
