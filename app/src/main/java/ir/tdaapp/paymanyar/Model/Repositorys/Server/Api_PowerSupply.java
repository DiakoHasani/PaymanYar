package ir.tdaapp.paymanyar.Model.Repositorys.Server;

import android.content.Context;

import com.google.android.gms.common.api.Api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.li_volley.Volleys.GetJsonArrayVolley;
import ir.tdaapp.li_volley.Volleys.GetJsonObjectVolley;
import ir.tdaapp.li_volley.Volleys.PostJsonObjectVolley;
import ir.tdaapp.li_volley.Volleys.PostJsonObject_And_GetJsonArrayVolley;
import ir.tdaapp.paymanyar.Model.Enums.AdType;
import ir.tdaapp.paymanyar.Model.Enums.AdTypeCondition;
import ir.tdaapp.paymanyar.Model.Enums.StepsAddPower;
import ir.tdaapp.paymanyar.Model.Services.onUploadFiles;
import ir.tdaapp.paymanyar.Model.Utilitys.Base_Api;
import ir.tdaapp.paymanyar.Model.Utilitys.FileManger;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_AdTypeMachinery;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_AdUpgrade;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailMachinery;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailPowerSupply;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FilterMachinery;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FilterPowerSupplyNetwork;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Job;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Machinery;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_MachinerySpinner;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PostMachinery;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PostPowerSupply;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PowerSupplyNetwork;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_ProvincesAndCities;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_WorkExperience;
import ir.tdaapp.paymanyar.R;

/**
 * مربوط به نیروکار
 **/
public class Api_PowerSupply extends Base_Api {

