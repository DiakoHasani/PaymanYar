package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;


import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import es.dmoral.toasty.Toasty;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Adapters.MaterialAdapter;
import ir.tdaapp.paymanyar.Model.Services.S_MaterialFragment;
import ir.tdaapp.paymanyar.Model.Services.onClickPowerSupplyNetwork;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FilterMaterial;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Material;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_MaterialSpinner;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_ProvincesAndCities;
import ir.tdaapp.paymanyar.Presenter.P_MaterialFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Dialogs.ErrorAplicationDialog;

/**
 * صفحه مصالح
 **/
public class MaterialFragment extends BaseFragment implements S_MaterialFragment, View.OnClickListener {

    public final static String TAG = "MaterialFragment";

    P_MaterialFragment p_materialFragment;
    Spinner cmb_Province, cmb_City, cmb_Material;
    MaterialAdapter materialAdapter;
    Toolbar toolbar;
    RecyclerView recycler;
    ShimmerFrameLayout loading;
    ImageView reload;
    LinearLayout empty;
    LinearLayoutManager layoutManager;
    ErrorAplicationDialog errorAplicationDialog;
    FloatingActionButton btn_PowerSupply;
    CardView btn_Search;
    int paging = 0;
    SwipeRefreshLayout refresh;
    boolean workedInternet = false;
    NestedScrollView nestedScroll;
    boolean loadingProgress = false;
    ProgressBar progressPaging;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.material_fragment, container, false);

        findItem(view);
        implement();
        setToolbar();

        p_materialFragment.getProvinces();

        new Handler().postDelayed(() -> {
            p_materialFragment.start();
        }, 300);

        return view;
    }

    void findItem(View view) {
        cmb_Province = view.findViewById(R.id.cmb_Province);
        cmb_City = view.findViewById(R.id.cmb_City);
        toolbar = view.findViewById(R.id.toolbar);
        recycler = view.findViewById(R.id.recycler);
        loading = view.findViewById(R.id.loading);
        reload = view.findViewById(R.id.reload);
        empty = view.findViewById(R.id.empty);
        cmb_Material = view.findViewById(R.id.cmb_Material);
        btn_PowerSupply = view.findViewById(R.id.btn_PowerSupply);
        btn_Search = view.findViewById(R.id.btn_Search);
        refresh = view.findViewById(R.id.refresh);
        nestedScroll = view.findViewById(R.id.nestedScroll);
        progressPaging = view.findViewById(R.id.progressPaging);
    }

    void implement() {
        p_materialFragment = new P_MaterialFragment(getContext(), this);
        btn_PowerSupply.setOnClickListener(this);
        btn_Search.setOnClickListener(this);
        reload.setOnClickListener(this);

        cmb_Province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                try {
                    VM_ProvincesAndCities item = ((VM_ProvincesAndCities) adapterView.getSelectedItem());

                    if (item != null) {
                        p_materialFragment.getCities(item.getId());
                    }

                } catch (Exception e) {
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        refresh.setOnRefreshListener(() -> {
            if (!isWorkedInternet()) {
                paging = 0;
                p_materialFragment.start();
            } else {
                refresh.setRefreshing(false);
            }
        });

        nestedScroll.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (view, i, i1, i2, i3) -> {

            //در اینجا چک می کند آداپتر رسایکلر نال نباشد که در برنامه خطای رخ دهد
            if (recycler.getAdapter() != null) {

                //اگر متغیر زیر ترو باشد یعنی مشغول عملیات پیجینگ است تا عملیات به پایان نرسد اجازه پیجینگ نمی دهد
                if (!isLoadingProgress()) {

                    //اگر اسکرول رسایکلر ما به آخر برسد یعنی زمان پیجینگ است و شرط زیر اجرا می شود
                    if (isLastItemDisplaying()) {

                        if (!isWorkedInternet()) {
                            loadingProgress = true;
                            ++paging;
                            p_materialFragment.start();
                        }

                    }

                }

            }

        });
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

    void setToolbar() {
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setTitle(getContext().getResources().getString(R.string.MaterialNetwork));
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

        //در مقدار زیر می گوییم این صفحه درحال گرفتن اطلاعات از سرور است و لازم نیست که دوباره از سرور اطلاعات بگیرد
        workedInternet = true;

        if (getPage() == 0) {
            materialAdapter = new MaterialAdapter(getContext());
            layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
            materialAdapter.setClickPowerSupplyNetwork(new onClickPowerSupplyNetwork() {
                @Override
                public void click(int id) {
                    DetailMaterialFragment detailMaterialFragment = new DetailMaterialFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", id);
                    detailMaterialFragment.setArguments(bundle);

                    ((MainActivity) getActivity()).onAddFragment(detailMaterialFragment, R.anim.fadein, R.anim.short_fadeout, true, DetailMaterialFragment.TAG);
                }

                @Override
                public void remove(int id) {

                }
            });

            recycler.setAdapter(materialAdapter);
            recycler.setLayoutManager(layoutManager);
        }
    }

    @Override
    public void onLoading(boolean load) {
        if (load) {
            loading.setVisibility(View.VISIBLE);
            loading.startShimmerAnimation();
        } else {
            loading.setVisibility(View.GONE);
            loading.stopShimmerAnimation();
        }
    }

    @Override
    public void onHideAll() {
        recycler.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
        reload.setVisibility(View.GONE);
        empty.setVisibility(View.GONE);
        progressPaging.setVisibility(View.GONE);
        refresh.setRefreshing(false);
    }

    /**
     * اگر آیتمی برای نمایش در رسایکلر نباشد متد زیر فراخوانی می شود
     **/
    @Override
    public void onEmpty() {
        empty.setVisibility(View.VISIBLE);
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

        reload.setVisibility(View.VISIBLE);

        errorAplicationDialog = new ErrorAplicationDialog(getString(R.string.Error), text, getString(R.string.Again), R.drawable.ic_error, R.color.colorError, () -> {
            paging = 0;
            p_materialFragment.start();
            errorAplicationDialog.dismiss();
        });
        errorAplicationDialog.show(getActivity().getSupportFragmentManager(), ErrorAplicationDialog.TAG);

        loadingProgress = false;
        workedInternet = false;
    }

    /**
     * اگر داده ها با موفقیت از سرور گرفته شود متد زیر فراخوانی می شود
     **/
    @Override
    public void onSuccess() {
        recycler.setVisibility(View.VISIBLE);
    }

    /**
     * وقتی داده ها از سرور گرفته شود متد زیر فراخوانی می شود
     **/
    @Override
    public void onFinish() {
        loadingProgress = false;
        workedInternet = false;
    }

    /**
     * در اینجا لیست استان ها برای اسپینر گرفته می شود
     **/
    @Override
    public void getProvinces(ArrayAdapter<VM_ProvincesAndCities> provinces) {
        cmb_Province.setAdapter(provinces);
    }

    /**
     * در اینجا لیست شهرها برای اسپینر گرفته می شود
     **/
    @Override
    public void getCities(ArrayAdapter<VM_ProvincesAndCities> cities) {
        cmb_City.setAdapter(cities);
    }

    /**
     * در اینجا دسته مصالح ست می شود
     **/
    @Override
    public void getMaterials(ArrayAdapter<VM_MaterialSpinner> adapter) {
        cmb_Material.setAdapter(adapter);
    }

    /**
     * در اینجا آیتم ها در رسایکلر ست می شوند
     **/
    @Override
    public void onItem(VM_Material material) {
        materialAdapter.add(material);
    }

    /**
     * در اینجا فیلتر گرفته می شود
     **/
    @Override
    public VM_FilterMaterial getFilter() {
        VM_FilterMaterial filter = new VM_FilterMaterial();

        try {

            VM_ProvincesAndCities state = ((VM_ProvincesAndCities) cmb_Province.getSelectedItem());
            VM_ProvincesAndCities city = ((VM_ProvincesAndCities) cmb_City.getSelectedItem());
            VM_MaterialSpinner material = ((VM_MaterialSpinner) cmb_Material.getSelectedItem());

            if (state != null) {
                filter.setStateId(state.getId());
            }

            if (city != null) {
                filter.setCityId(city.getId());
            }

            if (material != null) {
                filter.setMaterialId(material.getId());
            }

            filter.setPaging(paging);

        } catch (Exception e) {
        }

        return filter;
    }

    /**
     * مربوط به لودینگ در زمان پیجینگ
     **/
    @Override
    public void onLoadingPaging(boolean load) {
        if (load) {
            progressPaging.setVisibility(View.VISIBLE);
        } else {
            progressPaging.setVisibility(View.GONE);
        }
    }

    /**
     * مربوط به خطا در زمان پیجینگ
     **/
    @Override
    public void onErrorPaging(ResaultCode result) {
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

        loadingProgress = false;
        workedInternet = false;

        Toasty.error(getContext(), text, Toast.LENGTH_SHORT,true).show();
    }

    /**
     * در اینجا نشان می دهد که این صفحه درحال دریافت اطلاعات از سرور است یا خیر
     **/
    @Override
    public boolean isWorkedInternet() {
        return workedInternet;
    }

    @Override
    public int getPage() {
        return paging;
    }

    @Override
    public boolean isLoadingProgress() {
        return loadingProgress;
    }

    /**
     * در اینجا چک می کند که اسپینر مصالح از سرور گرفته شده است اگر فالس باشد دوباره اقدام به گرفتن داده ها می کند
     **/
    @Override
    public boolean checkedMaterialSpinner() {
        return cmb_Material.getAdapter() != null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_PowerSupply:
                ((MainActivity) getActivity()).onAddFragment(new AddMaterialFragment(), R.anim.fadein, R.anim.short_fadeout, true, AddMaterialFragment.TAG);
                break;
            case R.id.btn_Search:
            case R.id.reload:
                paging = 0;
                p_materialFragment.start();
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.my_ad_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.myAd:
                ((MainActivity) getActivity()).onAddFragment(new MyMaterialFragment(), R.anim.fadein, R.anim.short_fadeout, true, MyMaterialFragment.TAG);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        p_materialFragment.cancel(TAG);
    }
}
