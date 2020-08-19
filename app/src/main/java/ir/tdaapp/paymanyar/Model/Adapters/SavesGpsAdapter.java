package ir.tdaapp.paymanyar.Model.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_SavesGps;
import ir.tdaapp.paymanyar.R;

//مربوط به آداپتر نقشه های ذخیره شده
public class SavesGpsAdapter extends RecyclerView.Adapter<SavesGpsAdapter.MyViewHolder> {

    Context context;
    List<VM_SavesGps> vals;
    ItemListener listener;

    public SavesGpsAdapter(Context context) {
        this.context = context;
        vals = new ArrayList<>();
    }

    //در اینجا نقشه ها اضافه می شوند
    public void Add(VM_SavesGps gps) {
        vals.add(gps);
        notifyItemInserted(vals.size());
    }

    void RemoveItem(int position) {
        vals.remove(position);
        notifyDataSetChanged();
    }

    public void setListener(ItemListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_saves_gps, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.lbl_Length.setText(vals.get(position).getLength() + "");
        holder.lbl_Wide.setText(vals.get(position).getWide() + "");
        holder.lbl_number.setText((position + 1) + "");

        holder.ic_close.setOnClickListener(view -> {
            if (this.listener != null) listener.RemoveITem(vals.get(position));
            RemoveItem(position);
        });

        holder.ic_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) listener.ShareItem(vals.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return vals.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout layout;
        ImageView ic_close, ic_share;
        TextView lbl_Wide, lbl_Length, lbl_number;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            findItem(itemView);
        }

        void findItem(View view) {
            layout = view.findViewById(R.id.layout);
            ic_close = view.findViewById(R.id.ic_close);
            ic_share = view.findViewById(R.id.ic_share);
            lbl_Wide = view.findViewById(R.id.lbl_Wide);
            lbl_Length = view.findViewById(R.id.lbl_Length);
            lbl_number = view.findViewById(R.id.lbl_number);
        }
    }

    public interface ItemListener {
        void RemoveITem(VM_SavesGps item);

        void ShareItem(VM_SavesGps item);
    }
}
