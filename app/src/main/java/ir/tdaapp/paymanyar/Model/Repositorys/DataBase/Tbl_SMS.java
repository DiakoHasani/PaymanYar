package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;
import android.database.Cursor;

import ir.tdaapp.paymanyar.Model.Services.addSMS;
import ir.tdaapp.paymanyar.Model.Services.removeSMS;

public class Tbl_SMS {

    DBAdapter db;
    Context context;

    public Tbl_SMS(Context context) {
        this.context = context;
        db = DBAdapter.getInstance(context);
    }

    public void add(String smsId, addSMS a) {

        ETC.getId(db, "Tbl_SMS", id -> {

            try {
                db.ExecuteQ("insert into Tbl_SMS (Id,SmsId) values (" + id + ",'" + smsId + "')");
                a.onSuccess();
            } catch (Exception e) {
                a.onError(e.toString());
            }

        });

    }

    public void remove(String id, removeSMS r) {

        try {

            db.ExecuteQ("delete from Tbl_SMS where SmsId='"+id+"'");
            r.onSuccess();

        } catch (Exception e) {
            r.onError(e.toString());
        }

    }

    public boolean hasSMS(String smsId) {

        Cursor q = db.ExecuteQ("select COUNT(Id) from Tbl_SMS where SmsId='" + smsId+"'");

        if (q.getString(0) != null) {
            return q.getInt(0) > 0;
        } else {
            return false;
        }
    }

}
