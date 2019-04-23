package com.futrue.follower;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferencesManager {


    private Activity activity;

    /**
     * 函数: SharePreferencesManager()
     * 功能: 构造方法
     * 参数: @activity
     */
    public SharePreferencesManager(Activity activity){
        this.activity = activity;
    }

    /**
     * 函数: getSingleHistory()
     * 功能: 获取待查询信息
     */
    public String getSingleHistory(){
        SharedPreferences history = activity.getSharedPreferences("history", Context.MODE_PRIVATE);
        return  history.getString("0",null);
    }

    /**
     * 函数: addSingleHistory()
     * 功能: 获取待查询信息
     */
    public void addSingleHistory(String find){
        SharedPreferences history = activity.getSharedPreferences("history", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = history.edit();
        editor.putString("0", find);
        editor.apply();
    }

    /**
     * 函数: getUserId()
     * 功能: 获取待查询用户账号
     */
    public String getUserId(){
        return "1";
    }


}

