package example.com.passwordmanagerinitial.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/6/29.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context mContext;

    private static final String CREATE_BANK = "create table Bank (" +
            "id integer primary key autoincrement," +
            "password text," +
            "account text)";

    private static final String CREATE_WEBSET = "create table webSet(" +
            "id integer primary key autoincrement," +
            "password text," +
            "account text)";

    private static final String CREATE_CLOUD = "create table Cloud(" +
            "id integer primary key autoincrement," +
            "password text," +
            "account text)";

    private static final String CREATE_DATABASE = "create table database(" +
            "id integer primary key autoincrement," +
            "password text," +
            "account text)";

    private static final String CREATE_ENTERTAINMENT = "create table entertainment(" +
            "id integer primary key autoincrement," +
            "password text," +
            "account text)";

    private static final String CREATE_OFTEN = "create table often(" +
            "id integer primary key autoincrement," +
            "password text," +
            "account text)";

    private static final String CREATE_OTHER = "create table other(" +
            "id integer primary key autoincrement," +
            "password text," +
            "account text)";

    private static final String CREATE_MAIN_PASSWORD = "create table mainPassword(" +
            "id integer primary key autoincrement," +
            "main_password text)";

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_BANK);
        db.execSQL(CREATE_CLOUD);
        db.execSQL(CREATE_DATABASE);
        db.execSQL(CREATE_ENTERTAINMENT);
        db.execSQL(CREATE_OFTEN);
        db.execSQL(CREATE_WEBSET);
        db.execSQL(CREATE_OTHER);
        db.execSQL(CREATE_MAIN_PASSWORD);
        Toast.makeText(mContext, "create successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
