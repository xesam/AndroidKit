package dev.xesam.android.kit.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;

import java.security.MessageDigest;
import java.util.List;

/**
 * 获取 App 包相关的信息
 * <p>
 * Created by xesamguo@gmail.com on 11/17/15.
 */
public final class PackageUtils {

    public static ActivityInfo[] getAllActivityInfo(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES).activities;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return new ActivityInfo[]{};
    }

    public static void getInstalled(Context context) {

        List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(0);

        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
//            AppInfo tmpInfo = new AppInfo();
//            tmpInfo.appName = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
//            tmpInfo.packageName = packageInfo.packageName;
//            tmpInfo.versionName = packageInfo.versionName;
//            tmpInfo.versionCode = packageInfo.versionCode;
//            tmpInfo.appIcon = packageInfo.applicationInfo.loadIcon(getPackageManager());
//            appList.add(tmpInfo);
        }
    }

    /**
     * check system app
     */
    public static boolean isSystemApp(PackageInfo packageInfo) {
        return (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1;
    }

    public static void uninstall(Context context, String packageName) {
        Uri uri = Uri.parse("package:" + packageName);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DELETE);
        intent.setData(uri);
        context.startActivity(intent);

    }

    public static final class Version {
        String versionName = "unknown";
        int versioncode = 0;
    }

    /**
     * 获取 App 版本信息
     */
    public static Version getVersion(Context context) {
        Version version = new Version();
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);

            if (TextUtils.isEmpty(pi.versionName)) {
                version.versionName = pi.versionName;
            }
            version.versioncode = pi.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 获取 App 版本名称
     */
    public static String getVersionName(Context context) {
        return getVersion(context).versionName;
    }

    /**
     * 获取 App 版本号
     */
    public static int getVersionCode(Context context) {
        return getVersion(context).versioncode;
    }

    /**
     * 获取应用签名
     *
     * @param context 上下文
     * @param pkgName 包名
     */
    public static String getSign(Context context, String pkgName) {
        try {
            PackageInfo pis = context.getPackageManager().getPackageInfo(pkgName, PackageManager.GET_SIGNATURES);
            return hexdigest(pis.signatures[0].toByteArray());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将签名字符串转换成需要的32位签名
     *
     * @param paramArrayOfByte 签名byte数组
     * @return 32位签名字符串
     */
    private static String hexdigest(byte[] paramArrayOfByte) {
        final char[] hexDigits = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97,
                98, 99, 100, 101, 102};
        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(paramArrayOfByte);
            byte[] arrayOfByte = localMessageDigest.digest();
            char[] arrayOfChar = new char[32];
            for (int i = 0, j = 0; ; i++, j++) {
                if (i >= 16) {
                    return new String(arrayOfChar);
                }
                int k = arrayOfByte[i];
                arrayOfChar[j] = hexDigits[(0xF & k >>> 4)];
                arrayOfChar[++j] = hexDigits[(k & 0xF)];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
