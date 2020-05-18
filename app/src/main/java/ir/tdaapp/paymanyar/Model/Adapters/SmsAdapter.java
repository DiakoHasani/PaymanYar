package ir.tdaapp.paymanyar.Model.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.paymanyar.Model.Services.onClickSMS;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_SMS;
import ir.tdaapp.paymanyar.R;

public class SmsAdapter extends RecyclerView.Adapter<SmsAdapter.MyViewHolder> {

    private Context context;
    private List<VM_SMS> messages;
    private onClickSMS clickSMS;

    public SmsAdapter(Context context) {
        this.context = context;
        messages = new ArrayList<>();
    }

    public void add(VM_SMS sms) {
        messages.add(sms);
        notifyItemInserted(messages.size());
    }

    //در اینجا پیام از رسایکلر حذف می شود
    public void archiveSMS(String msgId) {
        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).getId().equalsIgnoreCase(msgId)) {
                messages.remove(i);
                notifyItemRemoved(i);
                notifyItemRangeChanged(i, messages.size());
            }
        }
    }

    public void setClickSMS(onClickSMS clickSMS) {
        this.clickSMS = clickSMS;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_sms, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.lbl_Title.setText(messages.get(position).getText());

        if (messages.get(position).isKird()) {
            holder.layout.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
        } else {
            holder.layout.setBackgroundColor(context.getResources().getColor(R.color.colorMySMS));
        }

        if (messages.get(position).isFevorit()) {
            holder.star.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_yellow));
        } else {
            holder.star.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_border_yellow));
        }

        holder.star.setOnClickListener(view -> {
            if (messages.get(position).isFevorit()) {
                clickSMS.onClickRemoveFevorit(messages.get(position).getId(), holder.star);
            } else {
                clickSMS.onClickAddFevorit(messages.get(position).getId(), holder.star);
            }
        });

        holder.archive.setOnClickListener(view -> {
            clickSMS.onClickArchive(messages.get(position).getId());
        });

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView lbl_Title;
        RelativeLayout layout;
        ImageView star, archive;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            lbl_Title = itemView.findViewById(R.id.lbl_Title);
            layout = itemView.findViewById(R.id.layout);
            star = itemView.findViewById(R.id.star);
            archive = itemView.findViewById(R.id.archive);
        }
    }

}
