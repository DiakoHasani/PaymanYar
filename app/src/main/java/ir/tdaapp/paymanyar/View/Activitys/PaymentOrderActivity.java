package ir.tdaapp.paymanyar.View.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import ir.tdaapp.paymanyar.R;

import android.content.Intent;
import android.os.Bundle;

//اگر کاربر هزینه یک سفارش را پرداخت کند بعد از پرداخت به این صفحه برگشت داده می شود
public class PaymentOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_order);

        if (MainActivity.isActive) {
            finish();
        } else {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }
}
