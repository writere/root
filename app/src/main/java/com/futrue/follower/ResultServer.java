package com.futrue.follower;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.futrue.LordActivity;
import com.futrue.R;
import com.futrue.following.OvalImageView;

public class ResultServer {

    private String resultStream;
    private Context context;
    private LordActivity lordActivity;
    private ViewGroup viewGroup;
    private Handler handler;
    private View clickView;
    private Fragment fragment;

    ImageView result_img_add, result_img_more;
    TextView result_text_name, result_text_distance, result_text_time, result_text_score,
            result_text_sale, result_text_shop, result_text_send, result_text_count, result_text_price;
    RelativeLayout result_layout, result_view_body;
    LinearLayout result_view_discount;
    OvalImageView result_img_food, result_img_icon;

    View.OnClickListener click_img_add, click_view_body, click_img_more, click_text_add;

    @SuppressLint("HandlerLeak")
    public ResultServer(String resultStream, final Context context, LordActivity lordActivity, ViewGroup viewGroup, Fragment fragment){
        this.resultStream = resultStream;
        this.context = context;
        this.lordActivity = lordActivity;
        this.viewGroup = viewGroup;
        this.fragment = fragment;

        handler = new android.os.Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch(msg.what){
                    case 0:
                        String[] resp = msg.obj.toString().split("#");
                        String add = resp[1];
                        if (add.equals("0")){
                            clickView.setTag("0");
                            Toast.makeText(context, "购物车添加成功!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "购物车添加失败，请重试!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 1:
                        String[] resp2 = msg.obj.toString().split("#");
                        String delete = resp2[1];
                        if (delete.equals("0")){
                            clickView.setTag("1");
                            Toast.makeText(context, "取消成功!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "购物车取消失败，请重试!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                handler.removeMessages(msg.what);
            }
        };
    }


    /**
     *  函数：initResultListener()
     *  功能：初始化快拍的监听事件
     */
    private void initListener(){
        //点击购物车
        click_img_add = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickView = v;
                ImageView addView = (ImageView) clickView;
                if (v.getTag().toString().equals("1")){
                    addView.setImageDrawable(lordActivity.getResources().getDrawable(R.drawable.carter_yang));
                    addCarter(v);
                } else {
                    addView.setImageDrawable(lordActivity.getResources().getDrawable(R.drawable.carter_ying));
                    deleteCarter(v);
                }
            }
        };

        //点击时间, 添加购物车
        click_text_add = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout viewFood = (RelativeLayout) v.getParent();
                clickView = viewFood.getChildAt(5);
                ImageView addView = (ImageView) clickView;
                if (clickView.getTag().toString().equals("1")){
                    addView.setImageDrawable(lordActivity.getResources().getDrawable(R.drawable.carter_yang));
                    addCarter(clickView);
                } else {
                    addView.setImageDrawable(lordActivity.getResources().getDrawable(R.drawable.carter_ying));
                    deleteCarter(clickView);
                }
            }
        };

