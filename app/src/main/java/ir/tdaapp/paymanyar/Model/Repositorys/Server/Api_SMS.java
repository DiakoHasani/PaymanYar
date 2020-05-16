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
import ir.tdaapp.paymanyar.Model.ViewModels.VM_SMS;

public class Api_SMS extends Base_Api {

    GetJsonArrayVolley volley_getSMS;

    //در اینجا لیست پیام ها گرفته می شوند
    public Single<List<VM_SMS>> getSMS(int userId){

        return Single.create(emitter -> {

            new Thread(()->{

                volley_getSMS=new GetJsonArrayVolley(ApiUrl+"Message/GetMessagesUser?UserId="+userId,resault -> {

                    if (resault.getResault()== ResaultCode.Success){

                        List<VM_SMS> smsList=new ArrayList<>();
                        JSONArray array=resault.getJsonArray();

                        for (int i=0;i<array.length();i++){
                            VM_SMS sms=new VM_SMS();

                            try {

                                sms.setId(array.getJSONObject(i).getString("MsgId"));
                                sms.setText(array.getJSONObject(i).getString("Body"));
                                sms.setKird(array.getJSONObject(i).getBoolean("Kird"));

                                smsList.add(sms);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        emitter.onSuccess(smsList);

                    }else{
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }

                });

            }).start();

        });

    }

    public void Cancel(String tag, Context context){

        if (volley_getSMS!=null){
            volley_getSMS.Cancel(tag,context);
        }

    }

}
