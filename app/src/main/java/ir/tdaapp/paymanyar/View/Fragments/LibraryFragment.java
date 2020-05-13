package ir.tdaapp.paymanyar.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.shimmer.ShimmerFrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Adapters.LibraryAdapter;
import ir.tdaapp.paymanyar.Model.Services.S_LibraryFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Library;
import ir.tdaapp.paymanyar.Presenter.P_LibraryFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Dialogs.ErrorAplicationDialog;

//مربوط به صفحه کتابخانه
public class LibraryFragment extends BaseFragment implements S_LibraryFragment {

    public static final String TAG="LibraryFragment";

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.library_fragment, container, false);

        findItem(view);
        implement();

        p_libraryFragment.start(txt_Search.getText().toString(), page);

        return view;
    }

    void findItem(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        recycler = view.findViewById(R.id.recycler);
        loading = view.findViewById(R.id.loading);
        btn_Search = view.findViewById(R.id.btn_Search);
        txt_Search = view.findViewById(R.id.txt_Search);
    }

    void implement() {
        p_libraryFragment = new P_LibraryFragment(getContext(), this);
    }

    @Override
    public void OnStart() {
        loading.startShimmerAnimation();

        libraryAdapter = new LibraryAdapter(getContext());
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        recycler.setAdapter(libraryAdapter);
        recycler.setLayoutManager(layoutManager);
    }

    @Override
    public void onError(ResaultCode resalt) {

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
    }

    @Override
    public void onFinish() {
        loading.stopShimmerAnimation();
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
    }

    //در اینجا کتاب ها در رسایکلر ست می شوند
    @Override
    public void onLibraryItem(VM_Library library) {
        libraryAdapter.add(library);
    }
}
