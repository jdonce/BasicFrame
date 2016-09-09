package com.djonce.sample.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.djonce.sample.R;
import com.donce.common.ui.BaseFragment;
import com.donce.common.util.ToastUtil;
import com.donce.common.widget.Dialog.CustomFragmentDialog;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/8/2 0002.
 */
public class ThirdFragment extends BaseFragment implements CustomFragmentDialog.OnItemButtonClickListener {
    @BindView(R.id.tv_title)
    TextView tvTitle;


    private CustomFragmentDialog alertDialog;
    private CustomFragmentDialog bottomDialog;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_third;
    }

    @Override
    protected void onInitView() {
        tvTitle.setText("TAB3");
    }


    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn1_bottom1, R.id.btn1_bottom2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1: {
                CustomFragmentDialog.Builder builder = new CustomFragmentDialog.Builder("这是标题", "这是内容",
                        new String[]{"取消", "确定"});
                builder.setOnItemButtonClickListener(this);
                alertDialog = builder.create();
                alertDialog.show(getFragmentManager(), "alert");

            }
            break;
            case R.id.btn2: {
                CustomFragmentDialog.Builder builder = new CustomFragmentDialog.Builder("这是标题", "这是内容",
                        new String[]{"确定"});
                builder.setOnItemButtonClickListener(this);
                alertDialog = builder.create();
                alertDialog.show(getFragmentManager(), "alert");

            }
            break;
            case R.id.btn3: {
                CustomFragmentDialog.Builder builder = new CustomFragmentDialog.Builder("这是标题", "这是内容",
                        new String[]{"选项1", "选项2", "选项3"}, CustomFragmentDialog.Builder.VERTICAL);
                builder.setOnItemButtonClickListener(this);
                alertDialog = builder.create();
                alertDialog.show(getFragmentManager(), "alert");
            }
            break;
            case R.id.btn1_bottom1: {
                CustomFragmentDialog.Builder builder = new CustomFragmentDialog.Builder("这是标题", "这是内容",
                        new String[]{"选项1", "选项2", "选项3"}, "cancel");
                builder.setOnItemButtonClickListener(this);
                bottomDialog = builder.create();
                bottomDialog.show(getFragmentManager(), "alert");
            }
            break;
            case R.id.btn1_bottom2:
                CustomFragmentDialog.Builder builder = new CustomFragmentDialog.Builder(new String[]{"选项1",
                        "选项2", "选项3"}, "cancel");
                builder.setOnItemButtonClickListener(this);
                bottomDialog = builder.create();
                bottomDialog.show(getFragmentManager(), "alert");
                break;
        }
    }

    @Override
    public void onItemClick(Object object, int position) {
        String objectStr;
        if (object.equals(alertDialog)) {
            objectStr = "alert";
        } else {
            objectStr = "bottom";
        }
        ToastUtil.showShortToast(getContext(), objectStr + "窗口" + "点击按钮" + position + "");
    }
}
