package example.com.passwordmanagerinitial.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import example.com.passwordmanagerinitial.MyApplication;
import example.com.passwordmanagerinitial.R;
import example.com.passwordmanagerinitial.util.ExitApplicationUtil;

/**
 * Created by Administrator on 2017/8/27.
 */

public class AutoGeneratePasswordActivity extends AppCompatActivity {

    private Context context;
    private WindowManager manager;

    private ExitApplicationUtil exitUtil;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_auto_genera_pass);

        context = MyApplication.getContext();

        manager = getWindowManager();
        Display display = manager.getDefaultDisplay();

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = (int) (display.getHeight() * 0.6);
        params.width = (int) (display.getWidth() * 0.7);
        params.gravity = Gravity.CENTER_VERTICAL;

        getWindow().setAttributes(params);

        exitUtil = ExitApplicationUtil.getInstance();
        exitUtil.addActivity(this);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            if (getCurrentFocus() != null){
                exitUtil.removeActivity(this);
            }
        }
        return super.onTouchEvent(event);
    }
}
