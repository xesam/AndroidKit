package dev.xesam.android.kit.util;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.widget.Toast;


/**
 * 全局提示工具
 *
 * @Warning 只能在 UI 线程调用
 * Created by xesamguo@gmail.com on 15-7-22.
 */
public final class ToastUtils {

    public static void show(Context context, @Nullable String message) {
        if (message == null) {
            message = "-";
        }
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void show(Context context, @StringRes int messageId) {
        String message = context.getResources().getString(messageId);
        show(context, message);
    }
}
