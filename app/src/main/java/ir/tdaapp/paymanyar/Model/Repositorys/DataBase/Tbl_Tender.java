package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ir.tdaapp.paymanyar.Model.Services.addTender;
import ir.tdaapp.paymanyar.Model.Services.onGetFevorites;
import ir.tdaapp.paymanyar.Model.Services.onSetFavoriteTenders;
import ir.tdaapp.paymanyar.Model.Services.removeTender;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotifications;

//مربوط به جدول مناقصات
public class Tbl_Tender {

    DBAdapter db;
    Context context;

    public Tbl_Tender(Context context) {
        this.context = context;
        db = DBAdapter.getInstance(context);
    }

    //در اینجا یک مناقصه به لیست علاقه مندی ها اضافه می شود
    public void Add(String tenderId, addTender t) {

        try {

            ETC.getId(db, "Tbl_Tender", id -> {
                try {

                    db.ExecuteQ("insert into Tbl_Tender (Id,TenderId) values (" + id + ",'" + tenderId + "')");
                    t.onSuccess();

                } catch (Exception e) {
                    t.onError(e.toString());
                }
            });

        } catch (Exception e) {
            t.onError(e.toString());
        }
    }

    //در اینجا یک مناقصه از لیست علاقه مندی حذف می شود
    public void remove(String id, removeTender t){

        try{

            db.ExecuteQ("delete from Tbl_Tender where TenderId='"+id+"'");
            t.onSuccess();

        }catch (Exception e){
            t.onError(e.toString());
        }

    }

    public boolean isFavoritTender(String id){

        Cursor q = db.ExecuteQ("select COUNT(Id) from Tbl_Tender where TenderId='" + id+"'");

        if (q.getString(0) != null) {
            return q.getInt(0) > 0;
        } else {
            return false;
        }

    }
}
