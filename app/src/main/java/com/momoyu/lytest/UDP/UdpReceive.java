package com.momoyu.lytest.UDP;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by ${momoyu} on 2018/10/25.
 */

public class UdpReceive {
    private final static String TAG = "UdpReceive";
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    public static void receive() {
        try {
            //1.创建数据报套接字
            DatagramSocket socket = new DatagramSocket(65301);
            //2.创建一个数据报包
            byte[] content = new byte[1024];

            while (true) {
                DatagramPacket datagramPacket = new DatagramPacket(content,content.length);
                //3.调用receive方法接收数据包
                socket.receive(datagramPacket);
                //4.从数据报包中获取数据
                byte[] data=  datagramPacket.getData();//获取数据报包中的数据
                int length = datagramPacket.getLength();//
                InetAddress ip = datagramPacket.getAddress();
                int port = datagramPacket.getPort();
                String message = new String(data, 0, length);
                if(message.equals("bye")){
                    break;
                }
                Log.d(TAG,"内容:"+message);
                Log.d(TAG,"数据长度:"+length);
                Log.d(TAG,"发送方的IP地址:"+ip);
                Log.d(TAG,"发送方的端口号:"+port);
            }
            //5.释放资源
            //socket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
