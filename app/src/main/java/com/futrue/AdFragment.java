package com.futrue;


import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class AdFragment extends android.support.v4.app.Fragment{

//=============================================================================================初始化

    View view;
    ImageView ad_img_ad;
    TextView ad_text_skip;
    View.OnClickListener click_img_ad, click_text_skip;

    LordActivity lordActivity;
    Handler handler;

//============================================================================================生命周期

    //创建
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //传递view
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        initObject();
        initHandler();
        initView(inflater,container);
        addView();
        initListener();
        addListener();
        showAd();
        return view;
    }

    //显示
    @Override
    public void onStart() {
        super.onStart();
    }



//===============================================================================================侍女

    /**
     * 函数：objectInit()
     * 功能：初始化全局对象
     * 后置：onCreate()
     */
    private void initObject(){
        lordActivity = (LordActivity) getActivity();
        assert lordActivity != null;
    }


    /**
     * 函数：handlerInit()
     * 功能；初始化异步消息处理机制
     * 后置：onCreate()
     */
    @SuppressLint("HandlerLeak")
    private void initHandler(){
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch(msg.what){
                    case 0:
                        Toast.makeText(getContext(),"点击广告",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        removeAd();
                        lordActivity.obtainFragmentManager().replaceFragment(R.id.activity_lord,
                                new MainFragment());
                        break;
                }
                handler.removeMessages(msg.what);
            }
        };
    }


    /**
     * 函数：viewInit()
     * 功能：初始化布局，获取空间实例
     * 参数：@inflater, @container
     * 后置：onCreateView()
     */
    private void initView(LayoutInflater inflater,ViewGroup container){
        view = inflater.inflate(R.layout.fragment_ad, container, false);
        ad_img_ad = view.findViewById(R.id.ad_img_ad);
        ad_text_skip = view.findViewById(R.id.ad_text_skip);
    }


    /**
     * 函数：viewAdd()
     * 功能：为view添加内容
     * 后置：onCreateView()
     */
    private void addView(){
        if (Build.VERSION.SDK_INT >= 16){
            ad_img_ad.setBackground(getResources().getDrawable(R.drawable.ad));
        }
    }


    /**
     * 函数：listenerInit()
     * 功能：初始化监听事件
     * 后置：onCreate()
     */
    private void initListener(){
        //点击广告
        click_img_ad = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessage(0);
            }
        };

        //点击跳转
        click_text_skip = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessage(1);
            }
        };
    }


    /**
     * 函数：listenerAdd()
     * 功能：为控件注册事件监听
     * 后置：onCreateView()
     */
    private void addListener(){
        ad_img_ad.setOnClickListener(click_img_ad);
        ad_text_skip.setOnClickListener(click_text_skip);
    }


//===============================================================================================侍卫

    /**
     * 函数：threadAdSleep
     * 功能：主线程睡眠2s后，唤醒主线程
     * 后置：onStart()
     */
    Thread threadShowAd = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                java.lang.Thread.sleep(5000);
                handler.sendEmptyMessage(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });


    /**
     * 函数：showAd()
     * 功能：加载广告
     */
    private void showAd(){
        lordActivity.setTheme(R.style.AdTheme);
        threadShowAd.start();
    }


    /**
     * 函数：removeAd()
     * 功能：关闭广告
     */
    private void removeAd(){
        lordActivity.setTheme(R.style.MainTheme);
        threadShowAd.interrupt();
        Toast.makeText(getContext(),"测试跳转",Toast.LENGTH_SHORT).show();
    }

}
