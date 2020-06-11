package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_City;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Major;
import ir.tdaapp.paymanyar.R;

public class Tbl_City {

    Context context;
    DBAdapter db;

    public Tbl_City(Context context) {
        this.context = context;
        db = DBAdapter.getInstance(context);
    }

    //در اینجا لیست شهرها برگشت داده می شوند
    public Single<List<VM_City>> getCitys() {
        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    List<VM_City> cities = new ArrayList<>();
                    cities.add(new VM_City(0, context.getResources().getString(R.string.SelectedOneCity)));

                    Cursor q = db.ExecuteQ("select * from TblStates");

                    for (int i = 0; i < q.getCount(); i++) {
                        VM_City city = new VM_City();
                        city.setId(q.getInt(q.getColumnIndex("Id")));
                        city.setTitle(q.getString(q.getColumnIndex("Title")));
                        cities.add(city);
                        q.moveToNext();
                    }

                    q.close();
                    emitter.onSuccess(cities);

                } catch (Exception e) {
                    emitter.onError(e);
                }

            }).run();

        });
    }

    //در اینجا تایتل شهر بر اساس آیدی برگشت داده می شود
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
