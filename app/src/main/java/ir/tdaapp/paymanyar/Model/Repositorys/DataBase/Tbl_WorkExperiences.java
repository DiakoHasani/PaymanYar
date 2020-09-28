package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ir.tdaapp.paymanyar.Model.ViewModels.VM_WorkExperience;
import ir.tdaapp.paymanyar.R;

public class Tbl_WorkExperiences {
    DBAdapter db;
    Context context;

    public Tbl_WorkExperiences(Context context) {
        this.context = context;
        db = DBAdapter.getInstance(context);
    }

    /**
     * در اینجا لیست سابقه کارها برگشت داد می شود
     **/
    public List<VM_WorkExperience> getWorkExperiences() {
        List<VM_WorkExperience> workExperiences = new ArrayList<>();

        try {

            VM_WorkExperience w = new VM_WorkExperience();
            w.setId(0);
            w.setTitle(context.getString(R.string.WorkExperience));
            workExperiences.add(w);

            Cursor q = db.ExecuteQ("select * from TblWorkExperiences");

            for (int i = 0; i < q.getCount(); i++) {
                VM_WorkExperience workExperience = new VM_WorkExperience();

                workExperience.setId(q.getInt(q.getColumnIndex("Id")));
                workExperience.setTitle(q.getString(q.getColumnIndex("Title")));

                workExperiences.add(workExperience);
                q.moveToNext();
            }
            q.close();

        } catch (Exception e) {
        } finally {
            return workExperiences;
        }
    }

    /**
     * در اینجا نام سابقه کار براساس آیدی آن برگشت داده می شود
     * **/
    public String getTitleById(int id) {
        try {

            String title;
            Cursor q = db.ExecuteQ("select Title from TblWorkExperiences where Id=" + id);
            title=q.getString(0);

            return title;

        } catch (Exception e) {
            return "";
        }
    }
}
