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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.futrue.follower.ImageServer;
import com.futrue.follower.SystemServer;
import com.futrue.follower.ResultServer;
import com.futrue.follower.SharePreferencesManager;
import com.futrue.follower.TcpManager;
import com.futrue.following.OvalImageView;


public class HomeFragment extends Fragment{


    LordActivity lordActivity;
    Context context;
    MainFragment mainFragment;
    Handler handler;

    View view;
    ImageView home_img_font, home_img_message, home_img_find, home_img_order, home_img_choose,
            home_img_ad;
    RelativeLayout home_view_find, home_view_00, home_view_01, home_view_02, home_view_03, home_view_04,
            home_view_10, home_view_11, home_view_12, home_view_13, home_view_14,
            home_view_20, home_view_21, home_view_22, home_view_23;
    LinearLayout home_view_result,home_view_location;
    TextView home_text_find, home_text_order, home_text_distance, home_text_sale, home_text_choose;
    OvalImageView home_img_20, home_img_21, home_img_22, home_img_23, home_img_24, home_img_25,
            home_img_26, home_img_27;


    View.OnClickListener click_img_font, click_img_message, click_view_find, click_view_00,click_view_01,
            click_view_02, click_view_03, click_view_04, click_view_10, click_view_11, click_view_12,
            click_view_13, click_view_14, click_view_location, click_view_20, click_view_21, click_view_22,
            click_view_23, click_view_ad, click_img_order, click_img_choose, click_text_order,
            click_text_distance, click_text_sale,click_text_choose, click_img_ad;




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
    }


    /**
     * 函数：initView()
     * 功能：初始化布局，获取空间实例
     * 参数：@inflater, @container
     * 后置：onCreateView()
     */
    private void initView(LayoutInflater inflater, ViewGroup container){                             //initView()
        view = inflater.inflate(R.layout.fragment_home, container, false);
        home_img_font = view.findViewById(R.id.home_img_font);
        home_img_message = view.findViewById(R.id.home_img_message);
        home_view_find = view.findViewById(R.id.home_view_find);
        home_text_find = view.findViewById(R.id.home_text_find);
        home_img_find = view.findViewById(R.id.home_img_find);
        home_view_00 = view.findViewById(R.id.home_view_00);
        home_view_01 = view.findViewById(R.id.home_view_01);
        home_view_02 = view.findViewById(R.id.home_view_02);
        home_view_03 = view.findViewById(R.id.home_view_03);
        home_view_04 = view.findViewById(R.id.home_view_04);
        home_view_10 = view.findViewById(R.id.home_view_10);
        home_view_11 = view.findViewById(R.id.home_view_11);
        home_view_12 = view.findViewById(R.id.home_view_12);
        home_view_13 = view.findViewById(R.id.home_view_13);
        home_view_14 = view.findViewById(R.id.home_view_14);
        home_view_location = view.findViewById(R.id.home_view_location);
        home_view_20 = view.findViewById(R.id.home_view_20);
        home_view_21 = view.findViewById(R.id.home_view_21);
        home_view_22 = view.findViewById(R.id.home_view_22);
        home_view_23 = view.findViewById(R.id.home_view_23);
        home_img_ad = view.findViewById(R.id.home_img_ad);
        home_img_order = view.findViewById(R.id.home_img_order);
        home_img_choose = view.findViewById(R.id.home_img_choose);
        home_text_order = view.findViewById(R.id.home_text_order);
        home_text_distance = view.findViewById(R.id.home_text_distance);
        home_text_sale = view.findViewById(R.id.home_text_sale);
        home_text_choose = view.findViewById(R.id.home_text_choose);
        home_img_20 = view.findViewById(R.id.home_img_20);
        home_img_21 = view.findViewById(R.id.home_img_21);
        home_img_22 = view.findViewById(R.id.home_img_22);
        home_img_23 = view.findViewById(R.id.home_img_23);
        home_img_24 = view.findViewById(R.id.home_img_24);
        home_img_25 = view.findViewById(R.id.home_img_25);
        home_img_26 = view.findViewById(R.id.home_img_26);
        home_img_27 = view.findViewById(R.id.home_img_27);
        home_view_result = view.findViewById(R.id.home_view_result);

    }


    /**
     * 函数：initListener()
     * 功能：初始化监听事件
     * 后置：onCreate()
     */
    private void initListener(){                                                                    //initListener()

        //点击logo
        click_img_font = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击logo", Toast.LENGTH_SHORT).show();
            }
        };

        //点击消息
        click_img_message = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击消息，待开发！", Toast.LENGTH_SHORT).show();
            }
        };

        //点击搜索
        click_view_find = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lordActivity.obtainFragmentManager().addFragment(R.id.activity_lord, new FindFragment());
            }
        };

        //点击早餐
        click_view_00 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击早餐，待开发！", Toast.LENGTH_SHORT).show();
            }
        };

        //滑午餐
        click_view_01 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击午餐，待开发！", Toast.LENGTH_SHORT).show();
            }
        };

        //点击晚餐
        click_view_02 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击晚餐，待开发！", Toast.LENGTH_SHORT).show();
            }
        };

        //点击日韩料理
        click_view_03 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击日韩料理，待开发！", Toast.LENGTH_SHORT).show();
            }
        };

        //点击西餐
        click_view_04 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击西餐，待开发！", Toast.LENGTH_SHORT).show();
            }
        };

        //点击饮品甜点
        click_view_10 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击饮品甜点，待开发！", Toast.LENGTH_SHORT).show();
            }
        };

        //点击超市
        click_view_11 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击超市，待开发！", Toast.LENGTH_SHORT).show();
            }
        };

        //点击浪漫鲜花
        click_view_12 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击浪漫鲜花，待开发！", Toast.LENGTH_SHORT).show();
            }
        };

        //点击水果
        click_view_13 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击水果，待开发！", Toast.LENGTH_SHORT).show();
            }
        };

        //点击跑腿代购
        click_view_14 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击跑腿代购，待开发！", Toast.LENGTH_SHORT).show();
            }
        };

        //点击位置
        click_view_location = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击位置，待开发！", Toast.LENGTH_SHORT).show();
            }
        };

        //点击限时抢购
        click_view_20 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击地址，待开发！", Toast.LENGTH_SHORT).show();
            }
        };

        //点击新品优惠
        click_view_21 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击新品优惠，待开发！", Toast.LENGTH_SHORT).show();
            }
        };

        //点击耍大牌
        click_view_22 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击耍大牌，待开发！", Toast.LENGTH_SHORT).show();
            }
        };

        //点击今日特价
        click_view_23 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击今日特价，待开发！", Toast.LENGTH_SHORT).show();
            }
        };

        //点击广告
        click_view_ad = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击广告，待开发！", Toast.LENGTH_SHORT).show();
            }
        };

        //点击排序图标
        click_img_order = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击排序图标，待开发！", Toast.LENGTH_SHORT).show();
            }
        };

        //点击筛选图标
        click_img_choose = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击筛选图标，待开发！", Toast.LENGTH_SHORT).show();
            }
        };

        //点击综合排序
        click_text_order = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击综合排序，待开发！", Toast.LENGTH_SHORT).show();
            }
        };

        //点击距离
        click_text_distance = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击距离，待开发！", Toast.LENGTH_SHORT).show();
            }
        };

        //点击销量
        click_text_sale = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击销量，待开发！", Toast.LENGTH_SHORT).show();
            }
        };

        //点击筛选
        click_text_choose = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击筛选，待开发！", Toast.LENGTH_SHORT).show();
            }
        };

        //点击广告
        click_img_ad= new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击广告，待开发！", Toast.LENGTH_SHORT).show();
            }
        };


    }


    /**
     * 函数：addListener()
     * 功能：为初始化的控件注册事件监听
     * 后置：onCreateView()
     */
    private void addListener(){                                                                     //addInitListener()
        home_img_font.setOnClickListener(click_img_font);
        home_img_message.setOnClickListener(click_img_message);
        home_view_find.setOnClickListener(click_view_find);
        home_view_00.setOnClickListener(click_view_00);
        home_view_01.setOnClickListener(click_view_01);
        home_view_02.setOnClickListener(click_view_02);
        home_view_03.setOnClickListener(click_view_03);
        home_view_04.setOnClickListener(click_view_04);
        home_view_10.setOnClickListener(click_view_10);
        home_view_11.setOnClickListener(click_view_11);
        home_view_12.setOnClickListener(click_view_12);
        home_view_13.setOnClickListener(click_view_13);
        home_view_14.setOnClickListener(click_view_14);
        home_view_location.setOnClickListener(click_view_location);
        home_view_20.setOnClickListener(click_view_20);
        home_view_21.setOnClickListener(click_view_21);
        home_view_22.setOnClickListener(click_view_22);
        home_view_23.setOnClickListener(click_view_23);
        home_img_ad.setOnClickListener(click_img_ad);
        home_img_order.setOnClickListener(click_img_order);
        home_img_choose.setOnClickListener(click_img_choose);
        home_text_order.setOnClickListener(click_text_order);
        home_text_distance.setOnClickListener(click_text_distance);
        home_text_sale.setOnClickListener(click_text_sale);
        home_text_choose.setOnClickListener(click_text_choose);
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
        String Id_user = new SharePreferencesManager(lordActivity).getUserId();
        return "5#" + "0#" + Id_user;
    }


    /**
     * 函数：paintImage()
     * 功能：绘制刷新快拍
     * 参数：@scream
     * 备注：信息流（昵称_头像编号_头像框编号_快拍编号）
     */
    private void paintImage(String screamImage){
        String[] images = screamImage.split("%");
        home_img_find.setTag(images[0]);
        new ImageServer().setBackground(home_img_find, context);
        OvalImageView[] imageViews = {home_img_20, home_img_21, home_img_22,
                home_img_23, home_img_24, home_img_25, home_img_26, home_img_27};
        int len = imageViews.length;
        for (int i=0; i<len; i++){
            if (imageViews[i] != null){
                imageViews[i].setTag(images[i+1]);
                imageViews[i].setRadius(8);
                new ImageServer().setBitmap(imageViews[i], context);
            } else {
                Log.d("调试广告流-损坏图片序号:",String.valueOf(i));
            }
        }
        home_img_ad.setTag(images[9]);
        new ImageServer().setBackground(home_img_ad, context);
    }

    /**
     * 函数：paintNew()
     * 功能：加载刷新内容
     */
    private void paintNew(String[] screams){
        String imageScream = screams[1];
        paintImage(imageScream);
        String resultScream = screams[2];
        new ResultServer(resultScream, getContext(), lordActivity, home_view_result, this).paintResult();
    }

}