    PostJsonObject_And_GetJsonArrayVolley volley_getPowerSupply, volley_getMachineries;
    GetJsonObjectVolley volley_getDetailPowerSupply, volley_getDetailMyPowerSupply, volley_getDetailMachinery, volley_getDetailMyMachinery;
    PostJsonObjectVolley volley_addPowerSupply, volley_addMachinery;
    GetJsonArrayVolley volley_getUpgrades, volley_getMyPowerSupplyNetwork, volley_getMyMachineries;

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
                                    supplyNetwork.setImage(ImageAd + object.getString("PicsAd"));
                                    supplyNetwork.setDate(object.getString("DateInsert"));
                                    supplyNetwork.setSpecial(object.getBoolean("Special"));

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
     * در اینجا لیست ماشین آلات گرفته می شود
     **/
    public Single<List<VM_Machinery>> getMachineries(VM_FilterMachinery filter, List<VM_ProvincesAndCities> provincesAndCities, List<VM_MachinerySpinner> machinerySpinners) {
        return Single.create(emitter -> {
            new Thread(() -> {
                try {
                    JSONObject input = new JSONObject();
                    try {
                        input.put("State", filter.getStateId());
                        input.put("City", filter.getCityId());
                        input.put("Machinery", filter.getMachineryId());
                        input.put("Paging", filter.getPaging());
                    } catch (Exception e) {
                    }

                    volley_getMachineries = new PostJsonObject_And_GetJsonArrayVolley(ApiUrl + "Advertising/PostAdMachinery", input, resault -> {
                        if (resault.getResault() == ResaultCode.Success) {

                            List<VM_Machinery> vals = new ArrayList<>();
                            JSONArray array = resault.getJsonArray();

                            try {

                                for (int i = 0; i < array.length(); i++) {

                                    VM_Machinery machinery = new VM_Machinery();

                                    try {
                                        JSONObject object = array.getJSONObject(i);

                                        machinery.setId(object.getInt("Id"));

                                        machinery.setImage(ImageAd + object.getString("PicsAd"));

                                        //در اینجا تایتل ماشین آلات ست می شود
                                        if (!object.getString("Machinery").equalsIgnoreCase("null")) {
                                            int machineryId = object.getInt("Machinery");
                                            for (int j = 0; j < machinerySpinners.size(); j++) {
                                                if (machinerySpinners.get(j).getId() == machineryId) {
                                                    machinery.setMachineryTitle(machinerySpinners.get(j).getTitle());
                                                }
                                            }
                                        }

                                        //در اینجا وضعیت آگهی فروش یا اجاره ای بودن ست می شود
                                        if (!object.getString("AdType").equalsIgnoreCase("null")) {
                                            int adType = object.getInt("AdType");
                                            if (adType == 1) {
                                                machinery.setAdTypeCondition(AdTypeCondition.Buy);
                                            } else if (adType == 2) {
                                                machinery.setAdTypeCondition(AdTypeCondition.Sales);
                                            } else if (adType == 3) {
                                                machinery.setAdTypeCondition(AdTypeCondition.RentGive);
                                            } else {
                                                machinery.setAdTypeCondition(AdTypeCondition.RentTake);
                                            }
                                        }

                                        //در اینجا قیمت ست می شود
                                        if (!object.getString("Price").equalsIgnoreCase("null")) {
                                            machinery.setPrice(object.getString("Price"));
                                        }

                                        //شماره موبایل
                                        if (!object.getString("Phone").equalsIgnoreCase("null")) {
                                            machinery.setCellPhone(object.getString("Phone"));
                                        }

                                        //استان
                                        if (object.getInt("State") != 0) {
                                            int stateId = object.getInt("State");

                                            for (int j = 0; j < provincesAndCities.size(); j++) {
                                                if (provincesAndCities.get(j).getId() == stateId) {
                                                    machinery.setProvinceAndCity(provincesAndCities.get(j).getTitle());
                                                    break;
                                                }
                                            }
                                        }

                                        //زمان
                                        if (!object.getString("DateInsert").equalsIgnoreCase("null")) {
                                            machinery.setDate(object.getString("DateInsert"));
                                        }

                                    } catch (Exception e) {
                                    } finally {
                                        vals.add(machinery);
                                    }
                                }

                            } catch (Exception e) {
                            }

                            emitter.onSuccess(vals);

                        } else {
                            if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                                postError("Api_PowerSupply->getMachineries", resault.getMessage());
                            }
                            emitter.onError(new IOException(resault.getResault().toString()));
                        }
                    });

                } catch (Exception e) {
                    postError("Api_PowerSupply->getMachineries", e.toString());
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
                                    String url = ImageAd + images.get(i).toString();
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
     * در اینجا جزئیات ماشین آلات گرفته می شود
     **/
    public Single<VM_DetailMachinery> getDetailMachinery(int id) {
        return Single.create(emitter -> {
            new Thread(() -> {
                volley_getDetailMachinery = new GetJsonObjectVolley(ApiUrl + "Advertising/GetAdMachineryInfo?Id=" + id, resault -> {
                    if (resault.getResault() == ResaultCode.Success) {
                        VM_DetailMachinery detailMachinery = new VM_DetailMachinery();
                        JSONObject object = resault.getObject();

                        try {

                            detailMachinery.setId(id);

                            //تایتل
                            if (!object.getString("Title").equalsIgnoreCase("null")) {
                                detailMachinery.setTitle(object.getString("Title"));
                            }

                            //در اینجا وضعیت آگهی فروش یا اجاره ای بودن ست می شود
                            if (!object.getString("AdType").equalsIgnoreCase("null")) {
                                int adType = object.getInt("AdType");
                                if (adType == 1) {
                                    detailMachinery.setAdTypeCondition(AdTypeCondition.Buy);
                                } else if (adType == 2) {
                                    detailMachinery.setAdTypeCondition(AdTypeCondition.Sales);
                                } else if (adType == 3) {
                                    detailMachinery.setAdTypeCondition(AdTypeCondition.RentGive);
                                } else {
                                    detailMachinery.setAdTypeCondition(AdTypeCondition.RentTake);
                                }
                            }

                            //آیدی ماشین آلات
                            if (!object.getString("Machinery").equalsIgnoreCase("null")) {
                                detailMachinery.setMachineryId(object.getInt("Machinery"));
                            }

                            //قیمت
                            if (!object.getString("Price").equalsIgnoreCase("null")) {
                                detailMachinery.setPrice(object.getString("Price"));
                            }

                            //استان
                            if (!object.getString("State").equalsIgnoreCase("null")) {
                                detailMachinery.setStateId(object.getInt("State"));
                            }

                            //شهر
                            if (!object.getString("City").equalsIgnoreCase("null")) {
                                detailMachinery.setCityId(object.getInt("City"));
                            }

                            //شماره موبایل
                            if (!object.getString("Phone").equalsIgnoreCase("null")) {
                                detailMachinery.setCellPhone(object.getString("Phone"));
                            }

                            //توضیحات
                            if (!object.getString("Description").equalsIgnoreCase("null")) {
                                detailMachinery.setDescription(object.getString("Description"));
                            }

                            //در اینجا عکس ها گرفته می شود
                            JSONArray array = object.getJSONArray("AllPicsAd");
                            List<String> images = new ArrayList<>();
                            if (array.length() > 0) {
                                for (int i = 0; i < array.length(); i++) {
                                    String image = ImageAd + array.get(i).toString();
                                    images.add(image);
                                }
                            }
                            detailMachinery.setImages(images);

                        } catch (Exception e) {
                        }

                        emitter.onSuccess(detailMachinery);
                    } else {
                        if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                            postError("Api_PowerSupply->getDetailMachinery", resault.getMessage());
                        }
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }
                });
            }).start();
        });
    }

    /**
     * در اینجا یک نیروکار اضافه می شود
     **/
    public Single<VM_Message> addPowerSupply(VM_PostPowerSupply powerSupply) {
        return Single.create(emitter -> {
            new Thread(() -> {

                JSONObject input = new JSONObject();

                try {

                    input.put("UserId", powerSupply.getUserId());
                    input.put("Name", powerSupply.getName());

                    //در اینجا نوع آگهی ست می شود
                    if (powerSupply.getAdType() == AdType.request) {
                        input.put("AdType", false);
                    } else {
                        input.put("AdType", true);
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
                        if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                            postError("Api_PowerSupply->addPowerSupply", resault.getMessage());
                        }
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }
                });

            }).start();
        });
    }

    /**
     * در اینجا ماشین آلات اضافه می شود
     **/
    public Single<VM_Message> addMachinery(VM_PostMachinery machinery) {
        return Single.create(emitter -> {
            new Thread(() -> {

                JSONObject input = new JSONObject();

                try {

                    //آیدی کاربر
                    input.put("UserId", machinery.getUserId());

                    //عنوان
                    input.put("Title", machinery.getTitle());

                    //نوع آگهی
                    switch (machinery.getAdType().getAdTypeCondition()) {
                        case Buy:
                            input.put("AdType", 1);
                            break;
                        case Sales:
                            input.put("AdType", 2);
                            break;
                        case RentGive:
                            input.put("AdType", 3);
                            break;
                        case RentTake:
                            input.put("AdType", 4);
                            break;
                    }

                    //آیدی دسته ماشین آلات
                    input.put("Machinery", machinery.getMachineryId());

                    //قیمت
                    input.put("Price", machinery.getPrice().replace(",", "").replace("٬", ""));

                    //استان
                    input.put("State", machinery.getState());

                    //شهر
                    input.put("City", machinery.getCity());

                    //شماره موبایل
                    input.put("Phone", machinery.getCellPhone());

                    //توضیحات
                    input.put("Description", machinery.getDescription());

                    //در اینجا عکس ها ست می شوند
                    JSONArray imagesArray = new JSONArray();
                    if (machinery.getImages().size() > 0) {
                        List<String> array = machinery.getImages();
                        for (int i = 0; i < array.size(); i++) {
                            imagesArray.put(array.get(i));
                        }
                    } else {
                        imagesArray.put("1");
                    }
                    input.put("PicsAd", imagesArray);


                } catch (Exception e) {
                }
                volley_addMachinery = new PostJsonObjectVolley(ApiUrl + "Advertising/PostAdAddMachinery", input, resault -> {
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
                        if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                            postError("Api_PowerSupply->addMachinery", resault.getMessage());
                        }
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }
                });
            }).start();
        });
    }

