package ir.tdaapp.paymanyar.Model.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.paymanyar.Model.Enums.FileUploadAnalizeTenderType;
import ir.tdaapp.paymanyar.Model.Services.onClickFileUpload_AnalizeTender;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FileUploadAnalizeTender;
import ir.tdaapp.paymanyar.R;

//مربوط به آداپتر فایل ها در صفحه آنالیز مناقصات
public class FileUpload_AnalizeTenderAdapter extends RecyclerView.Adapter<FileUpload_AnalizeTenderAdapter.MyViewHolder> {

    Context context;
    List<VM_FileUploadAnalizeTender> vals;
    onClickFileUpload_AnalizeTender clickFileUpload_analizeTender;

    public FileUpload_AnalizeTenderAdapter(Context context, List<VM_FileUploadAnalizeTender> vals) {
        this.context = context;
        this.vals = vals;
    }

    public void addFile(VM_FileUploadAnalizeTender v) {
        int position = 0;
        for (int i = 0; i < vals.size(); i++) {
            if (vals.get(i).getId() == v.getId()) {
                position = i;
                break;
            }
        }

        notifyItemChanged(position);
    }

    //در اینجا یک فایل را پاک می کند
    public void clearFile(VM_FileUploadAnalizeTender v) {
        int position = 0;
        for (int i = 0; i < vals.size(); i++) {
            if (vals.get(i).getId() == v.getId()) {
                position = i;
                break;
            }
        }

        vals.get(position).setType(FileUploadAnalizeTenderType.empty);
        vals.get(position).setPath("");
        notifyItemChanged(position);
    }

    public void setClickFileUpload_analizeTender(onClickFileUpload_AnalizeTender clickFileUpload_analizeTender) {
        this.clickFileUpload_analizeTender = clickFileUpload_analizeTender;
    }

    //در اینجا آدرس فایل ها برگشت داده می شوند
    public List<String> getPathFiles() {
        List<String> paths = new ArrayList<>();

        for (int i = 0; i < vals.size(); i++) {
            if (!vals.get(i).getPath().equalsIgnoreCase("")) {
                paths.add(vals.get(i).getPath());
            }
        }
        return paths;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_add_documents, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setType(vals.get(position).getType());

        if (clickFileUpload_analizeTender != null) {

            holder.layout.setOnClickListener(view -> {
                clickFileUpload_analizeTender.onClickFile(vals.get(position));
            });

            holder.close.setOnClickListener(view -> {
                clearFile(vals.get(position));
                clickFileUpload_analizeTender.onClickClose(vals.get(position));
            });

        }
    }

    @Override
    public int getItemCount() {
        return vals.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout empty_Layout, file_Layout;
        ImageView fileIcon, close;
        CardView layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            findItem(itemView);
        }

        void findItem(View view) {
            empty_Layout = view.findViewById(R.id.empty_Layout);
            file_Layout = view.findViewById(R.id.file_Layout);
            fileIcon = view.findViewById(R.id.fileIcon);
            close = view.findViewById(R.id.close);
            layout = view.findViewById(R.id.layout);
        }

        ///در اینجا نوع فایل برای نمایش ست می شود
        void setType(FileUploadAnalizeTenderType type) {
            switch (type) {
                case empty:
                    empty_Layout.setVisibility(View.VISIBLE);
                    file_Layout.setVisibility(View.GONE);
                    break;
                case jpg:
                    empty_Layout.setVisibility(View.GONE);
                    file_Layout.setVisibility(View.VISIBLE);

                    file_Layout.setBackgroundColor(context.getResources().getColor(R.color.colorJPG));
                    fileIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_jpg_logo));
                    break;
                case pdf:
                    empty_Layout.setVisibility(View.GONE);
                    file_Layout.setVisibility(View.VISIBLE);

                    file_Layout.setBackgroundColor(context.getResources().getColor(R.color.colorPDF));
                    fileIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_pdf_logo));
                    break;
                case png:
                    empty_Layout.setVisibility(View.GONE);
                    file_Layout.setVisibility(View.VISIBLE);

                    file_Layout.setBackgroundColor(context.getResources().getColor(R.color.colorPNG));
                    fileIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_png_logo));
                    break;
                case rar:
                    empty_Layout.setVisibility(View.GONE);
                    file_Layout.setVisibility(View.VISIBLE);

                    file_Layout.setBackgroundColor(context.getResources().getColor(R.color.colorRAR));
                    fileIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_zip_logo));
                    break;
                case zip:
                    empty_Layout.setVisibility(View.GONE);
                    file_Layout.setVisibility(View.VISIBLE);

                    file_Layout.setBackgroundColor(context.getResources().getColor(R.color.colorZIP));
                    fileIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_zip_logo));
                    break;
                case word:
                    empty_Layout.setVisibility(View.GONE);
                    file_Layout.setVisibility(View.VISIBLE);

                    file_Layout.setBackgroundColor(context.getResources().getColor(R.color.colorWord));
                    fileIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_word_logo));
                    break;
                case excel:
                    empty_Layout.setVisibility(View.GONE);
                    file_Layout.setVisibility(View.VISIBLE);

                    file_Layout.setBackgroundColor(context.getResources().getColor(R.color.colorExcel));
                    fileIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_excel_logo));
                    break;
                case powerPoint:
                    empty_Layout.setVisibility(View.GONE);
                    file_Layout.setVisibility(View.VISIBLE);

                    file_Layout.setBackgroundColor(context.getResources().getColor(R.color.colorPowerPoint));
                    fileIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_powerpoint_file));
                    break;
            }
        }
    }
}
