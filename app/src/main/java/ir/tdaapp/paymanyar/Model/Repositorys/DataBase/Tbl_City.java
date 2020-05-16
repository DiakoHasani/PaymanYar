package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_City;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Major;
import ir.tdaapp.paymanyar.R;

public class Tbl_City {

    Context context;
    DBExcute db;

    public Tbl_City(Context context) {
        this.context = context;
        db=DBExcute.getInstance(context);
    }

    //در اینجا لیست شهرها برگشت داده می شوند
    public Single<List<VM_City>> getCitys() {
        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    List<VM_City> cities = new ArrayList<>();
                    cities.add(new VM_City(0, context.getResources().getString(R.string.SelectedOneCity)));

                    db.Read("select * from TblStates",new RecordHolder()).RecordFound(record -> {

                        cities.add(new VM_City(Integer.valueOf(record.get(0).value),record.get(1).value));

                    },null,() -> {
                        emitter.onSuccess(cities);
                    },2);

                } catch (Exception e) {
                    emitter.onError(e);
                }

            }).run();

        });
    }

}
