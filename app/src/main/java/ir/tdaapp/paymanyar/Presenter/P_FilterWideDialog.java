package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import java.util.ArrayList;

import ir.tdaapp.paymanyar.Model.Adapters.FilterAdapter;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.DBCursor;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.DBExcute;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.FieldItem;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.RecordHolder;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.database;
import ir.tdaapp.paymanyar.Model.Services.S_FilterDialog;
import ir.tdaapp.paymanyar.Model.Services.S_IPE_SemiWideFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_EshtalItem;

public class P_FilterWideDialog {

    private Context context;
    private S_FilterDialog s_FilterDialog;
    private DBExcute dbExcute;
    private VM_EshtalItem item;

    public P_FilterWideDialog(Context context, S_FilterDialog s_ipe_semiWideFragment, VM_EshtalItem item) {
        this.context = context;
        this.s_FilterDialog = s_ipe_semiWideFragment;
        this.item=item;
        this.dbExcute=DBExcute.getInstance(this.context);
        this.dbExcute.Open();

        ArrayList<VM_EshtalItem> arrayList=new ArrayList<>();
        //Get All Column Values
        dbExcute.Read(database.QRY_Eshtal_Column_Values,new RecordHolder(new FieldItem("#1#",this.item.getColumn_id()))).RecordFound(new DBCursor.ListCounter() {
            @Override
            public void onEachrecord(ArrayList<FieldItem> record) {
                VM_EshtalItem item = new VM_EshtalItem();
                item.setId(record.get(6).value);
                item.setColumn_id(record.get(0).value);
                item.setParent(record.get(5).value);
                item.setTitle(record.get(1).value);
                item.setValue(record.get(4).value);
                item.setUnit(record.get(2).value);
                arrayList.add(item);
            }
        }, null, new DBCursor.FetchFinishListener() {
            @Override
            public void onFinish() {
                SetToAdapter(arrayList);
            }
        }, 7);

    }

    private void SetToAdapter(ArrayList<VM_EshtalItem> arrayList){
        FilterAdapter adapter=new FilterAdapter(this.context);
        this.s_FilterDialog.OnRowsFind(adapter);

        adapter.setListener(new FilterAdapter.FilterAdapterListener() {
            @Override
            public void onChanged(VM_EshtalItem itemChoosed) {
                s_FilterDialog.OnItemChoosed(itemChoosed);
            }
        });

        for(int i=0;i<arrayList.size();i++){
            adapter.add(arrayList.get(i));
            if(this.item.getId().compareToIgnoreCase(arrayList.get(i).getId())==0){
                adapter.SetIndex(i);
            }
        }
        adapter.notifyDataSetChanged();

    }

    public void SaveChoosed(String lastid,String eshtal_id){

        //We Save Choosed ID in Database for Next Usage
        dbExcute.Execute(database.QRY_Eshtal_Update_LastID,new RecordHolder(new FieldItem("#1#",lastid),new FieldItem("#2#",eshtal_id)));
    }

}
