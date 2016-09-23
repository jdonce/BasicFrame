package com.donce.common.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * http请求的回调
 * Created by Administrator on 2016/9/22 0022.
 */
public abstract class ApiCallback<T> extends BaseCallback<T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        onComplete();
        if (response != null && response.body() != null) {
            onSuccess(response.body());
        } else {
            onFailure("请求失败");
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFailure("请求失败");
    }

}
