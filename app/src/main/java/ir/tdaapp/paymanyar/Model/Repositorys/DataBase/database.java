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
    public final String dbname="BisanDB.db";
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
        super(context,"BisanDB.db",null,2);
        mycontext=context;
        try {
            this.databse();
            this.isDBOpen=false;
        }catch (Exception e){
        }
    }

    public database(DbNeedPermission permission,Context context){
        super(context,"BisanDB.db",null,2);
        mycontext=context;
        try {
            this.needPermission=permission;
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
            InputStream myinput = mycontext.getAssets().open("BisanDB.db");
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
    public static String QRY_Eshtal_Item="select  tbl_eshtal_cols_related.id,tbl_eshtal_cols.name,tbl_eshtal_cols.unit,tbl_eshtal_values.col_id,tbl_eshtal_values.value,tbl_eshtal_values.parent from tbl_eshtal_values inner join tbl_eshtal_cols_related on tbl_eshtal_values.col_id=tbl_eshtal_cols_related.id inner join tbl_eshtal_cols on tbl_eshtal_cols_related.cols_id=tbl_eshtal_cols.id where tbl_eshtal_cols_related.eshtal_id=#1# and (tbl_eshtal_values.id=#2# OR tbl_eshtal_values.parent=#3#);";
    public static String QRY_INSERT_USER_INFO="insert into tbl_user( phone , activation , user_id ) values( '@i1' ,1, '@i2' )";

    //*********** Setting Queries
    public static String QRY_GET_SETTING_INFO="select * from tbl_setting";
    public static String QRY_UPDATE_SETTING_INFO_FIELD="update tbl_setting set @f1 = '@i1' where 1";

    //*********** Favorite Queries
    public static String QRY_GET_FAVORITE_INFO_BY_ID="select * from tbl_favorite where product_id= '@i1' ";
    public static String QRY_DELETE_FAVORITE_INFO_BY_ID="delete from tbl_favorite where product_id= '@i1' ";
    public static String QRY_GET_FAVORITE_INFO="select * from tbl_favorite";
    public static String QRY_INSERT_FAVORITE_INFO="insert into tbl_favorite(product_id) values( '@i1' )";

    //*********** Basket Queries
    public static String QRY_GET_BASKET_INFO_BY_ID="select * from tbl_basket where product_id= '@i1' ";
    public static String QRY_UPDATE_BASKET_INFO_INCREASE=" update tbl_basket set count=count+1 where product_id= '@i1' ";
    public static String QRY_INSERT_BASKET_INFO="insert into tbl_basket(product_id,count) values( '@i1' ,1)";
    public static String QRY_UPDATE_BASKET_INFO_DECREASE=" update tbl_basket set count=count-1 where product_id= '@i1' ";
    public static String QRY_GET_BASKET_INFO="select * from tbl_basket";
    public static String QRY_DELETE_BASKET_INFO_BY_ID="delete from tbl_basket where product_id= '@i1' ";
    public static String QRY_DELETE_BASKET_INFO="delete from tbl_basket";

    //*********** Product Queries
    public static String QRY_GET_PRODUCT_INFO_BY_TYPE="select * from tbl_products where type= '@i1' ";
    public static String QRY_GET_PRODUCT_INFO="select * from tbl_products";
    public static String QRY_DELETE_PRODUCT_INFO="delete from tbl_products";
    public static String QRY_DELETE_PRODUCT_INFO_BY_TYPE="delete from tbl_products where type = '@i1' ";
    public static String QRY_INSERT_PRODUCT_INFO="insert into tbl_products(product_id,name,address,type,price) values( '@i1' , '@i2' , '@i3' , '@i4' , '@i5' )";

    //*********** Category Queries
    public static String QRY_GET_CATEGORY_INFO="select * from tbl_category  ";
    public static String QRY_DELETE_CATEGORY_INFO="delete from tbl_category  ";
    public static String QRY_INSERT_CATEGORY_INFO="insert into tbl_category(name,category_id,address) values( '@i1' , '@i2' , '@i3' )";


}
