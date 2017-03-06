package dev.xesam.android.kit.util;

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

    public static float getDensitySize(Context context, int size) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.density * size;
    }

    private static Size sScreenSize;

    /**
     * 获取屏幕尺寸
     *
     * @param context
     * @return 屏幕尺寸
     */
    public static Size getScreenSize(Context context) {
        if (sScreenSize == null) {
            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            sScreenSize = new Size(dm.widthPixels, dm.heightPixels);
        }
        return sScreenSize;
    }

    /**
     * 获取屏幕宽度
     *
     * @param context Activity
     * @return 屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        return getScreenSize(context).getWidth();
    }

    /**
     * 获取屏幕高度
     *
     * @param context Activity
     * @return 屏幕高度
     */
    public static int getScreenHeight(Context context) {
        return getScreenSize(context).getHeight();
    }
}
