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
import ir.tdaapp.li_utility.Codes.ShowPrice;
import ir.tdaapp.li_utility.Codes.Validation;
import ir.tdaapp.paymanyar.Model.Services.S_PriceRangeFragment;
import ir.tdaapp.paymanyar.Model.Services.layoutSize;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.PercentPriceRange;
import ir.tdaapp.paymanyar.Model.Utilitys.Views;
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
    CardView btnCalc, btn_Home;
    Toolbar toolbar;
    EditText txt_AmountToToman9, txt_AmountToToman10, txt_AmountToToman11, txt_AmountToToman12, percent9, percent10, percent11, percent12;
    TextView Low_limit, lbl_upper_line, lbl_outOfRange;

    TextView lbl_Number1, lbl_Number2, lbl_Number3, lbl_Number4, lbl_Number5, lbl_Number6, lbl_Number7, lbl_Number8, lbl_Number9, lbl_Number10;

    RelativeLayout chartUpLine, chartDownLine;

    LinearLayout number1, number2, number3, number4, number5, number6, number7, number8, number9, number10;

    RelativeLayout chartPoint1, chartPoint2, chartPoint3, chartPoint4, chartPoint5, chartPoint6;
    RelativeLayout chartPoint7, chartPoint8, chartPoint9, chartPoint10, chartPoint11, chartPoint12;

    RelativeLayout circle_chartPoint1, circle_chartPoint2, circle_chartPoint3, circle_chartPoint4, circle_chartPoint5, circle_chartPoint6;
    RelativeLayout circle_chartPoint7, circle_chartPoint8, circle_chartPoint9, circle_chartPoint10, circle_chartPoint11, circle_chartPoint12;

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

        number1 = view.findViewById(R.id.number1);
        number2 = view.findViewById(R.id.number2);
        number3 = view.findViewById(R.id.number3);
        number4 = view.findViewById(R.id.number4);
        number5 = view.findViewById(R.id.number5);
        number6 = view.findViewById(R.id.number6);
        number7 = view.findViewById(R.id.number7);
        number8 = view.findViewById(R.id.number8);
        number9 = view.findViewById(R.id.number9);
        number10 = view.findViewById(R.id.number10);

        lbl_Number1 = view.findViewById(R.id.lbl_Number1);
        lbl_Number2 = view.findViewById(R.id.lbl_Number2);
        lbl_Number3 = view.findViewById(R.id.lbl_Number3);
        lbl_Number4 = view.findViewById(R.id.lbl_Number4);
        lbl_Number5 = view.findViewById(R.id.lbl_Number5);
        lbl_Number6 = view.findViewById(R.id.lbl_Number6);
        lbl_Number7 = view.findViewById(R.id.lbl_Number7);
        lbl_Number8 = view.findViewById(R.id.lbl_Number8);
        lbl_Number9 = view.findViewById(R.id.lbl_Number9);
        lbl_Number10 = view.findViewById(R.id.lbl_Number10);

        chartPoint1 = view.findViewById(R.id.chartPoint1);
        chartPoint2 = view.findViewById(R.id.chartPoint2);
        chartPoint3 = view.findViewById(R.id.chartPoint3);
        chartPoint4 = view.findViewById(R.id.chartPoint4);
        chartPoint5 = view.findViewById(R.id.chartPoint5);
        chartPoint6 = view.findViewById(R.id.chartPoint6);
        chartPoint7 = view.findViewById(R.id.chartPoint7);
        chartPoint8 = view.findViewById(R.id.chartPoint8);
        chartPoint9 = view.findViewById(R.id.chartPoint9);
        chartPoint10 = view.findViewById(R.id.chartPoint10);
        chartPoint11 = view.findViewById(R.id.chartPoint11);
        chartPoint12 = view.findViewById(R.id.chartPoint12);

        chartUpLine = view.findViewById(R.id.chartUpLine);
        chartDownLine = view.findViewById(R.id.chartDownLine);

        winnerNumber = view.findViewById(R.id.pricerange_winnerNumber);
        winnerPrice = view.findViewById(R.id.pricerange_winnerprice);

        txt_Percentage = view.findViewById(R.id.txt_percentage);
        btn_Home = view.findViewById(R.id.btn_Home);
        lbl_upper_line = view.findViewById(R.id.lbl_upper_line);
        Low_limit = view.findViewById(R.id.Low_limit);
        lbl_outOfRange = view.findViewById(R.id.lbl_outOfRange);

        circle_chartPoint1 = view.findViewById(R.id.circle_chartPoint1);
        circle_chartPoint2 = view.findViewById(R.id.circle_chartPoint2);
        circle_chartPoint3 = view.findViewById(R.id.circle_chartPoint3);
        circle_chartPoint4 = view.findViewById(R.id.circle_chartPoint4);
        circle_chartPoint5 = view.findViewById(R.id.circle_chartPoint5);
        circle_chartPoint6 = view.findViewById(R.id.circle_chartPoint6);
        circle_chartPoint7 = view.findViewById(R.id.circle_chartPoint7);
        circle_chartPoint8 = view.findViewById(R.id.circle_chartPoint8);
        circle_chartPoint9 = view.findViewById(R.id.circle_chartPoint9);
        circle_chartPoint10 = view.findViewById(R.id.circle_chartPoint10);
        circle_chartPoint11 = view.findViewById(R.id.circle_chartPoint11);
        circle_chartPoint12 = view.findViewById(R.id.circle_chartPoint12);

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

    /**
     * در اینجا محاسبات مربوط به چارت انجام می شود
     **/
    private void Calculate() {

        //چک کنیم که مبلغ مناقصه وارد شده است
        if (Validation.Required(txt_Price, getString(R.string.Please_Input_The_Price))) {

            //ابتدا درصدها مشخص شود
            setPercents();

            //ساخت یک آرایه از مقادیر پیشنهادی
            ArrayList<VM_PriceRange> arrayList = new ArrayList<>();
            if (txt_AmountToToman1.getText().toString().length() > 0) {
                arrayList.add(new VM_PriceRange("1", txt_AmountToToman1.getText().toString().replace(",", "").replace("٬", ""), percent1.getText().toString()));
            }
            if (txt_AmountToToman2.getText().toString().length() > 0) {
                arrayList.add(new VM_PriceRange("2", txt_AmountToToman2.getText().toString().replace(",", "").replace("٬", ""), percent2.getText().toString()));
            }
            if (txt_AmountToToman3.getText().toString().length() > 0) {
                arrayList.add(new VM_PriceRange("3", txt_AmountToToman3.getText().toString().replace(",", "").replace("٬", ""), percent3.getText().toString()));
            }
            if (txt_AmountToToman4.getText().toString().length() > 0) {
                arrayList.add(new VM_PriceRange("4", txt_AmountToToman4.getText().toString().replace(",", "").replace("٬", ""), percent4.getText().toString()));
            }
            if (txt_AmountToToman5.getText().toString().length() > 0) {
                arrayList.add(new VM_PriceRange("5", txt_AmountToToman5.getText().toString().replace(",", "").replace("٬", ""), percent5.getText().toString()));
            }
            if (txt_AmountToToman6.getText().toString().length() > 0) {
                arrayList.add(new VM_PriceRange("6", txt_AmountToToman6.getText().toString().replace(",", "").replace("٬", ""), percent6.getText().toString()));
            }
            if (txt_AmountToToman7.getText().toString().length() > 0) {
                arrayList.add(new VM_PriceRange("7", txt_AmountToToman7.getText().toString().replace(",", "").replace("٬", ""), percent7.getText().toString()));
            }
            if (txt_AmountToToman8.getText().toString().length() > 0) {
                arrayList.add(new VM_PriceRange("8", txt_AmountToToman8.getText().toString().replace(",", "").replace("٬", ""), percent8.getText().toString()));
            }
            if (txt_AmountToToman9.getText().toString().length() > 0) {
                arrayList.add(new VM_PriceRange("9", txt_AmountToToman9.getText().toString().replace(",", "").replace("٬", ""), percent9.getText().toString()));
            }
            if (txt_AmountToToman10.getText().toString().length() > 0) {
                arrayList.add(new VM_PriceRange("10", txt_AmountToToman10.getText().toString().replace(",", "").replace("٬", ""), percent10.getText().toString()));
            }
            if (txt_AmountToToman11.getText().toString().length() > 0) {
                arrayList.add(new VM_PriceRange("11", txt_AmountToToman11.getText().toString().replace(",", "").replace("٬", ""), percent11.getText().toString()));
            }
            if (txt_AmountToToman12.getText().toString().length() > 0) {
                arrayList.add(new VM_PriceRange("12", txt_AmountToToman12.getText().toString().replace(",", "").replace("٬", ""), percent12.getText().toString()));
            }

            //شروع محاسبه
            p_priceRangeFragment.StartCalculate(txt_Price.getText().toString().replace(",", "").replace("٬", ""), arrayList, txt_guarantee.getText().toString().replace(",", "").replace("٬", ""), cmb_Degree_of_Importance.getSelectedItemPosition() + 1);

        }
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

    @Override
    public int IntToDP(int value) {

        //تبدیل عدد صحیح به dp
        int marginInDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, value, getResources()
                        .getDisplayMetrics());
        return marginInDp;
    }

    /**
     * نمایش خط بالا و پایین نمودار
     * نمایش برنده
     * حدبالا و پایین گرفته می شود
     * number مقدار پول شخص برنده
     * position شماره نفر برنده
     * CUp مقدار خط بالا
     * CDown مقدارخط پایین
     **/
    @Override
    public void winnerSelection(String number, String position, int CUp, int CDown) {
        winnerPrice.setText(number);
        winnerNumber.setText(position);

        Views.getLinearLayoutHeight(number1, (width, height) -> {
            //در اینجا خط بالا نمودار ست می شود
            int marginBottomCUp = getMarginPointChart(CUp, height);
            if (marginBottomCUp != -1) {
                chartUpLine.setVisibility(View.VISIBLE);

                setMarginLineChart(chartUpLine, marginBottomCUp);

                lbl_upper_line.setText(String.valueOf(CUp));
            }

            //ر اینجا خط پایین نمودار ست می شود
            int marginBottomCDown = getMarginPointChart(CDown, height);

            if (marginBottomCDown != -1) {
                chartDownLine.setVisibility(View.VISIBLE);

                setMarginLineChart(chartDownLine, marginBottomCDown);
                Low_limit.setText(String.valueOf(CDown));
            }
        });
    }

    /**
     * در اینجا نمودار به حالت اولیه برمی گردد
     **/
    @Override
    public void setDefaultChartNumber() {

        //در اینجا اعداد طرف راست دامنه قیمت به حالت اولیه بر می گردد
        lbl_Number1.setText("1");
        lbl_Number2.setText("2");
        lbl_Number3.setText("3");
        lbl_Number4.setText("4");
        lbl_Number5.setText("5");
        lbl_Number6.setText("6");
        lbl_Number7.setText("7");
        lbl_Number8.setText("8");
        lbl_Number9.setText("9");
        lbl_Number10.setText("10");

        //در اینجا مارجین نقاط دامنه قیمت به حالت اولیه بر می گردد
        setMarginPointChart(chartPoint1, 11);
        setMarginPointChart(chartPoint2, 11);
        setMarginPointChart(chartPoint3, 11);
        setMarginPointChart(chartPoint4, 11);
        setMarginPointChart(chartPoint5, 11);
        setMarginPointChart(chartPoint6, 11);
        setMarginPointChart(chartPoint7, 11);
        setMarginPointChart(chartPoint8, 11);
        setMarginPointChart(chartPoint9, 11);
        setMarginPointChart(chartPoint10, 11);
        setMarginPointChart(chartPoint11, 11);
        setMarginPointChart(chartPoint12, 11);

        //در اینجا خط های دامنه قیمت به حالت اولیه برمی گردد
        setMarginLineChart(chartUpLine, 18);
        setMarginLineChart(chartDownLine, 18);

        //در اینجا نقاط چارت مخفی می شوند
        chartPoint1.setVisibility(View.INVISIBLE);
        chartPoint2.setVisibility(View.INVISIBLE);
        chartPoint3.setVisibility(View.INVISIBLE);
        chartPoint4.setVisibility(View.INVISIBLE);
        chartPoint5.setVisibility(View.INVISIBLE);
        chartPoint6.setVisibility(View.INVISIBLE);
        chartPoint7.setVisibility(View.INVISIBLE);
        chartPoint8.setVisibility(View.INVISIBLE);
        chartPoint9.setVisibility(View.INVISIBLE);
        chartPoint10.setVisibility(View.INVISIBLE);
        chartPoint11.setVisibility(View.INVISIBLE);
        chartPoint12.setVisibility(View.INVISIBLE);

        circle_chartPoint1.setBackground(getResources().getDrawable(R.drawable.circle_chart));
        circle_chartPoint2.setBackground(getResources().getDrawable(R.drawable.circle_chart));
        circle_chartPoint3.setBackground(getResources().getDrawable(R.drawable.circle_chart));
        circle_chartPoint4.setBackground(getResources().getDrawable(R.drawable.circle_chart));
        circle_chartPoint5.setBackground(getResources().getDrawable(R.drawable.circle_chart));
        circle_chartPoint6.setBackground(getResources().getDrawable(R.drawable.circle_chart));
        circle_chartPoint7.setBackground(getResources().getDrawable(R.drawable.circle_chart));
        circle_chartPoint8.setBackground(getResources().getDrawable(R.drawable.circle_chart));
        circle_chartPoint9.setBackground(getResources().getDrawable(R.drawable.circle_chart));
        circle_chartPoint10.setBackground(getResources().getDrawable(R.drawable.circle_chart));
        circle_chartPoint11.setBackground(getResources().getDrawable(R.drawable.circle_chart));
        circle_chartPoint12.setBackground(getResources().getDrawable(R.drawable.circle_chart));
    }

    /**
     * در اینجا مارجین نقاط دامنه قیمت ست می شود
     **/
    @Override
    public void setMarginPointChart(RelativeLayout point, int marginBottom) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(point.getLayoutParams());
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.setMargins(params.leftMargin, params.topMargin, IntToDP(getMarginRightChartPoint(point)), IntToDP(marginBottom));
        point.setLayoutParams(params);
    }

    /**
     * در اینجا مارجین راست نقاط نمودار گرفته می شود
     **/
    @Override
    public int getMarginRightChartPoint(RelativeLayout layout) {
        int margin = 0;

        switch (layout.getId()) {
            case R.id.chartPoint1:
                margin = 10;
                break;
            case R.id.chartPoint2:
                margin = 30;
                break;
            case R.id.chartPoint3:
                margin = 50;
                break;
            case R.id.chartPoint4:
                margin = 70;
                break;
            case R.id.chartPoint5:
                margin = 90;
                break;
            case R.id.chartPoint6:
                margin = 110;
                break;
            case R.id.chartPoint7:
                margin = 130;
                break;
            case R.id.chartPoint8:
                margin = 150;
                break;
            case R.id.chartPoint9:
                margin = 170;
                break;
            case R.id.chartPoint10:
                margin = 190;
                break;
            case R.id.chartPoint11:
                margin = 215;
                break;
            case R.id.chartPoint12:
                margin = 240;
                break;
        }

        return margin;
    }

    /**
     * در اینجا مارجین خط دامنه قیمت ست می شود
     **/
    @Override
    public void setMarginLineChart(RelativeLayout layout, int marginBottom) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(layout.getLayoutParams());
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.setMargins(params.leftMargin, params.topMargin, params.rightMargin, IntToDP(marginBottom));
        layout.setLayoutParams(params);
    }

    /**
     * در اینجا نقاط دامنه قیمت که از محدوده خارج شده اند تغییر رنگ پیدا می کنند
     **/
    @Override
    public void setOutOfRangePointChart(String position) {
        String text = lbl_outOfRange.getText().toString();

        if (text.equalsIgnoreCase("---")) {
            text = position + "";
        } else {
            text = text + "," + position;
        }

        lbl_outOfRange.setText(text);

        switch (position) {
            case "1":
                circle_chartPoint1.setBackground(getResources().getDrawable(R.drawable.circle_chart_out));
                break;
            case "2":
                circle_chartPoint2.setBackground(getResources().getDrawable(R.drawable.circle_chart_out));
                break;
            case "3":
                circle_chartPoint3.setBackground(getResources().getDrawable(R.drawable.circle_chart_out));
                break;
            case "4":
                circle_chartPoint4.setBackground(getResources().getDrawable(R.drawable.circle_chart_out));
                break;
            case "5":
                circle_chartPoint5.setBackground(getResources().getDrawable(R.drawable.circle_chart_out));
                break;
            case "6":
                circle_chartPoint6.setBackground(getResources().getDrawable(R.drawable.circle_chart_out));
                break;
            case "7":
                circle_chartPoint7.setBackground(getResources().getDrawable(R.drawable.circle_chart_out));
                break;
            case "8":
                circle_chartPoint8.setBackground(getResources().getDrawable(R.drawable.circle_chart_out));
                break;
            case "9":
                circle_chartPoint9.setBackground(getResources().getDrawable(R.drawable.circle_chart_out));
                break;
            case "10":
                circle_chartPoint10.setBackground(getResources().getDrawable(R.drawable.circle_chart_out));
                break;
            case "11":
                circle_chartPoint11.setBackground(getResources().getDrawable(R.drawable.circle_chart_out));
                break;
            case "12":
                circle_chartPoint12.setBackground(getResources().getDrawable(R.drawable.circle_chart_out));
                break;
        }

    }

    /**
     * در اینجا اعداد سمت راست نمودار ست می شود
     **/
    @Override
    public void onSetChartNumbers(int lineDown) {
        //در اینجا مقدار خط پایین نمودار گرد می شود
        int roundNumber = roundNumber(lineDown);

        //این کد برای جلوگیری از ست شدن اعداد منفی در سمت راست نمودار می باشد
        if (roundNumber < 20) {
            roundNumber = 20;
        }

        //این متغیر برای ست کردن عدد سمت راست نمودار می باشد که از عدد پایین شروع می کند و به بالا می رود
        int firstNumberChart = roundNumber - 20;

        for (int i = 1; i <= 10; i++) {
            switch (i) {
                case 1:
                    lbl_Number1.setText(String.valueOf(firstNumberChart));
                    break;
                case 2:
                    lbl_Number2.setText(String.valueOf(firstNumberChart));
                    break;
                case 3:
                    lbl_Number3.setText(String.valueOf(firstNumberChart));
                    break;
                case 4:
                    lbl_Number4.setText(String.valueOf(firstNumberChart));
                    break;
                case 5:
                    lbl_Number5.setText(String.valueOf(firstNumberChart));
                    break;
                case 6:
                    lbl_Number6.setText(String.valueOf(firstNumberChart));
                    break;
                case 7:
                    lbl_Number7.setText(String.valueOf(firstNumberChart));
                    break;
                case 8:
                    lbl_Number8.setText(String.valueOf(firstNumberChart));
                    break;
                case 9:
                    lbl_Number9.setText(String.valueOf(firstNumberChart));
                    break;
                case 10:
                    lbl_Number10.setText(String.valueOf(firstNumberChart));
                    break;
            }
            firstNumberChart += 10;
        }
    }

    /**
     * در اینجا اگر عدد 642 از ورودی بگیرد به عدد 640 گرد می شود
     **/
    @Override
    public int roundNumber(int number) {
        int roundNumber = 0;

        if (number >= 0 && number < 100) {
            int tempDiv = number / 10;
            roundNumber = tempDiv * 10;
        } else if (number >= 100 && number < 1000) {
            int tempMul = number * 10;
            int tempDiv = tempMul / 100;
            roundNumber = tempDiv * 10;
        }

        return roundNumber;
    }

    /**
     * در اینجا مارجین باتوم نقاط نمودار به دست می آید و این نقاط در جای خود قرار می گیرند
     **/
    @Override
    public void setPointChart(VM_PriceRange point) {

        //در اینجا درصد نقطه برای به دست آوردن مارجین باتوم آن گرفته می شود
        int pointPercent = Math.round(Float.valueOf(point.percent));

        Views.getLinearLayoutHeight(number1, (width, height) -> {
            int marginBottom = getMarginPointChart(pointPercent, height);

            if (marginBottom != -1) {

                //در اینجا نقطه چارت براساس آیدی آن گرفته می شود
                RelativeLayout pointLayout = getChartPointByNumber(Integer.valueOf(point.id));

                pointLayout.setVisibility(View.VISIBLE);

                setMarginPointChart(pointLayout, marginBottom - 5);
            }
        });
    }

    /**
     * در اینجا مارجین باتوم نقاط نمودار به دست می آید
     * percentPoint مربوط به درصد نقطه چارت که مارجین براساس آن به دست می آید
     * heightChartLayout اندازه ارتفاع اعداد سمت راست چارت
     **/
    @Override
    public int getMarginPointChart(int percentPoint, int heightChartLayout) {

        try {

            if (Integer.valueOf(lbl_Number1.getText().toString()) <= percentPoint && Integer.valueOf(lbl_Number10.getText().toString()) >= percentPoint) {

                //در اینجا درصد نقطه گرد می شود مثلا عدد 62 به 60 گرد می شود
                int roundPercentPoint;
                if (percentPoint > 10) {
                    roundPercentPoint = roundNumber(percentPoint);
                } else {
                    roundPercentPoint = percentPoint;
                }

                //در اینجا فاصله میان عدد اول نمودار و عددی که قرار است نقطه روی آن قرار گیرد به دست می آید
                int spaceNumberChart = (roundPercentPoint - Integer.valueOf(lbl_Number1.getText().toString())) / 10;

                //در اینجا مارجین اولیه ب دست می آید
                int marginRound;
                if (spaceNumberChart > 0) {
                    marginRound = (spaceNumberChart * onPixelTodp(heightChartLayout).intValue()) + (onPixelTodp(heightChartLayout).intValue() / 2);
                } else {
                    marginRound = (onPixelTodp(heightChartLayout).intValue() / 10) * roundPercentPoint + (onPixelTodp(heightChartLayout).intValue() / 2);
                }

                //در اینجا مقداری که در گرد کردن درصد پاک شده بود در اینجا نگهداری می شود
                int remainingPoint = 0;
                if (percentPoint - roundPercentPoint > 0) {
                    remainingPoint = percentPoint - roundPercentPoint;
                }

                //در اینجا مارجین عددی که در گرد کردن درصد پاک شده است گرفته می شود
                int additionalMargin = 0;
                if (remainingPoint > 0) {
                    additionalMargin = (onPixelTodp(heightChartLayout).intValue() / 10) * remainingPoint;
                }

                marginRound += additionalMargin;

                return marginRound;
            } else {
                return -1;
            }

        } catch (Exception e) {
            return -1;
        }
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

    @Override
    public Float onPixelTodp(float pixel) {
        float density = getResources().getDisplayMetrics().density;
        float dp = pixel / density;
        return dp;
    }

    /**
     * در اینجا نقاط چارت را براساس عدد آن پاس می دهد
     **/
    @Override
    public RelativeLayout getChartPointByNumber(int number) {
        RelativeLayout layout = null;
        switch (number) {
            case 1:
                layout = chartPoint1;
                break;
            case 2:
                layout = chartPoint2;
                break;
            case 3:
                layout = chartPoint3;
                break;
            case 4:
                layout = chartPoint4;
                break;
            case 5:
                layout = chartPoint5;
                break;
            case 6:
                layout = chartPoint6;
                break;
            case 7:
                layout = chartPoint7;
                break;
            case 8:
                layout = chartPoint8;
                break;
            case 9:
                layout = chartPoint9;
                break;
            case 10:
                layout = chartPoint10;
                break;
            case 11:
                layout = chartPoint11;
                break;
            case 12:
                layout = chartPoint12;
                break;
        }
        return layout;
    }

    /**
     * در اینجا نقطه چشمک زن برنده مناقصه در نمودار ست می شود
     * **/
    @Override
    public void enableAnimationPointChart(String position) {
        disableAnimationChart();

        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);

        switch (position) {
            case "1":
                circle_chartPoint1.startAnimation(anim);
                break;
            case "2":
                circle_chartPoint2.startAnimation(anim);
                break;
            case "3":
                circle_chartPoint3.startAnimation(anim);
                break;
            case "4":
                circle_chartPoint4.startAnimation(anim);
                break;
            case "5":
                circle_chartPoint5.startAnimation(anim);
                break;
            case "6":
                circle_chartPoint6.startAnimation(anim);
                break;
            case "7":
                circle_chartPoint7.startAnimation(anim);
                break;
            case "8":
                circle_chartPoint8.startAnimation(anim);
                break;
            case "9":
                circle_chartPoint9.startAnimation(anim);
                break;
            case "10":
                circle_chartPoint10.startAnimation(anim);
                break;
            case "11":
                circle_chartPoint11.startAnimation(anim);
                break;
            case "12":
                circle_chartPoint12.startAnimation(anim);
                break;
        }
    }

    /**
     * در اینجا نقطه چشمک زن نمودار غیرفعال می شود
     * **/
    @Override
    public void disableAnimationChart() {
        circle_chartPoint1.setAnimation(null);
        circle_chartPoint2.setAnimation(null);
        circle_chartPoint3.setAnimation(null);
        circle_chartPoint4.setAnimation(null);
        circle_chartPoint5.setAnimation(null);
        circle_chartPoint6.setAnimation(null);
        circle_chartPoint7.setAnimation(null);
        circle_chartPoint8.setAnimation(null);
        circle_chartPoint9.setAnimation(null);
        circle_chartPoint10.setAnimation(null);
        circle_chartPoint11.setAnimation(null);
        circle_chartPoint12.setAnimation(null);
    }
}
