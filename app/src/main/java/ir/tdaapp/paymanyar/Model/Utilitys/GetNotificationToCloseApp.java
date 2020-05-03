package ir.tdaapp.paymanyar.Model.Utilitys;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;

//زمانی که اپلیکیشن بسته باشد و کاربر روی نوتیفیکیشن کلیک کند این کلاس فراخوانی می شود
public class GetNotificationToCloseApp extends FirebaseMessagingService {

    PendingIntent pendingIntent;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage.getData() != null) {

            show_Notification(remoteMessage);

        }

    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    public void show_Notification(RemoteMessage remoteMessage){

        String key1=  remoteMessage.getData().get("key1");

        int notificationID = (int) System.currentTimeMillis();



        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("key1",key1);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        String CHANNEL_ID="MYCHANNEL";

        NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,"name", NotificationManager.IMPORTANCE_LOW);


        pendingIntent=PendingIntent.getActivity(this,notificationID,intent, PendingIntent.FLAG_UPDATE_CURRENT);



        Notification notification=new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentText(remoteMessage.getData().get("Content"))
                .setContentTitle(remoteMessage.getData().get("Title"))
                .setSmallIcon(android.R.drawable.sym_action_chat)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setChannelId(CHANNEL_ID)
                .build();

        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);

        notificationManager.notify(notificationID,notification);

    }

}
