package com.djonce.sample.presenter;

import com.djonce.sample.model.api.CodeConstant;
import com.djonce.sample.model.bean.BaseResponse;
import com.donce.common.presenter.BasePresenter;
import com.donce.common.presenter.LoadView;

import retrofit2.Response;

/**
 * Created by Administrator on 2016/7/22 0022.
 */
public class LoadPresenter<T> extends BasePresenter<LoadView> {

    public LoadPresenter(LoadView view) {
        super(view);
    }


    //处理返回的数据
    public void handleResponse(Response<BaseResponse<T>> response) {
        getView().onLoadComplete();
        BaseResponse<T> body = response.body();
        parseResponse(body);
    }

    //解析
    public void parseResponse(BaseResponse<T> body) {
        if (body == null) {
            getView().onFailure("json解析异常");
            return;
        }

        if (body.getCode() == CodeConstant.SUCCESS) {
            T resultObject = body.getObject();
            getView().onLoadSuccess(resultObject);
        } else {
            getView().onFailure(body.getErrorMsg());
        }
    }

    public void onLoadFailure(String msg) {
        getView().onFailure(msg);
    }


    public void onLoadComplete() {
        getView().onLoadComplete();
    }
}
