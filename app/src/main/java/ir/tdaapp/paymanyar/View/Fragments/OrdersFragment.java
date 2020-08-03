package ir.tdaapp.paymanyar.View.Fragments;

import android.Manifest;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Adapters.OrdersAdapter;
import ir.tdaapp.paymanyar.Model.Enums.OrderKind;
import ir.tdaapp.paymanyar.Model.Enums.StepsAnalizeTender;
import ir.tdaapp.paymanyar.Model.Services.S_OrdersFragment;
import ir.tdaapp.paymanyar.Model.Services.onClickFilterOrdersDialog;
import ir.tdaapp.paymanyar.Model.Services.onClickOrders;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.openUrl;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FilterOrder;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Orders;
import ir.tdaapp.paymanyar.Presenter.P_OrdersFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Dialogs.ErrorAplicationDialog;
import ir.tdaapp.paymanyar.View.Dialogs.FilterOrdersDialog;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

//صفحه سفارشات
public class OrdersFragment extends BaseFragment implements S_OrdersFragment, View.OnClickListener {

    public static final String TAG = "OrdersFragment";
    Toolbar toolbar;
    P_OrdersFragment p_ordersFragment;
    OrdersAdapter ordersAdapter;
    LinearLayoutManager layoutManager;
    RecyclerView recycler;
    ErrorAplicationDialog errorAplicationDialog;
    boolean isLoading = false;
    int page = 0;
    ShimmerFrameLayout loading;
    SwipeRefreshLayout reload;
    LinearLayout empty;
    ImageView refresh;
    FloatingActionButton btn_filter;
    RelativeLayout loading_DownloadFile;
    String[] Permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    //در اینجا فیلترهای که کاربر کرده است نگهداری می شود
    VM_FilterOrder filterOrder;

    //زمانی که برنامه در حال دریافت مناقصه از سرور باشد مقدار زیر ترو است
    boolean isWorking = false;

    //اگر تعداد آیتم ها در سرور به پایان برسد مقدار زیر ترو می شود
    boolean finishOrders = false;

