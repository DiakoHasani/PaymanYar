package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;

import ir.tdaapp.paymanyar.Model.Services.addSMS;
import ir.tdaapp.paymanyar.Model.Services.removeSMS;

public class Tbl_SMS {

    DBExcute db;
    Context context;

    public Tbl_SMS(Context context) {
        this.context = context;
        db = DBExcute.getInstance(context);
    }

    public void add(String smsId, addSMS a) {

        ETC.getId(db, "Tbl_SMS", id -> {

            try {
                db.Execute("insert into Tbl_SMS (Id,SmsId) values (" + id + ",'" + smsId + "')", new RecordHolder());
                a.onSuccess();
            } catch (Exception e) {
                a.onError(e.toString());
            }

        });

    }

    public void remove(String id, removeSMS r) {

        try {

            db.Execute("delete from Tbl_SMS where SmsId='"+id+"'", new RecordHolder());
            r.onSuccess();

        } catch (Exception e) {
            r.onError(e.toString());
        }

    }

    public boolean hasSMS(String smsId) {
        return db.Read("select * from Tbl_SMS where SmsId='"+smsId+"'", new RecordHolder()).HasRecord();
    }

}
