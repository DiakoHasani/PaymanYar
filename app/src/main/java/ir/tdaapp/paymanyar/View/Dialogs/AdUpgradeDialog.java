package ir.tdaapp.paymanyar.View.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Adapters.AdUpgradeAdapter;
import ir.tdaapp.paymanyar.Model.Services.S_AdUpgradeDialog;
import ir.tdaapp.paymanyar.Model.Services.onClickAdUpgrade;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseDialogFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_AdUpgrade;
import ir.tdaapp.paymanyar.Presenter.P_AdUpgradeDialog;
import ir.tdaapp.paymanyar.R;
import pl.droidsonroids.gif.GifImageView;

/**
 * مربوط به دیالوگ ارتقا آگهی نیروکار یا مصالح یا ماشین آلات
 **/
public class AdUpgradeDialog extends BaseDialogFragment implements S_AdUpgradeDialog, View.OnClickListener {

    public static final String TAG = "AdUpgradeDialog";

    RecyclerView recycler;
    ImageView reload;
    GifImageView loading;
    P_AdUpgradeDialog p_adUpgradeDialog;
    AdUpgradeAdapter adUpgradeAdapter;
    LinearLayoutManager layoutManager;
    TextView btn_neverMind;
    onClickAdUpgrade clickAdUpgrade;

    public AdUpgradeDialog(onClickAdUpgrade clickAdUpgrade) {
        this.clickAdUpgrade = clickAdUpgrade;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.ad_upgrade_dialog, container, false);

        findItem(view);
        implement();

        p_adUpgradeDialog.start();

        return view;
    }

    void findItem(View view) {
        recycler = view.findViewById(R.id.recycler);
        reload = view.findViewById(R.id.reload);
        loading = view.findViewById(R.id.loading);
        btn_neverMind = view.findViewById(R.id.btn_neverMind);
    }

    void implement() {
        p_adUpgradeDialog = new P_AdUpgradeDialog(getContext(), this);
        reload.setOnClickListener(this);
        btn_neverMind.setOnClickListener(this);
    }

    @Override
    public void OnStart() {
        adUpgradeAdapter = new AdUpgradeAdapter(getContext());
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        recycler.setAdapter(adUpgradeAdapter);
        recycler.setLayoutManager(layoutManager);
        adUpgradeAdapter.setClickAdUpgrade(id -> {
            clickAdUpgrade.click(id);
            dismiss();
        });
    }

    @Override
    public void onLoading(boolean load) {
        if (load) {
            loading.setVisibility(View.VISIBLE);
        } else {
            loading.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSuccess() {
        recycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideAll() {
        recycler.setVisibility(View.GONE);
        reload.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
    }

    @Override
    public void onError(ResaultCode resaultCode) {
        reload.setVisibility(View.VISIBLE);

        String text = "";

        switch (resaultCode) {
            case NetworkError:
                text = getString(R.string.please_Checked_Your_Internet_Connection);
                break;
            case TimeoutError:
                text = getString(R.string.YourInternetIsVrySlow);
                break;
            case ServerError:
                text = getString(R.string.There_Was_an_Error_In_The_Server);
                break;
            case ParseError:
            case Error:
                text = getString(R.string.There_Was_an_Error_In_The_Application);
                break;
        }

        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onItem(VM_AdUpgrade upgrade) {
        adUpgradeAdapter.add(upgrade);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        p_adUpgradeDialog.cancel(TAG);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reload:
                p_adUpgradeDialog.start();
                break;
            case R.id.btn_neverMind:
                dismiss();
                break;
        }
    }
}
