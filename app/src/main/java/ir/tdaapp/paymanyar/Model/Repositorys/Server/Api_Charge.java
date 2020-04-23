package ir.tdaapp.paymanyar.Model.Repositorys.Server;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ir.tdaapp.paymanyar.Model.Utilitys.Base_Api;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Charge;
import ir.tdaapp.paymanyar.R;

public class Api_Charge extends Base_Api {

    //در اینجا لیست شارژها از سرور گرفته می شوند
    public Single<List<VM_Charge>> getCharges(Context context) {

        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    List<VM_Charge> charges = new ArrayList<>();

                    for (int i = 1; i < 6; i++) {

                        VM_Charge charge = new VM_Charge();
                        charge.setId(i);
                        charge.setTitle(i * 5000 + context.getString(R.string.Toman));
                        charge.setSubTitle("اطلاع رسانی مناقصات به مدت شش ماه");

                        charges.add(charge);
                    }

                    emitter.onSuccess(charges);

                } catch (Exception e) {
                    emitter.onError(e);
                }

            }).run();

        });

    }
}
