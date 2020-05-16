package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Major;
import ir.tdaapp.paymanyar.R;

public class Tbl_Major {

    DBExcute db;
    Context context;

    public Tbl_Major(Context context) {
        this.context=context;
        db=DBExcute.getInstance(context);
    }

    //در اینجا لیست رشته های تحصیلی برگشت داده می شوند
    public Single<List<VM_Major>> getMojors() {

        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    List<VM_Major> majors = new ArrayList<>();

                    majors.add(new VM_Major(0, context.getResources().getString(R.string.SelectedOneMajor)));

                    //db.Execute("",new RecordHolder());

                    db.Read("select * from TblFields",new RecordHolder()).RecordFound(record -> {
                        majors.add(new VM_Major(Integer.valueOf(record.get(0).value),record.get(1).value));
                    },null,() -> {
                        emitter.onSuccess(majors);
                    },2);

                } catch (Exception e) {
                    emitter.onError(e);
                }

            }).run();

        });

    }

}
