package example.com.passwordmanagerinitial.util;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import example.com.passwordmanagerinitial.R;

/**
 * Created by Administrator on 2017/7/10.
 */

public class OpenPopupWindow {

    private ExitApplicationUtil exitApplicationUtil;
    private PopupWindow popupWindow;

    public OpenPopupWindow() {
        exitApplicationUtil = ExitApplicationUtil.getInstance();
    }

    public void showPopWindow(View anchor,View popView){
        popupWindow = new PopupWindow(popView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(anchor,0,0);
    }

    public void hidePopupWindow(){
        Log.d("TAG", "hidePopupWindow: " + "true");
        if (popupWindow != null){
            popupWindow.dismiss();
        }
    }
}
