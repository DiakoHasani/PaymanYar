package ir.tdaapp.paymanyar.Model.Repositorys.Server;

import android.content.Context;

import com.google.android.gms.common.api.Api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import ir.tdaapp.li_utility.Codes.Replace;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.li_volley.Volleys.GetJsonArrayVolley;
import ir.tdaapp.li_volley.Volleys.GetJsonObjectVolley;
import ir.tdaapp.li_volley.Volleys.PostJsonObjectVolley;
import ir.tdaapp.li_volley.Volleys.PostJsonObject_And_GetJsonArrayVolley;
import ir.tdaapp.paymanyar.Model.Enums.AdType;
import ir.tdaapp.paymanyar.Model.Enums.AdTypeCondition;
import ir.tdaapp.paymanyar.Model.Enums.AdTypeMaterial;
import ir.tdaapp.paymanyar.Model.Enums.StepsAddPower;
import ir.tdaapp.paymanyar.Model.Services.onUploadFiles;
import ir.tdaapp.paymanyar.Model.Utilitys.Base_Api;
import ir.tdaapp.paymanyar.Model.Utilitys.FileManger;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_AdTypeMachinery;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_AdTypeMaterial;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_AdUpgrade;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailMachinery;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailMaterial;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailPowerSupply;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FilterMachinery;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FilterMaterial;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FilterPowerSupplyNetwork;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Job;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Machinery;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_MachinerySpinner;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Material;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_MaterialSpinner;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PostMachinery;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PostMaterial;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PostPowerSupply;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PowerSupplyNetwork;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_ProvincesAndCities;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_WorkExperience;
import ir.tdaapp.paymanyar.R;

/**
 * مربوط به نیروکار
 **/
public class Api_PowerSupply extends Base_Api {

    PostJsonObject_And_GetJsonArrayVolley volley_getPowerSupply, volley_getMachineries, volley_getMaterials;
    GetJsonObjectVolley volley_getDetailPowerSupply, volley_getDetailMyPowerSupply, volley_getDetailMachinery, volley_getDetailMyMachinery, volley_getDetailMaterial, volley_getDetailMyMaterial;
    PostJsonObjectVolley volley_addPowerSupply, volley_addMachinery, volley_addMaterial;
    GetJsonArrayVolley volley_getUpgrades, volley_getMyPowerSupplyNetwork, volley_getMyMachineries, volley_getMyMaterials;
    GetJsonArrayVolley volley_getJobs, volley_getMachinerySpinner, volley_getMaterialSpinner;
    GetJsonArrayVolley volley_getMachinerysTitle, volley_getJobsTitle, volley_getMaterialsTitle;

    //زمانی که کاربر درحال آپلود فایل باشد مقدار زیر ترو خواهد شد
    boolean isUploadedFile = false;

