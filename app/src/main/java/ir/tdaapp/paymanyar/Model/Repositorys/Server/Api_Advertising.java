package ir.tdaapp.paymanyar.Model.Repositorys.Server;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ir.tdaapp.li_utility.Codes.Replace;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.li_volley.Volleys.GetJsonArrayVolley;
import ir.tdaapp.paymanyar.Model.Utilitys.Base_Api;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Charge;

/**
 * مربوط به تبلیغات
 **/
public class Api_Advertising extends Base_Api {

    GetJsonArrayVolley volley_getAds;

    /**
     * در اینجا لیست تبلیغات گرفته می شود
     **/
    public Single<List<VM_Charge>> getAds() {
        return Single.create(emitter -> {
            volley_getAds = new GetJsonArrayVolley(ApiUrl + "AdvertisingCost/GetAdvertisingCos", resault -> {
                if (resault.getResault() == ResaultCode.Success) {

                    List<VM_Charge> ads = new ArrayList<>();
                    JSONArray array = resault.getJsonArray();

                    for (int i = 0; i < array.length(); i++) {

                        VM_Charge ad = new VM_Charge();

                        try {

                            ad.setId(array.getJSONObject(i).getInt("Id"));
                            ad.setTitle(Replace.Number_en_To_fa(array.getJSONObject(i).getString("Title")));
                            ad.setSubTitle(Replace.Number_en_To_fa(array.getJSONObject(i).getString("Details")));
                            ad.setSpecial(array.getJSONObject(i).getBoolean("IsSpecial"));
                            ad.setTotalHour(array.getJSONObject(i).getInt("TotalHour"));

                            ads.add(ad);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    emitter.onSuccess(ads);

                } else {
                    if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                        postError("Api_Charge->getInventory", resault.getMessage());
                    }
                    emitter.onError(new IOException(resault.getResault().toString()));
                }
            });
        });
    }

    public void Cancel(Context context, String TAG) {

        cancelBase(TAG, context);

        if (volley_getAds != null) {
            volley_getAds.Cancel(TAG, context);
        }
    }
}
