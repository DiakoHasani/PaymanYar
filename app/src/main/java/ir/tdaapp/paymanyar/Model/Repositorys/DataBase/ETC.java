package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ir.tdaapp.paymanyar.Model.Services.etcDataBase;

//در اینجا عملیاتی که مربوط به هیچ جدول خاصی نمی باشد انجام می شود مثلا آخرین آی دی تمامی جدول ها گرفته می شود
public class ETC {

    //در اینجا نام یک جدول می گیرد و آیدی بعدی آن را پاس می دهد
    public static void getId(DBAdapter db, String tableName, etcDataBase i) {

        Cursor GetId = db.ExecuteQ("select MAX(Id) from "+tableName);
        int Id;
        if (GetId.getString(0) != null)
            Id = Integer.parseInt(GetId.getString(0)) + 1;
        else
            Id = 1;

        i.getId(Id);
    }

}
