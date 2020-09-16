package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ir.tdaapp.paymanyar.Model.ViewModels.VM_NewsPaperFilter;

//مربوط به جدول فیلتر روزنامه ها
public class Tbl_NewsPaper {
    DBAdapter db;
    Context context;

    public Tbl_NewsPaper(Context context) {
        this.context = context;
        db = DBAdapter.getInstance(context);
    }

    /**
     * در اینجا لیست فیلتر های روزنامه پاس داده می شوند
     **/
    public List<VM_NewsPaperFilter> getNewsPaperFilters() {
        List<VM_NewsPaperFilter> vals = new ArrayList<>();

        Cursor q = db.ExecuteQ("select * from Tbl_NewsPaper");

        for (int i = 0; i < q.getCount(); i++) {

            try {
                VM_NewsPaperFilter newsPaperFilter = new VM_NewsPaperFilter();

                newsPaperFilter.setId(q.getInt(q.getColumnIndex("id")));
                newsPaperFilter.setTitle(q.getString(q.getColumnIndex("title")));

                vals.add(newsPaperFilter);
            } catch (Exception e) {
            }

            q.moveToNext();
        }

        return vals;
    }
}
