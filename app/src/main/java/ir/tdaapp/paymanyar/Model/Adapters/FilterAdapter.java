package ir.tdaapp.paymanyar.Model.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Charge;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_EshtalItem;
import ir.tdaapp.paymanyar.R;

//مربوط به آداپتر شارژ
public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.MyViewHolder> {

    Context context;
    List<VM_EshtalItem> ITems;
    FilterAdapterListener listener;
    int choosedIndex=0;

    public FilterAdapter(Context context) {
        this.context = context;
        ITems = new ArrayList<>();
    }

    public void SetIndex(int index){
        this.choosedIndex=index;
    }

    public void add(VM_EshtalItem charge) {
        ITems.add(charge);
        notifyItemInserted(ITems.size());
    }

    public FilterAdapterListener getListener() {
        return listener;
    }

    public void setListener(FilterAdapterListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_filterdialog, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if(choosedIndex==position){
            holder.lbl_Title.setChecked(true);
        }else{
            holder.lbl_Title.setChecked(false);
        }

        holder.lbl_Title.setText(ITems.get(position).getValue());
        holder.lbl_Title.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    FilterAdapter.this.notifyItemChanged(choosedIndex);
                    choosedIndex=position;
                    if(listener!=null)listener.onChanged(ITems.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return ITems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        RadioButton lbl_Title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            findItem(itemView);
        }

        void findItem(View view) {
            lbl_Title = view.findViewById(R.id.recycler_filter_radio);

        }
    }

    public interface FilterAdapterListener{
        public void onChanged(VM_EshtalItem itemChoosed);
    }

}
