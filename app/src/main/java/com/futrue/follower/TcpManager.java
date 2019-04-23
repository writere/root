package com.futrue.follower;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class TcpManager {

    private String host;
    private String req;
    private int port;
    private boolean connTcp;

    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;

    private String resp;


    /**
     * 函数：TcpManager()
     * 功能：构造方法
     */
    public TcpManager(String req) {
        UrlManager urlManager = new UrlManager();
        this.host = urlManager.getHost();
        this.port = urlManager.getPort();
        this.req = req;
    }


    /**
     * 函数：getResponse()
     * 功能：获得返回的数据
     */
    public String getResponse() {
        conn();
        write(req);
        read();
        close();
        return resp;
    }


    /**
     * 函数：connTcp()
     * 功能：连接TCP协议
     */
    private void conn(){
        if (port != 0){
            try{
                socket = new Socket();
                SocketAddress endpoint = new InetSocketAddress(InetAddress.getByName(host), port);
                socket.setSoTimeout(3000);
                socket.connect(endpoint);
                if (socket.getSoTimeout() <= 4000){
                    connTcp = true;
                } else {
                    resp = "e";
                    connTcp = false;
                }
            } catch (IOException e){
                resp = "b";
                connTcp = false;
            }
        } else {
            resp = "a";
            connTcp = false;
        }
    }


    /**
     * 函数：write()
     * 功能：写request请求信息
     * 参数：@request
     */
    private void write(String req) {
        if (connTcp) {
            try {
                out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
                out.println(req);
                Log.d("发送信息:",req);
            } catch (IOException e) {
                resp = "c";
            }
        }
    }


    /**
     * 函数：readTcp()
     * 功能：读取返回的全部信息流
     */
    public String read() {
        if (connTcp) {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    builder.append(line);
                }
                resp = builder.toString();
            } catch (IOException e) {
                resp = "d";
            }
        }
        return resp;
    }


    /**
     * 函数：close()
     * 功能：关闭socket
     */
    private void close(){
        try {
            if (out != null){
                out.close();
                out = null;
            } else {
                resp = "b";
                Log.d("调试网络请求:","连接服务器失败！");
            }
            if (in != null){
                in.close();
                in = null;
            }
            if (socket != null){
                socket.close();
                socket = null;
            }
        } catch (IOException e) {
            Log.d("🐰：","TCP关闭异常");
        }
    }



    /**
     * 函数：wrapRequest()
     * 功能：封装请求信息
     * 返回：@content
     */
//    private String wrapRequest(String request){
//        return new Wine().zipString(request);
//    }


    /**
     * 函数：getInputStream()
     * 功能：获取返回输入数据流
     */
    public InputStream getInputStream(){
        conn();
        write(req);
        InputStream is = null;
        try {
            is = socket.getInputStream();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }


}