package ir.tdaapp.paymanyar.Model.Repositorys.Server;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.li_volley.Volleys.GetJsonArrayVolley;
import ir.tdaapp.li_volley.Volleys.PostJsonObjectVolley;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_SMS;
import ir.tdaapp.paymanyar.Model.Utilitys.Base_Api;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_SMS;

public class Api_SMS extends Base_Api {

    GetJsonArrayVolley volley_getSMS;
    PostJsonObjectVolley volley_setArchiveSMS;

    //در اینجا لیست پیام ها گرفته می شوند
    public Single<List<VM_SMS>> getSMS(int userId, Tbl_SMS tbl_sms, boolean showAllSMS) {

        return Single.create(emitter -> {

            new Thread(() -> {

                volley_getSMS = new GetJsonArrayVolley(ApiUrl + "Message/GetMessagesUser?UserId=" + userId, resault -> {

                    if (resault.getResault() == ResaultCode.Success) {

                        List<VM_SMS> smsList = new ArrayList<>();
                        JSONArray array = resault.getJsonArray();

                        for (int i = 0; i < array.length(); i++) {
                            VM_SMS sms = new VM_SMS();

                            try {

                                sms.setId(array.getJSONObject(i).getString("MsgId"));
                                sms.setText(array.getJSONObject(i).getString("Body"));
                                sms.setKird(array.getJSONObject(i).getBoolean("Kird"));

                                if (tbl_sms.hasSMS(array.getJSONObject(i).getString("MsgId"))) {
                                    sms.setFevorit(true);
                                } else {
                                    sms.setFevorit(false);
                                }

                                //اگر شرط زیر درست باشد فقط پیام های مورد علاقه نمایش داده می شود در غیر این صورت همه پیام ها نمایش داده می شوند
                                if (!showAllSMS) {

                                    if (sms.isFevorit()) {
                                        smsList.add(sms);
                                    }

                                } else {
                                    smsList.add(sms);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        emitter.onSuccess(smsList);

                    } else {
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }

                });

            }).start();

        });

    }

    //در اینجا پیام ها حذف می شوند
    public Single<VM_Message> setArchiveSMS(String smsId, int userId) {
        return Single.create(emitter -> {

            new Thread(() -> {

                JSONObject input = new JSONObject();

                try {
                    input.put("MessageId", smsId);
                    input.put("UserId", userId);
                } catch (Exception e) {
                }

                volley_setArchiveSMS = new PostJsonObjectVolley(ApiUrl + "Message/PostMessageArchive", input, resault -> {

                    if (resault.getResault() == ResaultCode.Success) {

                        VM_Message message = new VM_Message();
                        JSONObject object = resault.getObject();

                        try {
                            message.setCode(object.getInt("Code"));
                            message.setResult(object.getBoolean("Result"));
                            message.setMessage(object.getString("MessageText"));
                        } catch (Exception e) {
                        }

                        emitter.onSuccess(message);

                    } else {
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }

                });

            }).start();

        });
    }

    public void Cancel(String tag, Context context) {

        if (volley_getSMS != null) {
            volley_getSMS.Cancel(tag, context);
        }

        if (volley_setArchiveSMS != null) {
            volley_setArchiveSMS.Cancel(tag, context);
        }

    }

}
