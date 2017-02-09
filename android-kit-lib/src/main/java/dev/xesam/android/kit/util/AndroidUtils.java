package dev.xesam.android.kit.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by xesamguo@gmail.com on 17-2-3.
 */

public class AndroidUtils {
    /**
     * 启动 Activity
     */
    public static void startActivity(Context context, Intent intent) {
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            if (!(context instanceof Activity)) {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            context.startActivity(intent);
        }
    }

    /**
     * 启动 Service
     */
    public static void startService() {

    }

    /**
     * 输入面板
     */
    public static void showIme(Context context, boolean show) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (show) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_NOT_ALWAYS);
        } else {
            if (context instanceof Activity) {
                View view = ((Activity) context).getWindow().peekDecorView();
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
}
