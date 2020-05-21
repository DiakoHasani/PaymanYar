package ir.tdaapp.paymanyar.Model.Services;

//برای نتیجه صفحه اصلی که آیا برنامه آپدیت لازم است یا خیر
public interface resultVersionApp_Sql {
    void updateApp(boolean update,boolean hadUpdate);
    void clearData(boolean clear);
}
