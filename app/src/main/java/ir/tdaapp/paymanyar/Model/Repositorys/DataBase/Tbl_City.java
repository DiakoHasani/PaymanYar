package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_City;
import ir.tdaapp.paymanyar.R;

public class Tbl_City {

    //در اینجا لیست شهرها برگشت داده می شوند
    public Single<List<VM_City>> getCitys(Context context) {
        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    List<VM_City> cities = new ArrayList<>();
                    cities.add(new VM_City(0, context.getResources().getString(R.string.SelectedOneCity)));
                    cities.add(new VM_City(1, "کردستان"));
                    cities.add(new VM_City(2, "مازندران"));
                    cities.add(new VM_City(3, "تهران"));
                    cities.add(new VM_City(4, "فارس"));
                    cities.add(new VM_City(5, "بندر عباس"));

                    emitter.onSuccess(cities);

                } catch (Exception e) {
                    emitter.onError(e);
                }

            }).run();

        });
    }

}
