package ir.tdaapp.paymanyar.Model.Utilitys;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

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
            // show error message
        }
    }

    //در اینجا وب سایت باز می شود
    public static void getWeb(String url, Context context) {
        try {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            context.startActivity(i);
        }catch (Exception e){}
    }

    //در اینجا یک متن را به اشتراک میزارد
    public static void getApplicationsText(String title, String url, Context context) {
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, title);
            intent.putExtra(Intent.EXTRA_TEXT, url);
            context.startActivity(Intent.createChooser(intent, context.getString(R.string.share)));
        }catch (Exception e){}
    }

    //در اینجا اینستاگرام باز می شود
    public static void getInstagram(String url, Context context) {
        try {
            Intent instagramIntent = new Intent(Intent.ACTION_VIEW);
            instagramIntent.setData(Uri.parse(url));
            context.startActivity(instagramIntent);
        } catch (Exception e) {
        }

    }

    //در اینجا واتس آپ را باز می کند
    public static void getWhatsApp(String url, Context context) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            context.startActivity(intent);
        } catch (Exception e) {
        }
    }

    //در اینجا شماره گیری موبایل را باز می کند
    public static void call(String phoneNumber, Context context) {
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            context.startActivity(intent);
        }catch (Exception e){}
    }

    //در اینجا پیامک رسانی را باز می کند
    void sms(String number, String text, Context context) {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts(text, number, null)));
        }catch (Exception e){}
    }

}
