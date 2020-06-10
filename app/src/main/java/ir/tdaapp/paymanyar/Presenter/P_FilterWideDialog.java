package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import java.util.ArrayList;

import ir.tdaapp.paymanyar.Model.Adapters.FilterAdapter;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Eshtal;
import ir.tdaapp.paymanyar.Model.Services.S_FilterDialog;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_EshtalItem;

public class P_FilterWideDialog {

    private Context context;
    private S_FilterDialog s_FilterDialog;
    private VM_EshtalItem item;
    Tbl_Eshtal tbl_eshtal;

    public P_FilterWideDialog(Context context, S_FilterDialog s_ipe_semiWideFragment, VM_EshtalItem item) {
        this.context = context;
        this.s_FilterDialog = s_ipe_semiWideFragment;
        this.item = item;
        tbl_eshtal = new Tbl_Eshtal(context);
        ArrayList<VM_EshtalItem> arrayList = tbl_eshtal.getEshtalFilter(item.getColumn_id());
        SetToAdapter(arrayList);
    }

    private void SetToAdapter(ArrayList<VM_EshtalItem> arrayList) {
        FilterAdapter adapter = new FilterAdapter(this.context);
        this.s_FilterDialog.OnRowsFind(adapter);

        adapter.setListener(itemChoosed -> s_FilterDialog.OnItemChoosed(itemChoosed));

        for (int i = 0; i < arrayList.size(); i++) {
            adapter.add(arrayList.get(i));
            if (this.item.getId().compareToIgnoreCase(arrayList.get(i).getId()) == 0) {
                adapter.SetIndex(i);
            }
        }
        adapter.notifyDataSetChanged();

    }

    public void SaveChoosed(String lastid, String eshtal_id) {
        tbl_eshtal.SaveChosed(lastid,eshtal_id);
    }

}
