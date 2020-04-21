package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

public class FieldItem {
    public String name,value;

    public FieldItem(){
        this.name="";
        this.value="";
    }

    public FieldItem(String name,String value){
        this.name=name;
        this.value=value;
    }
}
