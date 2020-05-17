package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ir.tdaapp.paymanyar.Model.Services.addTender;
import ir.tdaapp.paymanyar.Model.Services.onGetFevorites;
import ir.tdaapp.paymanyar.Model.Services.onSetFavoriteTenders;
import ir.tdaapp.paymanyar.Model.Services.removeTender;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotifications;

//مربوط به جدول مناقصات
public class Tbl_Tender {

    DBExcute db;
    Context context;

    public Tbl_Tender(Context context) {
        this.context = context;
        db = DBExcute.getInstance(context);
    }

    //در اینجا یک مناقصه به لیست علاقه مندی ها اضافه می شود
    public void Add(String tenderId, addTender t) {

        try {

            ETC.getId(db, "Tbl_Tender", id -> {
                try {

                    db.Execute("insert into Tbl_Tender (Id,TenderId) values (" + id + ",'" + tenderId + "')", new RecordHolder());
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

            db.Execute("delete from Tbl_Tender where TenderId='"+id+"'",new RecordHolder());
            t.onSuccess();

        }catch (Exception e){
            t.onError(e.toString());
        }

    }

    //در اینجا لیست علاقه مندی ها گرفته می شود
    public void getFevorites(onGetFevorites t) {

        List<String> vals = new ArrayList<>();

        db.Read("select TenderId from Tbl_Tender", new RecordHolder()).RecordFound(record -> {

            try {
                vals.add(record.get(0).value);
            } catch (Exception e) {

            }
        }, () -> {
            t.getTenders(vals);
        }, () -> {
            t.getTenders(vals);
        }, 1);
    }

    public boolean isFavoritTender(String id){

        return db.Read("select * from Tbl_Tender where TenderId='"+id+"'",new RecordHolder()).HasRecord();

    }
}
