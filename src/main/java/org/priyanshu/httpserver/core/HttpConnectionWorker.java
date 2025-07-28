package org.priyanshu.httpserver.core;

import org.priyanshu.httpserver.parser.HttpRequest;
import org.priyanshu.httpserver.parser.HttpRequestParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorker implements Runnable {
    private final Socket socket;

    HttpConnectionWorker(Socket socket){
        this.socket=socket;
    }


    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            HttpRequest request = HttpRequestParser.parse(inputStream);
            System.out.println("Parsed Request: " + request);

            outputStream.write("HTTP/1.1 200 OK\r\n".getBytes());
            outputStream.write("Content-Type: text/html\r\n".getBytes());
            outputStream.write("\r\n".getBytes());
            outputStream.write("<html><body><h1>Static Content from Bablu Page</h1></body></html>".getBytes());
            outputStream.flush();

        } catch (IOException e) {
            System.err.println("Connection error: " + e.getMessage());
        } finally {
            try {
                if (inputStream != null) inputStream.close();
            } catch (IOException e) {
                System.err.println("Failed to close input stream: " + e.getMessage());
            }

            try {
                if (outputStream != null) outputStream.close();
            } catch (IOException e) {
                System.err.println("Failed to close output stream: " + e.getMessage());
            }

            try {
                if (socket != null && !socket.isClosed()) socket.close();
            } catch (IOException e) {
                System.err.println("Failed to close socket: " + e.getMessage());
            }
            System.out.println("Disconnected: " + socket.getRemoteSocketAddress());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
