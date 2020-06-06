package ir.tdaapp.paymanyar.Model.Utilitys;

import android.content.Context;
import android.util.TypedValue;

public class DPParser {

    public static int IntToDP(Context context,int value){

        //تبدیل عدد صحیح به dp
        int marginInDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, value, context.getResources()
                        .getDisplayMetrics());
        return marginInDp;
    }
}
