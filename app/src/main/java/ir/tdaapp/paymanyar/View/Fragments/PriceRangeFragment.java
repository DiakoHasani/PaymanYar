package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import ir.tdaapp.li_utility.Codes.ShowPrice;
import ir.tdaapp.paymanyar.Model.Services.S_PriceRangeFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_PriceRange;
import ir.tdaapp.paymanyar.Presenter.P_PriceRangeFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;

//صفحه دامنه قیمت
public class PriceRangeFragment extends BaseFragment implements S_PriceRangeFragment,View.OnClickListener {

    P_PriceRangeFragment p_priceRangeFragment;

    EditText txt_Price, txt_Number_of_People;
    EditText txt_AmountToToman1, txt_AmountToToman2, txt_AmountToToman3, txt_AmountToToman4, txt_AmountToToman5, txt_AmountToToman6;
    EditText txt_AmountToToman7,txt_AmountToToman8,percent1,percent2,percent3,percent4,percent5,percent6,percent7,percent8,txt_Percentage;
    Spinner cmb_Degree_of_Importance;
    TextView winnerPrice,winnerNumber;
    Toolbar toolbar;
    CardView btn_PriceRange;

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

        txt_Percentage = view.findViewById(R.id.txt_percentage);
        btn_PriceRange = view.findViewById(R.id.btn_PriceRange);
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

        btn_PriceRange.setOnClickListener(this);
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

    //در اینجا آداپتر میزان اهمیت گرفته می شود
    @Override
    public void onGetDegreeOfImportance(ArrayAdapter adapter) {
        cmb_Degree_of_Importance.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_PriceRange:
                Calculate();
                break;
        }
    }

    private void Calculate(){

        txt_Price.setError(null);

        //چک کنیم که مبلغ مناقصه وارد شده است
        if(txt_Price.getText().toString().length()>0){

            //ابتدا درصدها مشخص شود
            percent1.setText(p_priceRangeFragment.CalculatePercent(txt_AmountToToman1.getText().toString().replaceAll(",",""),txt_Price.getText().toString().replaceAll(",","")));
            percent2.setText(p_priceRangeFragment.CalculatePercent(txt_AmountToToman2.getText().toString().replaceAll(",",""),txt_Price.getText().toString().replaceAll(",","")));
            percent3.setText(p_priceRangeFragment.CalculatePercent(txt_AmountToToman3.getText().toString().replaceAll(",",""),txt_Price.getText().toString().replaceAll(",","")));
            percent4.setText(p_priceRangeFragment.CalculatePercent(txt_AmountToToman4.getText().toString().replaceAll(",",""),txt_Price.getText().toString().replaceAll(",","")));
            percent5.setText(p_priceRangeFragment.CalculatePercent(txt_AmountToToman5.getText().toString().replaceAll(",",""),txt_Price.getText().toString().replaceAll(",","")));
            percent6.setText(p_priceRangeFragment.CalculatePercent(txt_AmountToToman6.getText().toString().replaceAll(",",""),txt_Price.getText().toString().replaceAll(",","")));
            percent7.setText(p_priceRangeFragment.CalculatePercent(txt_AmountToToman7.getText().toString().replaceAll(",",""),txt_Price.getText().toString().replaceAll(",","")));
            percent8.setText(p_priceRangeFragment.CalculatePercent(txt_AmountToToman8.getText().toString().replaceAll(",",""),txt_Price.getText().toString().replaceAll(",","")));

            //ساخت یک آرایه از مقادیر پیشنهادی
            ArrayList<VM_PriceRange> arrayList=new ArrayList<>();
            arrayList.add(new VM_PriceRange("1",txt_AmountToToman1.getText().toString().replaceAll(",",""),percent1.getText().toString()));
            arrayList.add(new VM_PriceRange("2",txt_AmountToToman2.getText().toString().replaceAll(",",""),percent2.getText().toString()));
            arrayList.add(new VM_PriceRange("3",txt_AmountToToman3.getText().toString().replaceAll(",",""),percent3.getText().toString()));
            arrayList.add(new VM_PriceRange("4",txt_AmountToToman4.getText().toString().replaceAll(",",""),percent4.getText().toString()));
            arrayList.add(new VM_PriceRange("5",txt_AmountToToman5.getText().toString().replaceAll(",",""),percent5.getText().toString()));
            arrayList.add(new VM_PriceRange("6",txt_AmountToToman6.getText().toString().replaceAll(",",""),percent6.getText().toString()));
            arrayList.add(new VM_PriceRange("7",txt_AmountToToman7.getText().toString().replaceAll(",",""),percent7.getText().toString()));
            arrayList.add(new VM_PriceRange("8",txt_AmountToToman8.getText().toString().replaceAll(",",""),percent8.getText().toString()));

            //شروع محاسبه
            p_priceRangeFragment.StartCalculate(txt_Price.getText().toString().replaceAll(",",""),arrayList,"",cmb_Degree_of_Importance.getSelectedItemId());

        }else{
            //نمایش خطا
            txt_Price.setError("لطفا مبلغ را وارد کنید");
        }
    }

    @Override
    public void onWinnerChoosed(String number, String position) {
        winnerPrice.setText(number);
        winnerNumber.setText(position);
    }
}
