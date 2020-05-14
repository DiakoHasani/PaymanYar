package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_Library;
import ir.tdaapp.paymanyar.Model.Services.S_LibraryFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Library;

public class P_LibraryFragment {

    private Context context;
    private S_LibraryFragment s_libraryFragment;
    Api_Library api_library;
    Disposable dispose_getLibraries, dispose_setLibraries;

    public P_LibraryFragment(Context context, S_LibraryFragment s_libraryFragment) {
        this.context = context;
        this.s_libraryFragment = s_libraryFragment;
        api_library = new Api_Library();
    }

    public void start(String queryText, int page) {

        if (page == 0) {
            s_libraryFragment.onHideAll();
            s_libraryFragment.OnStart();
        }

        getLibraries(queryText, page);
    }

    //در اینجا کتاب ها گرفته می شود
    void getLibraries(String queryText, int page) {

        if (page == 0) {
            s_libraryFragment.onLoading(true);
        } else {
            s_libraryFragment.onLoadingPaging(true);
        }

        Single<List<VM_Library>> data = api_library.getLibraries(queryText, page);

        dispose_getLibraries = data.subscribeWith(new DisposableSingleObserver<List<VM_Library>>() {
            @Override
            public void onSuccess(List<VM_Library> vm_libraries) {

                if (page == 0) {
                    s_libraryFragment.onHideAll();
                    s_libraryFragment.onSuccess();

                    if (vm_libraries.size() == 0) {
                        s_libraryFragment.onEmptyItem();
                    }

                } else {
                    s_libraryFragment.onLoadingPaging(false);
                }

                setLibraries(vm_libraries);
            }

            @Override
            public void onError(Throwable e) {
                if (page == 0) {
                    s_libraryFragment.onHideAll();
                }
                s_libraryFragment.onError(Error.GetErrorVolley(e.toString()));
            }
        });
    }

    //در اینجا کتابخانه ها در رسایکلر ست می شود
    void setLibraries(List<VM_Library> libraries) {

        Observable<VM_Library> data = Observable.fromIterable(libraries);

        dispose_setLibraries = data.subscribe(library -> {
            s_libraryFragment.onLibraryItem(library);
        }, throwable -> {

        }, () -> {
            if (libraries.size() == 40) {
                s_libraryFragment.onFinish();
            }
        });

    }

    public void Cancel(String tag) {

        if (api_library != null) {
            api_library.Cancel(tag, context);
        }

        if (dispose_getLibraries != null) {
            dispose_getLibraries.dispose();
        }

        if (dispose_setLibraries != null) {
            dispose_setLibraries.dispose();
        }
    }
}
