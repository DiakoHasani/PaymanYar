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

                    emitter.onSuccess(charges);

                } catch (Exception e) {
                    emitter.onError(e);
                }

            }).start();

        });

    }
}
