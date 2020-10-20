package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ir.tdaapp.paymanyar.Model.Enums.AdTypeCondition;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_AdTypeMachinery;
import ir.tdaapp.paymanyar.R;

/**
 * در اینجا مقادیر نوع آگهی ماشین آلات برگشت داده می شود
 **/
public class Tbl_AdTypeMachinery {
    Context context;
    List<VM_AdTypeMachinery> vals;

    public Tbl_AdTypeMachinery(Context context) {
        this.context = context;
        vals = new ArrayList<>();

        vals.add(new VM_AdTypeMachinery(AdTypeCondition.title, context.getString(R.string.Title)));
        vals.add(new VM_AdTypeMachinery(AdTypeCondition.Sales, context.getString(R.string.Sales)));
        vals.add(new VM_AdTypeMachinery(AdTypeCondition.Buy, context.getString(R.string.Buy)));
        vals.add(new VM_AdTypeMachinery(AdTypeCondition.RentGive, context.getString(R.string.RentGive)));
        vals.add(new VM_AdTypeMachinery(AdTypeCondition.RentTake, context.getString(R.string.RentTake)));
    }

    public List<VM_AdTypeMachinery> getAdTypes() {
        return vals;
    }

    public int getPositionByName(AdTypeCondition adTypeMachinery) {
        int position = 0;

        for (int i = 0; i < vals.size(); i++) {
            if (vals.get(i).getAdTypeCondition() == adTypeMachinery) {
                position = i;
                break;
            }
        }
        return position;
    }
}
