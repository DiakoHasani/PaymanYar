package ir.tdaapp.paymanyar.View.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import ir.tdaapp.li_utility.Codes.ShowPriceTextView;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Services.S_DetailsTenderFragment;
import ir.tdaapp.paymanyar.Model.Services.addTender;
import ir.tdaapp.paymanyar.Model.Services.onClickFevoritTender;
import ir.tdaapp.paymanyar.Model.Services.removeTender;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailsTender;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FilterTenderNotification;
import ir.tdaapp.paymanyar.Presenter.P_DetailsTenderFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Dialogs.ErrorAplicationDialog;
import pl.droidsonroids.gif.GifImageView;

//صفحه جزئیات مناقصه
public class DetailsTenderFragment extends BaseFragment implements S_DetailsTenderFragment, View.OnClickListener {


    private VM_FilterTenderNotification filter;

    //در اینجا آیدی آیتم بعدی ست می شود
    String NextTenderId = "";

    //در اینجا آیدی آیتم قبلی ست می شود
    String PrevTenderId = "";

    boolean isLike = false;

    onClickFevoritTender clickFevoritTender;

    boolean hasNextPrevTender = true;

    public DetailsTenderFragment(VM_FilterTenderNotification filter, boolean hasNextPrevTender, onClickFevoritTender clickFevoritTender) {
        this.filter = filter;
        this.hasNextPrevTender = hasNextPrevTender;
        this.clickFevoritTender = clickFevoritTender;
    }

    public final static String TAG = "DetailsTenderFragment";

    P_DetailsTenderFragment p_detailsTenderFragment;

