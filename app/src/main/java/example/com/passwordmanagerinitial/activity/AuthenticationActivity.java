package example.com.passwordmanagerinitial.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import example.com.passwordmanagerinitial.MainActivity;
import example.com.passwordmanagerinitial.MyApplication;
import example.com.passwordmanagerinitial.R;
import example.com.passwordmanagerinitial.database.MyDatabaseHelper;
import example.com.passwordmanagerinitial.util.ExitApplicationUtil;
import example.com.passwordmanagerinitial.util.SoftInputUtil;

/**
 * Created by Administrator on 2017/7/14.
 */

public class AuthenticationActivity extends AppCompatActivity {
    
    private ExitApplicationUtil exitApplicationUtil;
    private MyDatabaseHelper dbHelper;
    
    private EditText authEt;
    private Button authBtn;
    private TextView titleTv;
    
    private Context context;
    private Cursor cursor;

    private String cursorString;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        
        context = MyApplication.getContext();
        
        exitApplicationUtil = ExitApplicationUtil.getInstance();
        exitApplicationUtil.addActivity(this);

        dbHelper = new MyDatabaseHelper(context,"PasswordStore.db",null,1);

        initView();
        initListener();
    }

    private void initListener() {
        authBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent getPosition = getIntent();
                String account = getPosition.getStringExtra("account");

                String password = authEt.getText().toString();

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                cursor = db.rawQuery("select * from mainPassword",null);

                if (cursor.moveToFirst()){
                    do {
                        cursorString = cursor.getString(cursor.getColumnIndex("main_password"));

                    }while (cursor.moveToNext());
                }
                cursor.close();

                Intent getActy = getIntent();
                int numActy = getActy.getIntExtra("whereActy",0);

                switch(numActy){
                    case 1:
                        if (password.equals(cursorString)){
                            Intent intent = new Intent(context,UpdateAccPassActy.class);
                            intent.putExtra("account",account);
                            startActivity(intent);
                        }else{
                            Toast.makeText(context, "密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        if (password.equals(cursorString)){
                            cursor = db.rawQuery("select * from Bank",null);
                            if (cursor.moveToFirst()) {
                                do {
                                    String name = cursor.getString(cursor.getColumnIndex("account"));
                                    db.execSQL("delete from Bank where account = ?",new String[]{name});
                                }while (cursor.moveToNext());
                            }
                            cursor  = db.rawQuery("select * from webSet",null);
                            deleteDatabase(cursor,db,"delete from webSet where account = ?");

                            cursor  = db.rawQuery("select * from Cloud",null);
                            deleteDatabase(cursor,db,"delete from Cloud where account = ?");

                            cursor  = db.rawQuery("select * from database",null);
                            deleteDatabase(cursor,db,"delete from database where account = ?");

                            cursor  = db.rawQuery("select * from entertainment",null);
                            deleteDatabase(cursor,db,"delete from entertainment where account = ?");

                            cursor  = db.rawQuery("select * from often",null);
                            deleteDatabase(cursor,db,"delete from often where account = ?");

                            cursor  = db.rawQuery("select * from other",null);
                            deleteDatabase(cursor,db,"delete from other where account = ?");

                            Intent intent = new Intent(context, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(context, "数据库删除成功", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                        }

                        break;
                    case 3:
                        if (password.equals(cursorString)){
                            Intent getDesc = getIntent();
                            String desc = getDesc.getStringExtra("passDec");
                            db.execSQL("delete from Bank where account = ?", new
                                    String[]{desc});
                            db.execSQL("delete from webSet where account = ?", new
                                    String[]{desc});
                            db.execSQL("delete from Cloud where account = ?", new
                                    String[]{desc});
                            db.execSQL("delete from database where account = ?", new
                                    String[]{desc});
                            db.execSQL("delete from entertainment where account = ?", new
                                    String[]{desc});
                            db.execSQL("delete from often where account = ?", new
                                    String[]{desc});
                            db.execSQL("delete from other where account = ?", new
                                    String[]{desc});
                            Intent intent = new Intent(context,MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(context, "内容删除成功", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }

            }
        });
    }

    private void initView() {
        authBtn = (Button)this.findViewById(R.id.btn_auth);
        authEt = (EditText)this.findViewById(R.id.et_auth);
        titleTv = (TextView)this.findViewById(R.id.tv_title_describe);
        titleTv.setText("请输入主密码");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        SoftInputUtil util = new SoftInputUtil();
        util.HideSoftInput(this,event);
        return super.onTouchEvent(event);
    }

    private void deleteDatabase(Cursor cursor,SQLiteDatabase db,String sql){
        if (cursor.moveToFirst()){
            do {
                String name = cursor.getString(cursor.getColumnIndex("account"));
                db.execSQL(sql,new String[]{name});
            }while (cursor.moveToNext());
        }
        cursor.close();
    }
}
