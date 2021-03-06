package ir.tdaapp.paymanyar.Model.Repositorys.Server;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;

import org.json.JSONArray;
import org.json.JSONException;
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
import ir.tdaapp.li_utility.Codes.Replace;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.li_volley.Volleys.DeleteJsonObjectVolley;
import ir.tdaapp.li_volley.Volleys.GetJsonObjectVolley;
import ir.tdaapp.li_volley.Volleys.PostJsonArrayVolley;
import ir.tdaapp.li_volley.Volleys.PostJsonObjectVolley;
import ir.tdaapp.li_volley.Volleys.PostJsonObject_And_GetJsonArrayVolley;
import ir.tdaapp.paymanyar.Model.Enums.OrderKind;
import ir.tdaapp.paymanyar.Model.Enums.StepsAnalizeTender;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Tender;
import ir.tdaapp.paymanyar.Model.Services.onUploadFiles;
import ir.tdaapp.paymanyar.Model.Utilitys.Base_Api;
import ir.tdaapp.paymanyar.Model.Utilitys.FileManger;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_AnaliseInfo;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailsTender;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FilterOrder;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FilterTenderNotification;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_InputAnalizeTender;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Let_me_know;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Orders;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotification;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotifications;

public class Api_Tender extends Base_Api {

    PostJsonObjectVolley volley_getTenderNotification, volley_getDetailsTender, volley_postLetMeKnow, volley_getTendersFilter, volley_postOrderAnalize;
    PostJsonObjectVolley volley_postOrderScheduling, volley_postOrderAudit, volley_postOrderCostEstimation, volley_postOrderDifference;
    PostJsonArrayVolley volley_getFavorites;
    PostJsonObject_And_GetJsonArrayVolley volley_getOrders;
    GetJsonObjectVolley volley_getOrderAnaliseInfo;
    DeleteJsonObjectVolley volley_deleteOrder;
    boolean workingDownloadFile = true;

    //زمانی که کاربر درحال آپلود فایل باشد مقدار زیر ترو خواهد شد
    boolean isUploadedFile = false;

