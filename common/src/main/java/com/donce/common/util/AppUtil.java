package com.donce.common.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;

/**
 * app的工具包
 * Created by Administrator on 2016/8/2 0002.
 */
public class AppUtil {
    /**
     * 获取App包 信息版本号
     *
     * @param context
     * @return
     */
    public static PackageInfo getAppPackageInfo(Context context) {
        if (context != null) {
            PackageManager pm = context.getPackageManager();
            if (pm != null) {
                try {
                    return pm.getPackageInfo(context.getPackageName(), 0);
                } catch (Exception var4) {
                    var4.printStackTrace();
                }
            }
        }

        return null;
    }

    //获取版本name
    public static String getVersionName(Context context) {
        PackageInfo info = getAppPackageInfo(context);
        String versionName = info.versionName;
        return versionName;
    }

    //获取版本号
    public static int getAppVersionCode(Context context) {
        if (context != null) {
            PackageManager pm = context.getPackageManager();
            if (pm != null) {
                try {
                    PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
                    if (pi != null) {
                        return pi.versionCode;
                    }
                } catch (PackageManager.NameNotFoundException var4) {
                    var4.printStackTrace();
                }
            }
        }

        return -1;
    }

    /**
     * 得到屏幕分辨率
     *
     * @param context
     * @return
     */
    public static DisplayMetrics getScreenWidthHeightInPx(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);

        return dm;
    }
}
