package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Adapters.TenderNotificationAdapter;
import ir.tdaapp.paymanyar.Model.Services.S_TenderNotificationFragment;
import ir.tdaapp.paymanyar.Model.Services.addTender;
import ir.tdaapp.paymanyar.Model.Services.onClickTenderNotification;
import ir.tdaapp.paymanyar.Model.Services.removeTender;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_City;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Estimate;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FilterTenderNotification;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_IncludesTheWord;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Let_me_know;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Major;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotifications;
import ir.tdaapp.paymanyar.Presenter.P_TenderNotificationFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Dialogs.ErrorAplicationDialog;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;
import static android.nfc.tech.MifareUltralight.get;

//صفحه مربوط به اطلاع رسانی مناقصات
public class TenderNotificationFragment extends BaseFragment implements S_TenderNotificationFragment, View.OnClickListener {

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
    SearchableSpinner cmb_City;
    TextInputEditText txt_Date, txt_IncludesTheWord;
    int countTenders = 0;
    ErrorAplicationDialog errorAplicationDialog;
    CardView btn_Let_me_know, btn_search;
    TextView text_btn_Let_me_know;
    ProgressBar progress_btn_Let_me_know;
    NestedScrollView nestedScroll;
    Spinner cmb_Major, cmb_FromEstimate, cmb_UntilEstimate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tender_notification_fragment, container, false);

        findItem(view);
        implement();
        setToolbar();

        p_tenderNotificationFragment.getSpinnerDatas();
        p_tenderNotificationFragment.start(getFilter());

        return view;
    }

    void findItem(View view) {
        toolBar = view.findViewById(R.id.toolBar);
        recycler = view.findViewById(R.id.recycler);
        Loading = view.findViewById(R.id.Loading);
        loading_paging = view.findViewById(R.id.loading_paging);
        cmb_City = view.findViewById(R.id.cmb_City);
        cmb_Major = view.findViewById(R.id.cmb_Major);
        cmb_FromEstimate = view.findViewById(R.id.cmb_FromEstimate);
        cmb_UntilEstimate = view.findViewById(R.id.cmb_UntilEstimate);
        txt_Date = view.findViewById(R.id.txt_Date);
        txt_IncludesTheWord = view.findViewById(R.id.txt_IncludesTheWord);
        btn_Let_me_know = view.findViewById(R.id.btn_Let_me_know);
        btn_search = view.findViewById(R.id.btn_search);
        text_btn_Let_me_know = view.findViewById(R.id.text_btn_Let_me_know);
        progress_btn_Let_me_know = view.findViewById(R.id.progress_btn_Let_me_know);
        nestedScroll = view.findViewById(R.id.nestedScroll);
    }

    void implement() {
        p_tenderNotificationFragment = new P_TenderNotificationFragment(getContext(), this);

        nestedScroll.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {

            //در اینجا چک می کند آداپتر رسایکلر نال نباشد که در برنامه خطای رخ دهد
            if (recycler.getAdapter() != null) {

                //اگر متغیر زیر ترو باشد یعنی مشغول عملیات پیجینگ است تا عملیات به پایان نرسد اجازه پیجینگ نمی دهد
                if (!isLoading) {

                    //اگر اسکرول رسایکلر ما به آخر برسد یعنی زمان پیجینگ است و شرط زیر اجرا می شود
                    if (isLastItemDisplaying()) {

                        //در اینجا اگر تعداد کل مناقصه ها در سرور با تعداد مناقصه ها در رسایکلر برابر باشد یعنی تمام مناقصه ها گرفته شده و اجازه عملیات پیجینگ نمی دهد
                        if (recycler.getAdapter().getItemCount() < countTenders) {
                            isLoading = true;

                            onLoadingPaging(true);

                            ++page;
                            p_tenderNotificationFragment.start(getFilter());
                        }
                    }
                }
            }

        });

        btn_search.setOnClickListener(this);
        btn_Let_me_know.setOnClickListener(this);

        txt_Date.setOnFocusChangeListener((view, b) -> {
            if (b) {
                showDatePersian();
            }
        });
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

    //در اینجا اگر مقدار ترو برگشت داده شود یعنی زمان پیجینگ رسیده است و نیاز به خواندن داده از سرور می باشد
    boolean isLastItemDisplaying() {

        View view = nestedScroll.getChildAt(nestedScroll.getChildCount() - 1);

        int diff = (view.getBottom() - (nestedScroll.getHeight() + nestedScroll
                .getScrollY()));

        if (diff <= 350)
            return true;
        return false;
    }

    //در اینجا دیالوگ تاریخ فارسی نمایش داده می شود
    void showDatePersian() {
        PersianDatePickerDialog picker = p_tenderNotificationFragment.getDatePicker();
        picker.setListener(new Listener() {
            @Override
            public void onDateSelected(PersianCalendar persianCalendar) {
                txt_Date.setText(persianCalendar.getPersianYear() + "/" + persianCalendar.getPersianMonth() + "/" + persianCalendar.getPersianDay());
            }

            @Override
            public void onDismissed() {

            }
        });
        picker.show();
    }

    //در اینجا فیلتر جستجو کاربر گرفته می شود
    VM_FilterTenderNotification getFilter() {

        VM_FilterTenderNotification filter = new VM_FilterTenderNotification();

        //در اینجا آیدی استان گرفته می شود
        if (cmb_City.getSelectedItem() != null) {
            filter.setCityId(((VM_City) cmb_City.getSelectedItem()).getId());
        }


        //در اینجا آیدی رشته تحصیلی گرفته می شود
        if (cmb_Major.getSelectedItem() != null) {
            filter.setMajorId(((VM_Major) cmb_Major.getSelectedItem()).getId());
        }


        //در اینجا شامل کلمه ست می شود
        filter.setIncludesTheWord(txt_IncludesTheWord.getText().toString());

        //در اینجا از تاریخ ست می شود
        filter.setDate(txt_Date.getText().toString());

        //در اینجا برآورد از ست می شود
        if (cmb_FromEstimate.getSelectedItem() != null) {
            filter.setFromEstimateId(((VM_Estimate) cmb_FromEstimate.getSelectedItem()).getId());
        }


        //در اینجا تا برآورد ست می شود
        if (cmb_UntilEstimate.getSelectedItem() != null) {
            filter.setUntilEstimateId(((VM_Estimate) cmb_UntilEstimate.getSelectedItem()).getId());
        }

        filter.setPage(page);

        return filter;
    }

    //در اینجا فیلترهای با خبرم کن گرفته می شود
    VM_Let_me_know getLet_Me_KnowFilter() {

        VM_Let_me_know vm = new VM_Let_me_know();

        vm.setCityId(((VM_City) cmb_City.getSelectedItem()).getId());
        vm.setMajorId(((VM_Major) cmb_Major.getSelectedItem()).getId());
        vm.setFromEstimateId(((VM_Estimate) cmb_FromEstimate.getSelectedItem()).getId());
        vm.setUntilEstimateId(((VM_Estimate) cmb_UntilEstimate.getSelectedItem()).getId());
        vm.setToken(((MainActivity) getActivity()).getTbl_notification().getToken(getContext()));

        return vm;
    }

    //در اینجا لودینگ دکمه باخبرم کن ست می شود
    void loadingLet_me_know(boolean load) {

        if (load) {

            btn_Let_me_know.setEnabled(false);
            text_btn_Let_me_know.setVisibility(View.INVISIBLE);
            progress_btn_Let_me_know.setVisibility(View.VISIBLE);

        } else {
            btn_Let_me_know.setEnabled(true);
            text_btn_Let_me_know.setVisibility(View.VISIBLE);
            progress_btn_Let_me_know.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void OnStart() {

        Loading.startShimmerAnimation();

        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        tenderNotificationAdapter = new TenderNotificationAdapter(getContext());
        recycler.setAdapter(tenderNotificationAdapter);
        recycler.setLayoutManager(layoutManager);

        //زمانی که کاربر یک روی یکی از مناقصات کلیک کند متد زیر فراخوانی شده و آی دی آن را به ما می دهد
        tenderNotificationAdapter.setOnClickTenderNotification(new onClickTenderNotification() {
            @Override
            public void onClick(String id) {
                VM_FilterTenderNotification filter = getFilter();
                filter.setTenderId(id);
                filter.setUserId(((MainActivity) getActivity()).getTbl_user().getUserId(getContext()));

                DetailsTenderFragment detailsTenderFragment = new DetailsTenderFragment(filter, (tenderId, fevorit) -> {
                    tenderNotificationAdapter.changeFevoritTender(tenderId, fevorit);
                });

                ((MainActivity) getActivity()).onAddFragment(detailsTenderFragment, R.anim.fadein
                        , R.anim.fadeout, true, DetailsTenderFragment.TAG);
            }

            @Override
            public void onClickFavorit_Add(String id, ImageView icon) {
                p_tenderNotificationFragment.AddFavorit(id, new addTender() {
                    @Override
                    public void onSuccess() {
                        tenderNotificationAdapter.setStart(id);
                    }

                    @Override
                    public void onError(String error) {
                    }
                });
            }

            @Override
            public void onClickFavorit_remove(String id, ImageView icon) {
                p_tenderNotificationFragment.RemoveFevorit(id, new removeTender() {
                    @Override
                    public void onSuccess() {
                        tenderNotificationAdapter.setStart(id);
                    }

                    @Override
                    public void onError(String error) {
                    }
                });
            }
        });

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

        errorAplicationDialog = new ErrorAplicationDialog(getString(R.string.Error), text, getString(R.string.Again), R.drawable.ic_error, R.color.colorError, () -> {
            p_tenderNotificationFragment.start(getFilter());
            errorAplicationDialog.dismiss();
        });
        errorAplicationDialog.show(getActivity().getSupportFragmentManager(), ErrorAplicationDialog.TAG);
    }

    //در اینجا رسایکلر نمایش داده می شود
    @Override
    public void onShowRecycler() {
        recycler.setVisibility(View.VISIBLE);
    }

    //در اینجا مناقصات یکی یکی به رسایکلر اضافه می شوند
    @Override
    public void onItemTender(VM_TenderNotifications notification) {
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

    //در اینجا داده های برآورد از ست می شود
    @Override
    public void onItemFromEstimateSpinner(ArrayAdapter<VM_Estimate> adapter) {
        cmb_FromEstimate.setAdapter(adapter);
    }

    //در اینجا داده های برآورد تا ست می شود
    @Override
    public void onItemUntilEstimateSpinner(ArrayAdapter<VM_Estimate> adapter) {
        cmb_UntilEstimate.setAdapter(adapter);
    }

    //در اینجا تعداد مناقصه ها در سرور گرفته می شود تا از پیجینگ اضافی جلوگیری کنیم
    @Override
    public void onCountTenders(int count) {
        countTenders = count;
    }

    //اگر در ارسال باخبرم کن خطای رخ دهد متد زیر فراخوانی می شود
    @Override
    public void onErrorLetMeKnow(ResaultCode result) {

        loadingLet_me_know(false);

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

        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();

    }

    //اگر عملیات باخبرم کن با موفقیت انجام شود متد زیر فراخوانی می شود
    @Override
    public void onSuccessLetMeKnow(VM_Message message) {
        loadingLet_me_know(false);
        Toast.makeText(getContext(), message.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        p_tenderNotificationFragment.Cancel(TAG);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Let_me_know:
                loadingLet_me_know(true);
                p_tenderNotificationFragment.LetMeKnow(getLet_Me_KnowFilter());
                break;
            case R.id.btn_search:
                p_tenderNotificationFragment.start(getFilter());
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.tender_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
