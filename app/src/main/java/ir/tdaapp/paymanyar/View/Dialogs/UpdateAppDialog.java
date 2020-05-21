package ir.tdaapp.paymanyar.View.Dialogs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseDialogFragment;
import ir.tdaapp.paymanyar.R;

public class UpdateAppDialog extends BaseDialogFragment implements View.OnClickListener {

    public static final String TAG = "UpdateAppDialog";

    Button btn_yes, btn_no;

    private boolean hadUpdate;

    public UpdateAppDialog(boolean hadUpdate) {
        this.hadUpdate = hadUpdate;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.update_app_dialog, container, false);

        findItem(view);
        implement();

        return view;
    }

    void findItem(View view) {
        btn_yes = view.findViewById(R.id.btn_yes);
        btn_no = view.findViewById(R.id.btn_no);
    }

    void implement() {
        btn_yes.setOnClickListener(this);
        btn_no.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_yes:

                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri.Builder uriBuilder = Uri.parse("https://play.google.com/store/apps/details?id=ir.tdaapp.karsan&hl=en")
                        .buildUpon()
                        .appendQueryParameter("id", "ir.tdaapp.karsan")
                        .appendQueryParameter("launch", "false");

                intent.setData(uriBuilder.build());
                intent.setPackage("com.android.vending");
                startActivity(intent);
                break;
            case R.id.btn_no:

                if (!hadUpdate){
                    getDialog().dismiss();
                }
                break;
        }
    }
}
