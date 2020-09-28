package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ir.tdaapp.paymanyar.Model.ViewModels.VM_ProvincesAndCities;
import ir.tdaapp.paymanyar.R;

/**
 * مربوط به جدول استان و شهرها
 * **/
public class Tbl_States {
    Context context;
    DBAdapter db;

    public Tbl_States(Context context) {
        this.context = context;
        db = DBAdapter.getInstance(context);
    }

    /**
     * در اینجا لیست شهر یا استان ها را پاس می دهد
     * **/
    public List<VM_ProvincesAndCities> getProvincesOrCities(int parentId){

        List<VM_ProvincesAndCities> provincesAndCities = new ArrayList<>();

        if (parentId == 0) {
            VM_ProvincesAndCities province = new VM_ProvincesAndCities(0, context.getString(R.string.Province), 0);
            provincesAndCities.add(province);
        } else {
            VM_ProvincesAndCities city = new VM_ProvincesAndCities(0, context.getString(R.string.City), 0);
            provincesAndCities.add(city);
        }

        Cursor q = db.ExecuteQ("select * from TblStates where ParentId=" + parentId);

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

    /**
     * در اینجا نام شهر یا استان بر اساس آیدی پاس می دهد
     * **/
    public String getTitleById(int id) {
        String name = "";

        try {
            Cursor q = db.ExecuteQ("select Title from TblStates where Id=" + id);
            name = q.getString(0);
        } catch (Exception e) {
        }

        return name;
    }
}
