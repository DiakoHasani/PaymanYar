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
import ir.tdaapp.li_volley.Volleys.GetJsonObjectVolley;
import ir.tdaapp.li_volley.Volleys.GetStringVolley;
import ir.tdaapp.paymanyar.Model.Utilitys.Base_Api;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Charge;
import ir.tdaapp.paymanyar.R;

public class Api_Charge extends Base_Api {

    GetJsonArrayVolley volley_getCharges;
    GetStringVolley volley_getInventory;

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

                                VM_Charge charge = new VM_Charge();

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
                            if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                                postError("Api_Charge->getCharges", resault.getMessage());
                            }
                            emitter.onError(new IOException(resault.getResault().toString()));
                        }

                    });

                } catch (Exception e) {
                    postError("Api_Charge->getCharges", e.toString());
                    emitter.onError(e);
                }

            }).start();

        });

    }

    //در اینجا موجودی کاربر گرفته می شود
    public Single<Integer> getInventory(int userId) {

        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    volley_getInventory = new GetStringVolley(ApiUrl + "UserBase/GetChargeUser?UserId=" + userId, resault -> {

                        if (resault.getResault() == ResaultCode.Success) {

                            try {
                                emitter.onSuccess(Integer.valueOf(resault.getRequest()));
                            } catch (Exception e) {
                                emitter.onError(e);
                            }

                        } else {
                            if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                                postError("Api_Charge->getInventory", resault.getMessage());
                            }
                            emitter.onError(new IOException(resault.getResault().toString()));
                        }

                    });

                } catch (Exception e) {
                    postError("Api_Charge->getInventory", e.toString());
                    emitter.onError(e);
                }

            }).start();

        });

    }

    public void Cancel(Context context, String TAG) {

        cancelBase(TAG, context);

        if (volley_getCharges != null) {
            volley_getCharges.Cancel(TAG, context);
        }

        if (volley_getInventory != null) {
            volley_getInventory.Cancel(TAG, context);
        }
    }
}
