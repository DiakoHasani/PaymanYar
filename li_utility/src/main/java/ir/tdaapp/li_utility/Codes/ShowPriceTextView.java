package ir.tdaapp.li_utility.Codes;

import android.widget.TextView;

import java.text.DecimalFormat;

public class ShowPriceTextView {

    private String text;
    private TextView lbl;

    public ShowPriceTextView(String text, TextView lbl) {
        this.text = text;
        this.lbl = lbl;

        convert();
    }

    void convert() {

        text = text.replace(",", "");

        if (text.length() > 0) {
            DecimalFormat sdd = new DecimalFormat("#,###");
            Double doubleNumber = Double.parseDouble(text);

            String format = sdd.format(doubleNumber);
            lbl.setText(format);
        }
    }
}
