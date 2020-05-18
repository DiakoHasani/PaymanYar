package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;

import java.util.ArrayList;
import java.util.List;

import ir.tdaapp.paymanyar.Model.Services.etcDataBase;

//در اینجا عملیاتی که مربوط به هیچ جدول خاصی نمی باشد انجام می شود مثلا آخرین آی دی تمامی جدول ها گرفته می شود
public class ETC {

    //در اینجا نام یک جدول می گیرد و آیدی بعدی آن را پاس می دهد
    public static void getId(DBExcute db, String tableName, etcDataBase i) {

        List<Integer> vals = new ArrayList<>();

        db.Read("select * from " + tableName + " order by Id desc", new RecordHolder()).RecordFound(record -> {

            try {
                vals.add(Integer.valueOf(record.get(0).value) + 1);
            } catch (Exception e) {
                vals.add(1);
            }
        }, () -> {
            i.getId(1);
        }, () -> {
            i.getId(vals.get(0));
        }, 1);

    }

}
