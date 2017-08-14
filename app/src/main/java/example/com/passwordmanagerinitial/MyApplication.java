package example.com.passwordmanagerinitial;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/6/8.
 */

public class MyApplication extends Application {

    public static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
