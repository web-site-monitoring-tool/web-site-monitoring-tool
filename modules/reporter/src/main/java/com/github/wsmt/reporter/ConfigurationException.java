package com.github.wsmt.reporter;

public class ConfigurationException extends RuntimeException {
    public ConfigurationException(String message) {
        super(message);
    }

    private ConfigurationException(String message, Exception cause) {
        super(message, cause);
    }

    public static ConfigurationException byException(Exception cause) {
        return new ConfigurationException("Can not read configuration", cause);
    }
}
