package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import java.util.ArrayList;

public class RecordHolder {

    private ArrayList<FieldItem> arrayList;

    public RecordHolder(FieldItem... params){
        this.arrayList=new ArrayList<>();
        for (int i = 0; i < params.length; i++) {
            arrayList.add(params[i]);
        }
    }

    public ArrayList<FieldItem> GetRecords(){
        return this.arrayList;
    }

}
