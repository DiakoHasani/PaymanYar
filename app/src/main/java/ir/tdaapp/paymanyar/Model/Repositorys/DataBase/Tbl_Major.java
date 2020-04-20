package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Major;
import ir.tdaapp.paymanyar.R;

public class Tbl_Major {

    //در اینجا لیست رشته های تحصیلی برگشت داده می شوند
    public Single<List<VM_Major>> getMojors(Context context) {

        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    List<VM_Major> majors = new ArrayList<>();

                    majors.add(new VM_Major(0, context.getResources().getString(R.string.SelectedOneMajor)));
                    majors.add(new VM_Major(1, "کامپیوتر"));
                    majors.add(new VM_Major(2, "برق"));
                    majors.add(new VM_Major(3, "عمران"));
                    majors.add(new VM_Major(4, "معماری"));
                    majors.add(new VM_Major(5, "تجربی"));

                    emitter.onSuccess(majors);

                } catch (Exception e) {
                    emitter.onError(e);
                }

            }).run();

        });

    }

}
