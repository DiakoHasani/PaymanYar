package ir.tdaapp.paymanyar.Model.Services;

import android.widget.ImageView;

//برای رویداد کلیک آیتم های مناقصات
public interface onClickTenderNotification {
    void onClick(String id);
    void onClickFavorit_Add(String id, ImageView icon);
    void onClickFavorit_remove(String id, ImageView icon);
}
