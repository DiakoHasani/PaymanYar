package ir.tdaapp.paymanyar.View.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ir.tdaapp.paymanyar.Model.Services.SelectedItemFilterWideDialog;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseDialogFragment;
import ir.tdaapp.paymanyar.R;

public class FilterWideDialog extends BaseDialogFragment implements View.OnClickListener {

    public final static String TAG = "FilterWideDialog";

    private SelectedItemFilterWideDialog selectedItemFilterWideDialog;
    Button btn_Select, btn_Cancel;

    public FilterWideDialog(SelectedItemFilterWideDialog selectedItemFilterWideDialog) {
        this.selectedItemFilterWideDialog = selectedItemFilterWideDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.filter_wide_dialog, container, false);

        findItem(view);
        implement();

        return view;
    }

    void findItem(View view) {
        btn_Select = view.findViewById(R.id.btn_Select);
        btn_Cancel = view.findViewById(R.id.btn_Cancel);
    }

    void implement(){
        btn_Select.setOnClickListener(this);
        btn_Cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_Select:
                selectedItemFilterWideDialog.onClick(0);
                getDialog().dismiss();
                break;
            case R.id.btn_Cancel:
                getDialog().dismiss();
                break;
        }
    }
}
