package ir.tdaapp.paymanyar.Model.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Library;
import ir.tdaapp.paymanyar.R;

//مربوط به آداپتر کتابخانه
public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.MyViewHolder> {

    private Context context;
    List<VM_Library> libraries;

    public LibraryAdapter(Context context) {
        this.context = context;
        libraries = new ArrayList<>();
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
    }

    @Override
    public int getItemCount() {
        return libraries.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView lbl_Title;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            lbl_Title=itemView.findViewById(R.id.lbl_Title);
        }
    }

}
