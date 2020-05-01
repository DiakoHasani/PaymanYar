package ir.tdaapp.li_utility.Codes;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;

public class ShowPrice implements TextWatcher {

    private EditText editText;

    public ShowPrice(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        editText.removeTextChangedListener(this);

        String s = editText.getText().toString();

        s = s.replace(",", "");
        if (s.length() > 0) {
            DecimalFormat sdd = new DecimalFormat("#,###");
            Double doubleNumber = Double.parseDouble(s);

            String format = sdd.format(doubleNumber);
            editText.setText(format);
            editText.setSelection(format.length());

        }
        editText.addTextChangedListener(this);
    }
}
