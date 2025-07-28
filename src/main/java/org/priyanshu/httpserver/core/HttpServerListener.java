package org.priyanshu.httpserver.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServerListener {
    private final int port;
    private final ExecutorService service = Executors.newVirtualThreadPerTaskExecutor();
    private final ServerSocket serverSocket;

    public HttpServerListener(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
    }

    public void listen() {
        System.out.println("Listening on port " + port + "...");
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                service.submit(new HttpConnectionWorker(socket));
            }
        } catch (IOException e) {
            // Consider logging instead of rethrowing
            System.err.println("Server encountered an error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }

    private void shutdown() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Error closing server socket: " + e.getMessage());
        }
        service.close(); // shuts down ExecutorService cleanly
        System.out.println("Server shutdown.");
    }
}
