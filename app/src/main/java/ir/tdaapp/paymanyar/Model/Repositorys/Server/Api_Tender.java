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

    PostJsonObjectVolley volley_getTenderNotification;

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
    public Single<VM_DetailsTender> getDetailsTender(int Id) {

        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    VM_DetailsTender detailsTender = new VM_DetailsTender();

                    detailsTender.setId(1);
                    detailsTender.setDescription("توضیحات الزامی است توضیحات الزامی است توضیحات الزامی است");
                    detailsTender.setGetDocumentsUp("1399/58/69");
                    detailsTender.setNationalEstimate("sdgsetewstsdgsetewstsdgsetewstsdgsetewst");
                    detailsTender.setPlace_of_Receipt_of_Documents("sgfgfgdfgsdgsetewstsdgsetewstsdgsetewst");
                    detailsTender.setReopeningDate("6546797");
                    detailsTender.setSendSuggestionsUp("stg3s4f6sd54f");
                    detailsTender.setTenderDevice("rtwr87twe+9r8");
                    detailsTender.setWebsite("www.rrrrr.com");

                    emitter.onSuccess(detailsTender);

                } catch (Exception e) {
                    emitter.onError(e);
                }

            }).run();

        });

    }

    public void Cancel(String Tag, Context context) {

        if (volley_getTenderNotification != null) {
            volley_getTenderNotification.Cancel(Tag, context);
        }

    }

}