    //در اینجا اطلاع رسانی مناقصات برگشت داده می شود
    public Single<VM_TenderNotification> getTenderNotification(VM_FilterTenderNotification filter, Tbl_Tender tbl_tender) {

        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    JSONObject input = new JSONObject();

                    try {

                        input.put("StateId", filter.getCityId());
                        input.put("FieldId", filter.getMajorId());
                        input.put("Word", filter.getIncludesTheWord());
                        input.put("FromDate", filter.getDate());
                        input.put("FromFeeId", filter.getFromEstimateId());
                        input.put("UpFeeId", filter.getUntilEstimateId());
                        input.put("Paging", filter.getPage());

                    } catch (Exception e) {

                    }


                    volley_getTenderNotification = new PostJsonObjectVolley(ApiUrl + "Tender/PostTender", input, resault -> {

                        if (resault.getResault() == ResaultCode.Success) {

                            List<VM_TenderNotifications> notifications = new ArrayList<>();
                            VM_TenderNotification notification = new VM_TenderNotification();
                            JSONArray array = new JSONArray();

                            try {
                                array = resault.getObject().getJSONArray("Tenders");
                                notification.setCountTenders(resault.getObject().getInt("CountTender"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            for (int i = 0; i < array.length(); i++) {

                                try {

                                    VM_TenderNotifications n = new VM_TenderNotifications();
                                    JSONObject object = array.getJSONObject(i);

                                    n.setId(object.getString("TenderId"));
                                    n.setTitle(Replace.Number_en_To_fa(object.getString("Title")));
                                    n.setFree(object.getBoolean("Free"));
                                    n.setStar(tbl_tender.isFavoritTender(object.getString("TenderId")));

                                    notifications.add(n);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            notification.setTenderNotifications(notifications);

                            emitter.onSuccess(notification);

                        } else {
                            if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                                postError("Api_Tender->getTenderNotification", resault.getMessage());
                            }
                            emitter.onError(new IOException(resault.getResault().toString()));
                        }

                    });

                } catch (Exception e) {
                    postError("Api_Tender->getTenderNotification", e.toString());
                    emitter.onError(e);
                }

            }).start();

        });

    }

    //در اینجا جزئیات مناقصات برگشت داده می شود
    public Single<VM_DetailsTender> getDetailsTender(VM_FilterTenderNotification filter, Tbl_Tender tbl_tender) {

        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    JSONObject obg = new JSONObject();

                    try {

                        obg.put("StateId", filter.getCityId());
                        obg.put("FieldId", filter.getMajorId());
                        obg.put("FromFeeId", filter.getFromEstimateId());
                        obg.put("UpFeeId", filter.getUntilEstimateId());
                        obg.put("UserId", filter.getUserId());
                        obg.put("TenderId", filter.getTenderId());
                        obg.put("ApiKey", filter.getApiKey());

                        if (filter.getDate() != null) {
                            obg.put("FromDate", filter.getDate());
                        } else {
                            obg.put("FromDate", "");
                        }

                        if (filter.getIncludesTheWord() != null) {
                            obg.put("Word", filter.getIncludesTheWord());
                        } else {
                            obg.put("Word", "");
                        }

                    } catch (Exception e) {
                    }

                    volley_getDetailsTender = new PostJsonObjectVolley(ApiUrl + "Tender/PostTenderInfo", obg, resault -> {

                        if (resault.getResault() == ResaultCode.Success) {

                            VM_DetailsTender detailsTender = new VM_DetailsTender();

                            try {

                                JSONObject object = resault.getObject();

                                detailsTender.setStatus(object.getBoolean("Status"));
                                detailsTender.setId(object.getInt("TenderId"));
                                detailsTender.setDescription(object.getString("Description"));
                                detailsTender.setGetDocumentsUp(object.getString("DateReceiveDocument"));
                                detailsTender.setNationalEstimate(object.getLong("Fee") + "");
                                detailsTender.setPlace_of_Receipt_of_Documents(object.getString("LocationOfReceiveDocument"));
                                detailsTender.setReopeningDate(object.getString("DateOpen"));
                                detailsTender.setSendSuggestionsUp(object.getString("DateExpireSuggest"));
                                detailsTender.setTenderDevice(object.getString("Organization"));
                                detailsTender.setWebsite(object.getString("WebSite"));
                                detailsTender.setNextTenderId(object.getString("NextTenderId"));
                                detailsTender.setBeforeTenderId(object.getString("BeforeTenderId"));
                                detailsTender.setFevorit(tbl_tender.isFavoritTender(filter.getTenderId()));
                                detailsTender.setCityId(object.getInt("StateId"));
                                detailsTender.setMajorId(object.getInt("FieldId"));
                                detailsTender.setTitle(object.getString("Title"));

                            } catch (Exception e) {
                            }

                            emitter.onSuccess(detailsTender);

                        } else {
                            if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                                postError("Api_Tender->getDetailsTender", resault.getMessage());
                            }
                            emitter.onError(new IOException(resault.getResault().toString()));
                        }

                    });

                } catch (Exception e) {
                    postError("Api_Tender->getDetailsTender", e.toString());
                    emitter.onError(e);
                }

            }).start();

        });

    }

    //در اینجا داده ها برای باخبرم کن به سرور پاس داده می شوند
    public Single<VM_Message> postLetMeKnow(VM_Let_me_know filter) {

        return Single.create(emitter -> {

            new Thread(() -> {

                JSONObject object = new JSONObject();

                try {

                    object.put("FkState", filter.getCityId());
                    object.put("FkField", filter.getMajorId());
                    object.put("FkMinPrice", filter.getFromEstimateId());
                    object.put("FkMaxPrice", filter.getUntilEstimateId());
                    object.put("ApiKey", filter.getToken());

                } catch (Exception e) {

                }

                volley_postLetMeKnow = new PostJsonObjectVolley(ApiUrl + "Tender/PostLetMeKnow", object, resault -> {

                    if (resault.getResault() == ResaultCode.Success) {

                        VM_Message message = new VM_Message();
                        JSONObject obj = resault.getObject();

                        try {

                            message.setCode(obj.getInt("Code"));
                            message.setMessage(obj.getString("MessageText"));
                            message.setResult(obj.getBoolean("Result"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        emitter.onSuccess(message);

                    } else {
                        if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                            postError("Api_Tender->postLetMeKnow", resault.getMessage());
                        }
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }

                });

            }).start();

        });

    }

    //در اینجا مناقصاتی که کاربر فیلتر کرده است از سرورگرفته می شود
    public Single<VM_TenderNotification> getTendersFilter(int page, String apiKey, Tbl_Tender tbl_tender) {
        return Single.create(emitter -> {
            new Thread(() -> {

                JSONObject input = new JSONObject();

                try {
                    input.put("Paging", page);
                    input.put("ApiKey", apiKey);
                } catch (Exception e) {
                }

                volley_getTendersFilter = new PostJsonObjectVolley(ApiUrl + "Tender/PostTenderUser", input, resault -> {

                    if (resault.getResault() == ResaultCode.Success) {

                        List<VM_TenderNotifications> notifications = new ArrayList<>();
                        VM_TenderNotification notification = new VM_TenderNotification();
                        JSONArray array = new JSONArray();

                        try {
                            array = resault.getObject().getJSONArray("Tenders");
                            notification.setCountTenders(resault.getObject().getInt("CountTender"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        for (int i = 0; i < array.length(); i++) {

                            try {

                                VM_TenderNotifications n = new VM_TenderNotifications();
                                JSONObject object = array.getJSONObject(i);

                                n.setId(object.getString("TenderId"));
                                n.setTitle(object.getString("Title"));
                                n.setFree(object.getBoolean("Free"));
                                n.setStar(tbl_tender.isFavoritTender(object.getString("TenderId")));

                                notifications.add(n);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        notification.setTenderNotifications(notifications);

                        emitter.onSuccess(notification);

                    } else {
                        if (resault.getResault() != ResaultCode.TimeoutError && resault.getResault() != ResaultCode.NetworkError) {
                            postError("Api_Tender->getTendersFilter", resault.getMessage());
                        }
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }

                });

            }).start();
        });
    }

    //در اینجا لیست علاقه مندی ها نمایش داده می شوند
    public Single<List<VM_TenderNotifications>> getFavorites(List<String> vals, Tbl_Tender tbl_tender) {
        return Single.create(emitter -> {
            new Thread(() -> {

                try {
                    JSONArray input = new JSONArray();

                    for (int i = 0; i < vals.size(); i++) {
                        input.put(vals.get(i));
                    }

                    volley_getFavorites = new PostJsonArrayVolley(ApiUrl + "Tender/PostFavorite", input, resault -> {

                        if (resault.getResault() == ResaultCode.Success) {

                            try {
                                List<VM_TenderNotifications> notifications = new ArrayList<>();
                                JSONArray array = resault.getJsonArray();

                                for (int i = 0; i < array.length(); i++) {
                                    try {
                                        JSONObject object = array.getJSONObject(i);
                                        VM_TenderNotifications notification = new VM_TenderNotifications();

                                        notification.setId(object.getString("TenderId"));
                                        notification.setTitle(object.getString("Title"));
                                        notification.setFree(object.getBoolean("Free"));
                                        notification.setStar(tbl_tender.isFavoritTender(object.getString("TenderId")));

                                        notifications.add(notification);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                emitter.onSuccess(notifications);

                            } catch (Exception e) {
                                emitter.onError(e);
                            }

                        } else {
                            emitter.onError(new IOException(resault.getResault().toString()));
                        }

                    });

                } catch (Exception e) {
                }

            }).start();
        });
    }

    //در اینجا لیستی از فایل ها آپلود می شود
    public void uploadFiles(List<String> urlfiles, onUploadFiles uploadFiles) {

        isUploadedFile = true;

        String url = ApiUrl + "PostFile/PostFileOrder";
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

    //در اینجا سفارش آنالیز به سمت سرور ارسال می شود
    public Single<VM_Message> postOrderAnalize(VM_InputAnalizeTender input) {

        return Single.create(emitter -> {
            new Thread(() -> {

                JSONObject obj = new JSONObject();

                try {
                    obj.put("UserId", input.getUserId());
                    obj.put("TenderId", input.getTenderId());
                    obj.put("Fee", input.getFee());
                    obj.put("ContractorName", input.getContractorName());
                    obj.put("CellPhone", input.getCellPhone());
                    obj.put("TenderName", input.getTenderName());
                    obj.put("Description", input.getDescription());
                    obj.put("FileUrl1", input.getFileUrl1());
                    obj.put("FileUrl2", input.getFileUrl2());
                    obj.put("FileUrl3", input.getFileUrl3());
                    obj.put("FileUrl4", input.getFileUrl4());
                    obj.put("FileUrl5", input.getFileUrl5());
                    obj.put("FileUrl6", input.getFileUrl6());
                    obj.put("FileUrl7", input.getFileUrl7());
                    obj.put("FileUrl8", input.getFileUrl8());
                    obj.put("FileUrl9", input.getFileUrl9());
                    obj.put("FileUrl10", input.getFileUrl10());
                    obj.put("Price1", input.getPrice1());
                    obj.put("Price2", input.getPrice2());
                    obj.put("Price3", input.getPrice3());
                    obj.put("Price4", input.getPrice4());
                    obj.put("Price5", input.getPrice5());
                    obj.put("Price6", input.getPrice6());
                    obj.put("Price7", input.getPrice7());
                    obj.put("Price8", input.getPrice8());
                    obj.put("Price9", input.getPrice9());
                    obj.put("Price10", input.getPrice10());
                } catch (Exception e) {
                }

                volley_postOrderAnalize = new PostJsonObjectVolley(ApiUrl + "Order/PostAddOrderAnaliz", obj, resault -> {

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

            }).start();
        });

    }

    //در اینجا زمانبندی سفارش به سرور ارسال می شود
    public Single<VM_Message> postOrderScheduling(VM_InputAnalizeTender input) {
        return Single.create(emitter -> {
            new Thread(() -> {

                JSONObject obj = new JSONObject();

                try {
                    obj.put("UserId", input.getUserId());
                    obj.put("TenderId", input.getTenderId());
                    obj.put("Fee", input.getFee());
                    obj.put("ContractorName", input.getContractorName());
                    obj.put("CellPhone", input.getCellPhone());
                    obj.put("ProjectName", input.getTenderName());
                    obj.put("Description", input.getDescription());
                    obj.put("FileUrl1", input.getFileUrl1());
                    obj.put("FileUrl2", input.getFileUrl2());
                    obj.put("FileUrl3", input.getFileUrl3());
                    obj.put("FileUrl4", input.getFileUrl4());
                    obj.put("FileUrl5", input.getFileUrl5());
                    obj.put("FileUrl6", input.getFileUrl6());
                    obj.put("FileUrl7", input.getFileUrl7());
                    obj.put("FileUrl8", input.getFileUrl8());
                    obj.put("FileUrl9", input.getFileUrl9());
                    obj.put("FileUrl10", input.getFileUrl10());
                    obj.put("DetailedSchedule", input.isDetailedSchedule());
                    obj.put("InitialSchedule", input.isInitialSchedule());
                    obj.put("LandDeliveryDateShamsi", input.getLandDeliveryDate());
                    obj.put("ProjectDuration", input.getProjectDuration());
                } catch (Exception e) {
                }

                volley_postOrderScheduling = new PostJsonObjectVolley(ApiUrl + "Order/PostAddOrderZamanBandi", obj, resault -> {
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

            }).start();
        });
    }

    //در اینجا یک حسابرسی جدید اضافه می شود
    public Single<VM_Message> postOrderAudit(VM_InputAnalizeTender input) {
        return Single.create(emitter -> {
            new Thread(() -> {

                try {

                    JSONObject obj = new JSONObject();

                    try {
                        obj.put("UserId", input.getUserId());
                        obj.put("TenderId", input.getTenderId());
                        obj.put("Fee", input.getFee());
                        obj.put("ContractorName", input.getContractorName());
                        obj.put("CellPhone", input.getCellPhone());
                        obj.put("TenderName", input.getTenderName());
                        obj.put("Description", input.getDescription());
                        obj.put("FileUrl1", input.getFileUrl1());
                        obj.put("FileUrl2", input.getFileUrl2());
                        obj.put("FileUrl3", input.getFileUrl3());
                        obj.put("FileUrl4", input.getFileUrl4());
                        obj.put("FileUrl5", input.getFileUrl5());
                        obj.put("FileUrl6", input.getFileUrl6());
                        obj.put("FileUrl7", input.getFileUrl7());
                        obj.put("FileUrl8", input.getFileUrl8());
                        obj.put("FileUrl9", input.getFileUrl9());
                        obj.put("FileUrl10", input.getFileUrl10());
                        obj.put("AuditYear", input.getAudit_of_the_year());
                        obj.put("RecordJame", input.isJumSystem());
                        obj.put("RecordPardis", input.isPardisSystem());
                        obj.put("RecordSahar", input.isSaharSystem());
                        obj.put("NoRecord", input.isNoSystem());
                        obj.put("Record", input.isIts_necessary());
                    } catch (Exception e) {
                    }

                    volley_postOrderAudit = new PostJsonObjectVolley(ApiUrl + "Order/PostAddOrderHasabresi", obj, resault -> {

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
    }

    //در اینجا متره و برآورد اضافه می شود
    public Single<VM_Message> postOrderCostEstimation(VM_InputAnalizeTender input) {
        return Single.create(emitter -> {
            new Thread(() -> {

                try {

                    JSONObject obj = new JSONObject();

                    try {
                        obj.put("UserId", input.getUserId());
                        obj.put("TenderId", input.getTenderId());
                        obj.put("Fee", input.getFee());
                        obj.put("ContractorName", input.getContractorName());
                        obj.put("CellPhone", input.getCellPhone());
                        obj.put("ProjectName", input.getProjectName());
                        obj.put("Description", input.getDescription());
                        obj.put("FileUrl1", input.getFileUrl1());
                        obj.put("FileUrl2", input.getFileUrl2());
                        obj.put("FileUrl3", input.getFileUrl3());
                        obj.put("FileUrl4", input.getFileUrl4());
                        obj.put("FileUrl5", input.getFileUrl5());
                        obj.put("FileUrl6", input.getFileUrl6());
                        obj.put("FileUrl7", input.getFileUrl7());
                        obj.put("FileUrl8", input.getFileUrl8());
                        obj.put("FileUrl9", input.getFileUrl9());
                        obj.put("FileUrl10", input.getFileUrl10());
                        obj.put("PriceList", input.getPriceList());
                        obj.put("SuggestedCoefficient", input.getSuggested());
                        obj.put("OverheadFactor", input.getAbove());
                        obj.put("AreaCoefficient", input.getZone());
                        obj.put("WorkshopEquipmentCoefficient", input.getEquippingTheIngotWorkshop());
                        obj.put("TimeFactor", input.getTimely());
                    } catch (Exception e) {
                    }

                    volley_postOrderCostEstimation = new PostJsonObjectVolley(ApiUrl + "Order/PostAddOrderMetehVaBaravard", obj, resault -> {

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
    }

    //در اینجا تعدیل برای اضافه شدن به سرور ارسال می شود
    public Single<VM_Message> postOrderDifference(VM_InputAnalizeTender input) {
        return Single.create(emitter -> {
            new Thread(() -> {

                try {

                    JSONObject obj = new JSONObject();

                    try {

                        obj.put("UserId", input.getUserId());
                        obj.put("TenderId", input.getTenderId());
                        obj.put("Fee", input.getFee());
                        obj.put("ContractorName", input.getContractorName());
                        obj.put("CellPhone", input.getCellPhone());
                        obj.put("ProjectName", input.getProjectName());
                        obj.put("Description", input.getDescription());
                        obj.put("FileUrl1", input.getFileUrl1());
                        obj.put("FileUrl2", input.getFileUrl2());
                        obj.put("FileUrl3", input.getFileUrl3());
                        obj.put("FileUrl4", input.getFileUrl4());
                        obj.put("FileUrl5", input.getFileUrl5());
                        obj.put("FileUrl6", input.getFileUrl6());
                        obj.put("FileUrl7", input.getFileUrl7());
                        obj.put("FileUrl8", input.getFileUrl8());
                        obj.put("FileUrl9", input.getFileUrl9());
                        obj.put("FileUrl10", input.getFileUrl10());
                        obj.put("PriceList", input.getPriceList());
                        obj.put("PermittedDelayOfMonth", input.getPermittedDelayOfMonth());
                        obj.put("PermittedDelayOfDay", input.getPermittedDelayOfDay());
                        obj.put("UnauthorizedDelayOfDay", input.getUnauthorizedDelayOfDay());
                        obj.put("UnauthorizedDelayOfMonth", input.getUnauthorizedDelayOfMonth());

                    } catch (Exception e) {
                    }

                    volley_postOrderDifference = new PostJsonObjectVolley(ApiUrl + "Order/PostAddOrderTeadil", obj, resault -> {

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
    }

    //در اینجا سفارشات برگشت داده می شود
    public Single<List<VM_Orders>> getOrders(int page, int userId, VM_FilterOrder filterOrder) {

        return Single.create(emitter -> {
            new Thread(() -> {

                JSONObject input = new JSONObject();
                JSONArray filters = new JSONArray();

                try {

                    input.put("UserId", userId);
                    input.put("Paging", page);

                    filters.put(filterOrder.isTenderAnalise());
                    filters.put(filterOrder.isScheduling());
                    filters.put(filterOrder.isCostEstimation());
                    filters.put(filterOrder.isDifference());
                    filters.put(filterOrder.isAudit());

                    input.put("Kind", filters);

                } catch (Exception e) {

                }

                volley_getOrders = new PostJsonObject_And_GetJsonArrayVolley(ApiUrl + "Order/PostOrders", input, resault -> {

                    if (resault.getResault() == ResaultCode.Success) {

                        List<VM_Orders> orders = new ArrayList<>();
                        JSONArray array = resault.getJsonArray();

                        if (array != null) {

                            for (int i = 0; i < array.length(); i++) {

                                try {

                                    JSONObject object = array.getJSONObject(i);
                                    VM_Orders order = new VM_Orders();

                                    order.setId(object.getInt("Id"));
                                    order.setTitle(object.getString("TenderName"));
                                    order.setDate(Replace.Number_fn_To_en(object.getString("DateOfCompletion")));
                                    order.setPayment(Replace.Number_fn_To_en(object.getString("AmountPayable")));
                                    order.setFileUrl(object.getString("FileUrl"));

                                    switch (object.getInt("Steep")) {
                                        case 1:
                                            order.setStepsAnalizeTender(StepsAnalizeTender.sendOrder);
                                            break;
                                        case 2:
                                            order.setStepsAnalizeTender(StepsAnalizeTender.orderCost);
                                            break;
                                        case 3:
                                            order.setStepsAnalizeTender(StepsAnalizeTender.pay);
                                            break;
                                        case 4:
                                            order.setStepsAnalizeTender(StepsAnalizeTender.doing);
                                            break;
                                        case 5:
                                            order.setStepsAnalizeTender(StepsAnalizeTender.takingOrders);
                                            break;
                                    }

                                    switch (object.getString("Kind")) {
                                        case "Analiz":
                                            order.setOrderKind(OrderKind.Analiz);
                                            break;
                                        case "ZamanBandi":
                                            try {
                                                if (!object.getString("ProjectName").equalsIgnoreCase("null")) {
                                                    order.setTitle(object.getString("ProjectName"));
                                                }
                                            } catch (Exception e) {
                                            }
                                            order.setOrderKind(OrderKind.ZamanBandi);
                                            break;
                                        case "MetehVaBaravard":
                                            try {
                                                if (!object.getString("ProjectName").equalsIgnoreCase("null")) {
                                                    order.setTitle(object.getString("ProjectName"));
                                                }
                                            } catch (Exception e) {
                                            }
                                            order.setOrderKind(OrderKind.MetehVaBaravard);
                                            break;
                                        case "Teadil":
                                            try {
                                                if (!object.getString("ProjectName").equalsIgnoreCase("null")) {
                                                    order.setTitle(object.getString("ProjectName"));
                                                }
                                            } catch (Exception e) {
                                            }
                                            order.setOrderKind(OrderKind.Teadil);
                                            break;
                                        case "Hasabresi":
                                            try {
                                                if (!object.getString("ContractorName").equalsIgnoreCase("null")) {
                                                    order.setTitle(object.getString("ContractorName"));
                                                }
                                            } catch (Exception e) {
                                            }
                                            order.setOrderKind(OrderKind.Hasabresi);
                                            break;
                                    }

                                    orders.add(order);

                                } catch (Exception e) {
                                }
                            }
                        }

                        emitter.onSuccess(orders);

                    } else {
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }
                });

            }).start();

        });

    }

    //در اینجا جزئیات آنالیز مناقصه برگشت داده می شود
    public Single<VM_AnaliseInfo> getOrderAnaliseInfo(int id) {
        return Single.create(emitter -> {
            new Thread(() -> {
                volley_getOrderAnaliseInfo = new GetJsonObjectVolley(ApiUrl + "Order/GetOrderInfo?Id=" + id, resault -> {
                    try {

                        if (resault.getResault() == ResaultCode.Success) {

                            VM_AnaliseInfo analiseInfo = new VM_AnaliseInfo();

                            JSONObject object = resault.getObject();

                            try {

                                JSONArray filesArray = object.getJSONArray("FileUrl1");
                                JSONArray percentsArray = object.getJSONArray("Price");

                                analiseInfo.setId(id);

                                if (!object.getString("TenderName").equalsIgnoreCase("null"))
                                    analiseInfo.setTenderName(object.getString("TenderName"));

                                if (!object.getString("Fee").equalsIgnoreCase("null"))
                                    analiseInfo.setNationalEstimate(object.getString("Fee"));

                                if (!object.getString("ContractorName").equalsIgnoreCase("null"))
                                    analiseInfo.setContractorName(object.getString("ContractorName"));

                                if (!object.getString("CellPhone").equalsIgnoreCase("null"))
                                    analiseInfo.setPhoneNumber(object.getString("CellPhone"));

                                if (!object.getString("Description").equalsIgnoreCase("null"))
                                    analiseInfo.setDescription(object.getString("Description"));

                                if (!object.getString("AmountPayable").equalsIgnoreCase("null")) {
                                    if (!object.getString("AmountPayable").equalsIgnoreCase("")) {
                                        try {

                                            String[] amountPayableArray = object.getString("AmountPayable").split("\\.");
                                            if (amountPayableArray.length != 0) {
                                                if (amountPayableArray.length == 2) {
                                                    analiseInfo.setAmountPayable(Replace.Number_en_To_fa(amountPayableArray[0]));
                                                } else {
                                                    analiseInfo.setAmountPayable(Replace.Number_en_To_fa(object.getString("AmountPayable")));
                                                }
                                            }

                                        } catch (Exception e) {
                                        }
                                    }
                                }

                                if (!object.getString("TimePay").equalsIgnoreCase("null"))
                                    analiseInfo.setTimePay(Replace.Number_en_To_fa(object.getString("TimePay")));

                                if (!object.getString("Time").equalsIgnoreCase("null"))
                                    analiseInfo.setDoingTime(Replace.Number_en_To_fa(object.getString("Time")));

                                if (!object.getString("TimeForTimer").equalsIgnoreCase("null")) {
                                    if (!object.getString("TimeForTimer").equalsIgnoreCase("")) {
                                        analiseInfo.setTimer(object.getString("TimeForTimer"));
                                    } else {
                                        analiseInfo.setTimer("00:00:00");
                                    }

                                } else {
                                    analiseInfo.setTimer("");
                                }

                                if (!object.getString("FileUrlOrder").equalsIgnoreCase("null"))
                                    analiseInfo.setFileName(object.getString("FileUrlOrder"));

//                                if (!object.getString("RecordJame").equalsIgnoreCase("null"))
//                                    analiseInfo.setJumSystem(object.getBoolean("RecordJame"));
//
//                                if (!object.getString("RecordPardis").equalsIgnoreCase("null"))
//                                    analiseInfo.setPardisSystem(object.getBoolean("RecordPardis"));

                                if (!object.getString("NoRecord").equalsIgnoreCase("null"))
                                    analiseInfo.setNoSystem(object.getBoolean("NoRecord"));

                                if (!object.getString("Record").equalsIgnoreCase("null"))
                                    analiseInfo.setIts_necessary(object.getBoolean("Record"));

//                                if (!object.getString("RecordSahar").equalsIgnoreCase("null"))
//                                    analiseInfo.setSaharSystem(object.getBoolean("RecordSahar"));

                                if (!object.getString("DetailedSchedule").equalsIgnoreCase("null"))
                                    analiseInfo.setDetailedSchedule(object.getBoolean("DetailedSchedule"));

                                if (!object.getString("InitialSchedule").equalsIgnoreCase("null"))
                                    analiseInfo.setInitialSchedule(object.getBoolean("InitialSchedule"));

                                if (!object.getString("LandDeliveryDate").equalsIgnoreCase("null"))
                                    analiseInfo.setLandDeliveryDate(object.getString("LandDeliveryDate"));

                                if (!object.getString("ProjectDuration").equalsIgnoreCase("null"))
                                    analiseInfo.setProjectDuration(object.getInt("ProjectDuration"));

                                if (!object.getString("AuditYear").equalsIgnoreCase("null"))
                                    analiseInfo.setAudit_of_the_year(object.getString("AuditYear"));

                                if (!object.getString("PriceList").equalsIgnoreCase("null"))
                                    analiseInfo.setPriceList(object.getString("PriceList"));

                                if (!object.getString("SuggestedCoefficient").equalsIgnoreCase("null"))
                                    if (!object.getString("SuggestedCoefficient").equalsIgnoreCase(""))
                                        analiseInfo.setSuggested(Float.valueOf(object.getString("SuggestedCoefficient")));

                                if (!object.getString("OverheadFactor").equalsIgnoreCase("null"))
                                    if (!object.getString("OverheadFactor").equalsIgnoreCase(""))
                                        analiseInfo.setAbove(Float.valueOf(object.getString("OverheadFactor")));

                                if (!object.getString("AreaCoefficient").equalsIgnoreCase("null"))
                                    if (!object.getString("AreaCoefficient").equalsIgnoreCase(""))
                                        analiseInfo.setZone(Float.valueOf(object.getString("AreaCoefficient")));

                                if (!object.getString("WorkshopEquipmentCoefficient").equalsIgnoreCase("null"))
                                    if (!object.getString("WorkshopEquipmentCoefficient").equalsIgnoreCase(""))
                                        analiseInfo.setEquippingTheIngotWorkshop(Float.valueOf(object.getString("WorkshopEquipmentCoefficient")));

                                if (!object.getString("ProjectName").equalsIgnoreCase("null"))
                                    analiseInfo.setProjectName(object.getString("ProjectName"));

                                if (!object.getString("TimeFactor").equalsIgnoreCase("null"))
                                    if (!object.getString("TimeFactor").equalsIgnoreCase(""))
                                        analiseInfo.setTimely(Float.valueOf(object.getString("TimeFactor")));

                                if (!object.getString("PermittedDelayOfDay").equalsIgnoreCase("null"))
                                    analiseInfo.setPermittedDelayOfDay(object.getInt("PermittedDelayOfDay"));

                                if (!object.getString("PermittedDelayOfMonth").equalsIgnoreCase("null"))
                                    analiseInfo.setPermittedDelayOfMonth(object.getInt("PermittedDelayOfMonth"));

                                if (!object.getString("UnauthorizedDelayOfDay").equalsIgnoreCase("null"))
                                    analiseInfo.setUnauthorizedDelayOfDay(object.getInt("UnauthorizedDelayOfDay"));

                                if (!object.getString("UnauthorizedDelayOfMonth").equalsIgnoreCase("null"))
                                    analiseInfo.setUnauthorizedDelayOfMonth(object.getInt("UnauthorizedDelayOfMonth"));

                                //در اینجا استپ ها گرفته می شوند
                                if (object.get("Steep") != null) {
                                    switch (object.getInt("Steep")) {
                                        case 1:
                                            analiseInfo.setStep(StepsAnalizeTender.sendOrder);
                                            break;
                                        case 2:
                                            analiseInfo.setStep(StepsAnalizeTender.orderCost);
                                            break;
                                        case 3:
                                            analiseInfo.setStep(StepsAnalizeTender.pay);
                                            break;
                                        case 4:
                                            analiseInfo.setStep(StepsAnalizeTender.doing);
                                            break;
                                        case 5:
                                            analiseInfo.setStep(StepsAnalizeTender.takingOrders);
                                            break;
                                    }
                                }

                                //در اینجا نام فایل ها گرفته می شود
                                List<String> files = new ArrayList<>();

                                if (filesArray != null) {
                                    for (int i = 0; i < filesArray.length(); i++) {
                                        files.add(filesArray.get(i).toString());
                                    }
                                }
                                analiseInfo.setFileUrls(files);

                                //در اینجا درصدها گرفته می شوند
                                List<Float> percents = new ArrayList<>();

                                if (percentsArray != null) {
                                    for (int i = 0; i < percentsArray.length(); i++) {
                                        percents.add(Float.valueOf(percentsArray.get(i).toString()));
                                    }
                                }
                                analiseInfo.setPercents(percents);

                            } catch (Exception e) {
                            }

                            emitter.onSuccess(analiseInfo);
                        } else {
                            emitter.onError(new IOException(resault.getResault().toString()));
                        }

                    } catch (Exception e) {
                        emitter.onError(e);
                    }
                });
            }).start();
        });
    }

    public Single<Boolean> downloadOrder(String fileName, String title) {
        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    Thread.sleep(3000);
                    URL url = new URL(Orderurl + fileName);
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

                    if (workingDownloadFile)
                        emitter.onSuccess(true);

                } catch (Exception e) {
                    e.printStackTrace();
                    if (workingDownloadFile)
                        emitter.onSuccess(false);
                }

            }).start();

        });
    }

    /**
     * در اینجا سفارش حذف می شود
     **/
    public Single<VM_Message> deleteOrder(int id) {
        return Single.create(emitter -> {
            new Thread(() -> {
                volley_deleteOrder = new DeleteJsonObjectVolley(ApiUrl + "Order/DeleteOrder?Id=" + id, resault -> {
                    if (resault.getResault() == ResaultCode.Success) {

                        JSONObject object = resault.getObject();
                        VM_Message message = new VM_Message();
                        try {
                            message.setCode(object.getInt("Code"));
                            message.setResult(object.getBoolean("Result"));
                            message.setMessage(object.getString("MessageText"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        emitter.onSuccess(message);
                    } else {
                        emitter.onError(new IOException(resault.getResault().toString()));
                    }
                });
            }).start();
        });
    }

    public void Cancel(String Tag, Context context) {

        isUploadedFile = false;
        workingDownloadFile = false;
        cancelBase(Tag, context);

        if (volley_getTenderNotification != null) {
            volley_getTenderNotification.Cancel(Tag, context);
        }

        if (volley_getDetailsTender != null) {
            volley_getDetailsTender.Cancel(Tag, context);
        }

        if (volley_postLetMeKnow != null) {
            volley_postLetMeKnow.Cancel(Tag, context);
        }

        if (volley_getTendersFilter != null) {
            volley_getTendersFilter.Cancel(Tag, context);
        }

        if (volley_getFavorites != null) {
            volley_getFavorites.Cancel(Tag, context);
        }

        if (volley_postOrderAnalize != null) {
            volley_postOrderAnalize.Cancel(Tag, context);
        }

        if (volley_getOrders != null) {
            volley_getOrders.Cancel(Tag, context);
        }

        if (volley_getOrderAnaliseInfo != null) {
            volley_getOrderAnaliseInfo.Cancel(Tag, context);
        }

        if (volley_postOrderScheduling != null) {
            volley_postOrderScheduling.Cancel(Tag, context);
        }

        if (volley_postOrderAudit != null) {
            volley_postOrderAudit.Cancel(Tag, context);
        }

        if (volley_postOrderCostEstimation != null) {
            volley_postOrderCostEstimation.Cancel(Tag, context);
        }

        if (volley_postOrderDifference != null) {
            volley_postOrderDifference.Cancel(Tag, context);
        }

        if (volley_deleteOrder != null) {
            volley_deleteOrder.Cancel(Tag, context);
        }

    }
}
