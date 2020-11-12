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

import org.json.JSONObject;

import java.util.Map;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.li_volley.Services.IGetJsonArray;
import ir.tdaapp.li_volley.Utility.AppController;
import ir.tdaapp.li_volley.Utility.CancelVolley;
import ir.tdaapp.li_volley.Utility.CustomRequest_JsonArray;
import ir.tdaapp.li_volley.ViewModel.ResaultGetJsonArrayVolley;

//در اینجا یک جیسون آبجکت پاس می دهد و یک جیسون ارای می گیرد
public class PostJsonObject_And_GetJsonArrayVolley {
    //آدرس ای پی آی
    String Url = "";

    JSONObject input;

    //زمان انتظار برای دریافت جواب
    int TimeOut = 0;

    //تعداد دفعات تکرار
    int Retries = -1;

    float Multiplier = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;

    IGetJsonArray iGetJsonArray;

    CustomRequest_JsonArray request;

    Map<String, String> header;

    public PostJsonObject_And_GetJsonArrayVolley(String url, JSONObject input, IGetJsonArray iGetJsonArray) {
        Url = url;
        this.input = input;
        this.iGetJsonArray = iGetJsonArray;
        post();
    }

    public PostJsonObject_And_GetJsonArrayVolley(String url, JSONObject input, int timeOut, IGetJsonArray iGetJsonArray) {
        Url = url;
        this.input = input;
        TimeOut = timeOut;
        this.iGetJsonArray = iGetJsonArray;
        post();
    }

    public PostJsonObject_And_GetJsonArrayVolley(String url, JSONObject input, int timeOut, int retries, IGetJsonArray iGetJsonArray) {
        Url = url;
        this.input = input;
        TimeOut = timeOut;
        Retries = retries;
        this.iGetJsonArray = iGetJsonArray;
        post();
    }

    public PostJsonObject_And_GetJsonArrayVolley(String url, JSONObject input, int timeOut, int retries, float multiplier, IGetJsonArray iGetJsonArray) {
        Url = url;
        this.input = input;
        TimeOut = timeOut;
        Retries = retries;
        Multiplier = multiplier;
        this.iGetJsonArray = iGetJsonArray;
        post();
    }

    void post() {
        ResaultGetJsonArrayVolley result = new ResaultGetJsonArrayVolley();

        try {

            request = new CustomRequest_JsonArray(Request.Method.POST, Url, input, response -> {
                result.setJsonArray(response);
                result.setResault(ResaultCode.Success);
                iGetJsonArray.Get(result);
            }, error -> {

                if (error instanceof TimeoutError) {
                    result.setResault(ResaultCode.TimeoutError);
                } else if (error instanceof ServerError) {
                    result.setResault(ResaultCode.ServerError);
                } else if (error instanceof NetworkError) {
                    result.setResault(ResaultCode.NetworkError);
                } else if (error instanceof ParseError) {
                    result.setResault(ResaultCode.ParseError);
                } else {
                    result.setResault(ResaultCode.Error);
                }
                result.setJsonArray(null);
                result.setMessage(error.toString());
                iGetJsonArray.Get(result);

            }){
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
            result.setJsonArray(null);
            result.setResault(ResaultCode.Error);
            result.setMessage(e.toString());
            iGetJsonArray.Get(result);
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
