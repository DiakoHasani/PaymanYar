package ir.tdaapp.paymanyar.View.Dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.paymanyar.Model.Adapters.SavesGpsAdapter;
import ir.tdaapp.paymanyar.Model.Services.S_SavesGpsDialog;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseBottomSheetDialogFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_SavesGps;
import ir.tdaapp.paymanyar.Presenter.P_SavesGpsDialog;
import ir.tdaapp.paymanyar.R;

//در اینجا لیست نقشه های ذخیره شده نمایش داده می شود
public class SavesGpsDialog extends BaseBottomSheetDialogFragment implements S_SavesGpsDialog, View.OnClickListener {

    public static final String TAG = "SavesGpsDialog";

    P_SavesGpsDialog p_savesGpsDialog;
    ImageView ic_retry;
    SavesGpsAdapter adapter;
    RecyclerView recycler;
    LinearLayout noItem;

    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View view = View.inflate(getContext(), R.layout.saves_gps_dialog, null);

        findItem(view);
        implement();
        p_savesGpsDialog.start();

        dialog.setContentView(view);
    }

    void findItem(View view) {
        ic_retry = view.findViewById(R.id.ic_retry);
        recycler = view.findViewById(R.id.recycler);
        noItem = view.findViewById(R.id.noItem);
    }

    void implement() {
        p_savesGpsDialog = new P_SavesGpsDialog(getContext(), this);
        ic_retry.setOnClickListener(this);
    }

    //زمانی که عملیات گرفتن داده ها شروع شود ابتدا متد زیر فراخوانی می شود
    @Override
    public void OnStart() {
        adapter = new SavesGpsAdapter(getContext());
        adapter.setListener(new SavesGpsAdapter.ItemListener() {
            @Override
            public void RemoveITem(VM_SavesGps item) {
                p_savesGpsDialog.RemoveITem(item.getId());
            }

            @Override
            public void ShareItem(VM_SavesGps item) {
                p_savesGpsDialog.ShareItem(item);
            }
        });
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }

    //زمانی که گرفتن داده ها با خطا مواجه شود متد زیر فراخوانی می شود
    @Override
    public void onError() {
        ic_retry.setEnabled(true);
        ic_retry.setVisibility(View.VISIBLE);
        Toast.makeText(getContext(), getContext().getResources().getString(R.string.There_Was_an_Error_In_The_Application), Toast.LENGTH_SHORT).show();
    }

    //در اینجا تمامی المنت های صفحه مخفی می شود
    @Override
    public void onHideAll() {
        ic_retry.setVisibility(View.GONE);
        recycler.setVisibility(View.GONE);
        noItem.setVisibility(View.GONE);
    }

    //زمانی که عملیات به پایان برسد متد زیر فراخوانی می شود
    @Override
    public void onFinish() {

    }

    //در اینجا نقشه ها یکی یکی به رسایکلر اضافه می شوند
    @Override
    public void onAddGpsItem(VM_SavesGps gps) {
        adapter.Add(gps);
    }

    //اگر عملیات با موفقیت انجام شود رسایکلر ویو نمایش داده می شود
    @Override
    public void onShowRecycler() {
        recycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void onShowDontHaveItem() {
        noItem.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //p_savesGpsDialog.Cancel();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_retry:
                ic_retry.setEnabled(false);
                p_savesGpsDialog.start();
                break;
        }
    }
}
