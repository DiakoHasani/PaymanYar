package ir.tdaapp.li_utility.Codes;

public class Replace {

    ///در اینجا اعداد انگلیسی به فارسی تبدیل می شود
    public static String Number_en_To_fa(String val){
        val=val.replace("0","۰");
        val=val.replace("1","۱");
        val=val.replace("2","۲");
        val=val.replace("3","۳");
        val=val.replace("4","۴");
        val=val.replace("5","۵");
        val=val.replace("6","۶");
        val=val.replace("7","۷");
        val=val.replace("8","۸");
        val=val.replace("9","۹");

        return val;
    }

    //در اینجا اعداد فارسی به انگلیسی تبدیل می شوند
    public static String Number_fn_To_en(String val){
        val=val.replace("۰","0");
        val=val.replace("۱","1");
        val=val.replace("۲","2");
        val=val.replace("۳","3");
        val=val.replace("۴","4");
        val=val.replace("۵","5");
        val=val.replace("۶","6");
        val=val.replace("۷","7");
        val=val.replace("۸","8");
        val=val.replace("۹","9");
        return val;
    }
}
