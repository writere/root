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

import com.futrue.follower.GroupServer;
import com.futrue.follower.SystemServer;
import com.futrue.follower.SharePreferencesManager;
import com.futrue.follower.TcpManager;

import java.math.BigDecimal;

public class CarterFragment extends Fragment {

    LordActivity lordActivity;
    Context context;
    MainFragment mainFragment;
    Handler handler;
    GroupServer groupServer;

    View view;
    TextView carter_text_manager, carter_text_money, carter_text_all, carter_text_pay;
    LinearLayout carter_view_scream;
    ImageView carter_img_all;
    RelativeLayout carter_view_empty;


    View.OnClickListener click_text_manager, click_text_all, click_text_pay;




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
    private void initView(LayoutInflater inflater,ViewGroup container){                             //initView()
        view = inflater.inflate(R.layout.fragment_carter, container, false);
        carter_img_all = view.findViewById(R.id.carter_img_all);
        carter_text_manager = view.findViewById(R.id.carter_text_manager);
        carter_text_all = view.findViewById(R.id.carter_text_all);
        carter_text_money = view.findViewById(R.id.carter_text_money);
        carter_text_pay = view.findViewById(R.id.carter_text_pay);
        carter_view_scream = view.findViewById(R.id.carter_view_scream);
        carter_view_empty = view.findViewById(R.id.carter_view_empty);
        carter_img_all.setTag("yin");
        carter_text_manager.setTag("yin");
        carter_text_money.setTag(new BigDecimal(0));
    }


    /**
     * 函数：initListener()
     * 功能：初始化监听事件
     * 后置：onCreate()
     */
    private void initListener(){                                                                    //initListener()

        //点击管理
        click_text_manager = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击管理, 待开发", Toast.LENGTH_SHORT).show();
                if (v.getTag().toString().equals("yin")){
                    carter_text_money.setVisibility(View.GONE);
                    LinearLayout viewPay = (LinearLayout) carter_text_money.getParent();
                    TextView textCount = (TextView) viewPay.getChildAt(0);
                    textCount.setVisibility(View.GONE);
                    String temp = carter_text_pay.getText().toString();
                    String count = temp.substring(temp.indexOf("(")+1, temp.indexOf(")"));
                    carter_text_pay.setText("清除("+count+")");
                    carter_text_manager.setText("取消管理");
                    v.setTag("yang");
                } else {
                    carter_text_money.setVisibility(View.VISIBLE);
                    LinearLayout viewPay = (LinearLayout) carter_text_money.getParent();
                    TextView textCount = (TextView) viewPay.getChildAt(0);
                    textCount.setVisibility(View.VISIBLE);
                    String temp = carter_text_pay.getText().toString();
                    String count = temp.substring(temp.indexOf("(")+1, temp.indexOf(")"));
                    carter_text_pay.setText("结算("+count+")");
                    carter_text_manager.setText("管理");
                    v.setTag("yin");
                }
            }
        };



        //点击支付
        click_text_pay = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (carter_text_manager.getTag().toString().equals("yin")){
                    Toast.makeText(getContext(),"成功支付了"+carter_text_money.getText().
                            toString().substring(1)+"元！", Toast.LENGTH_SHORT).show();
                } else {
                    deleteChoose();
                }
            }
        };


    }


    /**
     * 函数：addListener()
     * 功能：为初始化的控件注册事件监听
     * 后置：onCreateView()
     */
    private void addListener(){                                                                     //addInitListener()
        carter_text_manager.setOnClickListener(click_text_manager);
        carter_text_pay.setOnClickListener(click_text_pay);

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
                Log.d("接收信息：", msg.obj.toString());
                switch(msg.what){
                    case 0:
                        String[] resp = msg.obj.toString().split("#");
                        new SystemServer().showNetworkException(lordActivity, resp[0]);
                        if (resp[0].equals("0")){
                            paintNew(resp);
                        }
                        break;
                    case 1:
                        String[] resp1 = msg.obj.toString().split("#");
                        new SystemServer().showNetworkException(lordActivity, resp1[0]);
                        if (resp1[0].equals("0")){
                            if (resp1[1].equals("0")){
                                Toast.makeText(lordActivity, "删除成功！", Toast.LENGTH_SHORT).show();
                                carter_view_scream.removeAllViews();
                                refresh();
                                carter_img_all.setImageDrawable(getResources().getDrawable(R.drawable.choose));
                                carter_text_pay.setText("清除(0)");
                            }
                        }
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
    private void refresh(){
        if (new SystemServer().isNetworkConnected(context)){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    TcpManager tcp = new TcpManager(newCarterRequest());
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
     */
    private String newCarterRequest(){
        String Id_user = new SharePreferencesManager(lordActivity).getUserId();
        return "7#0#"+ Id_user;
    }


    /**
     * 函数：paintNew()
     * 功能：加载刷新内容
     */
    private void paintNew(String[] screams){
        if (screams.length>1){
            carter_view_empty.setVisibility(View.GONE);
            String carterScream = screams[1];
            groupServer = new GroupServer(carterScream, getContext(), lordActivity, carter_view_scream,
                    carter_text_money, carter_text_pay, carter_img_all, carter_text_all);
            groupServer.paintResult();
            setChooseAll();
        } else {
            carter_view_empty.setVisibility(View.VISIBLE);
        }
    }


    /**
     * 函数：setChooseAll()
     * 功能：设置全选功能
     */
    private void setChooseAll(){
        click_text_all = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupServer.changeAll();
            }
        };
        carter_text_all.setOnClickListener(click_text_all);
        carter_img_all.setOnClickListener(click_text_all);
    }


    /**
     * 函数：deleteChoose()
     * 功能：清除选择的商品
     */
    private void deleteChoose(){
        if (new SystemServer().isNetworkConnected(context)){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    TcpManager tcp = new TcpManager(getDeleteChooseRequest());
                    Message msg = new Message();
                    msg.obj = tcp.getResponse();
                    msg.what = 1;
                    handler.sendMessage(msg);
                }
            }).start();
        } else {
            Toast.makeText(lordActivity, "网络未连接", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 函数：getDeleteChooseRequest()
     * 功能：获取批量删除的网络请求
     */
    private String getDeleteChooseRequest(){
        String deleteChoose = "";
        for (int i=0; i<carter_view_scream.getChildCount(); i++){
            LinearLayout layoutGroup = (LinearLayout) carter_view_scream.getChildAt(i);
            RelativeLayout viewGroup = (RelativeLayout) layoutGroup.getChildAt(0);
            LinearLayout screamFood = (LinearLayout) viewGroup.getChildAt(2);
            for (int j=0; j<screamFood.getChildCount(); j++){
                LinearLayout layoutFood = (LinearLayout) screamFood.getChildAt(j);
                RelativeLayout viewFood = (RelativeLayout) layoutFood.getChildAt(0);
                ImageView foodChoose = (ImageView) viewFood.getChildAt(0);
                if (foodChoose.getTag().toString().equals("yang")){
                    deleteChoose += viewFood.getTag() + "_";
                }
            }
        }
        String Id_user = new SharePreferencesManager(lordActivity).getUserId();
        return "7#3#"+Id_user+"#"+deleteChoose;
    }

}
