package ir.tdaapp.paymanyar.Model.Services;

import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_FilterOrder;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Orders;

public interface S_OrdersFragment {
    void OnStart();
    void onFinish();
    void onSuccess();
    void onError(ResaultCode result);
    void onItem(VM_Orders order);
    void onNoAccount();
    void onLoading(boolean load);
    void onHideAll();
    void onEmpty();
    void onReload();
    VM_FilterOrder getFilterOrder();
    void onLoadingPaging(boolean load);
    void onFinishOrderServer();
    void onLoading_DownloadFile(boolean load);
    void onErrorDownloadFile(Throwable e);
    void onShowFile(String fileName);
    void onResultRemoveOrder(VM_Message message,int orderId);
    void onErrorRemoveOrder(ResaultCode result);
}