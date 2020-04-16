package ir.tdaapp.li_volley.Volleys;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.li_volley.Services.IGetString;
import ir.tdaapp.li_volley.Utility.AppController;
import ir.tdaapp.li_volley.Utility.CancelVolley;
import ir.tdaapp.li_volley.ViewModel.ResaultGetStringVolley;

public class PutStringVolley {
    //آدرس ای پی آی
    String Url = "";

    //ورودی پارامتر
    Map<String, String> params;

    //زمان انتظار برای دریافت جواب
    int TimeOut = 0;

    //تعداد دفعات تکرار
    int Retries = -1;

    float Multiplier = 1f;

    IGetString iGetString;

    StringRequest request;

    public PutStringVolley(String url, Map<String, String> params, IGetString iGetString) {
        Url = url;
        this.params = params;
        this.iGetString = iGetString;
        Put();
    }

    public PutStringVolley(String url, Map<String, String> params, int timeOut, IGetString iGetString) {
        Url = url;
        this.params = params;
        TimeOut = timeOut;
        this.iGetString = iGetString;
        Put();
    }

    public PutStringVolley(String url, Map<String, String> params, int timeOut, int retries, IGetString iGetString) {
        Url = url;
        this.params = params;
        TimeOut = timeOut;
        Retries = retries;
        this.iGetString = iGetString;
        Put();
    }

    public PutStringVolley(String url, Map<String, String> params, int timeOut, int retries, float multiplier, IGetString iGetString) {
        Url = url;
        this.params = params;
        TimeOut = timeOut;
        Retries = retries;
        Multiplier = multiplier;
        this.iGetString = iGetString;
        Put();
    }

    void Put() {
        ResaultGetStringVolley resault = new ResaultGetStringVolley();

        try {

            request = new StringRequest(Request.Method.PUT, Url, response -> {
                resault.setRequest(response);
                resault.setResault(ResaultCode.Success);
                iGetString.Get(resault);
            }, error -> {
                if (error instanceof TimeoutError) {
                    resault.setRequest("");
                    resault.setResault(ResaultCode.TimeoutError);
                } else if (error instanceof ServerError) {
                    resault.setRequest("");
                    resault.setResault(ResaultCode.ServerError);
                } else if (error instanceof NetworkError) {
                    resault.setRequest("");
                    resault.setResault(ResaultCode.NetworkError);
                } else if (error instanceof ParseError) {
                    resault.setRequest("");
                    resault.setResault(ResaultCode.ParseError);
                } else {
                    resault.setRequest("");
                    resault.setResault(ResaultCode.Error);
                }
                resault.setMessage(error.toString());
                iGetString.Get(resault);
            }) {
                @Override
                protected Map<String, String> getParams() {
                    return params;
                }
            };

            RetryPolicy policy = new DefaultRetryPolicy(TimeOut, Retries, Multiplier);
            request.setRetryPolicy(policy);

            AppController.getInstance().addToRequestQueue(request);

        } catch (Exception e) {
            resault.setRequest("");
            resault.setResault(ResaultCode.Error);
            resault.setMessage(e.toString());
            iGetString.Get(resault);
        }
    }

    //برای لغو کردن عملیات
    public void Cancel(String TAG, Context context) {
        new CancelVolley(TAG, request, context);
    }
}
