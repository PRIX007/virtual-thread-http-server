package org.priyanshu.httpserver;

import org.priyanshu.httpserver.config.Configuration;
import org.priyanshu.httpserver.config.ConfigurationManager;
import org.priyanshu.httpserver.core.HttpServerListener;

import java.io.IOException;

public class HttpServer {
    public static void main(String[] args)  {
        System.out.println("Hello, World!");

        ConfigurationManager.getInstance().loadConfiguration("src/main/resources/http.json");
        Configuration configuration = ConfigurationManager.getConfiguration();

        System.out.println(configuration.getPort());
        System.out.println(configuration.getWebroot());
        HttpServerListener httpServerListener = null;
        try {
            httpServerListener = new HttpServerListener(configuration.getPort());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        httpServerListener.listen();
//        try {
//            ServerSocket serverSocket=new ServerSocket(configuration.getPort());
//            while(serverSocket.isBound() && !serverSocket.isClosed()) {
//                Socket socket = serverSocket.accept();
//                InputStream inputStream = socket.getInputStream();
//                OutputStream outputStream = socket.getOutputStream();
//                outputStream.write("HTTP/1.1 200 OK\r\n".getBytes());
//                outputStream.write("\r\n".getBytes());
//                outputStream.write("<html><body><h1>Static Content Bablu Page</h1></body></html>".getBytes());
//                outputStream.write("\r\n".getBytes());
//                outputStream.write("\r\n".getBytes());
//                outputStream.flush();
//
//                inputStream.close();
//                outputStream.close();
//                socket.close();
////                Thread.sleep(1000);
//            }
////            serverSocket.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }
}