    /**
     * در اینجا فایل آگهی ها آپلود می شود
     **/
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

    /**
     * در اینجا لیست آیتم ها برای ارتقا آگهی از سرور گرفته می شود
     **/
    public Single<List<VM_AdUpgrade>> getUpgrades() {
        return Single.create(emitter -> {
            new Thread(() -> {
                volley_getUpgrades = new GetJsonArrayVolley(ApiUrl + "Advertising/GetUpgradeAd", resault -> {

                    if (resault.getResault() == ResaultCode.Success) {

                        List<VM_AdUpgrade> adUpgrades = new ArrayList<>();

                        try {

                            JSONArray array = resault.getJsonArray();

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject object = array.getJSONObject(i);
                                VM_AdUpgrade adUpgrade = new VM_AdUpgrade();

                                try {
                                    adUpgrade.setId(object.getInt("Id"));
                                    adUpgrade.setDescription(object.getString("Description"));
                                    adUpgrade.setPrice(object.getString("Price"));
                                } catch (Exception e) {
                                } finally {
                                    adUpgrades.add(adUpgrade);
                                }
                            }

                        } catch (Exception e) {
                        }

                        emitter.onSuccess(adUpgrades);

                    } else {
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }

                });
            }).start();
        });
    }

    /**
     * در اینجا لیست نیروکارهای من برگشت داده می شود
     **/
    public Single<List<VM_PowerSupplyNetwork>> getMyPowerSupplyNetwork(int userId, List<VM_Job> jobs, List<VM_ProvincesAndCities> provincesAndCities, List<VM_WorkExperience> workExperiences) {
        return Single.create(emitter -> {
            new Thread(() -> {
                try {
                    volley_getMyPowerSupplyNetwork = new GetJsonArrayVolley(ApiUrl + "Advertising/GetAdMeList?UserId=" + userId, resault -> {

                        if (resault.getResault() == ResaultCode.Success) {

                            List<VM_PowerSupplyNetwork> supplyNetworks = new ArrayList<>();

                            JSONArray array = resault.getJsonArray();

                            for (int i = 0; i < array.length(); i++) {
                                try {

                                    JSONObject object = array.getJSONObject(i);
                                    VM_PowerSupplyNetwork supplyNetwork = new VM_PowerSupplyNetwork();

                                    supplyNetwork.setId(object.getInt("Id"));
                                    supplyNetwork.setCellPhone(object.getString("Phone"));
                                    supplyNetwork.setImage(ImageAd + object.getString("PicsAd"));
                                    supplyNetwork.setDate(object.getString("DateInsert"));
                                    supplyNetwork.setSpecial(object.getBoolean("Special"));

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
                    emitter.onError(e);
                }
            }).start();
        });
    }

    public Single<List<VM_Machinery>> getMyMachineries(int userId, List<VM_ProvincesAndCities> provincesAndCities, List<VM_MachinerySpinner> machinerySpinners) {
        return Single.create(emitter -> {
            new Thread(() -> {
                volley_getMyMachineries = new GetJsonArrayVolley(ApiUrl + "Advertising/GetMeAdMachinery?UserId=" + userId, resault -> {
                    if (resault.getResault() == ResaultCode.Success) {

                        List<VM_Machinery> vals = new ArrayList<>();
                        JSONArray array = resault.getJsonArray();

                        try {

                            for (int i = 0; i < array.length(); i++) {

                                VM_Machinery machinery = new VM_Machinery();

                                try {
                                    JSONObject object = array.getJSONObject(i);

                                    machinery.setId(object.getInt("Id"));

                                    machinery.setImage(ImageAd + object.getString("PicsAd"));

                                    //در اینجا تایتل ماشین آلات ست می شود
                                    if (!object.getString("Machinery").equalsIgnoreCase("null")) {
                                        int machineryId = object.getInt("Machinery");
                                        for (int j = 0; j < machinerySpinners.size(); j++) {
                                            if (machinerySpinners.get(j).getId() == machineryId) {
                                                machinery.setMachineryTitle(machinerySpinners.get(j).getTitle());
                                            }
                                        }
                                    }

                                    //در اینجا وضعیت آگهی فروش یا اجاره ای بودن ست می شود
                                    if (!object.getString("AdType").equalsIgnoreCase("null")) {
                                        int adType = object.getInt("AdType");
                                        if (adType == 1) {
                                            machinery.setAdTypeCondition(AdTypeCondition.Buy);
                                        } else if (adType == 2) {
                                            machinery.setAdTypeCondition(AdTypeCondition.Sales);
                                        } else if (adType == 3) {
                                            machinery.setAdTypeCondition(AdTypeCondition.RentGive);
                                        } else {
                                            machinery.setAdTypeCondition(AdTypeCondition.RentTake);
                                        }
                                    }

                                    //در اینجا قیمت ست می شود
                                    if (!object.getString("Price").equalsIgnoreCase("null")) {
                                        machinery.setPrice(object.getString("Price"));
                                    }

                                    //شماره موبایل
                                    if (!object.getString("Phone").equalsIgnoreCase("null")) {
                                        machinery.setCellPhone(object.getString("Phone"));
                                    }

                                    //استان
                                    if (object.getInt("State") != 0) {
                                        int stateId = object.getInt("State");

                                        for (int j = 0; j < provincesAndCities.size(); j++) {
                                            if (provincesAndCities.get(j).getId() == stateId) {
                                                machinery.setProvinceAndCity(provincesAndCities.get(j).getTitle());
                                                break;
                                            }
                                        }
                                    }

                                    //زمان
                                    if (!object.getString("DateInsert").equalsIgnoreCase("null")) {
                                        machinery.setDate(object.getString("DateInsert"));
                                    }

                                } catch (Exception e) {
                                } finally {
                                    vals.add(machinery);
                                }
                            }

                        } catch (Exception e) {
                        }

                        emitter.onSuccess(vals);

                    } else {
                        if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                            postError("Api_PowerSupply->getMyMachineries", resault.getMessage());
                        }
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }
                });
            }).start();
        });
    }

    /**
     * در اینجا جزئیات نیروکار برای ویرایش گرفته می شود
     **/
    public Single<VM_PostPowerSupply> getDetailMyPowerSupply(int id) {
        return Single.create(emitter -> {
            new Thread(() -> {
                volley_getDetailMyPowerSupply = new GetJsonObjectVolley(ApiUrl + "Advertising/GetAdInfo?Id=" + id, resault -> {

                    if (resault.getResault() == ResaultCode.Success) {

                        VM_PostPowerSupply model = new VM_PostPowerSupply();
                        try {
                            JSONObject object = resault.getObject();

                            //نوع آگهی
                            if (!object.getString("AdType").equalsIgnoreCase("null")) {
                                if (object.getBoolean("AdType")) {
                                    model.setAdType(AdType.presentation);
                                } else {
                                    model.setAdType(AdType.request);
                                }
                            }

                            //سابقه کاری
                            model.setWorkExperiences(object.getInt("WorkExperience"));

                            //استان
                            model.setState(object.getInt("State"));

                            //شهر
                            model.setCity(object.getInt("City"));

                            //شغل
                            model.setJob(object.getInt("Job"));

                            //نام
                            model.setName(object.getString("Name"));

                            //شماره تماس
                            model.setCellPhone(object.getString("Phone"));

                            //توضیحات
                            model.setDescription(object.getString("Description"));

                            //ویژه
                            model.setSpecial(object.getBoolean("Special"));

                            //در اینجا وضعیت سفارش گرفته می شود
                            if (!object.getString("IsActive").equalsIgnoreCase("null")) {
                                model.setStepPower(object.getBoolean("IsActive") ? StepsAddPower.Post_an_Ad : StepsAddPower.Check_The_Ad);
                            }

                            //مربوط به عکس
                            JSONArray images = object.getJSONArray("AllPicsAd");
                            for (int i = 0; i < images.length(); i++) {
                                try {
                                    String url = ImageAd + images.get(i).toString();
                                    model.getImages().add(url);
                                } catch (Exception e) {
                                }
                            }

                        } catch (Exception e) {
                        }

                        emitter.onSuccess(model);
                    } else {
                        if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                            postError("Api_PowerSupply->getDetailMyPowerSupply", resault.getMessage());
                        }
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }

                });
            }).start();
        });
    }

    /**
     * در اینجا جزئیات ماشین آلات برای ویرایش گرفته می شود
     **/
    public Single<VM_PostMachinery> getDetailMyMachinery(int id, Context context) {
        return Single.create(emitter -> {
            new Thread(() -> {
                volley_getDetailMyMachinery = new GetJsonObjectVolley(ApiUrl + "Advertising/GetAdMachineryInfo?Id=" + id, resault -> {
                    if (resault.getResault() == ResaultCode.Success) {

                        VM_PostMachinery model = new VM_PostMachinery();
                        JSONObject object = resault.getObject();

                        try {
                            //در اینجا وضعیت آگهی فروش یا اجاره ای بودن ست می شود
                            if (!object.getString("AdType").equalsIgnoreCase("null")) {
                                int adType = object.getInt("AdType");
                                if (adType == 1) {
                                    model.setAdType(new VM_AdTypeMachinery(AdTypeCondition.Buy, context.getString(R.string.Buy)));
                                } else if (adType == 2) {
                                    model.setAdType(new VM_AdTypeMachinery(AdTypeCondition.Sales, context.getString(R.string.Sales)));
                                } else if (adType == 3) {
                                    model.setAdType(new VM_AdTypeMachinery(AdTypeCondition.RentGive, context.getString(R.string.RentGive)));
                                } else {
                                    model.setAdType(new VM_AdTypeMachinery(AdTypeCondition.RentTake, context.getString(R.string.RentTake)));
                                }
                            }

                            //آیدی دسته ماشین آلات
                            model.setMachineryId(object.getInt("Machinery"));

                            //استان
                            model.setState(object.getInt("State"));

                            //شهر
                            model.setCity(object.getInt("City"));

                            //قیمت
                            model.setPrice(object.getString("Price").replace(context.getString(R.string.Toman), "")
                                    .replace(",", "").replace("٬", "").trim());

                            //شماره تماس
                            model.setCellPhone(object.getString("Phone"));

                            //توضیحات
                            model.setDescription(object.getString("Description"));

                            //ویژه
                            model.setSpecial(object.getBoolean("Special"));

                            //عنوان
                            model.setTitle(object.getString("Title"));

                            //در اینجا وضعیت سفارش گرفته می شود
                            if (!object.getString("IsActive").equalsIgnoreCase("null")) {
                                model.setStepPower(object.getBoolean("IsActive") ? StepsAddPower.Post_an_Ad : StepsAddPower.Check_The_Ad);
                            }

                            //مربوط به عکس
                            JSONArray images = object.getJSONArray("AllPicsAd");
                            for (int i = 0; i < images.length(); i++) {
                                try {
                                    String url = ImageAd + images.get(i).toString();
                                    model.getImages().add(url);
                                } catch (Exception e) {
                                }
                            }

                        } catch (Exception e) {
                        }

                        emitter.onSuccess(model);

                    } else {
                        if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                            postError("Api_PowerSupply->getDetailMyMachinery", resault.getMessage());
                        }
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }
                });
            }).start();
        });
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

        if (volley_getUpgrades != null) {
            volley_getUpgrades.Cancel(tag, context);
        }

        if (volley_getMyPowerSupplyNetwork != null) {
            volley_getMyPowerSupplyNetwork.Cancel(tag, context);
        }

        if (volley_getDetailMyPowerSupply != null) {
            volley_getDetailMyPowerSupply.Cancel(tag, context);
        }

        if (volley_getMachineries != null) {
            volley_getMachineries.Cancel(tag, context);
        }

        if (volley_getDetailMachinery != null) {
            volley_getDetailMachinery.Cancel(tag, context);
        }

        if (volley_getMyMachineries != null) {
            volley_getMyMachineries.Cancel(tag, context);
        }

        if (volley_getDetailMyMachinery != null) {
            volley_getDetailMyMachinery.Cancel(tag, context);
        }

        if (volley_addMachinery != null) {
            volley_addMachinery.Cancel(tag, context);
        }
    }

}
