package ir.tdaapp.paymanyar.Model.Repositorys.Server;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.li_volley.Volleys.GetJsonObjectVolley;
import ir.tdaapp.li_volley.Volleys.PostJsonObjectVolley;
import ir.tdaapp.li_volley.Volleys.PostJsonObject_And_GetJsonArrayVolley;
import ir.tdaapp.paymanyar.Model.Enums.AdType;
import ir.tdaapp.paymanyar.Model.Services.onUploadFiles;
import ir.tdaapp.paymanyar.Model.Utilitys.Base_Api;
import ir.tdaapp.paymanyar.Model.Utilitys.FileManger;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailPowerSupply;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FilterPowerSupplyNetwork;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Job;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PostPowerSupply;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PowerSupplyNetwork;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_ProvincesAndCities;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_WorkExperience;

/**
 * مربوط به نیروکار
 **/
public class Api_PowerSupply extends Base_Api {

    PostJsonObject_And_GetJsonArrayVolley volley_getPowerSupply;
    GetJsonObjectVolley volley_getDetailPowerSupply;
    PostJsonObjectVolley volley_addPowerSupply;
    //زمانی که کاربر درحال آپلود فایل باشد مقدار زیر ترو خواهد شد
    boolean isUploadedFile = false;

    /**
     * در اینجا لیست آگهی های شبکه تامین کار گرفته می شود
     **/
    public Single<List<VM_PowerSupplyNetwork>> getPowerSupply(VM_FilterPowerSupplyNetwork filter, List<VM_Job> jobs, List<VM_ProvincesAndCities> provincesAndCities, List<VM_WorkExperience> workExperiences) {
        return Single.create(emitter -> {
            new Thread(() -> {

                try {

                    JSONObject input = new JSONObject();
                    try {

                        input.put("State", filter.getStateId());
                        input.put("City", filter.getCityId());
                        input.put("Job", filter.getJobId());
                        input.put("WorkExperience", filter.getWorkExperienceId());
                        input.put("Paging", filter.getPaging());

                    } catch (Exception e) {
                    }

                    volley_getPowerSupply = new PostJsonObject_And_GetJsonArrayVolley(ApiUrl + "Advertising/PostAdList", input, resault -> {
                        if (resault.getResault() == ResaultCode.Success) {

                            List<VM_PowerSupplyNetwork> supplyNetworks = new ArrayList<>();

                            JSONArray array = resault.getJsonArray();

                            for (int i = 0; i < array.length(); i++) {
                                try {

                                    JSONObject object = array.getJSONObject(i);
                                    VM_PowerSupplyNetwork supplyNetwork = new VM_PowerSupplyNetwork();

                                    supplyNetwork.setId(object.getInt("Id"));
                                    supplyNetwork.setCellPhone(object.getString("Phone"));
                                    supplyNetwork.setImage(object.getString("PicsAd"));
                                    supplyNetwork.setDate(object.getString("DateInsert"));

                                    if (object.getInt("Job") != 0) {
                                        int jobId = object.getInt("Job");

                                        for (int j = 0; j < jobs.size(); j++) {
                                            if (jobs.get(j).getId() == jobId) {
                                                supplyNetwork.setJobTitle(jobs.get(j).getTitle());
                                                break;
                                            }
                                        }
                                    }

                                    if (object.getInt("State") != 0) {
                                        int stateId = object.getInt("State");

                                        for (int j = 0; j < provincesAndCities.size(); j++) {
                                            if (provincesAndCities.get(j).getId() == stateId) {
                                                supplyNetwork.setProvinceAndCity(provincesAndCities.get(j).getTitle());
                                                break;
                                            }
                                        }
                                    }

                                    if (object.getInt("WorkExperience") != 0) {
                                        int workExperience = object.getInt("WorkExperience");

                                        for (int j = 0; j < workExperiences.size(); j++) {
                                            if (workExperiences.get(j).getId() == workExperience) {
                                                supplyNetwork.setWorkExperience(workExperiences.get(j).getTitle());
                                                break;
                                            }
                                        }
                                    }

                                    supplyNetwork.setName(object.getString("Name"));

                                    supplyNetworks.add(supplyNetwork);

                                } catch (Exception e) {
                                }
                            }

                            emitter.onSuccess(supplyNetworks);

                        } else {
                            if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                                postError("Api_PowerSupply->getPowerSupply", resault.getMessage());
                            }
                            emitter.onError(new IOException(resault.getResault().toString()));
                        }
                    });

                } catch (Exception e) {
                    postError("Api_PowerSupply->getPowerSupply", e.toString());
                    emitter.onError(e);
                }

            }).start();
        });
    }

    /**
     * در اینجا جزئیات نیروکار برگشت داده می شود
     **/
    public Single<VM_DetailPowerSupply> getDetailPowerSupply(int id) {
        return Single.create(emitter -> {
            new Thread(() -> {

                volley_getDetailPowerSupply = new GetJsonObjectVolley(ApiUrl + "Advertising/GetAdInfo?Id=" + id, resault -> {

                    if (resault.getResault() == ResaultCode.Success) {

                        VM_DetailPowerSupply detailPowerSupply = new VM_DetailPowerSupply();

                        try {

                            JSONObject object = resault.getObject();

                            detailPowerSupply.setId(object.getInt("Id"));

                            if (!object.getString("DateInsert").equalsIgnoreCase("null"))
                                detailPowerSupply.setDateInsert(object.getString("DateInsert"));

                            if (!object.getString("Name").equalsIgnoreCase("null"))
                                detailPowerSupply.setName(object.getString("Name"));

                            if (!object.getString("AdType").equalsIgnoreCase("null")) {
                                if (object.getBoolean("AdType")) {
                                    detailPowerSupply.setAdType(AdType.presentation);
                                } else {
                                    detailPowerSupply.setAdType(AdType.request);
                                }
                            }

                            if (!object.getString("Job").equalsIgnoreCase("null"))
                                detailPowerSupply.setJobId(object.getInt("Job"));

                            if (!object.getString("WorkExperience").equalsIgnoreCase("null"))
                                detailPowerSupply.setWorkExperienceId(object.getInt("WorkExperience"));

                            if (!object.getString("State").equalsIgnoreCase("null"))
                                detailPowerSupply.setStateId(object.getInt("State"));

                            if (!object.getString("City").equalsIgnoreCase("null"))
                                detailPowerSupply.setCityId(object.getInt("City"));

                            if (!object.getString("Phone").equalsIgnoreCase("null"))
                                detailPowerSupply.setPhone(object.getString("Phone"));

                            if (!object.getString("Description").equalsIgnoreCase("null"))
                                detailPowerSupply.setDescription(object.getString("Description"));

                            if (!object.getString("Special").equalsIgnoreCase("null"))
                                detailPowerSupply.setSpecial(object.getBoolean("Special"));

                            JSONArray images = object.getJSONArray("AllPicsAd");

                            for (int i = 0; i < images.length(); i++) {
                                try {
                                    String url = images.get(i).toString();
                                    detailPowerSupply.getImages().add(url);
                                } catch (Exception e) {
                                }
                            }

                        } catch (Exception e) {
                            postError("Api_PowerSupply->getDetailPowerSupply", e.toString());
                        }

                        emitter.onSuccess(detailPowerSupply);

                    } else {
                        if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                            postError("Api_PowerSupply->getDetailPowerSupply", resault.getMessage());
                        }
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }

                });

            }).start();
        });
    }

    /**
     * در اینجا یک نیروکار اضافه می شود
     * **/
    public Single<VM_Message> addPowerSupply(VM_PostPowerSupply powerSupply) {
        return Single.create(emitter -> {
            new Thread(() -> {

                JSONObject input = new JSONObject();

                try {

                    input.put("UserId", powerSupply.getUserId());
                    input.put("Name", powerSupply.getName());

                    //در اینجا نوع آگهی ست می شود
                    if (powerSupply.getAdType() == AdType.request) {
                        input.put("AdType", true);
                    } else {
                        input.put("AdType", false);
                    }

                    input.put("Job", powerSupply.getJob());
                    input.put("WorkExperience", powerSupply.getWorkExperiences());
                    input.put("State", powerSupply.getState());
                    input.put("City", powerSupply.getCity());
                    input.put("Phone", powerSupply.getCellPhone());
                    input.put("Description", powerSupply.getDescription());

                    //در اینجا عکس ها ست می شوند
                    JSONArray imagesArray = new JSONArray();
                    if (powerSupply.getImages().size() > 0) {
                        List<String> array = powerSupply.getImages();
                        for (int i = 0; i < array.size(); i++) {
                            imagesArray.put(array.get(i));
                        }
                    } else {
                        imagesArray.put("1");
                    }
                    input.put("PicsAd", imagesArray);

                } catch (Exception e) {
                }

                volley_addPowerSupply = new PostJsonObjectVolley(ApiUrl + "Advertising/PostAdvertising", input, resault -> {

                    if (resault.getResault() == ResaultCode.Success) {

                        VM_Message message = new VM_Message();

                        try {
                            JSONObject object = resault.getObject();

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

    /**
     * در اینجا فایل آگهی ها آپلود می شود
     * **/
    public void uploadFile(List<String> urlfiles, onUploadFiles uploadFiles) {

        isUploadedFile = true;

        String url = ApiUrl + "PostFile/PostFileAd";
        FileManger fileManger = new FileManger(url);

        new Thread(() -> {

            List<String> vals = new ArrayList<>();

            for (String i : urlfiles) {
                try {
                    vals.add(fileManger.uploadFile(i).replace("\"", ""));
                } catch (Exception e) {
                }
            }

            if (isUploadedFile) {
                uploadFiles.onSuccess(vals);
            }

        }).start();

    }

    public void cancel(String tag, Context context) {

        isUploadedFile = false;

        if (volley_getPowerSupply != null) {
            volley_getPowerSupply.Cancel(tag, context);
        }

        if (volley_getDetailPowerSupply != null) {
            volley_getDetailPowerSupply.Cancel(tag, context);
        }

        if (volley_addPowerSupply != null) {
            volley_addPowerSupply.Cancel(tag, context);
        }
    }

}
