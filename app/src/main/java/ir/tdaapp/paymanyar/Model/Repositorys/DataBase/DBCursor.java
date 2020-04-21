package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.database.Cursor;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class DBCursor{

    private Cursor row;

    public DBCursor(Cursor cursor){
        this.row=cursor;
    }

    public DBCursor(){

    }

    public boolean HasRecord(){
        boolean answer=false;

        try{
            if(this.row==null){
                answer=false;
            }else{
                if(this.row.getCount()>0){
                    answer=true;
                }
            }
        }catch (Exception e){}

//        dbExcute.Close();
        return answer;
    }

    public String GetField(int index){
        String answer="";
        if(this.row.getCount()<=0){
            answer="";
        }else {
            this.row.moveToFirst();
            answer=this.row.getString(index);
        }
//        dbExcute.Close();
        return answer;
    }

    public void RecordFound(ListCounter counter , @Nullable NoRecordListener noRecordListener, @Nullable FetchFinishListener fetchFinishListener, int ColNumbers){
        try {

            this.row.moveToFirst();

            for (int i = 0; i < this.row.getCount(); i++) {

                ArrayList<FieldItem> fieldItems=new ArrayList<>();
                for(int col_counter=0;col_counter<ColNumbers;col_counter++){

                    FieldItem item=new FieldItem();

                    item.name=this.row.getColumnName(col_counter);

                    item.value=this.row.getString(col_counter);

                    fieldItems.add(item);

                }

                if(counter!=null)counter.onEachrecord(fieldItems);

                this.row.moveToNext();
            }

            //If No Record Affect
            if(this.row.getCount()<=0){
                if(noRecordListener!=null)noRecordListener.NoRecord();
            }else{
                if(fetchFinishListener!=null)fetchFinishListener.onFinish();
            }

        }catch (Exception e){}

    }

    public interface ListCounter{
        public void onEachrecord(ArrayList<FieldItem> record);
    }

    public interface NoRecordListener{
        public void NoRecord();
    }

    public interface FetchFinishListener{
        public void onFinish();
    }
}

