package ir.tdaapp.paymanyar.Model.Services;

public interface onClickSupport {

    void onClickSMS(String phoneNumber);
    void onClickTelegram(String url);
    void onClickCall(String phoneNumber);
    void onClickEmail(String url);

}
