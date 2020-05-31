package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Estimate;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Major;
import ir.tdaapp.paymanyar.R;

//مربوط به جدول برآورد
public class Tbl_Estimate {

    DBExcute db;
    Context context;

    public Tbl_Estimate(Context context) {
        this.context = context;
        db = DBExcute.getInstance(context);
    }

    //در اینجا داده های برآورد از پاس داده می شوند
    public Single<List<VM_Estimate>> getFromEstimates() {

        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    List<VM_Estimate> estimates = new ArrayList<>();
                    estimates.add(new VM_Estimate(0, context.getString(R.string.FromEstimate)));

                    db.Read("select * from TblPrices", new RecordHolder()).RecordFound(record -> {

                        VM_Estimate estimate = new VM_Estimate();
                        estimate.setId(Integer.valueOf(record.get(0).value));
                        estimate.setTitle(context.getString(R.string.from) + " " + record.get(1).value);

                        if (Integer.valueOf(record.get(4).value)==0){
                            estimates.add(estimate);
                        }

                    }, () -> {
                    }, () -> {
                        emitter.onSuccess(estimates);
                    }, 5);

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

                    db.Read("select * from TblPrices", new RecordHolder()).RecordFound(record -> {

                        if (Integer.valueOf(record.get(0).value) != 17) {
                            VM_Estimate estimate = new VM_Estimate();
                            estimate.setId(Integer.valueOf(record.get(0).value));
                            estimate.setTitle(context.getString(R.string.until) + " " + record.get(1).value);
                            if (Integer.valueOf(record.get(4).value)==1){
                                estimates.add(estimate);
                            }
                        }

                    }, () -> {

                    }, () -> {
                        emitter.onSuccess(estimates);
                    }, 5);

                } catch (Exception e) {
                    emitter.onError(e);
                }

            }).run();

        });

    }

}
