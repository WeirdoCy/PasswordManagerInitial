package example.com.passwordmanagerinitial.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import example.com.passwordmanagerinitial.MyApplication;
import example.com.passwordmanagerinitial.R;
import example.com.passwordmanagerinitial.adapter.SearchActivityAdapter;
import example.com.passwordmanagerinitial.adapter.SecondSearchActivityAdapter;
import example.com.passwordmanagerinitial.database.MyDatabaseHelper;
import example.com.passwordmanagerinitial.entity.SearchListEntity;
import example.com.passwordmanagerinitial.util.DataUtil;
import example.com.passwordmanagerinitial.util.ExitApplicationUtil;
import example.com.passwordmanagerinitial.util.OpenPopupWindow;
import example.com.passwordmanagerinitial.util.SoftInputUtil;
import example.com.passwordmanagerinitial.view.RecyclerItemDivider;

/**
 * Created by Administrator on 2017/7/10.
 */

public class SearchActivity extends AppCompatActivity {

    private EditText searchEt;
    private ImageView deleteIv;
    private RecyclerView searchRecycler,testRecycler;
    private TextView titleTv;

    private Context context;
    private Cursor cursor;
    private SQLiteDatabase db;

    private MyDatabaseHelper dbHelper;
    private ExitApplicationUtil exitApplicationUtil;
    private SearchActivityAdapter adapter;
    private OpenPopupWindow openPop;
    private DataUtil dataUtil;
    private SecondSearchActivityAdapter secondAdapter;

    private List<SearchListEntity> mList;
    private String account;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        exitApplicationUtil = ExitApplicationUtil.getInstance();
        exitApplicationUtil.addActivity(this);

        context = MyApplication.getContext();

        dataUtil = new DataUtil();

        dbHelper = new MyDatabaseHelper(this, "PasswordStore.db", null, 1);
        db = dbHelper.getWritableDatabase();

