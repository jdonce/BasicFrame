package com.djonce.sample.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.djonce.sample.R;
import com.donce.common.util.GlideHelper;
import com.donce.common.adapter.RecyclerViewAdapter;
import com.donce.common.adapter.RecyclerViewHolder;

/**
 * Created by Administrator on 2016/7/26 0026.
 */
public class HomeAdapter extends RecyclerViewAdapter<String> {
    public HomeAdapter(Context context) {
        super(context, R.layout.item_recycler);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String s) {
        holder.setTextView(R.id.text, s);
        ImageView imageView = holder.getView(R.id.image);
        GlideHelper.showNetworkIcon(context, "http://www.qq745.com/uploads/allimg/141106/1-141106153Q5.png", imageView);
    }
}
