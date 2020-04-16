package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.li_volley.Enum.ResaultCode;

public interface S_SmsFragment {
    void OnStart();
    void onError(ResaultCode resault);
    void onFinish();
}
