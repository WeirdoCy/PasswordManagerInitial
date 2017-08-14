package example.com.passwordmanagerinitial.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

/**
 * Created by Administrator on 2017/7/13.
 */

public class UpdateMainPassActivity  extends AppCompatActivity{

    private EditText oldPassEt,newPassEt;
    private Button ensureBtn;
    private TextView titleTv;

    private MyDatabaseHelper dbHelper;
    private ExitApplicationUtil exitApplicationUtil;

    private Context context;
    private Cursor cursor;

    private String oldPassword,newPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_main);

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
                oldPassword = oldPassEt.getText().toString();
                newPassword = newPassEt.getText().toString();
                String password = null;
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                cursor = db.rawQuery("select * from mainPassword",null);
                if (cursor.moveToFirst()){
                    do {
                        password = cursor.getString(cursor.getColumnIndex("main_password"));
                    }while (cursor.moveToNext());
                }
                if (oldPassword.equals(password)){
                    db.execSQL("update mainPassword set main_password = ?", new String [] {newPassword});
                    Toast.makeText(context, "修改成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(context, "密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initView() {

        oldPassEt = (EditText)this.findViewById(R.id.et_old_pass);
        newPassEt = (EditText)this.findViewById(R.id.et_new_pass);
        ensureBtn = (Button)this.findViewById(R.id.btn_ensure_modify);

        titleTv = (TextView)this.findViewById(R.id.tv_title_describe);
        titleTv.setText("更新主密码");
        
    }
}
