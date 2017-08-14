package example.com.passwordmanagerinitial.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import example.com.passwordmanagerinitial.MyApplication;
import example.com.passwordmanagerinitial.R;
import example.com.passwordmanagerinitial.activity.SearchActivity;
import example.com.passwordmanagerinitial.util.ExitApplicationUtil;
import example.com.passwordmanagerinitial.util.OpenPopupWindow;

/**
 * Created by Administrator on 2017/6/8.
 */

public class TitleLayout extends RelativeLayout implements View.OnClickListener{

    private ImageView searchIv,settingIv;
    private Context context;

    private ExitApplicationUtil exitApplicationUtil;


    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this);
        exitApplicationUtil = ExitApplicationUtil.getInstance();

        searchIv = (ImageView)findViewById(R.id.iv_search);
        settingIv = (ImageView)findViewById(R.id.iv_setting_right);

        searchIv.setOnClickListener(this);
        settingIv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        context = MyApplication.getContext();
        switch (v.getId()){
            case R.id.iv_search:
                Intent intent = new Intent(context, SearchActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                break;
            case R.id.iv_setting_right:
                OpenPopupWindow popupWindow = new OpenPopupWindow();
                View popView = LayoutInflater.from(context).inflate(R.layout.pop_layout,null,false);
                popupWindow.showPopWindow(settingIv,popView);
                TextView exitTv = (TextView)popView.findViewById(R.id.tv_exit);
                exitTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitApplicationUtil.removeAllActivity();
                    }
                });
                break;
        }
    }
}
