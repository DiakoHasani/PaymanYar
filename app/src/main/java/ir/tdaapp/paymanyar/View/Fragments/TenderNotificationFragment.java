package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Adapters.TenderNotificationAdapter;
import ir.tdaapp.paymanyar.Model.Services.S_TenderNotificationFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_City;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Major;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotification;
import ir.tdaapp.paymanyar.Presenter.P_TenderNotificationFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Activitys.ToolsActivity;
import ir.tdaapp.paymanyar.View.Dialogs.ErrorAplicationDialog;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

//صفحه مربوط به اطلاع رسانی مناقصات
public class TenderNotificationFragment extends BaseFragment implements S_TenderNotificationFragment {

    public final static String TAG = "TenderNotificationFragment";
    P_TenderNotificationFragment p_tenderNotificationFragment;
    Toolbar toolBar;
    RecyclerView recycler;
    ShimmerFrameLayout Loading;
    int page = 0;
    boolean isLoading = false;
    ProgressBar loading_paging;
    TenderNotificationAdapter tenderNotificationAdapter;
    LinearLayoutManager layoutManager;
    SearchableSpinner cmb_City,cmb_Major;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tender_notification_fragment, container, false);

        findItem(view);
        implement();
        setToolbar();

        p_tenderNotificationFragment.getSpinnerDatas();
        p_tenderNotificationFragment.start(page);

        return view;
    }

    void findItem(View view) {
        toolBar = view.findViewById(R.id.toolBar);
        recycler = view.findViewById(R.id.recycler);
        Loading = view.findViewById(R.id.Loading);
        loading_paging = view.findViewById(R.id.loading_paging);
        cmb_City = view.findViewById(R.id.cmb_City);
        cmb_Major = view.findViewById(R.id.cmb_Major);
    }

    void implement() {
        p_tenderNotificationFragment = new P_TenderNotificationFragment(getContext(), this);

        recycler.setOnScrollListener(pOnScrollListener);
    }

    //در اینجا تنظیمات تولبار ست می شود
    void setToolbar() {

        toolBar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolBar.setTitle(getContext().getResources().getString(R.string.TenderNotification));
        ((MainActivity) getActivity()).setSupportActionBar(toolBar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        toolBar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });
        setHasOptionsMenu(true);
    }

    //در اینجا چک می کند که زمان پیجینگ رسیده است اگر رسیده باشد عملیات را شروع می کند
    RecyclerView.OnScrollListener pOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (!isLoading) {
                if (isLastItemDisplaying()) {
                    isLoading = true;

                    onLoadingPaging(true);
                    p_tenderNotificationFragment.start(++page);
                }
            }
        }
    };

    //در اینجا اگر مقدار ترو برگشت داده شود یعنی زمان پیجینگ رسیده است و نیاز به خواندن داده از سرور می باشد
    boolean isLastItemDisplaying() {

        if (!isLoading) {

            int visibleItemCount = layoutManager.getChildCount() + 5;
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= PAGE_SIZE) {
                isLoading = true;
                return true;
            }
        }

        return false;
    }

    @Override
    public void OnStart() {
        Loading.startShimmerAnimation();

        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        tenderNotificationAdapter = new TenderNotificationAdapter(getContext());
        recycler.setAdapter(tenderNotificationAdapter);
        recycler.setLayoutManager(layoutManager);
    }

    @Override
    public void onSuccess() {
    }

    @Override
    public void onHideAll() {
        recycler.setVisibility(View.GONE);
        Loading.setVisibility(View.GONE);
        loading_paging.setVisibility(View.GONE);
    }

    @Override
    public void onFinish() {
        Loading.stopShimmerAnimation();
        isLoading = false;
    }

    @Override
    public void onLoading(boolean load) {
        if (load) {
            Loading.setVisibility(View.VISIBLE);
        } else {
            Loading.setVisibility(View.GONE);
        }
    }

    //مربوط به لودینگ پیجینگ داده های رسایکلر وقتی که کاربر به آخر رسایکلر شروع دانلود کردن داده های جدید می کند و در اینجا لودینگ مربوط به آن نمایش داده می شود
    @Override
    public void onLoadingPaging(boolean load) {
        if (load) {
            loading_paging.setVisibility(View.VISIBLE);
        } else {
            loading_paging.setVisibility(View.GONE);
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

        new ErrorAplicationDialog(getString(R.string.Error), text, getString(R.string.Again), R.drawable.ic_error, R.color.colorError, () -> {
            p_tenderNotificationFragment.start(page);
        }).show(getActivity().getSupportFragmentManager(), ErrorAplicationDialog.TAG);
    }

    //در اینجا رسایکلر نمایش داده می شود
    @Override
    public void onShowRecycler() {
        recycler.setVisibility(View.VISIBLE);
    }

    //در اینجا مناقصات یکی یکی به رسایکلر اضافه می شوند
    @Override
    public void onItemTender(VM_TenderNotification notification) {
        tenderNotificationAdapter.add(notification);
    }

    //در اینجا داده های اسپینر شهر ست می شود
    @Override
    public void onItemCitySpinner(ArrayAdapter<VM_City> adapter) {
        cmb_City.setAdapter(adapter);
    }

    //در اینجا داده های اسپینر رشته تحصیلی ست می شود
    @Override
    public void onItemMajorSpinner(ArrayAdapter<VM_Major> adapter) {
        cmb_Major.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        p_tenderNotificationFragment.Cancel(TAG);
    }
}