        initView();
        initListener();
        initSearch();
    }

    private void initSearch() {
        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() == 0) {
                    deleteIv.setVisibility(View.GONE);
                } else {
                    deleteIv.setVisibility(View.VISIBLE);
                }

                if (!TextUtils.isEmpty(s.toString()) && s.toString() != "") {
                    Log.d("TAG", "afterTextChanged: " + s.toString());
                    testRecycler = (RecyclerView)findViewById(R.id.recycler_search);
//                    searchSetData(s);
//                    testRecycler.setLayoutManager(new LinearLayoutManager(context));
//                    testRecycler.addItemDecoration(new RecyclerItemDivider(context,LinearLayout.VERTICAL));
//                    secondAdapter = new SecondSearchActivityAdapter(context,mList);
//                    testRecycler.setAdapter(secondAdapter);

                    SearchListEntity entity = null;
                    String test = null;
                    mList = new ArrayList<SearchListEntity>();
                    cursor = db.rawQuery("select * from Bank where account like '%"+ s +"%'",null);
                    if (cursor.moveToFirst()){
                        do{
                            test =  cursor.getString(cursor.getColumnIndex("account"));
                            entity = new SearchListEntity(test);
                            mList.add(entity);
                        }while (cursor.moveToNext());
                    }
                    cursor.close();
//                    secondAdapter = new SecondSearchActivityAdapter(context,mList);
//                    searchRecycler.setAdapter(secondAdapter);
                    adapter = new SearchActivityAdapter(context,mList);
                    searchRecycler.setAdapter(adapter);

                    cursor = db.rawQuery("select * from webSet where account like '%"+ s +"%'",null);
                    if (cursor.moveToFirst()){
                        do{
                            test =  cursor.getString(cursor.getColumnIndex("account"));
                            entity = new SearchListEntity(test);
                            mList.add(entity);
                        }while (cursor.moveToNext());
                    }
                    cursor.close();
                    adapter = new SearchActivityAdapter(context,mList);
                    searchRecycler.setAdapter(adapter);

                    cursor = db.rawQuery("select * from Cloud where account like '%"+ s +"%'",null);
                    if (cursor.moveToFirst()){
                        do{
                            test =  cursor.getString(cursor.getColumnIndex("account"));
                            entity = new SearchListEntity(test);
                            mList.add(entity);
                        }while (cursor.moveToNext());
                    }
                    cursor.close();
                    adapter = new SearchActivityAdapter(context,mList);
                    searchRecycler.setAdapter(adapter);

                    cursor = db.rawQuery("select * from entertainment where account like '%"+ s +"%'",null);
                    if (cursor.moveToFirst()){
                        do{
                            test =  cursor.getString(cursor.getColumnIndex("account"));
                            entity = new SearchListEntity(test);
                            mList.add(entity);
                        }while (cursor.moveToNext());
                    }
                    cursor.close();
                    adapter = new SearchActivityAdapter(context,mList);
                    searchRecycler.setAdapter(adapter);

                    cursor = db.rawQuery("select * from often where account like '%"+ s +"%'",null);
                    if (cursor.moveToFirst()){
                        do{
                            test =  cursor.getString(cursor.getColumnIndex("account"));
                            entity = new SearchListEntity(test);
                            mList.add(entity);
                        }while (cursor.moveToNext());
                    }
                    cursor.close();
                    adapter = new SearchActivityAdapter(context,mList);
                    searchRecycler.setAdapter(adapter);

                    cursor = db.rawQuery("select * from other where account like '%"+ s +"%'",null);
                    if (cursor.moveToFirst()){
                        do{
                            test =  cursor.getString(cursor.getColumnIndex("account"));
                            entity = new SearchListEntity(test);
                            mList.add(entity);
                        }while (cursor.moveToNext());
                    }
                    cursor.close();
                    adapter = new SearchActivityAdapter(context,mList);
                    searchRecycler.setAdapter(adapter);
                }
                if (s.length() == 0){
//                    initData();
//                    mList = new ArrayList<SearchListEntity>();
//                    adapter = new SearchActivityAdapter(SearchActivity.this,mList);
//                    searchRecycler.setAdapter(adapter);
                    searchRecycler = (RecyclerView) findViewById(R.id.recycler_search);
                    initData();
                    searchRecycler.setLayoutManager(new LinearLayoutManager(context));
                    adapter = new SearchActivityAdapter(SearchActivity.this, mList);
                    searchRecycler.setAdapter(adapter);
                }

            }

        });
    }

//    private void searchSetData(Editable s){
////        SearchListEntity entity = null;
////        String test = null;
////        mList = new ArrayList<SearchListEntity>();
//
//        cursor = db.rawQuery("select * from Bank where account like '%"+ s +"%'",null);
//        searchData(cursor);
//
//        cursor = db.rawQuery("select * from webSet where account like '%" + s + "%'",null);
//        searchData(cursor);
//
//        cursor = db.rawQuery("select * from Cloud where account like '%" + s + "%'",null);
//        searchData(cursor);
//
//        cursor = db.rawQuery("select * from database where account like '%" + s + "%'",null);
//        searchData(cursor);
//
//        cursor = db.rawQuery("select * from entertainment where account like '%" + s + "%'",null);
//        searchData(cursor);
//
//        cursor = db.rawQuery("select * from often where account like '%" + s + "%'",null);
//        searchData(cursor);
//
//        cursor = db.rawQuery("select * from other where account like '%" + s + "%'",null);
//        searchData(cursor);
//    }

