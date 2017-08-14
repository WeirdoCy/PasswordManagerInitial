package example.com.passwordmanagerinitial.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;


/**
 * Created by Administrator on 2017/7/13.
 * 复制文本
 */

public class ClicpUtil {

    private ClipboardManager clipboardManager;
    private ClipData clipData;

    public ClicpUtil(Context context) {
        clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    public void copyText(String content){
        clipData = ClipData.newPlainText("content",content);
        clipboardManager.setPrimaryClip(clipData);
    }
}
