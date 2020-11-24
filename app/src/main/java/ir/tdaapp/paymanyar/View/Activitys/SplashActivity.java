package ir.tdaapp.paymanyar.View.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import ir.tdaapp.paymanyar.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

public class SplashActivity extends AppCompatActivity {

    RelativeLayout image2, image3, image4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);

        new Handler().postDelayed(() -> {
            Animation aniFade=AnimationUtils.loadAnimation(this,R.anim.fadein);
            image2.setAnimation(aniFade);
            image2.setVisibility(View.VISIBLE);
        },500);

        new Handler().postDelayed(() -> {
            Animation aniFade=AnimationUtils.loadAnimation(this,R.anim.fadein);
            image3.setAnimation(aniFade);
            image3.setVisibility(View.VISIBLE);
        },1000);

        new Handler().postDelayed(() -> {
            Animation aniFade=AnimationUtils.loadAnimation(this,R.anim.fadein);
            image4.setAnimation(aniFade);
            image4.setVisibility(View.VISIBLE);
        },1500);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }, 2500);
    }
}
