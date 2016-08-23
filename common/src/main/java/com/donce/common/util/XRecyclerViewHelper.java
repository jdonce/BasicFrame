package com.donce.common.util;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.donce.common.widget.DefaultDividerItemDecoration;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * XRecyclerView帮助类
 * Created by Administrator on 2016/8/1 0001.
 */
public class XRecyclerViewHelper {

    public static void setLayoutManager(Context context, XRecyclerView xRecyclerView, boolean isAddItemDecoration) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        xRecyclerView.setLayoutManager(layoutManager);
        if (isAddItemDecoration) {
            //添加分割线 建议在选项布局中增加view 也可如下方式添加
            xRecyclerView.addItemDecoration(new DefaultDividerItemDecoration(context,
                    DefaultDividerItemDecoration.VERTICAL_LIST));
        }
        //设置recyclerView的样式
        setViewStyle(xRecyclerView);

    }

    public static void setGridLayoutManager(Context context, XRecyclerView recyclerView, int spanCount) {
        GridLayoutManager manager = new GridLayoutManager(context, spanCount);
        recyclerView.setLayoutManager(manager);
        //设置recyclerView的样式
        setViewStyle(recyclerView);

    }

    public static void setStaggeredGridLayoutManager(Context context, XRecyclerView recyclerView, int spanCount) {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(spanCount,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        //设置recyclerView的样式
        setViewStyle(recyclerView);
    }

    //设置recyclerView的样式
    private static void setViewStyle(XRecyclerView recyclerView) {
        //设置刷新进度的样式
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        //设置加载更多的进度的样式
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        //可设置设置刷新箭头图标
//        xRecyclerView.setArrowImageView();
    }
}
