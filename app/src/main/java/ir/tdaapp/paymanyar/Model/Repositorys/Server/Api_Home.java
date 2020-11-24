package ir.tdaapp.paymanyar.Model.Repositorys.Server;

import android.content.Context;
import android.os.Environment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.li_volley.Volleys.GetJsonObjectVolley;
import ir.tdaapp.paymanyar.Model.Utilitys.Base_Api;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Home;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_HomeSlider;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_UpdateApp;

//در اینجا اطلاعات صفحه اصلی از سرور گرفته می شوند
public class Api_Home extends Base_Api {

    GetJsonObjectVolley get_HomeData;

    //در اینجا اطلاعات صفحه اصلی گرفته می شود
    public Single<VM_Home> HomeData() {
        return Single.create(emitter -> {
            new Thread(() -> {

                get_HomeData = new GetJsonObjectVolley(ApiUrl + "SliderAndApp/SliderAndApp", resault -> {

                    if (resault.getResault() == ResaultCode.Success) {

                        try {

                            JSONArray array_sliders = resault.getObject().getJSONArray("Slider");
                            JSONArray array_updateApps = resault.getObject().getJSONArray("Update");

                            List<VM_HomeSlider> sliders = new ArrayList<>();
                            List<VM_UpdateApp> updateApps = new ArrayList<>();

                            //در اینجا اسلایدرها گرفته می شوند
                            for (int i = 0; i < array_sliders.length(); i++) {

                                try {

                                    VM_HomeSlider slider = new VM_HomeSlider();
                                    JSONObject object = array_sliders.getJSONObject(i);

                                    slider.setId(object.getInt("Id"));
                                    slider.setTitle(object.getString("Title"));
                                    slider.setImage(SliderImageUrl + object.getString("Image"));

                                    //در اینجا نوع لینک اسلایدر ست می شود
                                    int urlKind = 0;
                                    String k = object.getString("UrlKind");
                                    if (!k.equalsIgnoreCase("null")) {
                                        urlKind = Integer.valueOf(k);
                                    }
                                    slider.setUrlKind(urlKind);

                                    String url = "";
                                    if (!object.getString("Url").equalsIgnoreCase("null")) {
                                        url = object.getString("Url");
                                    }
                                    slider.setUrl(url);

                                    sliders.add(slider);

                                } catch (Exception e) {
                                }

                            }

                            //در اینجا ورژن های آپدیت گرفته می شوند
                            for (int i = 0; i < array_updateApps.length(); i++) {

                                try {

                                    VM_UpdateApp updateApp = new VM_UpdateApp();
                                    JSONObject object = array_updateApps.getJSONObject(i);

                                    updateApp.setAppVesrsion(object.getDouble("AppVesrsion"));
                                    updateApp.setCleardate(object.getBoolean("Cleardate"));
                                    updateApp.setQuery(object.getString("Query"));
                                    updateApp.setVesrsion(object.getDouble("Vesrsion"));

                                    updateApps.add(updateApp);

                                } catch (Exception e) {
                                }
                            }

                            emitter.onSuccess(new VM_Home(sliders, updateApps));

                        } catch (Exception e) {
                            emitter.onError(e);
                        }

                    } else {
                        if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                            postError("Api_Home->HomeData", resault.getMessage());
                        }
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }

                });

            }).start();
        });
    }

    //در اینجا عملیات وولی لغو می شود
    public void Cancel(String TAG, Context context) {

        cancelBase(TAG, context);

        if (get_HomeData != null) {
            get_HomeData.Cancel(TAG, context);
        }
    }
}