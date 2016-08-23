package com.donce.common.presenter;

/**
 * Created by Administrator on 2016/7/22 0022.
 */
public class BasePresenter<T> {

    private T view;

    public BasePresenter(T view) {
        this.view = view;
    }

    public T getView() {
        return view;
    }


}
