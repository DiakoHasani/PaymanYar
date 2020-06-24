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
import ir.tdaapp.paymanyar.Model.Services.onClickLibrary;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Library;
import ir.tdaapp.paymanyar.R;

//مربوط به آداپتر کتابخانه
public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.MyViewHolder> {

    private Context context;
    List<VM_Library> libraries;
    private onClickLibrary onClickLibrary;

    public LibraryAdapter(Context context) {
        this.context = context;
        libraries = new ArrayList<>();
    }

    public void setOnClickLibrary(ir.tdaapp.paymanyar.Model.Services.onClickLibrary onClickLibrary) {
        this.onClickLibrary = onClickLibrary;
    }

    public void add(VM_Library library) {
        libraries.add(library);
        notifyItemInserted(libraries.size());
    }

    //در اینجا زمانی که کاربر یک کتاب دانلود می کند بک گروند آن را سلکت می کند
    public void addLibraryDownloaded(int libraryId) {

        for (int i = 0; i < libraries.size(); i++) {
            if (libraries.get(i).getId() == libraryId) {
                libraries.get(i).setDownloaded(true);
                notifyItemChanged(i);
            }
        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_library, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.lbl_Title.setText(libraries.get(position).getTitle());

        if (libraries.get(position).isDownloaded()) {
            holder.layout.setBackgroundColor(context.getResources().getColor(R.color.colorMySMS));
        } else {
            holder.layout.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
        }

        holder.download.setOnClickListener(view -> {
            onClickLibrary.clickDownload(libraries.get(position));
        });

        holder.layout.setOnClickListener(view -> {
            onClickLibrary.clickItem(libraries.get(position).getId());
        });

        holder.share.setOnClickListener(view -> {
            onClickLibrary.clickShare(libraries.get(position).getUrl(),libraries.get(position).getTitle());
        });
    }

    @Override
    public int getItemCount() {
        return libraries.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView lbl_Title;
        RelativeLayout layout;
        ImageView download,share;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            lbl_Title = itemView.findViewById(R.id.lbl_Title);
            download = itemView.findViewById(R.id.download);
            layout = itemView.findViewById(R.id.layout);
            share = itemView.findViewById(R.id.share);
        }
    }

}
