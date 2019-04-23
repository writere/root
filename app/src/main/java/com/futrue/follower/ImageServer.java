package com.futrue.follower;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ImageView;

import com.futrue.LordActivity;
import com.futrue.following.OvalImageView;


public class ImageServer {

     private Bitmap bitmap;
     private android.os.Handler handler;
     private ImageView imageView;
     private OvalImageView ovalImageView;


    @SuppressLint("HandlerLeak")
    public ImageServer() {
        bitmap = null;
        handler = new android.os.Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch(msg.what){
                    case 0:
                        if (imageView != null && bitmap != null){
                            imageView.setImageBitmap(bitmap);
                        }
                        bitmap = null;
                        imageView = null;
                        break;
                    case 1:
                        if (imageView != null && bitmap != null){
                            Drawable bitmapDrawable = new BitmapDrawable(bitmap);
                            if (Build.VERSION.SDK_INT > 16){
                                imageView.setBackground(bitmapDrawable);
                            }
                        }
                        bitmap = null;
                        imageView = null;
                        break;
                    case 2:
                        if (ovalImageView != null && bitmap != null){
                            ovalImageView.setBitmap(bitmap);
                        }
                        bitmap = null;
                        ovalImageView = null;
                        break;
                }
                handler.removeMessages(msg.what);
            }
        };
    }



    /**
     * 函数：setBitmap()
     * 功能：为ImageView动态加载网络图片
     */
    public void setBitmap(ImageView imageView, Context context){
        this.imageView = imageView;
        final String request = "2#food#" + imageView.getTag();
        if (new SystemServer().isNetworkConnected(context)){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    TcpManager tcp = new TcpManager(request);
                    byte[] b = stringToByte(tcp.getResponse());
                    if (b != null){
                        bitmap = BitmapFactory.decodeByteArray(b,0, b.length);
                        handler.sendEmptyMessage(0);
                    }
                }
            }).start();
        }
    }


    /**
     * 函数：setBitmap()
     * 功能：为OvalImageView动态加载网络图片
     */
    public void setBitmap(final OvalImageView ovalImageView, Context context){
        this.ovalImageView = ovalImageView;
        final String request = "2#food#" + ovalImageView.getTag();
        if (new SystemServer().isNetworkConnected(context)){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    TcpManager tcp = new TcpManager(request);
                    byte[] b = stringToByte(tcp.getResponse());
                    if (b != null){
                        bitmap = BitmapFactory.decodeByteArray(b,0, b.length);
                        handler.sendEmptyMessage(2);
                    }
                }
            }).start();
        }
    }


    /**
     * 函数：setBackground()
     * 功能：为ImageView动态加载网络背景
     */
    public void setBackground(ImageView imageView, Context context){
        this.imageView = imageView;
        final String request = "2#food#" + imageView.getTag();
        if (new SystemServer().isNetworkConnected(context)){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    TcpManager tcp = new TcpManager(request);
                    byte[] b = stringToByte(tcp.getResponse());
                    if (b != null){
                        bitmap = BitmapFactory.decodeByteArray(b,0, b.length);
                        handler.sendEmptyMessage(1);
                    }
                }
            }).start();
        }
    }


    /**
     * 函数：stringToByte()
     * 功能：字符串转二进制
     * 参数：@string
     */
    private static byte[] stringToByte(String string) {
        if (string == null)
            return null;
        string = string.trim();
        int len = string.length();
        if (len == 0 || len % 2 == 1)
            return null;
        byte[] b = new byte[len / 2];
        try {
            for (int i = 0; i < string.length(); i += 2) {
                b[i / 2] = (byte) Integer.decode("0X" + string.substring(i, i + 2)).intValue();
            }
            return b;
        } catch (Exception e) {
            return null;
        }
    }

}
