package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.core.content.FileProvider;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_Library;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_Library;
import ir.tdaapp.paymanyar.Model.Services.S_LibraryFragment;
import ir.tdaapp.paymanyar.Model.Services.addLibrary;
import ir.tdaapp.paymanyar.Model.Utilitys.Error;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Library;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.R;

public class P_LibraryFragment {

    private Context context;
    private S_LibraryFragment s_libraryFragment;
    Api_Library api_library;
    Disposable dispose_getLibraries, dispose_setLibraries, dispose_downloadLibrary, dispose_downloadPDF;
    Tbl_Library tbl_library;

    public P_LibraryFragment(Context context, S_LibraryFragment s_libraryFragment) {
        this.context = context;
        this.s_libraryFragment = s_libraryFragment;
        api_library = new Api_Library();
        tbl_library = new Tbl_Library(context);
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

        Single<List<VM_Library>> data = api_library.getLibraries(queryText, page, tbl_library);

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

    //زمانی که کاربر یک کتاب دانلود کند آی دی آن به سرور ارسال می شود
    public void downloadLibrary(int id) {
        Single<VM_Message> data = api_library.addCountDownloadLibrary(id);

        dispose_downloadLibrary = data.subscribeWith(new DisposableSingleObserver<VM_Message>() {
            @Override
            public void onSuccess(VM_Message message) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    //زمانی که کاربر یک کتابخانه دانلود کند آن را به لیست دانلود شده ها اضافه می کند
    public void addLibraryDownloaded(int libraryId, addLibrary a) {
        tbl_library.add(libraryId, a);
    }

    public void downloadPDF(String downloadUrl, String title) {

        new Handler(Looper.getMainLooper()).post(() -> {
            s_libraryFragment.onLoadingDownloadPDF(true);
        });

        Single<Boolean> val = api_library.downloadPDF(downloadUrl, title);
        dispose_downloadPDF = val.subscribeWith(new DisposableSingleObserver<Boolean>() {
            @Override
            public void onSuccess(Boolean res) {

                new Handler(Looper.getMainLooper()).post(() -> {
                    s_libraryFragment.onLoadingDownloadPDF(false);
                    if (res) {
                        s_libraryFragment.onShowPDF(title);
                    } else {
                        s_libraryFragment.onErrorDownloadPDF();
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                new Handler(Looper.getMainLooper()).post(() -> {
                    s_libraryFragment.onLoadingDownloadPDF(false);
                    s_libraryFragment.onErrorDownloadPDF();
                });
            }
        });
    }

    //در اینجا پی دی اف به اشتراک گذاشته می شود
    public void sharePDF(VM_Library library) {

        String fileName = library.getBookName().substring(11);
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);

        if (file.exists()) {
            try {
                Uri uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file);
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                shareIntent.setType("application/pdf");
                context.startActivity(Intent.createChooser(shareIntent,context.getString(R.string.share)));
            } catch (Exception e) { }
        } else {
            Toast.makeText(context, context.getString(R.string.Download_the_PDF_first), Toast.LENGTH_SHORT).show();
        }
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

        if (dispose_downloadLibrary != null) {
            dispose_downloadLibrary.dispose();
        }

        if (dispose_downloadPDF != null) {
            dispose_downloadPDF.dispose();
        }
    }
}
