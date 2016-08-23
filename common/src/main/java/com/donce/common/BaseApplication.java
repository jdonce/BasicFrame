package com.donce.common;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import butterknife.ButterKnife;


/**
 * 基类
 * Created by Administrator on 2016/7/19 0019.
 */
public class BaseApplication extends Application {
    private static final String TAG = BaseApplication.class.getSimpleName();
    protected static BaseApplication instance;
    private static Context mContext;

    public static synchronized BaseApplication getInstance() {
        return instance;
    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = getApplicationContext();
        Log.i(TAG, "---------- BaseApplication Create -------");

        ButterKnife.setDebug(BuildConfig.LOG_DEBUG);
    }
}
