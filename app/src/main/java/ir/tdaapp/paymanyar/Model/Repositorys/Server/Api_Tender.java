package ir.tdaapp.paymanyar.Model.Repositorys.Server;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotification;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotifications;

public class Api_Tender {

    //در اینجا اطلاع رسانی مناقصات برگشت داده می شود
    public Single<VM_TenderNotification> getTenderNotification(int page) {

        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    List<VM_TenderNotifications> notifications = new ArrayList<>();
                    VM_TenderNotification notification=new VM_TenderNotification();

                    for (int i = 1; i <= 20; i++) {

                        VM_TenderNotifications no = new VM_TenderNotifications();

                        no.setId(i);
                        no.setTitle("مناقصه شماره " + i + " " + page);
                        no.setFree(i % 2 == 0);
                        no.setStar(i % 2 != 0);

                        notifications.add(no);
                    }

                    notification.setCountTenders(40);
                    notification.setTenderNotifications(notifications);
                    emitter.onSuccess(notification);

                } catch (Exception e) {
                    emitter.onError(e);
                }

            }).run();

        });

    }

}
