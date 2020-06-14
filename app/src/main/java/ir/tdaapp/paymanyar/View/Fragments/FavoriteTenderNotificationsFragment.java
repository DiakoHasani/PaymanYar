package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.shimmer.ShimmerFrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Adapters.TenderNotificationAdapter;
import ir.tdaapp.paymanyar.Model.Services.S_FavoriteTenderNotificationsFragment;
import ir.tdaapp.paymanyar.Model.Services.onClickFevoritTender;
import ir.tdaapp.paymanyar.Model.Services.onClickTenderNotification;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FilterTenderNotification;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotification;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotifications;
import ir.tdaapp.paymanyar.Presenter.P_FavoriteTenderNotificationsFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Dialogs.ErrorAplicationDialog;

//در اینجا مناقصات مورد علاقه نمایش داده می شود
public class FavoriteTenderNotificationsFragment extends BaseFragment implements S_FavoriteTenderNotificationsFragment {

    public static final String TAG="FavoriteTenderNotificationsFragment";

    Toolbar toolbar;
    RecyclerView recycler;
    ShimmerFrameLayout loading;
    P_FavoriteTenderNotificationsFragment p_favoriteTenderNotificationsFragment;
    TenderNotificationAdapter tenderNotificationAdapter;
    ErrorAplicationDialog errorAplicationDialog;
    onClickFevoritTender clickFevoritTender;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorite_tender_notifications, container, false);

        findItem(view);
        implement();
        setToolbar();
        p_favoriteTenderNotificationsFragment.start();

        return view;
    }

    void findItem(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        recycler = view.findViewById(R.id.recycler);
        loading = view.findViewById(R.id.loading);
    }

    void implement() {
        p_favoriteTenderNotificationsFragment = new P_FavoriteTenderNotificationsFragment(getContext(), this);
    }

    public void setClickFevoritTender(onClickFevoritTender clickFevoritTender) {
        this.clickFevoritTender = clickFevoritTender;
    }

    //در اینجا تنظیمات تولبار ست می شود
    void setToolbar() {

        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setTitle(getContext().getResources().getString(R.string.Favorite));
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });
        setHasOptionsMenu(true);
    }

    @Override
    public void OnStart() {
        tenderNotificationAdapter = new TenderNotificationAdapter(getContext());
        loading.startShimmerAnimation();
        recycler.setAdapter(tenderNotificationAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));

        tenderNotificationAdapter.setOnClickTenderNotification(new onClickTenderNotification() {
            @Override
            public void onClick(String id) {
                VM_FilterTenderNotification filter = new VM_FilterTenderNotification();
                filter.setTenderId(id);
                filter.setUserId(((MainActivity) getActivity()).getTbl_user().getUserId(getContext()));

                DetailsTenderFragment detailsTenderFragment = new DetailsTenderFragment(filter, true,true, (tenderId, fevorit) -> {
                    tenderNotificationAdapter.changeFevoritTender(tenderId, fevorit);
                    if (clickFevoritTender != null) {
                        clickFevoritTender.onClick(tenderId, fevorit);
                    }
                });

                ((MainActivity) getActivity()).onAddFragment(detailsTenderFragment, R.anim.fadein
                        , R.anim.fadeout, true, DetailsTenderFragment.TAG);
            }

            @Override
            public void onClickFavorit_Add(String id, ImageView icon) {
                tenderNotificationAdapter.setStart(id);
                if (clickFevoritTender != null) {
                    clickFevoritTender.onClick(id, true);
                }
            }

            @Override
            public void onClickFavorit_remove(String id, ImageView icon) {
                tenderNotificationAdapter.setStart(id);
                if (clickFevoritTender != null) {
                    clickFevoritTender.onClick(id, false);
                }
            }
        });
    }

    @Override
    public void onSuccess() {
        recycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideAll() {
        recycler.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
    }

    @Override
    public void onFinish() {
        loading.stopShimmerAnimation();
    }

    @Override
    public void onLoading(boolean load) {
        if (load){
            loading.setVisibility(View.VISIBLE);
        }else{
            loading.setVisibility(View.GONE);
        }
    }

    @Override
    public void onError(ResaultCode result) {
        String text = "";

        switch (result) {
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

        errorAplicationDialog = new ErrorAplicationDialog(getString(R.string.Error), text, getString(R.string.Again), R.drawable.ic_error, R.color.colorError, () -> {
            p_favoriteTenderNotificationsFragment.start();
            errorAplicationDialog.dismiss();
        });
        errorAplicationDialog.show(getActivity().getSupportFragmentManager(), ErrorAplicationDialog.TAG);
    }

    @Override
    public void onItem(VM_TenderNotifications item) {
        tenderNotificationAdapter.add(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        p_favoriteTenderNotificationsFragment.cancel(TAG);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }
}
