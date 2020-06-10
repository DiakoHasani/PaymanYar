package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import java.util.ArrayList;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_EshtalItem;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_IPE_SemiWideColumns;

public class Tbl_Eshtal {

    DBAdapter dbAdapter;
    Context context;

    public Tbl_Eshtal(Context context) {
        this.context = context;
        dbAdapter = DBAdapter.getInstance(context);
    }

    public VM_IPE_SemiWideColumns getColumns(String eshtal_id) {

        VM_IPE_SemiWideColumns v = new VM_IPE_SemiWideColumns();

        try {

            Cursor q = dbAdapter.ExecuteQ("select * from tbl_eshtal where id=" + eshtal_id);

            v.setColNumber(q.getString(q.getColumnIndex("columnNumber")));
            if (q.getString(q.getColumnIndex("LastID")).length() > 0)
                v.setLastID(q.getString(q.getColumnIndex("LastID")));

            q.close();

        } catch (Exception e) {
        }

        return v;
    }

    public ArrayList<VM_EshtalItem> getEshtalItems(String eshtal_id, String colNumber, String lastID) {

        ArrayList<VM_EshtalItem> vals = new ArrayList<>();

        try {

            Cursor q = dbAdapter.ExecuteQ("select  tbl_eshtal_cols_related.id,tbl_eshtal_cols.name,tbl_eshtal_cols.unit,tbl_eshtal_values.col_id,tbl_eshtal_values.value,tbl_eshtal_values.parent,tbl_eshtal_values.id from tbl_eshtal_values inner join tbl_eshtal_cols_related on tbl_eshtal_values.col_id=tbl_eshtal_cols_related.id inner join tbl_eshtal_cols on tbl_eshtal_cols_related.cols_id=tbl_eshtal_cols.id where tbl_eshtal_cols_related.eshtal_id=" + eshtal_id + " and (tbl_eshtal_values.id=" + lastID + " OR tbl_eshtal_values.parent=" + lastID + ");");

            for (int i = 0; i < q.getCount(); i++) {
                try {

                    VM_EshtalItem item = new VM_EshtalItem();

                    item.setColumn_id(q.getString(0));
                    item.setParent(q.getString(q.getColumnIndex("parent")));
                    item.setId(q.getString(6));
                    item.setTitle(q.getString(q.getColumnIndex("name")));
                    item.setValue(q.getString(q.getColumnIndex("value")));
                    item.setUnit(q.getString(q.getColumnIndex("unit")));

                    vals.add(item);

                } catch (Exception e) {
                    Log.e("getEshtalItems", e.toString());
                }
                q.moveToNext();
            }
            q.close();
        } catch (Exception e) {
        }

        return vals;
    }

    public ArrayList<VM_EshtalItem> getEshtalFilter(String column_id) {
        ArrayList<VM_EshtalItem> arrayList = new ArrayList<>();

        try {

            Cursor q = dbAdapter.ExecuteQ("select  tbl_eshtal_cols_related.id,tbl_eshtal_cols.name,tbl_eshtal_cols.unit,tbl_eshtal_values.col_id,tbl_eshtal_values.value,tbl_eshtal_values.parent,tbl_eshtal_values.id from tbl_eshtal_values inner join tbl_eshtal_cols_related on tbl_eshtal_values.col_id=tbl_eshtal_cols_related.id inner join tbl_eshtal_cols on tbl_eshtal_cols_related.cols_id=tbl_eshtal_cols.id where tbl_eshtal_values.col_id=" + column_id);

            for (int i = 0; i < q.getCount(); i++) {

                try {

                    VM_EshtalItem item = new VM_EshtalItem();
                    item.setId(q.getString(6));
                    item.setColumn_id(q.getString(0));
                    item.setParent(q.getString(5));
                    item.setTitle(q.getString(1));
                    item.setValue(q.getString(4));
                    item.setUnit(q.getString(2));
                    arrayList.add(item);

                } catch (Exception e) {
                    Log.e("getEshtalFilter",e.toString());
                }

                q.moveToNext();
            }

        } catch (Exception e) {
        }
        return arrayList;
    }

    public void SaveChosed(String lastid, String eshtal_id) {
        dbAdapter.ExecuteQ("update tbl_eshtal set lastID=" + lastid + " where id=" + eshtal_id);
    }

}
