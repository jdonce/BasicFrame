package com.donce.common.presenter;

/**
 * view更新（当htpp请求完后的响应处理流程）
 * Created by Administrator on 2016/7/22 0022.
 */
public interface LoadView<D> {
    void onLoadComplete();

    void onLoadSuccess(D response);

    void onFailure(String msg);

}
