package ir.tdaapp.paymanyar.Model.Repositorys.Server;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.li_volley.Volleys.GetJsonArrayVolley;
import ir.tdaapp.paymanyar.Model.Utilitys.Base_Api;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Charge;
import ir.tdaapp.paymanyar.R;

public class Api_Charge extends Base_Api {

    GetJsonArrayVolley volley_getCharges;

    //در اینجا لیست شارژها از سرور گرفته می شوند
    public Single<List<VM_Charge>> getCharges() {

        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    volley_getCharges = new GetJsonArrayVolley(ApiUrl + "UserBase/GetCharge", resault -> {

                        if (resault.getResault() == ResaultCode.Success) {

                            List<VM_Charge> charges = new ArrayList<>();
                            JSONArray array = resault.getJsonArray();

                            for (int i = 0; i < array.length(); i++) {

                                VM_Charge charge=new VM_Charge();

                                try {

                                    charge.setId(array.getJSONObject(i).getInt("Id"));
                                    charge.setTitle(array.getJSONObject(i).getString("Title"));
                                    charge.setSubTitle(array.getJSONObject(i).getString("Details"));
                                    charge.setSpecial(array.getJSONObject(i).getBoolean("IsSpecial"));
                                    charge.setTotalHour(array.getJSONObject(i).getInt("TotalHour"));

                                    charges.add(charge);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            emitter.onSuccess(charges);

                        } else {
                            emitter.onError(new IOException(resault.getResault().toString()));
                        }

                    });

                } catch (Exception e) {
                    emitter.onError(e);
                }

            }).start();

        });

    }

    public void Cancel(Context context, String TAG) {
        if (volley_getCharges != null) {
            volley_getCharges.Cancel(TAG, context);
        }
    }
}