    /**
     * در اینجا لیست آگهی های شبکه تامین کار گرفته می شود
     **/
    public Single<List<VM_PowerSupplyNetwork>> getPowerSupply(VM_FilterPowerSupplyNetwork filter, List<VM_ProvincesAndCities> provincesAndCities, List<VM_WorkExperience> workExperiences) {
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

                                    if (!object.getString("Id").equalsIgnoreCase("null"))
                                        supplyNetwork.setId(object.getInt("Id"));

                                    if (!object.getString("Phone").equalsIgnoreCase("null"))
                                        supplyNetwork.setCellPhone(Replace.Number_en_To_fa(object.getString("Phone")));

                                    if (!object.getString("PicsAd").equalsIgnoreCase("null"))
                                        supplyNetwork.setImage(ImageAd + object.getString("PicsAd"));

                                    if (!object.getString("DateInsert").equalsIgnoreCase("null"))
                                        supplyNetwork.setDate(Replace.Number_en_To_fa(object.getString("DateInsert")));

                                    if (!object.getString("Special").equalsIgnoreCase("null"))
                                        supplyNetwork.setSpecial(object.getBoolean("Special"));

                                    if (!object.getString("JobTitle").equalsIgnoreCase("null")) {
                                        supplyNetwork.setJobTitle(object.getString("JobTitle"));
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

                                    supplyNetwork.setName(Replace.Number_en_To_fa(object.getString("Name")));

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
    public Single<List<VM_Machinery>> getMachineries(VM_FilterMachinery filter, List<VM_ProvincesAndCities> provincesAndCities) {
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
                                        if (!object.getString("MachineryTitle").equalsIgnoreCase("null")) {
                                            machinery.setMachineryTitle(object.getString("MachineryTitle"));
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
                                            machinery.setPrice(Replace.Number_en_To_fa(object.getString("Price")));
                                        }

                                        //شماره موبایل
                                        if (!object.getString("Phone").equalsIgnoreCase("null")) {
                                            machinery.setCellPhone(Replace.Number_en_To_fa(object.getString("Phone")));
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
                                            machinery.setDate(Replace.Number_en_To_fa(object.getString("DateInsert")));
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
     * در اینجا لیست مصالح گرفته می شود
     **/
    public Single<List<VM_Material>> getMaterials(VM_FilterMaterial filter, List<VM_ProvincesAndCities> provincesAndCities) {
        return Single.create(emitter -> {
            new Thread(() -> {
                try {

                    JSONObject input = new JSONObject();
                    try {
                        input.put("State", filter.getStateId());
                        input.put("City", filter.getCityId());
                        input.put("Materials", filter.getMaterialId());
                        input.put("Paging", filter.getPaging());
                    } catch (Exception e) {
                    }

                    volley_getMaterials = new PostJsonObject_And_GetJsonArrayVolley(ApiUrl + "Advertising/PostAdMaterials", input, resault -> {
                        if (resault.getResault() == ResaultCode.Success) {

                            List<VM_Material> vals = new ArrayList<>();
                            JSONArray array = resault.getJsonArray();

                            for (int i = 0; i < array.length(); i++) {
                                VM_Material material = new VM_Material();

                                try {

                                    JSONObject object = array.getJSONObject(i);

                                    //آیدی
                                    material.setId(object.getInt("Id"));

                                    //عکس
                                    material.setImage(ImageAd + object.getString("PicsAd"));

                                    //تایتل مصالح گرفته می شود
                                    if (!object.getString("MaterialsTitle").equalsIgnoreCase("null"))
                                        material.setMaterial(object.getString("MaterialsTitle"));

                                    //نوع آگهی
                                    if (object.getBoolean("AdType")) {
                                        material.setAdType(AdTypeMaterial.Sales);
                                    } else {
                                        material.setAdType(AdTypeMaterial.Buy);
                                    }

                                    //در اینجا قیمت ست می شود
                                    if (!object.getString("Price").equalsIgnoreCase("null")) {
                                        material.setPrice(Replace.Number_en_To_fa(object.getString("Price")));
                                    }

                                    //شماره موبایل
                                    if (!object.getString("Phone").equalsIgnoreCase("null")) {
                                        material.setCellPhone(Replace.Number_en_To_fa(object.getString("Phone")));
                                    }

                                    //استان
                                    if (object.getInt("State") != 0) {
                                        int stateId = object.getInt("State");
                                        for (VM_ProvincesAndCities j : provincesAndCities) {
                                            if (j.getId() == stateId) {
                                                material.setProvinceAndCity(j.getTitle());
                                                break;
                                            }
                                        }
                                    }

                                    //زمان
                                    if (!object.getString("DateInsert").equalsIgnoreCase("null")) {
                                        material.setDate(Replace.Number_en_To_fa(object.getString("DateInsert")));
                                    }

                                } catch (Exception e) {
                                } finally {
                                    vals.add(material);
                                }
                            }

                            emitter.onSuccess(vals);

                        } else {
                            if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                                postError("Api_PowerSupply->getMaterials", resault.getMessage());
                            }
                            emitter.onError(new IOException(resault.getResault().toString()));
                        }
                    });

                } catch (Exception e) {
                    postError("Api_PowerSupply->getMaterials", e.toString());
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
                                detailPowerSupply.setDateInsert(Replace.Number_en_To_fa(object.getString("DateInsert")));

                            if (!object.getString("Name").equalsIgnoreCase("null"))
                                detailPowerSupply.setName(Replace.Number_en_To_fa(object.getString("Name")));

                            if (!object.getString("AdType").equalsIgnoreCase("null")) {
                                if (object.getBoolean("AdType")) {
                                    detailPowerSupply.setAdType(AdType.presentation);
                                } else {
                                    detailPowerSupply.setAdType(AdType.request);
                                }
                            }

                            if (!object.getString("JobTitle").equalsIgnoreCase("null"))
                                detailPowerSupply.setJobTitle(object.getString("JobTitle"));

                            if (!object.getString("Job").equalsIgnoreCase("null"))
                                detailPowerSupply.setJobId(object.getInt("Job"));

                            if (!object.getString("WorkExperience").equalsIgnoreCase("null"))
                                detailPowerSupply.setWorkExperienceId(object.getInt("WorkExperience"));

                            if (!object.getString("State").equalsIgnoreCase("null"))
                                detailPowerSupply.setStateId(object.getInt("State"));

                            if (!object.getString("City").equalsIgnoreCase("null"))
                                detailPowerSupply.setCityId(object.getInt("City"));

                            if (!object.getString("Phone").equalsIgnoreCase("null"))
                                detailPowerSupply.setPhone(Replace.Number_en_To_fa(object.getString("Phone")));

                            if (!object.getString("Description").equalsIgnoreCase("null"))
                                detailPowerSupply.setDescription(Replace.Number_en_To_fa(object.getString("Description")));

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
                                detailMachinery.setTitle(Replace.Number_en_To_fa(object.getString("Title")));
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

                            //تایتل ماشین آلات
                            if (!object.getString("MachineryTitle").equalsIgnoreCase("null")) {
                                detailMachinery.setMachineryTitle(object.getString("MachineryTitle"));
                            }

                            //آیدی ماشین آلات
                            if (!object.getString("Machinery").equalsIgnoreCase("null")) {
                                detailMachinery.setMachineryId(object.getInt("Machinery"));
                            }

                            //قیمت
                            if (!object.getString("Price").equalsIgnoreCase("null")) {
                                detailMachinery.setPrice(Replace.Number_en_To_fa(object.getString("Price")));
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
                                detailMachinery.setCellPhone(Replace.Number_en_To_fa(object.getString("Phone")));
                            }

                            //توضیحات
                            if (!object.getString("Description").equalsIgnoreCase("null")) {
                                detailMachinery.setDescription(Replace.Number_en_To_fa(object.getString("Description")));
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
     * در اینجا جزئیات مصالح برگشت داده می شود
     **/
    public Single<VM_DetailMaterial> getDetailMaterial(int id) {
        return Single.create(emitter -> {
            new Thread(() -> {
                volley_getDetailMaterial = new GetJsonObjectVolley(ApiUrl + "Advertising/GetAdMaterialsInfo?Id=" + id, resault -> {

                    if (resault.getResault() == ResaultCode.Success) {

                        VM_DetailMaterial material = new VM_DetailMaterial();
                        JSONObject object = resault.getObject();

                        try {

                            material.setId(id);

                            //تایتل مصالح
                            if (!object.getString("MaterialsTitle").equalsIgnoreCase("null")) {
                                material.setMaterialsTitle(object.getString("MaterialsTitle"));
                            }

                            //تایتل
                            if (!object.getString("Title").equalsIgnoreCase("null")) {
                                material.setTitle(Replace.Number_en_To_fa(object.getString("Title")));
                            }

                            //نوع آگهی
                            if (object.getBoolean("AdType")) {
                                material.setAdTypeMaterial(AdTypeMaterial.Sales);
                            } else {
                                material.setAdTypeMaterial(AdTypeMaterial.Buy);
                            }

                            //آیدی مصالح
                            if (!object.getString("Materials").equalsIgnoreCase("null")) {
                                material.setMaterialId(object.getInt("Materials"));
                            }

                            //قیمت
                            if (!object.getString("Price").equalsIgnoreCase("null")) {
                                material.setPrice(Replace.Number_en_To_fa(object.getString("Price")));
                            }

                            //استان
                            if (!object.getString("State").equalsIgnoreCase("null")) {
                                material.setStateId(object.getInt("State"));
                            }

                            //شهر
                            if (!object.getString("City").equalsIgnoreCase("null")) {
                                material.setCityId(object.getInt("City"));
                            }

                            //شماره موبایل
                            if (!object.getString("Phone").equalsIgnoreCase("null")) {
                                material.setCellPhone(Replace.Number_en_To_fa(object.getString("Phone")));
                            }

                            //توضیحات
                            if (!object.getString("Description").equalsIgnoreCase("null")) {
                                material.setDescription(Replace.Number_en_To_fa(object.getString("Description")));
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
                            material.setImages(images);

                        } catch (Exception e) {
                        } finally {
                            emitter.onSuccess(material);
                        }

                    } else {
                        if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                            postError("Api_PowerSupply->getDetailMaterial", resault.getMessage());
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

                    input.put("JobName", powerSupply.getJobTitle());
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

                    //دسته ماشین آلات
                    input.put("MachineryName", machinery.getMachineryName());

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
     * در اینجا مصالح اضافه می شود
     **/
    public Single<VM_Message> addMaterial(VM_PostMaterial material) {
        return Single.create(emitter -> {
            new Thread(() -> {
                JSONObject input = new JSONObject();

                try {
                    //آیدی کاربر
                    input.put("UserId", material.getUserId());

                    //عنوان
                    input.put("Title", material.getTitle());

                    //نوع آگهی
                    if (material.getAdType().getAdType() == AdTypeMaterial.Sales) {
                        input.put("AdType", true);
                    } else {
                        input.put("AdType", false);
                    }

                    //آیدی دسته مصالح
                    input.put("MaterialsName", material.getMaterialTitle());

                    //قیمت
                    input.put("Price", material.getPrice().replace(",", "").replace("٬", ""));

                    //استان
                    input.put("State", material.getState());

                    //شهر
                    input.put("City", material.getCity());

                    //شماره موبایل
                    input.put("Phone", material.getCellPhone());

                    //توضیحات
                    input.put("Description", material.getDescription());

                    //در اینجا عکس ها ست می شوند
                    JSONArray imagesArray = new JSONArray();
                    if (material.getImages().size() > 0) {
                        List<String> array = material.getImages();
                        for (int i = 0; i < array.size(); i++) {
                            imagesArray.put(array.get(i));
                        }
                    } else {
                        imagesArray.put("1");
                    }
                    input.put("PicsAd", imagesArray);

                } catch (Exception e) {
                }

                volley_addMaterial = new PostJsonObjectVolley(ApiUrl + "Advertising/PostAdAddMaterials", input, resault -> {
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
                            postError("Api_PowerSupply->addMaterial", resault.getMessage());
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
                                    adUpgrade.setDescription(Replace.Number_en_To_fa(object.getString("Description")));
                                    adUpgrade.setPrice(Replace.Number_en_To_fa(object.getString("Price")));
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
    public Single<List<VM_PowerSupplyNetwork>> getMyPowerSupplyNetwork(int userId, List<VM_ProvincesAndCities> provincesAndCities, List<VM_WorkExperience> workExperiences) {
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

                                    if (!object.getString("Id").equalsIgnoreCase("null"))
                                        supplyNetwork.setId(object.getInt("Id"));

                                    if (!object.getString("Phone").equalsIgnoreCase("null"))
                                        supplyNetwork.setCellPhone(Replace.Number_en_To_fa(object.getString("Phone")));

                                    if (!object.getString("PicsAd").equalsIgnoreCase("null"))
                                        supplyNetwork.setImage(ImageAd + object.getString("PicsAd"));

                                    if (!object.getString("DateInsert").equalsIgnoreCase("null"))
                                        supplyNetwork.setDate(Replace.Number_en_To_fa(object.getString("DateInsert")));

                                    if (!object.getString("Special").equalsIgnoreCase("null"))
                                        supplyNetwork.setSpecial(object.getBoolean("Special"));

                                    if (!object.getString("JobTitle").equalsIgnoreCase("null"))
                                        supplyNetwork.setJobTitle(object.getString("JobTitle"));

                                    if (!object.getString("State").equalsIgnoreCase("null"))
                                        if (object.getInt("State") != 0) {
                                            int stateId = object.getInt("State");

                                            for (int j = 0; j < provincesAndCities.size(); j++) {
                                                if (provincesAndCities.get(j).getId() == stateId) {
                                                    supplyNetwork.setProvinceAndCity(provincesAndCities.get(j).getTitle());
                                                    break;
                                                }
                                            }
                                        }

                                    if (!object.getString("WorkExperience").equalsIgnoreCase("null"))
                                        if (object.getInt("WorkExperience") != 0) {
                                            int workExperience = object.getInt("WorkExperience");

                                            for (int j = 0; j < workExperiences.size(); j++) {
                                                if (workExperiences.get(j).getId() == workExperience) {
                                                    supplyNetwork.setWorkExperience(workExperiences.get(j).getTitle());
                                                    break;
                                                }
                                            }
                                        }

                                    if (!object.getString("Name").equalsIgnoreCase("null"))
                                        supplyNetwork.setName(Replace.Number_en_To_fa(object.getString("Name")));

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

    public Single<List<VM_Machinery>> getMyMachineries(int userId, List<VM_ProvincesAndCities> provincesAndCities) {
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

                                    if (!object.getString("Id").equalsIgnoreCase("null"))
                                        machinery.setId(object.getInt("Id"));

                                    if (!object.getString("PicsAd").equalsIgnoreCase("null"))
                                        machinery.setImage(ImageAd + object.getString("PicsAd"));

                                    //در اینجا تایتل ماشین آلات ست می شود
                                    if (!object.getString("MachineryTitle").equalsIgnoreCase("null")) {
                                        machinery.setMachineryTitle(object.getString("MachineryTitle"));
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
                                        machinery.setPrice(Replace.Number_en_To_fa(object.getString("Price")));
                                    }

                                    //شماره موبایل
                                    if (!object.getString("Phone").equalsIgnoreCase("null")) {
                                        machinery.setCellPhone(Replace.Number_en_To_fa(object.getString("Phone")));
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
                                        machinery.setDate(Replace.Number_en_To_fa(object.getString("DateInsert")));
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
     * در اینجا مصالح من برگشت داده می شود
     **/
    public Single<List<VM_Material>> getMyMaterials(int userId, List<VM_ProvincesAndCities> provincesAndCities) {
        return Single.create(emitter -> {
            new Thread(() -> {
                volley_getMyMaterials = new GetJsonArrayVolley(ApiUrl + "Advertising/GetMeAdMaterials?UserId=" + userId, resault -> {
                    if (resault.getResault() == ResaultCode.Success) {

                        List<VM_Material> vals = new ArrayList<>();
                        JSONArray array = resault.getJsonArray();

                        for (int i = 0; i < array.length(); i++) {
                            VM_Material material = new VM_Material();

                            try {

                                JSONObject object = array.getJSONObject(i);

                                //آیدی
                                if (!object.getString("Id").equalsIgnoreCase("null"))
                                    material.setId(object.getInt("Id"));

                                //عکس
                                if (!object.getString("PicsAd").equalsIgnoreCase("null"))
                                    material.setImage(ImageAd + object.getString("PicsAd"));

                                //تایتل مصالح گرفته می شود
                                if (!object.getString("MaterialsTitle").equalsIgnoreCase("null"))
                                    material.setMaterial(object.getString("MaterialsTitle"));

                                //نوع آگهی
                                if (!object.getString("AdType").equalsIgnoreCase("null"))
                                    if (object.getBoolean("AdType")) {
                                        material.setAdType(AdTypeMaterial.Sales);
                                    } else {
                                        material.setAdType(AdTypeMaterial.Buy);
                                    }

                                //در اینجا قیمت ست می شود
                                if (!object.getString("Price").equalsIgnoreCase("null")) {
                                    material.setPrice(Replace.Number_en_To_fa(object.getString("Price")));
                                }

                                //شماره موبایل
                                if (!object.getString("Phone").equalsIgnoreCase("null")) {
                                    material.setCellPhone(Replace.Number_en_To_fa(object.getString("Phone")));
                                }

                                //استان
                                if (object.getInt("State") != 0) {
                                    int stateId = object.getInt("State");
                                    for (VM_ProvincesAndCities j : provincesAndCities) {
                                        if (j.getId() == stateId) {
                                            material.setProvinceAndCity(j.getTitle());
                                            break;
                                        }
                                    }
                                }

                                //زمان
                                if (!object.getString("DateInsert").equalsIgnoreCase("null")) {
                                    material.setDate(Replace.Number_en_To_fa(object.getString("DateInsert")));
                                }

                            } catch (Exception e) {
                            } finally {
                                vals.add(material);
                            }
                        }

                        emitter.onSuccess(vals);

                    } else {
                        if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                            postError("Api_PowerSupply->getMyMaterials", resault.getMessage());
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
                            if (!object.getString("WorkExperience").equalsIgnoreCase("null"))
                                model.setWorkExperiences(object.getInt("WorkExperience"));

                            //استان
                            if (!object.getString("State").equalsIgnoreCase("null"))
                                model.setState(object.getInt("State"));

                            //شهر
                            if (!object.getString("City").equalsIgnoreCase("null"))
                                model.setCity(object.getInt("City"));

                            //شغل
                            if (!object.getString("JobTitle").equalsIgnoreCase("null"))
                                model.setJobTitle(object.getString("JobTitle"));

                            //نام
                            if (!object.getString("Name").equalsIgnoreCase("null"))
                                model.setName(Replace.Number_en_To_fa(object.getString("Name")));

                            //شماره تماس
                            if (!object.getString("Phone").equalsIgnoreCase("null"))
                                model.setCellPhone(Replace.Number_en_To_fa(object.getString("Phone")));

                            //توضیحات
                            if (!object.getString("Description").equalsIgnoreCase("null"))
                                model.setDescription(Replace.Number_en_To_fa(object.getString("Description")));

                            //ویژه
                            if (!object.getString("Special").equalsIgnoreCase("null"))
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

                            //دسته ماشین آلات
                            if (!object.getString("MachineryTitle").equalsIgnoreCase("null"))
                                model.setMachineryName(object.getString("MachineryTitle"));

                            //استان
                            if (!object.getString("State").equalsIgnoreCase("null"))
                                model.setState(object.getInt("State"));

                            //شهر
                            if (!object.getString("City").equalsIgnoreCase("null"))
                                model.setCity(object.getInt("City"));

                            //قیمت
                            if (!object.getString("Price").equalsIgnoreCase("null")) {
                                String price = Replace.Number_en_To_fa(object.getString("Price"))
                                        .replace(context.getString(R.string.Toman), "")
                                        .replace(",", "").replace("٬", "").trim();
                                model.setPrice(price);
                            }

                            //شماره تماس
                            if (!object.getString("Phone").equalsIgnoreCase("null"))
                                model.setCellPhone(Replace.Number_en_To_fa(object.getString("Phone")));

                            //توضیحات
                            if (!object.getString("Description").equalsIgnoreCase("null"))
                                model.setDescription(Replace.Number_en_To_fa(object.getString("Description")));

                            //ویژه
                            if (!object.getString("Special").equalsIgnoreCase("null"))
                                model.setSpecial(object.getBoolean("Special"));

                            //عنوان
                            if (!object.getString("Title").equalsIgnoreCase("null"))
                                model.setTitle(Replace.Number_en_To_fa(object.getString("Title")));

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

    /**
     * در اینجا جزئیات مصالح من گرفته می شود
     **/
    public Single<VM_PostMaterial> getDetailMyMaterial(int id, Context context) {
        return Single.create(emitter -> {
            new Thread(() -> {
                volley_getDetailMyMaterial = new GetJsonObjectVolley(ApiUrl + "Advertising/GetAdMaterialsInfo?Id=" + id, resault -> {
                    if (resault.getResault() == ResaultCode.Success) {

                        VM_PostMaterial material = new VM_PostMaterial();
                        JSONObject object = resault.getObject();

                        try {

                            //نوع آگهی
                            if (!object.getString("AdType").equalsIgnoreCase("null"))
                                if (object.getBoolean("AdType")) {
                                    material.setAdType(new VM_AdTypeMaterial(AdTypeMaterial.Sales, context.getString(R.string.Sales)));
                                } else {
                                    material.setAdType(new VM_AdTypeMaterial(AdTypeMaterial.Buy, context.getString(R.string.Buy)));
                                }

                            //دسته مصالح
                            if (!object.getString("MaterialsTitle").equalsIgnoreCase("null"))
                                material.setMaterialTitle(object.getString("MaterialsTitle"));

                            //استان
                            if (!object.getString("State").equalsIgnoreCase("null"))
                                material.setState(object.getInt("State"));

                            //شهر
                            if (!object.getString("City").equalsIgnoreCase("null"))
                                material.setCity(object.getInt("City"));

                            //قیمت
                            if (!object.getString("Price").equalsIgnoreCase("null"))
                                material.setPrice(Replace.Number_en_To_fa(object.getString("Price").replace(context.getString(R.string.Toman), "")
                                        .replace(",", "").replace("٬", "").trim()));

                            //شماره تماس
                            if (!object.getString("Phone").equalsIgnoreCase("null"))
                                material.setCellPhone(Replace.Number_en_To_fa(object.getString("Phone")));

                            //توضیحات
                            if (!object.getString("Description").equalsIgnoreCase("null"))
                                material.setDescription(Replace.Number_en_To_fa(object.getString("Description")));

                            //ویژه
                            if (!object.getString("Special").equalsIgnoreCase("null"))
                                material.setSpecial(object.getBoolean("Special"));

                            //عنوان
                            if (!object.getString("Title").equalsIgnoreCase("null"))
                                material.setTitle(Replace.Number_en_To_fa(object.getString("Title")));

                            //در اینجا وضعیت سفارش گرفته می شود
                            if (!object.getString("IsActive").equalsIgnoreCase("null")) {
                                material.setStepPower(object.getBoolean("IsActive") ? StepsAddPower.Post_an_Ad : StepsAddPower.Check_The_Ad);
                            }

                            //مربوط به عکس
                            JSONArray images = object.getJSONArray("AllPicsAd");
                            for (int i = 0; i < images.length(); i++) {
                                try {
                                    String url = ImageAd + images.get(i).toString();
                                    material.getImages().add(url);
                                } catch (Exception e) {
                                }
                            }

                        } catch (Exception e) {
                        }

                        emitter.onSuccess(material);

                    } else {
                        if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                            postError("Api_PowerSupply->getDetailMyMaterial", resault.getMessage());
                        }
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }
                });
            }).start();
        });
    }

    /**
     * در اینجا شغل ها ازسرور گرفته می شود
     **/
    public Single<List<VM_Job>> getJobs(Context context) {
        return Single.create(emitter -> {
            new Thread(() -> {
                volley_getJobs = new GetJsonArrayVolley(ApiUrl + "Advertising/GetJobs", resault -> {
                    if (resault.getResault() == ResaultCode.Success) {

                        List<VM_Job> jobs = new ArrayList<>();

                        try {

                            JSONArray array = resault.getJsonArray();

                            jobs.add(new VM_Job(0, context.getString(R.string.job)));

                            for (int i = 0; i < array.length(); i++) {
                                try {

                                    JSONObject object = array.getJSONObject(i);
                                    VM_Job job = new VM_Job();

                                    job.setId(object.getInt("Id"));
                                    job.setTitle(object.getString("Title"));

                                    jobs.add(job);

                                } catch (Exception e) {
                                }
                            }

                        } catch (Exception e) {
                        }

                        emitter.onSuccess(jobs);
                    } else {
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }
                });
            }).start();
        });
    }

    /**
     * در اینجا تایتل شغل ها از سرور گرفته می شود
     **/
    public Single<String[]> getJobsTitle() {
        return Single.create(emitter -> {
            new Thread(() -> {
                volley_getJobsTitle = new GetJsonArrayVolley(ApiUrl + "Advertising/GetJobs", resault -> {
                    if (resault.getResault() == ResaultCode.Success) {
                        String[] vals = new String[resault.getJsonArray().length()];

                        try {

                            JSONArray array = resault.getJsonArray();

                            for (int i = 0; i < array.length(); i++) {
                                try {
                                    vals[i] = array.getJSONObject(i).getString("Title");
                                } catch (Exception e) {
                                }
                            }

                        } catch (Exception e) {
                        }

                        emitter.onSuccess(vals);

                    } else {
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }
                });
            }).start();
        });
    }

    /**
     * در اینجا لیست دسته ماشین آلات از سرور گرفته می شود
     **/
    public Single<List<VM_MachinerySpinner>> getMachinerySpinner(Context context) {
        return Single.create(emitter -> {
            new Thread(() -> {
                volley_getMachinerySpinner = new GetJsonArrayVolley(ApiUrl + "Advertising/GetMachinery", resault -> {
                    if (resault.getResault() == ResaultCode.Success) {

                        List<VM_MachinerySpinner> machinerySpinners = new ArrayList<>();

                        try {

                            JSONArray array = resault.getJsonArray();

                            machinerySpinners.add(new VM_MachinerySpinner(0, context.getString(R.string.Machinery2)));

                            for (int i = 0; i < array.length(); i++) {
                                try {
                                    JSONObject object = array.getJSONObject(i);
                                    VM_MachinerySpinner machinerySpinner = new VM_MachinerySpinner();

                                    machinerySpinner.setId(object.getInt("Id"));
                                    machinerySpinner.setTitle(object.getString("Title"));

                                    machinerySpinners.add(machinerySpinner);

                                } catch (Exception e) {
                                }
                            }

                        } catch (Exception e) {
                        }

                        emitter.onSuccess(machinerySpinners);

                    } else {
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }
                });
            }).start();
        });
    }

    /**
     * در اینجا لیست تایتل ماشین آلات از سرور گرفته می شود
     **/
    public Single<String[]> getMachinerysTitle() {
        return Single.create(emitter -> {
            new Thread(() -> {
                volley_getMachinerysTitle = new GetJsonArrayVolley(ApiUrl + "Advertising/GetMachinery", resault -> {
                    if (resault.getResault() == ResaultCode.Success) {

                        String[] vals = new String[resault.getJsonArray().length()];

                        try {
                            JSONArray array = resault.getJsonArray();

                            for (int i = 0; i < array.length(); i++) {
                                try {
                                    vals[i] = array.getJSONObject(i).getString("Title");
                                } catch (Exception e) {
                                }
                            }

                        } catch (Exception e) {
                        }

                        emitter.onSuccess(vals);

                    } else {
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }
                });
            }).start();
        });
    }

    /**
     * در اینجا لیست مصالح گرفته می شود
     **/
    public Single<List<VM_MaterialSpinner>> getMaterialSpinner(Context context) {
        return Single.create(emitter -> {
            new Thread(() -> {
                volley_getMaterialSpinner = new GetJsonArrayVolley(ApiUrl + "Advertising/GetMaterials", resault -> {

                    if (resault.getResault() == ResaultCode.Success) {

                        List<VM_MaterialSpinner> materialSpinners = new ArrayList<>();

                        try {

                            JSONArray array = resault.getJsonArray();
                            materialSpinners.add(new VM_MaterialSpinner(0, context.getString(R.string.Material)));

                            for (int i = 0; i < array.length(); i++) {
                                try {
                                    JSONObject object = array.getJSONObject(i);
                                    VM_MaterialSpinner materialSpinner = new VM_MaterialSpinner();

                                    materialSpinner.setId(object.getInt("Id"));
                                    materialSpinner.setTitle(object.getString("Title"));

                                    materialSpinners.add(materialSpinner);

                                } catch (Exception e) {
                                }
                            }

                        } catch (Exception e) {
                        }

                        emitter.onSuccess(materialSpinners);

                    } else {
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }

                });
            }).start();
        });
    }

    /**
     * در اینجا لیست تایتل مصالح از سرور گرفته می شود
     * **/
    public Single<String[]> getMaterialsTitle() {
        return Single.create(emitter -> {
            new Thread(() -> {
                volley_getMaterialsTitle = new GetJsonArrayVolley(ApiUrl + "Advertising/GetMaterials", resault -> {
                    if (resault.getResault() == ResaultCode.Success) {

                        String[] vals=new String[resault.getJsonArray().length()];

                        try {
                            JSONArray array = resault.getJsonArray();

                            for (int i = 0; i < array.length(); i++) {
                                try {
                                    vals[i] = array.getJSONObject(i).getString("Title");
                                } catch (Exception e) {
                                }
                            }

                        } catch (Exception e) {
                        }

                        emitter.onSuccess(vals);

                    } else {
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

        if (volley_getMaterials != null) {
            volley_getMaterials.Cancel(tag, context);
        }

        if (volley_getDetailMaterial != null) {
            volley_getDetailMaterial.Cancel(tag, context);
        }

        if (volley_getMyMaterials != null) {
            volley_getMyMaterials.Cancel(tag, context);
        }

        if (volley_addMaterial != null) {
            volley_addMaterial.Cancel(tag, context);
        }

        if (volley_getDetailMyMaterial != null) {
            volley_getDetailMyMaterial.Cancel(tag, context);
        }

        if (volley_getJobs != null) {
            volley_getJobs.Cancel(tag, context);
        }

        if (volley_getMachinerySpinner != null) {
            volley_getMachinerySpinner.Cancel(tag, context);
        }

        if (volley_getMaterialSpinner != null) {
            volley_getMaterialSpinner.Cancel(tag, context);
        }

        if (volley_getMachinerysTitle != null) {
            volley_getMachinerysTitle.Cancel(tag, context);
        }

        if (volley_getJobsTitle != null) {
            volley_getJobsTitle.Cancel(tag, context);
        }

        if (volley_getMaterialsTitle != null) {
            volley_getMaterialsTitle.Cancel(tag, context);
        }
    }

}
