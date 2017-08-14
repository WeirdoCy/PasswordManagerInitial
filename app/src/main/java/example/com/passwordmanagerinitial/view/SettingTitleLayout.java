package example.com.passwordmanagerinitial.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import example.com.passwordmanagerinitial.MyApplication;
import example.com.passwordmanagerinitial.R;
import example.com.passwordmanagerinitial.util.ExitApplicationUtil;
import example.com.passwordmanagerinitial.util.OpenPopupWindow;

/**
 * Created by Administrator on 2017/6/9.
 */

public class SettingTitleLayout extends RelativeLayout implements View.OnClickListener {

    private ExitApplicationUtil exitApplicationUtil;

    private Context context;

    private ImageView backIv,rightIv;

    public SettingTitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.setting_title,this);
        exitApplicationUtil = ExitApplicationUtil.getInstance();

        backIv = (ImageView)findViewById(R.id.iv_back);
        rightIv = (ImageView)findViewById(R.id.iv_setting_title_right);
        backIv.setOnClickListener(this);
        rightIv.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        context = MyApplication.getContext();
        switch (v.getId()){
            case R.id.iv_back:
//                ((Activity)getContext()).finish();
                exitApplicationUtil.removeActivity((Activity) getContext());
                break;
            case R.id.iv_setting_title_right:
                OpenPopupWindow popupWindow = new OpenPopupWindow();
                View popView = LayoutInflater.from(context).inflate(R.layout.pop_layout,null,false);
                popupWindow.showPopWindow(rightIv,popView);
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
