package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.paymanyar.Model.Enums.OrderKind;
import ir.tdaapp.paymanyar.Model.Enums.StepsAnalizeTender;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Orders;

//مربوط به کلیک روی آیتم سفارشات
public interface onClickOrders {
    void onClickItem(int id, OrderKind orderKind);
    void onClickButton(VM_Orders order);
}
