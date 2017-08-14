package example.com.passwordmanagerinitial.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import example.com.passwordmanagerinitial.MainActivity;
import example.com.passwordmanagerinitial.MyApplication;
import example.com.passwordmanagerinitial.R;
import example.com.passwordmanagerinitial.database.MyDatabaseHelper;
import example.com.passwordmanagerinitial.util.ExitApplicationUtil;
import example.com.passwordmanagerinitial.util.SoftInputUtil;

/**
 * Created by Administrator on 2017/6/30.
 */

public class VerifyActivity extends AppCompatActivity{

    private EditText verifyPasswordEt;
    private Button verifyBtn;
    private ImageView backIv;

    private MyDatabaseHelper dbHelper;
    private SoftInputUtil softUtil;
    private ExitApplicationUtil appUtil;

    private Context context;

    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        appUtil = ExitApplicationUtil.getInstance();
        appUtil.addActivity(this);

        context = MyApplication.getContext();

        dbHelper = new MyDatabaseHelper(this,"PasswordStore.db",null,1);

        verifyPasswordEt = (EditText)this.findViewById(R.id.et_verify);
        verifyBtn = (Button)this.findViewById(R.id.btn_verify);
        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("mainPassword",null,null,null,null,null,null);
                if (cursor.moveToFirst()){
                    do{
                        password = cursor.getString(cursor.getColumnIndex("main_password"));

                    }while (cursor.moveToNext());
                }
                cursor.close();
                if (password.equals(verifyPasswordEt.getText().toString())){
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(VerifyActivity.this, "密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backIv = (ImageView)this.findViewById(R.id.iv_back);
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appUtil.removeActivity(VerifyActivity.this);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        softUtil = new SoftInputUtil();
        softUtil.HideSoftInput(this,event);
        return super.onTouchEvent(event);
    }
}
