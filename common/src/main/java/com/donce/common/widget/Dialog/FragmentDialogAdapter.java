package com.donce.common.widget.Dialog;

import android.content.Context;
import android.view.View;

import com.donce.common.R;
import com.donce.common.adapter.CommonAdapter;
import com.donce.common.adapter.ViewHolder;

/**
 * 对话框的适配器
 * Created by Administrator on 2016/9/7 0007.
 */
public class FragmentDialogAdapter extends CommonAdapter<String> {

    private final int view_top_corners = 0;//顶部是圆角
    private final int view_none_corners = 1;//没有圆角 四周都是直角
    private final int view_bottom_corners = 2;//底部是圆角
    private final int view_corners = 3;//圆角 四周都是圆角

    private boolean isHasHeaderView;

    public FragmentDialogAdapter(Context context, boolean isHasHeaderView) {
        super(context, R.layout.item_alert_button);
        this.isHasHeaderView = isHasHeaderView;
    }


    @Override
    public void convert(ViewHolder holder, String s) {
        holder.setText(R.id.tv_alert, s);
        int type = getItemViewType(holder.getmPosition());
        View view = holder.getConvertView();
        View segmentView = holder.getView(R.id.view_segment_line);
        switch (type) {
            case view_top_corners:
                segmentView.setVisibility(View.GONE);
                view.setBackgroundResource(R.drawable.alert_top_corners_selector);
                break;
            case view_bottom_corners:
                segmentView.setVisibility(View.VISIBLE);
                view.setBackgroundResource(R.drawable.alert_bottom_corners_selector);
                break;
            case view_corners:
                segmentView.setVisibility(View.GONE);
                view.setBackgroundResource(R.drawable.alert_corners_selector);
                break;
            case view_none_corners:
            default:
                segmentView.setVisibility(View.VISIBLE);
                view.setBackgroundResource(R.drawable.alert_none_corners_selector);
                break;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {

        if (isHasHeaderView) {//如果列表上有头部View（标题或内容）
            //存在头部view 那就只有如下两种情况
            if (position == getCount() - 1) {//最后一项 也满足只有一个按钮的情况
                return view_bottom_corners;
            } else {//其他选项
                return view_none_corners;
            }
        } else {//没有头部View 则会出现四种情况
            //当只有一个选项时
            if (getCount() == 1) {
                return view_corners;
            } else if (position == 0) {//两个以上时的第一个选项
                return view_top_corners;
            } else if (position == getCount() - 1) {//两个以上时的最后一个选项
                return view_bottom_corners;
            } else {//两个以上时的中间选项
                return view_none_corners;
            }
        }
    }
}
