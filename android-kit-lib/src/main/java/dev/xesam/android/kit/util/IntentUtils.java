package dev.xesam.android.kit.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by xesamguo@gmail.com on 16-3-13.
 */
public class IntentUtils {
    /**
     * 调用系统发短信界面
     *
     * @param context     Activity
     * @param phoneNumber 手机号码
     * @param smsContent  短信内容
     */
    public static void sendMessage(Context context, String phoneNumber, String smsContent) {
        if (phoneNumber == null || phoneNumber.length() < 4) {
            return;
        }
        Uri uri = Uri.parse("smsto:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", smsContent);
        AndroidUtils.startActivity(context, intent);
    }

    /**
     * 调用系统打电话界面
     *
     * @param context     上下文
     * @param phoneNumber 手机号码
     */
    public static void callPhones(Context context, String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() < 1) {
            return;
        }
        Uri uri = Uri.parse("tel:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        AndroidUtils.startActivity(context, intent);
    }
}
