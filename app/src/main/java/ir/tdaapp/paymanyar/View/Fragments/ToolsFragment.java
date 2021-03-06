package ir.tdaapp.paymanyar.View.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.shimmer.ShimmerFrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import ir.tdaapp.paymanyar.Model.Services.S_ToolsFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Presenter.P_ToolsFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.LevelActivity;
import ir.tdaapp.paymanyar.View.Activitys.MagnifierActivity;
import ir.tdaapp.paymanyar.View.Activitys.RullerActivity;
import ir.tdaapp.paymanyar.View.Activitys.ToolsActivity;

public class ToolsFragment extends BaseFragment implements S_ToolsFragment, View.OnClickListener {

    public final static String TAG = "ToolsFragment";
    ShimmerFrameLayout animationLogo;
    P_ToolsFragment p_toolsFragment;
    CardView btn_StallTable, btn_GPS, btn_UnitConversionFragment, btn_Level, btn_Magnifier, btn_PriceRange;
    ImageView logo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tools_fragment, container, false);

        findItem(view);
        implement();
        p_toolsFragment.start();

        return view;
    }

    void findItem(View view) {
        animationLogo = view.findViewById(R.id.animationLogo);
        btn_StallTable = view.findViewById(R.id.btn_StallTable);
        btn_GPS = view.findViewById(R.id.btn_GPS);
        btn_UnitConversionFragment = view.findViewById(R.id.btn_UnitConversionFragment);
        btn_Level = view.findViewById(R.id.btn_Level);
        btn_Magnifier = view.findViewById(R.id.tools_btnMagnifier);
        btn_PriceRange = view.findViewById(R.id.btn_PriceRange);
        logo = view.findViewById(R.id.logo);
    }

    void implement() {
        p_toolsFragment = new P_ToolsFragment(getContext(), this);
        btn_StallTable.setOnClickListener(this);
        btn_GPS.setOnClickListener(this);
        btn_UnitConversionFragment.setOnClickListener(this);
        btn_Level.setOnClickListener(this);
        btn_Magnifier.setOnClickListener(this);
        btn_PriceRange.setOnClickListener(this);
        logo.setOnClickListener(this);
    }

    @Override
    public void OnStart() {
        animationLogo.startShimmerAnimation();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_StallTable:
                ((ToolsActivity) getActivity()).onAddFragment(new StallTableFragment(), R.anim.slide_in_right, R.anim.short_fadeout, true, StallTableFragment.TAG);
                break;
            case R.id.btn_GPS:
                ((ToolsActivity) getActivity()).onAddFragment(new GpsFragment(), R.anim.slide_in_right, R.anim.short_fadeout, true, StallTableFragment.TAG);
                break;
            case R.id.btn_UnitConversionFragment:
                ((ToolsActivity) getActivity()).onAddFragment(new UnitConversionFragment(), R.anim.slide_in_right, R.anim.short_fadeout, true, UnitConversionFragment.TAG);
                break;
            case R.id.btn_Level:
                startActivity(new Intent(getActivity(), LevelActivity.class));
                break;
            case R.id.tools_btnMagnifier:
                startActivity(new Intent(getActivity(), MagnifierActivity.class));
                break;
            case R.id.btn_PriceRange:
                ((ToolsActivity) getActivity()).onAddFragment(new PriceRangeFragment(), R.anim.fadein, R.anim.short_fadeout, true, PriceRangeFragment.TAG);
                break;
            case R.id.logo:
                ((ToolsActivity) getActivity()).onAddFragment(new Compass_Fragment(), R.anim.slide_in_right, R.anim.short_fadeout, true, Compass_Fragment.TAG);
                break;
        }
    }

}
