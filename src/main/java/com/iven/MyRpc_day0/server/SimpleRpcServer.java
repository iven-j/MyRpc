package com.iven.MyRpc_day0.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleRpcServer implements RpcServer{
    private ServiceProvider serviceProvide;
    @Override
    public void start(int port) {
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("启动");
            while(true){
                Socket socket = serverSocket.accept();
                new Thread(new WorkThread(socket,serviceProvide)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {

    }
}
