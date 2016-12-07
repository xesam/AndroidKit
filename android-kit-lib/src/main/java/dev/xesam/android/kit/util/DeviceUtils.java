package dev.xesam.android.kit.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by xe on 2/22/16.
 */
public final class DeviceUtils {
    /**
     * 获取设备名称
     */
    public static String getDeviceName() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取设备的可用内存大小
     *
     * @param context 应用上下文对象context
     * @return 当前内存大小
     */
    public static int getDeviceUsableMemory(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        // 返回当前系统的可用内存
        return (int) (mi.availMem / (1024 * 1024));
    }

    public static float getDensitySize(Context context, int size) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.density * size;
    }

    /**
     * 获取dialog宽度
     *
     * @param activity Activity
     * @return Dialog宽度
     */
    public static int getDialogW(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = activity.getResources().getDisplayMetrics();
        int w = dm.widthPixels - 100;
        // int w = aty.getWindowManager().getDefaultDisplay().getWidth() - 100;
        return w;
    }

    /**
     * 获取屏幕宽度
     *
     * @param activity Activity
     * @return 屏幕宽度
     */
    public static int getScreenWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = activity.getResources().getDisplayMetrics();
        int w = dm.widthPixels;
        // int w = aty.getWindowManager().getDefaultDisplay().getWidth();
        return w;
    }

    /**
     * 获取屏幕高度
     *
     * @param activity Activity
     * @return 屏幕高度
     */
    public static int getScreenHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = activity.getResources().getDisplayMetrics();
        int h = dm.heightPixels;
        // int h = aty.getWindowManager().getDefaultDisplay().getHeight();
        return h;
    }
}
