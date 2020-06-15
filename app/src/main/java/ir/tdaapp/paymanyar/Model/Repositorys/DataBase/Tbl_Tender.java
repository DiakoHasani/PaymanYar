package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;
import android.database.Cursor;

import java.io.IOException;
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

            if (!isFavoritTender(tenderId)) {
                ETC.getId(db, "Tbl_Tender", id -> {
                    try {

                        db.ExecuteQ("insert into Tbl_Tender (Id,TenderId) values (" + id + ",'" + tenderId + "')");
                        t.onSuccess();

                    } catch (Exception e) {
                        t.onError(e.toString());
                    }
                });
            } else {
                t.onError("its already");
            }

        } catch (Exception e) {
            t.onError(e.toString());
        }
    }

    //در اینجا یک مناقصه از لیست علاقه مندی حذف می شود
    public void remove(String id, removeTender t) {

        try {

            db.ExecuteQ("delete from Tbl_Tender where TenderId='" + id + "'");
            t.onSuccess();

        } catch (Exception e) {
            t.onError(e.toString());
        }

    }

    public boolean isFavoritTender(String id) {

        Cursor q = db.ExecuteQ("select COUNT(Id) from Tbl_Tender where TenderId='" + id + "'");

        if (q.getString(0) != null) {
            return q.getInt(0) > 0;
        } else {
            return false;
        }

    }

    //در اینجا آیدی مورد علاقه ها پاس داده می شود
    public List<String> getFavorites() {

        List<String> vals = new ArrayList<>();

        try {

            Cursor q = db.ExecuteQ("select TenderId from Tbl_Tender order by Id desc LIMIT 40");

            for (int i = 0; i < q.getCount(); i++) {

                try {
                    String id = q.getString(q.getColumnIndex("TenderId"));
                    vals.add(id);
                } catch (Exception e) {
                }

                q.moveToNext();
            }

            q.close();

        } catch (Exception e) {
        }
        return vals;
    }

    //در اینجا آیدی مناقصه را می گیرد و آیدی بعدی آن را در جدول علاقه مندی برگشت می دهد
    public String getNextTenderIdFavorite(String tenderId) {

        try {
            List<String> vals = getFavorites();
            String nextTenderId = "";

            for (int i = 0; i < vals.size(); i++) {
                if (vals.get(i).equalsIgnoreCase(tenderId)) {
                    if (vals.get(i + 1) != null) {
                        nextTenderId = vals.get(i + 1);
                    }
                }
            }

            return nextTenderId;

        } catch (Exception e) {
            return "";
        }
    }

    //در اینجا آیدی مناقصه را می گیرد و آیدی قبل آن را در جدول علاقه مندی برگشت می دهد
    public String getPrevTenderIdFavorite(String tenderId) {

        try {
            List<String> vals = getFavorites();
            String prevTenderId = "";

            for (int i = 0; i < vals.size(); i++) {
                if (vals.get(i).equalsIgnoreCase(tenderId)) {
                    if (vals.get(i - 1) != null) {
                        prevTenderId = vals.get(i - 1);
                    }
                }
            }

            return prevTenderId;

        } catch (Exception e) {
            return "";
        }
    }
}
