package com.csnt.baselib;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.csnt.utils.activityManage.ActivityLifecycleImp;

import androidx.annotation.NonNull;

/**
 * Created by sunrain
 * Created Date 2020/7/3 11:28 AM
 */
public class BaseApplication extends Application {
    private static BaseApplication instance;
    public static Context getInstance(){
        return instance;
    }
    @Override
    public void onCreate() {
        // 程序创建的时候执行
        super.onCreate();
        instance=this;
        registerActivityLifecycleCallbacks(ActivityLifecycleImp.getInstance());
    }
    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        super.onTerminate();
    }
    @Override
    public void onLowMemory() {
        // 低内存的时候执行
        super.onLowMemory();
    }
    @Override
    public void onTrimMemory(int level) {
        // 程序在内存清理的时候执行
        super.onTrimMemory(level);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
