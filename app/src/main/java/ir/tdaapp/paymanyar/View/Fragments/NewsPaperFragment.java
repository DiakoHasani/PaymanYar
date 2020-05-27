package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.shimmer.ShimmerFrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Adapters.NewsPaperAdapter;
import ir.tdaapp.paymanyar.Model.Services.S_NewsPaperFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_NewsPaper;
import ir.tdaapp.paymanyar.Presenter.P_NewsPaperFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Activitys.ToolsActivity;
import ir.tdaapp.paymanyar.View.Dialogs.ErrorAplicationDialog;

//مربوط به صفحه روزنامه
public class NewsPaperFragment extends BaseFragment implements S_NewsPaperFragment {

    public final static String TAG = "NewsPaperFragment";

    P_NewsPaperFragment p_newsPaperFragment;
    ShimmerFrameLayout loading;
    RecyclerView recycler;
    Toolbar toolbar;
    NewsPaperAdapter newsPaperAdapter;
    ErrorAplicationDialog errorAplicationDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_paper_fragment, container, false);

        findItem(view);
        implement();
        setToolbar();

        return view;
    }

    void findItem(View view) {
        loading = view.findViewById(R.id.loading);
        recycler = view.findViewById(R.id.recycler);
        toolbar = view.findViewById(R.id.toolbar);
    }

    void implement() {
        p_newsPaperFragment = new P_NewsPaperFragment(getContext(), this);
    }

    //در اینجا تنظیمات تولبار ست می شود
    void setToolbar() {

        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setTitle(getContext().getResources().getString(R.string.Counter));
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();

        new Handler().postDelayed(() -> {
            p_newsPaperFragment.start();
        },300);
    }

    @Override
    public void OnStart() {
        loading.startShimmerAnimation();
        newsPaperAdapter = new NewsPaperAdapter(getContext());
        recycler.setAdapter(newsPaperAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
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
    public void onError(ResaultCode result) {

        String text = getString(R.string.please_Checked_Your_Internet_Connection);

        errorAplicationDialog = new ErrorAplicationDialog(getString(R.string.Error), text, getString(R.string.Again), R.drawable.ic_error, R.color.colorError, () -> {
            p_newsPaperFragment.start();
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
    }

    @Override
    public void onSuccess() {
        recycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNews(VM_NewsPaper newsPaper) {
        newsPaperAdapter.add(newsPaper);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        p_newsPaperFragment.Cancel(TAG);
    }
}
