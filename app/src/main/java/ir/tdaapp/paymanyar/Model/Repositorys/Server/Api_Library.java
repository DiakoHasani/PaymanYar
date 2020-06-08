package ir.tdaapp.paymanyar.Model.Repositorys.Server;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.li_volley.Volleys.GetJsonArrayVolley;
import ir.tdaapp.li_volley.Volleys.PostJsonObjectVolley;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Library;
import ir.tdaapp.paymanyar.Model.Utilitys.Base_Api;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Library;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;

//مربوط به کتابخانه
public class Api_Library extends Base_Api {

    private GetJsonArrayVolley volley_getLibraries;
    PostJsonObjectVolley volley_addCountDownloadLibrary;

    //در اینجا لیست کتاب ها گرفته می شود
    public Single<List<VM_Library>> getLibraries(String query, int page, Tbl_Library tbl_library) {

        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    List<VM_Library> libraries = new ArrayList<>();

                    volley_getLibraries = new GetJsonArrayVolley(ApiUrl + "Libraries/GetLibraries?Title=" + query + "&Paging=" + page, resault -> {

                        if (resault.getResault() == ResaultCode.Success) {

                            JSONArray array = resault.getJsonArray();

                            for (int i = 0; i < array.length(); i++) {

                                try {
                                    JSONObject object = array.getJSONObject(i);
                                    VM_Library library = new VM_Library();

                                    library.setId(object.getInt("Id"));
                                    library.setTitle(object.getString("Title"));
                                    library.setUrl(PDFurl + object.getString("Url"));
                                    library.setDownloaded(tbl_library.hasLibray(object.getInt("Id")));

                                    libraries.add(library);

                                } catch (Exception e) {

                                }
                            }

                            emitter.onSuccess(libraries);

                        } else {
                            if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                                postError("Api_Library->getLibraries", resault.getMessage());
                            }
                            emitter.onError(new IOException(resault.getResault().toString()));
                        }

                    });

                } catch (Exception e) {
                    postError("Api_Library->getLibraries", e.toString());
                    emitter.onError(e);
                }

            }).start();

        });

    }

    //در اینجا هر کتابی که دانلود می شود آیدی آن به برای افزایش تعداد دانلود شده کتاب به سرور ارسال می شود
    public Single<VM_Message> addCountDownloadLibrary(int id) {
        return Single.create(emitter -> {
            new Thread(() -> {

                JSONObject input = new JSONObject();
                try {
                    input.put("Id", id);
                } catch (Exception e) {
                }

                volley_addCountDownloadLibrary = new PostJsonObjectVolley(ApiUrl+"Libraries/PostAddCountDownloadBook?Id="+id,input,resault -> {
                    if (resault.getResault()==ResaultCode.Success){
                        VM_Message message=new VM_Message();
                        JSONObject object=resault.getObject();

                        try{
                            message.setResult(object.getBoolean("Result"));
                            message.setCode(object.getInt("Code"));
                            message.setMessage(object.getString("MessageText"));
                        }catch (Exception e){}

                        emitter.onSuccess(message);

                    }else{
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }
                });

            }).start();
        });
    }

    public void Cancel(String tag, Context context) {

        cancelBase(tag, context);

        if (volley_getLibraries != null) {
            volley_getLibraries.Cancel(tag, context);
        }

        if (volley_addCountDownloadLibrary != null) {
            volley_addCountDownloadLibrary.Cancel(tag, context);
        }

    }

}
