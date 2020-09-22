package ir.tdaapp.paymanyar.Model.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PowerSupplyNetwork;
import ir.tdaapp.paymanyar.R;

/**
 * آداپتر مربوط به شبکه تامین نیروکار
 **/
public class PowerSupplyNetworkAdapter extends RecyclerView.Adapter<PowerSupplyNetworkAdapter.MyViewHolder> {

    Context context;
    List<VM_PowerSupplyNetwork> powerSupplyNetworks;

    public PowerSupplyNetworkAdapter(Context context) {
        this.context = context;
        powerSupplyNetworks = new ArrayList<>();
    }

    public void add(VM_PowerSupplyNetwork item) {
        powerSupplyNetworks.add(item);
        notifyItemInserted(powerSupplyNetworks.size());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_power_supply_network, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        try {

            holder.lbl_job.setText(powerSupplyNetworks.get(position).getJobTitle());
            holder.lbl_name.setText(powerSupplyNetworks.get(position).getName());
            holder.lbl_work_experience.setText(powerSupplyNetworks.get(position).getWorkExperience());
            holder.lbl_cellPhone.setText(powerSupplyNetworks.get(position).getCellPhone());
            holder.lbl_City.setText(powerSupplyNetworks.get(position).getProvinceAndCity());
            holder.lbl_Date.setText(powerSupplyNetworks.get(position).getDate());

            Glide.with(context)
                    .load(powerSupplyNetworks.get(position).getImage())
                    .error(R.drawable.power_supply_network)
                    .into(holder.img);

        } catch (Exception e) {
        }
    }

    @Override
    public int getItemCount() {
        return powerSupplyNetworks.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        CardView layout;
        TextView lbl_job, lbl_name, lbl_work_experience, lbl_cellPhone, lbl_City, lbl_Date;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            findItem(itemView);
        }

        void findItem(View view) {
            layout = view.findViewById(R.id.layout);
            lbl_job = view.findViewById(R.id.lbl_job);
            lbl_name = view.findViewById(R.id.lbl_name);
            lbl_work_experience = view.findViewById(R.id.lbl_work_experience);
            lbl_cellPhone = view.findViewById(R.id.lbl_cellPhone);
            lbl_City = view.findViewById(R.id.lbl_City);
            lbl_Date = view.findViewById(R.id.lbl_Date);
            img = view.findViewById(R.id.img);
        }
    }
}
