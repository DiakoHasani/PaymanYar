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
import de.hdodenhof.circleimageview.CircleImageView;
import ir.tdaapp.paymanyar.Model.Services.onClickSupport;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Support;
import ir.tdaapp.paymanyar.R;

public class SupportAdapter extends RecyclerView.Adapter<SupportAdapter.MyViewHolder> {

    List<VM_Support> supports;
    Context context;

    onClickSupport clickSupport;

    public SupportAdapter(Context context) {
        this.context = context;
        supports = new ArrayList<>();
    }

    public void add(VM_Support support) {
        supports.add(support);
        notifyItemInserted(supports.size());
    }

    public void setClickSupport(onClickSupport clickSupport) {
        this.clickSupport = clickSupport;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_support, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Glide.with(context)
                .load(supports.get(position).getImage())
                .error(R.drawable.ic_profile)
                .into(holder.img_profile);

        holder.lbl_title.setText(supports.get(position).getName());
        holder.lbl_description.setText(supports.get(position).getDescription());
        holder.lbl_cellphone.setText(supports.get(position).getCellPhone());

        holder.img_call.setOnClickListener(view -> {
            if (clickSupport != null) {
                clickSupport.onClickCall(supports.get(position).getCellPhone());
            }
        });

        holder.img_sms.setOnClickListener(view -> {
            if (clickSupport != null) {
                clickSupport.onClickSMS(supports.get(position).getWhatsApp());
            }
        });

        holder.img_telegram.setOnClickListener(view -> {
            if (clickSupport != null){
                clickSupport.onClickTelegram(supports.get(position).getTelegram());
            }
        });

        holder.img_email.setOnClickListener(view -> {
            if (clickSupport != null){
                clickSupport.onClickEmail(supports.get(position).getEmail());
            }
        });

    }

    @Override
    public int getItemCount() {
        return supports.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        CircleImageView img_profile;
        TextView lbl_title, lbl_description, lbl_cellphone;
        ImageView img_sms, img_telegram, img_call,img_email;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            findItem(itemView);

        }

        void findItem(View view) {
            img_profile = view.findViewById(R.id.img_profile);
            lbl_title = view.findViewById(R.id.lbl_title);
            lbl_description = view.findViewById(R.id.lbl_description);
            lbl_cellphone = view.findViewById(R.id.lbl_cellphone);
            img_sms = view.findViewById(R.id.img_sms);
            img_telegram = view.findViewById(R.id.img_telegram);
            img_call = view.findViewById(R.id.img_call);
            img_email = view.findViewById(R.id.img_email);
        }

    }
}
