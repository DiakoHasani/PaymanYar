package ir.tdaapp.paymanyar.Model.ViewModels;

/**
 * مربوط به ارتقا آگهی
 **/
public class VM_AdUpgrade {
    int id;
    String price;
    String description;

    public VM_AdUpgrade() {
        id = 0;
        price = "-";
        description = "-";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
