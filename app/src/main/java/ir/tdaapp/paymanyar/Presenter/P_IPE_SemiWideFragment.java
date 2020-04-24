package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import java.util.ArrayList;

import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.DBCursor;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.DBExcute;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.FieldItem;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.RecordHolder;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.database;
import ir.tdaapp.paymanyar.Model.Services.S_IPE_SemiWideFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_EshtalItem;

public class P_IPE_SemiWideFragment {

    private Context context;
    private S_IPE_SemiWideFragment s_ipe_semiWideFragment;
    private DBExcute dbExcute;
    private String eshtal_id,colNumber,lastID;

    public P_IPE_SemiWideFragment(Context context, S_IPE_SemiWideFragment s_ipe_semiWideFragment,String Current_eshtaal_id) {
        this.context = context;
        this.s_ipe_semiWideFragment = s_ipe_semiWideFragment;
        this.eshtal_id=Current_eshtaal_id;
        this.dbExcute=DBExcute.getInstance(this.context);

    }

    public void GetColumns(){
        this.dbExcute.Open();
        //Get Last Choosed Value + Get Columns Count of that table
        dbExcute.Read(database.QRY_Eshtal_INFO,new RecordHolder(new FieldItem("#1#",eshtal_id))).RecordFound(new DBCursor.ListCounter() {
            @Override
            public void onEachrecord(ArrayList<FieldItem> record) {
                colNumber=record.get(2).value;
                if(record.get(3).value.length()>0)lastID=record.get(3).value;
            }
        }, null, new DBCursor.FetchFinishListener() {
            @Override
            public void onFinish() {
                GetItems();
            }
        }, 4);
    }

    private void GetItems(){
        ArrayList<VM_EshtalItem> arrayList=new ArrayList<>();

        //Read Data From Database Base on Our Eshtal ID : eshtal_id
        dbExcute.Read(database.QRY_Eshtal_Item,new RecordHolder(new FieldItem("#1#",eshtal_id),new FieldItem("#2#",lastID),new FieldItem("#3#",lastID))).RecordFound(new DBCursor.ListCounter() {
            @Override
            public void onEachrecord(ArrayList<FieldItem> record) {
                VM_EshtalItem item = new VM_EshtalItem();
                item.setColumn_id(record.get(0).value);
                item.setParent(record.get(5).value);
                item.setId(record.get(6).value);
                item.setTitle(record.get(1).value);
                item.setValue(record.get(4).value);
                item.setUnit(record.get(2).value);
                arrayList.add(item);
            }
        }, null, new DBCursor.FetchFinishListener() {
            @Override
            public void onFinish() {
                s_ipe_semiWideFragment.SetItems(arrayList);
            }
        }, 7);
    }


    public void start(){
        s_ipe_semiWideFragment.OnStart();
    }
}
