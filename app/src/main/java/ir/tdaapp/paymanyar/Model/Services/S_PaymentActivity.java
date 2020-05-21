package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.li_volley.Enum.ResaultCode;

public interface S_PaymentActivity {
    void OnStart();
    void onError(ResaultCode result);
    void onLoading(boolean load);
    void onFinish(int day,int hour);
}
