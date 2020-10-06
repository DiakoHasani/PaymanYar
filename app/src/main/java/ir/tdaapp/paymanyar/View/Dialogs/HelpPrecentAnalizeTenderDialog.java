package ir.tdaapp.paymanyar.View.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseDialogFragment;
import ir.tdaapp.paymanyar.R;

//مربوط به راهنمای بخش درصدها در صفحه آنالیز مناقصات
public class HelpPrecentAnalizeTenderDialog extends BaseDialogFragment {

    public static final String TAG = "HelpPrecentAnalizeTenderDialog";

    TextView btn_ok;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.help_precent_analize_tender_dialog, container, false);

        btn_ok = view.findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(view1 -> {
            dismiss();
        });

        return view;
    }
}
