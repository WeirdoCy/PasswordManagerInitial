package example.com.passwordmanagerinitial.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import example.com.passwordmanagerinitial.MainActivity;
import example.com.passwordmanagerinitial.R;
import example.com.passwordmanagerinitial.database.MyDatabaseHelper;
import example.com.passwordmanagerinitial.util.ExitApplicationUtil;

/**
 * Created by Administrator on 2017/6/29.
 */

public class LoginActivity extends AppCompatActivity {

    private ExitApplicationUtil appUtil;
    private MyDatabaseHelper dbHelper;

    private EditText passwordEt;
    private Button loginBtn;

    private String password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        appUtil = ExitApplicationUtil.getInstance();
        appUtil.addActivity(this);

        dbHelper = new MyDatabaseHelper(this,"PasswordStore.db",null,1);
        initView();
        initListener();
    }

    private void initListener() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("mainPassword",null,null,null,null,null,null);
                if (cursor.moveToFirst()){
                    do {
                        password = cursor.getColumnName(cursor.getColumnIndex("main_password"));
                    }while (cursor.moveToNext());
                }
                cursor.close();
                if (passwordEt.getText().toString().equals(password)){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        passwordEt = (EditText)this.findViewById(R.id.et_in_main_password);
        loginBtn = (Button)this.findViewById(R.id.btn_login);
    }
}