        //点击食物
        click_view_body = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(lordActivity, "进入店铺", Toast.LENGTH_SHORT).show();
            }
        };

        //点击更多优惠
        click_img_more = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(lordActivity, "点击查看更多优惠, 待开发！", Toast.LENGTH_SHORT).show();
            }
        };

    }


    /**
     * 函数：paintPhoto()
     * 功能：刷新绘制图片流的文字信息，绑定编号
     * 参数：@scream
     */
    public void paintResult(){
        initListener();
        String[] results = resultStream.split("%");
        String[] one;
        for (String result : results) {
            one = result.split("_");
            View view = LayoutInflater.from(context).
                    inflate(R.layout.layout_result, result_layout, false);


            result_img_food = view.findViewById(R.id.result_img_food);
            result_img_add = view.findViewById(R.id.result_img_add);
            result_img_icon = view.findViewById(R.id.result_img_icon);
            result_img_more = view.findViewById(R.id.result_img_more);
            result_text_name = view.findViewById(R.id.result_text_name);
            result_text_distance = view.findViewById(R.id.result_text_distance);
            result_text_time = view.findViewById(R.id.result_text_time);
            result_text_score = view.findViewById(R.id.result_text_score);
            result_text_sale = view.findViewById(R.id.result_text_sale);
            result_text_shop = view.findViewById(R.id.result_text_shop);
            result_text_send = view.findViewById(R.id.result_text_send);
            result_view_discount = view.findViewById(R.id.result_view_discount);
            result_text_count = view.findViewById(R.id.result_text_count);
            result_text_price = view.findViewById(R.id.result_text_price);
            result_view_body = view.findViewById(R.id.result_view_body);

            result_img_food.setTag(one[0]);
            result_img_icon.setTag(one[1]);

            new ImageServer().setBitmap(result_img_food, context);
            new ImageServer().setBitmap(result_img_icon, context);

            result_text_name.setText(one[2]);
            result_text_distance.setText(one[3]+"公里");
            result_text_time.setText(one[4]+"分钟");
            result_text_score.setText(one[5]);
            result_text_sale.setText("销量 "+one[6]);
            result_text_shop.setText(one[7]);
            result_text_send.setText(one[8]);
            result_text_count.setText(one[9]);                                                      //2019/4/16 优惠信息显示需要改进
            result_text_price.setText("¥"+one[10]);
            result_view_body.setTag(one[11]);
            result_img_add.setTag(one[12]);
            if (one[12].equals("0")){
                result_img_add.setImageDrawable(lordActivity.getResources().getDrawable(R.drawable.carter_yang));
            }

            result_img_add.setOnClickListener(click_img_add);
            result_text_time.setOnClickListener(click_text_add);
            result_text_distance.setOnClickListener(click_text_add);
            result_view_body.setOnClickListener(click_view_body);
            result_img_more.setOnClickListener(click_img_more);

            viewGroup.addView(view,0);
        }
    }


    /**
     * 函数：addCarter()
     * 功能：添加商品到购物车
     */
    private void addCarter(View v){
        RelativeLayout viewFood = (RelativeLayout) v.getParent();
        final String Id_food = viewFood.getTag().toString();
        if (new SystemServer().isNetworkConnected(context)){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    TcpManager tcp = new TcpManager(newAddRequest(Id_food));
                    Message msg = new Message();
                    msg.obj = tcp.getResponse();
                    msg.what = 0;
                    handler.sendMessage(msg);
                }
            }).start();
        } else {
            Toast.makeText(lordActivity, "添加商品失败，网络未连接", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 函数: newAddRequest()
     * 功能：封装添加购物车的请求
     */
    private String newAddRequest(String id){
        String Id_user = new SharePreferencesManager(lordActivity).getUserId();
        String Id_food = id;
        return "7#1#" +  Id_user + "_" + Id_food + "_1_中份";
    }


    /**
     * 函数：deleteCarter()
     * 功能：删除购物车中的商品
     */
    private void deleteCarter(View v){
        RelativeLayout viewFood = (RelativeLayout) v.getParent();
        final String Id_food = viewFood.getTag().toString();
        if (new SystemServer().isNetworkConnected(context)){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    TcpManager tcp = new TcpManager(newDeleteRequest(Id_food));
                    Message msg = new Message();
                    msg.obj = tcp.getResponse();
                    msg.what = 1;
                    handler.sendMessage(msg);
                }
            }).start();
        } else {
            Toast.makeText(lordActivity, "删除商品失败，网络未连接", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 函数: newDeleteRequest()
     * 功能：封装添加购物车的请求
     */
    private String newDeleteRequest(String id){
        String Id_user = new SharePreferencesManager(lordActivity).getUserId();
        String Id_food = id;
        return "7#2#" +  Id_user + "_" + Id_food;
    }


}
