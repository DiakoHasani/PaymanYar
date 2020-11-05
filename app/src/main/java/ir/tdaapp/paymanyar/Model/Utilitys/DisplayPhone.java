package ir.tdaapp.paymanyar.Model.Utilitys;

import android.content.Context;

import ir.tdaapp.paymanyar.Model.Enums.DisplaySize;
import ir.tdaapp.paymanyar.View.Activitys.LevelActivity;

public class DisplayPhone {

    //در اینجا نشان می دهد اندازه گوشی جز کدام دسته بندی می باشد
    public static DisplaySize getDisplaySize(Context context) {
        int width = ((LevelActivity) context).getWidthDisplay();

        if (width >= 0 && width <= 480) {
            return DisplaySize.xSmall;
        } else if (width >= 600 && width <= 840) {
            return DisplaySize.small;
        } else if (width >= 960 && width < 1280) {
            return DisplaySize.medium;
        } else if (width >= 1280 && width <= 1440) {
            return DisplaySize.large;
        } else if (width >= 1900) {
            return DisplaySize.xLarge;
        } else {
            return DisplaySize.other;
        }
    }

    public static DisplaySize getDisplaySize(int width) {

        if (width >= 0 && width <= 480) {
            return DisplaySize.xSmall;
        } else if (width >= 600 && width <= 840) {
            return DisplaySize.small;
        } else if (width >= 960 && width < 1280) {
            return DisplaySize.medium;
        } else if (width >= 1280 && width <= 1440) {
            return DisplaySize.large;
        } else if (width >= 1900) {
            return DisplaySize.xLarge;
        } else {
            return DisplaySize.other;
        }
    }
}
