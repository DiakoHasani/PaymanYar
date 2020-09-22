package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ir.tdaapp.paymanyar.Model.ViewModels.VM_ProvincesAndCities;
import ir.tdaapp.paymanyar.R;

/**
 * مربوط به جدول استان و شهرها
 **/
public class Tbl_ProvincesAndCities {

    DBAdapter db;
    Context context;

    public Tbl_ProvincesAndCities(Context context) {
        this.context = context;
        db = DBAdapter.getInstance(context);
    }

    /**
     * در اینجا لیست استان یا شهرها پاس داده می شود
     **/
    public List<VM_ProvincesAndCities> getProvincesOrCities(int parentId) {

        List<VM_ProvincesAndCities> provincesAndCities = new ArrayList<>();

        if (parentId == 0) {
            VM_ProvincesAndCities province = new VM_ProvincesAndCities(0, context.getString(R.string.Province), 0);
            provincesAndCities.add(province);
        } else {
            VM_ProvincesAndCities city = new VM_ProvincesAndCities(0, context.getString(R.string.City), 0);
            provincesAndCities.add(city);
        }

        Cursor q = db.ExecuteQ("select * from ProvincesAndCities where ParentId=" + parentId);

        for (int i = 0; i < q.getCount(); i++) {

            try {

                VM_ProvincesAndCities province = new VM_ProvincesAndCities();
                province.setId(q.getInt(q.getColumnIndex("Id")));
                province.setTitle(q.getString(q.getColumnIndex("Title")));
                province.setParentId(q.getInt(q.getColumnIndex("ParentId")));

                provincesAndCities.add(province);

                q.moveToNext();

            } catch (Exception e) {
            }
        }
        q.close();

        return provincesAndCities;
    }
}
