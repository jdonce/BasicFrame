package com.djonce.sample.ui.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.djonce.sample.R;
import com.djonce.sample.model.bean.User;
import com.djonce.sample.model.db.DBUser;
import com.donce.common.ui.BaseFragment;
import com.donce.common.util.SimpleAlertDialogHelper;
import com.donce.common.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/8/2 0002.
 */
public class SecondFragment extends BaseFragment {
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
                SimpleAlertDialogHelper.showAlertDialog(getActivity(), "提示", "这是提示内容", "确定", "取消",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtil.showLongToast(getActivity(), "toast提示");
                            }
                        });
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


}
