package com.donce.common.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView的适配器封装类
 * Created by Administrator on 2016/7/26 0026.
 */
public abstract class RecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {
    private List<T> datas;
    protected Context context;
    private int layoutId;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public RecyclerViewAdapter(Context context, int layoutId) {
        this.context = context;
        this.layoutId = layoutId;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public List<T> getDatas() {
        if (datas == null) {
            datas = new ArrayList<>();
        }
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewHolder recyclerViewHolder = RecyclerViewHolder.getViewHolder(context, parent, layoutId);

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        setListener(holder, position);
        convert(holder, getDatas().get(position));

    }

    protected int getPosition(RecyclerViewHolder holder) {
        return holder.getAdapterPosition();
    }

    //设置选项点击和长按事件
    private void setListener(RecyclerViewHolder holder, final int position) {
        //选项点击事件
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, datas.get(position), position);
                }
            }
        });

        //选项长按事件
        holder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemLongClickListener != null) {
                    onItemLongClickListener.onItemLongClick(v, datas.get(position), position);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (datas.size() == 0 || datas == null) {
            return 0;
        }
        return datas.size();
    }


    public abstract void convert(RecyclerViewHolder holder, T t);

    public interface OnItemClickListener<T> {
        void onItemClick(View view, T t, int position);

    }

    public interface OnItemLongClickListener<T> {
        boolean onItemLongClick(View view, T t, int position);
    }
}
