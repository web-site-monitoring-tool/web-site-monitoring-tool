package com.github.wsmt.reporter.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;
import java.util.Optional;

@ConfigurationProperties
public class HBaseProperties {
    private Map<String, String> hBase;

    public Optional<Map<String, String>> getHBase() {
        return Optional.ofNullable(hBase);
    }

    public void setHBase(Map<String, String> hBase) {
        this.hBase = hBase;
    }
}
