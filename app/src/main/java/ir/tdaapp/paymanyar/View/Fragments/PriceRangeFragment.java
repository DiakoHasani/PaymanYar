package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import ir.tdaapp.li_utility.Codes.ShowPrice;
import ir.tdaapp.li_utility.Codes.Validation;
import ir.tdaapp.paymanyar.Model.Services.S_PriceRangeFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.PercentPriceRange;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PriceRange;
import ir.tdaapp.paymanyar.Presenter.P_PriceRangeFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;

//صفحه دامنه قیمت
public class PriceRangeFragment extends BaseFragment implements S_PriceRangeFragment, View.OnClickListener {

    P_PriceRangeFragment p_priceRangeFragment;

    EditText txt_Price, txt_Number_of_People;
    EditText txt_AmountToToman1, txt_AmountToToman2, txt_AmountToToman3, txt_AmountToToman4, txt_AmountToToman5, txt_AmountToToman6;
    EditText txt_AmountToToman7, txt_AmountToToman8, percent1, percent2, percent3, percent4, percent5, percent6, percent7, percent8, txt_Percentage, txt_guarantee;
    Spinner cmb_Degree_of_Importance;
    TextView winnerPrice, winnerNumber;
    RelativeLayout chPoint1, chPoint2, chPoint3, chPoint4, chPoint5, chPoint6, chPoint7, chPoint8;
    RelativeLayout ChartUpLine, ChartDownLine;
    CardView btnCalc, btn_Home;
    Toolbar toolbar;

    public static final String TAG = "PriceRangeFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.price_range_fragment, container, false);

        findItem(view);
        implement();
        p_priceRangeFragment.start();

        setToolbar();

