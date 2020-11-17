package ir.tdaapp.paymanyar.Model.Repositorys.Server;

import android.content.Context;
import android.os.Environment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.li_volley.Volleys.GetJsonArrayVolley;
import ir.tdaapp.li_volley.Volleys.PostJsonObjectVolley;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Library;
import ir.tdaapp.paymanyar.Model.Utilitys.Base_Api;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_HomeSlider;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Library;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;

//مربوط به کتابخانه
public class Api_Library extends Base_Api {

    private GetJsonArrayVolley volley_getLibraries, volley_getSliderImages;
    PostJsonObjectVolley volley_addCountDownloadLibrary;
    Thread thread;

    //در اینجا لیست کتاب ها گرفته می شود
    public Single<List<VM_Library>> getLibraries(String query, int page, Tbl_Library tbl_library) {

        return Single.create(emitter -> {

            thread = new Thread(() -> {

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
                                    library.setBookName(object.getString("Url"));
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

            });
            thread.start();

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

                volley_addCountDownloadLibrary = new PostJsonObjectVolley(ApiUrl + "Libraries/PostAddCountDownloadBook?Id=" + id, input, resault -> {
                    if (resault.getResault() == ResaultCode.Success) {
                        VM_Message message = new VM_Message();
                        JSONObject object = resault.getObject();

                        try {
                            message.setResult(object.getBoolean("Result"));
                            message.setCode(object.getInt("Code"));
                            message.setMessage(object.getString("MessageText"));
                        } catch (Exception e) {
                        }

                        emitter.onSuccess(message);

                    } else {
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }
                });

            }).start();
        });
    }

    //ر اینجا پی دی اف دانلود می شود
    public Single<Boolean> downloadPDF(String downloadUrl, String title) {
        return Single.create(emitter -> {

            new Thread(() -> {
                try {
                    URL url = new URL(downloadUrl);
                    HttpURLConnection c = (HttpURLConnection) url.openConnection();
//                    c.setRequestMethod("GET");
//                    c.setDoOutput(true);
                    c.connect();

                    String PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
                    File file = new File(PATH);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    File outputFile = new File(file, title);
                    FileOutputStream fos = new FileOutputStream(outputFile);
                    InputStream is = c.getInputStream();
                    byte[] buffer = new byte[1024];
                    int len1 = 0;
                    while ((len1 = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, len1);
                    }
                    fos.flush();
                    fos.close();
                    is.close();
                    emitter.onSuccess(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    emitter.onSuccess(false);
                }
            }).start();

        });
    }

    /**
     * در اینجا عکس های اسلایدر گرفته می شود
     * **/
    public Single<List<VM_HomeSlider>> getSliderImages() {
        return Single.create(emitter -> {
            new Thread(() -> {
                volley_getSliderImages = new GetJsonArrayVolley(ApiUrl + "SliderAndApp/GetSliderForLibraries", resault -> {
                    if (resault.getResault() == ResaultCode.Success) {

                        try {

                            JSONArray array = resault.getJsonArray();
                            List<VM_HomeSlider> sliders = new ArrayList<>();

                            for (int i = 0; i < array.length(); i++) {
                                try {
                                    JSONObject object = array.getJSONObject(i);
                                    VM_HomeSlider slider = new VM_HomeSlider();

                                    slider.setId(object.getInt("Id"));
                                    slider.setTitle(object.getString("Title"));
                                    slider.setImage(SliderImageUrl + object.getString("Image"));

                                    //در اینجا نوع لینک اسلایدر ست می شود
                                    int urlKind = 0;
                                    String k = object.getString("UrlKind");
                                    if (!k.equalsIgnoreCase("null")) {
                                        urlKind = Integer.valueOf(k);
                                    }
                                    slider.setUrlKind(urlKind);

                                    String url = "";
                                    if (!object.getString("Url").equalsIgnoreCase("null")) {
                                        url = object.getString("Url");
                                    }
                                    slider.setUrl(url);

                                    sliders.add(slider);
                                } catch (Exception e) {
                                }
                            }

                            emitter.onSuccess(sliders);

                        } catch (Exception e) {
                            emitter.onError(e);
                        }

                    } else {
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

        if (volley_getSliderImages != null) {
            volley_getSliderImages.Cancel(tag, context);
        }

    }

}
