package ir.tdaapp.paymanyar.Model.ViewModels;

import ir.tdaapp.paymanyar.Model.Enums.StepsAnalizeTender;

//برای لیست سفارشات
public class VM_Orders {
    int id;

    //تیتر مناقصه
    String title;

    //تاریخ انجام
    String date;

    //هزینه پرداخت
    String payment;

    //وضعیت سفارش که درچه حالتی است
    StepsAnalizeTender stepsAnalizeTender;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public StepsAnalizeTender getStepsAnalizeTender() {
        return stepsAnalizeTender;
    }

    public void setStepsAnalizeTender(StepsAnalizeTender stepsAnalizeTender) {
        this.stepsAnalizeTender = stepsAnalizeTender;
    }
}
