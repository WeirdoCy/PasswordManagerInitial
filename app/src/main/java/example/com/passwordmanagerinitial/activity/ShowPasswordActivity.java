package example.com.passwordmanagerinitial.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import example.com.passwordmanagerinitial.MyApplication;
import example.com.passwordmanagerinitial.R;
import example.com.passwordmanagerinitial.database.MyDatabaseHelper;
import example.com.passwordmanagerinitial.util.ClicpUtil;
import example.com.passwordmanagerinitial.util.ExitApplicationUtil;
import example.com.passwordmanagerinitial.util.OpenPopupWindow;

/**
 * Created by Administrator on 2017/7/10.
 */

public class ShowPasswordActivity extends AppCompatActivity {

    private TextView titleTv;
    private TextView showDescTv;
    private TextView showPasswordTv;
    private ImageView eyeIv;

    private MyDatabaseHelper dbHelper;
    private ExitApplicationUtil appUtil;
    private OpenPopupWindow openPopup;

    private Cursor cursor;
    private Context context;

    private boolean isClick = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_password);

        context = MyApplication.getContext();

        appUtil = ExitApplicationUtil.getInstance();
        appUtil.addActivity(this);

        dbHelper = new MyDatabaseHelper(this,"PasswordStore.db",null,1);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        showPasswordTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPopup = new OpenPopupWindow();
                View popView = LayoutInflater.from(context).inflate(R.layout.pop_search_activity,null,false);
                openPopup.showPopWindow(showPasswordTv,popView);

                TextView copyTv = (TextView)popView.findViewById(R.id.tv_update);
                copyTv.setText("复制");

                copyTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String copy = showPasswordTv.getText().toString();
                        ClicpUtil clicpUtil = new ClicpUtil(context);
                        clicpUtil.copyText(copy);
                        Toast.makeText(ShowPasswordActivity.this, "复制成功", Toast.LENGTH_SHORT).show();
                        openPopup.hidePopupWindow();
                    }
                });

                TextView text = (TextView)popView.findViewById(R.id.tv_delete);
                if (isClick){
                    text.setText("显示");
                    text.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showPasswordTv.setInputType(InputType.TYPE_CLASS_TEXT);
                            openPopup.hidePopupWindow();
                        }
                    });
                    isClick = !isClick;
                }else {
                    text.setText("隐藏");
                    text.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showPasswordTv.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            openPopup.hidePopupWindow();
                        }
                    });
                    isClick = !isClick;
                }
            }
        });

        showDescTv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                openPopup = new OpenPopupWindow();
                View popupWindow = LayoutInflater.from(context).inflate(R.layout.pop_layout,null,false);
                openPopup.showPopWindow(showDescTv,popupWindow);
                RelativeLayout relativeLayout = (RelativeLayout)popupWindow.findViewById(R.id.rl_pop_layout);
                relativeLayout.setBackgroundResource(R.mipmap.topic_up_left);
                TextView textView = (TextView)popupWindow.findViewById(R.id.tv_exit);
                textView.setText("复制");

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ClicpUtil util = new ClicpUtil(context);
                        util.copyText(showDescTv.getText().toString());
                        Toast.makeText(context, "复制成功", Toast.LENGTH_SHORT).show();
                        openPopup.hidePopupWindow();
                    }
                });
                return true;
            }
        });

        eyeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showPasswordTv.getInputType() == InputType.TYPE_CLASS_TEXT){
                    showPasswordTv.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else {
                    showPasswordTv.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }
        });
    }

    private void initView() {

        titleTv = (TextView)this.findViewById(R.id.tv_title_describe);
        titleTv.setText("查看密码");
        showDescTv = (TextView)this.findViewById(R.id.tv_show_desc);
        showPasswordTv = (TextView)this.findViewById(R.id.tv_show_password);

        eyeIv = (ImageView)this.findViewById(R.id.iv_eye);
    }

    private void initData(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        cursor = db.query("often",null,null,null,null,null,null);
        queryData(cursor);

        cursor = db.query("webSet",null,null,null,null,null,null);
        queryData(cursor);

        cursor = db.query("Cloud",null,null,null,null,null,null);
        queryData(cursor);

        cursor = db.query("database",null,null,null,null,null,null);
        queryData(cursor);

        cursor = db.query("entertainment",null,null,null,null,null,null);
        queryData(cursor);

        cursor = db.query("Bank",null,null,null,null,null,null);
        queryData(cursor);

        cursor = db.query("other",null,null,null,null,null,null);
        queryData(cursor);
    }

    private void queryData(Cursor cursor){
        Intent intent = getIntent();
        String account = intent.getStringExtra("position");
        String desc = intent.getStringExtra("desc");
        Log.d("TAG", "queryData: " + desc);
        if (cursor.moveToFirst()){
            do {
                String name = cursor.getString(cursor.getColumnIndex("account"));
                String password = cursor.getString(cursor.getColumnIndex("password"));
                if (name.equals(account)){
                    showDescTv.setText(name);
                    showPasswordTv.setText(password);
                }
                if (name.equals(desc)){
                    showDescTv.setText(name);
                    showPasswordTv.setText(password);
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
    }
}
