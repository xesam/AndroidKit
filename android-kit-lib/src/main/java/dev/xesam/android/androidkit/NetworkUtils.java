/**
 * Copyright (c) 2014 CoderKiss
 * <p>
 * CoderKiss[AT]gmail.com
 */

package dev.xesam.android.androidkit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

public final class NetworkUtils {
    public static final String TAG = "NetworkHelper";

    /**
     * Unknown network class
     */
    public static final int NETWORK_CLASS_UNKNOWN = 0;
    /**
     * Class of broadly defined "2G" networks
     */
    public static final int NETWORK_CLASS_2_G = 1;
    /**
     * Class of broadly defined "3G" networks.
     */
    public static final int NETWORK_CLASS_3_G = 2;
    /**
     * Class of broadly defined "4G" networks.
     */
    public static final int NETWORK_CLASS_4_G = 3;


    /**
     * 网络类型
     */
    private static NetworkType type;

    /**
     * 获取当前网络类型, 并设置网络状态改变
     */
    public static NetworkType getNetworkType(Context context) {
        updateNetwork(context);
        return type;
    }

    private static void updateNetwork(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        type = checkType(networkInfo);
    }

    /**
     * 获取NetworkInfo
     */
    public static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo();
    }

    /**
     * 检测网络状态
     */
    private static NetworkType checkType(NetworkInfo info) {
        if (info == null || !info.isConnected()) {
            return NetworkType.UNKNOWN;
        }
        int type = info.getType();
        int subType = info.getSubtype();
        if ((type == ConnectivityManager.TYPE_WIFI)
                || (type == ConnectivityManager.TYPE_ETHERNET)) {
            return NetworkType.WIFI;
        }
        if (type == ConnectivityManager.TYPE_MOBILE) {
            switch (subType) {
                case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                    return NetworkType.UNKNOWN;
                case TelephonyManager.NETWORK_TYPE_GPRS:
                    return NetworkType.MOBILE_GPRS;
                case TelephonyManager.NETWORK_TYPE_EDGE:
                    return NetworkType.MOBILE_EDGE;
                case TelephonyManager.NETWORK_TYPE_CDMA:
                    return NetworkType.MOBILE_CDMA;
                case TelephonyManager.NETWORK_TYPE_UMTS:
                    return NetworkType.MOBILE_UMTS;
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    return NetworkType.MOBILE_EVDO_0;
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    return NetworkType.MOBILE_EVDO_A;
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                    return NetworkType.MOBILE_1xRTT;
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                    return NetworkType.MOBILE_HSDPA;
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                    return NetworkType.MOBILE_HSUPA;
                case TelephonyManager.NETWORK_TYPE_HSPA:
                    return NetworkType.MOBILE_HSPA;
                case TelephonyManager.NETWORK_TYPE_IDEN:
                    return NetworkType.MOBILE_IDEN; // 2G
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    return NetworkType.MOBILE_EVDO_B;
                case TelephonyManager.NETWORK_TYPE_LTE:
                    return NetworkType.MOBILE_LTE; // 4G
                case TelephonyManager.NETWORK_TYPE_EHRPD:
                    return NetworkType.MOBILE_EHRPD;
                case TelephonyManager.NETWORK_TYPE_HSPAP:
                    return NetworkType.MOBILE_HSPAP;// 3G
            }
        }
        return NetworkType.UNKNOWN;
    }

    public static int getNetworkClass(int networkType) {
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return NETWORK_CLASS_2_G;
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return NETWORK_CLASS_3_G;
            case TelephonyManager.NETWORK_TYPE_LTE:
                return NETWORK_CLASS_4_G;
            default:
                return NETWORK_CLASS_UNKNOWN;
        }
    }

    //判断用户当前是否为2G网络
    public static boolean isNetworkClass2G(NetworkInfo info) {
        return info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE
                && getNetworkClass(info.getSubtype()) == NETWORK_CLASS_2_G;
    }

    public enum NetworkType {
        WIFI, MOBILE_GPRS, MOBILE_EDGE, MOBILE_CDMA, MOBILE_1xRTT, MOBILE_IDEN, MOBILE_UMTS,
        MOBILE_EVDO_0, MOBILE_EVDO_A, MOBILE_HSDPA, MOBILE_HSUPA, MOBILE_HSPA, MOBILE_EVDO_B,
        MOBILE_EHRPD, MOBILE_HSPAP, MOBILE_LTE, NOT_CONNECT, UNKNOWN
    }

    /**
     * 获取运营商名称
     */
    public static String getSimOperatorName(Context context) {
        TelephonyManager telMgr =
                (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (telMgr.getSimState() == TelephonyManager.SIM_STATE_READY) {
            String simOperator = telMgr.getSimOperator();
            if (simOperator == null || TextUtils.isEmpty(simOperator))
                simOperator = telMgr.getSubscriberId();
            if (simOperator != null) {
                switch (simOperator) {
                    case "46000":
                    case "46002":
                    case "46007":
                        return "中国移动";
                    case "46001":
                        return "中国联通";
                    case "46003":
                        return "中国电信";
                    default:
                }
            }
        }
        return "未知";
    }

    /**
     * 判断网络是否已连接
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager conManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    /**
     * 判断当前网络类型是否为wifi
     */
    public static boolean isWifiConnected(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * 判断网络连接是否打开,包括移动数据连接
     *
     * @param context 上下文
     * @return 是否联网
     */
    public static boolean isNetworkAvailable(Context context) {
        boolean netstate = false;
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {

            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {

                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {

                        netstate = true;
                        break;
                    }
                }
            }
        }
        return netstate;
    }

    /**
     * 检测当前打开的网络类型是否3G
     *
     * @param context 上下文
     * @return 是否是3G上网
     */
    public static boolean is3G(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }

    /**
     * 检测当前开打的网络类型是否4G
     *
     * @param context 上下文
     * @return 是否是4G上网
     */
    public static boolean is4G(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.isConnectedOrConnecting()) {
            if (activeNetInfo.getType() == TelephonyManager.NETWORK_TYPE_LTE) {
                return true;
            }
        }
        return false;
    }
}
