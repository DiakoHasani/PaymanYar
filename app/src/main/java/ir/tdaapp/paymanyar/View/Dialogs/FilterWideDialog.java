package ir.tdaapp.paymanyar.View.Dialogs;

import android.graphics.drawable.GradientDrawable;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import ir.tdaapp.paymanyar.Model.Adapters.FilterAdapter;
import ir.tdaapp.paymanyar.Model.Services.S_FilterDialog;
import ir.tdaapp.paymanyar.Model.Services.SelectedItemFilterWideDialog;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseDialogFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_EshtalItem;
import ir.tdaapp.paymanyar.Presenter.P_FilterWideDialog;
import ir.tdaapp.paymanyar.R;

public class FilterWideDialog extends BaseDialogFragment implements View.OnClickListener, S_FilterDialog {

    public final static String TAG = "FilterWideDialog";
    private P_FilterWideDialog p_filterWideDialog;
    Button btn_Select, btn_Cancel;
    public VM_EshtalItem ChoosedItem;
    private RecyclerView recyclerView;
    private String eshtal_id;
    private DialogListener dialogListener;

    public FilterWideDialog(VM_EshtalItem x,String eshtal_id,@Nullable DialogListener dialogListener) {
        this.ChoosedItem=x;
        this.eshtal_id=eshtal_id;
        this.dialogListener=dialogListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.filter_wide_dialog, container, false);

        findItem(view);
        implement();

        return view;
    }

    void findItem(View view) {
        recyclerView=view.findViewById(R.id.filter_dialog_recycler);
        btn_Select = view.findViewById(R.id.btn_Select);
        btn_Cancel = view.findViewById(R.id.btn_Cancel);
    }

    void implement(){
        p_filterWideDialog=new P_FilterWideDialog(this.getContext(),this,this.ChoosedItem);
        btn_Select.setOnClickListener(this);
        btn_Cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_Select:

                /* We Save Choosed ID in 2 way.
                *
                *  1 - if parent = 0 so this is our first Column in table. we Save it's ID
                *
                *  2 - if parent != 0 so this Other Columns that we should save their first column ID which is Parent ID
                *
                 */
                if(Integer.valueOf(this.ChoosedItem.getParent())!=0){
                    p_filterWideDialog.SaveChoosed(this.ChoosedItem.getParent(),this.eshtal_id);
                }else{
                    p_filterWideDialog.SaveChoosed(this.ChoosedItem.getId(),this.eshtal_id);
                }
                if(dialogListener!=null){dialogListener.OnDialogClosed();}
                getDialog().dismiss();
                break;
            case R.id.btn_Cancel:
                getDialog().dismiss();
                break;
        }
    }


    @Override
    public void OnRowsFind(FilterAdapter adapter) {
        //When rows completely read from database then we show it as adapter
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnItemChoosed(VM_EshtalItem choosed) {
        this.ChoosedItem=choosed;
    }

    public interface DialogListener{
        public void OnDialogClosed();
    }
}
