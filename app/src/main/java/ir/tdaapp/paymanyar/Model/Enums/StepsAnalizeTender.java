package ir.tdaapp.paymanyar.Model.Enums;

//برای استپ های سفارش که در صفحه آنالیز می باشد
public enum  StepsAnalizeTender {
    //ارسال سفارش
    sendOrder,

    //بررسی سفارش
    orderCheck,

    //مدت زمان انجام
    duration,

    //در حال انجام
    doing,

    //پرداخت هزینه
    pay,

    //هزینه سفارش
    orderCost,

    //دریافت سفارش
    takingOrders
}
