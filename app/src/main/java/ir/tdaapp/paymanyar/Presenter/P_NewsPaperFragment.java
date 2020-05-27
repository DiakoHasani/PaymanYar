package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_NewsPaper;
import ir.tdaapp.paymanyar.Model.Services.S_NewsPaperFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_NewsPaper;

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

    public void start() {
        s_newsPaperFragment.OnStart();
        s_newsPaperFragment.onHideAll();
        s_newsPaperFragment.onLoading(true);
        getNewsPapers();
    }

    //در اینجا روزنامه ها گرفته می شوند
    void getNewsPapers() {

        Single<List<VM_NewsPaper>> data = api_newsPaper.getNews();

        dispose_getNewsPapers = data.subscribeWith(new DisposableSingleObserver<List<VM_NewsPaper>>() {
            @Override
            public void onSuccess(List<VM_NewsPaper> vm_newsPapers) {
                new Handler(Looper.getMainLooper()).post(() -> {
                    s_newsPaperFragment.onHideAll();
                    s_newsPaperFragment.onSuccess();
                    setNewsPaper(vm_newsPapers);
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
    void setNewsPaper(List<VM_NewsPaper> newsPaper) {

        Observable<VM_NewsPaper> newsPaperObservable = Observable.fromIterable(newsPaper);

        dispose_setNewsPaper = newsPaperObservable.subscribe(vm_newsPaper -> {
            s_newsPaperFragment.onNews(vm_newsPaper);
        }, throwable -> {

        }, () -> {
            s_newsPaperFragment.onFinish();
        });

    }

    public void Cancel(String Tag) {

        api_newsPaper.cancel(Tag,context);

        if (dispose_getNewsPapers != null) {
            dispose_getNewsPapers.dispose();
        }

        if (dispose_setNewsPaper != null) {
            dispose_setNewsPaper.dispose();
        }
    }
}
