package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Estimate;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Major;
import ir.tdaapp.paymanyar.R;

//مربوط به جدول برآورد
public class Tbl_Estimate {

    DBAdapter db;
    Context context;

    public Tbl_Estimate(Context context) {
        this.context = context;
        db = DBAdapter.getInstance(context);
    }

    //در اینجا داده های برآورد از پاس داده می شوند
    public Single<List<VM_Estimate>> getFromEstimates() {

        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    List<VM_Estimate> estimates = new ArrayList<>();
                    estimates.add(new VM_Estimate(0, context.getString(R.string.FromEstimate)));

                    Cursor q = db.ExecuteQ("select * from TblPrices");

                    for (int i = 0; i < q.getCount(); i++) {

                        try{

                            VM_Estimate estimate = new VM_Estimate();

                            estimate.setId(q.getInt(q.getColumnIndex("Id")));
                            estimate.setTitle(context.getString(R.string.from) + " " + q.getString(q.getColumnIndex("Price")));

                            if (q.getInt(q.getColumnIndex("Kind")) == 0)
                                estimates.add(estimate);

                        }catch (Exception e){}

                        q.moveToNext();
                    }

                    q.close();
                    emitter.onSuccess(estimates);

                } catch (Exception e) {
                    emitter.onError(e);
                }

            }).run();

        });

    }

    //در اینجا داده های برآورد تا پاس داده می شوند
    public Single<List<VM_Estimate>> getUntilEstimates(Context context) {

        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    List<VM_Estimate> estimates = new ArrayList<>();
                    estimates.add(new VM_Estimate(0, context.getString(R.string.UntilEstimate)));

                    Cursor q = db.ExecuteQ("select * from TblPrices");

                    for (int i = 0; i < q.getCount(); i++) {

                        try{

                            VM_Estimate estimate = new VM_Estimate();

                            estimate.setId(q.getInt(q.getColumnIndex("Id")));
                            estimate.setTitle(context.getString(R.string.until) + " " + q.getString(q.getColumnIndex("Price")));

                            if (q.getInt(q.getColumnIndex("Kind")) == 1)
                                estimates.add(estimate);

                        }catch (Exception e){}

                        q.moveToNext();
                    }

                    q.close();
                    emitter.onSuccess(estimates);

                } catch (Exception e) {
                    emitter.onError(e);
                }

            }).run();

        });

    }

}
