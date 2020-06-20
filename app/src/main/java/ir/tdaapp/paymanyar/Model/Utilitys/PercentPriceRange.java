package ir.tdaapp.paymanyar.Model.Utilitys;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import ir.tdaapp.li_utility.Codes.Replace;

public class PercentPriceRange implements TextWatcher {

    EditText txt_PricePercent, txt_AmountToToman, txt_Price;

    public PercentPriceRange(EditText txt_PricePercent, EditText txt_AmountToToman, EditText txt_Price) {
        this.txt_PricePercent = txt_PricePercent;
        this.txt_AmountToToman = txt_AmountToToman;
        this.txt_Price = txt_Price;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

        try {

            if (txt_Price.getText().length() > 0) {

                if (txt_AmountToToman.getText() == null) {
                    txt_PricePercent.setText("0");
                } else {

                    if (txt_AmountToToman.getText().toString().length() > 0) {
                        String price = txt_AmountToToman.getText().toString();
                        price = price.replace(",", "").replace("٬", "");
                        String precent = txt_Price.getText().toString().replace(",", "").replace("٬", "");

                        price= Replace.Number_fn_To_en(price);
                        precent=Replace.Number_fn_To_en(precent);

                        double prc = (Double.valueOf(price) * 100) / Double.valueOf(precent);
                        txt_PricePercent.setText(String.valueOf(prc));
                    } else {
                        txt_PricePercent.setText("0");
                    }

                }

            } else {
                txt_PricePercent.setText("");
            }

        } catch (Exception e) {
        }

    }
}
