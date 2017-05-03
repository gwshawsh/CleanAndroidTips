package com.gws.android.tips.presentation.app.base;

import android.app.Activity;
import android.app.Application;
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
    }

    public void addActivity(Activity activity) {
        allActivities.add(activity);
    }

    public void removeActivity(Activity a) {
        allActivities.remove(a);

    }
    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    public void setCurrentActivity(@NonNull Activity mCurrentActivity) {
        this.mCurrentActivity = mCurrentActivity;
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
}
