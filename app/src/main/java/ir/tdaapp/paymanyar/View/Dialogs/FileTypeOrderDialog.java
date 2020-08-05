package ir.tdaapp.paymanyar.View.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ir.tdaapp.paymanyar.Model.Services.onClickFileTypeOrderDialog;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseDialogFragment;
import ir.tdaapp.paymanyar.R;

//مربوط دیالوگ نوع فایل در صفحه سفارشات کاربر انتخاب می کند چه نوع فایلی را انتخاب کند عکس یا گالری یا فایل دیگر
public class FileTypeOrderDialog extends BaseDialogFragment implements View.OnClickListener {

    public final static String TAG = "FileTypeOrderDialog";

    public FileTypeOrderDialog(onClickFileTypeOrderDialog clickFileTypeOrderDialog) {
        this.clickFileTypeOrderDialog = clickFileTypeOrderDialog;
    }

    onClickFileTypeOrderDialog clickFileTypeOrderDialog;

    TextView btn_Camera, btn_Gallery, btn_File;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.file_type_order_dialog, container, false);

        findItem(view);
        implement();

        return view;
    }

    void findItem(View view) {
        btn_Camera = view.findViewById(R.id.btn_Camera);
        btn_Gallery = view.findViewById(R.id.btn_Gallery);
        btn_File = view.findViewById(R.id.btn_File);
    }

    void implement() {
        btn_Camera.setOnClickListener(this);
        btn_Gallery.setOnClickListener(this);
        btn_File.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Camera:
                if (clickFileTypeOrderDialog != null) {
                    clickFileTypeOrderDialog.clickedCamera();
                }
                break;
            case R.id.btn_Gallery:
                if (clickFileTypeOrderDialog != null) {
                    clickFileTypeOrderDialog.clickedGallery();
                }
                break;
            case R.id.btn_File:
                if (clickFileTypeOrderDialog != null) {
                    clickFileTypeOrderDialog.clickedFile();
                }
                break;
        }

        dismiss();
    }
}
