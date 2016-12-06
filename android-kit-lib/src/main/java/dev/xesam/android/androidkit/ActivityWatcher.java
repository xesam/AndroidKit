package dev.xesam.android.androidkit;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * App Activity Status watcher
 * Created by xe on 16-11-15.
 */

public class ActivityWatcher {

    public static final String TAG = ActivityWatcher.class.getSimpleName();

    private Application mHost;
    private int mSurviveCount = 0;
    private int mVisibleCount = 0;

    private Application.ActivityLifecycleCallbacks mActivityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            if (mSurviveCount == 0) {
                onOpen(activity);
            }
            mSurviveCount++;
        }

        @Override
        public void onActivityStarted(Activity activity) {
            if (mVisibleCount == 0) {
                onAppBackgroundToForeground(activity);
            }
            mVisibleCount++;
        }

        @Override
        public void onActivityResumed(Activity activity) {
        }

        @Override
        public void onActivityPaused(Activity activity) {
        }

        @Override
        public void onActivityStopped(Activity activity) {
            mVisibleCount--;
            if (mVisibleCount == 0) {
                onAppForegroundToBackground(activity);
            }
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            mSurviveCount--;
            if (mSurviveCount == 0) {

            }
        }
    };

    public void attach(Application application) {
        this.mHost = application;
        mHost.registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks);
    }

    public void detach() {
        mHost.unregisterActivityLifecycleCallbacks(mActivityLifecycleCallbacks);
    }

    public boolean hasVisible() {
        return mVisibleCount > 0;
    }

    public boolean hasSurvive() {
        return mSurviveCount > 0;
    }

    /**
     * 用户启动了第一个 Activity
     */
    public static void onOpen(Activity activity) {
    }

    /**
     * 前台->后台
     */
    public static void onAppForegroundToBackground(Activity activity) {
    }

    /**
     * 后台->前台
     */
    public static void onAppBackgroundToForeground(Activity activity) {
    }
}
