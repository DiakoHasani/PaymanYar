package ir.tdaapp.paymanyar.View.Fragments;

import android.Manifest;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Adapters.LibraryAdapter;
import ir.tdaapp.paymanyar.Model.Services.S_LibraryFragment;
import ir.tdaapp.paymanyar.Model.Services.addLibrary;
import ir.tdaapp.paymanyar.Model.Services.onClickLibrary;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.openUrl;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Library;
import ir.tdaapp.paymanyar.Presenter.P_LibraryFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Dialogs.ErrorAplicationDialog;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

//مربوط به صفحه کتابخانه
public class LibraryFragment extends BaseFragment implements S_LibraryFragment, View.OnClickListener {

    public static final String TAG = "LibraryFragment";

    P_LibraryFragment p_libraryFragment;
    LibraryAdapter libraryAdapter;
    LinearLayoutManager layoutManager;
    Toolbar toolbar;
    RecyclerView recycler;
    ShimmerFrameLayout loading;
    EditText txt_Search;
    Button btn_Search;
    int page = 0;
    ErrorAplicationDialog errorAplicationDialog;
    boolean isLoading = false;
    ProgressBar progress_paging;
    LinearLayout empty;
    SwipeRefreshLayout reload;
    String[] Permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    RelativeLayout loading_Download;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.library_fragment, container, false);

        findItem(view);
        implement();
        setToolbar();

        new Handler().postDelayed(() -> {
            p_libraryFragment.start(txt_Search.getText().toString(), page);
        }, 300);
        return view;
    }

    void findItem(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        recycler = view.findViewById(R.id.recycler);
        loading = view.findViewById(R.id.loading);
        btn_Search = view.findViewById(R.id.btn_Search);
        txt_Search = view.findViewById(R.id.txt_Search);
        progress_paging = view.findViewById(R.id.progress_paging);
        empty = view.findViewById(R.id.empty);
        reload = view.findViewById(R.id.reload);
        loading_Download = view.findViewById(R.id.loading_Download);
    }

    void implement() {
        p_libraryFragment = new P_LibraryFragment(getContext(), this);
        btn_Search.setOnClickListener(this);

        recycler.setOnScrollListener(pOnScrollListener);

        reload.setOnRefreshListener(() -> {
            page = 0;
            p_libraryFragment.start(txt_Search.getText().toString(), page);
        });

    }

    //در اینجا تنظیمات تولبار ست می شود
    void setToolbar() {

        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setTitle(getContext().getResources().getString(R.string.Library));
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });
        setHasOptionsMenu(true);
    }

    //در اینجا چک می کند که زمان پیجینگ رسیده است اگر رسیده باشد عملیات را شروع می کند
    RecyclerView.OnScrollListener pOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            //در اینجا چک می کند آداپتر رسایکلر نال نباشد که در برنامه خطای رخ دهد
            if (recycler.getAdapter() != null) {

                //اگر متغیر زیر ترو باشد یعنی مشغول عملیات پیجینگ است تا عملیات به پایان نرسد اجازه پیجینگ نمی دهد
                if (!isLoading) {

                    //اگر اسکرول رسایکلر ما به آخر برسد یعنی زمان پیجینگ است و شرط زیر اجرا می شود
                    if (isLastItemDisplaying()) {

                        isLoading = true;

                        ++page;
                        p_libraryFragment.start(txt_Search.getText().toString(), page);
                    }
                }
            }

        }
    };

    //در اینجا اگر مقدار ترو برگشت داده شود یعنی زمان پیجینگ رسیده است و نیاز به خواندن داده از سرور می باشد
    boolean isLastItemDisplaying() {

        if (!isLoading) {

            int visibleItemCount = layoutManager.getChildCount() + 5;
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= PAGE_SIZE) {
                isLoading = true;
                return true;
            }
        }

        return false;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void OnStart() {
        loading.startShimmerAnimation();

        libraryAdapter = new LibraryAdapter(getContext());
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        recycler.setAdapter(libraryAdapter);
        recycler.setLayoutManager(layoutManager);

        libraryAdapter.setOnClickLibrary(new onClickLibrary() {
            @Override
            public void clickDownload(VM_Library library) {

                Dexter.withActivity(getActivity()).withPermissions(Permissions).withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {

                        String fileName = library.getBookName().substring(11);

                        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);

                        if (!file.exists()) {
                            //در اینجا آیدی کتاب به سمت سرور ارسال می شود برای بالا بردن تعداد دانلود این کتاب
                            p_libraryFragment.downloadLibrary(library.getId());

                            //در اینجا کتاب دانلود می شود
                            p_libraryFragment.downloadPDF(library.getUrl(), fileName);

                            //در اینجا آی دی کتاب دانلود شده در دیتابیس ذخیره می شود
                            p_libraryFragment.addLibraryDownloaded(library.getId(), new addLibrary() {
                                @Override
                                public void onSuccess() {
                                    libraryAdapter.addLibraryDownloaded(library.getId());
                                }

                                @Override
                                public void onError(String message) {
                                }
                            });

                        } else {
                            onShowPDF(fileName);
                        }

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }
                }).check();

            }

            @Override
            public void clickItem(VM_Library library) {

                Dexter.withActivity(getActivity()).withPermissions(Permissions).withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        String fileName = library.getBookName().substring(11);
                        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);
                        if (file.exists()) {
                            onShowPDF(fileName);
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }
                }).check();

            }

            @Override
            public void clickShare(VM_Library library) {
                p_libraryFragment.sharePDF(library);
            }
        });
    }

    @Override
    public void onError(ResaultCode resalt) {

        reload.setRefreshing(false);
        String text = "";

        switch (resalt) {
            case NetworkError:
                text = getString(R.string.please_Checked_Your_Internet_Connection);
                break;
            case TimeoutError:
                text = getString(R.string.YourInternetIsVrySlow);
                break;
            case ServerError:
                text = getString(R.string.There_Was_an_Error_In_The_Server);
                break;
            case ParseError:
            case Error:
                text = getString(R.string.There_Was_an_Error_In_The_Application);
                break;
        }

        errorAplicationDialog = new ErrorAplicationDialog(getString(R.string.Error), text, getString(R.string.Again), R.drawable.ic_error, R.color.colorError, () -> {
            p_libraryFragment.start(txt_Search.getText().toString(), page);
            errorAplicationDialog.dismiss();
        });
        errorAplicationDialog.show(getActivity().getSupportFragmentManager(), ErrorAplicationDialog.TAG);
    }

    @Override
    public void onHideAll() {
        loading.setVisibility(View.GONE);
        recycler.setVisibility(View.GONE);
        empty.setVisibility(View.GONE);
    }

    @Override
    public void onFinish() {
        loading.stopShimmerAnimation();
        isLoading = false;
    }

    @Override
    public void onLoading(boolean load) {
        if (load) {
            loading.setVisibility(View.VISIBLE);
        } else {
            loading.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSuccess() {
        recycler.setVisibility(View.VISIBLE);
        reload.setRefreshing(false);
    }

    //در اینجا کتاب ها در رسایکلر ست می شوند
    @Override
    public void onLibraryItem(VM_Library library) {
        libraryAdapter.add(library);
    }

    //مربوط به لودینگ رسایکلر
    @Override
    public void onLoadingPaging(boolean load) {
        if (load) {
            progress_paging.setVisibility(View.VISIBLE);
        } else {
            progress_paging.setVisibility(View.INVISIBLE);
        }
    }

    //در صورت نبود کتاب این متد فراخوانی می شود
    @Override
    public void onEmptyItem() {
        empty.setVisibility(View.VISIBLE);
    }

    //در اینجا کتاب نمایش داده می شود
    @Override
    public void onShowPDF(String pdfName) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), pdfName);

        if (file.exists()) {

            try {
                String extension = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(file).toString());
                String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_NEW_TASK);
                Uri uri = FileProvider.getUriForFile(getContext(), getActivity().getApplicationContext().getPackageName() + ".provider", file);

                intent.setDataAndType(uri, mimeType);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                startActivity(Intent.createChooser(intent, getString(R.string.ChoseApp)));
            } catch (Exception e) {
                Toast.makeText(getContext(), getString(R.string.There_Was_an_Error_In_The_Application), Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(getContext(), getString(R.string.notFoundThisPDF), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoadingDownloadPDF(boolean load) {
        if (load) {
            loading_Download.setVisibility(View.VISIBLE);
        } else {
            loading_Download.setVisibility(View.GONE);
        }
    }

    @Override
    public void onErrorDownloadPDF() {
        Toast.makeText(getContext(), getString(R.string.There_Was_an_Error_In_The_Application), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Search:
                page = 0;
                p_libraryFragment.start(txt_Search.getText().toString(), page);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        p_libraryFragment.Cancel(TAG);
    }
}
