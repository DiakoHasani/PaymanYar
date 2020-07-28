package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_Tender;
import ir.tdaapp.paymanyar.Model.Services.S_OrdersFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Orders;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;

public class P_OrdersFragment {
    Context context;
    Api_Tender api_tender;
    S_OrdersFragment s_ordersFragment;
    Disposable dispose_getOrders, dispose_setOrders;

    public P_OrdersFragment(Context context, S_OrdersFragment s_ordersFragment) {
        this.context = context;
        this.s_ordersFragment = s_ordersFragment;
        api_tender = new Api_Tender();
    }

    public void start(int page) {

        s_ordersFragment.OnStart();

        getOrders(page);
    }

    void getOrders(int page) {

        if (page == 0) {
            s_ordersFragment.onHideAll();
            s_ordersFragment.onLoading(true);
        }else{
            s_ordersFragment.onLoadingPaging(true);
        }

        int userId = ((MainActivity) context).getTbl_user().getUserId(context);

        if (userId != 0) {
            Single<List<VM_Orders>> vals = api_tender.getOrders(page, userId, s_ordersFragment.getFilterOrder());

            dispose_getOrders = vals.subscribeWith(new DisposableSingleObserver<List<VM_Orders>>() {
                @Override
                public void onSuccess(List<VM_Orders> vm_orders) {
                    if (page == 0) {
                        s_ordersFragment.onHideAll();
                        s_ordersFragment.onSuccess();

                        if (vm_orders.size() == 0) {
                            s_ordersFragment.onEmpty();
                        } else {
                            setOrders(vm_orders);
                        }

                    } else {
                        s_ordersFragment.onLoadingPaging(false);
                        setOrders(vm_orders);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    if (page == 0) {
                        s_ordersFragment.onHideAll();
                        s_ordersFragment.onReload();
                    }else{
                        s_ordersFragment.onLoadingPaging(false);
                    }

                    s_ordersFragment.onError(Error.GetErrorVolley(e.toString()));
                }
            });

        } else {
            s_ordersFragment.onHideAll();
            s_ordersFragment.onReload();
            s_ordersFragment.onNoAccount();
        }
    }

    void setOrders(List<VM_Orders> orders) {

        //اگر شرط زیر درست باشد یعنی تعداد آیتم ها در سرور به پایان رسیده
        if (orders.size() < 30) {
            s_ordersFragment.onFinishOrderServer();
        }

        Observable<VM_Orders> data = Observable.fromIterable(orders);
        dispose_setOrders = data.subscribe(order -> {
            s_ordersFragment.onItem(order);
        }, throwable -> {

        }, () -> {
            s_ordersFragment.onFinish();
        });
    }

    public void cancel(String tag) {
        api_tender.Cancel(tag, context);

        if (dispose_getOrders != null) {
            dispose_getOrders.dispose();
        }

        if (dispose_setOrders != null) {
            dispose_setOrders.dispose();
        }
    }
}
