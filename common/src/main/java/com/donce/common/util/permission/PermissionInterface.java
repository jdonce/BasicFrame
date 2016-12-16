package com.donce.common.util.permission;

/**
 * Created by Administrator on 2016/12/13 0013.
 */
public interface PermissionInterface {
    //申请权限被允许的回调
    void onPermissionGranted(String... permission);


    //申请权限被拒绝的回调
    void onPermissionDenied(String... permission);

    //申请权限的失败的回调
    void onPermissionFailure();

    //如果从设置界面返回，则重新申请权限
    void onRecheckPermission();
}
