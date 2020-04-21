package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Estimate;
import ir.tdaapp.paymanyar.R;

//مربوط به جدول برآورد
public class Tbl_Estimate {

    //در اینجا داده های برآورد از پاس داده می شوند
    public Single<List<VM_Estimate>> getFromEstimates(Context context) {

        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    List<VM_Estimate> estimates = new ArrayList<>();
                    estimates.add(new VM_Estimate(0, context.getString(R.string.FromEstimate)));
                    estimates.add(new VM_Estimate(1, context.getString(R.string.from) + " " + "120 میلیارد"));
                    estimates.add(new VM_Estimate(2, context.getString(R.string.from) + " " + "150 میلیارد"));
                    estimates.add(new VM_Estimate(3, context.getString(R.string.from) + " " + "190 میلیارد"));
                    estimates.add(new VM_Estimate(4, context.getString(R.string.from) + " " + "170 میلیارد"));
                    estimates.add(new VM_Estimate(5, context.getString(R.string.from) + " " + "200 میلیارد"));

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
                    estimates.add(new VM_Estimate(1, context.getString(R.string.until) + " " + "120 میلیارد"));
                    estimates.add(new VM_Estimate(2, context.getString(R.string.until) + " " + "150 میلیارد"));
                    estimates.add(new VM_Estimate(3, context.getString(R.string.until) + " " + "190 میلیارد"));
                    estimates.add(new VM_Estimate(4, context.getString(R.string.until) + " " + "170 میلیارد"));
                    estimates.add(new VM_Estimate(5, context.getString(R.string.until) + " " + "200 میلیارد"));

                    emitter.onSuccess(estimates);

                } catch (Exception e) {
                    emitter.onError(e);
                }

            }).run();

        });

    }

}
