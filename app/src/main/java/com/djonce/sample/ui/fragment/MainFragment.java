package com.djonce.sample.ui.fragment;


import android.util.Log;
import android.view.View;

import com.djonce.sample.R;
import com.djonce.sample.ui.adapter.HomeAdapter;
import com.donce.common.adapter.RecyclerViewAdapter;
import com.donce.common.ui.BaseFragment;
import com.donce.common.util.XRecyclerViewHelper;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/8/2 0002.
 */
public class MainFragment extends BaseFragment implements XRecyclerView.LoadingListener {
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;

    private List<String> list;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main;
    }

    @Override
    protected void onInitView() {
        initData();


        /*recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        recyclerView.addItemDecoration(new SpacesItemDecoration(10));*/
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        XRecyclerViewHelper.setLayoutManager(getActivity(), recyclerView, true);
        HomeAdapter adapter = new HomeAdapter(getActivity());
        adapter.setDatas(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLoadingListener(this);

        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick( View view, Object o, int position) {
                Log.d("onitem", position + "");
            }
        });
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("选项" + i);
        }
    }

    @Override
    public void onRefresh() {
        Log.d("onRefresh", "=====================");
        //数据加载完成后调用
        recyclerView.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        Log.d("onLoadMore", "=====================");
        //数据加载完成后调用
        recyclerView.loadMoreComplete();
    }

}
