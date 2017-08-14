package example.com.passwordmanagerinitial.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import example.com.passwordmanagerinitial.MainActivity;
import example.com.passwordmanagerinitial.MyApplication;
import example.com.passwordmanagerinitial.R;
import example.com.passwordmanagerinitial.database.MyDatabaseHelper;
import example.com.passwordmanagerinitial.util.ExitApplicationUtil;
import example.com.passwordmanagerinitial.util.SoftInputUtil;

/**
 * Created by Administrator on 2017/7/13.
 */

public class UpdateAccPassActy extends AppCompatActivity {
    
    private TextView descTv;
    private EditText passwordEt;
    private Button ensureBtn;
    private TextView titleTv;
    private ImageView eyeIv;

    private ExitApplicationUtil exitApplicationUtil;
    private MyDatabaseHelper dbHelper;

    private Context context;
    private Cursor cursor;

    private String account;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);

        context = MyApplication.getContext();

        exitApplicationUtil = ExitApplicationUtil.getInstance();
        exitApplicationUtil.addActivity(this);

        dbHelper = new MyDatabaseHelper(context,"PasswordStore.db",null,1);

        initView();
        initListener();
    }

    private void initListener() {

        ensureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String sql;

                cursor = db.rawQuery("select * from Bank",null);
                sql = "update Bank set password = ?";
                updateData(cursor,db,sql);

                cursor = db.rawQuery("select * from webSet",null);
                sql = "update webSet set password = ?";
                updateData(cursor,db,sql);

                cursor = db.rawQuery("select * from Cloud",null);
                sql = "update Cloud set password = ?";
                updateData(cursor,db,sql);

                cursor = db.rawQuery("select * from database",null);
                sql = "update database set password = ?";
                updateData(cursor,db,sql);

                cursor = db.rawQuery("select * from entertainment",null);
                sql = "update entertainment set password = ?";
                updateData(cursor,db,sql);

                cursor = db.rawQuery("select * from often",null);
                sql = "update often set password = ?";
                updateData(cursor,db,sql);

                cursor = db.rawQuery("select * from other",null);
                sql = "update other set password = ?";
                updateData(cursor,db,sql);

                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });

        eyeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordEt.getInputType() == InputType.TYPE_CLASS_TEXT){
                    passwordEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else {
                    passwordEt.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }
        });

    }

    private void initView() {
        Intent intent =getIntent();
        descTv = (TextView)this.findViewById(R.id.tv_show_desc_modify);
        passwordEt = (EditText)this.findViewById(R.id.et_show_password_modify);
        ensureBtn = (Button)this.findViewById(R.id.btn_yes_modify);

        account = intent.getStringExtra("account");
        descTv.setText(account);

        titleTv = (TextView)this.findViewById(R.id.tv_title_describe);
        titleTv.setText("更新账户密码");

        eyeIv = (ImageView)this.findViewById(R.id.iv_eye_modify);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        SoftInputUtil softUtil = new SoftInputUtil();
        softUtil.HideSoftInput(this,event);
        return super.onTouchEvent(event);
    }

    private void updateData(Cursor cursor,SQLiteDatabase db,String sql){

        String newPassword = passwordEt.getText().toString();
        String cursorAccount = null;

        if (cursor.moveToFirst()){
            do {
                cursorAccount = cursor.getString(cursor.getColumnIndex("account"));
            }while (cursor.moveToNext());
        }
        if (account.equals(cursorAccount)){
            db.execSQL(sql,new String[]{newPassword});
        }
        cursor.close();
    }
}
