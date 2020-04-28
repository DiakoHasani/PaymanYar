package ir.tdaapp.paymanyar.Model.Repositorys.Server;

import java.util.List;

import io.reactivex.Single;
import ir.tdaapp.paymanyar.Model.Services.S_HandleXML;
import ir.tdaapp.paymanyar.Model.Utilitys.HandleXML;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_NewsPaper;

//مربوط به روزنامه
public class Api_NewsPaper {

    //در اینجا لیست روزنامه ها پاس داده می شود
    public Single<List<VM_NewsPaper>> getNews() {
        return Single.create(emitter -> {
            try {

                HandleXML obj;
                obj = new HandleXML("http://www.pishkhaan.net/pishkhaan.xml", new S_HandleXML() {
                    @Override
                    public void onSuccess(List<VM_NewsPaper> newsPapers) {
                        emitter.onSuccess(newsPapers);
                    }

                    @Override
                    public void onError(Exception e) {
                        emitter.onError(e);
                    }
                });
                obj.fetchXML();

            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
