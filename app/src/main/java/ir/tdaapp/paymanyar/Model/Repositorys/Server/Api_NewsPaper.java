package ir.tdaapp.paymanyar.Model.Repositorys.Server;

import android.content.Context;

import java.util.List;

import io.reactivex.Single;
import ir.tdaapp.paymanyar.Model.Services.S_HandleXML;
import ir.tdaapp.paymanyar.Model.Utilitys.Base_Api;
import ir.tdaapp.paymanyar.Model.Utilitys.HandleXML;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_NewsPaper;

//مربوط به روزنامه
public class Api_NewsPaper extends Base_Api {

    //در اینجا لیست روزنامه ها پاس داده می شود
    public Single<List<VM_NewsPaper>> getNews() {
        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    HandleXML obj;
                    obj = new HandleXML("https://www.pishkhan.com/pishkhaan.xml", new S_HandleXML() {
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
                    postError("Api_NewsPaper->getNews",e.toString());
                    emitter.onError(e);
                }

            }).start();
        });
    }

    public void cancel(String tag, Context context){
        cancelBase(tag,context);
    }
}