        return view;
    }

    void findItem(View view) {
        txt_Price = view.findViewById(R.id.txt_Price);
        txt_Number_of_People = view.findViewById(R.id.txt_Number_of_People);
        cmb_Degree_of_Importance = view.findViewById(R.id.cmb_Degree_of_Importance);
        toolbar = view.findViewById(R.id.toolbar);
        txt_AmountToToman1 = view.findViewById(R.id.txt_AmountToToman1);
        txt_AmountToToman2 = view.findViewById(R.id.txt_AmountToToman2);
        txt_AmountToToman3 = view.findViewById(R.id.txt_AmountToToman3);
        txt_AmountToToman4 = view.findViewById(R.id.txt_AmountToToman4);
        txt_AmountToToman5 = view.findViewById(R.id.txt_AmountToToman5);
        txt_AmountToToman6 = view.findViewById(R.id.txt_AmountToToman6);
        txt_AmountToToman7 = view.findViewById(R.id.txt_AmountToToman7);
        txt_AmountToToman8 = view.findViewById(R.id.txt_AmountToToman8);
        percent1 = view.findViewById(R.id.pricerange_percent1);
        percent2 = view.findViewById(R.id.pricerange_percent2);
        percent3 = view.findViewById(R.id.pricerange_percent3);
        percent4 = view.findViewById(R.id.pricerange_percent4);
        percent5 = view.findViewById(R.id.pricerange_percent5);
        percent6 = view.findViewById(R.id.pricerange_percent6);
        percent7 = view.findViewById(R.id.pricerange_percent7);
        percent8 = view.findViewById(R.id.pricerange_percent8);
        btnCalc = view.findViewById(R.id.btn_PriceRange);
        txt_guarantee = view.findViewById(R.id.txt_percentage);

        chPoint1 = view.findViewById(R.id.pricerange_chartpoint1);
        chPoint2 = view.findViewById(R.id.pricerange_chartpoint2);
        chPoint3 = view.findViewById(R.id.pricerange_chartpoint3);
        chPoint4 = view.findViewById(R.id.pricerange_chartpoint4);
        chPoint5 = view.findViewById(R.id.pricerange_chartpoint5);
        chPoint6 = view.findViewById(R.id.pricerange_chartpoint6);
        chPoint7 = view.findViewById(R.id.pricerange_chartpoint7);
        chPoint8 = view.findViewById(R.id.pricerange_chartpoint8);

        ChartDownLine = (RelativeLayout) view.findViewById(R.id.pricerange_chartDownLine);
        ChartUpLine = (RelativeLayout) view.findViewById(R.id.pricerange_chartUpLine);

        winnerNumber = view.findViewById(R.id.pricerange_winnerNumber);
        winnerPrice = view.findViewById(R.id.pricerange_winnerprice);

        txt_Percentage = view.findViewById(R.id.txt_percentage);
        btn_Home = view.findViewById(R.id.btn_Home);
    }

    void implement() {
        p_priceRangeFragment = new P_PriceRangeFragment(getContext(), this);
        txt_Percentage.addTextChangedListener(new ShowPrice(txt_Percentage));
        txt_Price.addTextChangedListener(new ShowPrice(txt_Price));
        txt_AmountToToman1.addTextChangedListener(new ShowPrice(txt_AmountToToman1));
        txt_AmountToToman2.addTextChangedListener(new ShowPrice(txt_AmountToToman2));
        txt_AmountToToman3.addTextChangedListener(new ShowPrice(txt_AmountToToman3));
        txt_AmountToToman4.addTextChangedListener(new ShowPrice(txt_AmountToToman4));
        txt_AmountToToman5.addTextChangedListener(new ShowPrice(txt_AmountToToman5));
        txt_AmountToToman6.addTextChangedListener(new ShowPrice(txt_AmountToToman6));
        txt_AmountToToman7.addTextChangedListener(new ShowPrice(txt_AmountToToman7));
        txt_AmountToToman8.addTextChangedListener(new ShowPrice(txt_AmountToToman8));
        txt_guarantee.addTextChangedListener(new ShowPrice(txt_Percentage));
        btnCalc.setOnClickListener(this);
        btn_Home.setOnClickListener(this);

        txt_AmountToToman1.addTextChangedListener(new PercentPriceRange(percent1, txt_AmountToToman1, txt_Price));
        txt_AmountToToman2.addTextChangedListener(new PercentPriceRange(percent2, txt_AmountToToman2, txt_Price));
        txt_AmountToToman3.addTextChangedListener(new PercentPriceRange(percent3, txt_AmountToToman3, txt_Price));
        txt_AmountToToman4.addTextChangedListener(new PercentPriceRange(percent4, txt_AmountToToman4, txt_Price));
        txt_AmountToToman5.addTextChangedListener(new PercentPriceRange(percent5, txt_AmountToToman5, txt_Price));
        txt_AmountToToman6.addTextChangedListener(new PercentPriceRange(percent6, txt_AmountToToman6, txt_Price));
        txt_AmountToToman7.addTextChangedListener(new PercentPriceRange(percent7, txt_AmountToToman7, txt_Price));
        txt_AmountToToman8.addTextChangedListener(new PercentPriceRange(percent8, txt_AmountToToman8, txt_Price));

        txt_Price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                setPercents();
            }
        });
    }

    //در اینجا تنظیمات تولبار ست می شود
    void setToolbar() {
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setTitle(getContext().getResources().getString(R.string.PriceRange));
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(v -> {
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

    //در اینجا آداپتر میزان اهمیت گرفته می شود
    @Override
    public void onGetDegreeOfImportance(ArrayAdapter adapter) {
        cmb_Degree_of_Importance.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_PriceRange:
                Calculate();
                break;
            case R.id.btn_Home:
                ((MainActivity) getActivity()).backToHome();
                break;
        }
    }

    private void Calculate() {

        //چک کنیم که مبلغ مناقصه وارد شده است
        if (Validation.Required(txt_Price, getString(R.string.Please_Input_The_Price))) {

            //ابتدا درصدها مشخص شود
            setPercents();

            //ساخت یک آرایه از مقادیر پیشنهادی
            ArrayList<VM_PriceRange> arrayList = new ArrayList<>();
            if (txt_AmountToToman1.getText().toString().length() > 0) {
                arrayList.add(new VM_PriceRange("1", txt_AmountToToman1.getText().toString().replaceAll(",", ""), percent1.getText().toString()));
                ChartPoint(percent1.getText().toString(), chPoint1, 1);
            }
            if (txt_AmountToToman2.getText().toString().length() > 0) {
                arrayList.add(new VM_PriceRange("2", txt_AmountToToman2.getText().toString().replaceAll(",", ""), percent2.getText().toString()));
                ChartPoint(percent2.getText().toString(), chPoint2, 2);
            }
            if (txt_AmountToToman3.getText().toString().length() > 0) {
                arrayList.add(new VM_PriceRange("3", txt_AmountToToman3.getText().toString().replaceAll(",", ""), percent3.getText().toString()));
                ChartPoint(percent3.getText().toString(), chPoint3, 3);
            }
            if (txt_AmountToToman4.getText().toString().length() > 0) {
                arrayList.add(new VM_PriceRange("4", txt_AmountToToman4.getText().toString().replaceAll(",", ""), percent4.getText().toString()));
                ChartPoint(percent4.getText().toString(), chPoint4, 4);
            }
            if (txt_AmountToToman5.getText().toString().length() > 0) {
                arrayList.add(new VM_PriceRange("5", txt_AmountToToman5.getText().toString().replaceAll(",", ""), percent5.getText().toString()));
                ChartPoint(percent5.getText().toString(), chPoint5, 5);
            }
            if (txt_AmountToToman6.getText().toString().length() > 0) {
                arrayList.add(new VM_PriceRange("6", txt_AmountToToman6.getText().toString().replaceAll(",", ""), percent6.getText().toString()));
                ChartPoint(percent6.getText().toString(), chPoint6, 6);
            }
            if (txt_AmountToToman7.getText().toString().length() > 0) {
                arrayList.add(new VM_PriceRange("7", txt_AmountToToman7.getText().toString().replaceAll(",", ""), percent7.getText().toString()));
                ChartPoint(percent7.getText().toString(), chPoint7, 7);
            }
            if (txt_AmountToToman8.getText().toString().length() > 0) {
                arrayList.add(new VM_PriceRange("8", txt_AmountToToman8.getText().toString().replaceAll(",", ""), percent8.getText().toString()));
                ChartPoint(percent8.getText().toString(), chPoint8, 8);
            }

            //شروع محاسبه
            p_priceRangeFragment.StartCalculate(txt_Price.getText().toString().replaceAll(",", ""), arrayList, txt_guarantee.getText().toString().replaceAll(",", ""), cmb_Degree_of_Importance.getSelectedItemPosition() + 1);

        }
    }

    //تنظیم ارتفاع هر کدام از نقاط چارت براساس درصد پیشنهادی
    private void ChartPoint(String Percenet, RelativeLayout obj, int number) {
        try {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(obj.getLayoutParams());
            String val = Percenet.substring(0, Percenet.indexOf("."));
            params.setMargins(IntToDP(params.leftMargin), IntToDP(params.topMargin), IntToDP(number * 20 + 20), IntToDP(Integer.valueOf(val) + 12));
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

            //فق براساس درصد پیشنهادی از پایین فاصله میدهیم
            obj.setLayoutParams(params);
        }catch (Exception e){}
    }

    @Override
    public void onWinnerChoosed(String number, String position, String CUp, String CDown) {
        winnerPrice.setText(number);
        winnerNumber.setText(position);

        //نمایش خط حد بالا و پایین

        //حد بالا
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ChartUpLine.getLayoutParams());
        int m = Integer.valueOf(CUp.substring(0, CUp.indexOf(".")));
        params.setMargins(IntToDP(params.leftMargin), IntToDP(params.topMargin), IntToDP(50), IntToDP(m + 18));
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        ChartUpLine.setLayoutParams(params);


        //حد پایین
        params = new RelativeLayout.LayoutParams(ChartDownLine.getLayoutParams());
        m = Integer.valueOf(CDown.substring(0, CDown.indexOf(".")));
        params.setMargins(IntToDP(params.leftMargin), IntToDP(params.topMargin), IntToDP(50), IntToDP(m + 18));
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        ChartDownLine.setLayoutParams(params);
    }

    private int IntToDP(int value) {

        //تبدیل عدد صحیح به dp
        int marginInDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, value, getResources()
                        .getDisplayMetrics());
        return marginInDp;
    }

    //در اینجا درصدها ست می شود
    void setPercents() {
        percent1.setText(p_priceRangeFragment.CalculatePercent(txt_AmountToToman1.getText().toString().replaceAll(",", ""), txt_Price.getText().toString().replaceAll(",", "")));
        percent2.setText(p_priceRangeFragment.CalculatePercent(txt_AmountToToman2.getText().toString().replaceAll(",", ""), txt_Price.getText().toString().replaceAll(",", "")));
        percent3.setText(p_priceRangeFragment.CalculatePercent(txt_AmountToToman3.getText().toString().replaceAll(",", ""), txt_Price.getText().toString().replaceAll(",", "")));
        percent4.setText(p_priceRangeFragment.CalculatePercent(txt_AmountToToman4.getText().toString().replaceAll(",", ""), txt_Price.getText().toString().replaceAll(",", "")));
        percent5.setText(p_priceRangeFragment.CalculatePercent(txt_AmountToToman5.getText().toString().replaceAll(",", ""), txt_Price.getText().toString().replaceAll(",", "")));
        percent6.setText(p_priceRangeFragment.CalculatePercent(txt_AmountToToman6.getText().toString().replaceAll(",", ""), txt_Price.getText().toString().replaceAll(",", "")));
        percent7.setText(p_priceRangeFragment.CalculatePercent(txt_AmountToToman7.getText().toString().replaceAll(",", ""), txt_Price.getText().toString().replaceAll(",", "")));
        percent8.setText(p_priceRangeFragment.CalculatePercent(txt_AmountToToman8.getText().toString().replaceAll(",", ""), txt_Price.getText().toString().replaceAll(",", "")));
    }

}
