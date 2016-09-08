package com.donce.common.widget.Dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.donce.common.R;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * 提示框/底部选择框
 * Created by Administrator on 2016/9/6 0006.
 */
public class CustomFragmentDialog extends DialogFragment {
    public static final int CANCEL_POSITION = -1;//点击取消按钮返回 －1，其他按钮从0开始算
    TextView tvTitle;
    TextView tvMessage;
    LinearLayout llAlertButtons;

    View rootView;

    private ArrayList<String> datas = new ArrayList<>();
    private Builder builder;

    public CustomFragmentDialog() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //隐藏默认标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置窗体的背景为透明色
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        //初始化数据
        initData();
        //初始化view
        initViews(inflater, container);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        //设置窗口的宽高和显示位置
        int marginWidth = 0;
        final WindowManager.LayoutParams layoutParams = getDialog().getWindow().getAttributes();
        Builder.Style style = builder.style;
        switch (style) {
            case BottomActionSheet:
//                marginWidth = getResources().getDimensionPixelSize(R.dimen.dp_20);
                layoutParams.gravity = Gravity.BOTTOM;
                break;
            case Alert:
                marginWidth = getResources().getDimensionPixelSize(R.dimen.dp_80);
                layoutParams.gravity = Gravity.CENTER;
                break;
        }
        layoutParams.width = getResources().getDisplayMetrics().widthPixels - marginWidth;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes(layoutParams);

    }


    //初始化数据
    private void initData() {
        if (builder != null) {
            String[] buttons = builder.buttons;
            if (buttons != null && buttons.length > 0) {
                datas.addAll(Arrays.asList(buttons));
            }
        }
    }

    //初始化页面
    private void initViews(LayoutInflater inflater, ViewGroup container) {
        Context context = getContext();
        Builder.Style style = builder.style;
        switch (style) {
            case BottomActionSheet:
                //设置dialog的 进出 动画
                getDialog().getWindow().setWindowAnimations(R.style.animate_bottom_dialog);

                rootView = inflater.inflate(R.layout.layout_alertview_actionsheet, container, false);
                initBottomActionSheet(context, rootView);
                break;
            case Alert:
                //设置dialog的 进出 动画
                getDialog().getWindow().setWindowAnimations(R.style.animate_center_dialog);

                rootView = inflater.inflate(R.layout.layout_alert_view, container, false);
                initAlertViews(context, rootView);
                break;
        }

    }

    //初始化头部view
    private void initCommonHeaderView(View view) {
        tvTitle = (TextView) view.findViewById(R.id.tv_alert_title);
        tvMessage = (TextView) view.findViewById(R.id.tv_alert_msg);
        if (builder != null) {
            setTextViewText(builder.title, tvTitle);
            setTextViewText(builder.message, tvMessage);
        }
    }

    //初始化底部显示选择器
    private void initBottomActionSheet(Context context, View view) {
        //初始化头部
        initCommonHeaderView(view);
        //初始化选择的列表
        ListView alertButtonListView = (ListView) view.findViewById(R.id.alert_button_listView);
        FragmentDialogAdapter fragmentDialogAdapter = new FragmentDialogAdapter(context);
        fragmentDialogAdapter.setmDatas(datas);
        alertButtonListView.setAdapter(fragmentDialogAdapter);
        alertButtonListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (builder != null && builder.onItemButtonClickListener != null) {
                    builder.onItemButtonClickListener.onItemClick(CustomFragmentDialog.this, position);
                }
                dismiss();
            }
        });
        //初始化底部的取消按钮
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_alert_cancel);
        setTextViewText(builder.bottomCancelButton, tvCancel);
        tvCancel.setOnClickListener(new OnTextClickListener(CANCEL_POSITION, builder == null ?
                null : builder.onItemButtonClickListener));
    }

    //初始化提示框的view
    private void initAlertViews(Context context, View view) {
        llAlertButtons = (LinearLayout) view.findViewById(R.id.ll_alert_buttons);
        initCommonHeaderView(view);
        int position = 0;
        for (int i = 0; i < datas.size(); i++) {
            if (i != 0) {
                View divider = new View(context);
                divider.setBackgroundColor(getResources().getColor(R.color.bgColor_divider));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) getResources()
                        .getDimension(R.dimen.dp_1), LinearLayout.LayoutParams.MATCH_PARENT);
                llAlertButtons.addView(divider, params);
            }
            View itemView = LayoutInflater.from(context).inflate(R.layout.item_alert_button, null);
            TextView tvAlert = (TextView) itemView.findViewById(R.id.tv_alert);
            tvAlert.setClickable(true);
            //设置点击效果
            if (datas.size() == 1) {
                tvAlert.setBackgroundResource(R.drawable.alert_bottom_corners_selector);
            } else if (i == 0) {//设置最左边的按钮效果
                tvAlert.setBackgroundResource(R.drawable.alert_bottom_left_corners_selector);
            } else if (i == datas.size() - 1) {//设置最右边的按钮效果
                tvAlert.setBackgroundResource(R.drawable.alert_bottom_right_corners_selector);
            } else {//中间按钮
                tvAlert.setBackgroundResource(R.drawable.alert_none_corners_selector);
            }
            String data = datas.get(i);
            tvAlert.setText(data);
            tvAlert.setOnClickListener(new OnTextClickListener(position, builder == null ?
                    null : builder.onItemButtonClickListener));
            llAlertButtons.addView(itemView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            position++;
        }
    }


    //设置标题
    public void setTitle(String title) {
        setTextViewText(title, tvTitle);
    }

    //设置提示内容
    public void setMessage(String message) {
        setTextViewText(message, tvMessage);
    }

    //设置textView的文本内容
    public void setTextViewText(String text, TextView textView) {
        if (TextUtils.isEmpty(text)) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setText(text);
        }
    }


    private void setBuilder(Builder builder) {
        this.builder = builder;
    }


    public static class Builder {
        public enum Style {
            BottomActionSheet,//底部选择器的形式
            Alert//提示框形式
        }

        private Style style = Style.Alert;
        private String title;
        private String message;
        private String[] buttons;
        private String bottomCancelButton;
        private OnItemButtonClickListener onItemButtonClickListener;

        /**
         * 提示框
         *
         * @param title   标题  为空时，会隐藏标题控件
         * @param message 内容  为空时，会隐藏内容控件
         * @param buttons 按钮
         */
        public Builder(String title, String message, String[] buttons) {
            this.title = title;
            this.message = message;
            this.buttons = buttons;
            this.style = Style.Alert;
        }

        /**
         * 底部选择框
         *
         * @param title              标题  为空时，会隐藏标题控件
         * @param message            内容  为空时，会隐藏内容控件
         * @param buttons            可选择的选项按钮
         * @param bottomCancelButton 底部取消按钮
         */
        public Builder(String title, String message, String[] buttons, String bottomCancelButton) {
            this.title = title;
            this.message = message;
            this.buttons = buttons;
            this.bottomCancelButton = bottomCancelButton;
            this.style = Style.BottomActionSheet;
        }

        public void setOnItemButtonClickListener(OnItemButtonClickListener onItemButtonClickListener) {
            this.onItemButtonClickListener = onItemButtonClickListener;
        }

        public CustomFragmentDialog create() {
            CustomFragmentDialog fragmentDialog = new CustomFragmentDialog();
            fragmentDialog.setBuilder(this);
            return fragmentDialog;
        }

    }

    class OnTextClickListener implements View.OnClickListener {

        private int position;
        private OnItemButtonClickListener onItemButtonClickListener;

        public OnTextClickListener(int position, OnItemButtonClickListener onItemButtonClickListener) {
            this.position = position;
            this.onItemButtonClickListener = onItemButtonClickListener;
        }

        @Override
        public void onClick(View view) {
            if (onItemButtonClickListener != null) {
                onItemButtonClickListener.onItemClick(CustomFragmentDialog.this, position);
            }
            dismiss();
        }
    }

    public interface OnItemButtonClickListener {
        void onItemClick(Object object, int position);
    }

}
