package ir.tdaapp.paymanyar.Model.Utilitys;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import ir.tdaapp.paymanyar.R;

//در اینجا یک لینک می گیرد و برنامه مربوط به آن را باز می کند مثل تلگرام یا اینستاگرام
public class openUrl {

    //در اینجا یک لینک گرفته و برنامه مربوط به آن را باز می کند
    //urlKind نوع لینک
    //1 webSite
    //2 telegram
    //3 instagram
    //4 faceBook
    //5 whatsApp
    //6 call
    public static void getLink(int urlKind, String url, Context context) {
        switch (urlKind) {
            case 1:
                getWeb(url, context);
                break;
            case 2:
                getTelegram(url, context);
                break;
            case 3:
                getInstagram(url, context);
                break;
            case 4:
                break;
            case 5:
                getWhatsApp(url, context);
                break;
            case 6:
                call(url, context);
                break;
        }

    }

    //در اینجا تلگرام باز می شود
    public static void getTelegram(String url, Context context) {
        try {
            Intent telegramIntent = new Intent(Intent.ACTION_VIEW);
            telegramIntent.setData(Uri.parse(url));
            context.startActivity(telegramIntent);
        } catch (Exception e) {
            Log.e("getTelegram", e.toString());
        }
    }

    //در اینجا وب سایت باز می شود
    public static void getWeb(String url, Context context) {
        try {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            context.startActivity(i);
        } catch (Exception e) {
            Log.e("getWeb", e.toString());
        }
    }

    //در اینجا یک متن را به اشتراک میزارد
    public static void getApplicationsText(String title, String url, Context context) {
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, title);
            intent.putExtra(Intent.EXTRA_TEXT, url);
            context.startActivity(Intent.createChooser(intent, context.getString(R.string.share)));
        } catch (Exception e) {
            Log.e("getApplicationsText", e.toString());
        }
    }

    //در اینجا اینستاگرام باز می شود
    public static void getInstagram(String url, Context context) {
        try {
            Intent instagramIntent = new Intent(Intent.ACTION_VIEW);
            instagramIntent.setData(Uri.parse(url));
            context.startActivity(instagramIntent);
        } catch (Exception e) {
            Log.e("getInstagram", e.toString());
        }

    }

    //در اینجا واتس آپ را باز می کند
    public static void getWhatsApp(String url, Context context) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://wa.me/" + url));
            context.startActivity(intent);
        } catch (Exception e) {
            Log.e("getWhatsApp", e.toString());
        }
    }

    //در اینجا شماره گیری موبایل را باز می کند
    public static void call(String phoneNumber, Context context) {
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            context.startActivity(intent);
        } catch (Exception e) {
            Log.e("call", e.toString());
        }
    }

    //در اینجا پیامک رسانی را باز می کند
    public static void sms(String number, String text, Context context) {
        try {
            Uri sms_uri = Uri.parse("smsto:"+number);
            Intent sms_intent = new Intent(Intent.ACTION_SENDTO, sms_uri);
            sms_intent.putExtra("sms_body", text);
            context.startActivity(sms_intent);
        } catch (Exception e) {
            Log.e("sms", e.toString());
        }
    }

    //در اینجا ایمیل باز می شود
    public static void getEmail(String email,Context context){
        try {
            Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + email));
            intent.putExtra(Intent.EXTRA_SUBJECT, "");
            intent.putExtra(Intent.EXTRA_TEXT, "");
            context.startActivity(intent);
        }catch (Exception e){
            Log.e("getEmail", e.toString());
        }
    }

}
