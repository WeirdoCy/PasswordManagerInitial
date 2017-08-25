package example.com.passwordmanagerinitial.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import example.com.passwordmanagerinitial.R;
import example.com.passwordmanagerinitial.adapter.FloatListAdapter;
import example.com.passwordmanagerinitial.entity.PasswordListEntity;
import example.com.passwordmanagerinitial.util.ExitApplicationUtil;

/**
 * Created by Administrator on 2017/6/29.
 */

public class FloatListActivity extends AppCompatActivity {

    private List<PasswordListEntity> passwordList;

    private RecyclerView recyclerView;
    private FloatListAdapter adapter;
    private ExitApplicationUtil exitApplicationUtil;

    private ExitApplicationUtil appUtil;

    private WindowManager manager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_float_list);

        exitApplicationUtil = ExitApplicationUtil.getInstance();
        exitApplicationUtil.addActivity(this);

        manager = getWindowManager();
        Display display = manager.getDefaultDisplay();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.height = (int) (display.getHeight() * 0.95);
        layoutParams.width = (int) (display.getWidth() * 0.8);
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        getWindow().setAttributes(layoutParams);

        appUtil = ExitApplicationUtil.getInstance();
        appUtil.addActivity(this);
        initView();
        initListenerEvent();
    }

    private void initListenerEvent() {

        adapter.setOnItemClickListener(new FloatListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(FloatListActivity.this,BankCardActivity.class);
                intent.putExtra("account",passwordList.get(position).getPassDescribe());
                startActivity(intent);
            }
        });
    }

    private void initView() {
        recyclerView = (RecyclerView)this.findViewById(R.id.recycler_float);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();
        adapter = new FloatListAdapter(passwordList,this);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        passwordList = new ArrayList<PasswordListEntity>();
        PasswordListEntity bank = new PasswordListEntity("银行",R.mipmap.bank);
        passwordList.add(bank);
        PasswordListEntity webSet = new PasswordListEntity("网站",R.mipmap.webset);
        passwordList.add(webSet);
        PasswordListEntity cloud = new PasswordListEntity("云服务",R.mipmap.cloud);
        passwordList.add(cloud);
        PasswordListEntity database = new PasswordListEntity("数据库",R.mipmap.database);
        passwordList.add(database);
        PasswordListEntity yule = new PasswordListEntity("娱乐",R.mipmap.youtube);
        passwordList.add(yule);
        PasswordListEntity changyong = new PasswordListEntity("常用密码",R.mipmap.wechat);
        passwordList.add(changyong);
        PasswordListEntity other = new PasswordListEntity("其他",R.mipmap.other);
        passwordList.add(other);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null){
                exitApplicationUtil.removeActivity(this);
            }
        }
        return super.onTouchEvent(event);
    }
}
