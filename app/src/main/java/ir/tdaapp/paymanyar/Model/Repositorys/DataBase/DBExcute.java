package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.ContentResolver;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class DBExcute {

    private database db;
    private static DBExcute sInstance;

    private DBExcute(Context context){
        this.db=database.getInstance(context);
    }

    public static synchronized DBExcute getInstance(Context context) {
        try {
            if (sInstance == null) {
                sInstance = new DBExcute(context.getApplicationContext());
            }
        }catch (Exception e){}
        return sInstance;
    }

    public void CopyDB(){
        this.db.databse();
        this.db.open();
        this.db.close();
    }

    public void Open(){try{this.db.open();}catch (Exception e){}}

    public void Close(){
        try{this.db.close();}catch (Exception e){}
    }

    public void Execute(String QRY, @Nullable RecordHolder fields){

        String qry=QRY;
        if(fields!=null)qry=MixQuery(QRY, fields.GetRecords());
        this.db.ExectueQuery(qry);
    }

    public DBCursor Read(String Qry,@Nullable RecordHolder fields){

        DBCursor row=new DBCursor();

        try {

            String qry=Qry;
            if(fields!=null)qry=MixQuery(Qry, fields.GetRecords());
            row = this.db.CursorQuery(qry);


        }catch (Exception e){
            e.printStackTrace();
        }

        return row;
    }

    private String MixQuery(String qry,ArrayList<FieldItem> arrayList){
        try{
            //Check Fields Count
            if(arrayList==null){
                //if Has No Field return Base Query
                return qry;
            }else{
                if(arrayList.size()<=0)return qry;
                for(int i=0;i<arrayList.size();i++){
                    qry=qry.replaceAll(arrayList.get(i).name,arrayList.get(i).value);
                }
            }
            return qry;
        }catch (Exception e){
            return qry;
        }
    }

}
