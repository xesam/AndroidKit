package dev.xesam.android.kit.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xesamguo@gmail.com on 17-2-10.
 */

public class CollectUtils {

    public interface Func1<T> {
        String get(T raw);
    }

    @NonNull
    public static <T> String join(@Nullable String sep, @Nullable T[] objects) {
        if (objects == null) {
            return "";
        }
        return join(sep, null, Arrays.asList(objects));
    }

    @NonNull
    public static <T> String join(@Nullable String sep, @Nullable List<T> objects) {
        return join(sep, null, objects);
    }

    @NonNull
    public static <T> String join(@Nullable String sep, @Nullable Func1<T> func1, @Nullable List<T> objects) {
        if (objects == null || objects.size() == 0) {
            return "";
        }
        if (sep == null) {
            sep = "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0, size = objects.size(); i < size; i++) {
            T raw = objects.get(i);
            String value;
            if (func1 == null) {
                value = String.valueOf(raw);
            } else {
                value = func1.get(raw);
            }
            if (i == 0) {
                sb.append(value);
            } else {
                sb.append(sep).append(value);
            }
        }
        return sb.toString();
    }
}
