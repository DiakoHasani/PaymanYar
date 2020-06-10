package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;
import android.database.Cursor;

import ir.tdaapp.paymanyar.Model.Services.addLibrary;

public class Tbl_Library {

    DBAdapter db;
    Context context;

    public Tbl_Library(Context context) {
        this.context = context;
        db = DBAdapter.getInstance(context);
    }

    public void add(int libraryId, addLibrary a) {

        ETC.getId(db, "Tbl_Library", id -> {

            try {

                if (!hasLibray(id)) {
                    db.ExecuteQ("insert into Tbl_Library (Id,LibraryId) values (" + id + "," + libraryId + ")");
                    a.onSuccess();
                } else {
                    a.onError("its all ready");
                }

            } catch (Exception e) {
                a.onError(e.toString());
            }

        });

    }

    public boolean hasLibray(int libraryId) {

        Cursor q = db.ExecuteQ("select COUNT(Id) from Tbl_Library where LibraryId=" + libraryId);

        if (q.getString(0) != null) {
            return q.getInt(0) > 0;
        } else {
            return false;
        }

    }

}
