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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_NewsPaper;
import ir.tdaapp.paymanyar.R;

//آداپتر روزنامه
public class NewsPaperAdapter extends RecyclerView.Adapter<NewsPaperAdapter.MyViewHolder> {

    Context context;
    List<VM_NewsPaper> newsPapers;

    public NewsPaperAdapter(Context context) {
        this.context = context;
        newsPapers = new ArrayList<>();
    }

    public void add(VM_NewsPaper newsPaper){
        newsPapers.add(newsPaper);
        notifyItemInserted(newsPapers.size());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_news_paper, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.lbl_title.setText(newsPapers.get(position).getTitle());

        Glide.with(context)
                .load(newsPapers.get(position).getImage())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return newsPapers.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView lbl_title;
        ImageView img;
        CardView btn_Seen, btn_Share;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            findItem(itemView);
        }

        void findItem(View view) {
            lbl_title = view.findViewById(R.id.lbl_title);
            img = view.findViewById(R.id.img);
            btn_Seen = view.findViewById(R.id.btn_Seen);
            btn_Share = view.findViewById(R.id.btn_Share);
        }
    }

}
