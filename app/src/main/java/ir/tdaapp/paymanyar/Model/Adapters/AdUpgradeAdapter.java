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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.paymanyar.Model.Services.onClickAdUpgrade;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_AdUpgrade;
import ir.tdaapp.paymanyar.R;

/**
 * آداپتر مربوط به ارتقا آگهی
 **/
public class AdUpgradeAdapter extends RecyclerView.Adapter<AdUpgradeAdapter.MyViewHolder> {

    Context context;
    List<VM_AdUpgrade> adUpgrades;
    onClickAdUpgrade clickAdUpgrade;

    public AdUpgradeAdapter(Context context) {
        this.context = context;
        adUpgrades = new ArrayList<>();
    }

    public void setClickAdUpgrade(onClickAdUpgrade clickAdUpgrade) {
        this.clickAdUpgrade = clickAdUpgrade;
    }

    public void add(VM_AdUpgrade adUpgrade) {
        adUpgrades.add(adUpgrade);
        notifyItemInserted(adUpgrades.size());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_ad_upgrade, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        try {

            holder.lbl_Title.setText(adUpgrades.get(position).getPrice() + " " + context.getString(R.string.Toman));
            holder.lbl_Description.setText(adUpgrades.get(position).getDescription());

            if (position == 0) {
                holder.typeUpgrade.setBackground(context.getResources().getDrawable(R.drawable.ad_upgrade_level_bronze));
            } else if (position == 1) {
                holder.typeUpgrade.setBackground(context.getResources().getDrawable(R.drawable.ad_upgrade_level_silver));
            } else {
                holder.typeUpgrade.setBackground(context.getResources().getDrawable(R.drawable.ad_upgrade_level_golden));
            }

            holder.layout.setOnClickListener(view -> {
                if (clickAdUpgrade != null) {
                    clickAdUpgrade.click(adUpgrades.get(position).getId());
                }
            });

        } catch (Exception e) {
        }

    }

    @Override
    public int getItemCount() {
        return adUpgrades.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout typeUpgrade;
        TextView lbl_Title, lbl_Description;
        CardView layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            findItem(itemView);
        }

        void findItem(View view) {
            typeUpgrade = view.findViewById(R.id.typeUpgrade);
            lbl_Title = view.findViewById(R.id.lbl_Title);
            lbl_Description = view.findViewById(R.id.lbl_Description);
            layout = view.findViewById(R.id.layout);
        }
    }
}
