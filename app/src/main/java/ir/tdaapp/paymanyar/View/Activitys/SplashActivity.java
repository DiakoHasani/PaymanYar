package ir.tdaapp.paymanyar.View.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import ir.tdaapp.li_utility.Codes.Replace;
import ir.tdaapp.paymanyar.Model.Services.S_SplashActivity;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Statistics;
import ir.tdaapp.paymanyar.Presenter.P_SplashActivity;
import ir.tdaapp.paymanyar.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class SplashActivity extends AppCompatActivity implements View.OnTouchListener, S_SplashActivity {

  P_SplashActivity p_splashActivity;

  RelativeLayout image2, image3, image4, root;
  TextView txtSplash1, txtSplash2, txtSplash3, installCount, orderCount, adCount;
  ImageView imgSplash1, imgSplash2, imgSplash3;
  ViewGroup statsRoot;

  boolean isReleased = true;
  boolean isTouchedAtAll = false;

  Handler handler = new Handler();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    findView();
    p_splashActivity.start();

    new Handler().postDelayed(() -> {
      Animation aniFade = AnimationUtils.loadAnimation(this, R.anim.fadein);
      imgSplash1.setAnimation(aniFade);
      imgSplash2.setAnimation(aniFade);
      imgSplash3.setAnimation(aniFade);

      imgSplash1.setVisibility(View.VISIBLE);
      imgSplash2.setVisibility(View.VISIBLE);
      imgSplash3.setVisibility(View.VISIBLE);
    }, 500);

    new Handler().postDelayed(() -> {
      Animation aniFade = AnimationUtils.loadAnimation(this, R.anim.fadein);

      txtSplash1.setAnimation(aniFade);
      txtSplash2.setAnimation(aniFade);
      txtSplash3.setAnimation(aniFade);
      installCount.setAnimation(aniFade);
      adCount.setAnimation(aniFade);
      orderCount.setAnimation(aniFade);

      txtSplash1.setVisibility(View.VISIBLE);
      txtSplash2.setVisibility(View.VISIBLE);
      txtSplash3.setVisibility(View.VISIBLE);
      installCount.setVisibility(View.VISIBLE);
      adCount.setVisibility(View.VISIBLE);
      orderCount.setVisibility(View.VISIBLE);
    }, 750);

    new Handler().postDelayed(() -> {
      Animation aniFade = AnimationUtils.loadAnimation(this, R.anim.fadein);
      image2.setAnimation(aniFade);
      image2.setVisibility(View.VISIBLE);
    }, 1250);

    new Handler().postDelayed(() -> {
      Animation aniFade = AnimationUtils.loadAnimation(this, R.anim.fadein);
      image3.setAnimation(aniFade);
      image3.setVisibility(View.VISIBLE);
    }, 2000);

    new Handler().postDelayed(() -> {
      Animation aniFade = AnimationUtils.loadAnimation(this, R.anim.fadein);
      image4.setAnimation(aniFade);
      image4.setVisibility(View.VISIBLE);
    }, 2500);

    new Handler().postDelayed(() -> {
      startActivity(new Intent(this, MainActivity.class));
      finish();
    }, 3250);

    new Thread(() -> {
      if (isTouchedAtAll) {
        while (true) {
          if (isReleased) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            break;
          } else {
            continue;
          }
        }
      }
    });
  }

  private void findView() {
    p_splashActivity = new P_SplashActivity(SplashActivity.this, this);
    image2 = findViewById(R.id.image2);
    image3 = findViewById(R.id.image3);
    image4 = findViewById(R.id.image4);
    root = findViewById(R.id.splashRoot);
    statsRoot = findViewById(R.id.splashStatisticsRoot);
    root.setOnTouchListener(this);

    imgSplash1 = findViewById(R.id.imgSplash1);
    imgSplash2 = findViewById(R.id.imgSplash2);
    imgSplash3 = findViewById(R.id.imgSplash3);
    txtSplash1 = findViewById(R.id.txtSplash1);
    txtSplash2 = findViewById(R.id.txtSplash2);
    txtSplash3 = findViewById(R.id.txtSplash3);

    installCount = findViewById(R.id.txtInstallCount);
    adCount = findViewById(R.id.txtAdCount);
    orderCount = findViewById(R.id.txtOrderCount);
  }


  @Override
  public boolean onTouch(View view, MotionEvent motionEvent) {
    switch (motionEvent.getAction()) {
      case MotionEvent.ACTION_DOWN:
        if (view.getId() == R.id.splashRoot) {
          Log.i("Log", "ActionDown: " + motionEvent.getDownTime());
          isReleased = false;
          isTouchedAtAll = true;
        }
        break;

      case MotionEvent.ACTION_UP:
        if (view.getId() == R.id.splashRoot) {
          Log.i("Log", "ActionUp: " + motionEvent.getEventTime());
          isReleased = true;
          isTouchedAtAll = true;
        }
        break;
    }
    return true;
  }

  @Override
  public void onReceivedStatistics(List<VM_Statistics> statistics) {
    VM_Statistics vm_statistics = statistics.get(0);
    installCount.setText(Replace.Number_en_To_fa(String.valueOf(vm_statistics.getActiveInstallsCount())));
    adCount.setText(Replace.Number_en_To_fa(String.valueOf(vm_statistics.getAdCount())));
    orderCount.setText(Replace.Number_en_To_fa(String.valueOf(vm_statistics.getOrderCount())));

  }

  @Override
  public void onError(String s) {
    statsRoot.setVisibility(View.GONE);
  }
}
