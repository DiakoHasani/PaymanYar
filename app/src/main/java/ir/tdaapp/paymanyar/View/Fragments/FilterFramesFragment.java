package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Adapters.TenderNotificationAdapter;
import ir.tdaapp.paymanyar.Model.Services.S_FilterFramesFragment;
import ir.tdaapp.paymanyar.Model.Services.addTender;
import ir.tdaapp.paymanyar.Model.Services.onClickFevoritTender;
import ir.tdaapp.paymanyar.Model.Services.onClickTenderNotification;
import ir.tdaapp.paymanyar.Model.Services.removeTender;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FilterTenderNotification;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotifications;
import ir.tdaapp.paymanyar.Presenter.P_FilterFramesFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Dialogs.ErrorAplicationDialog;

//در این صفحه مناقصه های که کاربر آن ها را در باخبرم کن صفحه مناقصات فیلتر کرده است نمایش داده می شود
public class FilterFramesFragment extends BaseFragment implements S_FilterFramesFragment {

    public final static String TAG = "FilterFramesFragment";

    P_FilterFramesFragment p_filterFramesFragment;
    Toolbar toolbar;
    ShimmerFrameLayout loading;
    RecyclerView recycler;
    TenderNotificationAdapter tenderNotificationAdapter;
    LinearLayoutManager layoutManager;
    ErrorAplicationDialog errorAplicationDialog;
    int page = 0;
    int countTenders = 0;
    LinearLayout empty;
    onClickFevoritTender clickFevoritTender;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.filter_frames_fragment, container, false);

        findItem(view);
        implement();
        setToolbar();

        return view;
    }

    void findItem(View view) {
        loading = view.findViewById(R.id.loading);
        recycler = view.findViewById(R.id.recycler);
        toolbar = view.findViewById(R.id.toolbar);
        empty = view.findViewById(R.id.empty);
    }

    void implement() {
        p_filterFramesFragment = new P_FilterFramesFragment(getContext(), this);
    }

    //در اینجا تنظیمات تولبار ست می شود
    void setToolbar() {

        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setTitle(getContext().getResources().getString(R.string.filter_frames));
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });
        setHasOptionsMenu(true);
    }

    public void setClickFevoritTender(onClickFevoritTender clickFevoritTender) {
        this.clickFevoritTender = clickFevoritTender;
    }

    @Override
    public void onResume() {
        super.onResume();

        new Handler().postDelayed(() -> {
            p_filterFramesFragment.start(page);
        }, 200);

    }

    @Override
    public void OnStart() {
        loading.startShimmerAnimation();
        tenderNotificationAdapter = new TenderNotificationAdapter(getContext());
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(tenderNotificationAdapter);

        //زمانی که کاربر یک روی یکی از مناقصات کلیک کند متد زیر فراخوانی شده و آی دی آن را به ما می دهد
        tenderNotificationAdapter.setOnClickTenderNotification(new onClickTenderNotification() {
            @Override
            public void onClick(String id) {
                VM_FilterTenderNotification filter = new VM_FilterTenderNotification();
                filter.setTenderId(id);
                filter.setUserId(((MainActivity) getActivity()).getTbl_user().getUserId(getContext()));

                DetailsTenderFragment detailsTenderFragment = new DetailsTenderFragment(filter, false, (tenderId, fevorit) -> {
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
                p_filterFramesFragment.AddFavorit(id, new addTender() {
                    @Override
                    public void onSuccess() {
                        tenderNotificationAdapter.setStart(id);
                        if (clickFevoritTender != null) {
                            clickFevoritTender.onClick(id, true);
                        }
                    }

                    @Override
                    public void onError(String error) {
                    }
                });
            }

            @Override
            public void onClickFavorit_remove(String id, ImageView icon) {
                p_filterFramesFragment.RemoveFevorit(id, new removeTender() {
                    @Override
                    public void onSuccess() {
                        tenderNotificationAdapter.setStart(id);
                        if (clickFevoritTender != null) {
                            clickFevoritTender.onClick(id, false);
                        }
                    }

                    @Override
                    public void onError(String error) {
                    }
                });
            }
        });
    }

    @Override
    public void onError(ResaultCode resault) {
        String text = "";

        switch (resault) {
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
            p_filterFramesFragment.start(page);
            errorAplicationDialog.dismiss();
        });
        errorAplicationDialog.show(getActivity().getSupportFragmentManager(), ErrorAplicationDialog.TAG);
    }

    @Override
    public void onFinish() {
        loading.stopShimmerAnimation();
    }

    @Override
    public void onHideAll() {
        loading.setVisibility(View.GONE);
        recycler.setVisibility(View.GONE);
        empty.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess() {
        recycler.setVisibility(View.VISIBLE);
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
    public void onEmpty() {
        empty.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCountTenders(int count) {
        countTenders = count;
    }

    @Override
    public void onItemTenders(VM_TenderNotifications item) {
        tenderNotificationAdapter.add(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        p_filterFramesFragment.cancel(TAG);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }
}
