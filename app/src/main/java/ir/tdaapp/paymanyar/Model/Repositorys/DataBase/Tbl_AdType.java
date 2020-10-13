package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ir.tdaapp.paymanyar.Model.Enums.AdType;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_AdType;
import ir.tdaapp.paymanyar.R;

/**
 * برای نوع آگهی
 **/
public class Tbl_AdType {
    List<VM_AdType> adTypes;
    Context context;

    public Tbl_AdType(Context context) {
        this.context = context;

        adTypes = new ArrayList<>();

        //در اینجا می توانیم نوع آگهی جدید اضافه کنیم
        adTypes.add(new VM_AdType(AdType.title, context.getString(R.string.AdType)));
        adTypes.add(new VM_AdType(AdType.request, context.getString(R.string.Request)));
        adTypes.add(new VM_AdType(AdType.presentation, context.getString(R.string.Presentation)));
    }

    public List<VM_AdType> getAdTypes() {
        return adTypes;
    }

    /**
     * در اینجا پوزیشن نوع آگهی بر اساس نام آن برگشت داده می شود
     * **/
    public int getPositionByName(AdType adType) {
        int position = 0;
        for (int i = 0; i < adTypes.size(); i++) {
            if (adType == adTypes.get(i).getAdType()) {
                position = i;
                break;
            }
        }
        return position;
    }
}
