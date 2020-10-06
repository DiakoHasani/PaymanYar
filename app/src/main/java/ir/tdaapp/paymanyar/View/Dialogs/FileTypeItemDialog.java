package ir.tdaapp.paymanyar.View.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ir.tdaapp.paymanyar.Model.Services.onClickFileTypeItemDialog;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseDialogFragment;
import ir.tdaapp.paymanyar.R;

/**
 * مربوط به دیالوگ فایل آپلود در صفحات مصالح و نیروکار و ماشین آلات
 * **/
public class FileTypeItemDialog extends BaseDialogFragment implements View.OnClickListener {

    public static final String TAG = "FileTypeItemDialog";

    onClickFileTypeItemDialog clickFileTypeItemDialog;

    public FileTypeItemDialog(onClickFileTypeItemDialog clickFileTypeItemDialog) {
        this.clickFileTypeItemDialog = clickFileTypeItemDialog;
    }

    TextView btn_Camera, btn_Gallery;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.file_type_item_dialog, container, false);

        findItem(view);
        implement();

        return view;
    }

    void findItem(View view) {
        btn_Camera = view.findViewById(R.id.btn_Camera);
        btn_Gallery = view.findViewById(R.id.btn_Gallery);
    }

    void implement() {
        btn_Camera.setOnClickListener(this);
        btn_Gallery.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Camera:
                if (clickFileTypeItemDialog != null) {
                    clickFileTypeItemDialog.clickedCamera();
                }
                break;
            case R.id.btn_Gallery:
                if (clickFileTypeItemDialog != null) {
                    clickFileTypeItemDialog.clickedGallery();
                }
                break;
        }

        dismiss();
    }
}
