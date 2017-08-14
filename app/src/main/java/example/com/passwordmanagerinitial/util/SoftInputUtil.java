package example.com.passwordmanagerinitial.util;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;


/**
 * Created by Administrator on 2017/6/30.
 */

public class SoftInputUtil {

    private InputMethodManager manager;

    //单击空白处隐藏键盘
    public void HideSoftInput(Activity activity,MotionEvent event){
        manager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            if (activity.getCurrentFocus() != null && activity.getCurrentFocus().getWindowToken() != null){
                manager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}
