package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import ir.tdaapp.paymanyar.Model.Services.S_LevelFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Presenter.P_IPE_SemiWideFragment;
import ir.tdaapp.paymanyar.Presenter.P_LevelFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Activitys.ToolsActivity;

//مربوط به صفحه تراز
public class LevelFragment extends BaseFragment implements S_LevelFragment {

    public final static String TAG = "LevelFragment";

    P_LevelFragment p_levelFragment;
    Toolbar toolBar;
    ImageView imgZ, imgY, imgX,imgbuble;
    int ActivityWidth=1,ActivityHeight=1;
    RelativeLayout background;

    Animation fadeIn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.level_fragment, container, false);

        findItem(view);
        implement();
        setToolbar();

        return view;
    }

    void findItem(View view) {
        imgX = view.findViewById(R.id.iv);
        imgY = view.findViewById(R.id.ix);
        imgZ = view.findViewById(R.id.icircle);
        toolBar = view.findViewById(R.id.toolBar);
        background = view.findViewById(R.id.background);
//        imgbuble=view.findViewById(R.id.buble_center);

        ActivityHeight=((ToolsActivity)getActivity()).getHeightDisplay();
        ActivityWidth=((ToolsActivity)getActivity()).getWidthDisplay();
    }

    void implement() {

        //Circlar Imageview
//        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(imgZ.getLayoutParams());
//        params.width=(ActivityWidth*40/100);
//        params.height=(ActivityWidth*40/100);
//        imgZ.setLayoutParams(params);



        p_levelFragment = new P_LevelFragment(getContext(), this,ActivityWidth,ActivityHeight, imgZ);

        fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(100);
    }

    //در اینجا تنظیمات تولبار ست می شود
    void setToolbar() {

        toolBar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolBar.setTitle(getContext().getResources().getString(R.string.Level));
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
    public void OnImageFinished(Bitmap bitmap, int type) {
        switch (type) {
            case 0:
                //Vertical (width = 10% of screen Width , Height = 25% of screen Height)

                imgX.setImageBitmap(bitmap);
                break;
            case 1:
                //Horizontal (width = 25% of screen Width , Height = 10% of screen Height)

                imgY.setImageBitmap(bitmap);
                break;
            case 2:
                //Circle (width = 65% of screen Width , Height = 65% of screen Height)

                imgZ.setAnimation(fadeIn);
                imgZ.setImageBitmap(bitmap);
                break;
        }
    }



}
