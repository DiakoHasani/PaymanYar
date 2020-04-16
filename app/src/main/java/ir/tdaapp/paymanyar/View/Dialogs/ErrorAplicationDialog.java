package ir.tdaapp.paymanyar.View.Dialogs;

import android.app.Dialog;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import ir.tdaapp.paymanyar.Model.Services.onClickErrorAplicationDialog;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseBottomSheetDialogFragment;
import ir.tdaapp.paymanyar.R;

//زمانی که عملیات ما به خطا مواجه شود این دیالوگ نمایش داده می شود
public class ErrorAplicationDialog extends BaseBottomSheetDialogFragment implements View.OnClickListener {

    public static final String TAG = "ErrorAplicationDialog";

    TextView lbl_Title, lbl_Description, lbl_TextButton;
    CardView btn;
    ImageView ic_icon;

    String title;
    String description;
    String textButton;
    int icon;
    int color;
    onClickErrorAplicationDialog ClickErrorAplicationDialog;

    //title برای متن تیتر مثل عملیات با موفقیت انجام شد یا خطای رخ داده است
    //description برای متن توضیحات درباره عملیات
    //textButton برای متن دکمه ما
    //icon برای آیکون بالای صفحه
    //color برای رنگ آیتم های صفحه مثال اگر خطای در عملیات رخ دهد تم ما قرمز باشد
    //ClickErrorAplicationDialog زمانی که کاربر رو دکمه کلیک کند متد کلیک آن فعال می شود و می توانیم عملیات خود را انجام دهیم
    public ErrorAplicationDialog(String title, String description, String textButton, int icon, int color, onClickErrorAplicationDialog ClickErrorAplicationDialog) {
        this.title = title;
        this.description = description;
        this.textButton = textButton;
        this.icon = icon;
        this.color = color;
        this.ClickErrorAplicationDialog = ClickErrorAplicationDialog;
    }

    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {

        super.setupDialog(dialog, style);
        View view = View.inflate(getContext(), R.layout.error_aplication_dialog, null);

        findItem(view);
        implement();
        setItem();

        dialog.setContentView(view);

        ((View) view.getParent()).setBackgroundColor(Color.TRANSPARENT);

    }

    void findItem(View view){
        lbl_Title=view.findViewById(R.id.lbl_Title);
        lbl_Description=view.findViewById(R.id.lbl_Description);
        lbl_TextButton=view.findViewById(R.id.lbl_TextButton);
        btn=view.findViewById(R.id.btn);
        ic_icon=view.findViewById(R.id.ic_icon);
    }

    void implement(){
        btn.setOnClickListener(this);
    }

    //در اینجا متن و رنگ المنت های موجود در صفحه ست می شوند
    void setItem(){
        lbl_Title.setText(title);
        lbl_Description.setText(description);
        lbl_TextButton.setText(textButton);

        lbl_Title.setTextColor(getContext().getResources().getColor(color));
        ic_icon.setImageDrawable(getContext().getResources().getDrawable(icon));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn:
                ClickErrorAplicationDialog.click();
                break;
        }
    }
}
