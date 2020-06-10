package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Major;
import ir.tdaapp.paymanyar.R;

public class Tbl_Major {

    DBAdapter db;
    Context context;

    public Tbl_Major(Context context) {
        this.context = context;
        db = DBAdapter.getInstance(context);
    }

    //در اینجا لیست رشته های تحصیلی برگشت داده می شوند
    public Single<List<VM_Major>> getMojors() {

        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    List<VM_Major> majors = new ArrayList<>();

                    majors.add(new VM_Major(0, context.getResources().getString(R.string.SelectedOneMajor)));

                    Cursor q = db.ExecuteQ("select * from TblFields");

                    for (int i = 0; i < q.getCount(); i++) {

                        try {

                            VM_Major major=new VM_Major();
                            major.setId(q.getInt(q.getColumnIndex("Id")));
                            major.setTitle(q.getString(q.getColumnIndex("Title")));

                            majors.add(major);

                        }catch (Exception e){}

                        q.moveToNext();
                    }

                    q.close();
                    emitter.onSuccess(majors);

                } catch (Exception e) {
                    emitter.onError(e);
                }

            }).run();

        });

    }

}
