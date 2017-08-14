package example.com.passwordmanagerinitial.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import example.com.passwordmanagerinitial.R;
import example.com.passwordmanagerinitial.database.MyDatabaseHelper;
import example.com.passwordmanagerinitial.util.ExitApplicationUtil;
import example.com.passwordmanagerinitial.util.SoftInputUtil;

/**
 * Created by Administrator on 2017/6/30.
 */

public class RegisterActivity extends AppCompatActivity {

    private EditText etMainPassword;
    private Button btnRegister;

    private SoftInputUtil softUtil;
    private MyDatabaseHelper dbHelper;

    private ExitApplicationUtil appUtil;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        appUtil = ExitApplicationUtil.getInstance();
        appUtil.addActivity(this);

        dbHelper = new MyDatabaseHelper(this,"PasswordStore.db",null,1);
        etMainPassword = (EditText)this.findViewById(R.id.et_main_password);
        btnRegister = (Button)this.findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("main_password",etMainPassword.getText().toString());
                db.insert("mainPassword",null,values);
                Intent intent = new Intent(RegisterActivity.this,VerifyActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //点击空白处隐藏键盘
        softUtil = new SoftInputUtil();
        softUtil.HideSoftInput(this,event);
        return super.onTouchEvent(event);
    }
}
