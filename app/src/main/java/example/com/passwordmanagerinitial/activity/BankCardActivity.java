package example.com.passwordmanagerinitial.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import example.com.passwordmanagerinitial.MyApplication;
import example.com.passwordmanagerinitial.R;
import example.com.passwordmanagerinitial.adapter.BankCardListAdapter;
import example.com.passwordmanagerinitial.util.ExitApplicationUtil;
import example.com.passwordmanagerinitial.util.OpenPopupWindow;
import example.com.passwordmanagerinitial.util.SoftInputUtil;

/**
 * Created by Administrator on 2017/8/23.
 */

public class BankCardActivity extends AppCompatActivity{

    private LinearLayout llBankList;
    private EditText etBankCardAccount,etBankCardPassword;
    private ImageView ivKey;
    private Button btnSure;
    private TextView tvBank;

    private RecyclerView recyclerView;

    private OpenPopupWindow popupWindow;
    private ExitApplicationUtil exit;
    private BankCardListAdapter adapter;

    private Context context;
    private List<String> mList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_card);

        context = MyApplication.getContext();

        exit = ExitApplicationUtil.getInstance();
        exit.addActivity(this);

        popupWindow = new OpenPopupWindow();

        initView();
        initPopView();
        initListener();
    }

    private void initListener() {

        ivKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,AutoGeneratePasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initPopView() {
        llBankList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View popView = LayoutInflater.from(context).inflate(R.layout.bank_card_list,null,false);
                recyclerView = (RecyclerView)popView.findViewById(R.id.recycler_bank);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                initData();
                adapter = new BankCardListAdapter(context,mList);
                recyclerView.setAdapter(adapter);
                popupWindow.showPopWindow(llBankList,popView,llBankList.getWidth());

                adapter.setOnItemClickListener(new BankCardListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        tvBank.setText(mList.get(position).toString());
                        popupWindow.hidePopupWindow();
                    }
                });
            }
        });



    }

    private void initData() {
        mList = new ArrayList<>();
        mList.add("农业银行");
        mList.add("工商银行");
        mList.add("建设银行");
        mList.add("中国银行");
        mList.add("交通银行");
        mList.add("招商银行");
        mList.add("国家开发银行");
        mList.add("中国进出口银行");
        mList.add("中信实业银行");
        mList.add("中国光大银行");
        mList.add("华夏银行");
        mList.add("中国农业发展银行");
        mList.add("深圳发展银行");
        mList.add("广东发展银行");
    }

    private void initView() {
        llBankList = (LinearLayout)this.findViewById(R.id.ll_bank_list);
        etBankCardAccount = (EditText)this.findViewById(R.id.et_back_card);
        etBankCardPassword = (EditText)this.findViewById(R.id.et_password_bank);
        ivKey = (ImageView)this.findViewById(R.id.iv_key);
        btnSure = (Button)this.findViewById(R.id.btn_bank_sure);

        tvBank = (TextView)this.findViewById(R.id.tv_bank_list);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        SoftInputUtil util = new SoftInputUtil();
        util.HideSoftInput(this,event);
        return super.onTouchEvent(event);
    }
}
