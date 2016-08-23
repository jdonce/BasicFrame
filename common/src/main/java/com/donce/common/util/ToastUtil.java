package com.donce.common.util;

import android.content.Context;
import android.widget.Toast;

/**
 * toast工具类
 * Created by Administrator on 2016/8/4 0004.
 */
public class ToastUtil {
    /**
     * 显示吐司信息（较长时间）
     *
     * @param context
     * @param text
     */
    public static void showLongToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    /**
     * 显示吐司信息（较短时间）
     *
     * @param context
     * @param text
     */
    public static void showShortToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }


   /* public static void showCustomToast(String message, int length, Context context) {
        Toast toast = new Toast(context.getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
        TextView tvToast = (TextView) view.findViewById(R.id.tv_toast);
        tvToast.setText(message);
        toast.setView(view);
        toast.show();

    }*/

}
