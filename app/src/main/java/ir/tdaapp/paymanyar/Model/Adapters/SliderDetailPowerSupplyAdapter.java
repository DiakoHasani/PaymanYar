package ir.tdaapp.paymanyar.Model.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.paymanyar.R;

/**
 * مربوط به اسلایدر صفحه جزئیات نیروکار
 **/
public class SliderDetailPowerSupplyAdapter extends RecyclerView.Adapter<SliderDetailPowerSupplyAdapter.MyViewHolder> {

    List<String> urls;
    Context context;

    public SliderDetailPowerSupplyAdapter(Context context, List<String> urls) {
        this.context = context;
        this.urls = urls;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.slider_detail_power_supply, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Glide.with(context)
                .load(urls.get(position))
                .error(R.drawable.power_supply_network)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
        }
    }
}
