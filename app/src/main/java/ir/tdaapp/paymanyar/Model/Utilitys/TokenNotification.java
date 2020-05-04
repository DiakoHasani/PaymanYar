package ir.tdaapp.paymanyar.Model.Utilitys;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Notification;

//در اینجا توکن کاربر برای نوتیفیکشن گرفته می شود
public class TokenNotification extends FirebaseInstanceIdService {

    Tbl_Notification tbl_notification;

    public TokenNotification() {
        tbl_notification = new Tbl_Notification();
    }

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();

        tbl_notification.add(getBaseContext(), token);
    }

}
