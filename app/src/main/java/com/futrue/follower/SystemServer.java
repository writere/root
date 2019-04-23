package com.futrue.follower;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class SystemServer {

    /**
     * 函数：isNetworkConnected()
     * 功能：判断是否有网络连接
     * 参数：@context
     */
    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }


    /**
     * 函数：showNetworkException()
     * 功能：显示网络错误
     */
    public void showNetworkException(Activity activity, String type){
        switch (type) {
            case "0":
                Toast.makeText(activity, "刷新成功", Toast.LENGTH_SHORT).show();
                break;
            case "1":
                Toast.makeText(activity, "没有刷新内容", Toast.LENGTH_SHORT).show();
                break;
            case "2":
                Toast.makeText(activity, "系统异常", Toast.LENGTH_SHORT).show();
                break;
            case "a":
                Toast.makeText(activity, "获取服务器地址失败", Toast.LENGTH_SHORT).show();
                break;
            case "b":
                Toast.makeText(activity, "连接服务器失败", Toast.LENGTH_SHORT).show();
                break;
            case "c":
                Toast.makeText(activity, "发送请求失败", Toast.LENGTH_SHORT).show();
                break;
            case "d":
                Toast.makeText(activity, "接收返回信息失败", Toast.LENGTH_SHORT).show();
                break;
            case "e":
                Toast.makeText(activity, "系统繁忙", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(activity, "未知错误", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 函数：showToast()
     * 功能：显示Toast
     */
    public void showToast(Activity activity, String content){
        Toast.makeText(activity, content, Toast.LENGTH_SHORT).show();
    }


}

