package example.com.passwordmanagerinitial.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import example.com.passwordmanagerinitial.MainActivity;
import example.com.passwordmanagerinitial.R;
import example.com.passwordmanagerinitial.database.MyDatabaseHelper;
import example.com.passwordmanagerinitial.util.ExitApplicationUtil;
import example.com.passwordmanagerinitial.util.SoftInputUtil;

/**
 * Created by Administrator on 2017/7/4.
 */

public class AddPasswordActivity extends AppCompatActivity {

    private TextView titleTv;
    private EditText accountEt,passwordEt;
    private Button ensureBtn;

    private ExitApplicationUtil exitApplicationUtil;
    private SoftInputUtil inputUtil;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_password);
        exitApplicationUtil = ExitApplicationUtil.getInstance();
        exitApplicationUtil.addActivity(this);
        dbHelper = new MyDatabaseHelper(this,"PasswordStore.db",null,1);

        initView();
        initListener();
    }

    private void initListener() {

        ensureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String account = intent.getStringExtra("account");

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("account",accountEt.getText().toString());
                values.put("password",passwordEt.getText().toString());

                if (account.equals("银行")){
                    db.insert("Bank",null,values);
                }else if (account.equals("网站")){
                    db.insert("webSet",null,values);
                }else if (account.equals("云服务")){
                    db.insert("Cloud",null,values);
                }else if (account.equals("数据库")){
                    db.insert("database",null,values);
                }else if (account.equals("娱乐")){
                    db.insert("entertainment",null,values);
                }else if (account.equals("常用密码")){
                    db.insert("often",null,values);
                }else{
                    db.insert("other",null,values);
                }

                Intent intentToMainActivity = new Intent(AddPasswordActivity.this, MainActivity.class);
                intentToMainActivity.putExtra("desc",account);
                startActivity(intentToMainActivity);
            }
        });
    }

    private void initView() {
        titleTv = (TextView)this.findViewById(R.id.tv_title_describe);

        accountEt = (EditText)this.findViewById(R.id.et_account);
        passwordEt = (EditText)this.findViewById(R.id.et_password);

        ensureBtn = (Button)this.findViewById(R.id.btn_ensure);

        titleTv.setText("添加管理项");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        inputUtil = new SoftInputUtil();
        inputUtil.HideSoftInput(AddPasswordActivity.this,event);
        return super.onTouchEvent(event);
    }
}
