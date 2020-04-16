package ir.tdaapp.paymanyar.View.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewpager2.widget.ViewPager2;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Adapters.SliderHomeAdapter;
import ir.tdaapp.paymanyar.Model.Services.S_HomeFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_HomeSlider;
import ir.tdaapp.paymanyar.Presenter.P_HomeFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.ToolsActivity;
import ir.tdaapp.paymanyar.View.Dialogs.ErrorAplicationDialog;

public class HomeFragment extends BaseFragment implements S_HomeFragment, View.OnClickListener {

    public static final String TAG = "HomeFragment";

    ViewPager2 Slider;
    P_HomeFragment p_homeFragment;
    SliderHomeAdapter sliderHomeAdapter;
    ShimmerFrameLayout Loading;
    CardView btn_Tools;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        findItem(view);
        implement();

        p_homeFragment.start();

        ErrorAplicationDialog dialog = new ErrorAplicationDialog();
        dialog.show(getActivity().getSupportFragmentManager(), ErrorAplicationDialog.TAG);

        return view;
    }

    void findItem(View view) {
        Slider = view.findViewById(R.id.Slider);
        Loading = view.findViewById(R.id.Loading);
        btn_Tools = view.findViewById(R.id.btn_Tools);
    }

    void implement() {
        p_homeFragment = new P_HomeFragment(this, getContext());
        btn_Tools.setOnClickListener(this);

        Slider.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });
    }

    //زمانی که عملیات گرفتن اطلاعات شروع شود اولین بار متد زیر فراخوانی می شود تا عملیات اولیه انجام شود
    @Override
    public void OnStart() {
        sliderHomeAdapter = new SliderHomeAdapter(getContext());
        Slider.setAdapter(sliderHomeAdapter);
        Slider.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        Loading.startShimmerAnimation();
    }

    //زمانی که در گرفتن اطلاعات از سرور خطای رخ دهد متد زیر فراخوانی می شود
    @Override
    public void onError(ResaultCode resault) {

    }

    //در اینجا تمام آیتم ها مخفی می شوند
    @Override
    public void onHideAll() {

    }

    //در اینجا حالت لودینگ برنامه ست می شود
    @Override
    public void onLoading(boolean isLoad) {
        if (isLoad) {
            Loading.setVisibility(View.VISIBLE);
        } else {
            Loading.setVisibility(View.GONE);
        }
    }

    //در اینجا یکی یکی آیتم های اسلایدر اضافه می شوند
    @Override
    public void onItemSlider(VM_HomeSlider slider) {
        sliderHomeAdapter.add(slider);
    }

    //زمانی عملیات گرفتن داده ها از اینترنت و خواندن داده ها و تمامی عملیات به پیان برسد متد زیر فراخوانی می شود
    @Override
    public void onFinish() {
        Loading.stopShimmerAnimation();
    }

    @Override
    public void onShowSlider(boolean show) {
        if (show) {
            Slider.setVisibility(View.VISIBLE);
        } else {
            Slider.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //ر اینجا تمامی عملیات ما لغو می شوند
        p_homeFragment.Cancel(TAG);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Tools:
                startActivity(new Intent(getContext(), ToolsActivity.class));
                break;
        }
    }
}
