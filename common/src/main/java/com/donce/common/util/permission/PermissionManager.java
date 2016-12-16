package com.donce.common.util.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限管理
 * Created by Administrator on 2016/12/13 0013.
 */
public class PermissionManager {
    //是否跳转过应用程序信息详情页
    private static boolean mIsJump2Settings = false;
    private int mRequestCode = -1;
    private PermissionInterface permissionInterface;

    public PermissionManager(PermissionInterface permission) {
        this.permissionInterface = permission;
    }


    //单个权限的检查
    public void checkPermission(final Object object, @NonNull final String permission, @Nullable String reason,
                                final int requestCode) {
        if (permissionInterface == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 23) {
            permissionInterface.onPermissionGranted(permission);
            return;
        }
        Context context = getContext(object);
        if (context == null) {
            return;
        }
        this.mRequestCode = requestCode;
        int permissionCheck = ContextCompat.checkSelfPermission(context, permission);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            //权限已经申请
            permissionInterface.onPermissionGranted(permission);

        } else {
            if (!TextUtils.isEmpty(reason)) {
                //判断用户先前是否拒绝过该权限申请，如果为true，我们可以向用户解释为什么使用该权限
                if (shouldShowRequestPermissionRationale(object, permission)) {
                    //这里的dialog可以自定义
                    new AlertDialog.Builder(context).setCancelable(false).setTitle("温馨提示").setMessage(reason).
                            setNegativeButton("我知道了", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    executePermissionsRequest(object, new String[]{permission}, requestCode);
                                    dialog.dismiss();
                                }
                            }).show();
                } else {
                    executePermissionsRequest(object, new String[]{permission}, requestCode);
                }
            } else {
                executePermissionsRequest(object, new String[]{permission}, requestCode);
            }

        }
    }

    //多个权限的检查
    public void checkPermissions(Object object, int requestCode, @NonNull String... permissions) {
        if (permissionInterface == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 23) {
            for (String permission : permissions) {
                permissionInterface.onPermissionGranted(permission);
            }
            return;
        }
        Context context = getContext(object);
        if (context == null) {
            return;
        }
        //用于记录权限申请被拒绝的权限集合
        List<String> permissionDeniedList = new ArrayList<>();
        for (String permission : permissions) {
            int permissionCheck = ContextCompat.checkSelfPermission(context, permission);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                permissionInterface.onPermissionGranted(permission);
            } else {
                permissionDeniedList.add(permission);
            }
        }
        if (!permissionDeniedList.isEmpty()) {
            this.mRequestCode = requestCode;
            String[] deniedPermissions = permissionDeniedList.toArray(new String[permissionDeniedList.size()]);
            executePermissionsRequest(object, deniedPermissions, requestCode);
        }
    }

    //调用系统API完成权限申请
    private static void requestPermission(Activity context, String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(context, permissions, requestCode);
    }

    public void onRequestPermissionsResult(Context context, int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (permissionInterface == null) {
            return;
        }
        if (requestCode != -1 && requestCode == mRequestCode) {
            if (grantResults.length > 0) {

                if (verifyPermissions(grantResults)) {
                    permissionInterface.onPermissionGranted(permissions);
                } else {
                    //如果有权限申请被拒绝，则弹出对话框提示用户去修改权限设置。
                    //showPermissionSettingsDialog(context);
                    permissionInterface.onPermissionDenied(permissions);
                }

            } else {
                permissionInterface.onPermissionFailure();
            }
        }

    }

    /**
     * 验证所有权限是否都已经授权
     */
    private static boolean verifyPermissions(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private static void showPermissionSettingsDialog(final Context context) {
        new AlertDialog.Builder(context).setCancelable(false).setTitle("温馨提示").
                setMessage("缺少必要权限\n不然将导致部分功能无法正常使用").setNegativeButton("下次吧", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("去设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                jump2PermissionSettings(context);
            }
        }).show();
    }

    /**
     * 跳转到应用程序信息详情页面
     */
    private static void jump2PermissionSettings(Context context) {
        mIsJump2Settings = true;
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        context.startActivity(intent);
    }

    @TargetApi(23)
    private static boolean shouldShowRequestPermissionRationale(@NonNull Object object,
                                                                @NonNull String perm) {
        if (object instanceof Activity) {
            return ActivityCompat.shouldShowRequestPermissionRationale((Activity) object, perm);
        } else if (object instanceof android.support.v4.app.Fragment) {
            return ((android.support.v4.app.Fragment) object).shouldShowRequestPermissionRationale(perm);
        } else if (object instanceof android.app.Fragment) {
            return ((android.app.Fragment) object).shouldShowRequestPermissionRationale(perm);
        }
        return false;

    }

    @TargetApi(23)
    private static void executePermissionsRequest(@NonNull Object object, @NonNull String[] perms, int requestCode) {
        if (object instanceof android.app.Activity) {
            ActivityCompat.requestPermissions((Activity) object, perms, requestCode);
        } else if (object instanceof android.support.v4.app.Fragment) {
            ((android.support.v4.app.Fragment) object).requestPermissions(perms, requestCode);
        } else if (object instanceof android.app.Fragment) {
            ((android.app.Fragment) object).requestPermissions(perms, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private static Context getContext(@NonNull Object object) {
        if (object instanceof Activity) {
            return ((Activity) object);
        } else if (object instanceof android.support.v4.app.Fragment) {
            return ((android.support.v4.app.Fragment) object).getContext();
        } else if (object instanceof android.app.Fragment) {
            return ((android.app.Fragment) object).getContext();
        } else {
            return null;
        }
    }

}
