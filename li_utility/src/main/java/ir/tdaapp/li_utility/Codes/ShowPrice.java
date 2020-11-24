package ir.tdaapp.li_utility.Codes;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;

public class ShowPrice implements TextWatcher {

    private EditText editText;
    String text="";

    public ShowPrice(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        text=charSequence.toString();
    }

    @Override
    public void afterTextChanged(Editable editable) {
        editText.removeTextChangedListener(this);

        String s = text;

        s = s.replace(",", "");
        s = s.replace("٬", "");
        s = s.replaceAll("[^\\d.]", "");
        if (s.length() > 0) {
            DecimalFormat sdd = new DecimalFormat("#,###");
            s = Replace.Number_fn_To_en(s);
            Double doubleNumber = Double.parseDouble(s);

            String format = sdd.format(doubleNumber);
            editText.setText(Replace.Number_fn_To_en(format));
            editText.setSelection(format.length());

        }
        editText.addTextChangedListener(this);
    }
}
