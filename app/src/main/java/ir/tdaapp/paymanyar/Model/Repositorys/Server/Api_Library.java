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
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Library;
import ir.tdaapp.paymanyar.Model.Utilitys.Base_Api;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Library;

//مربوط به کتابخانه
public class Api_Library extends Base_Api {

    private GetJsonArrayVolley volley_getLibraries;

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
                                    library.setUrl("https://files.tarikhema.org/pdf/ejtemaee/Bishoori.pdf");
                                    library.setDownloaded(tbl_library.hasLibray(object.getInt("Id")));

                                    libraries.add(library);

                                } catch (Exception e) {

                                }
                            }

                            emitter.onSuccess(libraries);

                        } else {
                            emitter.onError(new IOException(resault.getResault().toString()));
                        }

                    });

                } catch (Exception e) {
                    emitter.onError(e);
                }

            }).start();

        });

    }

    public void Cancel(String tag, Context context) {

        if (volley_getLibraries != null) {
            volley_getLibraries.Cancel(tag, context);
        }

    }

}
