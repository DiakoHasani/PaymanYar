package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ir.tdaapp.paymanyar.Model.ViewModels.VM_MaterialSpinner;
import ir.tdaapp.paymanyar.R;

/**
 * مربوط به جدول مصالح
 **/
public class Tbl_Material {
    DBAdapter db;
    Context context;

    public Tbl_Material(Context context) {
        this.context = context;
        db = DBAdapter.getInstance(context);
    }

    /**
     * در اینجا لیست مصالح برگشت داده می شود
     **/
    public List<VM_MaterialSpinner> getMaterials() {
        List<VM_MaterialSpinner> vals = new ArrayList<>();

        vals.add(new VM_MaterialSpinner(0, context.getString(R.string.Material)));

        Cursor q = db.ExecuteQ("select * from TblMaterials");

        for (int i = 0; i < q.getCount(); i++) {

            VM_MaterialSpinner materialSpinner = new VM_MaterialSpinner();

            try {
                materialSpinner.setId(q.getInt(q.getColumnIndex("Id")));
                materialSpinner.setTitle(q.getString(q.getColumnIndex("Title")));
            } catch (Exception e) {
            } finally {
                vals.add(materialSpinner);
            }

            q.moveToNext();
        }

        return vals;
    }

    public int getPositionById(int id) {
        List<VM_MaterialSpinner> vals = getMaterials();
        int position = 0;

        for (int i = 0; i < vals.size(); i++) {
            try {
                if (vals.get(i).getId() == id) {
                    position = i;
                    break;
                }
            } catch (Exception e) {
            }
        }

        return position;
    }
}
