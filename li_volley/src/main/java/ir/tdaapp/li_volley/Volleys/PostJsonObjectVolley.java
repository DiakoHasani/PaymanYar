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
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Map;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.li_volley.Services.IGetJsonObject;
import ir.tdaapp.li_volley.Utility.AppController;
import ir.tdaapp.li_volley.Utility.CancelVolley;
import ir.tdaapp.li_volley.ViewModel.ResaultGetJsonObjectVolley;

public class PostJsonObjectVolley {

    //آدرس ای پی آی
    String Url = "";

    JSONObject input;

    //زمان انتظار برای دریافت جواب
    int TimeOut = 0;

    //تعداد دفعات تکرار
    int Retries = -1;

    float Multiplier = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;

    IGetJsonObject iGetJsonObject;

    JsonObjectRequest request;

    Map<String, String> header;

    public PostJsonObjectVolley(String url, JSONObject input, IGetJsonObject iGetJsonObject) {
        Url = url;
        this.input = input;
        this.iGetJsonObject = iGetJsonObject;
        Post();
    }

    public PostJsonObjectVolley(String url, JSONObject input, int timeOut, IGetJsonObject iGetJsonObject) {
        Url = url;
        this.input = input;
        TimeOut = timeOut;
        this.iGetJsonObject = iGetJsonObject;
        Post();
    }

    public PostJsonObjectVolley(String url, JSONObject input, int timeOut, int retries, IGetJsonObject iGetJsonObject) {
        Url = url;
        this.input = input;
        TimeOut = timeOut;
        Retries = retries;
        this.iGetJsonObject = iGetJsonObject;
        Post();
    }

    public PostJsonObjectVolley(String url, JSONObject input, int timeOut, int retries, float multiplier, IGetJsonObject iGetJsonObject) {
        Url = url;
        this.input = input;
        TimeOut = timeOut;
        Retries = retries;
        Multiplier = multiplier;
        this.iGetJsonObject = iGetJsonObject;
        Post();
    }

    void Post(){
        ResaultGetJsonObjectVolley resault=new ResaultGetJsonObjectVolley();

        try{

            request=new JsonObjectRequest(Request.Method.POST,Url,input,response -> {
                resault.setObject(response);
                resault.setResault(ResaultCode.Success);
                iGetJsonObject.Get(resault);
            },error -> {
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
                resault.setObject(null);
                resault.setMessage(error.toString());
                iGetJsonObject.Get(resault);
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

        }catch (Exception e){
            resault.setObject(null);
            resault.setResault(ResaultCode.Error);
            resault.setMessage(e.toString());
            iGetJsonObject.Get(resault);
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
