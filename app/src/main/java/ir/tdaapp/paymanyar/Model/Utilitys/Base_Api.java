package ir.tdaapp.paymanyar.Model.Utilitys;

import android.content.Context;

import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.li_volley.Volleys.PostJsonObjectVolley;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;

public abstract class Base_Api {
    public String ApiUrl = "http://api.paimanyar.ir/api/";
    public String SliderImageUrl = "http://api.paimanyar.ir/Content/data/Sliders/";
    public String ImageAd = "http://api.paimanyar.ir/Content/data/Ad/";
    public String PDFurl="http://api.paimanyar.ir/Content/data/Books/";
    public String Orderurl="http://api.paimanyar.ir/Content/data/OrderFile/";
    public String SupportImageurl="http://api.paimanyar.ir/Content/data/Supporter/";

    PostJsonObjectVolley volley_PostError;
    Disposable dispose_postError;

    //در برنامه خطای رخ دهد در اینجا خطای آن را به سرور ارسال می کند
    public void postError(String name, String text) {

        String versionAndroid = android.os.Build.VERSION.RELEASE;

        Single<VM_Message> er = Single.create(emitter -> {
            new Thread(() -> {

                try {

                    JSONObject input = new JSONObject();

                    try {

                        input.put("Name", name);
                        input.put("Text", text);
                        input.put("AndroidVersion", versionAndroid);

                    } catch (Exception e) {
                    }

                    volley_PostError = new PostJsonObjectVolley(ApiUrl + "Error/AddError", input, resault -> {
                        if (resault.getResault() == ResaultCode.Success) {

                            VM_Message message = new VM_Message();
                            JSONObject object = resault.getObject();

                            try {
                                message.setMessage(object.getString("MessageText"));
                                message.setCode(object.getInt("Code"));
                                message.setResult(object.getBoolean("Result"));
                            } catch (Exception e) {
                            }

                            emitter.onSuccess(message);

                        } else {
                            emitter.onError(new IOException(resault.getResault().toString()));
                        }
                    });

                } catch (Exception e) {
                    emitter.onError(e);
                }

            }).start();
        });

        dispose_postError=er.subscribeWith(new DisposableSingleObserver<VM_Message>() {
            @Override
            public void onSuccess(VM_Message message) {

            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }

    public void cancelBase(String tag, Context context) {
        if (volley_PostError != null) {
            volley_PostError.Cancel(tag, context);
        }

        if (dispose_postError!=null){
            dispose_postError.dispose();
        }
    }
}
