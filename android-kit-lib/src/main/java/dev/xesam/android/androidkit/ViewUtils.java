package dev.xesam.android.androidkit;

import android.app.Activity;
import android.view.View;

/**
 * Created by xe on 16-2-29.
 */
public final class ViewUtils {
    @SuppressWarnings("unchecked")
    public static <V extends View> V findById(Activity activity, int id) {
        return (V) activity.findViewById(id);
    }

    @SuppressWarnings("unchecked")
    public static <V extends View> V findById(View view, int id) {
        return (V) view.findViewById(id);
    }
}
