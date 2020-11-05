package ir.tdaapp.paymanyar.Model.Repositorys.Server;

import android.content.Context;

import com.google.android.gms.common.api.Api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ir.tdaapp.li_utility.Codes.Replace;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.li_volley.Volleys.GetJsonArrayVolley;
import ir.tdaapp.li_volley.Volleys.GetJsonObjectVolley;
import ir.tdaapp.li_volley.Volleys.PostJsonObjectVolley;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_SMS;
import ir.tdaapp.paymanyar.Model.Utilitys.Base_Api;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailSMS;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_SMS;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Support;

public class Api_SMS extends Base_Api {

    GetJsonArrayVolley volley_getSMS, volley_getUsersSuport;
    PostJsonObjectVolley volley_setArchiveSMS, volley_postSMS;
    GetJsonObjectVolley volley_getDetailSMS;

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
                        if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                            postError("Api_SMS->getSMS", resault.getMessage());
                        }
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
                        if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                            postError("Api_SMS->setArchiveSMS", resault.getMessage());
                        }
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }

                });

            }).start();

        });
    }

    //در اینجا جزئیات پیامک گرفته می شود
    public Single<VM_DetailSMS> getDetailSMS(String smsId) {

        return Single.create(emitter -> {
            new Thread(() -> {

                volley_getDetailSMS = new GetJsonObjectVolley(ApiUrl + "Message/GetMessagesUserInfo?MsgId=" + smsId, resault -> {

                    if (resault.getResault() == ResaultCode.Success) {

                        JSONObject object = resault.getObject();
                        VM_DetailSMS detailSMS = new VM_DetailSMS();

                        try {

                            detailSMS.setMsgId(smsId);
                            detailSMS.setDescription(object.getString("Body"));
                            detailSMS.setDate(object.getString("DateSend"));

                        } catch (Exception e) {
                            detailSMS.setMsgId(smsId);
                            detailSMS.setDescription("");
                            detailSMS.setDate("");
                        }

                        emitter.onSuccess(detailSMS);

                    } else {
                        if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                            postError("Api_SMS->getDetailSMS", resault.getMessage());
                        }
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }

                });

            }).start();
        });

    }

    //در اینجا پیامک ارسال می شود
    public Single<VM_Message> postSMS(String message, int userId) {

        return Single.create(emitter -> {

            new Thread(() -> {

                JSONObject input = new JSONObject();

                try {
                    input.put("UserId", userId);
                    input.put("TextMessage", message);
                } catch (Exception e) {
                }

                volley_postSMS = new PostJsonObjectVolley(ApiUrl + "Message/PostSendMessage", input, resault -> {

                    if (resault.getResault() == ResaultCode.Success) {

                        VM_Message msg = new VM_Message();
                        JSONObject obj = resault.getObject();

                        try {

                            msg.setMessage(obj.getString("MessageText"));
                            msg.setResult(obj.getBoolean("Result"));
                            msg.setCode(obj.getInt("Code"));

                        } catch (Exception e) {
                        }

                        emitter.onSuccess(msg);

                    } else {
                        if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                            postError("Api_SMS->postSMS", resault.getMessage());
                        }
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }

                });

            }).start();

        });

    }

    //در اینجا افراد مربوط به بخش پشتیبانی گرفته می شوند
    public Single<List<VM_Support>> getUsersSupport() {
        return Single.create(emitter -> {
            new Thread(() -> {

                try {

                    volley_getUsersSuport = new GetJsonArrayVolley(ApiUrl + "ContactUs/GetSupporter", resault -> {

                        if (resault.getResault() == ResaultCode.Success) {

                            List<VM_Support> supports = new ArrayList<>();
                            JSONArray array = resault.getJsonArray();

                            for (int i = 0; i < array.length(); i++) {

                                try {

                                    VM_Support support = new VM_Support();
                                    JSONObject object = array.getJSONObject(i);

                                    support.setCellPhone(Replace.Number_en_To_fa(object.getString("CellPhone")));
                                    support.setDescription(object.getString("Role"));
                                    support.setImage(SupportImageurl + object.getString("Image"));
                                    support.setName(object.getString("FullName"));
                                    support.setTelegram(object.getString("Telegram"));
                                    support.setEmail(object.getString("Emaill"));

                                    supports.add(support);

                                } catch (Exception e) {
                                }

                            }

                            emitter.onSuccess(supports);

                        } else {
                            if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                                postError("Api_SMS->getUsersSupport", resault.getMessage());
                            }
                            emitter.onError(new IOException(resault.getResault().toString()));
                        }

                    });

                } catch (Exception e) {
                    postError("Api_SMS->getUsersSupport", e.toString());
                    emitter.onError(e);
                }

            }).start();
        });
    }

    public void Cancel(String tag, Context context) {

        cancelBase(tag, context);

        if (volley_getSMS != null) {
            volley_getSMS.Cancel(tag, context);
        }

        if (volley_setArchiveSMS != null) {
            volley_setArchiveSMS.Cancel(tag, context);
        }

        if (volley_getDetailSMS != null) {
            volley_getDetailSMS.Cancel(tag, context);
        }

        if (volley_postSMS != null) {
            volley_postSMS.Cancel(tag, context);
        }

        if (volley_getUsersSuport != null) {
            volley_getUsersSuport.Cancel(tag, context);
        }

    }

}
