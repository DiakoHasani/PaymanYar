package ir.tdaapp.li_volley.Volleys;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
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

public class GetStringVolley {

    //آدرس ای پی آی
    String Url = "";

    //زمان انتظار برای دریافت جواب
    int TimeOut = 0;

    //تعداد دفعات تکرار
    int Retries = -1;
    float Multiplier = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
    IGetString iGetString;

    StringRequest stringRequest;

    Map<String, String> header;

    public GetStringVolley(String url, IGetString iGetString) {
        Url = url;
        this.iGetString = iGetString;
        Get();
    }

    public GetStringVolley(String url, int timeOut, IGetString iGetString) {
        Url = url;
        TimeOut = timeOut;
        this.iGetString = iGetString;
        Get();
    }

    public GetStringVolley(String url, int timeOut, int retries, IGetString iGetString) {
        Url = url;
        TimeOut = timeOut;
        Retries = retries;
        this.iGetString = iGetString;
        Get();
    }

    public GetStringVolley(String url, int timeOut, int retries, float multiplier, IGetString iGetString) {
        Url = url;
        TimeOut = timeOut;
        Retries = retries;
        Multiplier = multiplier;
        this.iGetString = iGetString;
        Get();
    }

    //در اینجا اطلاعات از سرور گرفته می شود
    void Get() {

        ResaultGetStringVolley resault = new ResaultGetStringVolley();

        try {
            stringRequest = new StringRequest(Request.Method.GET, Url, response -> {
                resault.setRequest(response);
                resault.setResault(ResaultCode.Success);
                iGetString.Get(resault);
            }
                    , error -> {
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
                public Map<String, String> getHeaders() throws AuthFailureError {
                    if (getHeader() != null)
                        return getHeader();
                    return super.getHeaders();
                }
            };

            RetryPolicy policy = new DefaultRetryPolicy(TimeOut, Retries, Multiplier);
            stringRequest.setRetryPolicy(policy);

            AppController.getInstance().addToRequestQueue(stringRequest);

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
        new CancelVolley(TAG, stringRequest, context);
    }
}
