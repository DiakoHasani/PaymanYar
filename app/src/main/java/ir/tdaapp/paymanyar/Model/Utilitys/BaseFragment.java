package ir.tdaapp.paymanyar.Model.Utilitys;

import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {
    protected String paymentUrl = "http://tiptop.tdaapp.ir/";

    //در اینجا ورژن اپلیکیشن نگهداری می شود
    protected float versionApplication = 1;
}
