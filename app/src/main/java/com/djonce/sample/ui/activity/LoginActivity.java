package com.djonce.sample.ui.activity;


import android.util.Log;
import android.widget.Button;

import com.djonce.sample.R;
import com.djonce.sample.model.bean.User;
import com.djonce.sample.presenter.LoginPresent;
import com.donce.common.presenter.LoadView;
import com.donce.common.ui.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/7/21 0021.
 */
public class LoginActivity extends BaseActivity implements LoadView<User> {


    @BindView(R.id.btn_login)
    Button btnLogin;

    private LoginPresent loginPresent;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected void onInitView() {
        loginPresent = new LoginPresent(this);
    }


    @OnClick(R.id.btn_login)
    public void onClick() {
        showLoadProgress(this, "登录中...", true);
        loginPresent.login(null, null);

    }

    @Override
    public void onLoadComplete() {
        dismissLoadProgress();
    }

    @Override
    public void onLoadSuccess(User response) {
        Log.d("user", response.getReal_name());
        goToNextActivity(MainActivity.class);
    }

    @Override
    public void onFailure(String msg) {
        Log.d("onFailure", msg);
        dismissLoadProgress();
        goToNextActivity(MainActivity.class);
    }
}
