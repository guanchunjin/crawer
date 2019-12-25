package com.chunjin.tomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;



//Serverlet 容器
public class CJTomcat
{




    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(8080);

        while (true)
        {
            //等待访问
            Socket socket1 = server.accept();
            //要访问的资源是什么
            InputStream in = socket1.getInputStream();

            //浏览器需要访问资源通过

            OutputStream out = socket1.getOutputStream();

        }





    }
}
