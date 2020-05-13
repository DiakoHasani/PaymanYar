package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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
    private double GetPriority(int participant_count,int priority){

        /*
        *   این متد برای محاسبه ضریب اهمیت مقایسه یا همان t است
         */

        double t=0.0;

        if(participant_count>=3 && participant_count<=6){
            if(priority==1)t=1.1;
            if(priority==2)t=1.0;
            if(priority==3)t=0.9;
        }else if(participant_count>=7 && participant_count<=10){
            if(priority==1)t=1.3;
            if(priority==2)t=1.2;
            if(priority==3)t=1.1;
        }else if(participant_count>10){
            if(priority==1)t=1.5;
            if(priority==2)t=1.4;
            if(priority==3)t=1.3;
        }

        return t;
    }

    public void StartCalculate(String price, ArrayList<VM_PriceRange> participates,String Guarantee,int priority){

        //مبلغ مناقصه
        long A=(Long.valueOf(price));

        //میزان اهمیت
        double T=GetPriority(participates.size(),priority);

        //مبلغ تضمین
        long D=A*5/100; // Default
        if(Guarantee.length()>0){
            // If User Entered Guarantee Value
            D=Long.valueOf(Guarantee);
        }

        //تعداد پیمانکاران
        int N=participates.size()+1;

        //میانگین درصدهای پیشنهادی
        long M=GetAverage(participates,A);




    }

    private long GetAverage(ArrayList<VM_PriceRange> arr,long price){
        long average=price;

        try {
            //جمع کردن مبالغ
            for (int i=0;i<arr.size();i++){
                average+=Long.valueOf(arr.get(i).price);
            }

            //تقسیم بر تعداد نفرات
            if(arr.size()>0)average=average/(arr.size()+1);
        }catch (Exception e){}

        return average;
    }

}
