package ir.tdaapp.paymanyar.View.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ir.tdaapp.paymanyar.Model.Services.onClickGoToLoginDialog;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseDialogFragment;
import ir.tdaapp.paymanyar.R;

public class GoToLoginDialog extends BaseDialogFragment implements View.OnClickListener {

    public static final String TAG = "GoToLoginDialog";

    onClickGoToLoginDialog clickGoToLoginDialog;

    public GoToLoginDialog(onClickGoToLoginDialog clickGoToLoginDialog) {
        this.clickGoToLoginDialog = clickGoToLoginDialog;
    }

    TextView btn_Ok, btn_neverMind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.go_to_login_dialog, container, false);

        findItem(view);
        implement();

        return view;
    }

    void findItem(View view) {
        btn_Ok = view.findViewById(R.id.btn_Ok);
        btn_neverMind = view.findViewById(R.id.btn_neverMind);
    }

    void implement() {
        btn_Ok.setOnClickListener(this);
        btn_neverMind.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Ok:
                clickGoToLoginDialog.ok();
                dismiss();
                break;
            case R.id.btn_neverMind:
                clickGoToLoginDialog.neverMind();
                dismiss();
                break;
        }
    }
}
