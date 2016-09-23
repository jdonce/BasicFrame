package com.djonce.sample.ui.fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.djonce.sample.R;
import com.djonce.sample.model.bean.User;
import com.djonce.sample.model.db.DBUser;
import com.djonce.sample.presenter.FileDownloadPresenter;
import com.donce.common.presenter.DownloadView;
import com.donce.common.ui.BaseFragment;
import com.donce.common.util.ToastUtil;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/8/2 0002.
 */
public class SecondFragment extends BaseFragment implements DownloadView {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.edit_age)
    EditText editAge;
    @BindView(R.id.tv_result)
    TextView tvResult;

    private FileDownloadPresenter fileDownloadPresenter;
    private boolean isDownload;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_second;
    }

    @Override
    protected void onInitView() {
        tvTitle.setText("tab2");
        updateView(new DBUser());
    }


    @OnClick({R.id.btn1, R.id.btn2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                if (isDownload) {
                    isDownload = false;
                    fileDownloadPresenter.onCancelDownload();
                } else {
                    isDownload = true;
                    btn1.setText("取消");

                    fileDownloadPresenter = new FileDownloadPresenter(this);
                    fileDownloadPresenter.download("url");
                }
                break;
            case R.id.btn2:
                String name = editName.getText().toString();
                String age = editAge.getText().toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(age)) {
                    ToastUtil.showShortToast(getActivity(), "请输入名称或年龄");
                    return;
                }

                insertUser(name, age);
                break;
        }

    }

    //数据插入
    private void insertUser(String name, String age) {
        DBUser dbUser = new DBUser();

        User user = new User();
        user.setReal_name(name);
        user.setAge(Integer.valueOf(age));
        dbUser.saveUser(user);
        editName.setText("");
        editAge.setText("");

        updateView(dbUser);
    }

    private void updateView(DBUser dbUser) {
        List<User> userList = dbUser.queryUsers();
        tvResult.setText(userList.toString());
    }


    @Override
    public void inProgress(float progress, long total) {
        int progressCount = (int) (100 * progress);
        Log.d("inProgress", progressCount + "%" + ",total:" + total);
    }

    @Override
    public void onSuccess(File file) {
        isDownload = false;
        btn1.setText("文件下载");
        ToastUtil.showLongToast(getActivity(), "成功");

    }

    @Override
    public void onFailure(String message) {
        isDownload = false;
        btn1.setText("文件下载");
        ToastUtil.showLongToast(getActivity(), "失败");
    }

}
