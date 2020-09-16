package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_NewsPaper;
import ir.tdaapp.paymanyar.Model.Services.S_NewsPaperFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_NewsPaper;
import ir.tdaapp.paymanyar.R;

public class P_NewsPaperFragment {

    private Context context;
    private S_NewsPaperFragment s_newsPaperFragment;
    Api_NewsPaper api_newsPaper;
    Disposable dispose_getNewsPapers, dispose_setNewsPaper;

    public P_NewsPaperFragment(Context context, S_NewsPaperFragment s_newsPaperFragment) {
        this.context = context;
        this.s_newsPaperFragment = s_newsPaperFragment;
        api_newsPaper = new Api_NewsPaper();
    }

    public void start(String title) {
        s_newsPaperFragment.OnStart();
        s_newsPaperFragment.onHideAll();
        s_newsPaperFragment.onLoading(true);
        getNewsPapers(title);
    }

    //در اینجا روزنامه ها گرفته می شوند
    void getNewsPapers(String title) {

        Single<List<VM_NewsPaper>> data = api_newsPaper.getNews(context);

        dispose_getNewsPapers = data.subscribeWith(new DisposableSingleObserver<List<VM_NewsPaper>>() {
            @Override
            public void onSuccess(List<VM_NewsPaper> vm_newsPapers) {
                new Handler(Looper.getMainLooper()).post(() -> {
                    s_newsPaperFragment.onHideAll();
                    s_newsPaperFragment.onSuccess();
                    setNewsPaper(vm_newsPapers,title);
                });

            }

            @Override
            public void onError(Throwable e) {

                new Handler(Looper.getMainLooper()).post(() -> {
                    s_newsPaperFragment.onHideAll();
                    s_newsPaperFragment.onError(Error.GetErrorVolley(e.toString()));
                });

            }
        });
    }

    //در اینجا روزنامه ها در رسایکلر ست می شوند
    void setNewsPaper(List<VM_NewsPaper> newsPaper,String title) {

        //در اینجا تایتل روزنامه ها گرفته می شود و در تکست باکس سرچ ست می شوند
        List<String> valsAdapter = new ArrayList<>();

        Observable<VM_NewsPaper> newsPaperObservable = Observable.fromIterable(newsPaper);

        dispose_setNewsPaper = newsPaperObservable.subscribe(vm_newsPaper -> {

            if (!title.equalsIgnoreCase("")){

                if (vm_newsPaper.getTitle().contains(title)){
                    s_newsPaperFragment.onNews(vm_newsPaper);
                }

            }else{
                s_newsPaperFragment.onNews(vm_newsPaper);
            }

            //در اینجا تایتل روزنامه ها گرفته می شود
            valsAdapter.add(vm_newsPaper.getTitle());
        }, throwable -> {

        }, () -> {

            //در اینجا تایتل ها در آداپتر ست می شوند
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.layout_auto_complete, valsAdapter);
            //سپس به ادیت تکست ارسال می شود
            s_newsPaperFragment.onAdapter_txtSearch(adapter);

            s_newsPaperFragment.onFinish();
        });

    }

    public void Cancel(String Tag) {

        api_newsPaper.cancel(Tag, context);

        if (dispose_getNewsPapers != null) {
            dispose_getNewsPapers.dispose();
        }

        if (dispose_setNewsPaper != null) {
            dispose_setNewsPaper.dispose();
        }
    }
}
