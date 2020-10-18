package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ir.tdaapp.paymanyar.Model.ViewModels.VM_MachinerySpinner;
import ir.tdaapp.paymanyar.R;

/**
 * مربوط به جدول دسته ماشین آلات
 **/
public class Tbl_Machineries {
    DBAdapter db;
    Context context;

    public Tbl_Machineries(Context context) {
        this.context = context;
        db = DBAdapter.getInstance(context);
    }

    /**
     * در اینجا لیست ماشین آلات گرفته می شود
     **/
    public List<VM_MachinerySpinner> getMachineries() {

        List<VM_MachinerySpinner> vals = new ArrayList<>();

        try {

            VM_MachinerySpinner first = new VM_MachinerySpinner();
            first.setId(0);
            first.setTitle(context.getString(R.string.Machinery2));

            vals.add(first);

            Cursor q = db.ExecuteQ("select * from TblMachineries");

            for (int i = 0; i < q.getCount(); i++) {
                try {

                    VM_MachinerySpinner model = new VM_MachinerySpinner();

                    model.setId(q.getInt(q.getColumnIndex("Id")));
                    model.setTitle(q.getString(q.getColumnIndex("Title")));

                    vals.add(model);
                } catch (Exception e) {
                }
                q.moveToNext();
            }
            q.close();

        } catch (Exception e) {
        }

        return vals;
    }

    /**
     * در اینجا تایتل براساس آیدی برگشت داده می شود
     * **/
    public String getTitleById(int id) {
        String title = "";
        List<VM_MachinerySpinner> vals = getMachineries();

        for (int i = 0; i < vals.size(); i++) {
            if (vals.get(i).getId() == id) {
                title = vals.get(i).getTitle();
                break;
            }
        }

        return title;
    }
}
