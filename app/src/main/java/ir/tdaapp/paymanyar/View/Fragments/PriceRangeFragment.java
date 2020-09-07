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

    RelativeLayout chartUpLine, chartDownLine;

    int[] chartVals;

    LinearLayout number0, number10, number20, number30, number40, number50, number60, number70, number80, number90;
    LinearLayout number100, number110, number120, number130, number140, number150, number160, number170, number180, number190;
    LinearLayout number200, number210, number220, number230, number240, number250, number260, number270, number280, number290;
    LinearLayout number300, number310, number320, number330, number340, number350;

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

        number0 = view.findViewById(R.id.number0);
        number10 = view.findViewById(R.id.number10);
        number20 = view.findViewById(R.id.number20);
        number30 = view.findViewById(R.id.number30);
        number40 = view.findViewById(R.id.number40);
        number50 = view.findViewById(R.id.number50);
        number60 = view.findViewById(R.id.number60);
        number70 = view.findViewById(R.id.number70);
        number80 = view.findViewById(R.id.number80);
        number90 = view.findViewById(R.id.number90);
        number100 = view.findViewById(R.id.number100);
        number110 = view.findViewById(R.id.number110);
        number120 = view.findViewById(R.id.number120);
        number130 = view.findViewById(R.id.number130);
        number140 = view.findViewById(R.id.number140);
        number150 = view.findViewById(R.id.number150);
        number160 = view.findViewById(R.id.number160);
        number170 = view.findViewById(R.id.number170);
        number180 = view.findViewById(R.id.number180);
        number190 = view.findViewById(R.id.number190);
        number200 = view.findViewById(R.id.number200);
        number210 = view.findViewById(R.id.number210);
        number220 = view.findViewById(R.id.number220);
        number230 = view.findViewById(R.id.number230);
        number240 = view.findViewById(R.id.number240);
        number250 = view.findViewById(R.id.number250);
        number260 = view.findViewById(R.id.number260);
        number270 = view.findViewById(R.id.number270);
        number280 = view.findViewById(R.id.number280);
        number290 = view.findViewById(R.id.number290);
        number300 = view.findViewById(R.id.number300);
        number310 = view.findViewById(R.id.number310);
        number320 = view.findViewById(R.id.number320);
        number330 = view.findViewById(R.id.number330);
        number340 = view.findViewById(R.id.number340);
        number350 = view.findViewById(R.id.number350);

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

    /**
     * در اینجا محاسبات مربوط به چارت انجام می شود
     **/
    private void Calculate() {

        //چک کنیم که مبلغ مناقصه وارد شده است
        if (Validation.Required(txt_Price, getString(R.string.Please_Input_The_Price))) {

            //ابتدا درصدها مشخص شود
            setPercents();

            int maxPercent = getMaxPercents();

            if (maxPercent <= 350) {

                setDefultPointChart();

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

            } else {
                Toast.makeText(getContext(), getResources().getString(R.string.The_maximum_percentage_should_be_999), Toast.LENGTH_SHORT).show();
            }

        }
    }

    /**
     * در اینجا اعداد سمت راست چارت ست می شود
     **/
    @Override
    public void onSetChartNumbers(int lineDown, int lineUp) {

        if (lineDown <= 350) {

            //در اینجا عدد خط پایین را گرد می کند مثلا عدد 54 به 50 تبدیل می شود
            int roundNumber = onGetRoundNumber(lineDown, true);

            //در اینجا عدد شروع چارت از سمت راست ست می شود
            int startNumber;

            //اگر مقدار متغیر زیر 0 یا 10 یا 20 باشد چارت از صفر شروع می شود اگر بزگتر یا مساوی 30 باشد چارت 20 عدد کمتر از خط پایین چارت شروع می شود
            if (roundNumber == 0 || roundNumber == 10 || roundNumber == 20) {
                startNumber = 0;
            } else if (roundNumber >= 30) {
                startNumber = roundNumber - 20;
            } else {
                startNumber = 0;
            }

            //در اینجا تمامی آیتم های چارت مخفی می شوند
            onHideAllChartItems();

            //در اینجا اعداد سمت راست چارت نمایش داده می شوند
            int count = 1;
            for (int i = startNumber; i <= 350; i += 10) {

                //این شرط برای این است که فقط 12 عدد سمت راست نمایش دهد
                if (count <= 12) {
                    onShowRightChartNumbers(i);
                    count++;
                } else {
                    break;
                }

            }

        }

    }

    /**
     * به طور مثال عدد 46 را می گیرد اگر ورودی بولین ترو باشد عدد 40 را برگشت می دهد در غیر این صورت عدد 50 را برگشت می دهد
     **/
    @Override
    public int onGetRoundNumber(int number, boolean isDown) {

        int res;

        if (!(number >= 0 && number < 10)) {
            try {

                String l = String.valueOf(number);
                String lastNumber;

                lastNumber = l.charAt(l.length() - 1) + "";

                if (!lastNumber.equalsIgnoreCase("0")) {

                    int last = Integer.valueOf(lastNumber);

                    res = number - last;

                    if (!isDown)
                        res += 10;

                } else {
                    res = number;
                }

            } catch (Exception e) {
                res = number;
            }
        } else {
            res = number;
        }
        return res;
    }

    /**
     * در اینجا تمامی آیتم های چارت مخفی می شوند
     **/
    @Override
    public void onHideAllChartItems() {
        number0.setVisibility(View.GONE);
        number10.setVisibility(View.GONE);
        number20.setVisibility(View.GONE);
        number30.setVisibility(View.GONE);
        number40.setVisibility(View.GONE);
        number50.setVisibility(View.GONE);
        number60.setVisibility(View.GONE);
        number70.setVisibility(View.GONE);
        number80.setVisibility(View.GONE);
        number90.setVisibility(View.GONE);
        number100.setVisibility(View.GONE);
        number110.setVisibility(View.GONE);
        number120.setVisibility(View.GONE);
        number130.setVisibility(View.GONE);
        number140.setVisibility(View.GONE);
        number150.setVisibility(View.GONE);
        number160.setVisibility(View.GONE);
        number170.setVisibility(View.GONE);
        number180.setVisibility(View.GONE);
        number190.setVisibility(View.GONE);
        number200.setVisibility(View.GONE);
        number210.setVisibility(View.GONE);
        number220.setVisibility(View.GONE);
        number230.setVisibility(View.GONE);
        number240.setVisibility(View.GONE);
        number250.setVisibility(View.GONE);
        number260.setVisibility(View.GONE);
        number270.setVisibility(View.GONE);
        number280.setVisibility(View.GONE);
        number290.setVisibility(View.GONE);
        number300.setVisibility(View.GONE);
        number310.setVisibility(View.GONE);
        number320.setVisibility(View.GONE);
        number330.setVisibility(View.GONE);
        number340.setVisibility(View.GONE);
        number350.setVisibility(View.GONE);

        chartPoint1.setVisibility(View.GONE);
        chartPoint2.setVisibility(View.GONE);
        chartPoint3.setVisibility(View.GONE);
        chartPoint4.setVisibility(View.GONE);
        chartPoint5.setVisibility(View.GONE);
        chartPoint6.setVisibility(View.GONE);
        chartPoint7.setVisibility(View.GONE);
        chartPoint8.setVisibility(View.GONE);
        chartPoint9.setVisibility(View.GONE);
        chartPoint10.setVisibility(View.GONE);
        chartPoint11.setVisibility(View.GONE);
        chartPoint12.setVisibility(View.GONE);
    }

    /**
     * به طور مثال عدد 60 را از ورودی می گیرد عدد 60 را در سمت راست چارت نمایش می دهد
     **/
    @Override
    public void onShowRightChartNumbers(int number) {
        switch (number) {
            case 0:
                number0.setVisibility(View.VISIBLE);
                break;
            case 10:
                number10.setVisibility(View.VISIBLE);
                break;
            case 20:
                number20.setVisibility(View.VISIBLE);
                break;
            case 30:
                number30.setVisibility(View.VISIBLE);
                break;
            case 40:
                number40.setVisibility(View.VISIBLE);
                break;
            case 50:
                number50.setVisibility(View.VISIBLE);
                break;
            case 60:
                number60.setVisibility(View.VISIBLE);
                break;
            case 70:
                number70.setVisibility(View.VISIBLE);
                break;
            case 80:
                number80.setVisibility(View.VISIBLE);
                break;
            case 90:
                number90.setVisibility(View.VISIBLE);
                break;
            case 100:
                number100.setVisibility(View.VISIBLE);
                break;
            case 110:
                number110.setVisibility(View.VISIBLE);
                break;
            case 120:
                number120.setVisibility(View.VISIBLE);
                break;
            case 130:
                number130.setVisibility(View.VISIBLE);
                break;
            case 140:
                number140.setVisibility(View.VISIBLE);
                break;
            case 150:
                number150.setVisibility(View.VISIBLE);
                break;
            case 160:
                number160.setVisibility(View.VISIBLE);
                break;
            case 170:
                number170.setVisibility(View.VISIBLE);
                break;
            case 180:
                number180.setVisibility(View.VISIBLE);
                break;
            case 190:
                number190.setVisibility(View.VISIBLE);
                break;
            case 200:
                number200.setVisibility(View.VISIBLE);
                break;
            case 210:
                number210.setVisibility(View.VISIBLE);
                break;
            case 220:
                number220.setVisibility(View.VISIBLE);
                break;
            case 230:
                number230.setVisibility(View.VISIBLE);
                break;
            case 240:
                number240.setVisibility(View.VISIBLE);
                break;
            case 250:
                number250.setVisibility(View.VISIBLE);
                break;
            case 260:
                number260.setVisibility(View.VISIBLE);
                break;
            case 270:
                number270.setVisibility(View.VISIBLE);
                break;
            case 280:
                number280.setVisibility(View.VISIBLE);
                break;
            case 290:
                number290.setVisibility(View.VISIBLE);
                break;
            case 300:
                number300.setVisibility(View.VISIBLE);
                break;
            case 310:
                number310.setVisibility(View.VISIBLE);
                break;
            case 320:
                number320.setVisibility(View.VISIBLE);
                break;
            case 330:
                number330.setVisibility(View.VISIBLE);
                break;
            case 340:
                number340.setVisibility(View.VISIBLE);
                break;
            case 350:
                number350.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * در اینجا بالاترین مقدار در ادیت تکست های درصد گرفته می شود
     **/
    @Override
    public int getMaxPercents() {
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
            max = (int) vals[0];
        } catch (Exception e) {
        }

        for (float i : vals) {
            try {

                int val = (int) i;

                if (val > max)
                    max = val;
            } catch (Exception e) {
            }
        }

        return max;
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
     * در اینجا اولین لایه عدد سمت راست چارت برگشت داده می شود
     **/
    @Override
    public LinearLayout getFirstNumberLayoutRightChart() {

        LinearLayout layout = null;

        for (int i = 0; i <= 350; i += 10) {
            layout = getLinearLayoutRightChart(i);

            if (layout != null) {
                if (layout.getVisibility() == View.VISIBLE) break;
            }
        }

        return layout;
    }

    /**
     * در اینجا یک لایوت مربوط به سمت راست چارت می گیرد و عدد آن را پاس می دهد
     * number10 مثلا لایوت روبرو را می گیرد و عدد 10 را برگشت می دهد
     **/
    @Override
    public int getNumberByLayoutChart(LinearLayout layout) {
        int number = 0;

        switch (layout.getId()) {
            case R.id.number0:
                number = 0;
                break;
            case R.id.number10:
                number = 10;
                break;
            case R.id.number20:
                number = 20;
                break;
            case R.id.number30:
                number = 30;
                break;
            case R.id.number40:
                number = 40;
                break;
            case R.id.number50:
                number = 50;
                break;
            case R.id.number60:
                number = 60;
                break;
            case R.id.number70:
                number = 70;
                break;
            case R.id.number80:
                number = 80;
                break;
            case R.id.number90:
                number = 90;
                break;
            case R.id.number100:
                number = 100;
                break;
            case R.id.number110:
                number = 110;
                break;
            case R.id.number120:
                number = 120;
                break;
            case R.id.number130:
                number = 130;
                break;
            case R.id.number140:
                number = 140;
                break;
            case R.id.number150:
                number = 150;
                break;
            case R.id.number160:
                number = 160;
                break;
            case R.id.number170:
                number = 170;
                break;
            case R.id.number180:
                number = 180;
                break;
            case R.id.number190:
                number = 190;
                break;
            case R.id.number200:
                number = 200;
                break;
            case R.id.number210:
                number = 210;
                break;
            case R.id.number220:
                number = 220;
                break;
            case R.id.number230:
                number = 230;
                break;
            case R.id.number240:
                number = 240;
                break;
            case R.id.number250:
                number = 250;
                break;
            case R.id.number260:
                number = 260;
                break;
            case R.id.number270:
                number = 270;
                break;
            case R.id.number280:
                number = 280;
                break;
            case R.id.number290:
                number = 290;
            case R.id.number300:
                number = 300;
            case R.id.number310:
                number = 310;
            case R.id.number320:
                number = 320;
            case R.id.number330:
                number = 330;
            case R.id.number340:
                number = 340;
            case R.id.number350:
                number = 350;
                break;
        }

        return number;
    }

    /**
     * در اینجا مارجین باتوم نقاط چارت به دست می آید
     * percentPoint مربوط به درصد نقطه چارت که مارجین براساس آن به دست می آید
     * heightChartLayout اندازه ارتفاع اعداد سمت راست چارت
     **/
    @Override
    public int getMarginPointChart(int percentPoint, int heightChartLayout) {

        try {

            //در اینجا اولین لایوت سمت راست چارت را می گیرد مثلا اولین لایوت چارت مربوط به عدد 20 می باشد
            LinearLayout firstLayout = getFirstNumberLayoutRightChart();

            //در اینجا ارتفاع نقطه در چارت رند می شود مثلا عدد 57 باشد به 50 رند می شود
            int percentPointHeight = onGetRoundNumber(percentPoint, true);

            //در اینجا مقدار اولین عدد چارت گرفته می شود
            int numberFirstLayout = getNumberByLayoutChart(firstLayout);

            //در اینجا فاصله بین عدد اول در نمودار با عددی که نقطه روی آن قرار دارد به دست می آید
            int spaceBetweenNumbers = getSpaceBetweenChartNumbers(numberFirstLayout, percentPointHeight);

            //در اینجا مارجین اولیه نقطه به دست می آید
            int primitiveMargin = 0;
            if (spaceBetweenNumbers != 0) {
                primitiveMargin = spaceBetweenNumbers * heightChartLayout;
            }

            //در اینجا مقداری که در گرد کردن درصد پاک شده بود در اینجا نگهداری می شود
            int remainingPoint = 0;
            if (percentPoint - percentPointHeight > 0) {
                remainingPoint = percentPoint - percentPointHeight;
            }

            //در اینجا مارجین عددی که در گرد کردن درصد پاک شده است گرفته می شود
            int additionalMargin = 0;
            if (remainingPoint > 0) {
                additionalMargin = (heightChartLayout / 10) * remainingPoint;
            }

            //در اینجا مارجین باتوم برای نقطه به دست می آید
            int finalMargin = (primitiveMargin + additionalMargin) - (heightChartLayout / 2);

            //اگر عدد به دست آمده از پایین ترین عدد سمت راست کمتر باشد عدد -1 را پاس می دهد
            if (percentPoint < numberFirstLayout) {
                finalMargin = -1;
            }

            //اگر عدد به دست آمده بیشتر از بالاترین عدد سمت راست چارت بیشتر باشد عدد -1 را پاس می دهد
            int numberLastLayout = getTopChartNumber();
            if (percentPoint > numberLastLayout) {
                finalMargin = -1;
            }

            return finalMargin;

        } catch (Exception e) {
            return -1;
        }
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
     * در اینجا بالاترین عدد سمت راست نمودار گرفته می شود
     **/
    @Override
    public int getTopChartNumber() {
        int last = getNumberByLayoutChart(getFirstNumberLayoutRightChart());

        int i = 1;

        while (i <= 12) {
            last += 10;
            i++;
        }

        return last;
    }

    /**
     * در اینجا فاصله بین دو عدد در طرف راست نمودار به دست می آید
     **/
    @Override
    public int getSpaceBetweenChartNumbers(int firstNumber, int secondNumber) {
        int space = 0;
        for (int i = firstNumber; i <= secondNumber; i += 10) {
            space++;
        }
        return space;
    }

    /**
     * در اینجا مارجین طرف راست نقاط چارت گرفته می شود
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

        getSpaceBetweenRightChartNumbers((width, height) -> {

            //در اینجا خط بالا نمودار ست می شود
            int marginBottomCUp = getMarginPointChart(CUp, height);
            if (marginBottomCUp != -1) {

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(chartUpLine.getLayoutParams());
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

                chartUpLine.setVisibility(View.VISIBLE);

                float space = onPixelTodp(marginBottomCUp);
                int bottom = IntToDP((int) space);

                params.setMargins(IntToDP(params.leftMargin), IntToDP(params.topMargin), IntToDP(params.rightMargin), bottom);

                chartUpLine.setLayoutParams(params);

                lbl_upper_line.setText(String.valueOf(CUp));
            }

            //ر اینجا خط پایین نمودار ست می شود
            int marginBottomCDown = getMarginPointChart(CDown, height);
            if (marginBottomCDown != -1) {

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(chartDownLine.getLayoutParams());
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

                chartDownLine.setVisibility(View.VISIBLE);

                float space = onPixelTodp(marginBottomCDown);
                int bottom = IntToDP((int) space);

                params.setMargins(IntToDP(params.leftMargin), IntToDP(params.topMargin), IntToDP(params.rightMargin), bottom);

                chartDownLine.setLayoutParams(params);

                Low_limit.setText(String.valueOf(CDown));
            }
        });
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

    /**
     * در اینجا نقطه چشمک زن چارت ست می شود
     **/
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
     * در اینجا انیمیشن نقطه های چارت غیر فعال می شود
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

    /**
     * در اینجا خارج از دامنه های چارت ست می شود
     * **/
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

    @Override
    public void setDefultPointChart() {
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
     * در اینجا نقاط چارت رسم می شوند
     **/
    @Override
    public void setPointChart(VM_PriceRange point) {

        //در اینجا درصد نقطه برای به دست آوردن مارجین باتوم آن گرفته می شود
        int pointPercent = Math.round(Float.valueOf(point.percent));

        getSpaceBetweenRightChartNumbers((width, height) -> {

            //در اینجا مارجین باتوم نقطه گرفته می شود
            int marginBottom = getMarginPointChart(pointPercent, height);

            if (marginBottom != -1) {

                //در اینجا نقطه چارت براساس آیدی آن گرفته می شود
                RelativeLayout pointLayout = getChartPointByNumber(Integer.valueOf(point.id));

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(pointLayout.getLayoutParams());
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

                pointLayout.setVisibility(View.VISIBLE);

                float space = onPixelTodp(marginBottom);
                int bottom = IntToDP((int) space) - IntToDP(6);

                params.setMargins(IntToDP(params.leftMargin), IntToDP(params.topMargin), IntToDP(getMarginRightChartPoint(pointLayout)), bottom);

                pointLayout.setLayoutParams(params);

            }
        });

    }

    /**
     * در اینجا درصد از ورودی می گیرد لایوت سمت راست چارت مربوط به آن را برگشت می دهد
     * به طور مثال عدد 60 از وروی بگیرد لایوت سمت راست که عدد 60 در آن نگهداری می شود را پاس می دهد
     **/
    @Override
    public LinearLayout getLinearLayoutRightChart(int percent) {
        LinearLayout layout = null;
        switch (percent) {
            case 0:
                layout = number0;
                break;
            case 10:
                layout = number10;
                break;
            case 20:
                layout = number20;
                break;
            case 30:
                layout = number30;
                break;
            case 40:
                layout = number40;
                break;
            case 50:
                layout = number50;
                break;
            case 60:
                layout = number60;
                break;
            case 70:
                layout = number70;
                break;
            case 80:
                layout = number80;
                break;
            case 90:
                layout = number90;
                break;
            case 100:
                layout = number100;
                break;
            case 110:
                layout = number110;
                break;
            case 120:
                layout = number120;
                break;
            case 130:
                layout = number130;
                break;
            case 140:
                layout = number140;
                break;
            case 150:
                layout = number150;
                break;
            case 160:
                layout = number160;
                break;
            case 170:
                layout = number170;
                break;
            case 180:
                layout = number180;
                break;
            case 190:
                layout = number190;
                break;
            case 200:
                layout = number200;
                break;
            case 210:
                layout = number210;
                break;
            case 220:
                layout = number220;
                break;
            case 230:
                layout = number230;
                break;
            case 240:
                layout = number240;
                break;
            case 250:
                layout = number250;
                break;
            case 260:
                layout = number260;
                break;
            case 270:
                layout = number270;
                break;
            case 280:
                layout = number280;
                break;
            case 290:
                layout = number290;
                break;
            case 300:
                layout = number300;
                break;
            case 310:
                layout = number310;
                break;
            case 320:
                layout = number320;
                break;
            case 330:
                layout = number330;
                break;
            case 340:
                layout = number340;
                break;
            case 350:
                layout = number350;
                break;
        }

        return layout;
    }

    /**
     * در اینجا فاصله بین لایوتهای سمت راست نمودار به دست می آید
     * مثلا فاصله بین عدد 60 تا 70 را در نمودار برگشت می دهد
     **/
    @Override
    public void getSpaceBetweenRightChartNumbers(layoutSize size) {
        LinearLayout layout = getFirstNumberLayoutRightChart();

        Views.getLinearLayoutHeight(layout, size);
    }

    @Override
    public Float onPixelTodp(float pixel) {
        float density = getResources().getDisplayMetrics().density;
        float dp = pixel / density;
        return dp;
    }
}
