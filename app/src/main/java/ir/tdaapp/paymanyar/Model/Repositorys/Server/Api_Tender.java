package ir.tdaapp.paymanyar.Model.Repositorys.Server;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotification;

public class Api_Tender {

    //در اینجا اطلاع رسانی مناقصات برگشت داده می شود
    public Single<List<VM_TenderNotification>> getTenderNotification(int page) {

        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    List<VM_TenderNotification> notifications = new ArrayList<>();

                    for (int i = 1; i <= 20; i++) {

                        VM_TenderNotification notification = new VM_TenderNotification();

                        notification.setId(i);
                        notification.setTitle("مناقصه شماره " + i + " " + page);
                        notification.setFree(i % 2 == 0);
                        notification.setStar(i % 2 != 0);

                        notifications.add(notification);
                    }

                    emitter.onSuccess(notifications);

                } catch (Exception e) {
                    emitter.onError(e);
                }

            }).run();

        });

    }

}
