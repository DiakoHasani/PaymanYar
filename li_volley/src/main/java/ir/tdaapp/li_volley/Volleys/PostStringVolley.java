package ir.tdaapp.li_volley.Volleys;

import android.content.Context;

import com.android.volley.AuthFailureError;
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

public class PostStringVolley {
    //آدرس ای پی آی
    String Url = "";

    //ورودی پارامتر
    Map<String, String> params;

    //زمان انتظار برای دریافت جواب
    int TimeOut = 0;

    //تعداد دفعات تکرار
    int Retries = -1;

    float Multiplier = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;

    IGetString iGetString;

    StringRequest request;

    Map<String, String> header;

    public PostStringVolley(String url, Map<String, String> params, IGetString iGetString) {
        Url = url;
        this.params = params;
        this.iGetString = iGetString;
        Post();
    }

    public PostStringVolley(String url, Map<String, String> params, int timeOut, IGetString iGetString) {
        Url = url;
        this.params = params;
        TimeOut = timeOut;
        this.iGetString = iGetString;
        Post();
    }

    public PostStringVolley(String url, Map<String, String> params, int timeOut, int retries, IGetString iGetString) {
        Url = url;
        this.params = params;
        TimeOut = timeOut;
        Retries = retries;
        this.iGetString = iGetString;
        Post();
    }

    public PostStringVolley(String url, Map<String, String> params, int timeOut, int retries, float multiplier, IGetString iGetString) {
        Url = url;
        this.params = params;
        TimeOut = timeOut;
        Retries = retries;
        Multiplier = multiplier;
        this.iGetString = iGetString;
        Post();
    }

    void Post() {
        ResaultGetStringVolley resault = new ResaultGetStringVolley();
        try {
            request = new StringRequest(Request.Method.POST, Url, response -> {
                resault.setRequest(response);
                resault.setResault(ResaultCode.Success);
                iGetString.Get(resault);
            }, error -> {
                if (error instanceof TimeoutError) {
                    resault.setResault(ResaultCode.TimeoutError);
                } else if (error instanceof ServerError) {
                    resault.setResault(ResaultCode.ServerError);
                } else if (error instanceof NetworkError) {
                    resault.setResault(ResaultCode.NetworkError);
                } else if (error instanceof ParseError) {
                    resault.setResault(ResaultCode.ParseError);
                } else {
                    resault.setResault(ResaultCode.Error);
                }
                resault.setRequest("");
                resault.setMessage(error.toString());
                iGetString.Get(resault);
            }) {
                @Override
                protected Map<String, String> getParams() {
                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    if (getHeader() != null)
                        return getHeader();
                    return super.getHeaders();
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

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    //برای لغو کردن عملیات
    public void Cancel(String TAG, Context context) {
        new CancelVolley(TAG, request, context);
    }
}
