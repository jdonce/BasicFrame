package com.donce.common.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.donce.common.widget.SimpleAlertDialog;

/**
 * dialog的帮助类
 * Created by Administrator on 2016/8/4 0004.
 */
public class SimpleAlertDialogHelper {

    //显示提示框
    public static SimpleAlertDialog showAlertDialog(Context context, String title, String message, String rightText,
                                                    String leftText, View.OnClickListener rightButtonClick) {
        SimpleAlertDialog alertDialog = getSimpleAlertDialog(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.showSegment();
        alertDialog.setRightButton(rightText, rightButtonClick);
        alertDialog.setLeftButton(leftText);
        return alertDialog;
    }

    //显示单个按钮的提示框
    public static SimpleAlertDialog showAlertDialog(Context context, String title, String message, String rightText,
                                                    View.OnClickListener rightButtonClick) {
        SimpleAlertDialog alertDialog = getSimpleAlertDialog(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setRightButton(rightText, rightButtonClick);
        return alertDialog;
    }

    @NonNull
    private static SimpleAlertDialog getSimpleAlertDialog(Context context) {
        SimpleAlertDialog alertDialog = new SimpleAlertDialog(context);
        alertDialog.show();
        return alertDialog;
    }
}
