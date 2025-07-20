package org.priyanshu.httpserver;

import org.priyanshu.httpserver.config.Configuration;
import org.priyanshu.httpserver.config.ConfigurationManager;

public class HttpServer {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        ConfigurationManager.getInstance().loadConfiguration("src/main/resources/http.json");
        Configuration configuration = ConfigurationManager.getConfiguration();

        System.out.println(configuration.getPort());
        System.out.println(configuration.getWebroot());
    }
}