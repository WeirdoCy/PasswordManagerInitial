package example.com.passwordmanagerinitial.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import example.com.passwordmanagerinitial.MyApplication;
import example.com.passwordmanagerinitial.R;
import example.com.passwordmanagerinitial.util.ExitApplicationUtil;

/**
 * Created by Administrator on 2017/6/8.
 */

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView exitText;
    private TextView titleTv;
    private TextView modifyTv;
    private TextView loginModeTv;

    private Context context;

    private ExitApplicationUtil exitApplicationUtil;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        context = MyApplication.getContext();

        exitApplicationUtil = ExitApplicationUtil.getInstance();
        exitApplicationUtil.addActivity(this);


        initView();
        initListener();
    }

    private void initListener() {
        modifyTv.setOnClickListener(this);
        exitText.setOnClickListener(this);
        loginModeTv.setOnClickListener(this);
    }

    private void initView() {
        exitText = (TextView)findViewById(R.id.tv_quit);

        titleTv = (TextView)this.findViewById(R.id.tv_title_describe);
        titleTv.setText("设置");

        modifyTv = (TextView)this.findViewById(R.id.tv_modify);

        loginModeTv = (TextView)this.findViewById(R.id.tv_login_mode);

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
                break;
            case R.id.tv_login_mode:
                intent = new Intent(context,LoginModeActivity.class);
                break;
        }
        startActivity(intent);
    }
}
