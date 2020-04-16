package ir.tdaapp.li_volley.Volleys;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.li_volley.Services.IGetJsonObject;
import ir.tdaapp.li_volley.Utility.AppController;
import ir.tdaapp.li_volley.Utility.CancelVolley;
import ir.tdaapp.li_volley.ViewModel.ResaultGetJsonObjectVolley;

public class PutJsonObjectVolley {

    //آدرس ای پی آی
    String Url = "";

    JSONObject input;

    //زمان انتظار برای دریافت جواب
    int TimeOut = 0;

    //تعداد دفعات تکرار
    int Retries = -1;

    float Multiplier = 1f;

    IGetJsonObject iGetJsonObject;

    JsonObjectRequest request;

    public PutJsonObjectVolley(String url, JSONObject input, IGetJsonObject iGetJsonObject) {
        Url = url;
        this.input = input;
        this.iGetJsonObject = iGetJsonObject;
        Put();
    }

    public PutJsonObjectVolley(String url, JSONObject input, int timeOut, IGetJsonObject iGetJsonObject) {
        Url = url;
        this.input = input;
        TimeOut = timeOut;
        this.iGetJsonObject = iGetJsonObject;
        Put();
    }

    public PutJsonObjectVolley(String url, JSONObject input, int timeOut, int retries, IGetJsonObject iGetJsonObject) {
        Url = url;
        this.input = input;
        TimeOut = timeOut;
        Retries = retries;
        this.iGetJsonObject = iGetJsonObject;
        Put();
    }

    public PutJsonObjectVolley(String url, JSONObject input, int timeOut, int retries, float multiplier, IGetJsonObject iGetJsonObject) {
        Url = url;
        this.input = input;
        TimeOut = timeOut;
        Retries = retries;
        Multiplier = multiplier;
        this.iGetJsonObject = iGetJsonObject;
        Put();
    }

    void Put() {

        ResaultGetJsonObjectVolley resault = new ResaultGetJsonObjectVolley();

        try {

            request = new JsonObjectRequest(Request.Method.PUT, Url, input, response -> {
                resault.setObject(response);
                resault.setResault(ResaultCode.Success);
                iGetJsonObject.Get(resault);
            }, error -> {
                if (error instanceof TimeoutError) {
                    resault.setObject(null);
                    resault.setResault(ResaultCode.TimeoutError);
                } else if (error instanceof ServerError) {
                    resault.setObject(null);
                    resault.setResault(ResaultCode.ServerError);
                } else if (error instanceof NetworkError) {
                    resault.setObject(null);
                    resault.setResault(ResaultCode.NetworkError);
                } else if (error instanceof ParseError) {
                    resault.setObject(null);
                    resault.setResault(ResaultCode.ParseError);
                } else {
                    resault.setObject(null);
                    resault.setResault(ResaultCode.Error);
                }
                resault.setMessage(error.toString());
                iGetJsonObject.Get(resault);
            });

            RetryPolicy policy = new DefaultRetryPolicy(TimeOut, Retries, Multiplier);
            request.setRetryPolicy(policy);

            AppController.getInstance().addToRequestQueue(request);

        } catch (Exception e) {
            resault.setObject(null);
            resault.setResault(ResaultCode.Error);
            resault.setMessage(e.toString());
            iGetJsonObject.Get(resault);
        }

    }

    //برای لغو کردن عملیات
    public void Cancel(String TAG, Context context) {
        new CancelVolley(TAG, request, context);
    }

}
