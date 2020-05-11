package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import ir.tdaapp.paymanyar.Model.Services.S_PriceRangeFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DegreeOfImportance;
import ir.tdaapp.paymanyar.R;

public class P_PriceRangeFragment {

    private Context context;
    private S_PriceRangeFragment s_priceRangeFragment;

    public P_PriceRangeFragment(Context context, S_PriceRangeFragment s_priceRangeFragment) {
        this.context = context;
        this.s_priceRangeFragment = s_priceRangeFragment;
    }

    public void start() {
        s_priceRangeFragment.OnStart();
        setDegreeOfImportance();
    }

    void setDegreeOfImportance() {

        List<VM_DegreeOfImportance> vals = new ArrayList<>();

        vals.add(new VM_DegreeOfImportance(1, "کم"));
        vals.add(new VM_DegreeOfImportance(2, "متوسط"));
        vals.add(new VM_DegreeOfImportance(3, "زیاد"));

        ArrayAdapter<VM_DegreeOfImportance> adapter = new ArrayAdapter<>(context, R.layout.spinner_item3, vals);
        s_priceRangeFragment.onGetDegreeOfImportance(adapter);
    }

}
