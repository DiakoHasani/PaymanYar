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
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_HomeSlider;
import ir.tdaapp.paymanyar.R;

//مربوط به آداپتر اسلایدر صفحه اصلی
public class SliderHomeAdapter extends RecyclerView.Adapter<SliderHomeAdapter.MyViewHolde> {

    Context context;
    List<VM_HomeSlider> vals;

    public SliderHomeAdapter(Context context) {
        this.context = context;
        vals = new ArrayList<>();
    }

    //در اینجا یک اسلایدر جدید اضافه می شود
    public void add(VM_HomeSlider slider) {
        vals.add(slider);
        notifyItemInserted(vals.size() + 1);
    }

    @NonNull
    @Override
    public MyViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewpager_slider_home, parent, false);
        return new MyViewHolde(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolde holder, int position) {

        Glide.with(context)
                .load(vals.get(position).getUrl())
                .error(R.drawable.ic_loading)
                .into(holder.img);

        holder.lbl_Title.setText(vals.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return vals.size();
    }

    class MyViewHolde extends RecyclerView.ViewHolder {

        ImageView img;
        TextView lbl_Title;

        public MyViewHolde(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            lbl_Title = itemView.findViewById(R.id.lbl_Title);
        }
    }
}
