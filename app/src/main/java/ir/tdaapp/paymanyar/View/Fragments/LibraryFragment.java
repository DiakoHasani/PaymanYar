package ir.tdaapp.paymanyar.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ir.tdaapp.paymanyar.Model.Services.S_LibraryFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Presenter.P_LibraryFragment;
import ir.tdaapp.paymanyar.R;

//مربوط به صفحه کتابخانه
public class LibraryFragment extends BaseFragment implements S_LibraryFragment {

    P_LibraryFragment p_libraryFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.library_fragment, container, false);

        findItem(view);
        implement();

        return view;
    }

    void findItem(View view){

    }

    void implement(){
        p_libraryFragment=new P_LibraryFragment(getContext(),this);
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onHideAll() {

    }

    @Override
    public void onFinish() {

    }
}
