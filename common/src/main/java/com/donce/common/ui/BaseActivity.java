package com.donce.common.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.donce.common.widget.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * activity基类
 * Created by Administrator on 2016/7/19 0019.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;
    private LoadingDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        unbinder = ButterKnife.bind(this);
        onInitView();
    }

    protected abstract int getLayoutResource();

    protected abstract void onInitView();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    public void goToNextActivity(Class<?> c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

    //显示加载框
    public void showLoadProgress(Context context, String message, boolean isTouchDismiss) {
        dismissLoadProgress();
        dialog = new LoadingDialog(context, isTouchDismiss);
        dialog.show();
        if (!TextUtils.isEmpty(message)) {
            dialog.setMessage(message);
        }
    }

    //关闭加载框
    public void dismissLoadProgress() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    public void goToNextActivity(Intent intent) {
        startActivity(intent);
    }
}
