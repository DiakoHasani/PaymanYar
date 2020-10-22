package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ir.tdaapp.paymanyar.Model.Enums.AdTypeMaterial;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_AdTypeMaterial;
import ir.tdaapp.paymanyar.R;

/**
 * در اینجا مقادیر نوع آگهی مصالح گرفته می شود
 **/
public class Tbl_AdTypeMaterial {

    Context context;
    List<VM_AdTypeMaterial> vals;

    public Tbl_AdTypeMaterial(Context context) {
        this.context = context;
        vals = new ArrayList<>();

        vals.add(new VM_AdTypeMaterial(AdTypeMaterial.title, context.getString(R.string.AdType)));
        vals.add(new VM_AdTypeMaterial(AdTypeMaterial.Sales, context.getString(R.string.Sales)));
        vals.add(new VM_AdTypeMaterial(AdTypeMaterial.Buy, context.getString(R.string.Buy)));
    }

    public List<VM_AdTypeMaterial> getVals() {
        return vals;
    }

    public int getPositionByName(AdTypeMaterial adTypeMaterial) {
        int position = 0;

        for (int i = 0; i < vals.size(); i++) {
            if (vals.get(i).getAdType() == adTypeMaterial) {
                position = i;
                break;
            }
        }

        return position;
    }
}
