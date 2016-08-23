package com.djonce.sample.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.djonce.sample.R;
import com.djonce.sample.ui.fragment.MainFragment;
import com.djonce.sample.ui.fragment.SecondFragment;
import com.djonce.sample.ui.fragment.ThirdFragment;
import com.donce.common.ui.BaseFragmentActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseFragmentActivity {

    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.ivTab1)
    ImageView ivTab1;
    @BindView(R.id.tvTab1)
    TextView tvTab1;
    @BindView(R.id.layout_tab1)
    RelativeLayout layoutTab1;
    @BindView(R.id.ivTab2)
    ImageView ivTab2;
    @BindView(R.id.tvTab2)
    TextView tvTab2;
    @BindView(R.id.layout_tab2)
    RelativeLayout layoutTab2;
    @BindView(R.id.ivTab3)
    ImageView ivTab3;
    @BindView(R.id.tvTab3)
    TextView tvTab3;
    @BindView(R.id.layout_tab3)
    RelativeLayout layoutTab3;
    @BindView(R.id.layoutBottom)
    LinearLayout layoutBottom;

    protected int lastTab = -1;

    @Override
    protected int getFragmentContentId() {
        return R.id.container;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {

        //添加fragment
        addFragment(MainFragment.class, "Tab1");
        addFragment(SecondFragment.class, "Tab2");
        addFragment(ThirdFragment.class, "Tab3");
        //更新tab显示fragment
        updateTabs(0);

    }


    private void updateTabs(int position) {
        clearSelectionTab(); // 清除tab选中状态
        hideAllFragment();//隐藏所有fragment
        setTabPressed(position);   // 设置选中状态

        //显示fragment
        setCurrentShowFragment(position);
        lastTab = position;
    }


    /**
     * 清除tab的选择状态
     */
    private void clearSelectionTab() {
        ivTab1.setImageResource(R.drawable.tab_1);
        SetNormalTabsTxt(tvTab1);

        ivTab2.setImageResource(R.drawable.tab_2);
        SetNormalTabsTxt(tvTab2);

        ivTab3.setImageResource(R.drawable.tab_3);
        SetNormalTabsTxt(tvTab3);

    }

    /**
     * 设置tab点击效果
     *
     * @param position 点击的tab
     */
    private void setTabPressed(int position) {
        switch (position) {
            case 0:
                ivTab1.setImageResource(R.drawable.tab_press_1);
                setPressTabsTxt(tvTab1);
                break;
            case 1:
                ivTab2.setImageResource(R.drawable.tab_press_2);
                setPressTabsTxt(tvTab2);
                break;
            case 2:
                ivTab3.setImageResource(R.drawable.tab_press_3);
                setPressTabsTxt(tvTab3);
                break;
        }
    }

    private void SetNormalTabsTxt(TextView tvTab) {
        tvTab.setTextColor(getResources().getColor(R.color._ffffff));
    }

    private void setPressTabsTxt(TextView tvTab) {
        tvTab.setTextColor(getResources().getColor(R.color._7CC6A0));
    }

    @OnClick({R.id.layout_tab1, R.id.layout_tab2, R.id.layout_tab3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_tab1:
                if (lastTab != 0) {
                    updateTabs(0);
                }
                break;
            case R.id.layout_tab2:
                if (lastTab != 1) {
                    updateTabs(1);
                }
                break;
            case R.id.layout_tab3:
                if (lastTab != 2) {
                    updateTabs(2);
                }
                break;
        }
    }
}
