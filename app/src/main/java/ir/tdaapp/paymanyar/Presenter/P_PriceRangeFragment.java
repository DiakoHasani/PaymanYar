package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ir.tdaapp.li_utility.Codes.Replace;
import ir.tdaapp.paymanyar.Model.Services.S_PriceRangeFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DegreeOfImportance;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PriceRange;
import ir.tdaapp.paymanyar.R;

public class P_PriceRangeFragment {

    private Context context;
    private S_PriceRangeFragment s_priceRangeFragment;

    public P_PriceRangeFragment(Context context, S_PriceRangeFragment s_priceRangeFragment) {
        this.context = context;
        this.s_priceRangeFragment = s_priceRangeFragment;
    }

    public void start() {
        s_priceRangeFragment.OnStart();
        setDegreeOfImportance();
    }

    void setDegreeOfImportance() {

        List<VM_DegreeOfImportance> vals = new ArrayList<>();

        vals.add(new VM_DegreeOfImportance(1, "کم"));
        vals.add(new VM_DegreeOfImportance(2, "متوسط"));
        vals.add(new VM_DegreeOfImportance(3, "زیاد"));

        ArrayAdapter<VM_DegreeOfImportance> adapter = new ArrayAdapter<>(context, R.layout.spinner_item3, vals);
        s_priceRangeFragment.onGetDegreeOfImportance(adapter);
    }

    //Display as : T
    private double GetPriority(int participant_count, long priority) {

        /*
         *   این متد برای محاسبه ضریب اهمیت مقایسه یا همان t است
         */

        double t = 0.0;

        if (participant_count >= 3 && participant_count <= 6) {
            if (priority == 1) t = 1.1;
            if (priority == 2) t = 1.0;
            if (priority == 3) t = 0.9;
        } else if (participant_count >= 7 && participant_count <= 10) {
            if (priority == 1) t = 1.3;
            if (priority == 2) t = 1.2;
            if (priority == 3) t = 1.1;
        } else if (participant_count > 10) {
            if (priority == 1) t = 1.5;
            if (priority == 2) t = 1.4;
            if (priority == 3) t = 1.3;
        }

        return t;
    }

    public void StartCalculate(String price, ArrayList<VM_PriceRange> participates, String Guarantee, long priority) {

        try {
            //مبلغ مناقصه
            long A = (Long.valueOf(Replace.Number_fn_To_en(price)));

            //میزان اهمیت
            double T = GetPriority(participates.size() - 1, priority);

            //مبلغ تضمین
            double D = A * 5 / 100; // Default
            if (Guarantee.length() > 0) {
                // If User Entered Guarantee Value
                D = Long.valueOf(Guarantee);
            }

            //تعداد پیمانکاران
            int N = participates.size();

            //میانگین درصدهای پیشنهادی
            double M = GetAverage(participates, true);

            //انحراف معیار اولیه
            double S = devitation(M, participates, 0);

            double B = 1;
            if (M > 115) {
                B = 1.1 * M;
            } else if (M <= 115) {
                B = 1.25 * M;
            }

            boolean hasRequestDeleted = false;
            //حذف درخواست های نامتعارف
            for (int i = 0; i < participates.size(); i++) {
                if (Double.valueOf(Replace.Number_fn_To_en(participates.get(i).percent)) > B) {
                    participates.get(i).isDeleted = true;
                    hasRequestDeleted = true;
                }
            }

            if (hasRequestDeleted) {
                //میانگین درصدهای پیشنهادی بعد از حذف درخواست های نامتعارف
                M = GetAverage(participates, false);

                //انحراف معیار درخواست های حذف نشده
                S = devitation(M, participates, M);
            }

            //نصاب معاملات سالانه
            long L = 450000000;

            long K = 1000 * L;

            //بدست آوردن حد پایین
            double C1 = 0;
            if (A > K || N <= 6) {
                C1 = Math.abs(0.97 * (M - (T * S)));
            } else {
                C1 = Math.abs(M - (T * S) - (0.5 * D / A * 100));
            }

            //بدست آوردن حد بالا
            double C2 = Math.abs(M + (T * S));

            s_priceRangeFragment.setDefultPointChart();
            for (VM_PriceRange i : participates) {
                float v = Float.valueOf(i.percent);

                if (v < C1 || v > C2) {
                    s_priceRangeFragment.setOutOfRangePointChart(i.id);
                }
            }

            s_priceRangeFragment.onSetChartNumbers((int) C1, (int) C2);

            for (int i = 0; i < participates.size(); i++) {
                s_priceRangeFragment.setPointChart(participates.get(i));
            }

            ChooseWinner(C1, participates, C2);

        } catch (Exception e) {
        }
    }

