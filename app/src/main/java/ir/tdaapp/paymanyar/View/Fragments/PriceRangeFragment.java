package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import ir.tdaapp.li_utility.Codes.ShowPrice;
import ir.tdaapp.paymanyar.Model.Services.S_PriceRangeFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Presenter.P_PriceRangeFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;

//صفحه دامنه قیمت
public class PriceRangeFragment extends BaseFragment implements S_PriceRangeFragment {

    P_PriceRangeFragment p_priceRangeFragment;

    EditText txt_Price, txt_Number_of_People;
    EditText txt_AmountToToman1, txt_AmountToToman2, txt_AmountToToman3, txt_AmountToToman4, txt_AmountToToman5, txt_AmountToToman6;
    EditText txt_AmountToToman7,txt_AmountToToman8,txt_Percentage;
    Spinner cmb_Degree_of_Importance;
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
        txt_Percentage = view.findViewById(R.id.txt_percentage);
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
}
