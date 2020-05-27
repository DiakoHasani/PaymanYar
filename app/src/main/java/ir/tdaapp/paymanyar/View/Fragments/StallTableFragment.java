package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digidemic.unitof.UnitOf;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import ir.tdaapp.paymanyar.Model.Services.S_StallTableFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Presenter.P_IPE_SemiWideFragment;
import ir.tdaapp.paymanyar.Presenter.P_StallTableFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.ToolsActivity;

//مربوط به صفحه جدول اشتال
public class StallTableFragment extends BaseFragment implements S_StallTableFragment,View.OnClickListener {

    public final static String TAG = "StallTableFragment";
    Toolbar toolBar;
    P_StallTableFragment p_stallTableFragment;
    CardView btn_IPE_Semi_Wide_INP,btn_IPE_Semi_Wide_IPE,btn_IPE_Semi_Wide_UNP,btn_IPE_Semi_Wide_IPB,btn_IPE_Semi_Wide_L,btn_IPE_Semi_Wide_P,btn_IPE_Semi_Wide_ST,btn_IPE_Semi_Wide_T;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stall_table_fragment, container, false);

        findItem(view);
        implement();
        setToolbar();

        p_stallTableFragment.start();

        return view;
    }

    void findItem(View view){
        toolBar=view.findViewById(R.id.toolBar);
        btn_IPE_Semi_Wide_INP=view.findViewById(R.id.btn_IPE_Semi_Wide_INP);
        btn_IPE_Semi_Wide_IPB=view.findViewById(R.id.btn_IPE_Semi_Wide_IPB);
        btn_IPE_Semi_Wide_IPE=view.findViewById(R.id.btn_IPE_Semi_Wide_IPE);
        btn_IPE_Semi_Wide_UNP=view.findViewById(R.id.btn_IPE_Semi_Wide_UNP);
        btn_IPE_Semi_Wide_T=view.findViewById(R.id.btn_IPE_Semi_Wide_T);
        btn_IPE_Semi_Wide_ST=view.findViewById(R.id.btn_IPE_Semi_Wide_ST);
        btn_IPE_Semi_Wide_L=view.findViewById(R.id.btn_IPE_Semi_Wide_L);
        btn_IPE_Semi_Wide_P=view.findViewById(R.id.btn_IPE_Semi_Wide_P);

    }
    void implement(){
        p_stallTableFragment=new P_StallTableFragment(getContext(),this);
        btn_IPE_Semi_Wide_INP.setOnClickListener(this);
        btn_IPE_Semi_Wide_UNP.setOnClickListener(this);
        btn_IPE_Semi_Wide_IPE.setOnClickListener(this);
        btn_IPE_Semi_Wide_IPB.setOnClickListener(this);
        btn_IPE_Semi_Wide_L.setOnClickListener(this);
        btn_IPE_Semi_Wide_P.setOnClickListener(this);
        btn_IPE_Semi_Wide_T.setOnClickListener(this);
        btn_IPE_Semi_Wide_ST.setOnClickListener(this);

    }

    //در اینجا تنظیمات تولبار ست می شود
    void setToolbar() {

        toolBar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolBar.setTitle(getContext().getResources().getString(R.string.StallTable));
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
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_IPE_Semi_Wide_INP:
                ((ToolsActivity)getActivity()).onAddFragment(new IPE_SemiWideFragment(7),0,
                        0,true,IPE_SemiWideFragment.TAG);
                break;
            case R.id.btn_IPE_Semi_Wide_IPB:
                ((ToolsActivity)getActivity()).onAddFragment(new IPE_SemiWideFragment(6),0,
                        0,true,IPE_SemiWideFragment.TAG);
                break;
            case R.id.btn_IPE_Semi_Wide_IPE:
                ((ToolsActivity)getActivity()).onAddFragment(new IPE_SemiWideFragment(8),0,
                        0,true,IPE_SemiWideFragment.TAG);
                break;
            case R.id.btn_IPE_Semi_Wide_L:
                ((ToolsActivity)getActivity()).onAddFragment(new IPE_SemiWideFragment(4),0,
                        0,true,IPE_SemiWideFragment.TAG);
                break;
            case R.id.btn_IPE_Semi_Wide_P:
                ((ToolsActivity)getActivity()).onAddFragment(new IPE_SemiWideFragment(2),0,
                        0,true,IPE_SemiWideFragment.TAG);
                break;
            case R.id.btn_IPE_Semi_Wide_ST:
                ((ToolsActivity)getActivity()).onAddFragment(new IPE_SemiWideFragment(3),0,
                        0,true,IPE_SemiWideFragment.TAG);
                break;
            case R.id.btn_IPE_Semi_Wide_T:
                ((ToolsActivity)getActivity()).onAddFragment(new IPE_SemiWideFragment(1),0,
                        0,true,IPE_SemiWideFragment.TAG);
                break;
            case R.id.btn_IPE_Semi_Wide_UNP:
                ((ToolsActivity)getActivity()).onAddFragment(new IPE_SemiWideFragment(5),0,
                        0,true,IPE_SemiWideFragment.TAG);
                break;
        }
    }
}
