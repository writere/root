package com.futrue;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.futrue.follower.ImageServer;
import com.futrue.follower.SystemServer;
import com.futrue.follower.ResultServer;
import com.futrue.follower.SharePreferencesManager;
import com.futrue.follower.TcpManager;

public class LookFragment extends Fragment{



    LordActivity lordActivity;
    Context context;
    Handler handler;

    View view;
    ImageView list_img_back, list_img_order, list_img_choose;
    TextView list_text_remove, list_text_order, list_text_distance, list_text_sale, list_text_choose,
            list_text_find;
    LinearLayout list_view_result;


    View.OnClickListener click_img_back, click_img_order, click_img_choose, click_text_remove,
        click_text_order, click_text_distance, click_text_sale, click_text_choose;




//============================================================================================生命周期


    //传递View
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {                      //onCreateView()
        init(inflater, container);
        refresh();
        return view;
    }


    //运行
    @Override
    public void onStart() {
        super.onStart();
    }

//===============================================================================================侍女

    /**
     * 函数：init()
     * 功能：初始化
     * 参数: init()
     */
    private void init(LayoutInflater inflater, ViewGroup container){                                //init()
        initObject();
        initView(inflater,container);
        addView();
        initListener();
        addListener();
        initHandler();
    }


    /**
     * 函数：initObject()
     * 功能：初始化全局对象
     * 后置：init()
     */
    private void initObject(){                                                                      //initObject()
        lordActivity = (LordActivity) getActivity();
        context = getContext();
    }


    /**
     * 函数：initView()
     * 功能：初始化布局，获取空间实例
     * 参数：@inflater, @container
     * 后置：onCreateView()
     */
    private void initView(LayoutInflater inflater, ViewGroup container){                             //initView()
        view = inflater.inflate(R.layout.fragment_look, container, false);
        list_img_back = view.findViewById(R.id.list_img_back);
        list_img_order = view.findViewById(R.id.list_img_order);
        list_img_choose = view.findViewById(R.id.list_img_choose);
        list_text_find = view.findViewById(R.id.list_text_find);
        list_text_remove = view.findViewById(R.id.list_text_remove);
        list_text_order = view.findViewById(R.id.list_text_order);
        list_text_distance = view.findViewById(R.id.list_text_distance);
        list_text_sale = view.findViewById(R.id.list_text_sale);
        list_text_choose = view.findViewById(R.id.list_text_choose);
        list_view_result = view.findViewById(R.id.list_view_result);
    }


    /**
     * 函数：addView()
     * 功能：初始化部分控件
     */
    private void addView(){
        list_text_find.setText(new SharePreferencesManager(lordActivity).getSingleHistory());
    }


    /**
     * 函数：initListener()
     * 功能：初始化监听事件
     * 后置：onCreate()
     */
    private void initListener(){                                                                    //initListener()

        //点击返回
        click_img_back = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击返回", Toast.LENGTH_SHORT).show();
                lordActivity.obtainFragmentManager().backFragment();
            }
        };

        //点击取消
        click_text_remove = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击取消", Toast.LENGTH_SHORT).show();
                lordActivity.obtainFragmentManager().backFragment();
                lordActivity.obtainFragmentManager().backFragment();
            }
        };

        //点击综合排序
        click_text_order = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击综合排序", Toast.LENGTH_SHORT).show();
            }
        };

        //点击综合排序图标
        click_img_order = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击综合排序图标", Toast.LENGTH_SHORT).show();
            }
        };

        //点击距离排序
        click_text_distance = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击距离排序", Toast.LENGTH_SHORT).show();
            }
        };

        //点击销量排序
        click_text_sale = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击销量排序", Toast.LENGTH_SHORT).show();
            }
        };

        //点击筛选
        click_text_choose = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击筛选", Toast.LENGTH_SHORT).show();
            }
        };

        //点击筛选图标
        click_text_sale = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击筛选图标", Toast.LENGTH_SHORT).show();
            }
        };

    }

    /**
     * 函数：addListener()
     * 功能：为初始化的控件注册事件监听
     * 后置：onCreateView()
     */
    private void addListener(){                                                                     //addInitListener()
        list_img_back.setOnClickListener(click_img_back);
        list_img_order.setOnClickListener(click_img_order);
        list_img_choose.setOnClickListener(click_img_choose);
        list_text_remove.setOnClickListener(click_text_remove);
        list_text_order.setOnClickListener(click_text_order);
        list_text_sale.setOnClickListener(click_text_sale);
        list_text_distance.setOnClickListener(click_text_distance);
        list_text_choose.setOnClickListener(click_text_choose);
    }


    /**
     * 函数：initHandler()
     * 功能；初始化异步消息处理机制
     * 后置：onCreate()
     */
    @SuppressLint("HandlerLeak")
    private void initHandler(){                                                                     //initHandler()
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch(msg.what){
                    case 0:
                        Log.d("接收信息：", msg.obj.toString());
                        String[] resp = msg.obj.toString().split("#");
                        switch (resp[0]) {
                            case "0":
                                paintNew(resp);
                                Toast.makeText(lordActivity, "刷新成功", Toast.LENGTH_SHORT).show();
                                break;
                            case "1":
                                Toast.makeText(lordActivity, "没有刷新内容", Toast.LENGTH_SHORT).show();
                                break;
                            case "2":
                                Toast.makeText(lordActivity, "系统异常", Toast.LENGTH_SHORT).show();
                                break;
                            case "a":
                                Toast.makeText(lordActivity, "获取服务器地址失败", Toast.LENGTH_SHORT).show();
                                break;
                            case "b":
                                Toast.makeText(lordActivity, "连接服务器失败", Toast.LENGTH_SHORT).show();
                                break;
                            case "c":
                                Toast.makeText(lordActivity, "发送请求失败", Toast.LENGTH_SHORT).show();
                                break;
                            case "d":
                                Toast.makeText(lordActivity, "接收返回信息失败", Toast.LENGTH_SHORT).show();
                                break;
                            case "e":
                                Toast.makeText(lordActivity, "系统繁忙", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                Toast.makeText(lordActivity, "未知错误", Toast.LENGTH_SHORT).show();

                                break;
                        }
                        break;
                    case 1:
                        break;
                }
                handler.removeMessages(msg.what);
            }
        };
    }

//==============================================================================================侍卫

    /**
     * 函数：load()
     * 功能：加载刷新首页
     */
    private void refresh(){                                                                            //load()
        if (new SystemServer().isNetworkConnected(context)){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    TcpManager tcp = new TcpManager(makePhotoRequest());
                    Message msg = new Message();
                    msg.obj = tcp.getResponse();
                    msg.what = 0;
                    handler.sendMessage(msg);
                }
            }).start();
        } else {
            Toast.makeText(lordActivity, "网络未连接", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 函数：makePhotoRequest()
     * 功能：封装形成查询请求
     * 后置：load()
     */
    private String makePhotoRequest(){
        String Id_user = new SharePreferencesManager(lordActivity).getUserId();
        return "6#"+Id_user+"#"+ list_text_find.getText().toString();
    }



    /**
     * 函数：paintNew()
     * 功能：加载刷新内容
     */
    private void paintNew(String[] screams){
        String resultScream = screams[1];
        if (resultScream != null){
            new ResultServer(resultScream, getContext(), lordActivity, list_view_result, this).paintResult();
        }
    }


}
