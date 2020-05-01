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
import ir.tdaapp.li_volley.Volleys.PostJsonObjectVolley;
import ir.tdaapp.paymanyar.Model.Utilitys.Base_Api;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailsTender;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FilterTenderNotification;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotification;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotifications;

public class Api_Tender extends Base_Api {

    PostJsonObjectVolley volley_getTenderNotification, volley_getDetailsTender;

    //در اینجا اطلاع رسانی مناقصات برگشت داده می شود
    public Single<VM_TenderNotification> getTenderNotification(VM_FilterTenderNotification filter) {

        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    JSONObject input = new JSONObject();

                    try {

                        input.put("StateId ", filter.getCityId());
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
                                    n.setStar(false);

                                    notifications.add(n);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            notification.setTenderNotifications(notifications);

                            emitter.onSuccess(notification);

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

    //در اینجا جزئیات مناقصات برگشت داده می شود
    public Single<VM_DetailsTender> getDetailsTender(VM_FilterTenderNotification filter) {

        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    JSONObject obg = new JSONObject();

                    try {

                        obg.put("StateId", filter.getCityId());
                        obg.put("FieldId", filter.getMajorId());
                        obg.put("Word", filter.getIncludesTheWord());
                        obg.put("FromDate", filter.getDate());
                        obg.put("FromFeeId", filter.getFromEstimateId());
                        obg.put("UpFeeId", filter.getUntilEstimateId());
                        obg.put("UserId", filter.getUserId());
                        obg.put("TenderId", filter.getTenderId());

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

                            } catch (Exception e) {
                            }

                            emitter.onSuccess(detailsTender);

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

    public void Cancel(String Tag, Context context) {

        if (volley_getTenderNotification != null) {
            volley_getTenderNotification.Cancel(Tag, context);
        }

        if (volley_getDetailsTender != null) {
            volley_getDetailsTender.Cancel(Tag, context);
        }

    }

}
