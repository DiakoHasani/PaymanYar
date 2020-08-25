package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
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

import androidx.core.content.res.ResourcesCompat;
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
    RelativeLayout chPoint1, chPoint2, chPoint3, chPoint4, chPoint5, chPoint6, chPoint7, chPoint8, chPoint9, chPoint10, chPoint11, chPoint12;
    RelativeLayout ChartUpLine, ChartDownLine;
    CardView btnCalc, btn_Home;
    Toolbar toolbar;
    EditText txt_AmountToToman9, txt_AmountToToman10, txt_AmountToToman11, txt_AmountToToman12, percent9, percent10, percent11, percent12;
    TextView Low_limit, lbl_upper_line, lbl_outOfRange;
    CardView chart;
    RelativeLayout chartLine, chartNumbers;
    int[] chartVals;

    float spaceChart = 1;

    //مربوط به خط کنار چارت
    View chart_view0, chart_view1, chart_view2, chart_view3, chart_view4, chart_view5, chart_view6, chart_view7, chart_view8, chart_view9, chart_view10, chart_view11;

    //مربوط به اعداد چارت
    TextView chart_lbl0, chart_lbl1, chart_lbl2, chart_lbl3, chart_lbl4, chart_lbl5, chart_lbl6, chart_lbl7, chart_lbl8, chart_lbl9, chart_lbl10, chart_lbl11;

    //مربوط به نقطه های چارت می باشد
    RelativeLayout circle_pricerange_chartpoint1, circle_pricerange_chartpoint2, circle_pricerange_chartpoint3;
    RelativeLayout circle_pricerange_chartpoint4, circle_pricerange_chartpoint5, circle_pricerange_chartpoint6;
    RelativeLayout circle_pricerange_chartpoint7, circle_pricerange_chartpoint8, circle_pricerange_chartpoint9;
    RelativeLayout circle_pricerange_chartpoint10, circle_pricerange_chartpoint11, circle_pricerange_chartpoint12;

    public static final String TAG = "PriceRangeFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.price_range_fragment, container, false);

        findItem(view);
        implement();
        onHideAllChPoints();
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
        txt_AmountToToman9 = view.findViewById(R.id.txt_AmountToToman9);
        txt_AmountToToman10 = view.findViewById(R.id.txt_AmountToToman10);
        txt_AmountToToman11 = view.findViewById(R.id.txt_AmountToToman11);
        txt_AmountToToman12 = view.findViewById(R.id.txt_AmountToToman12);
        percent1 = view.findViewById(R.id.pricerange_percent1);
        percent2 = view.findViewById(R.id.pricerange_percent2);
        percent3 = view.findViewById(R.id.pricerange_percent3);
        percent4 = view.findViewById(R.id.pricerange_percent4);
        percent5 = view.findViewById(R.id.pricerange_percent5);
        percent6 = view.findViewById(R.id.pricerange_percent6);
        percent7 = view.findViewById(R.id.pricerange_percent7);
        percent8 = view.findViewById(R.id.pricerange_percent8);
        percent9 = view.findViewById(R.id.pricerange_percent9);
        percent10 = view.findViewById(R.id.pricerange_percent10);
        percent11 = view.findViewById(R.id.pricerange_percent11);
        percent12 = view.findViewById(R.id.pricerange_percent12);
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
        chPoint9 = view.findViewById(R.id.pricerange_chartpoint9);
        chPoint10 = view.findViewById(R.id.pricerange_chartpoint10);
        chPoint11 = view.findViewById(R.id.pricerange_chartpoint11);
        chPoint12 = view.findViewById(R.id.pricerange_chartpoint12);

        ChartDownLine = view.findViewById(R.id.pricerange_chartDownLine);
        ChartUpLine = view.findViewById(R.id.pricerange_chartUpLine);

        winnerNumber = view.findViewById(R.id.pricerange_winnerNumber);
        winnerPrice = view.findViewById(R.id.pricerange_winnerprice);

        txt_Percentage = view.findViewById(R.id.txt_percentage);
        btn_Home = view.findViewById(R.id.btn_Home);
        lbl_upper_line = view.findViewById(R.id.lbl_upper_line);
        Low_limit = view.findViewById(R.id.Low_limit);
        lbl_outOfRange = view.findViewById(R.id.lbl_outOfRange);

        chart = view.findViewById(R.id.chart);
        chartLine = view.findViewById(R.id.line);
        chartNumbers = view.findViewById(R.id.chartNumbers);

        chart_view0 = view.findViewById(R.id.chart_view0);
        chart_view1 = view.findViewById(R.id.chart_view1);
        chart_view2 = view.findViewById(R.id.chart_view2);
        chart_view3 = view.findViewById(R.id.chart_view3);
        chart_view4 = view.findViewById(R.id.chart_view4);
        chart_view5 = view.findViewById(R.id.chart_view5);
        chart_view6 = view.findViewById(R.id.chart_view6);
        chart_view7 = view.findViewById(R.id.chart_view7);
        chart_view8 = view.findViewById(R.id.chart_view8);
        chart_view9 = view.findViewById(R.id.chart_view9);
        chart_view10 = view.findViewById(R.id.chart_view10);
        chart_view11 = view.findViewById(R.id.chart_view11);

        chart_lbl0 = view.findViewById(R.id.chart_lbl0);
        chart_lbl1 = view.findViewById(R.id.chart_lbl1);
        chart_lbl2 = view.findViewById(R.id.chart_lbl2);
        chart_lbl3 = view.findViewById(R.id.chart_lbl3);
        chart_lbl4 = view.findViewById(R.id.chart_lbl4);
        chart_lbl5 = view.findViewById(R.id.chart_lbl5);
        chart_lbl6 = view.findViewById(R.id.chart_lbl6);
        chart_lbl7 = view.findViewById(R.id.chart_lbl7);
        chart_lbl8 = view.findViewById(R.id.chart_lbl8);
        chart_lbl9 = view.findViewById(R.id.chart_lbl9);
        chart_lbl10 = view.findViewById(R.id.chart_lbl10);
        chart_lbl11 = view.findViewById(R.id.chart_lbl11);

        circle_pricerange_chartpoint1 = view.findViewById(R.id.circle_pricerange_chartpoint1);
        circle_pricerange_chartpoint2 = view.findViewById(R.id.circle_pricerange_chartpoint2);
        circle_pricerange_chartpoint3 = view.findViewById(R.id.circle_pricerange_chartpoint3);
        circle_pricerange_chartpoint4 = view.findViewById(R.id.circle_pricerange_chartpoint4);
        circle_pricerange_chartpoint5 = view.findViewById(R.id.circle_pricerange_chartpoint5);
        circle_pricerange_chartpoint6 = view.findViewById(R.id.circle_pricerange_chartpoint6);
        circle_pricerange_chartpoint7 = view.findViewById(R.id.circle_pricerange_chartpoint7);
        circle_pricerange_chartpoint8 = view.findViewById(R.id.circle_pricerange_chartpoint8);
        circle_pricerange_chartpoint9 = view.findViewById(R.id.circle_pricerange_chartpoint9);
        circle_pricerange_chartpoint10 = view.findViewById(R.id.circle_pricerange_chartpoint10);
        circle_pricerange_chartpoint11 = view.findViewById(R.id.circle_pricerange_chartpoint11);
        circle_pricerange_chartpoint12 = view.findViewById(R.id.circle_pricerange_chartpoint12);
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
        txt_AmountToToman9.addTextChangedListener(new ShowPrice(txt_AmountToToman9));
        txt_AmountToToman10.addTextChangedListener(new ShowPrice(txt_AmountToToman10));
        txt_AmountToToman11.addTextChangedListener(new ShowPrice(txt_AmountToToman11));
        txt_AmountToToman12.addTextChangedListener(new ShowPrice(txt_AmountToToman12));
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
        txt_AmountToToman9.addTextChangedListener(new PercentPriceRange(percent9, txt_AmountToToman9, txt_Price));
        txt_AmountToToman10.addTextChangedListener(new PercentPriceRange(percent10, txt_AmountToToman10, txt_Price));
        txt_AmountToToman11.addTextChangedListener(new PercentPriceRange(percent11, txt_AmountToToman11, txt_Price));
        txt_AmountToToman12.addTextChangedListener(new PercentPriceRange(percent12, txt_AmountToToman12, txt_Price));

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

        chartVals = new int[12];
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
                lbl_outOfRange.setText("---");
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

            onHideAllChPoints();

            //ابتدا درصدها مشخص شود
            setPercents();

            int maxPercent = getMaxPercents();

            if (maxPercent < 1000) {

                setDefultPointChart();

                onSetChartNumbers(maxPercent);

                //ساخت یک آرایه از مقادیر پیشنهادی
                ArrayList<VM_PriceRange> arrayList = new ArrayList<>();
                if (txt_AmountToToman1.getText().toString().length() > 0) {
                    chPoint1.setVisibility(View.VISIBLE);
                    arrayList.add(new VM_PriceRange("1", txt_AmountToToman1.getText().toString().replace(",", "").replace("٬", ""), percent1.getText().toString()));
                    ChartPoint(percent1.getText().toString(), chPoint1, 1);
                }
                if (txt_AmountToToman2.getText().toString().length() > 0) {
                    chPoint2.setVisibility(View.VISIBLE);
                    arrayList.add(new VM_PriceRange("2", txt_AmountToToman2.getText().toString().replace(",", "").replace("٬", ""), percent2.getText().toString()));
                    ChartPoint(percent2.getText().toString(), chPoint2, 2);
                }
                if (txt_AmountToToman3.getText().toString().length() > 0) {
                    chPoint3.setVisibility(View.VISIBLE);
                    arrayList.add(new VM_PriceRange("3", txt_AmountToToman3.getText().toString().replace(",", "").replace("٬", ""), percent3.getText().toString()));
                    ChartPoint(percent3.getText().toString(), chPoint3, 3);
                }
                if (txt_AmountToToman4.getText().toString().length() > 0) {
                    chPoint4.setVisibility(View.VISIBLE);
                    arrayList.add(new VM_PriceRange("4", txt_AmountToToman4.getText().toString().replace(",", "").replace("٬", ""), percent4.getText().toString()));
                    ChartPoint(percent4.getText().toString(), chPoint4, 4);
                }
                if (txt_AmountToToman5.getText().toString().length() > 0) {
                    chPoint5.setVisibility(View.VISIBLE);
                    arrayList.add(new VM_PriceRange("5", txt_AmountToToman5.getText().toString().replace(",", "").replace("٬", ""), percent5.getText().toString()));
                    ChartPoint(percent5.getText().toString(), chPoint5, 5);
                }
                if (txt_AmountToToman6.getText().toString().length() > 0) {
                    chPoint6.setVisibility(View.VISIBLE);
                    arrayList.add(new VM_PriceRange("6", txt_AmountToToman6.getText().toString().replace(",", "").replace("٬", ""), percent6.getText().toString()));
                    ChartPoint(percent6.getText().toString(), chPoint6, 6);
                }
                if (txt_AmountToToman7.getText().toString().length() > 0) {
                    chPoint7.setVisibility(View.VISIBLE);
                    arrayList.add(new VM_PriceRange("7", txt_AmountToToman7.getText().toString().replace(",", "").replace("٬", ""), percent7.getText().toString()));
                    ChartPoint(percent7.getText().toString(), chPoint7, 7);
                }
                if (txt_AmountToToman8.getText().toString().length() > 0) {
                    chPoint8.setVisibility(View.VISIBLE);
                    arrayList.add(new VM_PriceRange("8", txt_AmountToToman8.getText().toString().replace(",", "").replace("٬", ""), percent8.getText().toString()));
                    ChartPoint(percent8.getText().toString(), chPoint8, 8);
                }
                if (txt_AmountToToman9.getText().toString().length() > 0) {
                    chPoint9.setVisibility(View.VISIBLE);
                    arrayList.add(new VM_PriceRange("9", txt_AmountToToman9.getText().toString().replace(",", "").replace("٬", ""), percent9.getText().toString()));
                    ChartPoint(percent9.getText().toString(), chPoint9, 9);
                }
                if (txt_AmountToToman10.getText().toString().length() > 0) {
                    chPoint10.setVisibility(View.VISIBLE);
                    arrayList.add(new VM_PriceRange("10", txt_AmountToToman10.getText().toString().replace(",", "").replace("٬", ""), percent10.getText().toString()));
                    ChartPoint(percent10.getText().toString(), chPoint10, 10);
                }
                if (txt_AmountToToman11.getText().toString().length() > 0) {
                    chPoint11.setVisibility(View.VISIBLE);
                    arrayList.add(new VM_PriceRange("11", txt_AmountToToman11.getText().toString().replace(",", "").replace("٬", ""), percent11.getText().toString()));
                    ChartPoint(percent11.getText().toString(), chPoint11, 11);
                }
                if (txt_AmountToToman12.getText().toString().length() > 0) {
                    chPoint12.setVisibility(View.VISIBLE);
                    arrayList.add(new VM_PriceRange("12", txt_AmountToToman12.getText().toString().replace(",", "").replace("٬", ""), percent12.getText().toString()));
                    ChartPoint(percent12.getText().toString(), chPoint12, 12);
                }

                //شروع محاسبه
                p_priceRangeFragment.StartCalculate(txt_Price.getText().toString().replace(",", "").replace("٬", ""), arrayList, txt_guarantee.getText().toString().replace(",", "").replace("٬", ""), cmb_Degree_of_Importance.getSelectedItemPosition() + 1);


                //در اینجا فقط بین دوخط دامنه نمایش داده می شوند یعنی بقیه دامنه نمایش داده نمی شود
//                if (!lbl_upper_line.getText().toString().equalsIgnoreCase("")) {
//                    float v = Float.valueOf(lbl_upper_line.getText().toString());
//                    int v2 = (int) v;
//                    hideNumbersChart(v2);
//                }

            } else {
                Toast.makeText(getContext(), getResources().getString(R.string.The_maximum_percentage_should_be_999), Toast.LENGTH_SHORT).show();
            }

        }
    }

    //تنظیم ارتفاع هر کدام از نقاط چارت براساس درصد پیشنهادی
    private void ChartPoint(String Percenet, RelativeLayout obj, int number) {
        try {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(obj.getLayoutParams());
            String val = Percenet.substring(0, Percenet.indexOf("."));
            int temp = (int) (Integer.valueOf(val) / spaceChart);
            params.setMargins(IntToDP(params.leftMargin), IntToDP(params.topMargin), IntToDP(number * 21 + 20), IntToDP(temp + 12));
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

            //فق براساس درصد پیشنهادی از پایین فاصله میدهیم
            obj.setLayoutParams(params);

        } catch (Exception e) {
        }
    }

    @Override
    public void onWinnerChoosed(String number, String position, String CUp, String CDown) {
        winnerPrice.setText(number);
        winnerNumber.setText(position);

        enableAnimationPointChart(position);

        //نمایش خط حد بالا و پایین

        //حد بالا
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ChartUpLine.getLayoutParams());
        int m = Integer.valueOf(CUp.substring(0, CUp.indexOf(".")));
        int valUp = (int) (m / spaceChart);
        params.setMargins(IntToDP(params.leftMargin), IntToDP(params.topMargin), IntToDP(35), IntToDP(valUp + 18));
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        ChartUpLine.setLayoutParams(params);


        //حد پایین
        params = new RelativeLayout.LayoutParams(ChartDownLine.getLayoutParams());
        m = Integer.valueOf(CDown.substring(0, CDown.indexOf(".")));
        int valDown = (int) (m / spaceChart);
        params.setMargins(IntToDP(params.leftMargin), IntToDP(params.topMargin), IntToDP(35), IntToDP(valDown + 18));
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        ChartDownLine.setLayoutParams(params);

        String up = "";
        String down = "";

        //ر اینجا حد بالا گرفته می شود
        boolean a = true;
        int a1 = 0;
        try {
            for (int i = 0; i < CUp.length(); i++) {
                if (CUp.charAt(i) == '.') {
                    a = false;
                }
                if (!a) {
                    a1++;
                }
                up = up + CUp.charAt(i);
                if (a1 > 2) {
                    break;
                }
            }
        } catch (Exception e) {
        }

        //در اینجا حدپایین گرفته می شود
        a = true;
        a1 = 0;
        try {
            for (int i = 0; i < CDown.length(); i++) {
                if (CDown.charAt(i) == '.') {
                    a = false;
                }
                if (!a) {
                    a1++;
                }
                down = down + CDown.charAt(i);
                if (a1 > 2) {
                    break;
                }
            }
        } catch (Exception e) {
        }

        lbl_upper_line.setText(up);
        Low_limit.setText(down);
    }

    @Override
    public void onHideAllChPoints() {
        chPoint1.setVisibility(View.GONE);
        chPoint2.setVisibility(View.GONE);
        chPoint3.setVisibility(View.GONE);
        chPoint4.setVisibility(View.GONE);
        chPoint5.setVisibility(View.GONE);
        chPoint6.setVisibility(View.GONE);
        chPoint7.setVisibility(View.GONE);
        chPoint8.setVisibility(View.GONE);
        chPoint9.setVisibility(View.GONE);
        chPoint10.setVisibility(View.GONE);
        chPoint11.setVisibility(View.GONE);
        chPoint12.setVisibility(View.GONE);
    }

    //در اینجا بالاترین عدد نمودار گرفته می شود
    @Override
    public int onGetTopChartNumber(int number) {

        int res = 0;

        if (number >= 100 && number <= 999) {
            int a = number;
            float b = a / 100;
            res = ((int) b) + 1;
            res *= 100;
        } else if (number < 100) {
            res = 100;
        } else {
            res = 900;
        }

        switch (res) {
            case 100:
                spaceChart = 0.5f;
                break;
            case 200:
                spaceChart = 1f;
                break;
            case 300:
                spaceChart = 1.5f;
                break;
            case 400:
                spaceChart = 2f;
                break;
            case 500:
                spaceChart = 2.5f;
                break;
            case 600:
                spaceChart = 3f;
                break;
            case 700:
                spaceChart = 3.5f;
                break;
            case 800:
                spaceChart = 4f;
                break;
            case 900:
            case 1000:
                spaceChart = 4.5f;
                break;
        }

        return res;
    }

    //در اینجا اعداد سمت راست نمودار ست می شوند
    @Override
    public void onSetChartNumbers(int number) {
        int topNumber = onGetTopChartNumber(number);
        int zarib = topNumber / 10;

        int i = 0;
        chartVals[0] = i;
        chart_lbl0.setText(String.valueOf(i));

        i += zarib;
        chartVals[1] = i;
        chart_lbl1.setText(String.valueOf(i));

        i += zarib;
        chartVals[2] = i;
        chart_lbl2.setText(String.valueOf(i));

        i += zarib;
        chartVals[3] = i;
        chart_lbl3.setText(String.valueOf(i));

        i += zarib;
        chartVals[4] = i;
        chart_lbl4.setText(String.valueOf(i));

        i += zarib;
        chartVals[5] = i;
        chart_lbl5.setText(String.valueOf(i));

        i += zarib;
        chartVals[6] = i;
        chart_lbl6.setText(String.valueOf(i));

        i += zarib;
        chartVals[7] = i;
        chart_lbl7.setText(String.valueOf(i));

        i += zarib;
        chartVals[8] = i;
        chart_lbl8.setText(String.valueOf(i));

        i += zarib;
        chartVals[9] = i;
        chart_lbl9.setText(String.valueOf(i));

        i += zarib;
        chartVals[10] = i;
        chart_lbl10.setText(String.valueOf(i));

        i += zarib;
        chartVals[11] = i;
        chart_lbl11.setText(String.valueOf(i));

        onSetChartVisibility(number);
    }

    //در اینجا عملیات مربوط به نمایش و مخفی کردن اعداد سمت راست انجام می شود
    @Override
    public void onSetChartVisibility(int maxNumber) {

        //در اینجا براساس بالاترین درصد عدد بزرگتر از آن را در سمت راست نمودار می گیرد
        //مثلا بالاترین درصد 300 باشد عدد 320 را ازسمت راست نمودار بر میدارد
        int max = 0;
        for (int i : chartVals) {
            if (i > maxNumber) {
                max = i;
                break;
            }
        }

        //در اینجا تنظیمات اولیه اسلایدر انجام می شود
        setDefaltChart();

        //در اینجا عددهای اضافی چارت حذف می شوند
//        hideNumbersChart(max);
    }

    //در اینجا عددهای سمت راست چارت گرفته می شود
    @Override
    public int[] onGetChartValues() {
        return chartVals;
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
        percent1.setText(p_priceRangeFragment.CalculatePercent(txt_AmountToToman1.getText().toString().replace(",", "").replace("٬", ""), txt_Price.getText().toString().replace(",", "").replace("٬", "")));
        percent2.setText(p_priceRangeFragment.CalculatePercent(txt_AmountToToman2.getText().toString().replace(",", "").replace("٬", ""), txt_Price.getText().toString().replace(",", "").replace("٬", "")));
        percent3.setText(p_priceRangeFragment.CalculatePercent(txt_AmountToToman3.getText().toString().replace(",", "").replace("٬", ""), txt_Price.getText().toString().replace(",", "").replace("٬", "")));
        percent4.setText(p_priceRangeFragment.CalculatePercent(txt_AmountToToman4.getText().toString().replace(",", "").replace("٬", ""), txt_Price.getText().toString().replace(",", "").replace("٬", "")));
        percent5.setText(p_priceRangeFragment.CalculatePercent(txt_AmountToToman5.getText().toString().replace(",", "").replace("٬", ""), txt_Price.getText().toString().replace(",", "").replace("٬", "")));
        percent6.setText(p_priceRangeFragment.CalculatePercent(txt_AmountToToman6.getText().toString().replace(",", "").replace("٬", ""), txt_Price.getText().toString().replace(",", "").replace("٬", "")));
        percent7.setText(p_priceRangeFragment.CalculatePercent(txt_AmountToToman7.getText().toString().replace(",", "").replace("٬", ""), txt_Price.getText().toString().replace(",", "").replace("٬", "")));
        percent8.setText(p_priceRangeFragment.CalculatePercent(txt_AmountToToman8.getText().toString().replace(",", "").replace("٬", ""), txt_Price.getText().toString().replace(",", "").replace("٬", "")));
        percent9.setText(p_priceRangeFragment.CalculatePercent(txt_AmountToToman9.getText().toString().replace(",", "").replace("٬", ""), txt_Price.getText().toString().replace(",", "").replace("٬", "")));
        percent10.setText(p_priceRangeFragment.CalculatePercent(txt_AmountToToman10.getText().toString().replace(",", "").replace("٬", ""), txt_Price.getText().toString().replace(",", "").replace("٬", "")));
        percent11.setText(p_priceRangeFragment.CalculatePercent(txt_AmountToToman11.getText().toString().replace(",", "").replace("٬", ""), txt_Price.getText().toString().replace(",", "").replace("٬", "")));
        percent12.setText(p_priceRangeFragment.CalculatePercent(txt_AmountToToman12.getText().toString().replace(",", "").replace("٬", ""), txt_Price.getText().toString().replace(",", "").replace("٬", "")));
    }

    //در اینجا اعداد اضافی نمودار سمت راست مخفی می شوند
    void hideNumbersChart(int max) {

        for (int i = chartVals.length - 1; i >= 0; i--) {

            if (chartVals[i] > max) {
                hideNumbersChart(i, true);
            } else {
                break;
            }
        }
    }

    void hideNumbersChart(int position, boolean isHide) {

        int show;

        if (isHide) {
            show = View.VISIBLE;
        } else {
            show = View.GONE;
        }

        switch (position) {
            case 11:
                chart_view11.setVisibility(show);
                chart_lbl11.setVisibility(show);
                break;
            case 10:
                chart_view10.setVisibility(show);
                chart_lbl10.setVisibility(show);
                break;
            case 9:
                chart_view9.setVisibility(show);
                chart_lbl9.setVisibility(show);
                break;
            case 8:
                chart_view8.setVisibility(show);
                chart_lbl8.setVisibility(show);
                break;
            case 7:
                chart_view7.setVisibility(show);
                chart_lbl7.setVisibility(show);
                break;
            case 6:
                chart_view6.setVisibility(show);
                chart_lbl6.setVisibility(show);
                break;
            case 5:
                chart_view5.setVisibility(show);
                chart_lbl5.setVisibility(show);
                break;
            case 4:
                chart_view4.setVisibility(show);
                chart_lbl4.setVisibility(show);
                break;
            case 3:
                chart_view3.setVisibility(show);
                chart_lbl3.setVisibility(show);
                break;
            case 2:
                chart_view2.setVisibility(show);
                chart_lbl2.setVisibility(show);
                break;
            case 1:
                chart_view1.setVisibility(show);
                chart_lbl1.setVisibility(show);
                break;
            case 0:
                chart_view0.setVisibility(show);
                chart_lbl0.setVisibility(show);
                break;
        }

        if (isHide) {
            chartLine.getLayoutParams().height -= IntToDP(20);
            chartNumbers.getLayoutParams().height -= IntToDP(20);
            chart.getLayoutParams().height -= IntToDP(20);
        } else {
            chartLine.getLayoutParams().height += IntToDP(20);
            chartNumbers.getLayoutParams().height += IntToDP(20);
            chart.getLayoutParams().height += IntToDP(20);
        }
    }

    //در اینجا تنظیمات اولیه چارت انجام می شود
    @Override
    public void setDefaltChart() {
        chart_view11.setVisibility(View.VISIBLE);
        chart_lbl11.setVisibility(View.VISIBLE);

        chart_view10.setVisibility(View.VISIBLE);
        chart_lbl10.setVisibility(View.VISIBLE);

        chart_view9.setVisibility(View.VISIBLE);
        chart_lbl9.setVisibility(View.VISIBLE);

        chart_view8.setVisibility(View.VISIBLE);
        chart_lbl8.setVisibility(View.VISIBLE);

        chart_view7.setVisibility(View.VISIBLE);
        chart_lbl7.setVisibility(View.VISIBLE);

        chart_view6.setVisibility(View.VISIBLE);
        chart_lbl6.setVisibility(View.VISIBLE);

        chart_view5.setVisibility(View.VISIBLE);
        chart_lbl5.setVisibility(View.VISIBLE);

        chart_view4.setVisibility(View.VISIBLE);
        chart_lbl4.setVisibility(View.VISIBLE);

        chart_view3.setVisibility(View.VISIBLE);
        chart_lbl3.setVisibility(View.VISIBLE);

        chart_view2.setVisibility(View.VISIBLE);
        chart_lbl2.setVisibility(View.VISIBLE);

        chart_view1.setVisibility(View.VISIBLE);
        chart_lbl1.setVisibility(View.VISIBLE);

        chart_view0.setVisibility(View.VISIBLE);
        chart_lbl0.setVisibility(View.VISIBLE);

        chartLine.getLayoutParams().height = IntToDP(220);
        chartNumbers.getLayoutParams().height = IntToDP(240);
        chart.getLayoutParams().height = IntToDP(260);


        //در اینجا دوتا خط نمودار ست می شوند
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ChartUpLine.getLayoutParams());
        params.setMargins(IntToDP(params.leftMargin), IntToDP(params.topMargin), IntToDP(35), IntToDP(0));
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        ChartUpLine.setLayoutParams(params);

        params = new RelativeLayout.LayoutParams(ChartDownLine.getLayoutParams());
        params.setMargins(IntToDP(params.leftMargin), IntToDP(params.topMargin), IntToDP(35), IntToDP(0));
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        ChartDownLine.setLayoutParams(params);
        ///////////////////////////////////////////////////////
    }

    //در اینجا خارج از محدوده ها ست می شود
    @Override
    public void onOutOfRange(String id) {
        String text = lbl_outOfRange.getText().toString();

        if (text.equalsIgnoreCase("---")) {
            text = id + "";
        } else {
            text = text + "," + id;
        }

        lbl_outOfRange.setText(text);

        setOutOfRangePointChart(id);
    }

    int getMaxPercents() {
        int max = 0;

        float[] vals = new float[12];

        if (!percent1.getText().toString().equalsIgnoreCase("")) {
            vals[0] = Float.valueOf(percent1.getText().toString());
        }
        if (!percent2.getText().toString().equalsIgnoreCase("")) {
            vals[1] = Float.valueOf(percent2.getText().toString());
        }
        if (!percent3.getText().toString().equalsIgnoreCase("")) {
            vals[2] = Float.valueOf(percent3.getText().toString());
        }
        if (!percent4.getText().toString().equalsIgnoreCase("")) {
            vals[3] = Float.valueOf(percent4.getText().toString());
        }
        if (!percent5.getText().toString().equalsIgnoreCase("")) {
            vals[4] = Float.valueOf(percent5.getText().toString());
        }
        if (!percent6.getText().toString().equalsIgnoreCase("")) {
            vals[5] = Float.valueOf(percent6.getText().toString());
        }
        if (!percent7.getText().toString().equalsIgnoreCase("")) {
            vals[6] = Float.valueOf(percent7.getText().toString());
        }
        if (!percent8.getText().toString().equalsIgnoreCase("")) {
            vals[7] = Float.valueOf(percent8.getText().toString());
        }
        if (!percent9.getText().toString().equalsIgnoreCase("")) {
            vals[8] = Float.valueOf(percent9.getText().toString());
        }
        if (!percent10.getText().toString().equalsIgnoreCase("")) {
            vals[9] = Float.valueOf(percent10.getText().toString());
        }
        if (!percent11.getText().toString().equalsIgnoreCase("")) {
            vals[10] = Float.valueOf(percent11.getText().toString());
        }
        if (!percent12.getText().toString().equalsIgnoreCase("")) {
            vals[11] = Float.valueOf(percent12.getText().toString());
        }

        try {
            max = Math.round(vals[0]);
        } catch (Exception e) {
        }

        for (float i : vals) {
            try {

                int val = Math.round(i);

                if (val > max)
                    max = val;
            } catch (Exception e) {
            }
        }

        return max;
    }

    //در اینجا نقطه چشمک زن چارت ست می شود
    void enableAnimationPointChart(String position) {
        disableAnimationChart();

        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);

        switch (position) {
            case "1":
                circle_pricerange_chartpoint1.startAnimation(anim);
                break;
            case "2":
                circle_pricerange_chartpoint2.startAnimation(anim);
                break;
            case "3":
                circle_pricerange_chartpoint3.startAnimation(anim);
                break;
            case "4":
                circle_pricerange_chartpoint4.startAnimation(anim);
                break;
            case "5":
                circle_pricerange_chartpoint5.startAnimation(anim);
                break;
            case "6":
                circle_pricerange_chartpoint6.startAnimation(anim);
                break;
            case "7":
                circle_pricerange_chartpoint7.startAnimation(anim);
                break;
            case "8":
                circle_pricerange_chartpoint8.startAnimation(anim);
                break;
            case "9":
                circle_pricerange_chartpoint9.startAnimation(anim);
                break;
            case "10":
                circle_pricerange_chartpoint10.startAnimation(anim);
                break;
            case "11":
                circle_pricerange_chartpoint11.startAnimation(anim);
                break;
            case "12":
                circle_pricerange_chartpoint12.startAnimation(anim);
                break;
        }
    }

    //در اینجا انیمیشن نقطه های چارت غیر فعال می شود
    void disableAnimationChart(){
        circle_pricerange_chartpoint1.setAnimation(null);
        circle_pricerange_chartpoint2.setAnimation(null);
        circle_pricerange_chartpoint3.setAnimation(null);
        circle_pricerange_chartpoint4.setAnimation(null);
        circle_pricerange_chartpoint5.setAnimation(null);
        circle_pricerange_chartpoint6.setAnimation(null);
        circle_pricerange_chartpoint7.setAnimation(null);
        circle_pricerange_chartpoint8.setAnimation(null);
        circle_pricerange_chartpoint9.setAnimation(null);
        circle_pricerange_chartpoint10.setAnimation(null);
        circle_pricerange_chartpoint11.setAnimation(null);
        circle_pricerange_chartpoint12.setAnimation(null);
    }

    void setOutOfRangePointChart(String position){

        switch (position) {
            case "1":
                circle_pricerange_chartpoint1.setBackground(getResources().getDrawable(R.drawable.circle_chart_out));
                break;
            case "2":
                circle_pricerange_chartpoint2.setBackground(getResources().getDrawable(R.drawable.circle_chart_out));
                break;
            case "3":
                circle_pricerange_chartpoint3.setBackground(getResources().getDrawable(R.drawable.circle_chart_out));
                break;
            case "4":
                circle_pricerange_chartpoint4.setBackground(getResources().getDrawable(R.drawable.circle_chart_out));
                break;
            case "5":
                circle_pricerange_chartpoint5.setBackground(getResources().getDrawable(R.drawable.circle_chart_out));
                break;
            case "6":
                circle_pricerange_chartpoint6.setBackground(getResources().getDrawable(R.drawable.circle_chart_out));
                break;
            case "7":
                circle_pricerange_chartpoint7.setBackground(getResources().getDrawable(R.drawable.circle_chart_out));
                break;
            case "8":
                circle_pricerange_chartpoint8.setBackground(getResources().getDrawable(R.drawable.circle_chart_out));
                break;
            case "9":
                circle_pricerange_chartpoint9.setBackground(getResources().getDrawable(R.drawable.circle_chart_out));
                break;
            case "10":
                circle_pricerange_chartpoint10.setBackground(getResources().getDrawable(R.drawable.circle_chart_out));
                break;
            case "11":
                circle_pricerange_chartpoint11.setBackground(getResources().getDrawable(R.drawable.circle_chart_out));
                break;
            case "12":
                circle_pricerange_chartpoint12.setBackground(getResources().getDrawable(R.drawable.circle_chart_out));
                break;
        }

    }

    void setDefultPointChart(){
        circle_pricerange_chartpoint1.setBackground(getResources().getDrawable(R.drawable.circle_chart));
        circle_pricerange_chartpoint2.setBackground(getResources().getDrawable(R.drawable.circle_chart));
        circle_pricerange_chartpoint3.setBackground(getResources().getDrawable(R.drawable.circle_chart));
        circle_pricerange_chartpoint4.setBackground(getResources().getDrawable(R.drawable.circle_chart));
        circle_pricerange_chartpoint5.setBackground(getResources().getDrawable(R.drawable.circle_chart));
        circle_pricerange_chartpoint6.setBackground(getResources().getDrawable(R.drawable.circle_chart));
        circle_pricerange_chartpoint7.setBackground(getResources().getDrawable(R.drawable.circle_chart));
        circle_pricerange_chartpoint8.setBackground(getResources().getDrawable(R.drawable.circle_chart));
        circle_pricerange_chartpoint9.setBackground(getResources().getDrawable(R.drawable.circle_chart));
        circle_pricerange_chartpoint10.setBackground(getResources().getDrawable(R.drawable.circle_chart));
        circle_pricerange_chartpoint11.setBackground(getResources().getDrawable(R.drawable.circle_chart));
        circle_pricerange_chartpoint12.setBackground(getResources().getDrawable(R.drawable.circle_chart));
    }
}
