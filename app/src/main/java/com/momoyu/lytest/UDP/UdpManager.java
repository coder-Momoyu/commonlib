package com.momoyu.lytest.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by ${momoyu} on 2018/10/25.
 */

public class UdpManager {

    private static UdpManager mUdpManager = null;
    private final String str = "hello world!";

    public static UdpManager getInstance() {
        if (mUdpManager == null) {
            synchronized (UdpManager.class) {
                if (mUdpManager == null) {
                    mUdpManager = new UdpManager();
                }
            }
        }
        return mUdpManager;
    }

    private UdpManager(){

    }

    public void startSocket(String content) {
        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            byte[] bytes = content.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(bytes,bytes.length, InetAddress.getByName("192.168.0.96"),65301);
            datagramSocket.send(datagramPacket);
            datagramSocket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
