package dev.xesam.android.kit.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by xesamguo@gmail.com on 16-8-19.
 */
public class TimeUtils {

    private static final long MILLIS_TIME = 24 * 60 * 60 * 1000;

    public static boolean isToday(long timestamp) {
        Calendar today = Calendar.getInstance(Locale.getDefault());
        Calendar compare = Calendar.getInstance(Locale.getDefault());

        compare.setTime(new Date(timestamp));
        return today.get(Calendar.YEAR) == compare.get(Calendar.YEAR)
                && today.get(Calendar.MONTH) == compare.get(Calendar.MONTH)
                && today.get(Calendar.DATE) == compare.get(Calendar.DATE);
    }

    public static boolean isYesterday(long timestamp) {
        return isToday(timestamp + MILLIS_TIME);
    }

    public static boolean isTomorrow(long timestamp) {
        return isToday(timestamp - MILLIS_TIME);
    }
}
