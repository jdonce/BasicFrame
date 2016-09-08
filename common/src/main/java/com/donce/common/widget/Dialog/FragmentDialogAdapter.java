package com.donce.common.widget.Dialog;

import android.content.Context;

import com.donce.common.R;
import com.donce.common.adapter.CommonAdapter;
import com.donce.common.adapter.ViewHolder;

/**
 * 对话框的适配器
 * Created by Administrator on 2016/9/7 0007.
 */
public class FragmentDialogAdapter extends CommonAdapter<String> {

    public FragmentDialogAdapter(Context context) {
        super(context, R.layout.item_alert_button);
    }



    @Override
    public void convert(ViewHolder holder, String s) {
        holder.setText(R.id.tv_alert, s);
    }
}
