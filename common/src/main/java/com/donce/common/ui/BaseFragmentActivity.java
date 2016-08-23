package com.donce.common.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * FragmentActivity 基类
 * Created by Administrator on 2016/8/2 0002.
 */
public abstract class BaseFragmentActivity extends FragmentActivity {
    private Unbinder unbinder;
    List<FragmentHolder> fragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        unbinder = ButterKnife.bind(this);
        fragmentList = new ArrayList<>();
        onInitView(savedInstanceState);
    }


    //添加fragment
    protected void addFragment(Class fragmentClass, String tag) {
        FragmentHolder fragmentHolder = new FragmentHolder();
        fragmentHolder.fragmentClass = fragmentClass;
        fragmentHolder.tabIndex = fragmentList.size();
        fragmentHolder.tag = tag;

        fragmentList.add(fragmentHolder);
    }

    //设置当前显示的fragment
    public void setCurrentShowFragment(int index) {
        if (index >= 0 && index < fragmentList.size()) {
            FragmentHolder holder = fragmentList.get(index);
            showFragment(holder);
        }
    }

    //显示fragment
    private void showFragment(FragmentHolder fragmentHolder) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(fragmentHolder.tag);
        if (fragment == null) {
            fragment = getFragmentInstance(fragmentHolder.tag);
            fragmentTransaction.add(getFragmentContentId(), fragment, fragmentHolder.tag);
        } else {
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
    }

    //隐藏所有的fragment
    protected void hideAllFragment() {
        if (fragmentList == null || fragmentList.size() == 0) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        for (FragmentHolder holder : fragmentList) {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(holder.tag);
            if (fragment != null && !fragment.isHidden()) {
                transaction.hide(fragment);
            }
        }
        transaction.commit();
    }

    private Fragment getFragmentInstance(String tag) {
        Fragment fragment = null;
        for (FragmentHolder holder : fragmentList) {
            if (TextUtils.equals(tag, holder.tag)) {
                try {
                    fragment = (Fragment) Class.forName(holder.fragmentClass.getName()).newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return fragment;
    }

    //布局中Fragment的ID
    protected abstract int getFragmentContentId();

    protected abstract int getLayoutResource();

    protected abstract void onInitView(Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private static class FragmentHolder {
        public String tag;
        public Class fragmentClass;
        public int tabIndex;
    }
}
