package example.com.passwordmanagerinitial;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import example.com.passwordmanagerinitial.activity.FloatListActivity;
import example.com.passwordmanagerinitial.activity.SearchActivity;
import example.com.passwordmanagerinitial.activity.SettingActivity;
import example.com.passwordmanagerinitial.activity.ShowPasswordActivity;
import example.com.passwordmanagerinitial.adapter.FloatListAdapter;
import example.com.passwordmanagerinitial.adapter.MainActivityAdapter;
import example.com.passwordmanagerinitial.database.MyDatabaseHelper;
import example.com.passwordmanagerinitial.entity.ParentEntity;
import example.com.passwordmanagerinitial.entity.PasswordListEntity;
import example.com.passwordmanagerinitial.util.ExitApplicationUtil;
import example.com.passwordmanagerinitial.view.RecyclerItemDivider;

import static example.com.passwordmanagerinitial.R.mipmap.bank;
import static example.com.passwordmanagerinitial.R.mipmap.other;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView imageView;
    private RecyclerView recyclerView;
    private LinearLayout linearLayout;
    private FloatingActionButton floatingBtn;

    private Context context;
    private ExitApplicationUtil exitApplicationUtil;

    private MyDatabaseHelper dbHelper;

    private List list;
    private MainActivityAdapter adapter;

    private Cursor cursor;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exitApplicationUtil = ExitApplicationUtil.getInstance();
        exitApplicationUtil.addActivity(this);

        context = MyApplication.getContext();

        dbHelper = new MyDatabaseHelper(this,"PasswordStore.db",null,1);
        db = dbHelper.getWritableDatabase();

        initView();
        initListener();
    }

    private void initListener() {
        imageView.setOnClickListener(this);
        floatingBtn.setOnClickListener(this);
    }

    private void initView() {

        linearLayout = (LinearLayout)this.findViewById(R.id.ll_main_one);

        recyclerView = (RecyclerView)this.findViewById(R.id.recycler_password_content);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.addItemDecoration(new RecyclerItemDivider(context,LinearLayout.VERTICAL));
//        initData();
        initTreeData();
        ParentEntity.ChildEntity child = new ParentEntity.ChildEntity();
        Log.d("TAG", "data: " + child.getName());
//        adapter = new AccountListAdapter(context,mList);
        adapter = new MainActivityAdapter(context,list);

        adapter.setOnItemClickListener(new MainActivityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (list.get(position) instanceof ParentEntity){
                    ParentEntity parent = (ParentEntity) list.get(position);
                    if ((position + 1) == list.size()){
                        adapter.addChild(position + 1,parent.getChildren());
                    }else{
                        if (list.get(position + 1) instanceof ParentEntity){
                            adapter.addChild(position + 1, parent.getChildren());
                        }else if (list.get(position + 1) instanceof ParentEntity.ChildEntity){
                            adapter.removeChild(position + 1,parent.getChildren().size());
                        }
                    }
                }else{

                    ParentEntity.ChildEntity child = (ParentEntity.ChildEntity) list.get(position);
                    Toast.makeText(context, "you click: " + child.getName(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(context, ShowPasswordActivity.class);
                    intent.putExtra("desc",child.getName());
                    startActivity(intent);
                }
            }
        });

        recyclerView.setAdapter(adapter);

//        if (mList.size() > 0){
//            linearLayout.setVisibility(View.GONE);
//            recyclerView.setVisibility(View.VISIBLE);
//        }
        if (list.size() > 0){
            linearLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        imageView = (ImageView)findViewById(R.id.iv_setting_left);
        floatingBtn = (FloatingActionButton)this.findViewById(R.id.floating_button);

        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setCheckedItem(R.id.search);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Intent intent;
                drawerLayout.closeDrawers();
                switch (item.getItemId()){
                    case R.id.search:
                        intent = new Intent(context, SearchActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.sort:
                        break;
                    case R.id.setting:
                        intent = new Intent(context, SettingActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

    }


    public void initTreeData(){

        list = new ArrayList();


        cursor = db.rawQuery("select * from Bank",null);
        if (cursor.moveToFirst()){
            ParentEntity parent = new ParentEntity();
            List<ParentEntity.ChildEntity> childList = new ArrayList<>();
            ParentEntity.ChildEntity bank ;
            String test;
            do {
                test = cursor.getString(cursor.getColumnIndex("account"));
                bank = new ParentEntity.ChildEntity(test);
                childList.add(bank);
            }while (cursor.moveToNext());
            if (bank != null){
                parent.setName("银行");
                parent.setChildren(childList);
                list.add(parent);

            }
        }

        cursor = db.rawQuery("select * from webSet",null);
        if (cursor.moveToFirst()){
            ParentEntity parent = new ParentEntity();
            ParentEntity.ChildEntity webSet = null;
            List<ParentEntity.ChildEntity> childList = new ArrayList<ParentEntity.ChildEntity>();
            do {
                webSet = new ParentEntity.ChildEntity(cursor.getString(cursor.getColumnIndex("account")));
                childList.add(webSet);

            }while (cursor.moveToNext());
            if (webSet != null){
                parent.setName("网站");
                parent.setChildren(childList);
                list.add(parent);
            }
        }


        cursor = db.rawQuery("select * from Cloud",null);
        if (cursor.moveToFirst()){
            ParentEntity parent = new ParentEntity();
            ParentEntity.ChildEntity cloudDate = null;
            List<ParentEntity.ChildEntity> childList = new ArrayList<ParentEntity.ChildEntity>();
            do {
                cloudDate = new ParentEntity.ChildEntity(cursor.getString(cursor.getColumnIndex("account")));
                childList.add(cloudDate);

            }while (cursor.moveToNext());
            if (cloudDate != null){
                parent.setName("云服务");
                parent.setChildren(childList);
                list.add(parent);
            }
        }



        cursor = db.rawQuery("select * from entertainment",null);
        if (cursor.moveToFirst()){
            ParentEntity parent = new ParentEntity();
            ParentEntity.ChildEntity entertainmentData = null;
            List<ParentEntity.ChildEntity> childList = new ArrayList<ParentEntity.ChildEntity>();
            do {
                entertainmentData = new ParentEntity.ChildEntity(cursor.getString(cursor.getColumnIndex("account")));
                childList.add(entertainmentData);

            }while (cursor.moveToNext());
            if (entertainmentData != null){
                parent.setName("娱乐");
                parent.setChildren(childList);
                list.add(parent);
            }
        }


        cursor = db.rawQuery("select * from database",null);
        if (cursor.moveToFirst()){
            ParentEntity parent = new ParentEntity();
            ParentEntity.ChildEntity child = new ParentEntity.ChildEntity();
            ParentEntity.ChildEntity databaseData = null;
            List<ParentEntity.ChildEntity> childList = new ArrayList<ParentEntity.ChildEntity>();
            do {
                databaseData = new ParentEntity.ChildEntity(cursor.getString(cursor.getColumnIndex("account")));
                childList.add(databaseData);

            }while (cursor.moveToNext());
            if (databaseData != null){
                parent.setName("数据库");
                parent.setChildren(childList);
                list.add(parent);
            }
        }


        cursor = db.rawQuery("select * from often",null);
        if (cursor.moveToFirst()){
            ParentEntity parent = new ParentEntity();
            ParentEntity.ChildEntity oftenData = null;
            List<ParentEntity.ChildEntity> childList = new ArrayList<ParentEntity.ChildEntity>();
            do {
                oftenData = new ParentEntity.ChildEntity(cursor.getString(cursor.getColumnIndex("account")));
                childList.add(oftenData);

            }while (cursor.moveToNext());
            if (oftenData != null){
                parent.setName("常用密码");
                parent.setChildren(childList);
                list.add(parent);
            }
        }

        cursor = db.rawQuery("select * from other",null);
        if (cursor.moveToFirst()){
            ParentEntity parent = new ParentEntity();
            ParentEntity.ChildEntity otherData = null;
            List<ParentEntity.ChildEntity> childList = new ArrayList<ParentEntity.ChildEntity>();
            do {
                otherData = new ParentEntity.ChildEntity(cursor.getString(cursor.getColumnIndex("account")));
                childList.add(otherData);

            }while (cursor.moveToNext());
            if (otherData != null){
                parent.setName("其他");
                parent.setChildren(childList);
                list.add(parent);
            }
        }

    }



    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.iv_setting_left:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.floating_button:
                Intent intent = new Intent(MainActivity.this,FloatListActivity.class);
                startActivity(intent);
                break;
        }

    }
}
