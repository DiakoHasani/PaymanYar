package ir.tdaapp.paymanyar.Model.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.paymanyar.Model.Services.onClickChargeItem;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Charge;
import ir.tdaapp.paymanyar.R;

//مربوط به آداپتر شارژ
public class ChargeAdapter extends RecyclerView.Adapter<ChargeAdapter.MyViewHolder> {

    Context context;
    List<VM_Charge> charges;
    private onClickChargeItem onClickChargeItem;

    public ChargeAdapter(Context context) {
        this.context = context;
        charges = new ArrayList<>();
    }

    public void setOnClickChargeItem(ir.tdaapp.paymanyar.Model.Services.onClickChargeItem onClickChargeItem) {
        this.onClickChargeItem = onClickChargeItem;
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

        holder.lbl_LessThanAnHour.setVisibility(View.GONE);

        if (charges.get(position).isSpecial()) {
            holder.img.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_special_charge_store));
//            holder.anim.startShimmerAnimation();

            holder.specialLayout.setVisibility(View.VISIBLE);

            if (charges.get(position).getTotalHour() > 0) {

                int day = charges.get(position).getTotalHour() / 24;
                int hour = charges.get(position).getTotalHour() % 24;

                if (day > 0) {
                    holder.dayLayout.setVisibility(View.VISIBLE);
                    holder.lbl_Day.setText(String.valueOf(day));
                } else {
                    holder.dayLayout.setVisibility(View.GONE);
                }

                if (hour > 0) {
                    holder.hourLayout.setVisibility(View.VISIBLE);
                    holder.lbl_Hour.setText(String.valueOf(hour));
                } else {
                    holder.hourLayout.setVisibility(View.GONE);
                }

            } else {
                holder.dayLayout.setVisibility(View.GONE);
                holder.hourLayout.setVisibility(View.GONE);
                holder.lbl_LessThanAnHour.setVisibility(View.VISIBLE);
            }

        } else {
            holder.img.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_charge_store));
//            holder.anim.stopShimmerAnimation();

            holder.specialLayout.setVisibility(View.GONE);
        }

        holder.layout.setOnClickListener(view -> {
            if (onClickChargeItem != null) {
                onClickChargeItem.onClick(charges.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return charges.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        CardView layout;
        TextView lbl_Title, lbl_SubTitle, lbl_Day, lbl_Hour, lbl_LessThanAnHour;
        ImageView img;
        ShimmerFrameLayout anim;
        LinearLayout specialLayout, hourLayout, dayLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            findItem(itemView);
        }

        void findItem(View view) {
            layout = view.findViewById(R.id.layout);
            lbl_Title = view.findViewById(R.id.lbl_Title);
            lbl_SubTitle = view.findViewById(R.id.lbl_SubTitle);
            img = view.findViewById(R.id.img);
            anim = view.findViewById(R.id.anim);
            specialLayout = view.findViewById(R.id.specialLayout);
            lbl_Day = view.findViewById(R.id.lbl_Day);
            lbl_Hour = view.findViewById(R.id.lbl_Hour);
            hourLayout = view.findViewById(R.id.hourLayout);
            dayLayout = view.findViewById(R.id.dayLayout);
            lbl_LessThanAnHour = view.findViewById(R.id.lbl_LessThanAnHour);
        }
    }

}
