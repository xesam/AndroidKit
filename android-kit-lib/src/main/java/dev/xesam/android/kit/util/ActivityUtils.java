package dev.xesam.android.kit.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by xesamguo@gmail.com on 17-2-3.
 */

public class ActivityUtils {
    public static void startActivity(Context context, Intent intent) {
        if (!(context instanceof Activity)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }
}
