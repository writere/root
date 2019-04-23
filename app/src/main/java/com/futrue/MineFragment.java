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
import com.futrue.follower.TcpManager;
import com.futrue.following.OvalImageView;

public class MineFragment extends Fragment {


    LordActivity lordActivity;
    Context context;
    MainFragment mainFragment;
    Handler handler;
    ImageServer imageServer;

    View view;
    TextView mine_text_version, mine_text_name, mine_text_place, mine_text_phone;
    ImageView mine_img_set;
    OvalImageView mine_img_icon;
    LinearLayout mine_view_order, mine_view_comment, mine_view_discount, mine_view_score,
            mine_view_shop, mine_view_friend, mine_view_ask, mine_view_invent, mine_view_contact;



    View.OnClickListener click_text_version, click_img_set, click_img_icon, click_text_name,
            click_text_phone, click_text_place, click_view_order, click_view_comment, click_view_discount,
            click_view_score, click_view_shop, click_view_friend, click_view_ask, click_view_invent,
            click_view_contact;




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
        mainFragment = (MainFragment) getParentFragment();
        imageServer = new ImageServer();
    }


    /**
     * 函数：initView()
     * 功能：初始化布局，获取空间实例
     * 参数：@inflater, @container
     * 后置：onCreateView()
     */
    private void initView(LayoutInflater inflater,ViewGroup container){                             //initView()
        view = inflater.inflate(R.layout.fragment_mine, container, false);
        mine_img_icon = view.findViewById(R.id.mine_img_icon);
        mine_img_set = view.findViewById(R.id.mine_img_set);
        mine_text_name = view.findViewById(R.id.mine_text_name);
        mine_text_place = view.findViewById(R.id.mine_text_place);
        mine_text_phone = view.findViewById(R.id.mine_text_phone);
        mine_view_order = view.findViewById(R.id.mine_view_order);
        mine_view_comment = view.findViewById(R.id.mine_view_comment);
        mine_view_discount = view.findViewById(R.id.mine_view_discount);
        mine_view_shop = view.findViewById(R.id.mine_view_shop);
        mine_view_friend = view.findViewById(R.id.mine_view_friend);
        mine_view_ask = view.findViewById(R.id.mine_view_ask);
        mine_view_invent = view.findViewById(R.id.mine_view_invent);
        mine_view_contact = view.findViewById(R.id.mine_view_contact);
        mine_view_score = view.findViewById(R.id.mine_view_score);
    }


    /**
     * 函数：initListener()
     * 功能：初始化监听事件
     * 后置：onCreate()
     */
    private void initListener(){                                                                    //initListener()

        //点击设置
        click_img_set = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"跳转设置页，待开发", Toast.LENGTH_SHORT).show();
            }
        };

        //点击头像
        click_img_icon = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"更换头像，待开发", Toast.LENGTH_SHORT).show();
            }
        };

        //点击用户名
        click_text_name = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"修改用户信息，待开发", Toast.LENGTH_SHORT).show();
            }
        };

        //点击手机号
        click_text_phone = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"修改用户信息，待开发", Toast.LENGTH_SHORT).show();
            }
        };

        //点击地址
        click_text_place = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"管理收货地址，待开发", Toast.LENGTH_SHORT).show();
            }
        };

        //点击订单
        click_view_order = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"查看所有订单，待开发", Toast.LENGTH_SHORT).show();
            }
        };

        //点击订单
        click_view_comment = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击评论，待开发", Toast.LENGTH_SHORT).show();
            }
        };

        //点击优惠券
        click_view_discount = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"查看我的优惠券，待开发", Toast.LENGTH_SHORT).show();
            }
        };

        //点击积分
        click_view_score = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"查看我的积分，待开发", Toast.LENGTH_SHORT).show();
            }
        };

        //点击商家入驻
        click_view_shop = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击商家入驻，待开发", Toast.LENGTH_SHORT).show();
            }
        };

        //点击我要合作
        click_view_friend = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击我要合合作，待开发", Toast.LENGTH_SHORT).show();
            }
        };

        //点击客服中心
        click_view_ask = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击客服中心，待开发", Toast.LENGTH_SHORT).show();
            }
        };

        //点击邀请有奖
        click_view_invent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击邀请有奖，待开发", Toast.LENGTH_SHORT).show();
            }
        };

        //点击联系我们
        click_view_invent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击联系我们，待开发", Toast.LENGTH_SHORT).show();
            }
        };


    }


    /**
     * 函数：addListener()
     * 功能：为初始化的控件注册事件监听
     * 后置：onCreateView()
     */
    private void addListener(){                                                                     //addInitListener()
        mine_img_set.setOnClickListener(click_img_set);
        mine_img_icon.setOnClickListener(click_img_icon);
        mine_text_name.setOnClickListener(click_text_name);
        mine_text_place.setOnClickListener(click_text_place);
        mine_text_phone.setOnClickListener(click_text_phone);
        mine_view_order.setOnClickListener(click_view_order);
        mine_view_comment.setOnClickListener(click_view_comment);
        mine_view_discount.setOnClickListener(click_view_discount);
        mine_view_score.setOnClickListener(click_view_score);
        mine_view_shop.setOnClickListener(click_view_shop);
        mine_view_friend.setOnClickListener(click_view_friend);
        mine_view_ask.setOnClickListener(click_view_ask);
        mine_view_invent.setOnClickListener(click_view_invent);
        mine_view_contact.setOnClickListener(click_view_contact);
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
     * 功能：封装形成图片刷新的请求
     * 后置：load()
     */
    private String makePhotoRequest(){
        return "8#"+"1";
    }


    /**
     * 函数：paintImage()
     * 功能：绘制刷新快拍
     * 参数：@scream
     * 备注：信息流（昵称_头像编号_头像框编号_快拍编号）
     */
    private void paintMine(String mineImage){
        String[] mines = mineImage.split("%");
        mine_img_icon.setTag(mines[0]);
        mine_text_name.setText(mines[1]);
        mine_text_phone.setText(mines[2]);
        mine_text_place.setText(mines[3]);

        new ImageServer().setBitmap(mine_img_icon, context);
    }

    /**
     * 函数：paintNew()
     * 功能：加载刷新内容
     */
    private void paintNew(String[] screams){
        String mineScream = screams[1];
        paintMine(mineScream);
    }

}
