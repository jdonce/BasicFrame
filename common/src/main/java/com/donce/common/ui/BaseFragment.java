package com.donce.common.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.donce.common.util.permission.PermissionManager;
import com.donce.common.widget.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * fragment基类
 * Created by Administrator on 2016/7/19 0019.
 */
public abstract class BaseFragment extends Fragment {

    protected View rootView;
    private Unbinder unbinder;
    private LoadingDialog dialog;
    protected PermissionManager permissionManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutResource(), container, false);
        }
        unbinder = ButterKnife.bind(this, rootView);
        onInitView();
        return rootView;
    }

    protected abstract int getLayoutResource();

    protected abstract void onInitView();

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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionManager != null) {
            permissionManager.onRequestPermissionsResult(getActivity(), requestCode, permissions, grantResults);
        }
    }


}
