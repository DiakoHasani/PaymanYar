package ir.tdaapp.paymanyar.Model.Repositorys.DataBase;



import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.widget.Toast;

        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.OutputStream;
        import java.util.ArrayList;

        import ir.tdaapp.paymanyar.BuildConfig;

public class database extends SQLiteOpenHelper {

    /*************************************
     *
     * CLASS :: this Class Used to connect to Database and DO SUDI Works.
     * -------------------------------------
     *
     *
     ************************************/

    private static database sInstance;
    public final String path="data/data/"+ BuildConfig.APPLICATION_ID +"/databases/";
    public SQLiteDatabase mydb;
    public boolean isDBOpen;
    public final String dbname="DBpaymanyar.db";
    private final Context mycontext;
    private final String RandomString="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private DbNeedPermission needPermission;

    public interface DbNeedPermission{
        public void active();
    }

    public void setNeedPermission(DbNeedPermission needPermission) {
        this.needPermission = needPermission;
    }

    public database(Context context){
        super(context,"DBpaymanyar.db",null,1);
        mycontext=context;
        try {
            this.close();
            this.databse();
            this.isDBOpen=false;
        }catch (Exception e){
        }
    }

    public database(DbNeedPermission permission,Context context){
        super(context,"DBpaymanyar.db",null,1);
        mycontext=context;
        try {
            this.needPermission=permission;
            this.close();
            this.databse();
            this.isDBOpen=false;
        }catch (Exception e){
        }
    }

    public static synchronized database getInstance(Context context) {
        try {

            if (sInstance == null) {
                sInstance = new database(context.getApplicationContext());
            }
        }catch (Exception e){}
        return sInstance;
    }

    public static synchronized database getInstance(DbNeedPermission Permission,Context context) {
        try {
            if (sInstance == null) {
                sInstance = new database(Permission,context.getApplicationContext());
            }else{
                sInstance.needPermission=Permission;
            }
        }catch (Exception e){}
        return sInstance;
    }

    public void CloseInstance(){
        try{
            if(sInstance!=null){
                sInstance.finalize();
                super.finalize();
            }
        }catch (Throwable e){}
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //**************************** Method to Check Database Copied or not
    public void databse(){
        boolean check=checkdb();
        if(check){

        }else{
            try{

                this.getReadableDatabase();
                copydatabase();


            }catch(Exception e){

            }
        }
    }
    
    //**************************** Opening Database
    public void open(){
        try {
            if(this.isDBOpen){

            }else {
                this.isDBOpen=true;
                this.mydb = SQLiteDatabase.openDatabase(path + dbname, null, SQLiteDatabase.OPEN_READWRITE);
            }
        }catch (Exception e){
        }
    }

    //**************************** Closing Database
    public void close(){
        try {
            if(this.isDBOpen) {
                this.isDBOpen=false;
                this.mydb.close();
            }else{

            }
        }catch (Exception e){}
    }

    //**************************** Check that is Database in our path or not!
    public boolean checkdb(){
        SQLiteDatabase db = null;

        try {
            db = SQLiteDatabase.openDatabase(path + dbname, null, SQLiteDatabase.OPEN_READONLY);
        }catch (Exception e){

        }
        return db !=null ? true:false;
    }


    //**************************** Copying Database File to Android Data Folder
    public void copydatabase() throws IOException{
        try {
            OutputStream myoutput = new FileOutputStream(path + dbname);
            byte[] buffer = new byte[1024];
            int length;
            InputStream myinput = mycontext.getAssets().open("DBpaymanyar.db");
            while ((length = myinput.read(buffer)) > 0) {
                myoutput.write(buffer, 0, length);
            }
            myinput.close();
            myoutput.flush();
            myoutput.close();
        }catch (Exception e){
            if(this.needPermission!=null){
                this.needPermission.active();
            }
            Toast.makeText(this.mycontext.getApplicationContext(),"Copy:"+this.path,Toast.LENGTH_LONG).show();
        }
    }

    //-------------------------------------------------------------//
    //----------------------- Part User -----------------------//

    public void ExectueQuery(String qry){
        if(!mydb.isOpen())this.open();
        mydb.execSQL(qry);
    }

    public DBCursor CursorQuery(String qry){
        if(!mydb.isOpen())this.open();
        Cursor r=mydb.rawQuery(qry,null);
        return new DBCursor(r);
    }


    //*********** Eshtal Queries
    public static String QRY_Eshtal_INFO="select * from tbl_eshtal where id=#1# ";
    public static String QRY_Eshtal_Item="select  tbl_eshtal_cols_related.id,tbl_eshtal_cols.name,tbl_eshtal_cols.unit,tbl_eshtal_values.col_id,tbl_eshtal_values.value,tbl_eshtal_values.parent,tbl_eshtal_values.id from tbl_eshtal_values inner join tbl_eshtal_cols_related on tbl_eshtal_values.col_id=tbl_eshtal_cols_related.id inner join tbl_eshtal_cols on tbl_eshtal_cols_related.cols_id=tbl_eshtal_cols.id where tbl_eshtal_cols_related.eshtal_id=#1# and (tbl_eshtal_values.id=#2# OR tbl_eshtal_values.parent=#3#);";
    public static String QRY_Eshtal_Column_Values="select  tbl_eshtal_cols_related.id,tbl_eshtal_cols.name,tbl_eshtal_cols.unit,tbl_eshtal_values.col_id,tbl_eshtal_values.value,tbl_eshtal_values.parent,tbl_eshtal_values.id from tbl_eshtal_values inner join tbl_eshtal_cols_related on tbl_eshtal_values.col_id=tbl_eshtal_cols_related.id inner join tbl_eshtal_cols on tbl_eshtal_cols_related.cols_id=tbl_eshtal_cols.id where tbl_eshtal_values.col_id=#1#";
    public static String QRY_Eshtal_Update_LastID="update tbl_eshtal set lastID=#1# where id=#2# ";

    //*********** GPS Queries
    public static String QRY_GPS_GET_All="select * from tbl_gps";
    public static String QRY_ADD_NEW_LOCATION="insert into tbl_gps(lat,lon) values(#1#,#2#)";
    public static String QRY_GPS_CHECK_EXIST="select * from tbl_gps where lat=#1# and lon=#2#";
    public static String QRY_GPS_REMOVE_ITEM="delete from tbl_gps where id=#1#";


}
