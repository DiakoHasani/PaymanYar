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
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_SMS;
import ir.tdaapp.paymanyar.R;

public class SmsAdapter extends RecyclerView.Adapter<SmsAdapter.MyViewHolder> {

    private Context context;
    private List<VM_SMS> messages;

    public SmsAdapter(Context context) {
        this.context = context;
        messages=new ArrayList<>();
    }

    public void add(VM_SMS sms){
        messages.add(sms);
        notifyItemInserted(messages.size());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_sms,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.lbl_Title.setText(messages.get(position).getText());

        if (messages.get(position).isKird()){
            holder.layout.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
        }else{
            holder.layout.setBackgroundColor(context.getResources().getColor(R.color.colorMySMS));
        }

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView lbl_Title;
        RelativeLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            lbl_Title=itemView.findViewById(R.id.lbl_Title);
            layout=itemView.findViewById(R.id.layout);
        }
    }

}
