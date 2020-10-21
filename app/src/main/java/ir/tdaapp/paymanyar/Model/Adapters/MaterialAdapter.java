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
import ir.tdaapp.paymanyar.Model.Services.onClickPowerSupplyNetwork;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Material;
import ir.tdaapp.paymanyar.R;

/**
 * آداپتر صفحه مصالح
 **/
public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.MyViewHolder> {

    Context context;
    List<VM_Material> vals;
    onClickPowerSupplyNetwork clickPowerSupplyNetwork;

    public MaterialAdapter(Context context) {
        this.context = context;
        vals = new ArrayList<>();
    }

    public void setClickPowerSupplyNetwork(onClickPowerSupplyNetwork clickPowerSupplyNetwork) {
        this.clickPowerSupplyNetwork = clickPowerSupplyNetwork;
    }

    public void add(VM_Material material) {
        vals.add(material);
        notifyItemInserted(vals.size());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_material, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        try {

            holder.lbl_Material.setText(vals.get(position).getMaterial());
            holder.lbl_cellPhone.setText(vals.get(position).getCellPhone());
            holder.lbl_City.setText(vals.get(position).getProvinceAndCity());
            holder.lbl_Date.setText(vals.get(position).getDate());
            holder.lbl_Price.setText(vals.get(position).getPrice());

            switch (vals.get(position).getAdType()) {
                case Sales:
                    holder.lbl_AdType.setText(context.getString(R.string.Sales));
                    break;
                case Buy:
                    holder.lbl_AdType.setText(context.getString(R.string.Buy));
                    break;
            }

            Glide.with(context)
                    .load(vals.get(position).getImage())
                    .error(R.drawable.machinery_image)
                    .into(holder.img);

            holder.layout.setOnClickListener(view -> {
                clickPowerSupplyNetwork.click(vals.get(position).getId());
            });

        } catch (Exception e) {
        }
    }

    @Override
    public int getItemCount() {
        return vals.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        CardView layout;
        TextView lbl_Material, lbl_AdType, lbl_Price, lbl_cellPhone, lbl_City, lbl_Date;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            findItem(itemView);
        }

        void findItem(View view) {
            layout = view.findViewById(R.id.layout);
            lbl_Material = view.findViewById(R.id.lbl_Material);
            lbl_AdType = view.findViewById(R.id.lbl_AdType);
            lbl_Price = view.findViewById(R.id.lbl_Price);
            lbl_cellPhone = view.findViewById(R.id.lbl_cellPhone);
            lbl_City = view.findViewById(R.id.lbl_City);
            lbl_Date = view.findViewById(R.id.lbl_Date);
            img = view.findViewById(R.id.img);
        }
    }
}
