package ir.tdaapp.paymanyar.Model.Repositorys.Server;

import android.content.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Single;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.li_volley.Volleys.PostStringVolley;
import ir.tdaapp.paymanyar.Model.Utilitys.Base_Api;

public class Api_User extends Base_Api {

    PostStringVolley volley_validationUser;

    //در اینجا اگر مقدار فالس گرفته شود یعنی کاربر حق استفاده از این اکانت را ندارد و باید اکانتش پاک شود
    public Single<Boolean> validationUser(int userId, String api_Key) {

        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    Map<String, String> input = new HashMap<>();
                    input.put("UserId", String.valueOf(userId));
                    input.put("ApiKey", api_Key);

                    volley_validationUser = new PostStringVolley(ApiUrl + "User/PostValidationUserForGoToApp", input, resault -> {

                        if (resault.getResault() == ResaultCode.Success) {

                            try {

                                emitter.onSuccess(Boolean.valueOf(resault.getRequest()));

                            } catch (Exception e) {
                                postError("Api_User->validationUser", e.toString());
                                emitter.onError(e);
                            }

                        } else {
                            if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                                postError("Api_User->validationUser", resault.getMessage());
                            }
                            emitter.onError(new IOException(resault.getResault().toString()));
                        }

                    });

                } catch (Exception e) {
                    postError("Api_User->validationUser", e.toString());
                    emitter.onError(e);
                }

            }).start();

        });

    }

    public void cancel(String tag, Context context) {
        if (volley_validationUser != null) {
            volley_validationUser.Cancel(tag, context);
        }
    }

}
