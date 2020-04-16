package ir.tdaapp.li_volley.ViewModel;

import org.json.JSONObject;

import ir.tdaapp.li_volley.Enum.ResaultCode;

public class ResaultGetJsonObjectVolley {
    private ResaultCode resault;
    private JSONObject object;
    private String Message;

    public ResaultCode getResault() {
        return resault;
    }

    public JSONObject getObject() {
        return object;
    }

    public void setResault(ResaultCode resault) {
        this.resault = resault;
    }

    public void setObject(JSONObject object) {
        this.object = object;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
