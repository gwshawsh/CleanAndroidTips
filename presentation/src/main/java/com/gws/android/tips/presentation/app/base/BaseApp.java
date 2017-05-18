package com.gws.android.tips.presentation.app.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by gws on 2017/5/2.
 */

public class BaseApp extends Application {
    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;

    private Activity mCurrentActivity;
    private Set<Activity> allActivities = new HashSet<>();

    @Override
    public void onCreate() {
        super.onCreate();
        initScreenSize();
        initActivityLifecycleCallbacks();
    }

    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }


    public void initScreenSize() {
        WindowManager wm = (WindowManager) this.getSystemService(WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = wm.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;

        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

    private void initActivityLifecycleCallbacks(){
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                allActivities.add(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                setCurrentActivity(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                if(activity.equals(mCurrentActivity)){
                    setCurrentActivity(null);
                }
                allActivities.remove(activity);
            }
        });
    }
    private void setCurrentActivity(@NonNull Activity mCurrentActivity) {
        this.mCurrentActivity = mCurrentActivity;
    }

}
