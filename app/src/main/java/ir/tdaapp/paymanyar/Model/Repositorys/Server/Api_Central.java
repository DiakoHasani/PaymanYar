package ir.tdaapp.paymanyar.Model.Repositorys.Server;

import android.content.Context;

import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.Single;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.li_volley.Volleys.PostJsonObjectVolley;
import ir.tdaapp.paymanyar.Model.Utilitys.Base_Api;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;

public class Api_Central extends Base_Api {

    PostJsonObjectVolley volley_PostError;

    //در برنامه خطای رخ دهد در اینجا خطای آن را به سرور ارسال می کند
    public Single<VM_Message> postError(String name, String text) {
        String versionAndroid = android.os.Build.VERSION.RELEASE;

        return Single.create(emitter -> {
            new Thread(() -> {

                try {

                    JSONObject input = new JSONObject();

                    try {

                        input.put("Name", name);
                        input.put("Text", text);
                        input.put("AndroidVersion", versionAndroid);

                    } catch (Exception e) {
                    }

                    volley_PostError = new PostJsonObjectVolley(ApiUrl + "Error/AddError", input, resault -> {
                        if (resault.getResault()== ResaultCode.Success){

                            VM_Message message=new VM_Message();
                            JSONObject object=resault.getObject();

                            try{
                                message.setMessage(object.getString("MessageText"));
                                message.setCode(object.getInt("Code"));
                                message.setResult(object.getBoolean("Result"));
                            }catch (Exception e){}

                            emitter.onSuccess(message);

                        }else{
                            emitter.onError(new IOException(resault.getResault().toString()));
                        }
                    });

                } catch (Exception e) {
                    emitter.onError(e);
                }

            }).start();
        });
    }

    public void cancel(String tag, Context context) {
        if (volley_PostError != null) {
            volley_PostError.Cancel(tag, context);
        }
    }

}
