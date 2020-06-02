package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
    ImageView imgZ, imgY, imgX;

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


    }

    void implement() {
        p_levelFragment = new P_LevelFragment(getContext(), this);
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
                //Vertical
                imgX.setImageBitmap(bitmap);
                break;
            case 1:
                //Horizontal
                imgY.setImageBitmap(bitmap);
                break;
            case 2:
                //Circle
                imgZ.setImageBitmap(bitmap);
                break;
        }
    }


}
