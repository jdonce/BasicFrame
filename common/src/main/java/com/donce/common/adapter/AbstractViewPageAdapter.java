package com.donce.common.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 抽象的PagerAdapter实现类,封装了内容为View的公共操作
 *
 * @param <T>
 */
public abstract class AbstractViewPageAdapter<T> extends PagerAdapter {
    protected List<T> mData;
    private SparseArray<View> mViews;
    public Context mContext;

    protected AbstractViewPageAdapter(Context context, List<T> mData) {
        this.mContext = context;
        this.mData = mData;
        mViews = new SparseArray<View>(mData.size());
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViews.get(position);
        if (view == null) {
            view = newView(position);
            mViews.put(position, view);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
    }

    public T getItem(int position) {
        return mData.get(position);
    }

    public abstract View newView(int position);
}
