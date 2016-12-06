package dev.xesam.android.androidkit;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public final class CommonUtils {
    /**
     * 弹出输入面板
     */
    public static void showIme(Context context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 收起输入面板
     */
    public static void hideIme(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 收起输入面板
     */
    public static void hideIme(Activity context) {
        hideIme(context, context.getWindow().peekDecorView());
    }
}
