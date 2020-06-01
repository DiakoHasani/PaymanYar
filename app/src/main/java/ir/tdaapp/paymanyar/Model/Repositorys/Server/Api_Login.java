package ir.tdaapp.paymanyar.Model.Repositorys.Server;

import android.content.Context;

import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.Single;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.li_volley.Volleys.PostJsonObjectVolley;
import ir.tdaapp.paymanyar.Model.Utilitys.Base_Api;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;

public class Api_Login extends Base_Api {

    PostJsonObjectVolley volley_login, volley_checkCode,volley_resendMessage;

    public Single<VM_Message> login(String phoneNumber, String androidId,String apiKey) {

        return Single.create(emitter -> {
            new Thread(() -> {

                JSONObject input = new JSONObject();

                try {

                    input.put("CellPhone", phoneNumber);
                    input.put("AndroidId", androidId);
                    input.put("Code", 0);
                    input.put("ApiKey", apiKey);

                } catch (Exception e) {
                }

                volley_login = new PostJsonObjectVolley(ApiUrl + "User/NewUser", input, resault -> {

                    if (resault.getResault() == ResaultCode.Success) {

                        JSONObject ob = resault.getObject();
                        VM_Message message = new VM_Message();

                        try {

                            message.setCode(ob.getInt("Code"));
                            message.setResult(ob.getBoolean("Result"));
                            message.setMessage(ob.getString("MessageText"));

                        } catch (Exception e) {
                        }

                        emitter.onSuccess(message);

                    } else {
                        if (resault.getResault()!=ResaultCode.TimeoutError&&resault.getResault()!=ResaultCode.NetworkError){
                            postError("Api_Login->login",resault.getMessage());
                        }
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }

                });

            }).start();
        });

    }

    //برای ارسال کد دریافت شده ازطریق پیامک
    public Single<VM_Message> checkCode(String phoneNumber, String androidId, int code,String api_Key) {

        return Single.create(emitter -> {

            new Thread(() -> {

                JSONObject input = new JSONObject();

                try {

                    input.put("cellPhone", phoneNumber);
                    input.put("androidId", androidId);
                    input.put("code", code);
                    input.put("apiKey", api_Key);

                    volley_checkCode = new PostJsonObjectVolley(ApiUrl + "User/CheckCode", input, resault -> {

                        if (resault.getResault() == ResaultCode.Success) {

                            JSONObject object=resault.getObject();
                            VM_Message message=new VM_Message();

                            try{

                                message.setMessage(object.getString("MessageText"));
                                message.setResult(object.getBoolean("Result"));
                                message.setCode(object.getInt("Code"));

                            }catch (Exception e){}

                            emitter.onSuccess(message);

                        } else {
                            if (resault.getResault()!=ResaultCode.TimeoutError&&resault.getResault()!=ResaultCode.NetworkError){
                                postError("Api_Login->checkCode",resault.getMessage());
                            }
                            emitter.onError(new IOException(resault.getResault().toString()));
                        }

                    });

                } catch (Exception e) {
                    postError("Api_Login->checkCode",e.toString());
                    emitter.onError(e);
                }

            }).start();

        });

    }

    //در اینجا درخواست برای ارسال مجدد پیامک ارسال می شود
    public Single<VM_Message> resendMessage(String phoneNumber){
        return Single.create(emitter -> {
           new Thread(()->{

               JSONObject input=new JSONObject();

               try{
                   input.put("CellPhone",phoneNumber);
                   input.put("AndroidId","");
                   input.put("Code",0);
                   input.put("ApiKey","");
               }catch (Exception e){}

               volley_resendMessage=new PostJsonObjectVolley(ApiUrl+"User/SendMessageAgain",input,resault -> {

                   if (resault.getResault()==ResaultCode.Success){

                       JSONObject obj=resault.getObject();
                       VM_Message message=new VM_Message();

                       try{

                           message.setCode(obj.getInt("Code"));
                           message.setResult(obj.getBoolean("Result"));
                           message.setMessage(obj.getString("MessageText"));

                       }catch (Exception e){ }

                       emitter.onSuccess(message);

                   }else{
                       if (resault.getResault()!=ResaultCode.TimeoutError&&resault.getResault()!=ResaultCode.NetworkError){
                           postError("Api_Login->resendMessage",resault.getMessage());
                       }
                       emitter.onError(new IOException(resault.getResault().toString()));
                   }

               });

           }).start();
        });
    }

    public void cancel(String tag, Context context) {

        cancelBase(tag,context);

        if (volley_login != null) {
            volley_login.Cancel(tag, context);
        }

        if (volley_checkCode != null) {
            volley_checkCode.Cancel(tag, context);
        }

        if (volley_resendMessage != null) {
            volley_resendMessage.Cancel(tag, context);
        }
    }

}
