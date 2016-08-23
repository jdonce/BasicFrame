package com.donce.common.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.donce.common.R;

/**
 * 加载框
 */
public class LoadingDialog extends Dialog {

    private boolean isTouchDismiss = true;

    private TextView tvMessage;

    public LoadingDialog(Context context, boolean isShow) {
        //设置style
        super(context, R.style.loading_dialog);
        this.isTouchDismiss = isShow;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_loading);
        tvMessage = (TextView) findViewById(R.id.tv_message);
        setCanceledOnTouchOutside(isTouchDismiss);
    }

    // 设置屏幕背景透明
    private void setScreenBgLight() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 1.0f;//透明度
        lp.dimAmount = 0.3f;//暗度0-1
        getWindow().setAttributes(lp);
    }

    public void setOnTouchOutside(boolean isShow) {
        this.isTouchDismiss = isTouchDismiss;
    }

    public void setMessage(String message) {
        if (this.tvMessage != null) {
            tvMessage.setText(message);
        }
    }
}
