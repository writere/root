package com.futrue;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.futrue.follower.ChildFragmentManager;

public class MainFragment extends Fragment{


    private View view;
    private ImageView main_img_home, main_img_carter, main_img_mine;
    private RelativeLayout main_view_home, main_view_carter, main_view_mine;
    private TextView main_text_home, main_text_carter, main_text_mine;

    View.OnClickListener click_view_home,  click_view_carter, click_view_mine;

    private ChildFragmentManager childFragmentManager;
    private HomeFragment homeFragment;
    private CarterFragment carterFragment;
    private MineFragment mineFragment;
    private int tab;

//============================================================================================生命周期


    //创建
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initObject();
        initParam();
    }

    //传递view
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        initView(inflater,container);
        addView();
        initListener();
        addListener();
        return view;
    }


//===============================================================================================侍女


    /**
     * 函数：initObject()
     * 功能：初始化全局对象
     * 后置：init()
     */
    private void initObject(){
        childFragmentManager = new ChildFragmentManager(this);
        homeFragment = new HomeFragment();
        carterFragment = new CarterFragment();
        mineFragment = new MineFragment();
    }


    /**
     * 函数：initParam()
     * 功能：初始化全局变量
     * 后置：onCreate()
     */
    private void initParam(){
        tab = 0;
    }


    /**
     * 函数：initView()
     * 功能：初始化布局，获取空间实例
     * 参数：@inflater, @container
     * 后置：onCreateView()
     */
    private void initView(LayoutInflater inflater,ViewGroup container){
        view = inflater.inflate(R.layout.fragment_main, container, false);
        main_view_home = view.findViewById(R.id.main_view_home);
        main_view_carter = view.findViewById(R.id.main_view_carter);
        main_view_mine = view.findViewById(R.id.main_view_mine);
        main_img_home = view.findViewById(R.id.main_img_home);
        main_img_carter = view.findViewById(R.id.main_img_carter);
        main_img_mine = view.findViewById(R.id.main_img_mine);
        main_text_home = view.findViewById(R.id.main_text_home);
        main_text_carter = view.findViewById(R.id.main_text_carter);
        main_text_mine = view.findViewById(R.id.main_text_mine);
    }


    /**
     * 函数：addView()
     * 功能：为view添加内容
     * 后置：onCreateView()
     */
    private void addView(){
        setFirstTad();
    }


    /**
     * 函数：initListener()
     * 功能：初始化监听事件
     * 后置：onCreate()
     */
    private void initListener(){

        //点击首页
        click_view_home = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTad(0);
            }
        };

        //点击购物车
        click_view_carter = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTad(1);
            }
        };

        //点击我的
        click_view_mine = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTad(2);
            }
        };
    }


    /**
     * 函数：addListener()
     * 功能：为控件注册事件监听
     * 后置：onCreateView()
     */
    private void addListener(){
        main_view_home.setOnClickListener(click_view_home);
        main_view_carter.setOnClickListener(click_view_carter);
        main_view_mine.setOnClickListener(click_view_mine);
    }


//==============================================================================================侍卫

    /**
     * 函数：changeTab()
     * 功能：实现底部的图标切换
     * 参数：@count
     */
    private void changeTad(int tabNum){
        if (tab != tabNum){
            setTabYing(tab);
            setTabYong(tabNum);
            tab = tabNum;
        } else if (tab==0){
//            homeFragment.handler.sendEmptyMessage(1);
        }
    }


    /**
     * 函数：setTabYoung()
     * 功能：设置tab为阳
     * 参数：@tabNum
     */
    private void setTabYong(int tabNum){
        switch (tabNum){
            case 0:
                main_img_home.setImageDrawable(getResources().getDrawable(R.drawable.home_yang));
                main_text_home.setTextColor(getResources().getColor(R.color.mainGreen));
                childFragmentManager.replaceFragment(R.id.fragment_main, homeFragment);
                break;
            case 1:
                main_img_carter.setImageDrawable(getResources().getDrawable(R.drawable.carter_yang));
                main_text_carter.setTextColor(getResources().getColor(R.color.mainGreen));
                childFragmentManager.replaceFragment(R.id.fragment_main, carterFragment);
                break;
            case 2:
                main_img_mine.setImageDrawable(getResources().getDrawable(R.drawable.mine_yang));
                main_text_mine.setTextColor(getResources().getColor(R.color.mainGreen));
                childFragmentManager.replaceFragment(R.id.fragment_main, mineFragment);
                break;
        }
    }


    /**
     * 函数：setTabYin()
     * 功能：设置tab为阴
     * 参数：@tabNum
     */
    private void setTabYing(int tabNum){
        switch (tabNum){
            case 0:
                main_img_home.setImageDrawable(getResources().getDrawable(R.drawable.home_ying));
                main_text_home.setTextColor(getResources().getColor(R.color.colorDark));
                break;
            case 1:
                main_img_carter.setImageDrawable(getResources().getDrawable(R.drawable.carter_ying));
                main_text_carter.setTextColor(getResources().getColor(R.color.colorDark));
                break;
            case 2:
                main_img_mine.setImageDrawable(getResources().getDrawable(R.drawable.mine_ying));
                main_text_mine.setTextColor(getResources().getColor(R.color.colorDark));
                break;
        }
    }


    /**
     * 函数：setFirstTab()
     * 功能：设置第一次的首页布局
     * 后置：setView()
     */
    private void setFirstTad(){
        setTabYing(1);
        setTabYing(2);
        main_img_home.setImageDrawable(getResources().getDrawable(R.drawable.home_yang));
        childFragmentManager.addFragment(R.id.fragment_main, homeFragment);
    }


    /**
     * 函数：obtainChildFragment()
     * 功能：返回子碎片管理
     */
    public ChildFragmentManager obtainChildFragment(){
        return childFragmentManager;
    }

}
