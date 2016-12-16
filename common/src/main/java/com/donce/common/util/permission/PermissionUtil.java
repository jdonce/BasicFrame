package com.donce.common.util.permission;

import android.Manifest;
import android.app.Activity;
import android.text.TextUtils;

import com.donce.common.util.PhoneUtils;


/**
 * 权限
 * Created by Administrator on 2016/12/13 0013.
 */
public class PermissionUtil {
    //申请请求的request code
    public final static int COMMON_CALL_PHONE_PERMISSION_REQUEST = 12;
    public final static int COMMON_WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST = 123;

    //拨打电话的请求权限及回调方法的处理
    public static PermissionManager callPhoneCheckPermission(final Activity context, final String phoneNumber, int requestCode) {
        PermissionManager manager = new PermissionManager(new PermissionInterface() {
            @Override
            public void onPermissionGranted(String... permission) {
                if (Manifest.permission.CALL_PHONE.equalsIgnoreCase(permission[0]) && !TextUtils.isEmpty(phoneNumber)) {
                    PhoneUtils.call(context, phoneNumber);
                }
            }

            @Override
            public void onPermissionDenied(String... permission) {

            }

            @Override
            public void onPermissionFailure() {

            }

            @Override
            public void onRecheckPermission() {

            }
        });
        //检测权限
        manager.checkPermission(context, Manifest.permission.CALL_PHONE, "", requestCode);
        return manager;
    }

    /**
     * 查看相册权限及回调方法
     *
     * @param object              指activity fragment
     * @param requestCode
     * @param permissionInterface
     * @return
     */
    public static PermissionManager writeExternalStorageCheckPermission(Object object, int requestCode,
                                                                        PermissionInterface permissionInterface) {
        PermissionManager permissionManager = new PermissionManager(permissionInterface);
        //检测权限
        permissionManager.checkPermission(object, Manifest.permission.WRITE_EXTERNAL_STORAGE, "", requestCode);
        return permissionManager;
    }
}
