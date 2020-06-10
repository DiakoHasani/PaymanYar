package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.text.DecimalFormat;
import java.util.List;

import ir.tdaapp.paymanyar.Model.Services.resultVersionApp_Sql;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_UpdateApp;

public class Tbl_Setting {
    DBAdapter db;
    Context context;

    public Tbl_Setting(Context context) {
        this.context = context;
        db = DBAdapter.getInstance(context);
    }

    //در اینجا کویری های جدید اسکیوال اعمال می شوند و لازم بودن آپدیت اپلیکیشن اعلام می شود
    public void setUpdates(List<VM_UpdateApp> updates, resultVersionApp_Sql res) {

        Cursor q=db.ExecuteQ("select * from Tbl_Setting where id=1");
        float versionApp;
        float versionSql;

        //اگر مقدار زیر ترو شود برنامه نیاز به آپدیت خواهد داشت
        boolean isUpdateApp = false;

        //در اینجا اجباری بودن آپدیت ست می شود
        boolean hadUpdate = false;

        try{

            for (int i = 0; i < updates.size(); i++) {

                res.clearData(updates.get(i).isCleardate());

                versionApp =Float.valueOf(String.valueOf(q.getInt(q.getColumnIndex("versionApp"))));
                versionSql =Float.valueOf(String.valueOf(q.getInt(q.getColumnIndex("versionSql"))));

                if (updates.get(i).getAppVesrsion() > versionApp) {
                    isUpdateApp = true;

                    //در اینجا عدد پشت ممیز گرفته می شود
                    //در اینجا بخش صحیح ورژن اپلیکیشن از سرور گرفته می شود
                    float f=Float.valueOf(String.valueOf(updates.get(i).getAppVesrsion()));
                    int first = ((int)f);

                    //در اینجا بخش صحیح ورژن اپلیکیشن در دیتابیس گرفته می شود
                    int second = ((int)versionApp);

                    //اگر ورژن اپ سرور از دیتابیس بزرگتر باشد یعنی آپدیت اجباری است
                    if (first>second) {
                        hadUpdate = true;
                    }

                }

                if (Float.valueOf(String.valueOf(updates.get(i).getVesrsion())) > versionSql) {
                    try {
                        db.ExecuteQ(updates.get(i).getQuery());
                        db.ExecuteQ("update Tbl_Setting set versionSql=" + Double.valueOf(versionSql) + " where id=1");
                    } catch (Exception e) {
                    }
                }

            }

            res.updateApp(isUpdateApp, hadUpdate);

        }catch (Exception e){
            res.updateApp(isUpdateApp, false);
        }
    }
}
