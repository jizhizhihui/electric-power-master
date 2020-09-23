package com.electricPower;

import com.electricPower.core.socket.client.SocketClient;
import org.junit.Test;

import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SockedConcurrentTest extends Thread {


    @Test
    @Override
    public void run(){
        for (int i = 0; i < 1; i++) {
            try {
                SocketClient socketClient = new SocketClient(InetAddress.getByName("127.0.0.1"),3000);
                socketClient.println("43 0B 02 11 11 11 11 11 11 B6 16");
                socketClient.getSocket().setKeepAlive(true);
            } catch (UnknownHostException | SocketException e) {
                e.printStackTrace();
            }
        }
    }

}
