package com.donce.common.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.donce.common.R;
import com.donce.common.util.AppUtil;


/**
 * 提示框
 * Created by Administrator on 2016/5/25 0025.
 */
public class SimpleAlertDialog extends Dialog {
    TextView tvTitle;//标题
    TextView tvMessage;//提示的内容
    TextView tvLeft;//左边按钮
    View viewLine;//按钮间的分隔线
    TextView tvRight;//右边按钮

    Context context;

    public SimpleAlertDialog(Context context) {
        super(context, R.style.loading_dialog);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvMessage = (TextView) findViewById(R.id.tv_message);
        tvLeft = (TextView) findViewById(R.id.tv_left);
        viewLine = findViewById(R.id.view_line);
        tvRight = (TextView) findViewById(R.id.tv_right);

        //设置dialog的大小
        int marginWidth = (int) context.getResources().getDimension(R.dimen.dp_100);
        DisplayMetrics dm = AppUtil.getScreenWidthHeightInPx(context);
        getWindow().setLayout(dm.widthPixels - marginWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    public void setTitle(String title) {
        if (tvTitle != null) {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        }
    }

    public void setMessage(String message) {
        if (!TextUtils.isEmpty(message)) {
            tvMessage.setVisibility(View.VISIBLE);
            tvMessage.setText(message);
        }
    }

    public void showSegment() {
        viewLine.setVisibility(View.VISIBLE);
    }


    public void setLeftButton(String btnName) {
        setButton(btnName, tvLeft, null);
    }

    public void setLeftButton(String btnName, View.OnClickListener clickListener) {

        setButton(btnName, tvLeft, clickListener);
    }

    public void setRightButton(String btnName) {
        setButton(btnName, tvRight, null);
    }

    public void setRightButton(String btnName, View.OnClickListener clickListener) {

        setButton(btnName, tvRight, clickListener);
    }


    private void setButton(String btnName, TextView btnView, final View.OnClickListener clickListener) {
        btnView.setVisibility(View.VISIBLE);
        btnView.setText(btnName);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onClick(v);
                }
                if (isShowing()) {
                    dismiss();
                }
            }
        });
    }


}
