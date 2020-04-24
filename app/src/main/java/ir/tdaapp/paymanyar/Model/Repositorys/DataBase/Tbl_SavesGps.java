package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_SavesGps;

//مربوط به جدول نقشه های ذخیره شده می باشد
public class Tbl_SavesGps {

    //در اینجا لیست نقشه های ذخیره شده بازگشت داده می شود
    public Single<List<VM_SavesGps>> getSavesGps() {

        return Single.create(emitter -> {

            new Thread(() -> {
                try {

                    List<VM_SavesGps> vals = new ArrayList<>();

                    for (int i = 1; i < 10; i++) {

                        VM_SavesGps v = new VM_SavesGps();

//                        v.setId(i);
//                        v.setLength(65 * i);
//                        v.setWide(78 * i);

                        vals.add(v);
                    }
                    emitter.onSuccess(vals);

                } catch (Exception ex) {
                    emitter.onError(ex);
                }
            }).run();

        });

    }

}
