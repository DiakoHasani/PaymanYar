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

    //در اینجا معتبر بودن کد ملی چک می شود
    public static boolean NationalCode(String Message, EditText txt) {

        String nationalCode = Replace.Number_fn_To_en(txt.getText().toString());

        //در صورتی که کد ملی وارد شده تهی باشد
        if (nationalCode.equalsIgnoreCase("")) {
            txt.setError(Message);
            return false;
        }


        //در صورتی که کد ملی وارد شده طولش کمتر از 10 رقم باشد
        if (nationalCode.length() != 10) {
            txt.setError(Message);
            return false;
        }

        //در صورتی که کد ملی عدد نباشد
        if (!Pattern.compile("[0-9]+").matcher(nationalCode).matches()) {
            txt.setError(Message);
            return false;
        }

        //در صورتی که کد ملی ده رقم عددی نباشد
        String[] allDigitEqual = new String[]{"0000000000", "1111111111", "2222222222", "3333333333", "4444444444", "5555555555", "6666666666", "7777777777", "8888888888", "9999999999"};

        //در صورتی که رقم‌های کد ملی وارد شده یکسان باشد
        for (int i = 0; i < allDigitEqual.length; i++) {
            if (nationalCode.equalsIgnoreCase(allDigitEqual[i])) {
                txt.setError(Message);
                return false;
            }
        }

        //عملیات شرح داده شده در بالا
        char[] chArray = nationalCode.toCharArray();
        int num0 = Integer.valueOf(String.valueOf(chArray[0])) * 10;
        int num2 = Integer.valueOf(String.valueOf(chArray[1])) * 9;
        int num3 = Integer.valueOf(String.valueOf(chArray[2])) * 8;
        int num4 = Integer.valueOf(String.valueOf(chArray[3])) * 7;
        int num5 = Integer.valueOf(String.valueOf(chArray[4])) * 6;
        int num6 = Integer.valueOf(String.valueOf(chArray[5])) * 5;
        int num7 = Integer.valueOf(String.valueOf(chArray[6])) * 4;
        int num8 = Integer.valueOf(String.valueOf(chArray[7])) * 3;
        int num9 = Integer.valueOf(String.valueOf(chArray[8])) * 2;
        int a = Integer.valueOf(String.valueOf(chArray[9]));

        int b = (((((((num0 + num2) + num3) + num4) + num5) + num6) + num7) + num8) + num9;
        int c = b % 11;

        boolean Resault = (((c < 2) && (a == c)) || ((c >= 2) && ((11 - c) == a)));

        if (!Resault) {
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
