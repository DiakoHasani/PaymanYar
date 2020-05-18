package ir.tdaapp.paymanyar.Model.ViewModels;

public class VM_SMS {

    private String Id;
    private String text;
    private boolean Kird;
    private boolean fevorit;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isKird() {
        return Kird;
    }

    public void setKird(boolean kird) {
        Kird = kird;
    }

    public boolean isFevorit() {
        return fevorit;
    }

    public void setFevorit(boolean fevorit) {
        this.fevorit = fevorit;
    }
}
