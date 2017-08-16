package example.com.passwordmanagerinitial.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import example.com.passwordmanagerinitial.MainActivity;
import example.com.passwordmanagerinitial.MyApplication;
import example.com.passwordmanagerinitial.R;
import example.com.passwordmanagerinitial.database.MyDatabaseHelper;
import example.com.passwordmanagerinitial.util.ExitApplicationUtil;

/**
 * Created by Administrator on 2017/6/8.
 */

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView exitText;
    private TextView titleTv;
    private TextView modifyTv;
    private TextView loginModeTv;
    private TextView deleteTv;

    private Context context;
    private Cursor cursor;

    private ExitApplicationUtil exitApplicationUtil;
    private MyDatabaseHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        context = MyApplication.getContext();

        exitApplicationUtil = ExitApplicationUtil.getInstance();
        exitApplicationUtil.addActivity(this);

        dbHelper = new MyDatabaseHelper(context,"PasswordStore.db",null,1);


        initView();
        initListener();
    }

    private void initListener() {
        modifyTv.setOnClickListener(this);
        exitText.setOnClickListener(this);
        loginModeTv.setOnClickListener(this);
        deleteTv.setOnClickListener(this);
    }

    private void initView() {
        exitText = (TextView)findViewById(R.id.tv_quit);

        titleTv = (TextView)this.findViewById(R.id.tv_title_describe);
        titleTv.setText("设置");

        modifyTv = (TextView)this.findViewById(R.id.tv_modify);

        loginModeTv = (TextView)this.findViewById(R.id.tv_login_mode);

        deleteTv = (TextView)this.findViewById(R.id.tv_delete_database);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.tv_quit:
//                exitApplicationUtil.removeActivity(this);
                exitApplicationUtil.removeAllActivity();
                break;
            case R.id.tv_modify:
                intent = new Intent(context,UpdateMainPassActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_login_mode:
                intent = new Intent(context,LoginModeActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_delete_database:
                AlertDialog.Builder dialog = new AlertDialog.Builder(SettingActivity.this);
                dialog.setTitle("Warning");
                dialog.setMessage("Are you sure to delete it?");
                dialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intentToAuth = new Intent(context, AuthenticationActivity.class);
                        intentToAuth.putExtra("whereActy",2);
                        startActivity(intentToAuth);

                    }

                });
                dialog.setNegativeButton("no",null);
                dialog.show();

                break;
        }
    }

}
