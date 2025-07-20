package org.priyanshu.httpserver.config;

public class ConfigurationManager {
    private static ConfigurationManager configurationManager;

    public static ConfigurationManager getInstance() {
        if (configurationManager == null) {
            configurationManager = new ConfigurationManager();
        }
        return configurationManager;
    }

    public Configuration getConfiguration() {
        return new Configuration();
    }
    public void loadConfiguration(String filePath) {

    }
}