    //اگر کاربر به صفحه پرداخت باشد متغیر زیر ترو می شود
    boolean isPayment = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.orders_fragment, container, false);

        findItem(view);
        implement();
        setToolbar();

        new Handler().postDelayed(() -> {
            p_ordersFragment.start(page);
        }, 300);

        return view;
    }

    void findItem(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        recycler = view.findViewById(R.id.recycler);
        loading = view.findViewById(R.id.loading);
        reload = view.findViewById(R.id.reload);
        empty = view.findViewById(R.id.empty);
        refresh = view.findViewById(R.id.refresh);
        btn_filter = view.findViewById(R.id.btn_filter);
        loading_DownloadFile = view.findViewById(R.id.loading_DownloadFile);
    }

    void implement() {
        p_ordersFragment = new P_OrdersFragment(getContext(), this);
        filterOrder = new VM_FilterOrder();

        recycler.setOnScrollListener(pOnScrollListener);

        reload.setOnRefreshListener(() -> {
            if (!isWorking) {
                page = 0;
                p_ordersFragment.start(page);
            } else {
                reload.setRefreshing(false);
            }
        });

        btn_filter.setOnClickListener(this);
        refresh.setOnClickListener(this);
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

            //در اینجا چک می کند آداپتر رسایکلر نال نباشد که در برنامه خطای رخ دهد
            if (recycler.getAdapter() != null) {

                //اگر متغیر زیر ترو باشد یعنی مشغول عملیات پیجینگ است تا عملیات به پایان نرسد اجازه پیجینگ نمی دهد
                if (!isLoading) {

                    //اگر اسکرول رسایکلر ما به آخر برسد یعنی زمان پیجینگ است و شرط زیر اجرا می شود
                    if (isLastItemDisplaying()) {

                        //اگر تعداد آیتم ها در سرور به پایانرسید باشد دیگر عمل پیجینگ انجام نمی شود
                        if (!finishOrders) {
                            isLoading = true;

                            ++page;
                            p_ordersFragment.start(page);
                        }
                    }
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

    //در اینجا تنظیمات تولبار ست می شود
    void setToolbar() {

        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setTitle(getContext().getResources().getString(R.string.orders));
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void OnStart() {

        isPayment = false;

        if (page == 0) {
            finishOrders = false;
            loading.startShimmerAnimation();
            ordersAdapter = new OrdersAdapter(getContext());
            layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

            ordersAdapter.setClickOrders(new onClickOrders() {
                @Override
                public void onClickItem(int id, OrderKind orderKind) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("Id", id);

                    switch (orderKind) {
                        case Analiz:
                            AnalizeTendersFragment analizeTendersFragment = new AnalizeTendersFragment();
                            analizeTendersFragment.setArguments(bundle);
                            ((MainActivity) getActivity()).onAddFragment(analizeTendersFragment, R.anim.fadein, R.anim.short_fadeout, true, AnalizeTendersFragment.TAG);
                            break;
                        case ZamanBandi:
                            SchedulingFragment schedulingFragment = new SchedulingFragment();
                            schedulingFragment.setArguments(bundle);
                            ((MainActivity) getActivity()).onAddFragment(schedulingFragment, R.anim.fadein, R.anim.short_fadeout, true, SchedulingFragment.TAG);
                            break;
                        case Hasabresi:
                            AuditFragment auditFragment = new AuditFragment();
                            auditFragment.setArguments(bundle);
                            ((MainActivity) getActivity()).onAddFragment(auditFragment, R.anim.fadein, R.anim.short_fadeout, true, AuditFragment.TAG);
                            break;
                        case MetehVaBaravard:
                            CostEstimationFragment costEstimationFragment=new CostEstimationFragment();
                            costEstimationFragment.setArguments(bundle);
                            ((MainActivity) getActivity()).onAddFragment(costEstimationFragment, R.anim.fadein, R.anim.short_fadeout, true, CostEstimationFragment.TAG);
                            break;
                        case Teadil:
                            DifferenceFragment differenceFragment=new DifferenceFragment();
                            differenceFragment.setArguments(bundle);
                            ((MainActivity) getActivity()).onAddFragment(differenceFragment, R.anim.fadein, R.anim.short_fadeout, true, DifferenceFragment.TAG);
                            break;
                    }


                }

                @Override
                public void onClickButton(VM_Orders order) {

                    switch (order.getStepsAnalizeTender()) {
                        case sendOrder:
                        case orderCheck:
                        case doing:
                        default:
                            Bundle bundle = new Bundle();
                            bundle.putInt("Id", order.getId());
                            AnalizeTendersFragment analizeTendersFragment = new AnalizeTendersFragment();
                            analizeTendersFragment.setArguments(bundle);

                            ((MainActivity) getActivity()).onAddFragment(analizeTendersFragment, R.anim.fadein, R.anim.short_fadeout, true, AnalizeTendersFragment.TAG);
                            break;
                        case duration:
                        case orderCost:
                        case pay:
                            isPayment = true;
                            String url = "http://tiptop.tdaapp.ir/PaymentOrder/Index?OrderId=" + order.getId();
                            openUrl.getWeb(url, getContext());
                            break;
                        case takingOrders:

                            Dexter.withActivity(getActivity()).withPermissions(Permissions).withListener(new MultiplePermissionsListener() {
                                @Override
                                public void onPermissionsChecked(MultiplePermissionsReport report) {

                                    if (!order.getFileUrl().equalsIgnoreCase("")) {

                                        String t = "";
                                        try {
                                            String[] a = order.getFileUrl().split("/");
                                            t = a[1];
                                        } catch (Exception e) {
                                        }
                                        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), t);

                                        //در اینجا چک می شود اگر قبلا این فایل دانلود شده است آن را نمایش می دهد در غیر این صورت آن را دانلود می کند
                                        if (file.exists()) {
                                            onShowFile(t);
                                        } else {
                                            p_ordersFragment.downloadFile(order.getFileUrl(), t);
                                        }
                                    }

                                }

                                @Override
                                public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                                }
                            }).check();

                            break;
                    }

                }
            });

            recycler.setAdapter(ordersAdapter);
            recycler.setLayoutManager(layoutManager);
        }

        isWorking = true;
    }

    @Override
    public void onFinish() {
        loading.stopShimmerAnimation();
        isLoading = false;
        isWorking = false;
    }

    @Override
    public void onSuccess() {
        recycler.setVisibility(View.VISIBLE);
        reload.setRefreshing(false);
    }

    @Override
    public void onError(ResaultCode result) {
        reload.setRefreshing(false);
        loading.stopShimmerAnimation();
        boolean showError;
        isLoading = false;
        isWorking = false;

        if (errorAplicationDialog != null
                && errorAplicationDialog.getDialog() != null
                && errorAplicationDialog.getDialog().isShowing()
                && !errorAplicationDialog.isRemoving()) {

            showError = false;

        } else {
            showError = true;
        }

        if (showError) {
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
                p_ordersFragment.start(page);
                errorAplicationDialog.dismiss();
            });
            errorAplicationDialog.show(getActivity().getSupportFragmentManager(), ErrorAplicationDialog.TAG);
        }
    }

    @Override
    public void onItem(VM_Orders order) {
        ordersAdapter.add(order);
    }

    @Override
    public void onNoAccount() {
        Toast.makeText(getContext(), getString(R.string.Create_an_account_first), Toast.LENGTH_SHORT).show();
        ((MainActivity) getActivity()).onAddFragment(new LoginFragment(), R.anim.fadein, R.anim.short_fadeout, true, LoginFragment.TAG);
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
    public void onHideAll() {
        recycler.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
        refresh.setVisibility(View.GONE);
        empty.setVisibility(View.GONE);
    }

    //اگر کاربر سفارشی نداشته باشد متد زیر فراخوانی می شود
    @Override
    public void onEmpty() {
        empty.setVisibility(View.VISIBLE);
    }

    @Override
    public void onReload() {
        refresh.setVisibility(View.VISIBLE);
    }

    //در اینجا فیلتر سفارشات برگشت داده می شود
    @Override
    public VM_FilterOrder getFilterOrder() {
        return filterOrder;
    }

    //برای لودینگ مربوط به پیجینگ می باشد
    @Override
    public void onLoadingPaging(boolean load) {
        reload.setRefreshing(load);
    }

    //اگر تعداد آیتم ها در سرور به پایان برسد متد زیر فراخوانی می شود
    @Override
    public void onFinishOrderServer() {
        finishOrders = true;
    }

    //مربوط به لودینگ دانلود فایل
    @Override
    public void onLoading_DownloadFile(boolean load) {
        if (load) {
            loading_DownloadFile.setVisibility(View.VISIBLE);
        } else {
            loading_DownloadFile.setVisibility(View.GONE);
        }
    }

    //مربوط به خطای دانلود فایل
    @Override
    public void onErrorDownloadFile(Throwable e) {
        Toast.makeText(getContext(), getString(R.string.There_Was_an_Error_In_The_Application), Toast.LENGTH_SHORT).show();
    }

    //در اینجا فایل نمایش داده می شود
    @Override
    public void onShowFile(String fileName) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);

        if (file.exists()) {

            try {
                String extension = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(file).toString());
                String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_NEW_TASK);
                Uri uri = FileProvider.getUriForFile(getContext(), getActivity().getApplicationContext().getPackageName() + ".provider", file);

                intent.setDataAndType(uri, mimeType);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                startActivity(Intent.createChooser(intent, getString(R.string.ChoseApp)));
            } catch (Exception e) {
                Toast.makeText(getContext(), getString(R.string.There_Was_an_Error_In_The_Application), Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(getContext(), getString(R.string.notFoundThisPDF), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_filter:

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag(FilterOrdersDialog.TAG);

                if (prev == null) {
                    ft.addToBackStack(null);

                    FilterOrdersDialog dialog = new FilterOrdersDialog(filterOrder, new onClickFilterOrdersDialog() {
                        @Override
                        public void clickSearch() {
                            page = 0;
                            p_ordersFragment.start(page);
                        }

                        @Override
                        public void clickCancel() {

                        }
                    });

                    dialog.show(ft, FilterOrdersDialog.TAG);
                }

                break;
            case R.id.refresh:
                page = 0;
                p_ordersFragment.start(page);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        p_ordersFragment.cancel(TAG);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isPayment) {
            isPayment = false;
            page = 0;
            p_ordersFragment.start(page);
        }
    }
}
