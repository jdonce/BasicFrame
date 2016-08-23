package com.djonce.sample.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.djonce.sample.R;
import com.donce.common.ui.BaseFragment;
import com.donce.common.util.SimpleAlertDialogHelper;
import com.donce.common.util.ToastUtil;
import com.donce.common.widget.SimpleAlertDialog;

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

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_second;
    }

    @Override
    protected void onInitView() {
        tvTitle.setText("tab2");
    }


    @OnClick(R.id.btn1)
    public void onClick() {
        SimpleAlertDialogHelper.showAlertDialog(getActivity(), "提示", "这是提示内容", "确定", "取消",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showLongToast(getActivity(), "toast提示");
                    }
                });
    }
}
