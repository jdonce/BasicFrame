package com.donce.common.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * RecyclerView的viewHolder封装类
 * Created by Administrator on 2016/7/26 0026.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private View convertView;
    private SparseArray<View> views;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        this.convertView = itemView;
        views = new SparseArray<>();
    }

    public static RecyclerViewHolder getViewHolder(Context context, ViewGroup parent, int resuorce) {
        View view = LayoutInflater.from(context).inflate(resuorce, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    /***
     * 根据viewId获取控件
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
        }
        return (T) view;
    }

    public View getConvertView() {
        return convertView;
    }

    public RecyclerViewHolder setTextView(int viewId, String text) {
        TextView textView = getView(viewId);
        textView.setText(text);
        return this;
    }

    public RecyclerViewHolder setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public RecyclerViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public RecyclerViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }
}
