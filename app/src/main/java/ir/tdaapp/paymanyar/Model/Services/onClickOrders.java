package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.paymanyar.Model.Enums.StepsAnalizeTender;

//مربوط به کلیک روی آیتم سفارشات
public interface onClickOrders {
    void onClickItem(int id);
    void onClickButton(int id,StepsAnalizeTender step);
}
