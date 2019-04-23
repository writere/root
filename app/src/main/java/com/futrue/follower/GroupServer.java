package com.futrue.follower;

import android.content.Context;
import android.os.Build;
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

import java.math.BigDecimal;


public class GroupServer {

    private String resultStream;
    private Context context;
    private LordActivity lordActivity;
    private ViewGroup viewGroup;
    private TextView textMoney;
    private TextView textPay;
    private ImageView imgChoose;
    private TextView textChoose;

//    ImageView  group_img_shopChoose, group_img_foodChoose, group_img_more;
//    OvalImageView group_img_shopIcon, group_img_foodIcon;
//    TextView group_text_shopName, group_text_status, group_text_foodName, group_text_foodScale,
//            group_text_foodPrice, group_text_send, group_text_amountFood;
//    LinearLayout group_view_foodScream, group_layout, food_layout;
//    RelativeLayout group_view_food, group_view_deleteFood, group_view_addFood;
     private LinearLayout  group_layout, food_layout;

    private View.OnClickListener click_img_shopChoose, click_img_foodChoose, click_img_shopIcon,
            click_text_shopName, click_view_food, click_view_addFood, click_view_deleteFood, click_img_more;


    public GroupServer(String resultStream, Context context, LordActivity lordActivity, ViewGroup viewGroup,
                       TextView textMoney, TextView textPay, ImageView imgChoose, TextView textChoose){
        this.resultStream = resultStream;
        this.context = context;
        this.lordActivity = lordActivity;
        this.viewGroup = viewGroup;
        this.textMoney = textMoney;
        this.textPay = textPay;
        this.imgChoose = imgChoose;
        this.textChoose = textChoose;
    }


