package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.DBExcute;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.FieldItem;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.RecordHolder;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.database;
import ir.tdaapp.paymanyar.Model.Services.S_IPE_SemiWideFragment;

public class P_IPE_SemiWideFragment {

    private Context context;
    private S_IPE_SemiWideFragment s_ipe_semiWideFragment;
    private DBExcute dbExcute;
    private int eshtal_id;

    public P_IPE_SemiWideFragment(Context context, S_IPE_SemiWideFragment s_ipe_semiWideFragment,int Current_eshtaal_id) {
        this.context = context;
        this.s_ipe_semiWideFragment = s_ipe_semiWideFragment;
        this.eshtal_id=Current_eshtaal_id;
        this.dbExcute=DBExcute.getInstance(this.context);

        //Show the Default Value
        dbExcute.Read(database.QRY_DELETE_BASKET_INFO,new RecordHolder(new FieldItem("","")));

    }

    public void start(){
        s_ipe_semiWideFragment.OnStart();
    }
}
