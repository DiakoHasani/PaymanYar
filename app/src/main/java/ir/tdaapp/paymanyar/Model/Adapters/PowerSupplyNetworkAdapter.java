package ir.tdaapp.paymanyar.Model.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.paymanyar.Model.Services.onClickPowerSupplyNetwork;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PowerSupplyNetwork;
import ir.tdaapp.paymanyar.R;

/**
 * آداپتر مربوط به شبکه تامین نیروکار
 **/
public class PowerSupplyNetworkAdapter extends RecyclerView.Adapter<PowerSupplyNetworkAdapter.MyViewHolder> {

    Context context;
    List<VM_PowerSupplyNetwork> powerSupplyNetworks;
    onClickPowerSupplyNetwork clickPowerSupplyNetwork;
    boolean showDeleteButton = false;

    public PowerSupplyNetworkAdapter(Context context) {
        this.context = context;
        powerSupplyNetworks = new ArrayList<>();
    }

    public void setShowDeleteButton(boolean showDeleteButton) {
        this.showDeleteButton = showDeleteButton;
    }

    public void setClickPowerSupplyNetwork(onClickPowerSupplyNetwork clickPowerSupplyNetwork) {
        this.clickPowerSupplyNetwork = clickPowerSupplyNetwork;
    }

    public void add(VM_PowerSupplyNetwork item) {
        powerSupplyNetworks.add(item);
        notifyItemInserted(powerSupplyNetworks.size());
    }

    public void delete(int id) {
        for (int i = 0; i < powerSupplyNetworks.size(); i++) {
            if (powerSupplyNetworks.get(i).getId() == id) {
                powerSupplyNetworks.remove(i);
                notifyItemRemoved(i);
                notifyItemRangeChanged(i, powerSupplyNetworks.size());
                break;
            }
        }
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

            //در اینجا انیمیشن آگهی ویژه ست می شود
            if (powerSupplyNetworks.get(position).isSpecial()) {
                holder.setAnimationSpecial(holder.layout2, true);
            } else {
                holder.setAnimationSpecial(holder.layout2, false);
            }

            //در اینجا ستاره آگهی ها که مربوط به عادی یا برنزی یا نقره ای بودن آگهی ها می باشد ست می شود
            //normal بدون ستاره
            //bronze یک ستاره
            //silver دو ستاره
            if (powerSupplyNetworks.get(position).getNetworkItemType() != null) {
                switch (powerSupplyNetworks.get(position).getNetworkItemType()) {
                    case normal:
                        holder.star1ImageView.setVisibility(View.GONE);
                        holder.star2ImageView.setVisibility(View.GONE);
                        break;
                    case bronze:
                        holder.star1ImageView.setVisibility(View.VISIBLE);
                        break;
                    case silver:
                        holder.star1ImageView.setVisibility(View.VISIBLE);
                        holder.star2ImageView.setVisibility(View.VISIBLE);
                        break;
                }
            } else {
                holder.star1ImageView.setVisibility(View.GONE);
                holder.star2ImageView.setVisibility(View.GONE);
            }

            Glide.with(context)
                    .load(powerSupplyNetworks.get(position).getImage())
                    .error(R.drawable.no_photography)
                    .into(holder.img);

            holder.layout.setOnClickListener(view -> {
                clickPowerSupplyNetwork.click(powerSupplyNetworks.get(position).getId());
            });

            holder.removeButton.setOnClickListener(view -> {
                clickPowerSupplyNetwork.remove(powerSupplyNetworks.get(position).getId());
            });

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
        ImageView img, removeButton, star1ImageView, star2ImageView;
        LinearLayout layout2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            findItem(itemView);

            if (showDeleteButton) {
                removeButton.setVisibility(View.VISIBLE);
            } else {
                removeButton.setVisibility(View.GONE);
            }
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
            layout2 = view.findViewById(R.id.layout2);
            removeButton = view.findViewById(R.id.removeButton);
            star1ImageView = view.findViewById(R.id.star1ImageView);
            star2ImageView = view.findViewById(R.id.star2ImageView);
        }

        /**
         * در اینجا انیمیشن آگهی ویژه ست می شود
         **/
        void setAnimationSpecial(LinearLayout layoutAnimation, boolean isAnimation) {
            if (isAnimation) {
                layoutAnimation.setVisibility(View.VISIBLE);
                Animation anim = new AlphaAnimation(0.0f, 1.0f);
                anim.setDuration(800);
                anim.setRepeatMode(Animation.REVERSE);
                anim.setRepeatCount(Animation.INFINITE);

                layoutAnimation.startAnimation(anim);
            } else {
                layoutAnimation.setVisibility(View.INVISIBLE);
                layoutAnimation.setAnimation(null);
            }
        }
    }
}
