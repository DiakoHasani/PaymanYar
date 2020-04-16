package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import ir.tdaapp.paymanyar.Model.Services.S_IPE_SemiWideFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Presenter.P_IPE_SemiWideFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.ToolsActivity;
import ir.tdaapp.paymanyar.View.Dialogs.FilterWideDialog;

//مربوط به صفحه نیم پهن
public class IPE_SemiWideFragment extends BaseFragment implements S_IPE_SemiWideFragment, View.OnClickListener {

    public final static String TAG = "IPE_SemiWideFragment";

    Toolbar toolBar;
    P_IPE_SemiWideFragment p_ipe_semiWideFragment;
    RelativeLayout btn_Item;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ipe_semi_wide_fragment, container, false);

        findItem(view);
        implement();
        setToolbar();

        p_ipe_semiWideFragment.start();

        return view;
    }

    void findItem(View view) {
        toolBar = view.findViewById(R.id.mToolbar);
        btn_Item = view.findViewById(R.id.btn_Item);
    }

    void implement() {
        p_ipe_semiWideFragment = new P_IPE_SemiWideFragment(getContext(), this);
        btn_Item.setOnClickListener(this);
    }

    //در اینجا تنظیمات تولبار ست می شود
    void setToolbar() {

        toolBar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolBar.setTitle(getContext().getResources().getString(R.string.IPE_Semi_wide));
        ((ToolsActivity) getActivity()).setSupportActionBar(toolBar);
        ((ToolsActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        toolBar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });
        setHasOptionsMenu(true);
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Item:
                showDialog();
                break;
        }
    }

    //در اینجا دیالوگ فیلتر ما ست می شود
    void showDialog() {
        new Thread(() -> {

            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag(FilterWideDialog.TAG);

            if (prev == null) {
                ft.addToBackStack(null);
                DialogFragment dialogFragment = new FilterWideDialog(value -> {
                    Toast.makeText(getContext(), value + "", Toast.LENGTH_SHORT).show();
                });
                dialogFragment.show(ft, FilterWideDialog.TAG);
            }

        }).run();
    }
}
