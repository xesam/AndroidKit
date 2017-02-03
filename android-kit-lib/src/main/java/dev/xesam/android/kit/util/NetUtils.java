/**
 * Copyright (c) 2014 CoderKiss
 * <p>
 * CoderKiss[AT]gmail.com
 */

package dev.xesam.android.kit.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

public final class NetUtils {

    /**
     * 连接类型
     */
    public static final class ConnectType {
        public static final int NONE = -1;
        public static final int UNKNOWN = 0;
        public static final int ETHERNET = 1;
        public static final int WIFI = 2;
        public static final int MOBILE = 3;
    }

    /**
     * 网络制式
     */
    public static final class MobileStandard {
        public static final int UNKNOWN = 0;
        public static final int MOBILE_2G = 1;
        public static final int MOBILE_3G = 2;
        public static final int MOBILE_4G = 3;
    }

    /**
     * 运营商
     */
    public static final class SimProvider {
        public static final int UNKNOWN = 0;
        public static final int CMCC = 1;
        public static final int CUCC = 2;
        public static final int CHINANET = 3;
    }

    public static final class NetInfo {
        public int connectType = ConnectType.NONE;
        public int mobileStandard = MobileStandard.UNKNOWN;
        public int simProvider = SimProvider.UNKNOWN;

        public boolean isConnected() {
            return connectType != ConnectType.NONE;
        }

        public boolean isWifi() {
            return connectType == ConnectType.WIFI;
        }

        public boolean isMobile() {
            return connectType == ConnectType.MOBILE;
        }

        public boolean is2G() {
            return mobileStandard == MobileStandard.MOBILE_2G;
        }

        public boolean is3G() {
            return mobileStandard == MobileStandard.MOBILE_3G;
        }

        public boolean is4G() {
            return mobileStandard == MobileStandard.MOBILE_4G;
        }

        @Override
        public String toString() {
            return "NetInfo{" +
                    "connectType=" + connectType +
                    ", mobileStandard=" + mobileStandard +
                    ", simProvider=" + simProvider +
                    '}';
        }
    }

    @NonNull
    public static NetInfo getNetInfo(Context context) {
        NetInfo netInfo = new NetInfo();
        NetworkInfo info = getNetworkInfo(context);
        final int connectType = getConnectType(info);
        if (connectType == ConnectType.NONE) {
            return netInfo;
        }
        netInfo.connectType = connectType;
        if (connectType == ConnectType.MOBILE) {
            netInfo.mobileStandard = getMobileStandard(info);
            netInfo.simProvider = getSimProvider(context);
        }
        return netInfo;
    }

    /**
     * 获取NetworkInfo
     */
    @Nullable
    public static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo();
    }

    /**
     * 获取连接类型
     */
    public static int getConnectType(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        return getConnectType(info);
    }

    /**
     * 获取连接类型
     */
    public static int getConnectType(@Nullable NetworkInfo info) {
        if (info == null || !info.isConnected()) {
            return ConnectType.NONE;
        }
        switch (info.getType()) {
            case ConnectivityManager.TYPE_ETHERNET:
                return ConnectType.ETHERNET;
            case ConnectivityManager.TYPE_WIFI:
                return ConnectType.WIFI;
            case ConnectivityManager.TYPE_MOBILE:
                return ConnectType.MOBILE;
            default:
                return ConnectType.UNKNOWN;
        }
    }

    /**
     * 网络制式
     */
    public static int getMobileStandard(@Nullable NetworkInfo info) {
        if (info == null || !info.isConnected()) {
            return MobileStandard.UNKNOWN;
        }
        int type = info.getType();
        int subType = info.getSubtype();
        if (type == ConnectivityManager.TYPE_MOBILE) {
            switch (subType) {
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN:
                    return MobileStandard.MOBILE_2G;
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                case TelephonyManager.NETWORK_TYPE_EHRPD:
                case TelephonyManager.NETWORK_TYPE_HSPAP:
                    return MobileStandard.MOBILE_3G;
                case TelephonyManager.NETWORK_TYPE_LTE:
                    return MobileStandard.MOBILE_4G;
                default:
                    return MobileStandard.UNKNOWN;
            }
        }
        return MobileStandard.UNKNOWN;
    }

    /**
     * 获取运营商
     */
    public static int getSimProvider(Context context) {
        TelephonyManager telMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (telMgr.getSimState() == TelephonyManager.SIM_STATE_READY) {
            String provider = telMgr.getSimOperator();
            if (provider == null || TextUtils.isEmpty(provider)) {
                provider = telMgr.getSimOperator();
            }
            if (provider != null) {
                switch (provider) {
                    case "46000":
                    case "46002":
                    case "46007":
                        return SimProvider.CMCC;
                    case "46001":
                        return SimProvider.CUCC;
                    case "46003":
                        return SimProvider.CHINANET;
                    default:
                }
            }
        }
        return SimProvider.UNKNOWN;
    }

    /**
     * 判断是否存在网络连接
     */
    public static boolean isConnected(Context context) {
        NetInfo netInfo = getNetInfo(context);
        return netInfo.isConnected();
    }

    /**
     * 判断当前网络类型是否为wifi
     */
    public static boolean isWifi(Context context) {
        NetInfo netInfo = getNetInfo(context);
        return netInfo.isWifi();
    }

    /**
     * 判断用户当前是否为2G网络
     */
    public static boolean is2G(Context context) {
        NetInfo netInfo = getNetInfo(context);
        return netInfo.is2G();
    }
}
