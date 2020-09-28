package ir.tdaapp.paymanyar.Model.Repositorys.Server;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.li_volley.Volleys.PostJsonObject_And_GetJsonArrayVolley;
import ir.tdaapp.paymanyar.Model.Utilitys.Base_Api;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FilterPowerSupplyNetwork;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Job;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PowerSupplyNetwork;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_ProvincesAndCities;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_WorkExperience;

/**
 * مربوط به نیروکار
 **/
public class Api_PowerSupply extends Base_Api {

    PostJsonObject_And_GetJsonArrayVolley volley_getPowerSupply;

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

                                        for (int j=0;j<workExperiences.size();j++){
                                            if (workExperiences.get(j).getId()==workExperience){
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
                            emitter.onError(new IOException(resault.getResault().toString()));
                        }
                    });

                } catch (Exception e) {
                    emitter.onError(e);
                }

            }).start();
        });
    }

    public void cancel(String tag, Context context) {
        if (volley_getPowerSupply != null) {
            volley_getPowerSupply.Cancel(tag, context);
        }
    }

}
