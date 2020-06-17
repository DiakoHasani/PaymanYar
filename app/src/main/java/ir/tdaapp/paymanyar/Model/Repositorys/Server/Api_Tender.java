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
import ir.tdaapp.li_volley.Volleys.PostJsonArrayVolley;
import ir.tdaapp.li_volley.Volleys.PostJsonObjectVolley;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Tender;
import ir.tdaapp.paymanyar.Model.Utilitys.Base_Api;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailsTender;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FilterTenderNotification;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Let_me_know;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotification;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotifications;

public class Api_Tender extends Base_Api {

    PostJsonObjectVolley volley_getTenderNotification, volley_getDetailsTender, volley_postLetMeKnow, volley_getTendersFilter;
    PostJsonArrayVolley volley_getFavorites;

    //در اینجا اطلاع رسانی مناقصات برگشت داده می شود
    public Single<VM_TenderNotification> getTenderNotification(VM_FilterTenderNotification filter, Tbl_Tender tbl_tender) {

        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    JSONObject input = new JSONObject();

                    try {

                        input.put("StateId", filter.getCityId());
                        input.put("FieldId", filter.getMajorId());
                        input.put("Word", filter.getIncludesTheWord());
                        input.put("FromDate", filter.getDate());
                        input.put("FromFeeId", filter.getFromEstimateId());
                        input.put("UpFeeId", filter.getUntilEstimateId());
                        input.put("Paging", filter.getPage());

                    } catch (Exception e) {

                    }


                    volley_getTenderNotification = new PostJsonObjectVolley(ApiUrl + "Tender/PostTender", input, resault -> {

                        if (resault.getResault() == ResaultCode.Success) {

                            List<VM_TenderNotifications> notifications = new ArrayList<>();
                            VM_TenderNotification notification = new VM_TenderNotification();
                            JSONArray array = new JSONArray();

                            try {
                                array = resault.getObject().getJSONArray("Tenders");
                                notification.setCountTenders(resault.getObject().getInt("CountTender"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            for (int i = 0; i < array.length(); i++) {

                                try {

                                    VM_TenderNotifications n = new VM_TenderNotifications();
                                    JSONObject object = array.getJSONObject(i);

                                    n.setId(object.getString("TenderId"));
                                    n.setTitle(object.getString("Title"));
                                    n.setFree(object.getBoolean("Free"));
                                    n.setStar(tbl_tender.isFavoritTender(object.getString("TenderId")));

                                    notifications.add(n);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            notification.setTenderNotifications(notifications);

                            emitter.onSuccess(notification);

                        } else {
                            if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                                postError("Api_Tender->getTenderNotification", resault.getMessage());
                            }
                            emitter.onError(new IOException(resault.getResault().toString()));
                        }

                    });

                } catch (Exception e) {
                    postError("Api_Tender->getTenderNotification", e.toString());
                    emitter.onError(e);
                }

            }).start();

        });

    }

    //در اینجا جزئیات مناقصات برگشت داده می شود
    public Single<VM_DetailsTender> getDetailsTender(VM_FilterTenderNotification filter, Tbl_Tender tbl_tender) {

        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    JSONObject obg = new JSONObject();

                    try {

                        obg.put("StateId", filter.getCityId());
                        obg.put("FieldId", filter.getMajorId());
                        obg.put("FromFeeId", filter.getFromEstimateId());
                        obg.put("UpFeeId", filter.getUntilEstimateId());
                        obg.put("UserId", filter.getUserId());
                        obg.put("TenderId", filter.getTenderId());

                        if (filter.getDate() != null) {
                            obg.put("FromDate", filter.getDate());
                        } else {
                            obg.put("FromDate", "");
                        }

                        if (filter.getIncludesTheWord() != null) {
                            obg.put("Word", filter.getIncludesTheWord());
                        } else {
                            obg.put("Word", "");
                        }

                    } catch (Exception e) {
                    }

                    volley_getDetailsTender = new PostJsonObjectVolley(ApiUrl + "Tender/PostTenderInfo", obg, resault -> {

                        if (resault.getResault() == ResaultCode.Success) {

                            VM_DetailsTender detailsTender = new VM_DetailsTender();

                            try {

                                JSONObject object = resault.getObject();

                                detailsTender.setStatus(object.getBoolean("Status"));
                                detailsTender.setId(object.getInt("TenderId"));
                                detailsTender.setDescription(object.getString("Description"));
                                detailsTender.setGetDocumentsUp(object.getString("DateReceiveDocument"));
                                detailsTender.setNationalEstimate(object.getLong("Fee") + "");
                                detailsTender.setPlace_of_Receipt_of_Documents(object.getString("LocationOfReceiveDocument"));
                                detailsTender.setReopeningDate(object.getString("DateOpen"));
                                detailsTender.setSendSuggestionsUp(object.getString("DateExpireSuggest"));
                                detailsTender.setTenderDevice(object.getString("Organization"));
                                detailsTender.setWebsite(object.getString("WebSite"));
                                detailsTender.setNextTenderId(object.getString("NextTenderId"));
                                detailsTender.setBeforeTenderId(object.getString("BeforeTenderId"));
                                detailsTender.setFevorit(tbl_tender.isFavoritTender(filter.getTenderId()));
                                detailsTender.setCityId(object.getInt("StateId"));
                                detailsTender.setMajorId(object.getInt("FieldId"));
                                detailsTender.setTitle(object.getString("Title"));

                            } catch (Exception e) {
                            }

                            emitter.onSuccess(detailsTender);

                        } else {
                            if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                                postError("Api_Tender->getDetailsTender", resault.getMessage());
                            }
                            emitter.onError(new IOException(resault.getResault().toString()));
                        }

                    });

                } catch (Exception e) {
                    postError("Api_Tender->getDetailsTender", e.toString());
                    emitter.onError(e);
                }

            }).start();

        });

    }

    //در اینجا داده ها برای باخبرم کن به سرور پاس داده می شوند
    public Single<VM_Message> postLetMeKnow(VM_Let_me_know filter) {

        return Single.create(emitter -> {

            new Thread(() -> {

                JSONObject object = new JSONObject();

                try {

                    object.put("FkState", filter.getCityId());
                    object.put("FkField", filter.getMajorId());
                    object.put("FkMinPrice", filter.getFromEstimateId());
                    object.put("FkMaxPrice", filter.getUntilEstimateId());
                    object.put("ApiKey", filter.getToken());

                } catch (Exception e) {

                }

                volley_postLetMeKnow = new PostJsonObjectVolley(ApiUrl + "Tender/PostLetMeKnow", object, resault -> {

                    if (resault.getResault() == ResaultCode.Success) {

                        VM_Message message = new VM_Message();
                        JSONObject obj = resault.getObject();

                        try {

                            message.setCode(obj.getInt("Code"));
                            message.setMessage(obj.getString("MessageText"));
                            message.setResult(obj.getBoolean("Result"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        emitter.onSuccess(message);

                    } else {
                        if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                            postError("Api_Tender->postLetMeKnow", resault.getMessage());
                        }
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }

                });

            }).start();

        });

    }

    //در اینجا مناقصاتی که کاربر فیلتر کرده است از سرورگرفته می شود
    public Single<VM_TenderNotification> getTendersFilter(int page, String apiKey, Tbl_Tender tbl_tender) {
        return Single.create(emitter -> {
            new Thread(() -> {

                JSONObject input = new JSONObject();

                try {
                    input.put("Paging", page);
                    input.put("ApiKey", apiKey);
                } catch (Exception e) {
                }

                volley_getTendersFilter = new PostJsonObjectVolley(ApiUrl + "Tender/PostTenderUser", input, resault -> {

                    if (resault.getResault() == ResaultCode.Success) {

                        List<VM_TenderNotifications> notifications = new ArrayList<>();
                        VM_TenderNotification notification = new VM_TenderNotification();
                        JSONArray array = new JSONArray();

                        try {
                            array = resault.getObject().getJSONArray("Tenders");
                            notification.setCountTenders(resault.getObject().getInt("CountTender"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        for (int i = 0; i < array.length(); i++) {

                            try {

                                VM_TenderNotifications n = new VM_TenderNotifications();
                                JSONObject object = array.getJSONObject(i);

                                n.setId(object.getString("TenderId"));
                                n.setTitle(object.getString("Title"));
                                n.setFree(object.getBoolean("Free"));
                                n.setStar(tbl_tender.isFavoritTender(object.getString("TenderId")));

                                notifications.add(n);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        notification.setTenderNotifications(notifications);

                        emitter.onSuccess(notification);

                    } else {
                        if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                            postError("Api_Tender->getTendersFilter", resault.getMessage());
                        }
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }

                });

            }).start();
        });
    }

    //در اینجا لیست علاقه مندی ها نمایش داده می شوند
    public Single<List<VM_TenderNotifications>> getFavorites(List<String> vals, Tbl_Tender tbl_tender) {
        return Single.create(emitter -> {
            new Thread(() -> {

                try {
                    JSONArray input = new JSONArray();

                    for (int i = 0; i < vals.size(); i++) {
                        input.put(vals.get(i));
                    }

                    volley_getFavorites = new PostJsonArrayVolley(ApiUrl + "Tender/PostFavorite", input, resault -> {

                        if (resault.getResault() == ResaultCode.Success) {

                            try{
                                List<VM_TenderNotifications> notifications = new ArrayList<>();
                                JSONArray array=resault.getJsonArray();

                                for (int i=0;i<array.length();i++){
                                    try {
                                        JSONObject object=array.getJSONObject(i);
                                        VM_TenderNotifications notification=new VM_TenderNotifications();

                                        notification.setId(object.getString("TenderId"));
                                        notification.setTitle(object.getString("Title"));
                                        notification.setFree(object.getBoolean("Free"));
                                        notification.setStar(tbl_tender.isFavoritTender(object.getString("TenderId")));

                                        notifications.add(notification);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                emitter.onSuccess(notifications);

                            }catch (Exception e){
                                emitter.onError(e);
                            }

                        } else {
                            emitter.onError(new IOException(resault.getResault().toString()));
                        }

                    });

                } catch (Exception e) {
                }

            }).start();
        });
    }

    public void Cancel(String Tag, Context context) {

        cancelBase(Tag, context);

        if (volley_getTenderNotification != null) {
            volley_getTenderNotification.Cancel(Tag, context);
        }

        if (volley_getDetailsTender != null) {
            volley_getDetailsTender.Cancel(Tag, context);
        }

        if (volley_postLetMeKnow != null) {
            volley_postLetMeKnow.Cancel(Tag, context);
        }

        if (volley_getTendersFilter != null) {
            volley_getTendersFilter.Cancel(Tag, context);
        }

        if (volley_getFavorites != null) {
            volley_getFavorites.Cancel(Tag, context);
        }

    }
}