    private double GetAverage(ArrayList<VM_PriceRange> arr, boolean withDeleted) {
        double average = 100.0f;

        if (withDeleted) {

            //جمع کردن کل درصد درخواست ها

            try {
                //جمع کردن درصدها
                for (int i = 0; i < arr.size(); i++) {
                    average += Double.valueOf(Replace.Number_fn_To_en(arr.get(i).percent));
                }

                //تقسیم بر تعداد نفرات
                if (arr.size() > 0) average = average / (arr.size() + 1);
            } catch (Exception e) {
            }
        } else {

            //جمع کردن درصد درخواست های حذف نشده

            try {
                int numbers = 1;
                //جمع کردن درصدها
                for (int i = 0; i < arr.size(); i++) {
                    if (!arr.get(i).isDeleted) {
                        average += Double.valueOf(Replace.Number_fn_To_en(arr.get(i).percent));
                        numbers++;
                    }
                }

                //تقسیم بر تعداد نفرات
                if (numbers > 0) average = average / numbers;
            } catch (Exception e) {
            }
        }

        return average;
    }

    //انحراف معیار درخواست های حذف نشده
    private double devitation(double M, ArrayList<VM_PriceRange> arr, double m) {
        double ans = 100.0f;

        try {

            int numbers = 1;
            for (int i = 0; i < arr.size(); i++) {

                if (m > 0) {
                    if (!arr.get(i).isDeleted) {
                        ans += Math.pow(Double.valueOf(arr.get(i).percent) - m, 2);// (Xi - X)^2  , X is Average
                        numbers++;
                    }
                } else {
                    ans += Math.pow(Double.valueOf(arr.get(i).percent) - M, 2);// (Xi - X)^2  , X is Average
                    numbers++;
                }

            }
            ans = Math.sqrt(ans / (numbers - 1));
        } catch (Exception e) {
        }

        return ans;
    }

    //محاسبه درصد مبلغ پیشنهادی به نسبت مبلغ مناقصه
    public String CalculatePercent(String tender_offer, String price) {

        try {
            String ans = "";

            if (tender_offer == null) return ans;

            if (tender_offer.length() > 0) {
                double prc = (Double.valueOf(Replace.Number_fn_To_en(tender_offer)) * 100) / Double.valueOf(Replace.Number_fn_To_en(price));
                ans = String.valueOf(prc);
            }

            return ans;
        } catch (Exception e) {
            return "";
        }
    }

    //پیدا کردن برنده مناقصه
    private void ChooseWinner(double C, ArrayList<VM_PriceRange> arr, double c2) {

        //C : حد پایین
        //c2: حد بالا
        long max_price = 0;
        double max_percent = 1000;
        String index = "---";

        Collections.sort(arr, (o1, o2) -> Double.valueOf(o1.percent).compareTo(Double.valueOf(o2.percent)));

        for (int i = 0; i < arr.size(); i++) {
            VM_PriceRange item = arr.get(i);

            if (Double.valueOf(item.percent) >= C) {
                max_price = Long.valueOf(item.price);
                max_percent = Math.abs(C - Double.valueOf(item.percent));
                index = item.id;
                break;
            }
        }

        //در اینجا نقطه چشمک زن ست می شود
        s_priceRangeFragment.enableAnimationPointChart(index);

        s_priceRangeFragment.winnerSelection(String.valueOf(max_price), index, (int) c2, (int) C);
    }
}