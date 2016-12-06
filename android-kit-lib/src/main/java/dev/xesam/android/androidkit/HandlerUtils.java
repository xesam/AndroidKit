package dev.xesam.android.androidkit;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by xe on 16-2-29.
 */
public final class HandlerUtils {
    private HandlerUtils() {
    }

    public static Handler getMainHandler() {
        return new Handler(Looper.getMainLooper());
    }

    public static void postOnMain(Runnable action) {
        getMainHandler().post(action);
    }

    public static void postOnMainDelay(Runnable action, long delayMillis) {
        getMainHandler().postDelayed(action, delayMillis);
    }
}