    ScrollView detail;
    ImageView btn_reload;
    GifImageView loading;
    CardView btn_Analize, btn_Share, btn_Star, btn_Right, btn_Left;
    TextView lbl_PaymanyarCode, lbl_NationalEstimate, lbl_ReopeningDate, lbl_SendSuggestionsUp, lbl_GetDocumentsUp;
    TextView lbl_Place_of_Receipt_of_Documents, lbl_TenderDevice, lbl_website, lbl_Description;
    RelativeLayout background;
    LinearLayout subscribers;
    ImageView img_star;
    ErrorAplicationDialog soonAdded;
    TextView lbl_Major, lbl_CityName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_tender_fragment, container, false);

        findItem(view);
        implement();

        p_detailsTenderFragment.start(filter);

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
        lbl_SendSuggestionsUp = view.findViewById(R.id.lbl_SendSuggestionsUp);
        lbl_GetDocumentsUp = view.findViewById(R.id.lbl_GetDocumentsUp);
        lbl_Place_of_Receipt_of_Documents = view.findViewById(R.id.lbl_Place_of_Receipt_of_Documents);
        lbl_TenderDevice = view.findViewById(R.id.lbl_TenderDevice);
        lbl_website = view.findViewById(R.id.lbl_website);
        lbl_Description = view.findViewById(R.id.lbl_Description);
        background = view.findViewById(R.id.background);
        subscribers = view.findViewById(R.id.subscribers);
        img_star = view.findViewById(R.id.img_star);
        lbl_Major = view.findViewById(R.id.lbl_Major);
        lbl_CityName = view.findViewById(R.id.lbl_CityName);

        if (hasNextPrevTender) {
            btn_Right.setVisibility(View.VISIBLE);
            btn_Left.setVisibility(View.VISIBLE);
        } else {
            btn_Right.setVisibility(View.GONE);
            btn_Left.setVisibility(View.GONE);
        }
    }

    void implement() {
        p_detailsTenderFragment = new P_DetailsTenderFragment(getContext(), this);
        background.setOnClickListener(this);
        btn_Right.setOnClickListener(this);
        btn_Left.setOnClickListener(this);
        img_star.setOnClickListener(this);
        btn_reload.setOnClickListener(this);
        btn_Analize.setOnClickListener(this);

        btn_Share.setOnClickListener(this);
    }

    //در اینجا دکمه ها فعال یا غیرفعال می شوند
    void setEnabledButtons(boolean enable) {
        btn_Analize.setEnabled(enable);
        btn_Share.setEnabled(enable);
        btn_Star.setEnabled(enable);
        btn_Right.setEnabled(enable);
        btn_Left.setEnabled(enable);
    }

    //در اینجا به تکست ویوها مقدار پیش فرض داده می شود
    void setDefaultVals() {
        lbl_PaymanyarCode.setText("-");
        lbl_NationalEstimate.setText("-");
        lbl_ReopeningDate.setText("-");
        lbl_SendSuggestionsUp.setText("-");
        lbl_GetDocumentsUp.setText("-");
        lbl_Place_of_Receipt_of_Documents.setText("-");
        lbl_TenderDevice.setText("-");
        lbl_website.setText("-");
        lbl_Description.setText("-");
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void OnStart() {
        setEnabledButtons(false);
        setDefaultVals();
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
        btn_reload.setVisibility(View.INVISIBLE);
        loading.setVisibility(View.INVISIBLE);
        detail.setVisibility(View.INVISIBLE);
        subscribers.setVisibility(View.INVISIBLE);
    }

    //در اینجا جزئیات مناقصات در تکست ویوها ست می شوند
    @Override
    public void onGetDetail(VM_DetailsTender detailsTender) {
        try {

            detail.setVisibility(View.VISIBLE);

            lbl_Description.setText(detailsTender.getDescription());
            new ShowPriceTextView(detailsTender.getNationalEstimate(), lbl_NationalEstimate);
            lbl_PaymanyarCode.setText(detailsTender.getId() + "");
            lbl_Place_of_Receipt_of_Documents.setText(detailsTender.getPlace_of_Receipt_of_Documents());
            lbl_ReopeningDate.setText(detailsTender.getReopeningDate());
            lbl_TenderDevice.setText(detailsTender.getTenderDevice());
            lbl_website.setText(detailsTender.getWebsite());
            lbl_GetDocumentsUp.setText(detailsTender.getGetDocumentsUp());
            lbl_SendSuggestionsUp.setText(detailsTender.getSendSuggestionsUp());
            lbl_CityName.setText(p_detailsTenderFragment.getCityTitle(detailsTender.getCityId()));
            lbl_Major.setText(p_detailsTenderFragment.getMajorTitle(detailsTender.getMajorId()));

        } catch (Exception e) {
        }
    }

    //در اینجا اگر کاربر موجودی برای نمایش مناقصات پولی نداشته باشد آیتم مربوط به ویژه مشترکان نمایش داده می شود
    @Override
    public void onShowSubscribers() {
        subscribers.setVisibility(View.VISIBLE);
    }

    //در اینجا آیدی مناقصه بعدی گرفته می شود
    @Override
    public void onGetNextTender(String tenderId) {

        NextTenderId = tenderId;

        if (!tenderId.equalsIgnoreCase("")) {
            btn_Right.setEnabled(true);
        } else {
            btn_Right.setEnabled(false);
        }
    }

    //در اینجا آیدی مناقصه قبلی گرفته می شود
    @Override
    public void onGetPrevTender(String tenderId) {
        PrevTenderId = tenderId;

        if (!tenderId.equalsIgnoreCase("")) {
            btn_Left.setEnabled(true);
        } else {
            btn_Left.setEnabled(false);
        }
    }

    //در اینجا آیکون مربوط به علاقه مندی مناقصه ست می شود
    @Override
    public void isFevorit(boolean f) {
        isLike = f;

        if (f) {
            img_star.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_star_black));
        } else {
            img_star.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_star_border_black));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        p_detailsTenderFragment.Cancel(TAG);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.background:
                getActivity().onBackPressed();
                break;
            case R.id.btn_Right:

                filter.setTenderId(NextTenderId);
                p_detailsTenderFragment.start(filter);

                break;
            case R.id.btn_Left:

                filter.setTenderId(PrevTenderId);
                p_detailsTenderFragment.start(filter);

                break;

            case R.id.btn_reload:
                p_detailsTenderFragment.start(filter);
                break;

            case R.id.btn_Analize:

                soonAdded = new ErrorAplicationDialog(getString(R.string.Soon), getString(R.string.It_will_be_presented_in_the_next_version), getString(R.string.ok), R.drawable.ic_error, R.color.colorError, () -> {
                    soonAdded.dismiss();
                });
                soonAdded.show(getActivity().getSupportFragmentManager(), ErrorAplicationDialog.TAG);

                break;

            case R.id.btn_Share:
                String url = "http://tiptop.tdaapp.ir/Home/Index?TenderId=";
                if (filter.getTenderId() != null) {
                    url += filter.getTenderId();
                }
                p_detailsTenderFragment.share(getActivity(),url);
                break;

            case R.id.img_star:

                if (isLike) {
                    p_detailsTenderFragment.RemoveFevorit(filter.getTenderId(), new removeTender() {
                        @Override
                        public void onSuccess() {
                            isLike = false;
                            img_star.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_star_border_black));
                            clickFevoritTender.onClick(filter.getTenderId(), false);
                        }

                        @Override
                        public void onError(String error) {
                            isLike = true;
                            img_star.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_star_black));
                            clickFevoritTender.onClick(filter.getTenderId(), true);
                        }
                    });
                } else {

                    p_detailsTenderFragment.AddFavorit(filter.getTenderId(), new addTender() {
                        @Override
                        public void onSuccess() {
                            isLike = true;
                            img_star.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_star_black));
                            clickFevoritTender.onClick(filter.getTenderId(), true);
                        }

                        @Override
                        public void onError(String error) {
                            isLike = false;
                            img_star.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_star_border_black));
                            clickFevoritTender.onClick(filter.getTenderId(), false);
                        }
                    });
                }

                break;
        }
    }
}
