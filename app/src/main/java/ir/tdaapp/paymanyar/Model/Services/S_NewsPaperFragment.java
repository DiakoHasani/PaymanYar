package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_NewsPaper;

public interface S_NewsPaperFragment {
    void OnStart();
    void onLoading(boolean load);
    void onError(ResaultCode result);
    void onFinish();
    void onHideAll();
    void onSuccess();
    void onNews(VM_NewsPaper newsPaper);
}
