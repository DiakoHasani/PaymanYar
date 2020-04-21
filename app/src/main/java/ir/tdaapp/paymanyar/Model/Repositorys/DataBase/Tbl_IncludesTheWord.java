package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_IncludesTheWord;
import ir.tdaapp.paymanyar.R;

//مربوط به دیتبیس شامل کلمه
public class Tbl_IncludesTheWord {

    //در اینجا لیست شامل کلمه ها برگشت داده می شود
    public Single<List<VM_IncludesTheWord>> getIncludesTheWords(Context context) {
        return Single.create(emitter -> {

            new Thread(() -> {

                try {

                    List<VM_IncludesTheWord> includesTheWords = new ArrayList<>();
                    includesTheWords.add(new VM_IncludesTheWord(0, context.getResources().getString(R.string.IncludesTheWord)));
                    includesTheWords.add(new VM_IncludesTheWord(1,"کلمه 1"));
                    includesTheWords.add(new VM_IncludesTheWord(2,"کلمه 2"));
                    includesTheWords.add(new VM_IncludesTheWord(3,"کلمه 3"));
                    includesTheWords.add(new VM_IncludesTheWord(4,"کلمه 4"));
                    includesTheWords.add(new VM_IncludesTheWord(5,"کلمه 5"));

                    emitter.onSuccess(includesTheWords);

                } catch (Exception e) {
                    emitter.onError(e);
                }

            }).run();

        });
    }

}
