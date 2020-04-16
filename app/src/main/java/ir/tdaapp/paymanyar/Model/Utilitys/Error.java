package ir.tdaapp.paymanyar.Model.Utilitys;

import ir.tdaapp.li_volley.Enum.ResaultCode;

public class Error {

    //در اینجا ارور وولی ست می شود
    public static ResaultCode GetErrorVolley(String er) {
        String error = er.replace("java.io.IOException: ", "");
        error = error.replace("\"", "");
        switch (error) {
            case "TimeoutError":
                return ResaultCode.TimeoutError;
            case "ServerError":
                return ResaultCode.ServerError;
            case "NetworkError":
                return ResaultCode.NetworkError;
            case "ParseError":
                return ResaultCode.ParseError;
            case "Error":
            default:
                return ResaultCode.Error;
        }
    }
}
