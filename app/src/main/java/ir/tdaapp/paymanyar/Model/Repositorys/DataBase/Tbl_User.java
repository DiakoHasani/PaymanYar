package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;
import android.content.SharedPreferences;

import ir.tdaapp.paymanyar.Model.Utilitys.SharedPreferences_Names;

import static android.content.Context.MODE_PRIVATE;

public class Tbl_User {

    public void add(Context context, int userId) {
        SharedPreferences.Editor editor = context.getSharedPreferences(SharedPreferences_Names.MyPREFERENCES, MODE_PRIVATE).edit();
        editor.putInt(SharedPreferences_Names.UserId, userId);
        editor.apply();
    }

    public boolean hasAccount(Context context) {

        if (getUserId(context) > 0)
            return true;
        return false;

    }

    public int getUserId(Context context) {

        SharedPreferences editor = context.getSharedPreferences(SharedPreferences_Names.MyPREFERENCES, MODE_PRIVATE);
        int userId = editor.getInt(SharedPreferences_Names.UserId, 0);
        return userId;

    }

}
