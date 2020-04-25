package ir.tdaapp.paymanyar.Model.Repositorys.Server;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_DetailsTender;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotification;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_TenderNotifications;

public class Api_Tender {

    //در اینجا اطلاع رسانی مناقصات برگشت داده می شود
    public Single<VM_TenderNotification> getTenderNotification(int page) {

        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    List<VM_TenderNotifications> notifications = new ArrayList<>();
                    VM_TenderNotification notification = new VM_TenderNotification();

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

    //در اینجا جزئیات مناقصات برگشت داده می شود
    public Single<VM_DetailsTender> getDetailsTender(int Id) {

        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    VM_DetailsTender detailsTender = new VM_DetailsTender();

                    detailsTender.setId(1);
                    detailsTender.setDescription("توضیحات الزامی است");
                    detailsTender.setGetDocumentsUp("1399/58/69");
                    detailsTender.setNationalEstimate("sdgsetewst");
                    detailsTender.setPlace_of_Receipt_of_Documents("sgfgfgdfg");
                    detailsTender.setReopeningDate("6546797");
                    detailsTender.setSendSuggestionsUp("stg3s4f6sd54f");
                    detailsTender.setTenderDevice("rtwr87twe+9r8");
                    detailsTender.setWebsite("www.rrrrr.com");

                    emitter.onSuccess(detailsTender);

                } catch (Exception e) {
                    emitter.onError(e);
                }

            }).run();

        });

    }

}
