package ir.tdaapp.paymanyar.View.Activitys;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import at.wirecube.additiveanimations.additive_animator.AdditiveAnimator;
import ir.tdaapp.paymanyar.Model.Enums.DisplaySize;
import ir.tdaapp.paymanyar.Model.Services.S_LevelFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.DisplayPhone;
import ir.tdaapp.paymanyar.Presenter.P_LevelFragment;
import ir.tdaapp.paymanyar.R;

public class LevelActivity extends AppCompatActivity implements S_LevelFragment {

    public final static String TAG = "LevelActivity";

    P_LevelFragment p_levelFragment;
    Toolbar toolBar;
    int ActivityWidth = 1, ActivityHeight = 1;
    ImageView background;
    RelativeLayout img_bubble;
    DisplaySize displaySize;
    int xBubble, yBubble = 0;
    int backgroundSize = 0;
    DisplayMetrics displayMetrics;
    ImageView border_background;
    RelativeLayout topLine, rightLine, bottomLine, leftLine;
    ImageView center_level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_level);

        findItem();
        implement();
        setToolbar();
    }

    void findItem() {
        toolBar = findViewById(R.id.toolBar);
        background = findViewById(R.id.background);
        img_bubble = findViewById(R.id.img_bubble);
        border_background = findViewById(R.id.border_background);
        topLine = findViewById(R.id.topLine);
        rightLine = findViewById(R.id.rightLine);
        bottomLine = findViewById(R.id.bottomLine);
        leftLine = findViewById(R.id.leftLine);
        center_level = findViewById(R.id.center_level);
    }

    void implement() {

        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        ActivityHeight = getHeightDisplay();
        ActivityWidth = getWidthDisplay();
        displaySize = DisplayPhone.getDisplaySize(this);

        if (displaySize == DisplaySize.xSmall) {
            xBubble = yBubble = 18;
            backgroundSize = 120;
        } else if (displaySize == DisplaySize.small) {
            xBubble = yBubble = 24;
            backgroundSize = 240;
        } else if (displaySize == DisplaySize.medium) {
            xBubble = yBubble = 32;
            backgroundSize = 440;
        } else if (displaySize == DisplaySize.large) {
            xBubble = yBubble = 48;
            backgroundSize = 880;
        } else if (displaySize == DisplaySize.xLarge) {
            xBubble = yBubble = 72;
            backgroundSize = 1760;
        }

        p_levelFragment = new P_LevelFragment(this, this, backgroundSize, ActivityWidth, ActivityHeight);

        background.getLayoutParams().height = (ActivityWidth - backgroundSize) + yBubble;
        background.getLayoutParams().width = (ActivityWidth - backgroundSize) + xBubble;
        border_background.getLayoutParams().height = (ActivityWidth - backgroundSize + Math.round(onDpTopixel(8))) + yBubble;
        border_background.getLayoutParams().width = (ActivityWidth - backgroundSize + Math.round(onDpTopixel(8))) + xBubble;

        topLine.getLayoutParams().height = ((ActivityWidth - backgroundSize) / 2 - yBubble) + yBubble / 2;
        bottomLine.getLayoutParams().height = ((ActivityWidth - backgroundSize) / 2 - yBubble) + yBubble / 2;
        rightLine.getLayoutParams().width = ((ActivityWidth - backgroundSize) / 2 - yBubble) + xBubble / 2;
        leftLine.getLayoutParams().width = ((ActivityWidth - backgroundSize) / 2 - yBubble) + xBubble / 2;
    }

    //در اینجا تنظیمات تولبار ست می شود
    void setToolbar() {

        toolBar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolBar.setTitle(getString(R.string.Level));
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        toolBar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
//        setHasOptionsMenu(true);
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void onPositionBubble(float x, float y) {

        AdditiveAnimator.animate(img_bubble).setDuration(600)
                .x((ActivityWidth - x) - xBubble)
                .y(y - yBubble)
                .start();
    }

    @Override
    public Float onPixelTodp(float pixel) {
        float density = getResources().getDisplayMetrics().density;
        float dp = pixel / density;
        return dp;
    }

    @Override
    public Float onDpTopixel(float dp) {
        float density = getResources().getDisplayMetrics().density;
        float pixel = dp * density;
        return pixel;
    }

    //اگر مقدار ورودی متد زیر ترو باشد یعنی حباب به مرکز تراز رسیده و رنگ بوردر تراز تغییر می کند
    @Override
    public void onCenterBubble(boolean isCenter) {
        changeColorBorderLevel(isCenter);
    }

    //اگر مقدار ورودی متد زیر ترو باشد یعنی حباب به بالا یا پایین تراز رسیده و رنگ بوردر تراز تغییر می کند
    @Override
    public void on_Y_Bubble(boolean isY) {
        changeColorBorderLevel(isY);
    }

    //اگر مقدار ورودی متد زیر ترو باشد یعنی حباب به راست یا چپ تراز رسیده و رنگ بوردر تراز تغییر می کند
    @Override
    public void on_X_Bubble(boolean isX) {
        changeColorBorderLevel(isX);
    }

    //در اینجا اندازه عرض صفحه نمایش برگشت داده می شود
    public int getWidthDisplay() {
        return displayMetrics.widthPixels;
    }

    //در اینجا اندازه طول صفحه نمایش برگشت داده می شود
    public int getHeightDisplay() {
        return displayMetrics.heightPixels;
    }

    //در اینجا رنگ بوردر تراز تغییر می کند
    void changeColorBorderLevel(boolean isChange) {
        if (isChange) {
            if (center_level.getTag().equals("f")) {
                new Handler().postDelayed(() -> {
                    center_level.setImageDrawable(getResources().getDrawable(R.drawable.border_center_level2));
                    border_background.setImageDrawable(getResources().getDrawable(R.drawable.border_level_background2));
                    bottomLine.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    topLine.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    rightLine.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    leftLine.setBackgroundColor(getResources().getColor(R.color.colorGreen));

                    center_level.setTag("t");
                }, 600);
//                center_level.setImageDrawable(getResources().getDrawable(R.drawable.border_center_level2));
//                border_background.setImageDrawable(getResources().getDrawable(R.drawable.border_level_background2));
//                bottomLine.setBackgroundColor(getResources().getColor(R.color.colorGreen));
//                topLine.setBackgroundColor(getResources().getColor(R.color.colorGreen));
//                rightLine.setBackgroundColor(getResources().getColor(R.color.colorGreen));
//                leftLine.setBackgroundColor(getResources().getColor(R.color.colorGreen));
//
//                center_level.setTag("t");
            }
        } else {
            if (center_level.getTag().equals("t")) {

                center_level.setImageDrawable(getResources().getDrawable(R.drawable.border_center_level));
                border_background.setImageDrawable(getResources().getDrawable(R.drawable.border_level_background));
                bottomLine.setBackgroundColor(getResources().getColor(R.color.colorTextLoading));
                topLine.setBackgroundColor(getResources().getColor(R.color.colorTextLoading));
                rightLine.setBackgroundColor(getResources().getColor(R.color.colorTextLoading));
                leftLine.setBackgroundColor(getResources().getColor(R.color.colorTextLoading));

                center_level.setTag("f");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        p_levelFragment.cancel();
    }
}
