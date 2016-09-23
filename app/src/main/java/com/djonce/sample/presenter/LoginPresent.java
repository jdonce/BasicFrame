package com.djonce.sample.presenter;

import com.djonce.sample.model.api.CodeConstant;
import com.djonce.sample.model.api.Factory;
import com.djonce.sample.model.bean.BaseResponse;
import com.djonce.sample.model.bean.User;
import com.donce.common.callback.ApiCallback;
import com.donce.common.presenter.LoadView;

/**
 * 登录的数据请求
 * Created by Administrator on 2016/7/22 0022.
 */
public class LoginPresent {
    private LoadView loadView;

    public LoginPresent(LoadView view) {
        this.loadView = view;
    }


    //登录
    public void login(String name, String password) {
//        String passwordMd5 = HexUtil.encodeHexStr(MD5Util.md5(password));
        Factory.provideHttpService().getUser(name, password).enqueue(new ApiCallback<BaseResponse<User>>() {

            @Override
            public void onComplete() {
                loadView.onLoadComplete();
            }


            @Override
            public void onFailure(String msg) {
                loadView.onFailure(msg);
            }

            @Override
            public void onSuccess(BaseResponse<User> response) {
                if (response == null) {
                    loadView.onFailure("json解析异常");
                    return;
                }

                if (response.getCode() == CodeConstant.SUCCESS) {
                    loadView.onLoadSuccess(response.getObject());
                } else {
                    loadView.onFailure(response.getErrorMsg());
                }
            }
        });

       /* //开启此方法 需要开启common下的build.gradle文件中注释的compile 'io.reactivex:rxandroid:1.2.1'
        Factory.provideHttpService().getUser1(name, password)
                .observeOn(AndroidSchedulers.mainThread())//观察者所在的线程
                .subscribeOn(Schedulers.io())//请求执行的线程
                //如果正常执行会顺序调用onNext，onCompleted，如果出错则会调用onError
                .subscribe(new Subscriber<BaseResponse<User>>() {
                    @Override
                    public void onCompleted() {
                        onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e == null) {
                            onLoadFailure("连接失败");
                            return;
                        }
                        Log.d("main", e.getMessage().toString());
                        onLoadFailure(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(BaseResponse<User> user) {
                        if (user.getCode() == CodeConstant.SUCCESS) {
                            loadView.onSuccess(user.getObject());
                        } else {
                            loadView.onFailure(user.getErrorMsg());
                        }
                    }
                });*/
    }
}
