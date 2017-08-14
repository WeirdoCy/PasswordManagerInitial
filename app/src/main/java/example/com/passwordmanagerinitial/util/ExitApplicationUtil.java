package example.com.passwordmanagerinitial.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/9.
 */

public class ExitApplicationUtil {

    private static ExitApplicationUtil exitApplicationUtil;

    private static List<Activity> mActivity = new ArrayList<Activity>();

    private ExitApplicationUtil(){

    }

    public static ExitApplicationUtil getInstance(){
        if (exitApplicationUtil == null){
            synchronized (ExitApplicationUtil.class){
                if (exitApplicationUtil == null){
                    exitApplicationUtil = new ExitApplicationUtil();
                }
            }
        }
        return exitApplicationUtil;
    }

    public void addActivity(Activity activity){
        mActivity.add(activity);
    }

    public void removeActivity(Activity activity){
        mActivity.remove(activity);
        activity.finish();
    }

    public void removeAllActivity(){
        for (Activity activity : mActivity){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
        System.exit(0);
    }
}
