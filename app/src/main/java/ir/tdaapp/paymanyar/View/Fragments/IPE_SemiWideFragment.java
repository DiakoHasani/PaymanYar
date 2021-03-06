package ir.tdaapp.paymanyar.View.Fragments;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import ir.tdaapp.paymanyar.Model.Services.S_IPE_SemiWideFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_EshtalItem;
import ir.tdaapp.paymanyar.Presenter.P_IPE_SemiWideFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.ToolsActivity;
import ir.tdaapp.paymanyar.View.Dialogs.FilterWideDialog;

//مربوط به صفحه نیم پهن
public class IPE_SemiWideFragment extends BaseFragment implements S_IPE_SemiWideFragment, View.OnClickListener {

    public IPE_SemiWideFragment(int Eshtal_item) {
        this.Current_eshtal_id = Eshtal_item;
    }

    public final static String TAG = "IPE_SemiWideFragment";

    Toolbar toolBar;
    P_IPE_SemiWideFragment p_ipe_semiWideFragment;
    public int Current_eshtal_id = 0;
    ImageView imageView;
    ArrayList<VM_EshtalItem> columns;
    ArrayList<RelativeLayout> arrCols;
    ArrayList<TextView> arrTitles, arrValues, arrUnits;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ipe_semi_wide_fragment, container, false);

        findItem(view);
        implement();
        setToolbar();
        SetImage();

        p_ipe_semiWideFragment.start();

        return view;
    }

    void findItem(View view) {
        toolBar = view.findViewById(R.id.mToolbar);
        arrCols = new ArrayList<>();
        arrTitles = new ArrayList<>();
        arrUnits = new ArrayList<>();
        arrValues = new ArrayList<>();

        arrCols.add(view.findViewById(R.id.ipe_btn_Item1));
        arrCols.add(view.findViewById(R.id.ipe_btn_Item2));
        arrCols.add(view.findViewById(R.id.ipe_btn_Item3));
        arrCols.add(view.findViewById(R.id.ipe_btn_Item4));
        arrCols.add(view.findViewById(R.id.ipe_btn_Item5));
        arrCols.add(view.findViewById(R.id.ipe_btn_Item6));
        arrCols.add(view.findViewById(R.id.ipe_btn_Item7));
        arrCols.add(view.findViewById(R.id.ipe_btn_Item8));
        arrCols.add(view.findViewById(R.id.ipe_btn_Item9));
        arrCols.add(view.findViewById(R.id.ipe_btn_Item10));
        arrCols.add(view.findViewById(R.id.ipe_btn_Item11));
        arrCols.add(view.findViewById(R.id.ipe_btn_Item12));
        arrCols.add(view.findViewById(R.id.ipe_btn_Item13));
        arrCols.add(view.findViewById(R.id.ipe_btn_Item14));

        arrTitles.add(view.findViewById(R.id.ipe_title1));
        arrTitles.add(view.findViewById(R.id.ipe_title2));
        arrTitles.add(view.findViewById(R.id.ipe_title3));
        arrTitles.add(view.findViewById(R.id.ipe_title4));
        arrTitles.add(view.findViewById(R.id.ipe_title5));
        arrTitles.add(view.findViewById(R.id.ipe_title6));
        arrTitles.add(view.findViewById(R.id.ipe_title7));
        arrTitles.add(view.findViewById(R.id.ipe_title8));
        arrTitles.add(view.findViewById(R.id.ipe_title9));
        arrTitles.add(view.findViewById(R.id.ipe_title10));
        arrTitles.add(view.findViewById(R.id.ipe_title11));
        arrTitles.add(view.findViewById(R.id.ipe_title12));
        arrTitles.add(view.findViewById(R.id.ipe_title13));
        arrTitles.add(view.findViewById(R.id.ipe_title14));

        arrValues.add(view.findViewById(R.id.ipe_value1));
        arrValues.add(view.findViewById(R.id.ipe_value2));
        arrValues.add(view.findViewById(R.id.ipe_value3));
        arrValues.add(view.findViewById(R.id.ipe_value4));
        arrValues.add(view.findViewById(R.id.ipe_value5));
        arrValues.add(view.findViewById(R.id.ipe_value6));
        arrValues.add(view.findViewById(R.id.ipe_value7));
        arrValues.add(view.findViewById(R.id.ipe_value8));
        arrValues.add(view.findViewById(R.id.ipe_value9));
        arrValues.add(view.findViewById(R.id.ipe_value10));
        arrValues.add(view.findViewById(R.id.ipe_value11));
        arrValues.add(view.findViewById(R.id.ipe_value12));
        arrValues.add(view.findViewById(R.id.ipe_value13));
        arrValues.add(view.findViewById(R.id.ipe_value14));

        arrUnits.add(view.findViewById(R.id.ipe_unit1));
        arrUnits.add(view.findViewById(R.id.ipe_unit2));
        arrUnits.add(view.findViewById(R.id.ipe_unit3));
        arrUnits.add(view.findViewById(R.id.ipe_unit4));
        arrUnits.add(view.findViewById(R.id.ipe_unit5));
        arrUnits.add(view.findViewById(R.id.ipe_unit6));
        arrUnits.add(view.findViewById(R.id.ipe_unit7));
        arrUnits.add(view.findViewById(R.id.ipe_unit8));
        arrUnits.add(view.findViewById(R.id.ipe_unit9));
        arrUnits.add(view.findViewById(R.id.ipe_unit10));
        arrUnits.add(view.findViewById(R.id.ipe_unit11));
        arrUnits.add(view.findViewById(R.id.ipe_unit12));
        arrUnits.add(view.findViewById(R.id.ipe_unit13));
        arrUnits.add(view.findViewById(R.id.ipe_unit14));

        imageView = view.findViewById(R.id.ipe_img);
    }

    void implement() {
        p_ipe_semiWideFragment = new P_IPE_SemiWideFragment(getContext(), this, String.valueOf(Current_eshtal_id));

        //p_ipe_semiWideFragment.GetColumns();

        //Set Listener to Items
        for (int i = 0; i < arrCols.size(); i++) {
            arrCols.get(i).setOnClickListener(this);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onResume() {
        super.onResume();

        p_ipe_semiWideFragment.GetColumns();
    }

    //در اینجا تنظیمات تولبار ست می شود
    void setToolbar() {

        String title = "";
        switch (Current_eshtal_id) {
            case 1:
                title = getString(R.string.Rebar);
                break;
            case 2:
                title = getString(R.string.P_Pipe);
                break;
            case 3:
                title = getString(R.string.TheCan);
                break;
            case 4:
                title = getString(R.string.L_Dont_Look);
                break;
            case 5:
                title = getString(R.string.UNP_Gutters);
                break;
            case 6:
                title = getString(R.string.IPB_Wide);
                break;
            case 7:
                title = getString(R.string.INP_Narrow);
                break;
            case 8:
                title = getString(R.string.IPE_Semi_wide);
                break;
        }

        toolBar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolBar.setTitle(title);
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
    public void SetItems(ArrayList<VM_EshtalItem> list) {

        //We Set Columns Here
        columns = list;
        ShowColumns(columns.size());

        //Now Set Texts and Values
        for (int i = 0; i < list.size(); i++) {
            arrValues.get(i).setText(list.get(i).getValue());
            arrUnits.get(i).setText(list.get(i).getUnit());
            arrTitles.get(i).setText(list.get(i).getTitle());
        }

    }

    private void SetImage() {
        switch (Current_eshtal_id) {
            case 1:
                imageView.setImageResource(R.drawable.ic_eshtal_teta);
                break;
            case 2:
                imageView.setImageResource(R.drawable.ic_eshtal_roundtube);
                break;
            case 3:
                imageView.setImageResource(R.drawable.ic_eshtal_squaretube);
                break;
            case 4:
                imageView.setImageResource(R.drawable.ic_eshtal_l);
                break;
            case 5:
                imageView.setImageResource(R.drawable.ic_eshtal_unp);
                break;
            case 6:
                imageView.setImageResource(R.drawable.ic_eshtal_ipb);
                break;
            case 7:
                imageView.setImageResource(R.drawable.ic_eshtal_inp);
                break;
            case 8:
                imageView.setImageResource(R.drawable.ic_eshtal_ipe);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ipe_btn_Item1:
                showDialog(1);
                break;
            case R.id.ipe_btn_Item2:
                showDialog(2);
                break;
            case R.id.ipe_btn_Item3:
                showDialog(3);
                break;
            case R.id.ipe_btn_Item4:
                showDialog(4);
                break;
            case R.id.ipe_btn_Item5:
                showDialog(5);
                break;
            case R.id.ipe_btn_Item6:
                showDialog(6);
                break;
            case R.id.ipe_btn_Item7:
                showDialog(7);
                break;
            case R.id.ipe_btn_Item8:
                showDialog(8);
                break;
            case R.id.ipe_btn_Item9:
                showDialog(9);
                break;
            case R.id.ipe_btn_Item10:
                showDialog(10);
                break;
            case R.id.ipe_btn_Item11:
                showDialog(11);
                break;
            case R.id.ipe_btn_Item12:
                showDialog(12);
                break;
            case R.id.ipe_btn_Item13:
                showDialog(13);
                break;
        }
    }

    void ShowColumns(int count) {
        //Set Visible Or Invisible Of Columns base on our Needs
        for (int i = 0; i < arrCols.size(); i++) {
            arrCols.get(i).setVisibility(View.GONE);
        }

        for (int i = 0; i < count; i++) {
            arrCols.get(i).setVisibility(View.VISIBLE);
        }

    }

    //در اینجا دیالوگ فیلتر ما ست می شود
    void showDialog(int index) {
        new Thread(() -> {

            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag(FilterWideDialog.TAG);

            if (prev == null) {
                ft.addToBackStack(null);
                DialogFragment dialogFragment = new FilterWideDialog(columns.get(index - 1), String.valueOf(Current_eshtal_id), new FilterWideDialog.DialogListener() {
                    @Override
                    public void OnDialogClosed() {
                        p_ipe_semiWideFragment.GetColumns();
                    }
                });
                dialogFragment.show(ft, FilterWideDialog.TAG);
            }

        }).run();
    }

}
