package com.djonce.sample.presenter;

import com.djonce.sample.model.api.CodeConstant;
import com.djonce.sample.model.bean.BaseResponse;
import com.donce.common.presenter.LoadView;

import retrofit2.Response;

/**
 * Presenter的基类
 * Created by Administrator on 2016/7/22 0022.
 */
public class BasePresenter<T>  {
    private LoadView loadView;

    public BasePresenter(LoadView view) {
        this.loadView = view;
    }
    //处理返回的数据
    public void handleResponse(Response<BaseResponse<T>> response) {
        loadView.onLoadComplete();
        BaseResponse<T> body = response.body();
        parseResponse(body);
    }

    //解析
    public void parseResponse(BaseResponse<T> body) {
        if (body == null) {
            onLoadFailure("json解析异常");
            return;
        }

        if (body.getCode() == CodeConstant.SUCCESS) {
            T resultObject = body.getObject();
            loadView.onLoadSuccess(resultObject);
        } else {
            onLoadFailure(body.getErrorMsg());
        }
    }

    public void onLoadFailure(String msg) {
        loadView.onFailure(msg);
    }

}
