package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

    public IPE_SemiWideFragment(int Eshtal_item){
        this.Current_eshtal_id=Eshtal_item;
    }

    public final static String TAG = "IPE_SemiWideFragment";

    Toolbar toolBar;
    P_IPE_SemiWideFragment p_ipe_semiWideFragment;
    RelativeLayout btn_Item1,btn_Item2,btn_Item3,btn_Item4,btn_Item5,btn_Item6,btn_Item7,btn_Item8,btn_Item9,btn_Item10,btn_Item11,btn_Item12,btn_Item13,btn_Item14;
    public int Current_eshtal_id=0;
    ImageView imageView;

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
        btn_Item1 = view.findViewById(R.id.ipe_btn_Item1);
        btn_Item2 = view.findViewById(R.id.ipe_btn_Item2);
        btn_Item3 = view.findViewById(R.id.ipe_btn_Item3);
        btn_Item4 = view.findViewById(R.id.ipe_btn_Item4);
        btn_Item5 = view.findViewById(R.id.ipe_btn_Item5);
        btn_Item6 = view.findViewById(R.id.ipe_btn_Item6);
        btn_Item7 = view.findViewById(R.id.ipe_btn_Item7);
        btn_Item8 = view.findViewById(R.id.ipe_btn_Item8);
        btn_Item9 = view.findViewById(R.id.ipe_btn_Item9);
        btn_Item10 = view.findViewById(R.id.ipe_btn_Item10);
        btn_Item11 = view.findViewById(R.id.ipe_btn_Item11);
        btn_Item12 = view.findViewById(R.id.ipe_btn_Item12);
        btn_Item13 = view.findViewById(R.id.ipe_btn_Item13);
        btn_Item14 = view.findViewById(R.id.ipe_btn_Item14);
        imageView=view.findViewById(R.id.ipe_img);
    }

    void implement() {
        p_ipe_semiWideFragment = new P_IPE_SemiWideFragment(getContext(), this,Current_eshtal_id);
        btn_Item1.setOnClickListener(this);
        btn_Item2.setOnClickListener(this);
        btn_Item3.setOnClickListener(this);
        btn_Item4.setOnClickListener(this);
        btn_Item5.setOnClickListener(this);
        btn_Item6.setOnClickListener(this);
        btn_Item7.setOnClickListener(this);
        btn_Item8.setOnClickListener(this);
        btn_Item9.setOnClickListener(this);
        btn_Item10.setOnClickListener(this);
        btn_Item11.setOnClickListener(this);
        btn_Item12.setOnClickListener(this);
        btn_Item13.setOnClickListener(this);
        btn_Item14.setOnClickListener(this);
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
            case R.id.ipe_btn_Item1:
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
