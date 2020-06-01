package ir.tdaapp.paymanyar.Model.Services;

import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.fragment.app.Fragment;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;

public interface S_MainActivity {
    void OnStart();
    void onAddFragment(Fragment fragment, @AnimatorRes @AnimRes int animEnter,@AnimatorRes @AnimRes int animExit, boolean backStack, String fragmentTAG);
}
