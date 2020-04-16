package ir.tdaapp.paymanyar.View.Dialogs;

import android.app.Dialog;
import android.graphics.Color;
import android.view.View;

import androidx.annotation.NonNull;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseBottomSheetDialogFragment;
import ir.tdaapp.paymanyar.R;

//زمانی که عملیات ما به خطا مواجه شود این دیالوگ نمایش داده می شود
public class ErrorAplicationDialog extends BaseBottomSheetDialogFragment {

    public static final String TAG = "ErrorAplicationDialog";

    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {

        super.setupDialog(dialog, style);
        View view = View.inflate(getContext(), R.layout.error_aplication_dialog, null);
        dialog.setContentView(view);

        ((View) view.getParent()).setBackgroundColor(Color.TRANSPARENT);

    }
}
