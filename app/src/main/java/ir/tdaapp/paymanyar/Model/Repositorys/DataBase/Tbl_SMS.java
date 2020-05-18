package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;

import ir.tdaapp.paymanyar.Model.Services.addSMS;

public class Tbl_SMS {

    DBExcute db;
    Context context;

    public Tbl_SMS(Context context) {
        this.context = context;
        db = DBExcute.getInstance(context);
    }

    public void add(int smsId, addSMS a) {

        ETC.getId(db, "Tbl_SMS", id -> {

            try {
                db.Execute("insert into Tbl_SMS (Id,SmsId) values (" + id + "," + smsId + ")", new RecordHolder());
                a.onSuccess();
            } catch (Exception e) {
                a.onError(e.toString());
            }

        });

    }

    public boolean hasSMS(int smsId) {
        return db.Read("select * from Tbl_SMS where SmsId=" + smsId, new RecordHolder()).HasRecord();
    }

}
