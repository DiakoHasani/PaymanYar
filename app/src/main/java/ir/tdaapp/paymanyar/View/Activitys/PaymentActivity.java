package ir.tdaapp.paymanyar.View.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_User;
import ir.tdaapp.paymanyar.Model.Services.S_PaymentActivity;
import ir.tdaapp.paymanyar.Presenter.P_PaymentActivity;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Dialogs.ErrorAplicationDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PaymentActivity extends AppCompatActivity implements S_PaymentActivity, View.OnClickListener {

    public final static String TAG = "PaymentActivity";

    P_PaymentActivity p_paymentActivity;
    ProgressBar progressDay, progressHour;
    TextView lbl_Day, lbl_Hour;
    CardView btn_back;
    ErrorAplicationDialog errorAplicationDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        findItem();
        implement();

        p_paymentActivity.start();
    }

    void findItem() {
        progressDay = findViewById(R.id.progressDay);
        progressHour = findViewById(R.id.progressHour);
        lbl_Day = findViewById(R.id.lbl_Day);
        lbl_Hour = findViewById(R.id.lbl_Hour);
        btn_back = findViewById(R.id.btn_back);
    }

    void implement() {
        p_paymentActivity = new P_PaymentActivity(getApplicationContext(), this);
        btn_back.setOnClickListener(this);
    }

    @Override
    public void OnStart() {
        lbl_Day.setText("");
        lbl_Hour.setText("");
    }

    @Override
    public void onError(ResaultCode result) {
        String text = "";

        switch (result) {
            case NetworkError:
                text = getString(R.string.please_Checked_Your_Internet_Connection);
                break;
            case TimeoutError:
                text = getString(R.string.YourInternetIsVrySlow);
                break;
            case ServerError:
                text = getString(R.string.There_Was_an_Error_In_The_Server);
                break;
            case ParseError:
            case Error:
                text = getString(R.string.There_Was_an_Error_In_The_Application);
                break;
        }

        errorAplicationDialog = new ErrorAplicationDialog(getString(R.string.Error), text, getString(R.string.Again), R.drawable.ic_error, R.color.colorError, () -> {
            errorAplicationDialog.dismiss();
            p_paymentActivity.start();
        });
        errorAplicationDialog.show(getSupportFragmentManager(), ErrorAplicationDialog.TAG);
    }

    @Override
    public void onLoading(boolean load) {
        if (load) {
            lbl_Day.setVisibility(View.GONE);
            lbl_Hour.setVisibility(View.GONE);

            progressDay.setVisibility(View.VISIBLE);
            progressHour.setVisibility(View.VISIBLE);
        } else {
            lbl_Day.setVisibility(View.VISIBLE);
            lbl_Hour.setVisibility(View.VISIBLE);

            progressDay.setVisibility(View.GONE);
            progressHour.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFinish(int day, int hour) {
        lbl_Day.setText(day + "");
        lbl_Hour.setText(hour + "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        p_paymentActivity.Cancel(TAG);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                if (MainActivity.isActive) {
                    finish();
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
                break;
        }
    }
}
