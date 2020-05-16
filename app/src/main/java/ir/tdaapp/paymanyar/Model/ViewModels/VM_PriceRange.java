package ir.tdaapp.paymanyar.Model.ViewModels;

public class VM_PriceRange {

    public String id,price,percent;
    public boolean isDeleted=false;

    public VM_PriceRange(){}

    public VM_PriceRange(String id,String price,String percent){
        this.id=id;
        this.price=price;
        this.percent=percent;
    }
}