//    private void searchData(Cursor cursor){
//        SearchListEntity entity = null;
//        String test = null;
//        mList = new ArrayList<SearchListEntity>();
//        if (cursor.moveToFirst()){
//            do{
//                test =  cursor.getString(cursor.getColumnIndex("account"));
//                entity = new SearchListEntity(test);
//                mList.add(entity);
//            }while (cursor.moveToNext());
//        }
//        cursor.close();
//        secondAdapter = new SecondSearchActivityAdapter(context,mList);
//        searchRecycler.setAdapter(secondAdapter);
//    }

    private void initListener() {

        adapter.setOnItemLongClickListener(new SearchActivityAdapter.OnItemLongClickListener() {
            @Override
            public void onLongClickListener(final int position) {
                View popView = LayoutInflater.from(context).inflate(R.layout.pop_search_activity,
                        null, false);
                openPop = new OpenPopupWindow();
                openPop.showPopWindow(searchEt, popView);
                TextView updateTv = (TextView) popView.findViewById(R.id.tv_update);
                final TextView deleteTv = (TextView) popView.findViewById(R.id.tv_delete);

                updateTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dataUtil.updateData(context, position, mList);
                        openPop.hidePopupWindow();
                    }
                });
                deleteTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
                        builder.setTitle("warning");
                        builder.setMessage("are you sure to delete it?");
                        builder.setCancelable(false);
                        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.execSQL("delete from Bank where account = ?", new
                                        String[]{mList.get(position).getPassDesc()});
                                db.execSQL("delete from webSet where account = ?", new
                                        String[]{mList.get(position).getPassDesc()});
                                db.execSQL("delete from Cloud where account = ?", new
                                        String[]{mList.get(position).getPassDesc()});
                                db.execSQL("delete from database where account = ?", new
                                        String[]{mList.get(position).getPassDesc()});
                                db.execSQL("delete from entertainment where account = ?", new
                                        String[]{mList.get(position).getPassDesc()});
                                db.execSQL("delete from often where account = ?", new
                                        String[]{mList.get(position).getPassDesc()});
                                db.execSQL("delete from other where account = ?", new
                                        String[]{mList.get(position).getPassDesc()});
                                adapter.deleteData(position);
                            }
                        });
                        builder.setNegativeButton("no", null);
                        builder.show();


                        openPop.hidePopupWindow();

                    }
                });
            }
        });
//        adapter.setOnItemClickListener(new SearchActivityAdapter.OnItemClickListener() {
//            @Override
//            public void onClickListener(int position) {
//                Intent intent = new Intent(context, ShowPasswordActivity.class);
//                intent.putExtra("position", mList.get(position).getPassDesc());
//                startActivity(intent);
//
//            }
//        });

        deleteIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEt.getText().clear();
            }
        });


    }

    private void initView() {
        searchEt = (EditText) this.findViewById(R.id.et_search);
        deleteIv = (ImageView) this.findViewById(R.id.iv_delete);
        searchRecycler = (RecyclerView) this.findViewById(R.id.recycler_search);
        initData();
        searchRecycler.setLayoutManager(new LinearLayoutManager(context));
        searchRecycler.addItemDecoration(new DividerItemDecoration(context, LinearLayout.VERTICAL));
        adapter = new SearchActivityAdapter(SearchActivity.this, mList);
        searchRecycler.setAdapter(adapter);

        titleTv = (TextView) this.findViewById(R.id.tv_title_describe);
        titleTv.setText("搜索");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        SoftInputUtil hideSoft = new SoftInputUtil();
        hideSoft.HideSoftInput(this, event);
        return super.onTouchEvent(event);

    }


    private void initData() {
        mList = new ArrayList<SearchListEntity>();

        cursor = db.query("Bank", null, null, null, null, null, null);
        addData(cursor);

        cursor = db.query("webSet", null, null, null, null, null, null);
        addData(cursor);

        cursor = db.query("Cloud", null, null, null, null, null, null);
        addData(cursor);

        cursor = db.query("database", null, null, null, null, null, null);
        addData(cursor);

        cursor = db.query("entertainment", null, null, null, null, null, null);
        addData(cursor);

        cursor = db.query("often", null, null, null, null, null, null);
        addData(cursor);

        cursor = db.query("other", null, null, null, null, null, null);
        addData(cursor);

    }

    private void addData(Cursor cursor) {
        if (cursor.moveToFirst()) {
            do {
                SearchListEntity searchListEntity = new SearchListEntity(cursor.getString(cursor
                        .getColumnIndex("account")));
                if (cursor.getString(cursor.getColumnIndex("account")) != null) {
                    mList.add(searchListEntity);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
    }


}
