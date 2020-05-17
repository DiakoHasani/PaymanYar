package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;

import ir.tdaapp.paymanyar.Model.Services.addLibrary;

public class Tbl_Library {

    DBExcute db;
    Context context;

    public Tbl_Library(Context context) {
        this.context = context;
        db = DBExcute.getInstance(context);
    }

    public void add(int libraryId, addLibrary a) {

        ETC.getId(db,"Tbl_Library", id -> {

            try{

                if (!hasLibray(id)){
                    db.Execute("insert into Tbl_Library (Id,LibraryId) values ("+id+","+libraryId+")",new RecordHolder());
                    a.onSuccess();
                }else{
                    a.onError("its all ready");
                }

            }catch (Exception e){
                a.onError(e.toString());
            }

        });

    }

    public boolean hasLibray(int libraryId) {

        return db.Read("select * from Tbl_Library where LibraryId=" + libraryId, new RecordHolder()).HasRecord();

    }

}
