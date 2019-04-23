package com.futrue.follower;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

public class ChildFragmentManager {

    private Fragment fragment;
    private FragmentTransaction transaction;
    private ArrayList<Fragment> fragments = new ArrayList<>();

    /**
     * 函数：FragmentManager()
     * 功能：构造方法
     * 参数：@transaction
     */
    public ChildFragmentManager(Fragment fragment){
        this.fragment = fragment;
    }


    /**
     * 函数：addFragment()
     * 功能：添加碎片
     * 参数：@fragment
     */
    public void addFragment(@IdRes int containerViewId, android.support.v4.app.Fragment fragment){
        transaction = this.fragment.getChildFragmentManager().beginTransaction();
        if (fragments.size() != 0){
            transaction.hide(fragments.get(fragments.size()-1));
        }
        transaction.add(containerViewId,fragment);
        transaction.commit();
        fragments.add(fragment);
    }


    /**
     * 函数：removeFragment()
     * 功能：删除碎片
     * 参数：@fragment
     */
    public void removeFragment(android.support.v4.app.Fragment fragment){
        transaction = this.fragment.getChildFragmentManager().beginTransaction();
        transaction.remove(fragment);
        transaction.commit();
        fragments.remove(fragment);
    }


    /**
     * 函数：replaceFragment()
     * 功能：替代碎片
     * 参数：@fragment
     */
    public void replaceFragment(@IdRes int containerViewId, android.support.v4.app.Fragment fragment){
        transaction = this.fragment.getChildFragmentManager().beginTransaction();
        int size = fragments.size();
        transaction.remove(fragments.get(size-1));
        transaction.add(containerViewId, fragment);
        transaction.commit();
        fragments.remove(size-1);
        fragments.add(fragment);
    }


    /**
     * 函数：replaceFragment()
     * 功能：替代碎片
     * 参数：@fragment
     */
    public void replaceFragment(@IdRes int containerViewId, android.support.v4.app.Fragment fragment, boolean store){
        transaction = this.fragment.getChildFragmentManager().beginTransaction();
        if (store){
            int size = fragments.size();
            transaction.hide(fragments.get(size-1));
            if (fragments.contains(fragment)){
                transaction.show(fragment);
            } else {
                transaction.add(containerViewId, fragment);
                fragments.add(fragment);
            }
            transaction.commit();
        } else {
            replaceFragment(containerViewId,fragment);
        }
    }


    /**
     * 函数：getCount()
     * 功能：获得已加载fragment个数
     */
    public int getCount(){
        return fragments.size();
    }


    /**
     * 函数：backFragment()
     * 功能：返回到之前的状态
     */
    public void backFragment(){
        transaction = this.fragment.getChildFragmentManager().beginTransaction();
        transaction.remove(fragments.get(fragments.size()-1));
        transaction.show(fragments.get(fragments.size()-2));
        transaction.commit();
        fragments.remove(fragments.size()-1);
    }

}
