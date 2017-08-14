package example.com.passwordmanagerinitial.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import example.com.passwordmanagerinitial.R;
import example.com.passwordmanagerinitial.database.MyDatabaseHelper;
import example.com.passwordmanagerinitial.util.ExitApplicationUtil;

/**
 * Created by Administrator on 2017/6/30.
 */

public class OnlyOnceActivity extends AppCompatActivity {

    private Button createBtn;
    private MyDatabaseHelper dbHelper;

    private ExitApplicationUtil appUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only_once);

        appUtil = ExitApplicationUtil.getInstance();
        appUtil.addActivity(this);

        createBtn = (Button)this.findViewById(R.id.btn_create);
        dbHelper = new MyDatabaseHelper(this,"PasswordStore.db",null,1);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
                Intent intent = new Intent(OnlyOnceActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
