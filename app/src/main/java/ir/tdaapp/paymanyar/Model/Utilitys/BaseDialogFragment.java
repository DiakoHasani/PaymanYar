package ir.tdaapp.paymanyar.Model.Utilitys;

import androidx.fragment.app.DialogFragment;

public abstract class BaseDialogFragment extends DialogFragment {
    protected String paymentUrl = "http://api.paimanyar.ir/";

    //مربوط به آدرس اپلیکیشن در پلی استور
    protected String appStoreUrl = "https://play.google.com/store/apps/details?id=ir.tdaapp.karsan&hl=en";

    //آدرس ای پی کی در سرور برای دانلود و بروزرسانی اپلیکیشن
    protected String apkUrl = "http://api.paimanyar.ir/Content/data/apk/a.apk";
}
