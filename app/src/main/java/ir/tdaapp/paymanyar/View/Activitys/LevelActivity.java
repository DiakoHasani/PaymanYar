package ir.tdaapp.paymanyar.View.Activitys;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
    float xBubble, yBubble = 0;
    int backgroundSize = 0;
    DisplayMetrics displayMetrics;
    ImageView border_background;
    RelativeLayout topLine,rightLine,bottomLine,leftLine;

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

        background.getLayoutParams().height = ActivityWidth - backgroundSize;
        background.getLayoutParams().width = ActivityWidth - backgroundSize;
        border_background.getLayoutParams().height = ActivityWidth - backgroundSize + Math.round(onDpTopixel(8));
        border_background.getLayoutParams().width = ActivityWidth - backgroundSize + Math.round(onDpTopixel(8));

        topLine.getLayoutParams().height = (ActivityWidth - backgroundSize) / 2 - Math.round(yBubble);
        bottomLine.getLayoutParams().height = (ActivityWidth - backgroundSize) / 2 - Math.round(yBubble);
        rightLine.getLayoutParams().width = (ActivityWidth - backgroundSize) / 2 - Math.round(yBubble);
        leftLine.getLayoutParams().width = (ActivityWidth - backgroundSize) / 2 - Math.round(yBubble);
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
    public void onPositionBubble(int x, int y) {
        img_bubble.animate().x((ActivityWidth - x) - xBubble).y(y - yBubble).setDuration(140);
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

    //در اینجا اندازه عرض صفحه نمایش برگشت داده می شود
    public int getWidthDisplay() {
        return displayMetrics.widthPixels;
    }

    //در اینجا اندازه طول صفحه نمایش برگشت داده می شود
    public int getHeightDisplay() {
        return displayMetrics.heightPixels;
    }
}
