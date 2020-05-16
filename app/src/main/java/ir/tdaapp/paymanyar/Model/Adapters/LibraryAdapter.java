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

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_library, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.lbl_Title.setText(libraries.get(position).getTitle());

        holder.download.setOnClickListener(view -> {
            onClickLibrary.clickDownload(libraries.get(position).getUrl());
        });

        holder.layout.setOnClickListener(view -> {
            onClickLibrary.clickItem(libraries.get(position).getId());
        });
    }

    @Override
    public int getItemCount() {
        return libraries.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView lbl_Title;
        RelativeLayout download,layout;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            lbl_Title=itemView.findViewById(R.id.lbl_Title);
            download=itemView.findViewById(R.id.download);
            layout=itemView.findViewById(R.id.layout);
        }
    }

}
