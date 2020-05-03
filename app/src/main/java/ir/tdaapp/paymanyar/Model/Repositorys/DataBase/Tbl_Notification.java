package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;
import android.content.SharedPreferences;

import ir.tdaapp.paymanyar.Model.Utilitys.SharedPreferences_Names;

import static android.content.Context.MODE_PRIVATE;

//مربوط به نوتیفیکشن ها
public class Tbl_Notification {

    //در اینجا توکن کاربر درج می شود
    public void add(Context context, String token) {
        SharedPreferences.Editor editor = context.getSharedPreferences(SharedPreferences_Names.MyPREFERENCES, MODE_PRIVATE).edit();
        editor.putString(SharedPreferences_Names.NotificationToken, token);
        editor.apply();
    }

    //در اینجا چک می کند که کاربر توکن دارد یا خیر
    public boolean hasToken(Context context) {

        if (!getToken(context).equalsIgnoreCase(""))
            return true;
        return false;

    }

    //در اینجا توکن کاربر گرفته می شود
    public String getToken(Context context) {
        SharedPreferences editor = context.getSharedPreferences(SharedPreferences_Names.MyPREFERENCES, MODE_PRIVATE);
        String token = editor.getString(SharedPreferences_Names.NotificationToken, "");
        return token;
    }

}
