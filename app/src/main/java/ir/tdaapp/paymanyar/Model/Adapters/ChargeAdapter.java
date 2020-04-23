package ir.tdaapp.paymanyar.Model.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Charge;
import ir.tdaapp.paymanyar.R;

//مربوط به آداپتر شارژ
public class ChargeAdapter extends RecyclerView.Adapter<ChargeAdapter.MyViewHolder> {

    Context context;
    List<VM_Charge> charges;

    public ChargeAdapter(Context context) {
        this.context = context;
        charges = new ArrayList<>();
    }

    public void add(VM_Charge charge) {
        charges.add(charge);
        notifyItemInserted(charges.size());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_charge, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.lbl_Title.setText(charges.get(position).getTitle());
        holder.lbl_SubTitle.setText(charges.get(position).getSubTitle());

    }

    @Override
    public int getItemCount() {
        return charges.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout layout;
        TextView lbl_Title, lbl_SubTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            findItem(itemView);
        }

        void findItem(View view) {
            layout = view.findViewById(R.id.layout);
            lbl_Title = view.findViewById(R.id.lbl_Title);
            lbl_SubTitle = view.findViewById(R.id.lbl_SubTitle);
        }
    }

}
