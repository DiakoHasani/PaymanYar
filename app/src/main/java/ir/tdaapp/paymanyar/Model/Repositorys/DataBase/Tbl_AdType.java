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

        adTypes=new ArrayList<>();

        //در اینجا می توانیم نوع آگهی جدید اضافه کنیم
        adTypes.add(new VM_AdType(AdType.request, context.getString(R.string.Request)));
        adTypes.add(new VM_AdType(AdType.presentation, context.getString(R.string.Presentation)));
    }

    public List<VM_AdType> getAdTypes() {
        return adTypes;
    }
}
