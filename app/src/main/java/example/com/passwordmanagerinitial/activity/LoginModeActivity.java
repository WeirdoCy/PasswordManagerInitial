package example.com.passwordmanagerinitial.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import example.com.passwordmanagerinitial.MyApplication;
import example.com.passwordmanagerinitial.R;
import example.com.passwordmanagerinitial.util.ExitApplicationUtil;

/**
 * Created by Administrator on 2017/7/24.
 */

public class LoginModeActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView exitTv;
    private Switch faceSwitch,fingerSwitch,picSwitch;

    private ExitApplicationUtil exitApplicationUtil;

    private Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_mode);

        exitApplicationUtil = ExitApplicationUtil.getInstance();
        exitApplicationUtil.addActivity(this);

        context = MyApplication.getContext();

        initView();
        initListener();
    }


    private void initListener() {
        exitTv.setOnClickListener(this);
        faceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    faceSwitch.setClickable(true);
                    fingerSwitch.setClickable(false);
                    picSwitch.setClickable(false);
                }else {
                    faceSwitch.setClickable(true);
                    fingerSwitch.setClickable(true);
                    picSwitch.setClickable(true);
                }
            }
        });
        fingerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    faceSwitch.setClickable(false);
                    fingerSwitch.setClickable(true);
                    picSwitch.setClickable(false);
                }else {
                    faceSwitch.setClickable(true);
                    fingerSwitch.setClickable(true);
                    picSwitch.setClickable(true);
                }
            }
        });
        picSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    faceSwitch.setClickable(false);
                    fingerSwitch.setClickable(false);
                    picSwitch.setClickable(true);
                }else {
                    faceSwitch.setClickable(true);
                    fingerSwitch.setClickable(true);
                    picSwitch.setClickable(true);
                }
            }
        });

    }

    private void initView() {
        exitTv = (TextView)this.findViewById(R.id.tv_exit_logmode);
        faceSwitch = (Switch)this.findViewById(R.id.switch_face);
        fingerSwitch = (Switch)this.findViewById(R.id.switch_finger);
        picSwitch = (Switch)this.findViewById(R.id.switch_pic);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_exit_logmode:
                exitApplicationUtil.removeAllActivity();
                break;
        }
    }
}
