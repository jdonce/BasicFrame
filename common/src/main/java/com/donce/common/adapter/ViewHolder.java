package com.donce.common.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/***
 * listView或gridView的viewHolder类
 */
public class ViewHolder {
    private SparseArray<View> mViews;
    private int position;
    private View convertView;

    public View getConvertView() {
        return convertView;
    }

    public ViewHolder(Context context, int position, int layoutId, ViewGroup parent) {
        this.position = position;
        this.mViews = new SparseArray<>();
        convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        convertView.setTag(this);
    }

    /**
     * 通过ID得到控价
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public static ViewHolder get(Context context, View convertView, int position, int layoutid, ViewGroup parent) {
        if (convertView == null) {
            return new ViewHolder(context, position, layoutid, parent);
        } else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.position = position;
            return holder;
        }
    }

    /**
     * 为Text赋值
     *
     * @param resId
     * @param text
     * @return
     */
    public ViewHolder setText(int resId, String text) {
        TextView tv = getView(resId);
        tv.setText(text);
        return this;
    }

    public interface OnViewClickListener {
        void onViewClick();
    }

    public void setOnViewClick(int id, final OnViewClickListener listener) {
        View view = getView(id);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onViewClick();
            }
        });
    }


    public int getmPosition() {
        return position;
    }

}
