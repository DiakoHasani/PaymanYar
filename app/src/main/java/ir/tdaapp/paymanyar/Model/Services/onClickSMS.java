package ir.tdaapp.paymanyar.Model.Services;

import android.widget.ImageView;

public interface onClickSMS {
    void onClickLayout(String id);
    void onClickAddFevorit(String id, ImageView star);
    void onClickRemoveFevorit(String id,ImageView star);
    void onClickArchive(String msgId);
}
