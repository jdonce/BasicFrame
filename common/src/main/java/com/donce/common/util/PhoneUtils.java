package com.donce.common.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * 手机相关的工具类
 * Created by Administrator on 2016/10/20 0020.
 */
public class PhoneUtils {
    /**
     * 跳至填充好phoneNumber的拨号界面
     *
     * @param context     上下文
     * @param phoneNumber 电话号码
     */
    public static void dial(Context context, String phoneNumber) {
        context.startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber)));
    }
    /**
     * 拨打phoneNumber
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.CALL_PHONE"/>}</p>
     *
     * @param context     上下文
     * @param phoneNumber 电话号码
     */
    public static void call(Context context, String phoneNumber) {
        context.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + phoneNumber)));
    }
}
