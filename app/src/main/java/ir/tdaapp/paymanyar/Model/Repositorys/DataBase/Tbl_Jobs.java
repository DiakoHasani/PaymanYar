package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ir.tdaapp.paymanyar.Model.ViewModels.VM_Job;
import ir.tdaapp.paymanyar.R;

public class Tbl_Jobs {

    DBAdapter db;
    Context context;

    public Tbl_Jobs(Context context) {
        db = DBAdapter.getInstance(context);
        this.context = context;
    }

    /**
     * در اینجا لیست شغل ها برگشت داده می شود
     **/
    public List<VM_Job> getJobs() {

        List<VM_Job> jobs = new ArrayList<>();

        try {

            VM_Job j = new VM_Job();
            j.setId(0);
            j.setTitle(context.getString(R.string.job));
            jobs.add(j);

            Cursor cursor = db.ExecuteQ("select * from TblJobs");

            for (int i = 0; i < cursor.getCount(); i++) {

                try {

                    VM_Job job = new VM_Job();
                    job.setId(cursor.getInt(cursor.getColumnIndex("Id")));
                    job.setTitle(cursor.getString(cursor.getColumnIndex("Name")));

                    jobs.add(job);
                    cursor.moveToNext();
                } catch (Exception e) {
                }
            }

            cursor.close();

        } catch (Exception e) {
        } finally {
            return jobs;
        }
    }

    /**
     * در اینجا نام شغل بر اساس آیدی آن برگشت می دهد
     * **/
    public String getTitleById(int id) {
        try {

            String title;
            Cursor q = db.ExecuteQ("select Name from TblJobs where Id=" + id);
            title=q.getString(0);

            return title;

        } catch (Exception e) {
            return "";
        }
    }
}
