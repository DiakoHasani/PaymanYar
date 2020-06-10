package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_SavesGps;

//مربوط به جدول نقشه های ذخیره شده می باشد
public class Tbl_SavesGps {
    Context context;
    DBAdapter dbAdapter;

    public Tbl_SavesGps(Context context) {
        this.context = context;
        dbAdapter = DBAdapter.getInstance(context);
    }

    public void removeItem(String id) {
        dbAdapter.ExecuteQ("delete from tbl_gps where id=" + id);
    }

    public ArrayList<VM_SavesGps> getAll() {

        ArrayList<VM_SavesGps> vals = new ArrayList<>();
        Cursor q = dbAdapter.ExecuteQ("select * from tbl_gps");

        for (int i = 0; i < q.getCount(); i++) {

            try{

                VM_SavesGps item=new VM_SavesGps();
                item.setId(q.getString(q.getColumnIndex("id")));
                item.setLength(q.getString(q.getColumnIndex("lat")));
                item.setWide(q.getString(q.getColumnIndex("lon")));

                vals.add(item);
                q.moveToNext();

            }catch (Exception e){
            }

        }

        return vals;

    }

    public void saveLocation(String lat,String lon){

        if(!hasGPS(lat,lon)){

            //if There is NO location like these so we Save it in database
            dbAdapter.ExecuteQ("insert into tbl_gps(lat,lon) values("+lat+","+lon+")");

        }

    }

    public boolean hasGPS(String lat,String lon){
        Cursor q = dbAdapter.ExecuteQ("select COUNT(id) from tbl_gps where lat="+lat+" and lon="+lon);

        if (q.getString(0) != null) {
            return q.getInt(0) > 0;
        } else {
            return false;
        }
    }

}
