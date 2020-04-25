package ir.tdaapp.paymanyar.View.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Services.S_DetailsTenderDialog;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseDialogFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailsTender;
import ir.tdaapp.paymanyar.Presenter.P_DetailsTenderDialog;
import ir.tdaapp.paymanyar.R;
import pl.droidsonroids.gif.GifImageView;

//دیالوگ مربوط به جزئیات مناقصات
public class DetailsTenderDialog extends BaseDialogFragment implements S_DetailsTenderDialog {

    public DetailsTenderDialog(int id) {
        Id = id;
    }

    private int Id;
    public final static String TAG = "DetailsTenderDialog";

    P_DetailsTenderDialog p_detailsTenderDialog;

    ScrollView detail;
    ImageView btn_reload;
    GifImageView loading;
    CardView btn_Analize, btn_Share, btn_Star, btn_Right, btn_Left;
    TextView lbl_PaymanyarCode, lbl_NationalEstimate, lbl_ReopeningDate, SendSuggestionsUp, GetDocumentsUp;
    TextView lbl_Place_of_Receipt_of_Documents, lbl_TenderDevice, lbl_website, lbl_Description;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.details_tender_dialog, container, false);

        findItem(view);
        implement();

        p_detailsTenderDialog.start(Id);

        return view;
    }

    void findItem(View view) {
        detail = view.findViewById(R.id.detail);
        btn_reload = view.findViewById(R.id.btn_reload);
        loading = view.findViewById(R.id.loading);
        btn_Analize = view.findViewById(R.id.btn_Analize);
        btn_Share = view.findViewById(R.id.btn_Share);
        btn_Star = view.findViewById(R.id.btn_Star);
        btn_Right = view.findViewById(R.id.btn_Right);
        btn_Left = view.findViewById(R.id.btn_Left);
        lbl_PaymanyarCode = view.findViewById(R.id.lbl_PaymanyarCode);
        lbl_NationalEstimate = view.findViewById(R.id.lbl_NationalEstimate);
        lbl_ReopeningDate = view.findViewById(R.id.lbl_ReopeningDate);
        SendSuggestionsUp = view.findViewById(R.id.SendSuggestionsUp);
        GetDocumentsUp = view.findViewById(R.id.GetDocumentsUp);
        lbl_Place_of_Receipt_of_Documents = view.findViewById(R.id.lbl_Place_of_Receipt_of_Documents);
        lbl_TenderDevice = view.findViewById(R.id.lbl_TenderDevice);
        lbl_website = view.findViewById(R.id.lbl_website);
        lbl_Description = view.findViewById(R.id.lbl_Description);
    }

    void implement() {
        p_detailsTenderDialog = new P_DetailsTenderDialog(getContext(), this);
    }

    //در اینجا دکمه ها فعال یا غیرفعال می شوند
    void setEnabledButtons(boolean enable) {
        btn_Analize.setEnabled(enable);
        btn_Share.setEnabled(enable);
        btn_Star.setEnabled(enable);
        btn_Right.setEnabled(enable);
        btn_Left.setEnabled(enable);
    }

    @Override
    public void OnStart() {
        setEnabledButtons(false);
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
    public void onFinish() {
        setEnabledButtons(true);
    }

    @Override
    public void onError(ResaultCode result) {

        String message = "";

        switch (result) {
            case NetworkError:
                message = getString(R.string.please_Checked_Your_Internet_Connection);
                break;
            case TimeoutError:
                message = getString(R.string.YourInternetIsVrySlow);
                break;
            case ServerError:
                message = getString(R.string.There_Was_an_Error_In_The_Server);
                break;
            case ParseError:
            case Error:
                message = getString(R.string.There_Was_an_Error_In_The_Application);
                break;
        }

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        btn_reload.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideAll() {
        btn_reload.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
        detail.setVisibility(View.GONE);
    }

    //در اینجا جزئیات مناقصات در تکست ویوها ست می شوند
    @Override
    public void onGetDetail(VM_DetailsTender detailsTender) {
        try {

            detail.setVisibility(View.VISIBLE);

            lbl_Description.setText(detailsTender.getDescription());
            lbl_NationalEstimate.setText(detailsTender.getNationalEstimate());
            lbl_PaymanyarCode.setText(detailsTender.getId() + "");
            lbl_Place_of_Receipt_of_Documents.setText(detailsTender.getPlace_of_Receipt_of_Documents());
            lbl_ReopeningDate.setText(detailsTender.getReopeningDate());
            lbl_TenderDevice.setText(detailsTender.getTenderDevice());
            lbl_website.setText(detailsTender.getWebsite());

        } catch (Exception e) {
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        p_detailsTenderDialog.Cancel(TAG);
    }
}
