package com.futrue.follower;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class ProcessManager {

    SharedPreferences message;

    /**
     * 函数：ProcessManager()
     * 功能：构造方法
     * 参数：@activity
     */
    public ProcessManager(Activity activity){
        message = activity.getSharedPreferences("message", Context.MODE_PRIVATE);
    }


    /**
     * 函数：checkIsFirst()
     * 功能：检查是否第一次加载
     */
    public boolean checkIsFirst(){
        return message.getBoolean("first",true);
    }


    /**
     * 函数：setNotFirst()
     * 功能：标记不是第一次加载
     * 参数：@activity
     */
    public void setNotFirst(Activity activity){
        SharedPreferences.Editor editor = message.edit();
        editor.putBoolean("first", false);
        editor.apply();
    }


    /**
     * 函数：threadStart()
     * 功能：激活线程
     * 参数：@thread
     */
    public void threadStart(java.lang.Thread thread){
        thread.start();
    }


    /**
     * 函数：threadInterrupt()
     * 功能：异常抛出线程
     * 参数：@thread
     */
    public void threadInterrupt(java.lang.Thread thread){
        thread.interrupt();
    }

}
