package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;
import java.util.ArrayList;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Eshtal;
import ir.tdaapp.paymanyar.Model.Services.S_IPE_SemiWideFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_EshtalItem;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_IPE_SemiWideColumns;

public class P_IPE_SemiWideFragment {

    private Context context;
    private S_IPE_SemiWideFragment s_ipe_semiWideFragment;
    private String eshtal_id,colNumber,lastID;
    Tbl_Eshtal tbl_eshtal;

    public P_IPE_SemiWideFragment(Context context, S_IPE_SemiWideFragment s_ipe_semiWideFragment,String Current_eshtaal_id) {
        this.context = context;
        this.s_ipe_semiWideFragment = s_ipe_semiWideFragment;
        this.eshtal_id=Current_eshtaal_id;
        tbl_eshtal=new Tbl_Eshtal(context);

    }

    public void GetColumns(){

        VM_IPE_SemiWideColumns v=tbl_eshtal.getColumns(eshtal_id);
        colNumber=v.getColNumber();
        lastID=v.getLastID();
        GetItems();

    }

    private void GetItems(){
        ArrayList<VM_EshtalItem> arrayList=tbl_eshtal.getEshtalItems(eshtal_id,colNumber,lastID);
        s_ipe_semiWideFragment.SetItems(arrayList);
    }


    public void start(){
        s_ipe_semiWideFragment.OnStart();
    }
}