    /**
     *  函数：initResultListener()
     *  功能：初始化快拍的监听事件
     */
    private void initListener(){
        //点击店铺全选的图标
        click_img_shopChoose = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeShop(v);
            }
        };

        //点击选择食物的图标
        click_img_foodChoose = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFood(v);
            }
        };

        //点击整体食物
        click_view_food = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(lordActivity, "点击食物整体", Toast.LENGTH_SHORT).show();
            }
        };


        //点击商店图标
        click_img_shopIcon = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(lordActivity, "进入店铺图标", Toast.LENGTH_SHORT).show();
            }
        };

        //点击商店名
        click_text_shopName = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(lordActivity, "进入店铺名称", Toast.LENGTH_SHORT).show();
            }
        };


        //点击添加数量
        click_view_addFood = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout view = (RelativeLayout) v.getParent();
                TextView textAmount = (TextView) view.getChildAt(1);
                textAmount.setText(String.valueOf(Integer.valueOf(
                        textAmount.getText().toString())+1));
                changeAmount(true, v);
                changePay();
            }
        };

        //点击减少数量
        click_view_deleteFood = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout view = (RelativeLayout) v.getParent();
                TextView textAmount = (TextView) view.getChildAt(1);
                int n = Integer.valueOf(textAmount.getText().toString())-1;
                if (n != 0){
                    textAmount.setText(String.valueOf(n));
                    changeAmount(false, v);
                } else {
                    Toast.makeText(lordActivity, "已经是最小了！", Toast.LENGTH_SHORT).show();
                }
                changePay();
            }
        };


        //点击更多优惠
        click_img_more = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(lordActivity, "点击更多优惠，待开发！", Toast.LENGTH_SHORT).show();
            }
        };


    }

    /**
     * 函数：paintPhoto()
     * 功能：刷新绘制图片流的文字信息，绑定编号
     * 参数：@scream
     */
    public void paintResult(){

        ImageView  group_img_shopChoose, group_img_foodChoose, group_img_more;
        OvalImageView group_img_shopIcon, group_img_foodIcon;
        TextView group_text_shopName, group_text_status, group_text_foodName, group_text_foodScale,
                group_text_foodPrice, group_text_send, group_text_amountFood;
        LinearLayout group_view_foodScream;
        RelativeLayout group_view_food, group_view_deleteFood, group_view_addFood;

        initListener();
        String[] results = resultStream.split("%");
        String[] shops;
        String[] foods;
        for (String result : results) {
            shops = result.split("_");
            View view = LayoutInflater.from(context).
                    inflate(R.layout.layout_group, group_layout, false);

            group_layout = view.findViewById(R.id.group_layout);
            group_img_shopChoose = view.findViewById(R.id.group_img_shopChoose);
            group_img_shopIcon = view.findViewById(R.id.group_img_shopIcon);
            group_text_shopName = view.findViewById(R.id.group_text_shopName);
            group_text_status = view.findViewById(R.id.group_text_status);
//            group_view_food = view.findViewById(R.id.group_view_food);
            group_view_foodScream = view.findViewById(R.id.group_view_foodScream);
            group_text_send = view.findViewById(R.id.group_text_send);
//            group_view_discount = view.findViewById(R.id.group_view_discount);
            group_img_more = view.findViewById(R.id.group_img_more);
            group_img_shopChoose.setTag("yin");

            group_view_foodScream.setTag(shops[0]);
            group_img_shopIcon.setTag(shops[1]);
            new ImageServer().setBitmap(group_img_shopIcon, context);
            group_text_shopName.setText(shops[2]);
            if (shops[3].equals("1")) {
                group_text_status.setText("营业");
                if (Build.VERSION.SDK_INT > 16) {
                    group_text_status.setBackground(lordActivity.getResources().getDrawable(R.drawable.shape_carter_work));
                }
            } else {
                group_text_status.setText("休息");
                if (Build.VERSION.SDK_INT > 16) {
                    group_text_status.setBackground(lordActivity.getResources().getDrawable(R.drawable.shape_carter_rest));
                }
            }
            if (!shops[4].equals("0")) {
                group_text_send.setText(String.format("配送费%s元  满%s元起送", shops[4], shops[5]));
            } else {
                group_text_send.setText(String.format("免配送费  满%s元起送", shops[5]));
            }
//            addScream(shops[6]+"#"+shops[7]+"#"+shops[8]);

            group_img_shopChoose.setOnClickListener(click_img_shopChoose);
            group_img_shopIcon.setOnClickListener(click_img_shopIcon);
            group_text_shopName.setOnClickListener(click_text_shopName);
            group_img_more.setOnClickListener(click_img_more);
//            group_view_food.setOnClickListener(click_view_food);

            foods = shops[9].split("&");
            for (String food : foods) {
                View viewFood = LayoutInflater.from(context).
                        inflate(R.layout.layout_food, food_layout, false);

                String items[] = food.split("/");
                food_layout = view.findViewById(R.id.layout_food);
                group_img_foodChoose = viewFood.findViewById(R.id.group_img_foodChoose);
                group_img_foodIcon = viewFood.findViewById(R.id.group_img_foodIcon);
                group_text_foodName = viewFood.findViewById(R.id.group_text_foodName);
                group_text_foodScale = viewFood.findViewById(R.id.group_text_foodScale);
                group_text_foodPrice = viewFood.findViewById(R.id.group_text_foodPrice);
                group_view_addFood = viewFood.findViewById(R.id.group_view_addFood);
                group_view_deleteFood = viewFood.findViewById(R.id.group_view_deleteFood);
                group_text_amountFood = viewFood.findViewById(R.id.group_text_amountFood);
                group_view_food = viewFood.findViewById(R.id.group_view_food);

                group_view_food.setTag(items[0]);
                group_img_foodIcon.setTag(items[1]);
                new ImageServer().setBitmap(group_img_foodIcon, context);
                group_text_foodName.setText(items[2]);
                group_text_foodPrice.setText(String.format("¥%s", items[3]));
                group_text_foodScale.setText(items[4]);
                group_text_amountFood.setText(items[5]);
                group_img_foodChoose.setTag("yin");

                group_view_food.setOnClickListener(click_view_food);
                group_view_addFood.setOnClickListener(click_view_addFood);
                group_view_deleteFood.setOnClickListener(click_view_deleteFood);
                group_img_foodChoose.setOnClickListener(click_img_foodChoose);

                group_view_foodScream.addView(viewFood, 0);
            }
            viewGroup.addView(view, 0);
        }
    }


    /**
     * 函数：changeFood()
     * 功能：选中食物
     */
    private void changeFood(View v){
        ImageView foodChoose = (ImageView) v;
        if (v.getTag().toString().equals("yin")){
            foodChoose.setImageDrawable(lordActivity.getResources().getDrawable(R.drawable.choose_yang));
            v.setTag("yang");
        } else {
            foodChoose.setImageDrawable(lordActivity.getResources().getDrawable(R.drawable.choose));
            v.setTag("yin");
        }
        RelativeLayout viewFood = (RelativeLayout) foodChoose.getParent();
        checkFoodChoose(viewFood);
        changePay();
    }


    /**
     * 函数：checkFoodChoose()
     * 功能：选择食物，判断是否有选满情况
     */
    private void checkFoodChoose(RelativeLayout viewFood){
        ImageView changeFood = (ImageView) viewFood.getChildAt(0);
        LinearLayout screamFood = (LinearLayout) viewFood.getParent().getParent();
        boolean allFoodChoose = true;
        LinearLayout food;
        ImageView foodChoose;
        for (int i=0; i<screamFood.getChildCount(); i++){
            food = (LinearLayout)screamFood.getChildAt(i);
            foodChoose = (ImageView)((RelativeLayout) food.getChildAt(0))
                    .getChildAt(0);
            if (foodChoose.getTag() != changeFood.getTag()){
                allFoodChoose = false;
                break;
            }
        }
        RelativeLayout viewGroup = (RelativeLayout)screamFood.getParent();
        LinearLayout viewShop = (LinearLayout) viewGroup.getChildAt(0);
        ImageView  shopChoose = (ImageView) viewShop.getChildAt(0);
        if (allFoodChoose){
            if (!(changeFood.getTag() == shopChoose.getTag())){
                if (changeFood.getTag().toString().equals("yin")){
                    shopChoose.setImageDrawable(lordActivity.getResources().getDrawable(R.drawable.choose));
                    shopChoose.setTag("yin");
                } else {
                    shopChoose.setImageDrawable(lordActivity.getResources().getDrawable(R.drawable.choose_yang));
                    shopChoose.setTag("yang");
                }
            }
        } else {
            shopChoose.setImageDrawable(lordActivity.getResources().getDrawable(R.drawable.choose));
            shopChoose.setTag("yin");
        }
        checkShopChoose(viewShop);
    }


    /**
     * 函数：changeShop()
     * 功能：选中店铺
     */
    private void changeShop(View v){
        ImageView shopChoose = (ImageView) v;
        if (shopChoose.getTag().toString().equals("yin")){
            shopChoose.setImageDrawable(lordActivity.getResources().getDrawable(R.drawable.choose_yang));
            shopChoose.setTag("yang");
        } else {
            shopChoose.setImageDrawable(lordActivity.getResources().getDrawable(R.drawable.choose));
            shopChoose.setTag("yin");
        }
        RelativeLayout viewCroup = (RelativeLayout) v.getParent().getParent();
        LinearLayout screamFood = (LinearLayout) viewCroup.getChildAt(2);
        for (int i=0; i<screamFood.getChildCount(); i++){
            RelativeLayout viewFood = (RelativeLayout)((LinearLayout)screamFood.getChildAt(i)).
                    getChildAt(0);
            ImageView foodChoose = (ImageView)viewFood.getChildAt(0);
            if (shopChoose.getTag().toString().equals("yang")){
                foodChoose.setImageDrawable(lordActivity.getResources().getDrawable(R.drawable.choose_yang));
                foodChoose.setTag("yang");
            } else {
                foodChoose.setImageDrawable(lordActivity.getResources().getDrawable(R.drawable.choose));
                foodChoose.setTag("yin");
            }
        }
        LinearLayout viewShop = (LinearLayout) shopChoose.getParent();
        checkShopChoose(viewShop);
        changePay();
    }


    /**
     * 函数：checkChooseAllFood()
     * 功能：是否商店已全选
     */
    private void checkShopChoose(LinearLayout viewShop){
        ImageView shopChoose = (ImageView) viewShop.getChildAt(0);
        LinearLayout screamCarter = (LinearLayout) viewShop.getParent().getParent().getParent();
        boolean allShopChoose = true;
        ImageView choose;
        LinearLayout shop;
        RelativeLayout group;
        LinearLayout scream;
        for (int i=0; i<screamCarter.getChildCount(); i++){
            scream = (LinearLayout) screamCarter.getChildAt(i);
            group = (RelativeLayout) scream.getChildAt(0);
            shop = (LinearLayout) group.getChildAt(0);
            choose = (ImageView) shop.getChildAt(0);
            if (choose.getTag() != shopChoose.getTag()){
                allShopChoose = false;
                break;
            }
        }
        if (allShopChoose){
            if (!(imgChoose.getTag() == shopChoose.getTag())){
                if (shopChoose.getTag().toString().equals("yin")){
                    setAllChoose("yin");
                } else {
                    setAllChoose("yang");
                }
            }
        } else {
            setAllChoose("yin");
        }
    }



    /**
     * 函数: setAllChoose()
     * 功能: 切换全选框
     */
    private void setAllChoose(String change){
        if (change.equals("yin")) {
            imgChoose.setImageDrawable(lordActivity.getResources().getDrawable(R.drawable.choose));
            imgChoose.setTag("yin");
            textChoose.setText("全选");
        } else {
            imgChoose.setImageDrawable(lordActivity.getResources().getDrawable(R.drawable.choose_yang));
            imgChoose.setTag("yang");
            textChoose.setText("全不选");
        }
    }




    /**
     * 函数：changeAll()
     * 功能：实现全选功能
     */
    public void changeAll(){
        if (imgChoose.getTag().toString().equals("yin")){
            imgChoose.setImageDrawable(lordActivity.getResources().getDrawable(R.drawable.choose_yang));
            imgChoose.setTag("yang");
            textChoose.setText("全不选");
        } else {
            imgChoose.setImageDrawable(lordActivity.getResources().getDrawable(R.drawable.choose));
            imgChoose.setTag("yin");
            textChoose.setText("全选");
        }
        if (imgChoose.getTag().toString().equals("yang")){
            float price;
            int amount;
            int count = 0;
            BigDecimal money = new BigDecimal(0);
            BigDecimal bd1, bd2;
            RelativeLayout screamGroup, viewAmount;
            ImageView shopChoose, foodChoose ;
            LinearLayout screamFood;
            TextView textPrice, textAmount;
            for (int i=0; i<viewGroup.getChildCount(); i++){
                screamGroup = (RelativeLayout)((LinearLayout) viewGroup.getChildAt(i))
                        .getChildAt(0);
                shopChoose = (ImageView)((LinearLayout)screamGroup.getChildAt(0))
                        .getChildAt(0);
                shopChoose.setImageDrawable(lordActivity.getResources().getDrawable(R.drawable.choose_yang));
                shopChoose.setTag("yang");
                screamFood = (LinearLayout)screamGroup.getChildAt(2);
                for (int j=0; j<screamFood.getChildCount(); j++){
                    RelativeLayout viewFood = (RelativeLayout)((LinearLayout)screamFood.getChildAt(j)).
                            getChildAt(0);
                    foodChoose = (ImageView)viewFood.getChildAt(0);
                    textPrice = (TextView)viewFood.getChildAt(4);
                    viewAmount = (RelativeLayout) viewFood.getChildAt(5);
                    textAmount = (TextView) viewAmount.getChildAt(1);
                    amount = Integer.valueOf(textAmount.getText().toString());
                    price = Float.valueOf(textPrice.getText().toString().substring(1));
                    bd1 = new BigDecimal(amount);
                    bd2 = new BigDecimal(price);
                    money = money.add(bd1.multiply(bd2));
                    count = count + amount;
                    foodChoose.setImageDrawable(lordActivity.getResources().getDrawable(R.drawable.choose_yang));
                    foodChoose.setTag("yang");
                }
            }
            setPayText(count);
            BigDecimal total = money.setScale(2,4);
            textMoney.setText(String.format("¥%s", total.toString()));
        } else {
            for (int i=0; i<viewGroup.getChildCount(); i++){
                RelativeLayout screamGroup = (RelativeLayout)((LinearLayout) viewGroup.getChildAt(i))
                        .getChildAt(0);
                ImageView shopChoose = (ImageView)((LinearLayout)screamGroup.getChildAt(0))
                        .getChildAt(0);
                shopChoose.setImageDrawable(lordActivity.getResources().getDrawable(R.drawable.choose));
                shopChoose.setTag("yin");
                LinearLayout screamFood = (LinearLayout)screamGroup.getChildAt(2);
                for (int j=0; j<screamFood.getChildCount(); j++){
                    RelativeLayout viewFood = (RelativeLayout)((LinearLayout)screamFood.getChildAt(j)).
                            getChildAt(0);
                    ImageView foodChoose = (ImageView)viewFood.getChildAt(0);
                    foodChoose.setImageDrawable(lordActivity.getResources().getDrawable(R.drawable.choose));
                    foodChoose.setTag("yin");
                }
            }
            setPayText(0);
            textMoney.setText(String.format("¥%s", String.valueOf(0)));
//            textMoney.setText("¥"+String.valueOf(0));
        }
    }



    /**
     * 函数：changeAmount()
     * 功能：改变商品数量
     */
    private void changeAmount(boolean isAdd, View v){
        changePay();
        String Id_user = new SharePreferencesManager(lordActivity).getUserId();
        RelativeLayout viewFood = (RelativeLayout) v.getParent().getParent();
        String Id_food = viewFood.getTag().toString();
        String head;
        if (isAdd){
            head = "7#4#1_1_";
        } else {
            head = "7#4#0_1_";
        }
        final String request = head + Id_user + "_" + Id_food;
        if (new SystemServer().isNetworkConnected(context)){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    TcpManager tcp = new TcpManager(request);
                    tcp.getResponse();
                }
            }).start();
        } else {
            Toast.makeText(lordActivity, "网络未连接", Toast.LENGTH_SHORT).show();
        }
    }



    private void changePay(){
        float price;
        int amount;
        int count=0;
        BigDecimal money = new BigDecimal(0);
        BigDecimal bd1,bd2, bd3;
        LinearLayout screamFood;
        RelativeLayout viewFood, viewAmount, screamGroup;
        ImageView foodChoose;
        TextView textPrice, textAmount;
        for (int i=0; i<viewGroup.getChildCount(); i++){
            screamGroup = (RelativeLayout)((LinearLayout) viewGroup.getChildAt(i))
                    .getChildAt(0);
            screamFood = (LinearLayout)screamGroup.getChildAt(2);
            for (int j=0; j<screamFood.getChildCount(); j++){
                viewFood = (RelativeLayout)((LinearLayout)screamFood.getChildAt(j)).
                        getChildAt(0);
                foodChoose = (ImageView)viewFood.getChildAt(0);
                if (foodChoose.getTag().toString().equals("yang")){
                    textPrice = (TextView)viewFood.getChildAt(4);
                    viewAmount = (RelativeLayout) viewFood.getChildAt(5);
                    textAmount = (TextView) viewAmount.getChildAt(1);
                    amount = Integer.valueOf(textAmount.getText().toString());
                    price = Float.valueOf(textPrice.getText().toString().substring(1));
                    bd1 = new BigDecimal(amount);
                    bd2 = new BigDecimal(price);
                    bd3 = bd1.multiply(bd2);
                    money = money.add(bd3);
                    count = count + amount;
                }
            }
        }
        setPayText(count);
        BigDecimal total = money.setScale(2, BigDecimal.ROUND_HALF_UP);
        textMoney.setText(String.format("¥%s", total.toString()));
    }


    /**
     * 函数：setPayText()
     * 功能：设置结算区域的文字
     */
    private void setPayText(int count){
        RelativeLayout layoutCarter = (RelativeLayout) textPay.getParent().getParent().getParent();
        RelativeLayout viewTitle =  (RelativeLayout) layoutCarter.getChildAt(0);
        TextView textManager = (TextView) viewTitle.getChildAt(1);
        if (textManager.getTag().toString().equals("yin")){
            String pay = "结算("+String.valueOf(count)+")";
            textPay.setText(pay);
        } else {
            String pay = "清除("+String.valueOf(count)+")";
//            textPay.setText("清除("+String.valueOf(count)+")");
            textPay.setText(pay);
        }
    }


}
