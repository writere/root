package com.futrue;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.futrue.follower.SharePreferencesManager;


public class FindFragment extends Fragment {


    LordActivity lordActivity;
    Context context;

    View view;
    ImageView find_img_back;
    EditText find_edit_find;
    TextView find_text_find, find_text_history;


    View.OnClickListener click_img_back, click_text_find, click_text_history;




//============================================================================================生命周期


    //传递View
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        init(inflater, container);
        return view;
    }


//===============================================================================================侍女

    /**
     * 函数：init()
     * 功能：初始化
     * 参数: init()
     */
    private void init(LayoutInflater inflater, ViewGroup container){
        initObject();
        initView(inflater,container);
        addView();
        initListener();
        addListener();
    }


    /**
     * 函数：initObject()
     * 功能：初始化全局对象
     * 后置：init()
     */
    private void initObject(){
        lordActivity = (LordActivity) getActivity();
        context = getContext();
    }


    /**
     * 函数：initView()
     * 功能：初始化布局，获取空间实例
     * 参数：@inflater, @container
     * 后置：onCreateView()
     */
    private void initView(LayoutInflater inflater,ViewGroup container){
        view = inflater.inflate(R.layout.fragment_find, container, false);
        find_img_back = view.findViewById(R.id.find_img_back);
        find_edit_find = view.findViewById(R.id.find_edit_find);
        find_text_find = view.findViewById(R.id.find_text_find);
        find_text_history = view.findViewById(R.id.find_text_history);
    }

    /**
     * 函数：addView()
     * 功能：初始化部分控件
     */
    private void addView(){
        find_text_history.setText(new SharePreferencesManager(lordActivity).getSingleHistory());
    }

    /**
     * 函数：initListener()
     * 功能：初始化监听事件
     * 后置：onCreate()
     */
    private void initListener(){

        //点击返回
        click_img_back = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击返回", Toast.LENGTH_SHORT).show();
                lordActivity.obtainFragmentManager().backFragment();
            }
        };

        //点击搜索
        click_text_find = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击搜索", Toast.LENGTH_SHORT).show();
                new SharePreferencesManager(lordActivity).
                        addSingleHistory(find_edit_find.getText().toString());
                lordActivity.obtainFragmentManager().addFragment(R.id.activity_lord, new LookFragment());
                addView();
            }
        };

        //点击历史记录
        click_text_history = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击历史记录", Toast.LENGTH_SHORT).show();
                lordActivity.obtainFragmentManager().addFragment(R.id.activity_lord, new LookFragment());
            }
        };


    }

    /**
     * 函数：addListener()
     * 功能：为初始化的控件注册事件监听
     * 后置：onCreateView()
     */
    private void addListener(){
        find_img_back.setOnClickListener(click_img_back);
        find_text_find.setOnClickListener(click_text_find);
        find_text_history.setOnClickListener(click_text_history);
    }




//==============================================================================================侍卫



}
