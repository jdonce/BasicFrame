package com.donce.common.callback;

/**
 * http请求的回调基类
 * Created by Administrator on 2016/9/23 0023.
 */
public abstract class BaseCallback<T> {
    /**
     * 请求完成
     */
    public void onComplete() {
    }

    /**
     * 加载进度
     *
     * @param progress
     * @param total
     */
    public void inProgress(float progress, long total) {
    }


    /**
     * 请求失败
     *
     * @param msg
     */
    public abstract void onFailure(String msg);

    /**
     * 请求成功
     *
     * @param response
     */

    public abstract void onSuccess(T response);
}
