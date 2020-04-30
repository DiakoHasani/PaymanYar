package ir.tdaapp.paymanyar.Model.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.paymanyar.Model.Services.onClickTenderNotification;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotifications;
import ir.tdaapp.paymanyar.R;

//مربوط به آداپتر اطلاع رسانی مناقصات
public class TenderNotificationAdapter extends RecyclerView.Adapter<TenderNotificationAdapter.MyViewHolder> {

    Context context;
    List<VM_TenderNotifications> tenders;
    AlphaAnimation anim;
    onClickTenderNotification onClickTenderNotification;

    public TenderNotificationAdapter(Context context) {
        this.context = context;
        tenders = new ArrayList<>();
        anim = new AlphaAnimation(0.0f, 1.0f);
    }

    public void setOnClickTenderNotification(ir.tdaapp.paymanyar.Model.Services.onClickTenderNotification onClickTenderNotification) {
        this.onClickTenderNotification = onClickTenderNotification;
    }

    public void add(VM_TenderNotifications tender) {
        tenders.add(tender);
        notifyItemInserted(tenders.size());
    }

    //در اینجا آیتم ها به صورت انیمیشن نمایش داده می شوند
    private void setFadeAnimation(View view) {
        anim.setDuration(100);
        view.startAnimation(anim);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_tender_notification, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.lbl_Title.setText(tenders.get(position).getTitle());

        //در اینجا آیکون های رایگان یا غیر رایگان بودن آیتم ست می شود
        if (tenders.get(position).isFree()) {
            holder.ic_condition.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_free));
        } else {
            holder.ic_condition.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_none_free));
        }

        //در اینجا آیکون مربوط به مناقصه های که در لیست علاقه مندی وجود دارد ست می شود
        if (tenders.get(position).isStar()) {
            holder.ic_star.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_black));
        } else {
            holder.ic_star.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_border_black));
        }

        holder.layout.setOnClickListener(view -> {
            if (onClickTenderNotification != null) {
                onClickTenderNotification.onClick(tenders.get(position).getId());
            }
        });

//        setFadeAnimation(holder.itemView);
    }

    @Override
    public int getItemCount() {
        return tenders.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout layout;
        ImageView ic_condition, ic_star;
        TextView lbl_Title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            findItem(itemView);
        }

        void findItem(View view) {
            layout = view.findViewById(R.id.layout);
            ic_condition = view.findViewById(R.id.ic_condition);
            ic_star = view.findViewById(R.id.ic_star);
            lbl_Title = view.findViewById(R.id.lbl_Title);
        }
    }

}
