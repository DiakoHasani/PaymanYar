package ir.tdaapp.li_volley.Utility;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class CancelVolley {

    RequestQueue requestQueue;

    public CancelVolley(String TAG, StringRequest request, Context context){

        if (request!=null){

            requestQueue = Volley.newRequestQueue(context);

            request.setTag(TAG);

            requestQueue.add(request);

            if (requestQueue != null) {
                requestQueue.cancelAll(TAG);
            }

        }
    }

    public CancelVolley(String TAG, JsonObjectRequest request, Context context){

        if (request!=null){

            requestQueue = Volley.newRequestQueue(context);

            request.setTag(TAG);

            requestQueue.add(request);

            if (requestQueue != null) {
                requestQueue.cancelAll(TAG);
            }

        }
    }

    public CancelVolley(String TAG, JsonArrayRequest request, Context context){

        if (request!=null){

            requestQueue = Volley.newRequestQueue(context);

            request.setTag(TAG);

            requestQueue.add(request);

            if (requestQueue != null) {
                requestQueue.cancelAll(TAG);
            }

        }
    }

    public CancelVolley(String TAG,CustomRequest_JsonArray request, Context context){
        if (request!=null){

            requestQueue = Volley.newRequestQueue(context);

            request.setTag(TAG);

            requestQueue.add(request);

            if (requestQueue != null) {
                requestQueue.cancelAll(TAG);
            }

        }
    }
}
