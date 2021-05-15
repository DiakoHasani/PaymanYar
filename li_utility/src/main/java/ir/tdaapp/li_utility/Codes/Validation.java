package ir.tdaapp.li_utility.Codes;

import android.util.Patterns;
import android.widget.EditText;

import java.util.regex.Pattern;

public class Validation {

    //در اینجا اجباری بودن پر کردن ادیت تکست ست می شود
    public static boolean Required(EditText txt, String Message) {
        if (txt.getText().toString().equalsIgnoreCase("")) {
            txt.setError(Message);
            return false;
        }
        return true;
    }

    //در اینجا حداقل و حداکثر تعداد کاراکتر ست می شود
    public static boolean StringLength(EditText txt, int MinChar, int MaxChar, String Error) {
        String Text = txt.getText().toString();

        if (Text.length() < MinChar || Text.length() > MaxChar) {
            txt.setError(Error);
            return false;
        }
        return true;
    }

    //در اینجا حداکثر تعداد کاراکتر ست می شود
    public static boolean MaxLength(EditText txt, String Message, int MaxChar) {
        String Text = txt.getText().toString();

        if (Text.length() > MaxChar) {
            txt.setError(Message);
            return false;
        }
        return true;
    }

    //در اینجا حداقل تعداد کاراکتر ست می شود
    public static boolean MinLength(EditText txt, String Message, int MinChar) {
        String Text = txt.getText().toString();

        if (Text.length() < MinChar) {
            txt.setError(Message);
            return false;
        }
        return true;
    }

    //در اینجا ولید بودن ایمیل ست می شود
    public static boolean EmailValid(EditText txt, String Message) {
        String Text = txt.getText().toString();

        if (!Patterns.EMAIL_ADDRESS.matcher(Text).matches()) {
            txt.setError(Message);
            return false;
        }
        return true;
    }

    //در اینجا چک می کند که کاربر فقط عدد وارد کند
    public static boolean IsNumber(EditText txt, String Message) {

        if (!txt.getText().toString().equalsIgnoreCase("")){
            if (!Pattern.compile("[0-9]+").matcher(txt.getText().toString()).matches()) {
                txt.setError(Message);
                return false;
            }
        }

        return true;
    }

    /**
     * در اینجا ولیدیشن شماره موبایل چک می شود
     **/
    public static boolean ValidPhoneNumber(EditText txt, String message) {

        String check = txt.getText().toString();
        if (!check.matches("(\\+98|0)?9\\d{9}")) {
            txt.setError(message);
            return false;
        }

        return true;
    }
}
